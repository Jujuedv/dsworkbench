/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.renderer;

import de.tor.tribes.types.FarmInformation;
import de.tor.tribes.ui.ImageManager;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

/**
 *
 * @author Torridity
 */
public class FarmStatusCellRenderer extends DefaultTableRenderer {

    private ImageIcon readyIcon = null;
    private ImageIcon notSpyedIcon = null;
    private ImageIcon troopsFoundIcon = null;
    private ImageIcon conqueredIcon = null;
    private ImageIcon farmingIcon = null;
    private ImageIcon reportIcon = null;
    private ImageIcon lockedIcon = null;

    public FarmStatusCellRenderer() {
        super();
        try {
            readyIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/checkbox.png"));
            notSpyedIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/ui/spy_needed.png"));
            troopsFoundIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/ui/red_report_lock.png"));
            conqueredIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/ui/snob_lock.png"));
            farmingIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/ui/trade_in.png"));
            reportIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/ui/report.png"));
            lockedIcon = new ImageIcon(FarmStatusCellRenderer.class.getResource("/res/ui/lock.png"));
        } catch (Exception e) {
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JLabel label = ((JLabel) c);
        try {
            label.setText("");

            label.setHorizontalAlignment(SwingConstants.CENTER);
            FarmInformation.FARM_STATUS status = (FarmInformation.FARM_STATUS) value;
            switch (status) {
                case NOT_SPYED:
                    label.setIcon(notSpyedIcon);
                    break;
                case TROOPS_FOUND:
                    label.setIcon(troopsFoundIcon);
                    break;
                case CONQUERED:
                    label.setIcon(conqueredIcon);
                    break;
                case FARMING:
                    label.setIcon(farmingIcon);
                    break;
                case REPORT_EXPECTED:
                    label.setIcon(reportIcon);
                    break;
                case LOCKED:
                    label.setIcon(lockedIcon);
                    break;
                default:
                    label.setIcon(readyIcon);
            }
        } catch (Exception e) {
            label.setText("?");
            label.setIcon(null);
        }
        return label;
    }
}
