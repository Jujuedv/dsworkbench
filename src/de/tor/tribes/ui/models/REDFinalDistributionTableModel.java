/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.models;

import de.tor.tribes.types.StorageStatus;
import de.tor.tribes.types.VillageMerchantInfo;
import de.tor.tribes.types.ext.Village;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Torridity
 */
public class REDFinalDistributionTableModel extends AbstractTableModel {

    private String[] columnNames = new String[]{
        "Dorf", "Rohstoffe", "Handelsrichtung"
    };
    Class[] types = new Class[]{
        Village.class, StorageStatus.class, VillageMerchantInfo.Direction.class
    };
    private final List<VillageMerchantInfo> elements = new LinkedList<VillageMerchantInfo>();

    public void clear() {
        elements.clear();
        fireTableDataChanged();
    }

    public void addRow(final Village pSource, int pStash, int pWood, int pClay, int pIron, VillageMerchantInfo.Direction pDirection) {
        VillageMerchantInfo newInfo = new VillageMerchantInfo(pSource, pStash, pWood, pClay, pIron, 0, 0);
        newInfo.setDirection(pDirection);
        elements.add(newInfo);
    }

    @Override
    public int getRowCount() {
        if (elements == null) {
            return 0;
        }
        return elements.size();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void removeRow(int row) {
        elements.remove(row);
        fireTableDataChanged();
    }

    public VillageMerchantInfo getRow(int row) {
        return elements.get(row);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (elements == null || elements.size() - 1 < row) {
            return null;
        }
        VillageMerchantInfo element = elements.get(row);
        switch (column) {
            case 0:
                return element.getVillage();
            case 1:
                return new StorageStatus(element.getWoodStock(), element.getClayStock(),
                        element.getIronStock(), element.getStashCapacity());
            default:
                return element.getDirection();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
}
