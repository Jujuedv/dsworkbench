/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.dnd;

import de.tor.tribes.types.ext.Village;
import de.tor.tribes.util.DSCalculator;
import de.tor.tribes.util.ServerSettings;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Torridity
 */
public class VillageTransferable implements Transferable {

    private List<Village> villages = null;

    public VillageTransferable(Village pVillage) {
        villages = new LinkedList<Village>();
        villages.add(pVillage);
    }

    public VillageTransferable(List<Village> pVillage) {
        villages = pVillage;
    }
    // This is the custom DataFlavor for Scribble objects
    public static DataFlavor villageDataFlavor = new DataFlavor(VillageTransferable.class, "Village");
    // This is a list of the flavors we know how to work with
    public static DataFlavor[] supportedFlavors = {villageDataFlavor, DataFlavor.stringFlavor};

    /** Return the data formats or "flavors" we know how to transfer */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return (DataFlavor[]) supportedFlavors.clone();
    }

    /** Check whether we support a given flavor */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return (flavor.equals(villageDataFlavor) || flavor.equals(DataFlavor.stringFlavor));
    }

    /**
     * Return the scribble data in the requested format, or throw an exception
     * if we don't support the requested format
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(villageDataFlavor)) {
            return villages;
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
            if (ServerSettings.getSingleton().getCoordType() != 2) {
                int[] coord = DSCalculator.xyToHierarchical(villages.get(0).getX(), villages.get(0).getY());
                return coord[0] + ":" + coord[1] + ":" + coord[2];
            } else {
                return villages.get(0).getX() + "|" + villages.get(0).getY();
            }
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
