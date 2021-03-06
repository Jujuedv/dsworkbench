/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.models;

import de.tor.tribes.types.ext.Tribe;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.ui.windows.DSWorkbenchMainFrame;
import de.tor.tribes.util.DSCalculator;
import de.tor.tribes.util.GlobalOptions;
import de.tor.tribes.util.dist.DistanceManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Charon
 */
public class DistanceTableModel extends AbstractTableModel {


    public DistanceTableModel() {
    }

    public void clear() {
        DistanceManager.getSingleton().clear();
    }

    @Override
    public int getRowCount() {
        Tribe currentUser = GlobalOptions.getSelectedProfile().getTribe();
        if (currentUser == null) {
            return 0;
        }
        return currentUser.getVillages();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Village.class;
        }
        return Double.class;
    }

    @Override
    public int getColumnCount() {
        return DistanceManager.getSingleton().getVillages().length + 1;
    }

    @Override
    public String getColumnName(int col) {
        if (col == 0) {
            return "Eigene";
        }
        return DistanceManager.getSingleton().getVillages()[col - 1].toString();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        Village v1 = GlobalOptions.getSelectedProfile().getTribe().getVillageList()[rowIndex];
        if (columnIndex == 0) {
            result = v1;
        } else {
            Village v2 = DistanceManager.getSingleton().getVillages()[columnIndex - 1];
            result = DSCalculator.calculateDistance(v1, v2);
        }
        return result;
    }
}
