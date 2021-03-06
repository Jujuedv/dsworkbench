/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.types;

import de.tor.tribes.types.ext.Tribe;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.control.ManageableType;
import de.tor.tribes.io.DataHolder;
import de.tor.tribes.io.ServerManager;
import de.tor.tribes.io.UnitHolder;
import de.tor.tribes.util.BBSupport;
import de.tor.tribes.util.GlobalOptions;
import de.tor.tribes.util.ServerSettings;
import de.tor.tribes.util.bb.AttackListFormatter;
import de.tor.tribes.util.support.SOSFormater;
import de.tor.tribes.util.xml.JaxenUtils;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Torridity
 */
public class SOSRequest extends ManageableType implements BBSupport {

    private final String[] VARIABLES = new String[]{"%SOS_ICON%", "%TARGET%", "%ATTACKS%", "%DEFENDERS%", "%WALL_INFO%", "%WALL_LEVEL%", "%FIRST_ATTACK%", "%LAST_ATTACK%", "%SOURCE_LIST%", "%SOURCE_DATE_TYPE_LIST%", "%ATTACK_LIST%", "%SOURCE_DATE_LIST%", "%SOURCE_TYPE_LIST%", "%SUMMARY%"};
    private final static String STANDARD_TEMPLATE = "[quote]%SOS_ICON% %TARGET% (%ATTACKS%)\n[quote]%DEFENDERS%\n%WALL_INFO%[/quote]\n\n%FIRST_ATTACK%\n%SOURCE_DATE_LIST%\n%LAST_ATTACK%\n\n%SUMMARY%[/quote]";
    private Tribe mDefender = null;
    private Hashtable<Village, TargetInformation> targetInformations = null;
    private Hashtable<Village, DefenseInformation> defenseInformations = null;

    @Override
    public String[] getBBVariables() {
        return VARIABLES;
    }

    public String[] getReplacementsForTarget(Village pTarget, boolean pExtended) {
        String serverURL = ServerManager.getServerURL(GlobalOptions.getSelectedServer());
        //main quote

        //village info size
        String sosImageVal = "[img]" + serverURL + "/graphic/reqdef.png[/img]";
        String targetVal = pTarget.toBBCode();
        String attackCountVal = "[img]" + serverURL + "/graphic/unit/att.png[/img] " + targetInformations.get(pTarget).getAttacks().size();
        //village details quote

        //add units and wall
        String unitVal = buildUnitInfo(targetInformations.get(pTarget));
        String wallInfoVal = "[img]" + serverURL + "/graphic/buildings/wall.png[/img] " + buildWallInfo(targetInformations.get(pTarget));
        String wallLevelVal = Integer.toString(targetInformations.get(pTarget).getWallLevel());

        //build first-last-attack

        List<TimedAttack> atts = targetInformations.get(pTarget).getAttacks();

        Collections.sort(atts, SOSRequest.ARRIVE_TIME_COMPARATOR);

        //add first and last attack information
        SimpleDateFormat dateFormat = null;
        if (ServerSettings.getSingleton().isMillisArrival()) {
            dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
        } else {
            dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        }
        String firstAttackVal = "[img]" + serverURL + "/graphic/map/attack.png[/img] " + dateFormat.format(new Date(atts.get(0).getlArriveTime()));

        //add details for all attacks
        int fakeCount = 0;
        int snobCount = 0;
        String sourceVal = "";
        String sourceDateVal = "";
        String sourceDateTypeVal = "";
        String attackList = "";
        String sourceTypeVal = "";
        List<Attack> thisAttacks = new ArrayList<Attack>();
        for (int i = 0; i < atts.size(); i++) {
            try {
                TimedAttack attack = atts.get(i);
                Attack a = new Attack();
                a.setSource(attack.getSource());
                a.setTarget(pTarget);
                a.setArriveTime(new Date(attack.getlArriveTime()));
                if (attack.isPossibleFake()) {
                    fakeCount++;
                    a.setType(Attack.FAKE_TYPE);
                } else if (attack.isPossibleSnob()) {
                    snobCount++;
                    a.setType(Attack.SNOB_TYPE);
                    a.setUnit(DataHolder.getSingleton().getUnitByPlainName("snob"));
                }
                if (a.getUnit() == null) {
                    a.setUnit(UnknownUnit.getSingleton());
                }
                thisAttacks.add(a);
            } catch (Exception e) {
            }
        }

        attackList = new AttackListFormatter().formatElements(thisAttacks, pExtended);

        for (int i = 0; i < atts.size(); i++) {
            try {
                TimedAttack attack = atts.get(i);
                sourceVal += attack.getSource().toBBCode() + "\n";
                if (attack.isPossibleFake()) {
                    sourceDateTypeVal += attack.getSource().toBBCode() + " " + dateFormat.format(new Date(attack.getlArriveTime())) + " [b](Fake)[/b]" + "\n";
                    sourceDateVal += attack.getSource().toBBCode() + " " + dateFormat.format(new Date(attack.getlArriveTime())) + "\n";
                    sourceTypeVal += attack.getSource().toBBCode() + " [b](Fake)[/b]" + "\n";
                } else if (attack.isPossibleSnob()) {
                    sourceDateTypeVal += attack.getSource().toBBCode() + " " + dateFormat.format(new Date(attack.getlArriveTime())) + " [b](AG)[/b]" + "\n";
                    sourceDateVal += attack.getSource().toBBCode() + " " + dateFormat.format(new Date(attack.getlArriveTime())) + "\n";
                    sourceTypeVal += attack.getSource().toBBCode() + " [b](AG)[/b]" + "\n";
                } else {
                    sourceDateTypeVal += attack.getSource().toBBCode() + " " + dateFormat.format(new Date(attack.getlArriveTime())) + "\n";
                    sourceDateVal += attack.getSource().toBBCode() + " " + dateFormat.format(new Date(attack.getlArriveTime())) + "\n";
                    sourceTypeVal += attack.getSource().toBBCode() + "\n";
                }
            } catch (Exception e) {
            }
        }

        sourceVal = sourceVal.trim();
        sourceTypeVal = sourceTypeVal.trim();
        sourceDateVal = sourceDateVal.trim();
        sourceDateTypeVal = sourceDateTypeVal.trim();
        String lastAttackVal = "[img]" + serverURL + "/graphic/map/return.png[/img] " + dateFormat.format(new Date(atts.get(atts.size() - 1).getlArriveTime()));
        String summaryVal = "[u]Mögliche Fakes:[/u] " + fakeCount + "\n" + "[u]Mögliche AGs:[/u] " + snobCount;

        return new String[]{sosImageVal, targetVal, attackCountVal, unitVal, wallInfoVal, wallLevelVal, firstAttackVal, lastAttackVal, sourceVal, sourceDateTypeVal, attackList, sourceDateVal, sourceTypeVal, summaryVal};
    }

    private String buildUnitInfo(TargetInformation pTargetInfo) {
        StringBuilder buffer = new StringBuilder();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        String defRow = "";
        String offRow = "";

        for (UnitHolder unit : DataHolder.getSingleton().getUnits()) {
            Integer amount = pTargetInfo.getTroops().get(unit);
            if (amount != null && amount != 0) {
                if (unit.getPlainName().equals("spear") || unit.getPlainName().equals("sword") || unit.getPlainName().equals("archer") || unit.getPlainName().equals("spy") || unit.getPlainName().equals("heavy") || unit.getPlainName().equals("knight")) {
                    defRow += unit.toBBCode() + " " + nf.format(amount) + " ";
                } else {
                    offRow += unit.toBBCode() + " " + nf.format(amount) + " ";
                }
            }
        }
        if (defRow.length() > 1) {
            buffer.append(defRow.trim()).append("\n");
        }
        if (offRow.length() > 1) {
            buffer.append(offRow.trim()).append("\n");
        }
        return buffer.toString();
    }

    private String buildWallInfo(TargetInformation pTargetInfo) {
        StringBuilder buffer = new StringBuilder();
        double perc = pTargetInfo.getWallLevel() / 20.0;
        int filledFields = (int) Math.rint(perc * 15.0);
        buffer.append("[color=#00FF00]");
        for (int i = 0; i < filledFields; i++) {
            buffer.append("█");
        }
        buffer.append("[/color]");
        if (filledFields < 15) {
            buffer.append("[color=#EEEEEE]");
            for (int i = 0; i < (15 - filledFields); i++) {
                buffer.append("█");
            }
            buffer.append("[/color]");
        }

        buffer.append(" (").append(pTargetInfo.getWallLevel()).append(")");
        return buffer.toString();
    }

    @Override
    public String[] getReplacements(boolean pExtended) {
        return getReplacementsForTarget(targetInformations.keys().nextElement(), pExtended);
    }

    @Override
    public String getStandardTemplate() {
        return STANDARD_TEMPLATE;
    }

    public SOSRequest() {
        targetInformations = new Hashtable<Village, TargetInformation>();
        defenseInformations = new Hashtable<Village, DefenseInformation>();
    }

    public SOSRequest(Tribe pDefender) {
        this();
        setDefender(pDefender);
    }

    public final void setDefender(Tribe pDefender) {
        mDefender = pDefender;
    }

    public Tribe getDefender() {
        return mDefender;
    }

    public TargetInformation addTarget(Village pTarget) {
        TargetInformation targetInfo = targetInformations.get(pTarget);
        if (targetInfo == null) {
            targetInfo = new TargetInformation(pTarget);
            targetInformations.put(pTarget, targetInfo);
            addDefense(pTarget);
        }
        return targetInfo;
    }

    private DefenseInformation addDefense(Village pTarget) {
        DefenseInformation defenseInfo = defenseInformations.get(pTarget);
        if (defenseInfo == null) {
            defenseInfo = new DefenseInformation(getTargetInformation(pTarget));
            defenseInfo.setAnalyzed(false);
            defenseInformations.put(pTarget, defenseInfo);
        }
        return defenseInfo;
    }

    public void resetDefenses() {
        Enumeration<Village> targets = getTargets();
        while (targets.hasMoreElements()) {
            Village target = targets.nextElement();
            DefenseInformation info = getDefenseInformation(target);
            info.reset();
        }

    }

    public TargetInformation getTargetInformation(Village pTarget) {
        return targetInformations.get(pTarget);
    }

    public DefenseInformation getDefenseInformation(Village pTarget) {
        return defenseInformations.get(pTarget);
    }

    public void removeTarget(Village pTarget) {
        targetInformations.remove(pTarget);
        defenseInformations.remove(pTarget);
    }

    public Enumeration<Village> getTargets() {
        return targetInformations.keys();
    }

    public String toBBCode() {
        return toBBCode(true);
    }

    public String toBBCode(boolean pDetailed) {
        StringBuilder buffer = new StringBuilder();
        Enumeration<Village> targets = getTargets();
        while (targets.hasMoreElements()) {
            Village target = targets.nextElement();
            TargetInformation targetInfo = getTargetInformation(target);
            buffer.append(SOSFormater.format(target, targetInfo, pDetailed));
            buffer.append("\n\n");
        }
        return buffer.toString();
    }

    public String toBBCode(Village pTarget, boolean pDetailed) {
        StringBuilder buffer = new StringBuilder();
        Village target = pTarget;
        TargetInformation targetInfo = getTargetInformation(target);
        if (targetInfo == null) {
            return "";
        }
        buffer.append(SOSFormater.format(target, targetInfo, pDetailed));
        return buffer.toString();
    }

    public void merge(SOSRequest pOther) {
        if (getDefender() == null || pOther == null || pOther.getDefender() == null || getDefender().getId() != pOther.getDefender().getId()) {
            throw new IllegalArgumentException("Cannot merge with unequal defender");
        }

        Enumeration<Village> otherTargets = pOther.getTargets();
        while (otherTargets.hasMoreElements()) {
            Village otherTarget = otherTargets.nextElement();
            TargetInformation otherInfo = pOther.getTargetInformation(otherTarget);
            TargetInformation thisInfo = addTarget(otherTarget);
            thisInfo.setDelta(0);
            thisInfo.setWallLevel(otherInfo.getWallLevel());
            int addCount = 0;
            for (TimedAttack att : otherInfo.getAttacks()) {
                if (thisInfo.addAttack(att.getSource(), new Date(att.getlArriveTime()))) {
                    addCount++;
                    getDefenseInformation(otherTarget).setAnalyzed(false);
                }
            }
            thisInfo.setDelta(addCount);
        }
    }

    @Override
    public String toString() {
        String result = "Verteidiger: " + getDefender() + "\n";
        Enumeration<Village> targets = getTargets();

        while (targets.hasMoreElements()) {
            Village target = targets.nextElement();
            result += " Ziel: " + target + "\n";
            result += getTargetInformation(target);
            //result += "\n";
        }

        return result;
    }

    @Override
    public String getElementIdentifier() {
        return "sosRequest";
    }

    @Override
    public String getElementGroupIdentifier() {
        return "sosRequests";
    }

    @Override
    public String getGroupNameAttributeIdentifier() {
        return "";
    }

    @Override
    public String toXml() {
        try {
            StringBuilder b = new StringBuilder();
            b.append("<").append(getElementIdentifier()).append(" defender=\"").append(getDefender().getId()).append("\">\n");
            b.append("<targetInformations>\n");
            Enumeration<Village> targetKeys = getTargets();
            while (targetKeys.hasMoreElements()) {
                Village target = targetKeys.nextElement();
                TargetInformation targetInfo = getTargetInformation(target);
                if (targetInfo != null) {
                    b.append("<targetInformation target=\"").append(target.getId()).append("\">\n");
                    b.append(targetInfo.toXml());
                    b.append("</targetInformation>\n");
                }
            }
            b.append("</targetInformations>\n");
            b.append("<defenseInformations>\n");
            targetKeys = getTargets();
            while (targetKeys.hasMoreElements()) {
                Village target = targetKeys.nextElement();
                DefenseInformation defense = getDefenseInformation(target);
                if (defense != null) {
                    b.append("<defenseInformation target=\"").
                            append(target.getId()).
                            append("\" analyzed=\"").
                            append(defense.isAnalyzed()).
                            append("\" ignored=\"").
                            append(defense.isIgnored()).append("\">\n");
                    b.append(defense.toXml());
                    b.append("</defenseInformation>\n");
                }
            }
            b.append("</defenseInformations>\n");
            b.append("</").append(getElementIdentifier()).append(">");
            return b.toString();
        } catch (Throwable t) {
            //getting xml data failed
        }
        return null;
    }

    @Override
    public void loadFromXml(Element e) {
        int defenderId = Integer.parseInt(e.getAttributeValue("defender"));
        setDefender(DataHolder.getSingleton().getTribes().get(defenderId));
        for (Element targetInfo : (List<Element>) JaxenUtils.getNodes(e, "targetInformations/targetInformation")) {
            int targetId = Integer.parseInt(targetInfo.getAttributeValue("target"));
            Village target = DataHolder.getSingleton().getVillagesById().get(targetId);
            addTarget(target);
            getTargetInformation(target).loadFromXml(targetInfo);
        }
        for (Element defenseInfo : (List<Element>) JaxenUtils.getNodes(e, "defenseInformations/defenseInformation")) {
            int targetId = Integer.parseInt(defenseInfo.getAttributeValue("target"));
            boolean analyzed = Boolean.parseBoolean(defenseInfo.getAttributeValue("analyzed"));
            boolean ignored = Boolean.parseBoolean(defenseInfo.getAttributeValue("ignored"));
            Village target = DataHolder.getSingleton().getVillagesById().get(targetId);
            DefenseInformation info = addDefense(target);
            info.loadFromXml(defenseInfo);
            info.setAnalyzed(analyzed);
            info.setIgnored(ignored);
        }
    }
    public static final Comparator<TimedAttack> ARRIVE_TIME_COMPARATOR = new ArriveTimeComparator();

    private static class ArriveTimeComparator implements Comparator<TimedAttack>, java.io.Serializable {

        @Override
        public int compare(TimedAttack s1, TimedAttack s2) {
            return s1.getlArriveTime().compareTo(s2.getlArriveTime());
        }
    }
}
