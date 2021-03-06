/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.wiz.dep;

import de.tor.tribes.io.DataHolder;
import de.tor.tribes.types.SOSRequest;
import de.tor.tribes.types.TargetInformation;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.util.GlobalOptions;
import de.tor.tribes.util.ProfileManager;
import de.tor.tribes.util.generator.ui.SOSGenerator;
import de.tor.tribes.util.sos.SOSManager;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.api.wizard.WizardResultReceiver;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPanelProvider;

/**
 *
 * @author Torridity
 */
public class DefensePlanerWizard extends WizardPanelProvider {

    private static final String ID_WELCOME = "welcome-id";
    private static final String ID_ANALYSE = "analyse-id";
    private static final String ID_VILLAGES = "villages-id";
    private static final String ID_FILTER = "filter-id";
    private static final String ID_CALCULATION = "calculation-id";
    private static final String ID_FINISH = "finish-id";
    private static JFrame parent = null;

    public DefensePlanerWizard() {
        super("DS Workbench - Verteidigungsplaner",
                new String[]{ID_WELCOME, ID_ANALYSE, ID_VILLAGES, ID_FILTER, ID_CALCULATION, ID_FINISH},
                new String[]{"Willkommen", "Angriffe analysieren", "Unterstützende Dörfer", "Filter", "Berechnung", "Fertigstellung"});
    }

    @Override
    protected JComponent createPanel(WizardController wc, String string, Map map) {
        if (string.equals(ID_WELCOME)) {
            return WelcomePanel.getSingleton();
        } /*else if (string.equals(ID_ANALYSE)) {
            DefenseAnalysePanel.getSingleton().setController(wc);
            return DefenseAnalysePanel.getSingleton();
        } else if (string.equals(ID_VILLAGES)) {
            DefenseSourcePanel.getSingleton().setController(wc);
            return DefenseSourcePanel.getSingleton();
        } else if (string.equals(ID_FILTER)) {
            DefenseFilterPanel.getSingleton().setController(wc);
            return DefenseFilterPanel.getSingleton();
        } else if (string.equals(ID_CALCULATION)) {
            DefenseCalculationSettingsPanel.getSingleton().setController(wc);
            return DefenseCalculationSettingsPanel.getSingleton();
        } else if (string.equals(ID_FINISH)) {
            return DefenseFinishPanel.getSingleton();
        }*/
        return null;
    }

    private static List<SOSRequest> createSampleRequests() {
        int wallLevel = 20;
        int supportCount = 100;
        int maxAttackCount = 50;
        int maxFakeCount = 0;

        List<SOSRequest> result = new LinkedList<SOSRequest>();
        Village[] villages = GlobalOptions.getSelectedProfile().getTribe().getVillageList();
        Village[] attackerVillages = DataHolder.getSingleton().getTribeByName("Alexander25").getVillageList();

        for (int i = 0; i < supportCount; i++) {
            int id = (int) Math.rint(Math.random() * (villages.length - 1));
            Village target = villages[id];
            SOSRequest r = new SOSRequest(target.getTribe());
            r.addTarget(target);
            TargetInformation info = r.getTargetInformation(target);
            info.setWallLevel(wallLevel);

            info.addTroopInformation(DataHolder.getSingleton().getUnitByPlainName("spear"), (int) Math.rint(Math.random() * 14000));
            info.addTroopInformation(DataHolder.getSingleton().getUnitByPlainName("sword"), (int) Math.rint(Math.random() * 14000));
            info.addTroopInformation(DataHolder.getSingleton().getUnitByPlainName("heavy"), (int) Math.rint(Math.random() * 5000));

            int cnt = (int) Math.rint(maxAttackCount * Math.random());
            for (int j = 0; j < cnt; j++) {
                int idx = (int) Math.rint(Math.random() * (attackerVillages.length - 2));
                Village v = attackerVillages[idx];
                info.addAttack(v, new Date(System.currentTimeMillis() + Math.round(DateUtils.MILLIS_PER_DAY * 7 * Math.random())));
                for (int k = 0; k < (int) Math.rint(maxFakeCount * Math.random()); k++) {
                    idx = (int) Math.rint(Math.random() * (attackerVillages.length - 2));
                    v = attackerVillages[idx];
                    info.addAttack(v, new Date(System.currentTimeMillis() + Math.round(3600 * Math.random())));
                }
            }
            result.add(r);
        }

        return result;
    }

    public static void show() {
        if (parent != null) {
            parent.toFront();
            return;
        }
        
        parent = new JFrame();
        parent.setTitle("Verteidigungsplaner");
        WizardPanelProvider provider = new DefensePlanerWizard();
        Wizard wizard = provider.createWizard();
        parent.getContentPane().setLayout(new BorderLayout());
        WizardDisplayer.installInContainer(parent, BorderLayout.CENTER, wizard, null, null, new WizardResultReceiver() {

            @Override
            public void finished(Object o) {
                parent.dispose();
                parent = null;
            }

            @Override
            public void cancelled(Map map) {
                parent.dispose();
                parent = null;
            }
        });
        parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parent.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                parent = null;
            }
        });
        parent.pack();
        parent.setVisible(true);
        new SOSGenerator().setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }

        Logger.getRootLogger().addAppender(new ConsoleAppender(new org.apache.log4j.PatternLayout("%d - %-5p - %-20c (%C [%L]) - %m%n")));
        GlobalOptions.setSelectedServer("de77");
        ProfileManager.getSingleton().loadProfiles();
        GlobalOptions.setSelectedProfile(ProfileManager.getSingleton().getProfiles("de77")[0]);
        DataHolder.getSingleton().loadData(false);
        GlobalOptions.loadUserData();

    /*    for (SOSRequest r : createSampleRequests()) {
            SOSManager.getSingleton().addManagedElement(r);
        }*/

new SOSGenerator().setVisible(true);
        new DefensePlanerWizard().show();
        // DefenseAnalysePanel.getSingleton().setData(createSampleRequests());
       /*
         * final JFrame f = new JFrame(); f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); JPanel p = new JPanel(); p.setLayout(new
         * BorderLayout()); WizardDisplayer.installInContainer(p, BorderLayout.CENTER, wizard, null, null, new WizardResultReceiver() {
         *
         * @Override public void finished(Object o) { System.out.println(o); }
         *
         * @Override public void cancelled(Map map) { System.out.println("Cancel: " + map); f.dispose(); } }); f.getContentPane().add(p);
         * f.pack(); f.setVisible(true);
         */

    }
}
