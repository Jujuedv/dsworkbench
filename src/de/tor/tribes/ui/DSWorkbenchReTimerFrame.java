/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DSWorkbenchReTimerFrame.java
 *
 * Created on 22.12.2009, 13:43:21
 */
package de.tor.tribes.ui;

import de.tor.tribes.io.DataHolder;
import de.tor.tribes.io.UnitHolder;
import de.tor.tribes.types.Tag;
import de.tor.tribes.types.Village;
import de.tor.tribes.ui.renderer.UnitListCellRenderer;
import de.tor.tribes.util.DSCalculator;
import de.tor.tribes.util.JOptionPaneHelper;
import de.tor.tribes.util.parser.VillageParser;
import de.tor.tribes.util.tag.TagManager;
import de.tor.tribes.util.tag.TagManagerListener;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import org.apache.log4j.Logger;

/**
 *
 * @author Jejkal
 */
public class DSWorkbenchReTimerFrame extends AbstractDSWorkbenchFrame implements TagManagerListener {

    private static Logger logger = Logger.getLogger("ReTimeTool");
    private static DSWorkbenchReTimerFrame SINGLETON = null;

    public static synchronized DSWorkbenchReTimerFrame getSingleton() {
        if (SINGLETON == null) {
            SINGLETON = new DSWorkbenchReTimerFrame();
        }
        return SINGLETON;
    }

    /** Creates new form DSWorkbenchReTimerFrame */
    DSWorkbenchReTimerFrame() {
        initComponents();
    }


    /*OPERA
    Herkunft	Spieler:	Rattenfutter
    Dorf:	001 Rattennest (486|833) K84
    Ziel	Spieler:	Rattenfutter
    Dorf:	005 Rattennest (486|834) K84
    Ankunft:	22.12.09 13:57:44:321
    Ankunft in:	0:08:34
     *
     *
     *
     * FF
    Herkunft	Spieler:	Rattenfutter
    Dorf:	001 Rattennest (486|833) K84
    Ziel	Spieler:	Rattenfutter
    Dorf:	005 Rattennest (486|834) K84
    Dauer:	0:09:00
    Ankunft:	22.12.09 14:02:30:232
    Ankunft in:	0:08:41
    » abbrechen
     */
    public void setup() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (UnitHolder unit : DataHolder.getSingleton().getUnits()) {
            model.addElement(unit);
        }
        jUnitBox.setModel(model);
        jUnitBox.setRenderer(new UnitListCellRenderer());
        DefaultListModel tagModel = new DefaultListModel();
        for (Tag t : TagManager.getSingleton().getTags()) {
            tagModel.addElement(t);
        }
        jTagList.setModel(tagModel);
        jRelationBox.setSelected(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComandArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSourceVillage = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTargetVillage = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jArriveField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jAxeBox = new javax.swing.JCheckBox();
        jSwordBox = new javax.swing.JCheckBox();
        jSpyBox = new javax.swing.JCheckBox();
        jLightBox = new javax.swing.JCheckBox();
        jHeavyBox = new javax.swing.JCheckBox();
        jRamBox = new javax.swing.JCheckBox();
        jPalaBox = new javax.swing.JCheckBox();
        jSnobBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jEstSendTime = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jReturnField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jParserInfo = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTagList = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jRelationBox = new javax.swing.JCheckBox();
        jUnitBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        jPanel1.setBackground(new java.awt.Color(239, 235, 223));

        jComandArea.setColumns(20);
        jComandArea.setRows(5);
        jComandArea.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                fireComandDataChangedEvent(evt);
            }
        });
        jScrollPane1.setViewportView(jComandArea);

        jLabel1.setText("Angriffsbefehl");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Gelesene Werte"));
        jPanel2.setOpaque(false);

        jLabel2.setText("Herkunft");

        jLabel3.setText("Ziel");

        jLabel4.setText("Ankunft");

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(4, 2));

        buttonGroup1.add(jAxeBox);
        jAxeBox.setText("Axt");
        jAxeBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jAxeBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jAxeBox.setDoubleBuffered(true);
        jAxeBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jAxeBox.setOpaque(false);
        jAxeBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jAxeBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jAxeBox);

        buttonGroup1.add(jSwordBox);
        jSwordBox.setText("Schwert");
        jSwordBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jSwordBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jSwordBox.setDoubleBuffered(true);
        jSwordBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jSwordBox.setOpaque(false);
        jSwordBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jSwordBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jSwordBox);

        buttonGroup1.add(jSpyBox);
        jSpyBox.setText("Späher");
        jSpyBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jSpyBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jSpyBox.setDoubleBuffered(true);
        jSpyBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jSpyBox.setOpaque(false);
        jSpyBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jSpyBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jSpyBox);

        buttonGroup1.add(jLightBox);
        jLightBox.setText("LKav");
        jLightBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jLightBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jLightBox.setDoubleBuffered(true);
        jLightBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jLightBox.setOpaque(false);
        jLightBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jLightBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jLightBox);

        buttonGroup1.add(jHeavyBox);
        jHeavyBox.setText("SKav");
        jHeavyBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jHeavyBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jHeavyBox.setDoubleBuffered(true);
        jHeavyBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jHeavyBox.setOpaque(false);
        jHeavyBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jHeavyBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jHeavyBox);

        buttonGroup1.add(jRamBox);
        jRamBox.setText("Ramme");
        jRamBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jRamBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jRamBox.setDoubleBuffered(true);
        jRamBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jRamBox.setOpaque(false);
        jRamBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jRamBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jRamBox);

        buttonGroup1.add(jPalaBox);
        jPalaBox.setText("Paladin");
        jPalaBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jPalaBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jPalaBox.setDoubleBuffered(true);
        jPalaBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jPalaBox.setOpaque(false);
        jPalaBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jPalaBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jPalaBox);

        buttonGroup1.add(jSnobBox);
        jSnobBox.setText("AG");
        jSnobBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jSnobBox.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_red.png"))); // NOI18N
        jSnobBox.setDoubleBuffered(true);
        jSnobBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_grey.png"))); // NOI18N
        jSnobBox.setOpaque(false);
        jSnobBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bullet_ball_green.png"))); // NOI18N
        jSnobBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEstUnitChangedEvent(evt);
            }
        });
        jPanel3.add(jSnobBox);

        jLabel6.setText("Abschickzeit");

        jEstSendTime.setEnabled(false);

        jLabel9.setText("Rückkehr");

        jReturnField.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jReturnField, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(jArriveField, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(jSourceVillage, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(jTargetVillage, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(jEstSendTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jSourceVillage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTargetVillage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jEstSendTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jArriveField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jReturnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setText("Status");

        jScrollPane2.setBorder(null);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 50));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(21, 50));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(2, 50));

        jParserInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jParserInfo.setEditable(false);
        jScrollPane2.setViewportView(jParserInfo);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Gegentimen"));
        jPanel4.setOpaque(false);

        jScrollPane3.setViewportView(jTagList);

        jLabel7.setText("Dorfgruppe");

        jRelationBox.setSelected(true);
        jRelationBox.setText("Verknüpfung");
        jRelationBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/logic_or.png"))); // NOI18N
        jRelationBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/logic_and.png"))); // NOI18N

        jUnitBox.setMaximumSize(new java.awt.Dimension(40, 25));
        jUnitBox.setMinimumSize(new java.awt.Dimension(40, 25));
        jUnitBox.setPreferredSize(new java.awt.Dimension(40, 25));

        jLabel8.setText("Einheit");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/axe.png"))); // NOI18N
        jButton1.setText("Berechnen");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireCalculateReTimingsEvent(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRelationBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jUnitBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jUnitBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRelationBox)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jCheckBox1.setText("Immer im Vordergrund");
        jCheckBox1.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fireComandDataChangedEvent(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_fireComandDataChangedEvent
        List<Village> villages = VillageParser.parse(jComandArea.getText());
        if (villages == null || villages.isEmpty() || villages.size() < 2) {
            jParserInfo.setBackground(Color.YELLOW);
            jParserInfo.setText("Keine Dörfer gefunden.\n" +
                    "Möglicherweise handelt es sich nicht um einen gültigen Angriffsbefehl.");
            return;
        }
        Village source = villages.get(0);
        Village target = villages.get(1);
        jSourceVillage.setText(source.toString());
        jTargetVillage.setText(target.toString());
        boolean fromSelection = false;
        Date arriveDate = null;
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yy HH:mm:ss:SSS");
        try {
            String text = jComandArea.getText();
            String selection = jComandArea.getSelectedText();
            String arrive = null;

            if (selection == null) {
                String arriveLine = text.substring(text.indexOf("Ankunft:"));
                StringTokenizer tokenizer = new StringTokenizer(arriveLine, " \t");
                tokenizer.nextToken();
                String date = tokenizer.nextToken();
                String time = tokenizer.nextToken();
                arrive = date.trim() + " " + time.trim();
            } else {
                fromSelection = true;
                arrive = selection;
            }

            arriveDate = f.parse(arrive);
            f = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.SSS");
            jArriveField.setText(f.format(arriveDate));
            jParserInfo.setBackground(Color.GREEN);
            jParserInfo.setText("Angriffsbefehl erfolgreich gelesen.");
        } catch (Exception e) {
            if (!fromSelection) {
                jParserInfo.setBackground(Color.RED);
                jParserInfo.setText("Es konnte keine Ankunftszeit gefunden werden.\nBitte markiere im oberen Textfeld die Ankunftszeit und -datum.");
                return;
            } else {
                jParserInfo.setBackground(Color.RED);
                jParserInfo.setText("Aus der Auswahl konnte keine Ankunftszeit bestimmt werden.\nBitte versuche, den Angriffsbefehl erneut zu kopieren oder wende dich an den DS Workbench Support.");
                return;
            }
        }

        //calc possible units
        double dist = DSCalculator.calculateDistance(source, target);

        UnitHolder axe = DataHolder.getSingleton().getUnitByPlainName("axe");
        long dur = (long) Math.floor(dist * axe.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jAxeBox.setEnabled(false);
        } else {
            jAxeBox.setEnabled(true);
        }


        UnitHolder sword = DataHolder.getSingleton().getUnitByPlainName("sword");
        dur = (long) Math.floor(dist * sword.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jSwordBox.setEnabled(false);
        } else {
            jSwordBox.setEnabled(true);
        }
        /*
        Herkunft	Spieler:	Rattenfutter
        Dorf:	065 Rattennest (474|850) K84
        Ziel	Spieler:	Rattenfutter
        Dorf:	020 Rattennest (476|850) K84
        Dauer:	0:18:00
        Ankunft:	22.12.09 20:00:38:362
        Ankunft in:	0:17:55
         */
        UnitHolder spy = DataHolder.getSingleton().getUnitByPlainName("spy");
        dur = (long) Math.floor(dist * spy.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jSpyBox.setEnabled(false);
        } else {
            jSpyBox.setEnabled(true);
        }

        UnitHolder light = DataHolder.getSingleton().getUnitByPlainName("light");
        dur = (long) Math.floor(dist * light.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jLightBox.setEnabled(false);
        } else {
            jLightBox.setEnabled(true);
        }

        UnitHolder heavy = DataHolder.getSingleton().getUnitByPlainName("heavy");
        dur = (long) Math.floor(dist * heavy.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jHeavyBox.setEnabled(false);
        } else {
            jHeavyBox.setEnabled(true);
        }


        UnitHolder ram = DataHolder.getSingleton().getUnitByPlainName("ram");
        dur = (long) Math.floor(dist * ram.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jRamBox.setEnabled(false);
        } else {
            jRamBox.setEnabled(true);
        }

        UnitHolder pala = DataHolder.getSingleton().getUnitByPlainName("knight");
        dur = (long) Math.floor(dist * pala.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jPalaBox.setEnabled(false);
        } else {
            jPalaBox.setEnabled(true);
        }

        UnitHolder snob = DataHolder.getSingleton().getUnitByPlainName("snob");
        dur = (long) Math.floor(dist * snob.getSpeed() * 60000.0);
        if (arriveDate.getTime() - dur > System.currentTimeMillis()) {
            //send time would be in future, unit not possible
            jSnobBox.setEnabled(false);
        } else {
            jSnobBox.setEnabled(true);
        }

        if (jSnobBox.isEnabled()) {
            jSnobBox.setSelected(true);
            fireEstUnitChangedEvent(new ItemEvent(jSnobBox, 0, null, 0));
        } else if (jRamBox.isEnabled()) {
            jRamBox.setSelected(true);
            fireEstUnitChangedEvent(new ItemEvent(jRamBox, 0, null, 0));
        } else if (jAxeBox.isEnabled()) {
            jAxeBox.setSelected(true);
            fireEstUnitChangedEvent(new ItemEvent(jAxeBox, 0, null, 0));
        } else {
            jSpyBox.setSelected(true);
            jEstSendTime.setText("(unbekannt)");
        }


    }//GEN-LAST:event_fireComandDataChangedEvent

    private void fireEstUnitChangedEvent(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fireEstUnitChangedEvent
        UnitHolder unit = null;
        if (evt.getSource() == jAxeBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("axe");
        } else if (evt.getSource() == jSwordBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("sword");
        } else if (evt.getSource() == jSpyBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("spy");
        } else if (evt.getSource() == jLightBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("light");
        } else if (evt.getSource() == jHeavyBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("heavy");
        } else if (evt.getSource() == jRamBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("ram");
        } else if (evt.getSource() == jPalaBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("knight");
        } else if (evt.getSource() == jSnobBox) {
            unit = DataHolder.getSingleton().getUnitByPlainName("snob");
        }
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.SSS");
        try {
            Date arrive = f.parse(jArriveField.getText());
            Village source = VillageParser.parse(jSourceVillage.getText()).get(0);
            Village target = VillageParser.parse(jTargetVillage.getText()).get(0);
            double dist = DSCalculator.calculateDistance(source, target);
            long dur = (long) Math.floor(dist * unit.getSpeed() * 60000.0);
            long send = arrive.getTime() - dur;
            double ret = (double) arrive.getTime() + (double) dur;
            ret /= 1000;
            ret = Math.round(ret + .5);
            ret *= 1000;
            jEstSendTime.setText("~ " + f.format(new Date(send)));
            jReturnField.setText("~ " + f.format(new Date((long) ret)));
        } catch (Exception e) {
            jEstSendTime.setText("(unbekannt)");
            jReturnField.setText("(unbekannt)");
        }

    }//GEN-LAST:event_fireEstUnitChangedEvent

    private void fireCalculateReTimingsEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireCalculateReTimingsEvent
        Object[] tags = jTagList.getSelectedValues();
        if (tags == null || tags.length == 0) {
            JOptionPaneHelper.showInformationBox(this, "Keine Dorfgruppe ausgewählt", "Information");
            return;
        }

        List<Village> candidates = new LinkedList<Village>();
        for (Object o : tags) {
            Tag t = (Tag) o;
            List<Integer> ids = t.getVillageIDs();
            for (Integer id : ids) {
                //add all villages tagged by current tag
                Village v = DataHolder.getSingleton().getVillagesById().get(id);
                if (!candidates.contains(v)) {
                    candidates.add(v);
                }
            }
        }

        if (jRelationBox.isSelected()) {
            //remove all villages that are not tagges by the current tag
            boolean oneFailed = false;
            Village[] aCandidates = candidates.toArray(new Village[]{});
            for (Village v_tmp : aCandidates) {
                for (Object o : tags) {
                    Tag t = (Tag) o;
                    if (!t.tagsVillage(v_tmp.getId())) {
                        oneFailed = true;
                        break;
                    }
                }

                if (oneFailed) {
                    //at least one tag is not valid for village
                    candidates.remove(v_tmp);
                    oneFailed = false;
                }
            }
        }


        System.out.println(candidates);

    }//GEN-LAST:event_fireCalculateReTimingsEvent

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DSWorkbenchReTimerFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField jArriveField;
    private javax.swing.JCheckBox jAxeBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JTextArea jComandArea;
    private javax.swing.JTextField jEstSendTime;
    private javax.swing.JCheckBox jHeavyBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jLightBox;
    private javax.swing.JCheckBox jPalaBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextPane jParserInfo;
    private javax.swing.JCheckBox jRamBox;
    private javax.swing.JCheckBox jRelationBox;
    private javax.swing.JTextField jReturnField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JCheckBox jSnobBox;
    private javax.swing.JTextField jSourceVillage;
    private javax.swing.JCheckBox jSpyBox;
    private javax.swing.JCheckBox jSwordBox;
    private javax.swing.JList jTagList;
    private javax.swing.JTextField jTargetVillage;
    private javax.swing.JComboBox jUnitBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fireTagsChangedEvent() {
        setup();
    }
}