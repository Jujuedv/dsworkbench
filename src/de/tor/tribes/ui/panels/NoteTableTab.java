/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MarkerTableTab.java
 *
 * Created on Mar 27, 2011, 3:50:19 PM
 */
package de.tor.tribes.ui.panels;

import de.tor.tribes.io.DataHolder;
import de.tor.tribes.types.Note;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.ui.windows.DSWorkbenchMainFrame;
import de.tor.tribes.ui.editors.BBPanelCellEditor;
import de.tor.tribes.ui.editors.NoteIconCellEditor;
import de.tor.tribes.ui.models.NoteTableModel;
import de.tor.tribes.ui.renderer.DateCellRenderer;
import de.tor.tribes.ui.renderer.DefaultTableHeaderRenderer;
import de.tor.tribes.ui.renderer.BBCellRenderer;
import de.tor.tribes.ui.renderer.NoteIconCellRenderer;
import de.tor.tribes.ui.renderer.VisibilityCellRenderer;
import de.tor.tribes.util.BrowserCommandSender;
import de.tor.tribes.util.Constants;
import de.tor.tribes.util.ImageUtils;
import de.tor.tribes.util.JOptionPaneHelper;
import de.tor.tribes.util.PluginManager;
import de.tor.tribes.util.bb.NoteListFormatter;
import de.tor.tribes.util.bb.VillageListFormatter;
import de.tor.tribes.util.note.NoteManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.decorator.PainterHighlighter;
import org.jdesktop.swingx.decorator.PatternPredicate;
import org.jdesktop.swingx.painter.AbstractLayoutPainter.HorizontalAlignment;
import org.jdesktop.swingx.painter.AbstractLayoutPainter.VerticalAlignment;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.table.TableColumnExt;

/**
 *
 * @author Torridity
 */
public class NoteTableTab extends javax.swing.JPanel implements ListSelectionListener {

    private static Logger logger = Logger.getLogger("NoteTableTab");

    public static enum TRANSFER_TYPE {

        CLIPBOARD_PLAIN, CLIPBOARD_BB, CUT_TO_INTERNAL_CLIPBOARD, COPY_TO_INTERNAL_CLIPBOARD, FROM_INTERNAL_CLIPBOARD
    }
    private String sNoteSet = null;
    private final static JXTable jxNoteTable = new JXTable();
    private final static JList jxVillageList = new JList();
    private static NoteTableModel noteModel = null;
    private static boolean KEY_LISTENER_ADDED = false;
    private static PainterHighlighter highlighter = null;

    static {
        jxNoteTable.addHighlighter(HighlighterFactory.createAlternateStriping(Constants.DS_ROW_A, Constants.DS_ROW_B));

        jxNoteTable.setColumnControlVisible(true);
        jxNoteTable.setDefaultRenderer(String.class, new BBCellRenderer());
        jxNoteTable.setDefaultRenderer(Boolean.class, new VisibilityCellRenderer());
        jxNoteTable.setDefaultRenderer(Date.class, new DateCellRenderer());
        jxNoteTable.setDefaultEditor(String.class, new BBPanelCellEditor(null));

        noteModel = new NoteTableModel(NoteManager.DEFAULT_GROUP);

        jxNoteTable.setModel(noteModel);
        jxNoteTable.getColumnExt("Icon").setCellRenderer(new NoteIconCellRenderer(NoteIconCellRenderer.ICON_TYPE.NOTE));
        jxNoteTable.getColumnExt("Kartensymbol").setCellRenderer(new NoteIconCellRenderer(NoteIconCellRenderer.ICON_TYPE.MAP));
        jxNoteTable.getColumnExt("Icon").setCellEditor(new NoteIconCellEditor(NoteIconCellEditor.ICON_TYPE.NOTE));
        jxNoteTable.getColumnExt("Kartensymbol").setCellEditor(new NoteIconCellEditor(NoteIconCellEditor.ICON_TYPE.MAP));

        BufferedImage corner_dr = ImageUtils.createCompatibleBufferedImage(5, 5, BufferedImage.BITMASK);
        Graphics2D g = corner_dr.createGraphics();
        GeneralPath p = new GeneralPath();
        p.moveTo(0, 0);
        p.lineTo(5, 0);
        p.lineTo(5, 5);
        p.closePath();
        g.setColor(Color.GREEN.darker());
        g.fill(p);
        g.dispose();
        jxNoteTable.addHighlighter(new PainterHighlighter(HighlightPredicate.EDITABLE, new ImagePainter(corner_dr, HorizontalAlignment.RIGHT, VerticalAlignment.TOP)));
    }

    /**
     * Creates new form MarkerTableTab
     *
     * @param pMarkerSet
     * @param pActionListener
     */
    public NoteTableTab(String pMarkerSet, final ActionListener pActionListener) {
        sNoteSet = pMarkerSet;
        initComponents();
        jScrollPane1.setViewportView(jxNoteTable);
        jScrollPane2.setViewportView(jxVillageList);
        if (!KEY_LISTENER_ADDED) {
            KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
            KeyStroke bbCopy = KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK, false);
            KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK, false);
            KeyStroke cut = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK, false);
            KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
            jxNoteTable.registerKeyboardAction(pActionListener, "Copy", copy, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            jxNoteTable.registerKeyboardAction(pActionListener, "BBCopy", bbCopy, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            jxNoteTable.registerKeyboardAction(pActionListener, "Cut", cut, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            jxNoteTable.registerKeyboardAction(pActionListener, "Paste", paste, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            jxNoteTable.registerKeyboardAction(pActionListener, "Delete", delete, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

            jxVillageList.registerKeyboardAction(pActionListener, "BBCopy_Village", bbCopy, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            jxVillageList.registerKeyboardAction(pActionListener, "Delete_Village", delete, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            jxNoteTable.getActionMap().put("find", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pActionListener.actionPerformed(new ActionEvent(jxNoteTable, 0, "Find"));
                }
            });
            KEY_LISTENER_ADDED = true;
        }
        jxNoteTable.getSelectionModel().addListSelectionListener(NoteTableTab.this);
        jxVillageList.getSelectionModel().addListSelectionListener(NoteTableTab.this);
    }

    public void deregister() {
        jxNoteTable.getSelectionModel().removeListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            int selectionCount = 0;
            if (jxNoteTable.getSelectionModel().equals(e.getSource())) {
                selectionCount = jxNoteTable.getSelectedRowCount();
                if (selectionCount != 0) {
                    showInfo(selectionCount + ((selectionCount == 1) ? " Notiz gewählt" : " Notizen gewählt"));
                }
            } else {
                selectionCount = jxVillageList.getSelectedValues().length;
                if (selectionCount != 0) {
                    showInfo(selectionCount + ((selectionCount == 1) ? " Dorf gewählt" : " Dörfer gewählt"));
                }
            }
        } else {
            if (jxNoteTable.getSelectionModel().equals(e.getSource())) {
                updateVillageList();
            }
        }
    }

    private void updateVillageList() {
        if (noteModel.getNoteSet().equals(getNoteSet())) {
            DefaultListModel model = new DefaultListModel();
            List<Note> notes = getSelectedNotes();
            List<Village> villages = new LinkedList<Village>();
            for (Note n : notes) {
                for (int id : n.getVillageIds()) {
                    Village v = DataHolder.getSingleton().getVillagesById().get(id);
                    if (v != null && !villages.contains(v)) {
                        villages.add(v);
                        model.addElement(v);
                    }
                }
            }
            jxVillageList.setModel(model);
        }
    }

    public void showSuccess(String pMessage) {
        infoPanel.setCollapsed(false);
        jXLabel1.setBackgroundPainter(new MattePainter(Color.GREEN));
        jXLabel1.setForeground(Color.BLACK);
        jXLabel1.setText(pMessage);
    }

    public void showInfo(String pMessage) {
        infoPanel.setCollapsed(false);
        jXLabel1.setBackgroundPainter(new MattePainter(getBackground()));
        jXLabel1.setForeground(Color.BLACK);
        jXLabel1.setText(pMessage);
    }

    public void showError(String pMessage) {
        infoPanel.setCollapsed(false);
        jXLabel1.setBackgroundPainter(new MattePainter(Color.RED));
        jXLabel1.setForeground(Color.WHITE);
        jXLabel1.setText(pMessage);
    }

    public void centerNoteVillage() {
        Village v = (Village) jxVillageList.getSelectedValue();
        if (v == null) {
            showInfo("Kein Dorf ausgewählt");
            return;
        }
        DSWorkbenchMainFrame.getSingleton().centerVillage(v);
    }

    public void centerNoteVillageInGame() {
        Village v = (Village) jxVillageList.getSelectedValue();
        if (v == null) {
            showInfo("Kein Dorf ausgewählt");
            return;
        }

        BrowserCommandSender.centerVillage(v);
    }

    public String getNoteSet() {
        return sNoteSet;
    }

    public JXTable getNoteTable() {
        return jxNoteTable;
    }

    public void updateSet() {
        noteModel.setNoteSet(sNoteSet);
        String[] cols = new String[]{"Icon", "Kartensymbol"};
        for (String col : cols) {
            TableColumnExt columns = jxNoteTable.getColumnExt(col);
            columns.setPreferredWidth(80);
            columns.setMaxWidth(80);
            columns.setWidth(80);
        }
        jScrollPane1.setViewportView(jxNoteTable);
        jScrollPane2.setViewportView(jxVillageList);
        jxNoteTable.getTableHeader().setDefaultRenderer(new DefaultTableHeaderRenderer());
    }

    public void updateFilter(final String pValue, final boolean pCaseSensitive, final boolean pFilterRows) {
        if (highlighter != null) {
            jxNoteTable.removeHighlighter(highlighter);
        }

        final List<String> columns = new LinkedList<String>();
        columns.add("Notiz");
        if (!pFilterRows) {
            jxNoteTable.setRowFilter(null);
            final List<Integer> relevantCols = new LinkedList<Integer>();
            List<TableColumn> cols = jxNoteTable.getColumns(true);
            for (int i = 0; i < jxNoteTable.getColumnCount(); i++) {
                TableColumnExt col = jxNoteTable.getColumnExt(i);
                if (col.isVisible() && columns.contains(col.getTitle())) {
                    relevantCols.add(cols.indexOf(col));
                }
            }
            for (Integer col : relevantCols) {
                PatternPredicate patternPredicate0 = new PatternPredicate((pCaseSensitive ? "" : "(?i)") + Matcher.quoteReplacement(pValue), col);
                MattePainter mp = new MattePainter(new Color(0, 0, 0, 120));
                highlighter = new PainterHighlighter(new HighlightPredicate.NotHighlightPredicate(patternPredicate0), mp);
                jxNoteTable.addHighlighter(highlighter);
            }
        } else {
            jxNoteTable.setRowFilter(new RowFilter<TableModel, Integer>() {
                @Override
                public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                    final List<Integer> relevantCols = new LinkedList<Integer>();
                    List<TableColumn> cols = jxNoteTable.getColumns(true);
                    for (int i = 0; i < jxNoteTable.getColumnCount(); i++) {
                        TableColumnExt col = jxNoteTable.getColumnExt(i);
                        if (col.isVisible() && columns.contains(col.getTitle())) {
                            relevantCols.add(cols.indexOf(col));
                        }
                    }

                    for (Integer col : relevantCols) {
                        if (pCaseSensitive) {
                            if (entry.getStringValue(col).indexOf(pValue) > -1) {
                                return true;
                            }
                        } else {
                            if (entry.getStringValue(col).toLowerCase().indexOf(pValue.toLowerCase()) > -1) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoPanel = new org.jdesktop.swingx.JXCollapsiblePane();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Zugeordnete Dörfer"));
        jPanel1.setPreferredSize(new java.awt.Dimension(180, 80));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        infoPanel.setCollapsed(true);
        infoPanel.setInheritAlpha(false);

        jXLabel1.setText("Keine Meldung");
        jXLabel1.setOpaque(true);
        jXLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fireHideInfoEvent(evt);
            }
        });
        infoPanel.add(jXLabel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(infoPanel, java.awt.BorderLayout.SOUTH);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void fireHideInfoEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireHideInfoEvent
        infoPanel.setCollapsed(true);
    }//GEN-LAST:event_fireHideInfoEvent
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXCollapsiblePane infoPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    // End of variables declaration//GEN-END:variables

    public void createNote() {
        NoteManager.getSingleton().addManagedElement(sNoteSet, new Note());
        showInfo("Notiz erstellt");
    }

    public void transferSelection(TRANSFER_TYPE pType) {
        switch (pType) {
            case COPY_TO_INTERNAL_CLIPBOARD:
                copyToInternalClipboard();
                break;
            case CUT_TO_INTERNAL_CLIPBOARD:
                cutToInternalClipboard();
                break;
            case FROM_INTERNAL_CLIPBOARD:
                copyFromInternalClipboard();
                break;
            case CLIPBOARD_BB:
                copyBBToExternalClipboardEvent();
                break;
        }
    }

    private void copyBBToExternalClipboardEvent() {
        try {
            List<Note> notes = getSelectedNotes();
            if (notes.isEmpty()) {
                showInfo("Keine Notizen ausgewählt");
                return;
            }
            boolean extended = (JOptionPaneHelper.showQuestionConfirmBox(this, "Erweiterte BB-Codes verwenden (nur für Forum und Notizen geeignet)?", "Erweiterter BB-Code", "Nein", "Ja") == JOptionPane.YES_OPTION);

            StringBuilder buffer = new StringBuilder();
            if (extended) {
                buffer.append("[u][size=12]Notizübersicht[/size][/u]\n\n");
            } else {
                buffer.append("[u]Notizübersicht[/u]\n\n");
            }
            buffer.append(new NoteListFormatter().formatElements(notes, extended));

            if (extended) {
                buffer.append("\n[size=8]Erstellt am ");
                buffer.append(new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss").format(Calendar.getInstance().getTime()));
                buffer.append(" mit [url=\"http://www.dsworkbench.de/index.php?id=23\"]DS Workbench ");
                buffer.append(Constants.VERSION).append(Constants.VERSION_ADDITION + "[/url][/size]\n");
            } else {
                buffer.append("\nErstellt am ");
                buffer.append(new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss").format(Calendar.getInstance().getTime()));
                buffer.append(" mit [url=\"http://www.dsworkbench.de/index.php?id=23\"]DS Workbench ");
                buffer.append(Constants.VERSION).append(Constants.VERSION_ADDITION + "[/url]\n");
            }

            String b = buffer.toString();
            StringTokenizer t = new StringTokenizer(b, "[");
            int cnt = t.countTokens();
            if (cnt > 1000) {
                if (JOptionPaneHelper.showQuestionConfirmBox(this, "Die ausgewählten Notizen benötigen mehr als 1000 BB-Codes\n" + "und können daher im Spiel (Forum/IGM/Notizen) nicht auf einmal dargestellt werden.\nTrotzdem exportieren?", "Zu viele BB-Codes", "Nein", "Ja") == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(b), null);
            String result = "Notizen in Zwischenablage kopiert.";
            showSuccess(result);
        } catch (Exception e) {
            logger.error("Failed to copy data to clipboard", e);
            String result = "Fehler beim Kopieren in die Zwischenablage.";
            showError(result);
        }
    }

    private boolean copyToInternalClipboard() {
        List<Note> selection = getSelectedNotes();
        StringBuilder b = new StringBuilder();
        int cnt = 0;
        for (Note a : selection) {
            b.append(Note.toInternalRepresentation(a)).append("\n");
            cnt++;
        }
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(b.toString()), null);
            showSuccess(cnt + ((cnt == 1) ? " Notiz kopiert" : " Notizen kopiert"));
            return true;
        } catch (HeadlessException hex) {
            showError("Fehler beim Kopieren der Notizen");
        }
        return false;
    }

    private void cutToInternalClipboard() {
        int size = getSelectedNotes().size();
        if (copyToInternalClipboard() && deleteSelection(false)) {
            showSuccess(size + ((size == 1) ? " Notiz ausgeschnitten" : " Notizen ausgeschnitten"));
        } else {
            showError("Fehler beim Ausschneiden der Notizen");
        }
    }

    private void copyFromInternalClipboard() {
        boolean changed = false;
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(DataFlavor.stringFlavor);

            String[] lines = data.split("\n");
            int cnt = 0;
            NoteManager.getSingleton().invalidate();
            for (String line : lines) {
                Note n = Note.fromInternalRepresentation(line);
                if (n != null) {
                    NoteManager.getSingleton().addManagedElement(getNoteSet(), n);
                    cnt++;
                }
            }
            NoteManager.getSingleton().revalidate();
            if (cnt > 0) {
                showSuccess(cnt + ((cnt == 1) ? " Notiz eingefügt" : " Notizen eingefügt"));
                changed = true;
            } else {
                logger.debug("No notes found, try to insert villages");
                List<Note> notes = getSelectedNotes();
                if (notes.isEmpty()) {
                    logger.info("No notes selected, returning");
                    showInfo("Keine Notizen gewählt, um Dörfer hinzuzufügen");
                    return;
                }
                List<Village> villages = PluginManager.getSingleton().executeVillageParser(data);
                if (!villages.isEmpty()) {
                    logger.info("Villages found, adding them to selected notes");

                    for (Note n : notes) {
                        for (Village v : villages) {
                            n.addVillage(v);
                        }
                    }

                    String message = ((villages.size() == 1) ? "Dorf wurde " : villages.size() + " Dörfer wurden ") + ((notes.size() == 1) ? "der Notiz" : "den Notizen") + " hinzugefügt";
                    showSuccess(message);
                    changed = true;
                }
            }
        } catch (UnsupportedFlavorException ufe) {
            logger.error("Failed to copy notes from internal clipboard", ufe);
            showError("Fehler beim Einfügen der Notizen");
        } catch (IOException ioe) {
            logger.error("Failed to copy notes from internal clipboard", ioe);
            showError("Fehler beim Einfügen der Notizen");
        }
        if (changed) {
            noteModel.fireTableDataChanged();
        } else {
            showInfo("<html>Keine Notizen in der Zwischenablage gefunden bzw.<br/>keine Notizen markiert, um D&ouml;rfer hinzuzuf&uuml;gen</html>");
        }
    }

    public void copyVillagesAsBBCodes() {
        Object[] selection = jxVillageList.getSelectedValues();
        if (selection == null || selection.length == 0) {
            showInfo("Keine Dörfer gewählt");
            return;
        }

        try {
            List<Village> villages = new LinkedList<Village>();
            for (Object o : selection) {
                villages.add((Village) o);
            }
            boolean extended = (JOptionPaneHelper.showQuestionConfirmBox(this, "Erweiterte BB-Codes verwenden (nur für Forum und Notizen geeignet)?", "Erweiterter BB-Code", "Nein", "Ja") == JOptionPane.YES_OPTION);

            StringBuilder buffer = new StringBuilder();
            if (extended) {
                buffer.append("[u][size=12]Dorfliste[/size][/u]\n\n");
            } else {
                buffer.append("[u]Dorfliste[/u]\n\n");
            }
            buffer.append(new VillageListFormatter().formatElements(villages, extended));

            if (extended) {
                buffer.append("\n[size=8]Erstellt am ");
                buffer.append(new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss").format(Calendar.getInstance().getTime()));
                buffer.append(" mit [url=\"http://www.dsworkbench.de/index.php?id=23\"]DS Workbench ");
                buffer.append(Constants.VERSION).append(Constants.VERSION_ADDITION + "[/url][/size]\n");
            } else {
                buffer.append("\nErstellt am ");
                buffer.append(new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss").format(Calendar.getInstance().getTime()));
                buffer.append(" mit [url=\"http://www.dsworkbench.de/index.php?id=23\"]DS Workbench ");
                buffer.append(Constants.VERSION).append(Constants.VERSION_ADDITION + "[/url]\n");
            }

            String b = buffer.toString();
            StringTokenizer t = new StringTokenizer(b, "[");
            int cnt = t.countTokens();
            if (cnt > 1000) {
                if (JOptionPaneHelper.showQuestionConfirmBox(this, "Die ausgewählten Dörfer benötigen mehr als 1000 BB-Codes\n" + "und können daher im Spiel (Forum/IGM/Notizen) nicht auf einmal dargestellt werden.\nTrotzdem exportieren?", "Zu viele BB-Codes", "Nein", "Ja") == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(b), null);
            String result = "Dörfer in Zwischenablage kopiert.";
            showSuccess(result);
        } catch (Exception e) {
            logger.error("Failed to copy data to clipboard", e);
            String result = "Fehler beim Kopieren in die Zwischenablage.";
            showError(result);
        }

    }

    public boolean deleteSelection(boolean pAsk) {
        List<Note> selectedNotes = getSelectedNotes();

        if (pAsk) {
            String message = ((selectedNotes.size() == 1) ? "Notiz " : (selectedNotes.size() + " Notizen ")) + "wirklich löschen?";
            if (selectedNotes.isEmpty() || JOptionPaneHelper.showQuestionConfirmBox(this, message, "Notizen löschen", "Nein", "Ja") != JOptionPane.YES_OPTION) {
                return false;
            }
        }
        jxNoteTable.editingCanceled(new ChangeEvent(this));
        NoteManager.getSingleton().removeElements(getNoteSet(), selectedNotes);
        noteModel.fireTableDataChanged();
        showSuccess(selectedNotes.size() + " Notiz(en) gelöscht");
        return true;
    }

    public void deleteSelection() {
        deleteSelection(true);
    }

    public void deleteVillagesFromNotes() {
        Object[] selection = jxVillageList.getSelectedValues();

        if (selection == null || selection.length == 0) {
            showInfo("Keine Dörfer gewählt");
            return;
        }

        List<Note> notes = getSelectedNotes();

        if (notes.isEmpty()) {
            showInfo("Keine Notizen gewählt");
            return;
        }

        String message = ((selection.length == 1) ? "Dorf aus " : selection.length + " Dörfer aus ") + ((notes.size() == 1) ? "der gewählten Notiz" : "den gewählten Notizen") + " löschen?";
        if (JOptionPaneHelper.showQuestionConfirmBox(this, message, "Löschen", "Nein", "Ja") == JOptionPane.YES_OPTION) {
            for (Object o : selection) {
                Village v = (Village) o;
                for (Note n : notes) {
                    n.removeVillage(v);
                }
            }

            showSuccess(((selection.length == 1) ? "Dorf " : selection.length + " Dörfer ") + "gelöscht");
            noteModel.fireTableDataChanged();
        }
    }

    private List<Note> getSelectedNotes() {
        final List<Note> selectedNotes = new LinkedList<Note>();
        int[] selectedRows = jxNoteTable.getSelectedRows();
        if (selectedRows != null && selectedRows.length < 1) {
            return selectedNotes;
        }
        for (Integer selectedRow : selectedRows) {
            Note a = (Note) NoteManager.getSingleton().getAllElements(getNoteSet()).get(jxNoteTable.convertRowIndexToModel(selectedRow));
            if (a != null) {
                selectedNotes.add(a);
            }
        }
        return selectedNotes;
    }
}
