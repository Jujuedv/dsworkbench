/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DSWorkbenchConquersFrame.java
 *
 * Created on 23.05.2009, 12:30:59
 */
package de.tor.tribes.ui;

import de.tor.tribes.io.DataHolder;
import de.tor.tribes.types.Ally;
import de.tor.tribes.types.Barbarians;
import de.tor.tribes.types.Conquer;
import de.tor.tribes.types.NoAlly;
import de.tor.tribes.types.Tribe;
import de.tor.tribes.types.Village;
import de.tor.tribes.ui.models.ConquersTableModel;
import de.tor.tribes.ui.renderer.SortableTableHeaderRenderer;
import de.tor.tribes.util.BrowserCommandSender;
import de.tor.tribes.util.Constants;
import de.tor.tribes.util.GlobalOptions;
import de.tor.tribes.util.JOptionPaneHelper;
import de.tor.tribes.util.conquer.AllyFilter;
import de.tor.tribes.util.conquer.ConquerFilterInterface;
import de.tor.tribes.util.conquer.ConquerManager;
import de.tor.tribes.util.conquer.ConquerManagerListener;
import de.tor.tribes.util.conquer.ContinentFilter;
import de.tor.tribes.util.conquer.InternalEnoblementFilter;
import de.tor.tribes.util.conquer.OwnEnoblementFilter;
import de.tor.tribes.util.conquer.TribeFilter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;

/**
 * @author Charon
 */
public class DSWorkbenchConquersFrame extends AbstractDSWorkbenchFrame implements ConquerManagerListener {

    private static Logger logger = Logger.getLogger("ConquerView");
    private static DSWorkbenchConquersFrame SINGLETON = null;
    private TableCellRenderer mHeaderRenderer = null;

    DSWorkbenchConquersFrame() {
        initComponents();
        try {
            jConquersFrameAlwaysOnTop.setSelected(Boolean.parseBoolean(GlobalOptions.getProperty("conquers.frame.alwaysOnTop")));
            setAlwaysOnTop(jConquersFrameAlwaysOnTop.isSelected());
        } catch (Exception e) {
            //setting not available
        }
        mHeaderRenderer = new SortableTableHeaderRenderer();/* new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, row);
        c.setBackground(Constants.DS_BACK);
        DefaultTableCellRenderer r = ((DefaultTableCellRenderer) c);
        r.setText("<html><b>" + r.getText() + "</b></html>");
        return r;
        }
        };*/

        jConquersTable.setColumnSelectionAllowed(false);
        jConquersTable.setModel(ConquersTableModel.getSingleton());

        ConquersTableModel.getSingleton().resetRowSorter(jConquersTable.getModel());
        jConquersTable.setRowSorter(ConquersTableModel.getSingleton().getRowSorter());

        MouseListener l = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2) {
                    ConquersTableModel.getSingleton().getPopup().show(jConquersTable, e.getX(), e.getY());
                    ConquersTableModel.getSingleton().getPopup().requestFocusInWindow();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        jConquersTable.addMouseListener(l);
        jScrollPane1.addMouseListener(l);

        // <editor-fold defaultstate="collapsed" desc=" Init HelpSystem ">
        GlobalOptions.getHelpBroker().enableHelpKey(getRootPane(), "pages.conquers_view", GlobalOptions.getHelpBroker().getHelpSet());
        // </editor-fold>

        pack();
    }

    public JPanel getView() {
        return jConquersPanel;
    }

    public static synchronized DSWorkbenchConquersFrame getSingleton() {
        if (SINGLETON == null) {
            SINGLETON = new DSWorkbenchConquersFrame();
        }
        return SINGLETON;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFilterDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAllyList = new javax.swing.JList();
        jRemoveAllyButton = new javax.swing.JButton();
        jAllySelection = new javax.swing.JComboBox();
        jAddAllyButton = new javax.swing.JButton();
        jAllyFilter = new javax.swing.JTextField();
        jShowAllAllies = new javax.swing.JCheckBox();
        jShowNoAllyTribes = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTribeList = new javax.swing.JList();
        jRemoveTribeButton = new javax.swing.JButton();
        jTribeSelection = new javax.swing.JComboBox();
        jAddTribeButton = new javax.swing.JButton();
        jTribeFilter = new javax.swing.JTextField();
        jShowAllTribes = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jStartContinent = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jEndContinent = new javax.swing.JTextField();
        jShowInternalEnoblements = new javax.swing.JCheckBox();
        jShowOwnEnoblements = new javax.swing.JCheckBox();
        jApplyFiltersButton = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jConquersPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jConquersTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLastUpdateLabel = new javax.swing.JLabel();
        jGreyConquersLabel = new javax.swing.JLabel();
        jFriendlyConquersLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jConquersFrameAlwaysOnTop = new javax.swing.JCheckBox();

        jFilterDialog.setTitle("Eroberungen filtern");
        jFilterDialog.setAlwaysOnTop(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtern nach Stämmen"));

        jAllyList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jAllyList.setEnabled(false);
        jScrollPane2.setViewportView(jAllyList);

        jRemoveAllyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/remove.gif"))); // NOI18N
        jRemoveAllyButton.setToolTipText("Stamm entfernen");
        jRemoveAllyButton.setEnabled(false);
        jRemoveAllyButton.setMaximumSize(new java.awt.Dimension(25, 25));
        jRemoveAllyButton.setMinimumSize(new java.awt.Dimension(25, 25));
        jRemoveAllyButton.setPreferredSize(new java.awt.Dimension(25, 25));
        jRemoveAllyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireChangeAllyListEvent(evt);
            }
        });

        jAllySelection.setToolTipText("Stammesauswahl");
        jAllySelection.setEnabled(false);

        jAddAllyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/add.gif"))); // NOI18N
        jAddAllyButton.setToolTipText("Stamm hinzufügen");
        jAddAllyButton.setEnabled(false);
        jAddAllyButton.setMaximumSize(new java.awt.Dimension(25, 25));
        jAddAllyButton.setMinimumSize(new java.awt.Dimension(25, 25));
        jAddAllyButton.setPreferredSize(new java.awt.Dimension(25, 25));
        jAddAllyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireChangeAllyListEvent(evt);
            }
        });

        jAllyFilter.setToolTipText("Angezeigte Stämme filtern");
        jAllyFilter.setEnabled(false);
        jAllyFilter.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                fireFilterChangedEvent(evt);
            }
        });

        jShowAllAllies.setSelected(true);
        jShowAllAllies.setText("Alle Stämme anzeigen");
        jShowAllAllies.setOpaque(false);
        jShowAllAllies.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireShowAllChangedEvent(evt);
            }
        });

        jShowNoAllyTribes.setSelected(true);
        jShowNoAllyTribes.setText("Stammlose Spieler anzeigen");
        jShowNoAllyTribes.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jAllyFilter)
                            .addComponent(jAllySelection, 0, 158, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAddAllyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jShowAllAllies))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jRemoveAllyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jShowNoAllyTribes, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jShowAllAllies)
                    .addComponent(jShowNoAllyTribes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRemoveAllyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jAddAllyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jAllySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAllyFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtern nach Spielern"));

        jTribeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTribeList.setEnabled(false);
        jScrollPane3.setViewportView(jTribeList);

        jRemoveTribeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/remove.gif"))); // NOI18N
        jRemoveTribeButton.setToolTipText("Spieler entfernen");
        jRemoveTribeButton.setEnabled(false);
        jRemoveTribeButton.setMaximumSize(new java.awt.Dimension(25, 25));
        jRemoveTribeButton.setMinimumSize(new java.awt.Dimension(25, 25));
        jRemoveTribeButton.setPreferredSize(new java.awt.Dimension(25, 25));
        jRemoveTribeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireChangeTribeListEvent(evt);
            }
        });

        jTribeSelection.setToolTipText("Spielerauswahl");
        jTribeSelection.setEnabled(false);

        jAddTribeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/add.gif"))); // NOI18N
        jAddTribeButton.setToolTipText("Spieler hinzufügen");
        jAddTribeButton.setEnabled(false);
        jAddTribeButton.setMaximumSize(new java.awt.Dimension(25, 25));
        jAddTribeButton.setMinimumSize(new java.awt.Dimension(25, 25));
        jAddTribeButton.setPreferredSize(new java.awt.Dimension(25, 25));
        jAddTribeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireChangeTribeListEvent(evt);
            }
        });

        jTribeFilter.setToolTipText("Angezeigte Spieler filtern");
        jTribeFilter.setEnabled(false);
        jTribeFilter.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                fireFilterChangedEvent(evt);
            }
        });

        jShowAllTribes.setSelected(true);
        jShowAllTribes.setText("Alle Spieler anzeigen");
        jShowAllTribes.setOpaque(false);
        jShowAllTribes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireShowAllChangedEvent(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jShowAllTribes)
                        .addGap(247, 247, 247))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTribeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTribeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAddTribeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRemoveTribeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jShowAllTribes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jRemoveTribeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddTribeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTribeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTribeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Sonstige Filter"));

        jLabel1.setText("Kontinent");

        jStartContinent.setMaximumSize(new java.awt.Dimension(60, 20));
        jStartContinent.setMinimumSize(new java.awt.Dimension(60, 20));
        jStartContinent.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel2.setText("bis");

        jEndContinent.setMaximumSize(new java.awt.Dimension(60, 20));
        jEndContinent.setMinimumSize(new java.awt.Dimension(60, 20));
        jEndContinent.setPreferredSize(new java.awt.Dimension(60, 20));

        jShowInternalEnoblements.setSelected(true);
        jShowInternalEnoblements.setText("Aufadelungen anzeigen");
        jShowInternalEnoblements.setOpaque(false);

        jShowOwnEnoblements.setSelected(true);
        jShowOwnEnoblements.setText("Überadelungen anzeigen");
        jShowOwnEnoblements.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jShowOwnEnoblements)
                    .addComponent(jShowInternalEnoblements)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jStartContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jEndContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jStartContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jEndContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jShowInternalEnoblements)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jShowOwnEnoblements)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jApplyFiltersButton.setText("OK");
        jApplyFiltersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireCloseFilterDialogEvent(evt);
            }
        });

        jButton7.setText("Abbrechen");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireCloseFilterDialogEvent(evt);
            }
        });

        javax.swing.GroupLayout jFilterDialogLayout = new javax.swing.GroupLayout(jFilterDialog.getContentPane());
        jFilterDialog.getContentPane().setLayout(jFilterDialogLayout);
        jFilterDialogLayout.setHorizontalGroup(
            jFilterDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFilterDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFilterDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jFilterDialogLayout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jApplyFiltersButton))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jFilterDialogLayout.setVerticalGroup(
            jFilterDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFilterDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFilterDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jApplyFiltersButton)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        setTitle("Eroberungen");

        jConquersPanel.setBackground(new java.awt.Color(239, 235, 223));

        jConquersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jConquersTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(jConquersTable);

        jButton1.setBackground(new java.awt.Color(239, 235, 223));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/center.png"))); // NOI18N
        jButton1.setToolTipText("Gewähltes Dorf auf der Karte zentrieren");
        jButton1.setMaximumSize(new java.awt.Dimension(59, 35));
        jButton1.setMinimumSize(new java.awt.Dimension(59, 35));
        jButton1.setPreferredSize(new java.awt.Dimension(59, 35));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireCenterConqueredVillageEvent(evt);
            }
        });

        jLastUpdateLabel.setText("Letzte Aktualisierung:");

        jGreyConquersLabel.setBackground(new java.awt.Color(255, 204, 204));
        jGreyConquersLabel.setText("Grau-Adelungen:");
        jGreyConquersLabel.setOpaque(true);

        jFriendlyConquersLabel.setBackground(new java.awt.Color(0, 255, 255));
        jFriendlyConquersLabel.setText("Aufadelungen:");
        jFriendlyConquersLabel.setOpaque(true);

        jButton2.setBackground(new java.awt.Color(239, 235, 223));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/filter_off.png"))); // NOI18N
        jButton2.setToolTipText("Eroberungen filtern");
        jButton2.setMaximumSize(new java.awt.Dimension(59, 35));
        jButton2.setMinimumSize(new java.awt.Dimension(59, 35));
        jButton2.setPreferredSize(new java.awt.Dimension(59, 35));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireShowFilterDialogEvent(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(239, 235, 223));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/att_browser.png"))); // NOI18N
        jButton3.setToolTipText("Gewählte Dörfer InGame zentrieren");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireCenterVillagesIngameEvent(evt);
            }
        });

        javax.swing.GroupLayout jConquersPanelLayout = new javax.swing.GroupLayout(jConquersPanel);
        jConquersPanel.setLayout(jConquersPanelLayout);
        jConquersPanelLayout.setHorizontalGroup(
            jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jConquersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jConquersPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jConquersPanelLayout.createSequentialGroup()
                        .addGroup(jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jFriendlyConquersLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                            .addComponent(jGreyConquersLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                            .addComponent(jLastUpdateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                        .addGap(79, 79, 79))))
        );
        jConquersPanelLayout.setVerticalGroup(
            jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jConquersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jConquersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jConquersPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLastUpdateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jGreyConquersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFriendlyConquersLabel)
                .addContainerGap())
        );

        jConquersFrameAlwaysOnTop.setText("Immer im Vordergrund");
        jConquersFrameAlwaysOnTop.setOpaque(false);
        jConquersFrameAlwaysOnTop.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fireConquersFrameAlwaysOnTopEvent(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jConquersFrameAlwaysOnTop)
                    .addComponent(jConquersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jConquersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jConquersFrameAlwaysOnTop)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fireConquersFrameAlwaysOnTopEvent(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fireConquersFrameAlwaysOnTopEvent
        setAlwaysOnTop(!isAlwaysOnTop());
    }//GEN-LAST:event_fireConquersFrameAlwaysOnTopEvent

    private void fireCenterConqueredVillageEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireCenterConqueredVillageEvent
        int[] rows = jConquersTable.getSelectedRows();
        if (rows == null || rows.length == 0) {
            return;
        }
        int row = jConquersTable.convertRowIndexToModel(rows[0]);
        Conquer c = ConquersTableModel.getSingleton().getConquerAtRow(row);

        if (c != null) {
            Village v = DataHolder.getSingleton().getVillagesById().get(c.getVillageID());
            DSWorkbenchMainFrame.getSingleton().centerVillage(v);
        }
    }//GEN-LAST:event_fireCenterConqueredVillageEvent

    private void fireCloseFilterDialogEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireCloseFilterDialogEvent
        if (evt.getSource() == jApplyFiltersButton) {
            List<ConquerFilterInterface> filters = new LinkedList<ConquerFilterInterface>();
            //setup continent filter
            int startConti = 0;
            int endConti = 100;
            try {
                startConti = Integer.parseInt(jStartContinent.getText());
                endConti = Integer.parseInt(jEndContinent.getText());

                if (startConti > endConti) {
                    int tmp = startConti;
                    startConti = endConti;
                    endConti = tmp;
                    jStartContinent.setText(Integer.toString(startConti));
                    jEndContinent.setText(Integer.toString(endConti));
                }

                ContinentFilter filter = new ContinentFilter();
                filter.setup(new Point(startConti, endConti));
                filters.add(filter);
            } catch (Exception e) {
                jStartContinent.setText("0");
                jEndContinent.setText("100");
                startConti = 0;
                endConti = 100;
                ContinentFilter filter = new ContinentFilter();
                filter.setup(new Point(startConti, endConti));
                filters.add(filter);
            }

            //set ally and tribe filters
            if (!jShowAllAllies.isSelected()) {

                if (((DefaultListModel) jAllyList.getModel()).getSize() == 0 && !jShowNoAllyTribes.isSelected()) {
                    JOptionPaneHelper.showInformationBox(jFilterDialog, "Es wurde kein anzuzeigender Stamm ausgewählt.\nDaher werden alle Stämme angezeigt.", "Information");
                } else {
                    List<Ally> shownAllies = new LinkedList<Ally>();
                    if (jShowNoAllyTribes.isSelected()) {
                        //show tribed which belong to no ally
                        shownAllies.add(NoAlly.getSingleton());
                    }
                    for (int i = 0; i < ((DefaultListModel) jAllyList.getModel()).getSize(); i++) {
                        shownAllies.add((Ally) ((DefaultListModel) jAllyList.getModel()).get(i));
                    }
                    AllyFilter allyFilter = new AllyFilter();
                    allyFilter.setup(shownAllies);
                    filters.add(allyFilter);
                }
            }

            if (!jShowAllTribes.isSelected()) {
                if (((DefaultListModel) jTribeList.getModel()).getSize() == 0) {
                    JOptionPaneHelper.showInformationBox(jFilterDialog, "Es wurde kein anzuzeigender Spieler ausgewählt.\nDaher werden alle Spieler angezeigt.", "Information");
                } else {
                    List<Tribe> shownTribes = new LinkedList<Tribe>();
                    for (int i = 0; i < ((DefaultListModel) jTribeList.getModel()).getSize(); i++) {
                        shownTribes.add((Tribe) ((DefaultListModel) jTribeList.getModel()).get(i));
                    }
                    TribeFilter tribeFilter = new TribeFilter();
                    tribeFilter.setup(shownTribes);
                    filters.add(tribeFilter);
                }
            }

            if (!jShowInternalEnoblements.isSelected()) {
                InternalEnoblementFilter filter = new InternalEnoblementFilter();
                filter.setup(false);
                filters.add(filter);
            }

            if (!jShowOwnEnoblements.isSelected()) {
                OwnEnoblementFilter filter = new OwnEnoblementFilter();
                filter.setup(false);
                filters.add(filter);
            }

            ConquerManager.getSingleton().setFilters(filters);
            fireConquersChangedEvent();
        }
        jFilterDialog.setVisible(false);

    }//GEN-LAST:event_fireCloseFilterDialogEvent

    private void fireShowAllChangedEvent(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fireShowAllChangedEvent
        if (evt.getSource() == jShowAllAllies) {
            jAllyFilter.setEnabled(!jShowAllAllies.isSelected());
            jAllySelection.setEnabled(!jShowAllAllies.isSelected());
            jAllyList.setEnabled(!jShowAllAllies.isSelected());
            jAddAllyButton.setEnabled(!jShowAllAllies.isSelected());
            jRemoveAllyButton.setEnabled(!jShowAllAllies.isSelected());
        } else {
            //show all tribes
            jTribeFilter.setEnabled(!jShowAllTribes.isSelected());
            jTribeSelection.setEnabled(!jShowAllTribes.isSelected());
            jTribeList.setEnabled(!jShowAllTribes.isSelected());
            jAddTribeButton.setEnabled(!jShowAllTribes.isSelected());
            jRemoveTribeButton.setEnabled(!jShowAllTribes.isSelected());
        }
    }//GEN-LAST:event_fireShowAllChangedEvent

    private void fireChangeAllyListEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireChangeAllyListEvent
        if (evt.getSource() == jAddAllyButton) {
            //add ally
            jAllySelection.firePopupMenuCanceled();
            Ally a = (Ally) jAllySelection.getSelectedItem();
            if (a != null) {
                if (((DefaultListModel) jAllyList.getModel()).indexOf(a) < 0) {
                    ((DefaultListModel) jAllyList.getModel()).addElement(a);
                }
            }
        } else {
            //remove ally
            Ally a = (Ally) jAllyList.getSelectedValue();
            if (a != null) {
                if (JOptionPaneHelper.showQuestionConfirmBox(jFilterDialog, "Gewählten Stamm entfernen?", "Stamm entfernen", "Nein", "Ja") == JOptionPane.YES_OPTION) {
                    ((DefaultListModel) jAllyList.getModel()).removeElement(a);
                }
            }
        }
    }//GEN-LAST:event_fireChangeAllyListEvent

    private void fireChangeTribeListEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireChangeTribeListEvent
        if (evt.getSource() == jAddTribeButton) {
            //add tribe
            jTribeSelection.firePopupMenuCanceled();
            Tribe t = (Tribe) jTribeSelection.getSelectedItem();
            if (t != Barbarians.getSingleton()) {
                if (((DefaultListModel) jTribeList.getModel()).indexOf(t) < 0) {
                    ((DefaultListModel) jTribeList.getModel()).addElement(t);
                }
            }
        } else {
            //remove tribe
            Tribe t = (Tribe) jTribeList.getSelectedValue();
            if (t != Barbarians.getSingleton()) {
                if (JOptionPaneHelper.showQuestionConfirmBox(jFilterDialog, "Gewählten Spieler entfernen?", "Spieler entfernen", "Nein", "Ja") == JOptionPane.YES_OPTION) {
                    ((DefaultListModel) jTribeList.getModel()).removeElement(t);
                }
            }
        }
    }//GEN-LAST:event_fireChangeTribeListEvent

    private void fireShowFilterDialogEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireShowFilterDialogEvent
        jFilterDialog.pack();
        jFilterDialog.setLocationRelativeTo(this);
        jFilterDialog.setVisible(true);
    }//GEN-LAST:event_fireShowFilterDialogEvent

    private void fireFilterChangedEvent(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_fireFilterChangedEvent
        if (evt.getSource() == jAllyFilter) {
            buildAllyList(jAllyFilter.getText());
        } else {
            buildTribesList(jTribeFilter.getText());
        }
    }//GEN-LAST:event_fireFilterChangedEvent

    private void fireCenterVillagesIngameEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireCenterVillagesIngameEvent
        int[] rows = jConquersTable.getSelectedRows();
        if (rows == null || rows.length == 0) {
            return;
        }
        int cnt = 0;
        for (int r : rows) {
            int row = jConquersTable.convertRowIndexToModel(r);
            Conquer c = ConquersTableModel.getSingleton().getConquerAtRow(row);

            if (c != null) {
                Village v = DataHolder.getSingleton().getVillagesById().get(c.getVillageID());
                BrowserCommandSender.centerVillage(v);
            }
            cnt++;
            if (cnt == 10) {
                //max. 10 villages
                return;
            }
        }
    }//GEN-LAST:event_fireCenterVillagesIngameEvent

    protected void setupConquersPanel() {
        jConquersTable.invalidate();
        jConquersTable.setModel(new DefaultTableModel());
        jConquersTable.revalidate();

        jConquersTable.setModel(ConquersTableModel.getSingleton());
        ConquerManager.getSingleton().addConquerManagerListener(this);
        jConquersTable.getTableHeader().setReorderingAllowed(false);
        /* jConquersTable.setDefaultRenderer(Village.class, new VillageCellRenderer());
        jConquersTable.setDefaultRenderer(Tribe.class, new TribeCellRenderer());
        jConquersTable.setDefaultRenderer(Ally.class, new AllyCellRenderer());*/

        //setup renderer and general view
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jConquersTable.getModel());
        jConquersTable.setRowSorter(sorter);
        jScrollPane1.getViewport().setBackground(Constants.DS_BACK_LIGHT);
        //update view
        for (int i = 0; i < jConquersTable.getColumnCount(); i++) {
            TableColumn column = jConquersTable.getColumnModel().getColumn(i);
            column.setHeaderRenderer(mHeaderRenderer);
        }
        jConquersTable.revalidate();
        setupFilterDialog();
        ConquerManager.getSingleton().updateFilters();
        ConquerManager.getSingleton().conquersUpdatedExternally();
    }

    private void setupFilterDialog() {
        jAllyList.setModel(new DefaultListModel());
        jTribeList.setModel(new DefaultListModel());
        buildAllyList(null);
        buildTribesList(null);

        jShowAllAllies.setSelected(true);
        jAllyFilter.setEnabled(!jShowAllAllies.isSelected());
        jAllySelection.setEnabled(!jShowAllAllies.isSelected());
        jAllyList.setEnabled(!jShowAllAllies.isSelected());
        jAddAllyButton.setEnabled(!jShowAllAllies.isSelected());
        jRemoveAllyButton.setEnabled(!jShowAllAllies.isSelected());
        jShowAllTribes.setSelected(true);
        jTribeFilter.setEnabled(!jShowAllTribes.isSelected());
        jTribeSelection.setEnabled(!jShowAllTribes.isSelected());
        jTribeList.setEnabled(!jShowAllTribes.isSelected());
        jAddTribeButton.setEnabled(!jShowAllTribes.isSelected());
        jRemoveTribeButton.setEnabled(!jShowAllTribes.isSelected());

        jStartContinent.setText("0");
        jEndContinent.setText("100");
        jShowInternalEnoblements.setSelected(true);
        jShowOwnEnoblements.setSelected(true);
    }

    private void buildAllyList(String pFilter) {
        Enumeration<Integer> allyIds = DataHolder.getSingleton().getAllies().keys();
        List<Ally> allies = new LinkedList<Ally>();
        while (allyIds.hasMoreElements()) {
            Ally a = DataHolder.getSingleton().getAllies().get(allyIds.nextElement());
            if (a != null
                    && (pFilter == null
                    || (pFilter.length() == 0)
                    || (a.getName().toLowerCase().indexOf(pFilter.toLowerCase()) >= 0)
                    || (a.getTag().toLowerCase().indexOf(pFilter.toLowerCase()) >= 0))) {
                allies.add(a);
            }

            Collections.sort(allies);
            jAllySelection.setModel(new DefaultComboBoxModel(allies.toArray(new Ally[]{})));
        }

    }

    private void buildTribesList(String pFilter) {
        Enumeration<Integer> tribeIds = DataHolder.getSingleton().getTribes().keys();
        List<Tribe> tribes = new LinkedList<Tribe>();
        while (tribeIds.hasMoreElements()) {
            Tribe t = DataHolder.getSingleton().getTribes().get(tribeIds.nextElement());
            if (t != Barbarians.getSingleton()
                    && (pFilter == null
                    || (pFilter.length() == 0)
                    || (t.getName().toLowerCase().indexOf(pFilter.toLowerCase()) >= 0))) {
                tribes.add(t);
            }

        }
        Collections.sort(tribes);
        jTribeSelection.setModel(new DefaultComboBoxModel(tribes.toArray(new Tribe[]{})));
    }

    @Override
    public void fireVillagesDraggedEvent(List<Village> pVillages, Point pDropLocation) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DSWorkbenchConquersFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddAllyButton;
    private javax.swing.JButton jAddTribeButton;
    private javax.swing.JTextField jAllyFilter;
    private javax.swing.JList jAllyList;
    private javax.swing.JComboBox jAllySelection;
    private javax.swing.JButton jApplyFiltersButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jConquersFrameAlwaysOnTop;
    private javax.swing.JPanel jConquersPanel;
    private javax.swing.JTable jConquersTable;
    private javax.swing.JTextField jEndContinent;
    private javax.swing.JDialog jFilterDialog;
    private javax.swing.JLabel jFriendlyConquersLabel;
    private javax.swing.JLabel jGreyConquersLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLastUpdateLabel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jRemoveAllyButton;
    private javax.swing.JButton jRemoveTribeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JCheckBox jShowAllAllies;
    private javax.swing.JCheckBox jShowAllTribes;
    private javax.swing.JCheckBox jShowInternalEnoblements;
    private javax.swing.JCheckBox jShowNoAllyTribes;
    private javax.swing.JCheckBox jShowOwnEnoblements;
    private javax.swing.JTextField jStartContinent;
    private javax.swing.JTextField jTribeFilter;
    private javax.swing.JList jTribeList;
    private javax.swing.JComboBox jTribeSelection;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fireConquersChangedEvent() {
        jConquersTable.invalidate();
        jConquersTable.setModel(ConquersTableModel.getSingleton());
        jConquersTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                try {
                    row = jConquersTable.convertRowIndexToModel(row);
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            c.setBackground(Constants.DS_ROW_B);
                        } else {
                            c.setBackground(Constants.DS_ROW_A);
                        }
                    }
                    Conquer con = ConquerManager.getSingleton().getConquer(row);
                    Tribe loser = DataHolder.getSingleton().getTribes().get(con.getLoser());
                    if (loser == null) {
                        c.setBackground(Color.PINK);
                    } else {
                        Tribe winner = DataHolder.getSingleton().getTribes().get(con.getWinner());
                        if (loser != null && winner != null) {
                            if (loser.getId() == winner.getId()) {
                                //self enoblement
                                c.setBackground(Color.GREEN);
                            } else if (loser.getAllyID() == winner.getAllyID()) {
                                //internal enoblement v1?
                                if (loser.getAllyID() != 0 && winner.getAllyID() != 0) {
                                    c.setBackground(Color.CYAN);
                                }
                            } else {
                                Ally loserAlly = loser.getAlly();
                                Ally winnerAlly = winner.getAlly();
                                if (loserAlly != null && winnerAlly != null) {
                                    String lAllyName = loserAlly.getName().toLowerCase();
                                    String wAllyName = winnerAlly.getName().toLowerCase();
                                    if (lAllyName.indexOf(wAllyName) > -1 || wAllyName.indexOf(lAllyName) > -1) {
                                        //internal enoblement v2
                                        c.setBackground(Color.CYAN);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }

                try {
                    if (value instanceof Village) {
                        ((JLabel) c).setToolTipText(((Village) value).getToolTipText());
                    } else if (value instanceof Tribe) {
                        ((JLabel) c).setToolTipText(((Tribe) value).getToolTipText());
                    } else if (value instanceof Ally) {
                        ((JLabel) c).setToolTipText(((Ally) value).getToolTipText());
                    }
                } catch (Exception e) {
                }

                if (value instanceof Double) {
                    //format dist col
                    double v = (Double) value;
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMinimumFractionDigits(2);
                    nf.setMaximumFractionDigits(2);
                    ((JLabel) c).setText(nf.format(v));
                }
                return c;
            }
        };

        jConquersTable.setDefaultRenderer(Object.class, renderer);
        jConquersTable.setDefaultRenderer(Double.class, renderer);
        jConquersTable.setDefaultRenderer(Integer.class, renderer);

        for (int i = 0; i < jConquersTable.getColumnCount(); i++) {
            TableColumn c = jConquersTable.getColumnModel().getColumn(i);
            c.setHeaderRenderer(mHeaderRenderer);
        }
        //setup row sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>();
        jConquersTable.setRowSorter(sorter);
        sorter.setModel(ConquersTableModel.getSingleton());
        /* sorter.setComparator(0, Village.CASE_INSENSITIVE_ORDER);
        sorter.setComparator(3, Tribe.CASE_INSENSITIVE_ORDER);
        sorter.setComparator(4, Ally.CASE_INSENSITIVE_ORDER);
        sorter.setComparator(5, Tribe.CASE_INSENSITIVE_ORDER);
        sorter.setComparator(6, Ally.CASE_INSENSITIVE_ORDER);*/
        jConquersTable.revalidate();
        jConquersTable.repaint();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ConquerManager.getSingleton().getLastUpdate());
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        jLastUpdateLabel.setText("<html><b>Letzte Aktualisierung:</b> " + f.format(c.getTime()) + "</html>");

        int[] conquerStats = ConquerManager.getSingleton().getConquersStats();
        int conquers = ConquerManager.getSingleton().getConquerCount();
        int percGrey = (int) Math.rint(100.0 * (double) conquerStats[0] / (double) conquers);
        int percFriendly = (int) Math.rint(100.0 * (double) conquerStats[1] / (double) conquers);

        jGreyConquersLabel.setText("<html><b>Grau-Adelungen:</b> " + conquerStats[0] + " von " + conquers + " (" + percGrey + "%)" + "</html>");
        jFriendlyConquersLabel.setText("<html><b>Aufadelungen:</b> " + conquerStats[1] + " von " + conquers + " (" + percFriendly + "%)" + "</html>");

    }
}
