/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.types;

import de.tor.tribes.types.ext.Village;
import de.tor.tribes.control.ManageableType;
import de.tor.tribes.io.DataHolder;
import de.tor.tribes.ui.ImageManager;
import de.tor.tribes.util.BBSupport;
import de.tor.tribes.util.bb.VillageListFormatter;
import org.jdom.Element;
import de.tor.tribes.util.xml.JaxenUtils;
import java.awt.Color;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.jdom.Document;

/**
 *
 * @author Charon
 */
public class Tag extends ManageableType implements Comparable<Tag>, BBSupport {

    /**<tags>
     * <tag name="TagName" shownOnMap="true">
     * <village>4711</village>
     * <village>4712</village>
     * </tag>
     * </tags>
     * 
     */
    public static final Comparator<Tag> CASE_INSENSITIVE_ORDER = new CaseInsensitiveTagComparator();
    public static final Comparator<Tag> SIZE_ORDER = new SizeComparator();
    private final static String[] VARIABLES = new String[]{"%NAME%", "%VILLAGE_LIST%", "%VILLAGE_COUNT%", "%COLOR%", "%ICON%"};
    private final static String STANDARD_TEMPLATE = "[u][color=\"%COLOR%\"][b]%NAME%[/b][/color][/u]\n"
            + "%ICON%\n"
            + "Dörfer: %VILLAGE_COUNT%\n"
            + "[quote]%VILLAGE_LIST%[/quote]";
    private String sName = null;
    private List<Integer> mVillageIDs = new LinkedList<Integer>();
    //-1 means no icon
    private TagMapMarker mapMarker = null;
    private boolean showOnMap = true;

    @Override
    public String[] getBBVariables() {
        return VARIABLES;
    }

    @Override
    public String[] getReplacements(boolean pExtended) {
        String nameVal = getName();
        List<Village> villages = new LinkedList<Village>();
        for (Integer id : getVillageIDs()) {
            Village v = DataHolder.getSingleton().getVillagesById().get(id);
            if (v != null) {
                villages.add(v);
            }
        }
        String villageListVal = new VillageListFormatter().formatElements(villages, pExtended);
        String villageCountVal = Integer.toString(getVillageIDs().size());
        String colorVal = "";
        if (getTagColor() != null) {
            colorVal = Integer.toHexString(getTagColor().getRGB());
            colorVal = "#" + colorVal.substring(2, colorVal.length());
        } else {
            colorVal = Integer.toHexString(Color.BLACK.getRGB());
            colorVal = "#" + colorVal.substring(2, colorVal.length());
        }
        String iconVal = "";
        if (getTagIcon() != -1) {
            /*  UnitHolder u = DataHolder.getSingleton().getUnits().get(getTagIcon());
            if (u != null) {
            iconVal = "[unit]" + u.getPlainName() + "[/unit]";
            }*/
            iconVal = "[img]" + ImageManager.getNoteImageURLOnServer(getTagIcon()) + "[/img]";

        }

        return new String[]{nameVal, villageListVal, villageCountVal, colorVal, iconVal};
    }

    @Override
    public String getStandardTemplate() {
        return STANDARD_TEMPLATE;
    }

    @Override
    public void loadFromXml(Element pElement) {
        try {
            String name = URLDecoder.decode(pElement.getChild("name").getTextTrim(), "UTF-8");
            boolean bShowOnMap = Boolean.parseBoolean(pElement.getAttributeValue("shownOnMap"));
            setName(name);
            setShowOnMap(bShowOnMap);
            try {
                Element color = pElement.getChild("color");
                int r = color.getAttribute("r").getIntValue();
                int g = color.getAttribute("g").getIntValue();
                int b = color.getAttribute("b").getIntValue();
                setTagColor(new Color(r, g, b));
            } catch (Exception e) {
                setTagColor(null);
            }

            try {
                Element icon = pElement.getChild("icon");
                setTagIcon(Integer.parseInt(icon.getText()));
            } catch (Exception e) {
                setTagIcon(-1);
            }

            for (Element e : (List<Element>) JaxenUtils.getNodes(pElement, "villages/village")) {
                tagVillage(Integer.parseInt(e.getValue()));
            }
        } catch (Exception e) {
        }
    }

    public Tag() {
        setMapMarker(new TagMapMarker());

    }

    /**Default constructor*/
    public Tag(String pName, boolean pShowOnMap) {
        setName(pName);
        setShowOnMap(pShowOnMap);
        setMapMarker(new TagMapMarker());
    }

    /**Get the tag name
     * @return String Name of this tag
     */
    public String getName() {
        return sName;
    }

    /**Set the tag name
     * @param pName Name of this tag
     */
    public final void setName(String pName) {
        this.sName = pName;
    }

    /**Get the map marker of this tag
     * @return TagMapMarker Map marker of this tag
     */
    public TagMapMarker getMapMarker() {
        return mapMarker;
    }

    /**Tag the village with the ID 'pVillageID' by this tag
     * @param pVillageID ID of the village to tag
     */
    public void tagVillage(Integer pVillageID) {
        if (!mVillageIDs.contains(pVillageID)) {
            mVillageIDs.add(pVillageID);
        }
    }

    /**Remove this tag from the village with the ID 'pVillageID'
     *@param pVillageID ID of the village to untag
     */
    public void untagVillage(Integer pVillageID) {
        mVillageIDs.remove(pVillageID);
    }

    /**Get the list of IDs of villages tagged by this tag
     * @return List<Integer> List of tagged villages IDs
     */
    public List<Integer> getVillageIDs() {
        return mVillageIDs;
    }
  
    /**Check whether this tag tags the village with the ID 'pVillageID' or not
     * @param pVillageID ID of the village to check
     * @return boolean TRUE=Tag tags the village
     */
    public boolean tagsVillage(int pVillageID) {
        return mVillageIDs.contains(pVillageID);
    }

    /**Remove all tagged villages*/
    public void clearTaggedVillages() {
        mVillageIDs.clear();
    }

    /**Set whether to render villages tagged by this tag or not
     * @param pValue TRUE=render villages tagged by this tag
     */
    public final void setShowOnMap(boolean pValue) {
        showOnMap = pValue;
    }

    /**Check whether villages tagged by this tag are rendered or not
     * @return boolean TRUE=tagges villages are rendered
     */
    public boolean isShowOnMap() {
        return showOnMap;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**Convert this tag into its XML representation
     * @return String String that contains the XML representation
     */
    @Override
    public String toXml() {
        try {
            String ret = "<tag shownOnMap=\"" + isShowOnMap() + "\">\n";
            ret += "<name><![CDATA[" + URLEncoder.encode(getName(), "UTF-8") + "]]></name>\n";
            Color c = getTagColor();
            if (c != null) {
                ret += "<color r=\"" + c.getRed() + "\" g=\"" + c.getGreen() + "\" b=\"" + c.getBlue() + "\"/>\n";
            }
            ret += "<icon>" + getTagIcon() + "</icon>\n";
            ret += "<villages>\n";
            for (Integer i : mVillageIDs) {
                ret += "<village>" + i + "</village>\n";
            }
            ret += "</villages>\n";
            ret += "</tag>\n";
            return ret;
        } catch (Exception e) {
            return "\n";
        }
    }

    /**Get the color of the associated TagMapMarker
     * @return Color the TagMapMarker's color
     */
    public Color getTagColor() {
        return getMapMarker().getTagColor();
    }

    /**Set the color of the associated TagMapMarker
     * @param tagColor the TagMapMarker's color
     */
    public void setTagColor(Color tagColor) {
        getMapMarker().setTagColor(tagColor);
    }

    /**Get the icon's ID of the associated TagMapMarker
     * @return the tagIcon
     */
    public int getTagIcon() {
        return getMapMarker().getTagIcon();
    }

    /**Set the icon's ID of the associated TagMapMarker
     * @param tagIcon the tagIcon to set
     */
    public void setTagIcon(int tagIcon) {
        getMapMarker().setTagIcon(tagIcon);
    }

    /**Set the associated TagMapMarker
     * @param mapMarker the mapMarker to set
     */
    public final void setMapMarker(TagMapMarker mapMarker) {
        this.mapMarker = mapMarker;
    }

    @Override
    public String getElementIdentifier() {
        return "tag";
    }

    @Override
    public String getElementGroupIdentifier() {
        return "tags";
    }

    @Override
    public String getGroupNameAttributeIdentifier() {
        return "";
    }

    private static class CaseInsensitiveTagComparator implements Comparator<Tag>, java.io.Serializable {
        // use serialVersionUID from JDK 1.2.2 for interoperability

        private static final long serialVersionUID = 8575799808933029326L;

        @Override
        public int compare(Tag s1, Tag s2) {
            int n1 = s1.toString().length(), n2 = s2.toString().length();
            for (int i1 = 0, i2 = 0; i1 < n1 && i2 < n2; i1++, i2++) {
                char c1 = s1.toString().charAt(i1);
                char c2 = s2.toString().charAt(i2);
                if (c1 != c2) {
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                    if (c1 != c2) {
                        c1 = Character.toLowerCase(c1);
                        c2 = Character.toLowerCase(c2);
                        if (c1 != c2) {
                            return c1 - c2;
                        }
                    }
                }
            }
            return n1 - n2;
        }
    }

    private static class SizeComparator implements Comparator<Tag>, java.io.Serializable {
        // use serialVersionUID from JDK 1.2.2 for interoperability

        private static final long serialVersionUID = 8575799808933029326L;

        @Override
        public int compare(Tag s1, Tag s2) {
            return new Integer(s2.getName().length()).compareTo(new Integer(s1.getName().length()));
        }
    }

    @Override
    public int compareTo(Tag o) {
        return CASE_INSENSITIVE_ORDER.compare(this, o);
    }
}
