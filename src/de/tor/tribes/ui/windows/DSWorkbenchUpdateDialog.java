/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DSWorkbenchUpdateDialog.java
 *
 * Created on Oct 29, 2011, 12:45:01 PM
 */
package de.tor.tribes.ui.windows;

import de.tor.tribes.util.AutoUpdater;
import de.tor.tribes.util.interfaces.UpdateListener;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;
import org.apache.log4j.Logger;

/**
 *
 * @author Torridity
 */
@SuppressWarnings("serial")
public class DSWorkbenchUpdateDialog extends javax.swing.JDialog implements UpdateListener {

    private static Logger logger = Logger.getLogger("DSWorkbenchUpdateDialog");
    private int filesToUpdate = 0;
    private int updatedFiles = 0;
    private UPDATE_RESULT result = UPDATE_RESULT.READY;
    private List<String> updates = null;

    public enum UPDATE_RESULT {

        READY, SUCCESS, ERROR, CANCELED, NOT_NEEDED
    }

    /**
     * Creates new form DSWorkbenchUpdateDialog
     *
     * @param parent
     * @param modal
     */
    public DSWorkbenchUpdateDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            jLabel1.setIcon(new ImageIcon("./graphics/big/information.png"));
            updates = AutoUpdater.getUpdatedResources(DSWorkbenchUpdateDialog.this);
            if (updates.isEmpty()) {
                result = UPDATE_RESULT.NOT_NEEDED;
                fireUpdateFinishedEvent(true, "Kein Update notwendig");
                jDoUpdateButton.setText("Schließen");
            } else {
                filesToUpdate = updates.size();
                jProgressBar1.setString("0 von " + filesToUpdate + " Datei(en) aktualisiert");
                jDoUpdateButton.setText("Update starten");
            }
        } catch (IOException ioe) {
            logger.error("Failed to obtain update list", ioe);
            result = UPDATE_RESULT.ERROR;
            jDoUpdateButton.setText("Schließen");
        }
    }

    public UPDATE_RESULT getResult() {
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jProgressBar1 = new javax.swing.JProgressBar();
        jDoUpdateButton = new javax.swing.JButton();
        jSkipButton = new javax.swing.JButton();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jUpdateInformationLabel = new org.jdesktop.swingx.JXLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update");
        setMinimumSize(new java.awt.Dimension(450, 150));
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                fireUpdateCanceledEvent(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jProgressBar1.setMaximum(10000);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(300, 17));
        jProgressBar1.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jProgressBar1, gridBagConstraints);

        jDoUpdateButton.setText("Update starten");
        jDoUpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                firePerformUpdateEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jDoUpdateButton, gridBagConstraints);

        jSkipButton.setText("Überspringen");
        jSkipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fireSkipUpdateEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jSkipButton, gridBagConstraints);

        jXLabel1.setText("<html>   Es stehen Updates für DS Workbench zur Verf&uuml;gung. Klicke auf <i>Update starten</i>, um deine Installation zu aktualisieren. Nach der Aktualisierung ist ein <b>Neustart</b> von DS Workbench <b>notwendig</b>. </html>  ");
        jXLabel1.setLineWrap(true);
        jXLabel1.setPreferredSize(new java.awt.Dimension(300, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        getContentPane().add(jXLabel1, gridBagConstraints);

        jUpdateInformationLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Updateinformationen"));
        jUpdateInformationLabel.setText("Keine");
        jUpdateInformationLabel.setLineWrap(true);
        jUpdateInformationLabel.setPreferredSize(new java.awt.Dimension(300, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        getContentPane().add(jUpdateInformationLabel, gridBagConstraints);

        jLabel1.setPreferredSize(new java.awt.Dimension(48, 48));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firePerformUpdateEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firePerformUpdateEvent
        if (!jDoUpdateButton.isEnabled()) {
            return;
        }
        if (jDoUpdateButton.getText().equals("Schließen")) {
            dispose();
        } else {
            jProgressBar1.setForeground(new JProgressBar().getForeground());
            jDoUpdateButton.setEnabled(false);
            jSkipButton.setEnabled(false);
            updatedFiles = 0;
            new UpdateThread(updates, this).start();
        }
    }//GEN-LAST:event_firePerformUpdateEvent

    private void fireUpdateCanceledEvent(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_fireUpdateCanceledEvent
        result = UPDATE_RESULT.CANCELED;
    }//GEN-LAST:event_fireUpdateCanceledEvent

    private void fireSkipUpdateEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireSkipUpdateEvent
        if (!jSkipButton.isEnabled()) {
            return;
        }
        result = UPDATE_RESULT.CANCELED;
        setVisible(false);
    }//GEN-LAST:event_fireSkipUpdateEvent
    @Override
    public void fireUpdatesFoundEvent(int pChangedFiles, int pNewFiles) {
        jUpdateInformationLabel.setText("<html><ul><li>Ge&auml;nderte Dateien: " + pChangedFiles + "</li><li>Neue Dateien: " + pNewFiles + "</li></ul></html>");
    }

    @Override
    public final void fireResourceUpdatedEvent(String pResource, double pPercentFinished) {
        jProgressBar1.setValue((int) Math.rint(pPercentFinished * 100));
        updatedFiles++;
        jProgressBar1.setString(updatedFiles + " von " + filesToUpdate + " Datei(en) aktualisiert");
        jProgressBar1.repaint();
    }

    @Override
    public final void fireUpdateFinishedEvent(boolean pResult, String pMessage) {
        jProgressBar1.setUI(new BasicProgressBarUI() {

            @Override
            protected Color getSelectionBackground() {
                return Color.BLACK;
            }

            @Override
            protected Color getSelectionForeground() {
                return Color.DARK_GRAY;
            }
        });
        if (pResult) {
            jProgressBar1.setForeground(Color.GREEN);
            jProgressBar1.setString("Update erfolgreich, Neustart erforderlich.");
            if (pMessage == null) {
                result = UPDATE_RESULT.SUCCESS;
                jDoUpdateButton.setText("Beenden");
            }
        } else {
            jProgressBar1.setString(pMessage);
            jProgressBar1.setForeground(Color.YELLOW);
            result = UPDATE_RESULT.ERROR;
        }
        jDoUpdateButton.setEnabled(true);
        jDoUpdateButton.setText("Schließen");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DSWorkbenchUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DSWorkbenchUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DSWorkbenchUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DSWorkbenchUpdateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        final DSWorkbenchUpdateDialog dialog = new DSWorkbenchUpdateDialog(null, true);
        dialog.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("OK " + dialog.getResult());
                System.exit(0);
            }
        });
        dialog.setVisible(true);


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDoUpdateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton jSkipButton;
    private org.jdesktop.swingx.JXLabel jUpdateInformationLabel;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    // End of variables declaration//GEN-END:variables
}

class UpdateThread extends Thread {

    private static Logger logger = Logger.getLogger("UpdateThread");
    private List<String> resources = null;
    private UpdateListener listener = null;

    public UpdateThread(List<String> pResources, UpdateListener pListener) {
        resources = pResources;
        listener = pListener;
        setName("DSWorkbenchUpdateThread");
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            AutoUpdater.downloadUpdate(resources, listener);
        } catch (IOException ioe) {
            logger.error("Failed to download updates", ioe);
        }
    }
}
