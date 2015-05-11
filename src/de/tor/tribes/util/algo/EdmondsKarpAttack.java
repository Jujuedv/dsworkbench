/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util.algo;

import de.tor.tribes.io.DataHolder;
import de.tor.tribes.io.UnitHolder;
import de.tor.tribes.types.AbstractTroopMovement;
import de.tor.tribes.types.Fake;
import de.tor.tribes.types.Off;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.util.Constants;
import de.tor.tribes.util.DSCalculator;
import de.tor.tribes.util.algo.types.TimeFrame;
import java.awt.Color;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.log4j.Logger;

/**
 *
 * @author Jujuedv
 */
public class EdmondsKarpAttack extends AbstractAttackAlgorithm {

    private static Logger logger = Logger.getLogger("EdmondsKarpAttack");

    @Override
    public List<AbstractTroopMovement> calculateAttacks(
            Hashtable<UnitHolder, List<Village>> pSources,
            Hashtable<UnitHolder, List<Village>> pFakes,
            List<Village> pTargets,
            List<Village> pFakeTargets,
            Hashtable<Village, Integer> pMaxAttacksTable,
            TimeFrame pTimeFrame,
            boolean pFakeOffTargets) {
        logText("Starte neue EdmondsKarp Berechnung");
        logText("Berechne Offs");

        UnitHolder ram = DataHolder.getSingleton().getUnitByPlainName("ram");
        UnitHolder cata = DataHolder.getSingleton().getUnitByPlainName("catapult");
        List<Village> ramAndCataSources = pSources.get(ram);
        if (ramAndCataSources == null) {
            ramAndCataSources = new LinkedList<Village>();
        }
        List<Village> cataSources = pSources.get(cata);
        if (cataSources != null) {
            ramAndCataSources.addAll(cataSources);
        }

        Hashtable<Village, Integer> sourceMaxAttacks = resolveDuplicates(ramAndCataSources);

        EdmondsKarp ek = new EdmondsKarp();
        ek.C = new int[ramAndCataSources.size() + pTargets.size() + 2][ramAndCataSources.size() + pTargets.size() + 2];
        ek.s = 0;
        ek.t = ek.C.length - 1;

        int u = 0;
        for (Village v : ramAndCataSources) {
            ek.C[0][++u] = sourceMaxAttacks.get(v);
        }

        u = ramAndCataSources.size();
        for (Village v : pTargets) {
            ek.C[++u][ek.C.length - 1] = pMaxAttacksTable.get(v);
        }

        u = 0;
        for (Village s : ramAndCataSources) {
            ++u;

            int w = ramAndCataSources.size();
            for (Village t : pTargets) {
                ++w;

                long runtime = (long) (1000 * DSCalculator.calculateMoveTimeInSeconds(s, t, ram.getSpeed()));

                if (pTimeFrame.isMovementPossible(runtime, t)) {
                    ek.C[u][w] = 1;
                }
            }
        }

        ek.calculateAdj();
        ek.execute();

        logText(" - Erstelle Ergebnisliste für " + ek.f + " Offs.");

        Hashtable<Village, Off> offMovements = new Hashtable<Village, Off>();
        List<AbstractTroopMovement> movementList = new LinkedList<AbstractTroopMovement>();

        Hashtable<Village, HashSet<Village>> usedConnections = new Hashtable<Village, HashSet<Village>>();

        //store results
        for (int i = 0; i < ramAndCataSources.size(); i++) {
            HashSet<Village> conns = new HashSet<Village>();
            usedConnections.put(ramAndCataSources.get(i), conns);
            for (int j = 0; j < pTargets.size(); j++) {
                if (ek.F[1 + i][1 + ramAndCataSources.size() + j] == 1) {
                    Village source = ramAndCataSources.get(i);
                    Village target = pTargets.get(j);
                    Off movementForTarget = offMovements.get(target);
                    if (movementForTarget == null) {
                        movementForTarget = new Off(target, pMaxAttacksTable.get(target));
                        offMovements.put(target, movementForTarget);
                    }

                    conns.add(source);
                    movementForTarget.addOff(ram, source);
                } else {
                    Village target = pTargets.get(j);
                    Off movementForTarget = offMovements.get(target);
                    if (movementForTarget == null) {
                        movementForTarget = new Off(target, pMaxAttacksTable.get(target));
                        offMovements.put(target, movementForTarget);
                    }
                }
            }
        }

        //set result movements and remove used targets if needed
        Enumeration<Village> targetKeys = offMovements.keys();
        while (targetKeys.hasMoreElements()) {
            Village target = targetKeys.nextElement();

            pMaxAttacksTable.put(target, pMaxAttacksTable.get(target) - offMovements.get(target).getOffCount());

            movementList.add(offMovements.get(target));
        }

        logText("Berechne Fakes");

        if (pFakeOffTargets) {
            pFakeTargets.addAll(pTargets);
        }

        List<Village> ramAndCataFakes = pFakes.get(ram);
        if (ramAndCataFakes == null) {
            ramAndCataFakes = new LinkedList<Village>();
        }
        List<Village> cataFakes = pFakes.get(cata);
        if (cataFakes != null) {
            ramAndCataFakes.addAll(cataFakes);
        }

        Hashtable<Village, Integer> fakeMaxAttacks = resolveDuplicates(ramAndCataFakes);

        ek.C = new int[ramAndCataFakes.size() + pFakeTargets.size() + 2][ramAndCataFakes.size() + pFakeTargets.size() + 2];
        ek.s = 0;
        ek.t = ek.C.length - 1;

        u = 0;
        for (Village v : ramAndCataFakes) {
            ek.C[0][++u] = fakeMaxAttacks.get(v);
        }

        u = ramAndCataFakes.size();
        for (Village v : pFakeTargets) {
            ek.C[++u][ek.C.length - 1] = pMaxAttacksTable.get(v);
        }

        u = 0;
        for (Village s : ramAndCataFakes) {
            ++u;

            int w = ramAndCataFakes.size();
            for (Village t : pFakeTargets) {
                ++w;

                long runtime = (long) (1000 * DSCalculator.calculateMoveTimeInSeconds(s, t, ram.getSpeed()));

                if (pTimeFrame.isMovementPossible(runtime, t)) {
                    HashSet<Village> fromFakeSource = usedConnections.get(s);
                    if (fromFakeSource != null && fromFakeSource.contains(t)) {
                        continue;
                    }

                    ek.C[u][w] = 1;
                }
            }
        }

        ek.calculateAdj();
        ek.execute();

        logText(" - Erstelle Ergebnisliste für " + ek.f + " Fakes.");

        Hashtable<Village, Fake> fakeMovements = new Hashtable<Village, Fake>();

        //store results
        for (int i = 0; i < ramAndCataFakes.size(); i++) {
            for (int j = 0; j < pFakeTargets.size(); j++) {
                if (ek.F[1 + i][1 + ramAndCataFakes.size() + j] == 1) {
                    Village source = ramAndCataFakes.get(i);
                    Village target = pFakeTargets.get(j);
                    Fake movementForTarget = fakeMovements.get(target);
                    if (movementForTarget == null) {
                        movementForTarget = new Fake(target, pMaxAttacksTable.get(target));
                        fakeMovements.put(target, movementForTarget);
                    }

                    movementForTarget.addOff(ram, source);
                } else {
                    Village target = pFakeTargets.get(j);
                    Fake movementForTarget = fakeMovements.get(target);
                    if (movementForTarget == null) {
                        movementForTarget = new Fake(target, pMaxAttacksTable.get(target));
                        fakeMovements.put(target, movementForTarget);
                    }
                }
            }
        }

        targetKeys = fakeMovements.keys();
        while (targetKeys.hasMoreElements()) {
            Village target = targetKeys.nextElement();
            movementList.add(fakeMovements.get(target));
        }

        logText("Berechnung abgeschlossen.");

        return movementList;
    }

    private Hashtable<Village, Integer> resolveDuplicates(List<Village> pVillages) {
        List<Village> processed = new LinkedList<Village>();
        List<Integer> amounts = new LinkedList<Integer>();
        for (Village v : pVillages) {
            if (!processed.contains(v)) {
                //add new village
                processed.add(v);
                amounts.add(1);
            } else {
                //increment amount
                int idx = processed.indexOf(v);
                amounts.set(idx, amounts.get(idx) + 1);
            }
        }
        pVillages.clear();
        for (Village v : processed) {
            pVillages.add(v);
        }

        Hashtable<Village, Integer> res = new Hashtable<Village, Integer>();

        for (int i = 0; i < processed.size(); ++i) {
            res.put(processed.get(i), amounts.get(i));
        }

        return res;
    }
}
