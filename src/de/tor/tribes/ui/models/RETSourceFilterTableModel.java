/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.models;

import de.tor.tribes.types.ext.Tribe;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.ui.wiz.ret.types.RETSourceElement;
import de.tor.tribes.ui.wiz.tap.types.TAPAttackSourceElement;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Torridity
 */
public class RETSourceFilterTableModel extends AbstractTableModel {

    private String[] columnNames = new String[]{
        "Dorf", "Ignoriert"
    };
    private Class[] types = new Class[]{
        Village.class, Boolean.class
    };
    private final List<RETSourceElement> elements = new LinkedList<RETSourceElement>();

    public RETSourceFilterTableModel() {
        super();
    }

    public void clear() {
        elements.clear();
    }

    public void addRow(RETSourceElement pElement, boolean pCheck) {
        if (!elements.contains(pElement)) {
            elements.add(pElement);
        }
        if (pCheck) {
            fireTableDataChanged();
        }
    }

    public void addRow(RETSourceElement pElement) {
        addRow(pElement, true);
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

    public RETSourceElement getRow(int row) {
        return elements.get(row);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (elements == null || elements.size() - 1 < row) {
            return null;
        }
        RETSourceElement element = elements.get(row);
        switch (column) {
            case 0:
                return element.getVillage();
            default:
                return element.isIgnored();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
}
