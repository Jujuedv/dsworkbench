/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.types;

import de.tor.tribes.types.ext.Tribe;
import de.tor.tribes.types.ext.Ally;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.types.ext.NoAlly;
import de.tor.tribes.io.DataHolder;
import de.tor.tribes.io.UnitHolder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Torridity
 */
public class FightStats {

    private List<Ally> attackingAllies = null;
    private List<Ally> defendingAllies = null;
    private List<Tribe> defendingTribes = null;
    private List<Village> defendingVillages = null;
    private List<Village> conqueredVillages = null;
    private long startTime = Long.MAX_VALUE;
    private long endTime = Long.MIN_VALUE;
    private int reportCount = 0;
    private Hashtable<Tribe, SingleAttackerStat> attackerList = null;

    public FightStats() {
        attackerList = new Hashtable<Tribe, SingleAttackerStat>();
        attackingAllies = new LinkedList<Ally>();
        defendingAllies = new LinkedList<Ally>();
        defendingTribes = new LinkedList<Tribe>();
        defendingVillages = new LinkedList<Village>();
        conqueredVillages = new LinkedList<Village>();
    }

    public void includeReport(FightReport pReport) {
        Tribe attacker = pReport.getAttacker();
        Tribe defender = pReport.getDefender();
        Village sourceVillage = pReport.getSourceVillage();
        Village targetVillage = pReport.getTargetVillage();
        if (attacker == null || defender == null || sourceVillage == null || targetVillage == null) {
            return;
        }

        reportCount++;
        if (pReport.getTimestamp() < startTime) {
            startTime = pReport.getTimestamp();
        }
        if (pReport.getTimestamp() > endTime) {
            endTime = pReport.getTimestamp();
        }
        Ally attackerAlly = attacker.getAlly();
        Ally defenderAlly = defender.getAlly();

        if (attackerAlly == null) {
            attackerAlly = NoAlly.getSingleton();
        }
        if (defenderAlly == null) {
            defenderAlly = NoAlly.getSingleton();
        }

        SingleAttackerStat attackerElement = attackerList.get(attacker);
        if (attackerElement == null) {
            attackerElement = new SingleAttackerStat(attacker);
            attackerList.put(attacker, attackerElement);
        }

        attackerElement.addSourceVillage(sourceVillage);

        if (!defendingTribes.contains(defender)) {
            defendingTribes.add(defender);
        }


        if (!defendingVillages.contains(targetVillage)) {
            defendingVillages.add(targetVillage);
        }

        if (!attackingAllies.contains(attackerAlly)) {
            attackingAllies.add(attackerAlly);
        }

        if (!defendingAllies.contains(defenderAlly) && !defenderAlly.equals(attackerAlly)) {
            defendingAllies.add(defenderAlly);
        }

        switch (pReport.guessType()) {
            case Attack.FAKE_TYPE:
                attackerElement.addFake();
                break;
            case Attack.SPY_TYPE:
                attackerElement.addFake();
                break;
            case Attack.CLEAN_TYPE:
                attackerElement.addOff();
                break;
            case Attack.SNOB_TYPE:
                if (pReport.isSimpleSnobAttack()) {
                    attackerElement.addSimpleSnobAttack();
                } else {
                    attackerElement.addSnobAttack();
                }
                break;
        }

        for (UnitHolder unit : DataHolder.getSingleton().getUnits()) {
            if (!pReport.areAttackersHidden()) {
                attackerElement.addSentUnit(unit, pReport.getAttackers().get(unit));
                attackerElement.addLostUnit(unit, pReport.getDiedAttackers().get(unit));
            }
            if (!pReport.wasLostEverything()) {
                attackerElement.addKilledUnit(unit, pReport.getDiedDefenders().get(unit));
            }

            if (pReport.wasConquered() && pReport.whereDefendersOnTheWay()) {
                attackerElement.addSilentlyKilledUnit(unit, pReport.getDefendersOnTheWay().get(unit));
            }
            if (pReport.wasConquered() && pReport.whereDefendersOutside()) {
                Enumeration<Village> targets = pReport.getDefendersOutside().keys();
                while (targets.hasMoreElements()) {
                    Village target = targets.nextElement();
                    attackerElement.addSilentlyKilledUnit(unit, pReport.getDefendersOutside().get(target).get(unit));
                }
            }
        }

        if (pReport.wasLostEverything() && pReport.wasWallDamaged()) {
            int diff = pReport.getWallBefore() - pReport.getWallAfter();
            switch (diff) {
                case 1:
                   // System.out.println("add 2");
                    attackerElement.addAtLeast2KDamage();
                    break;
                case 2:
                   // System.out.println("add 4");
                    attackerElement.addAtLeast4KDamage();
                    break;
                case 3:
                    //System.out.println("add 6");
                    attackerElement.addAtLeast6KDamage();
                    break;
                default:
                   // System.out.println("add 8");
                    attackerElement.addAtLeast8KDamage();
                    break;
            }
        } else if (pReport.wasLostEverything() && !pReport.wasWallDamaged()) {
            attackerElement.addUnknownDamage();
        }

        if (pReport.wasConquered()) {
            attackerElement.addEnoblement();
            if (!conqueredVillages.contains(targetVillage)) {
                conqueredVillages.add(targetVillage);
            }
        }

        if (pReport.wasWallDamaged()) {
            attackerElement.addDestroyedWallLevels(pReport.getWallBefore() - pReport.getWallAfter());
        }

        if (pReport.wasBuildingDamaged()) {
            attackerElement.addDestroyedBuildingLevel(pReport.getAimedBuilding(), (pReport.getBuildingBefore() - pReport.getBuildingAfter()));
        }
    }

    public List<Ally> getAttackingAllies() {
        return attackingAllies;
    }

    public int getReportCount() {
        return reportCount;
    }

    public Date getStartDate() {
        return new Date(startTime);
    }

    public Date getEndDate() {
        return new Date(endTime);
    }

    public Tribe[] getAttackingTribes(Ally pAlly) {
        Enumeration<Tribe> tribes = attackerList.keys();
        List<Tribe> result = new LinkedList<Tribe>();
        while (tribes.hasMoreElements()) {
            Tribe next = tribes.nextElement();
            if (next != null && next.getAlly() != null && next.getAlly().equals(pAlly)) {
                result.add(next);
            } else if (pAlly != null && next.getAlly() == null && pAlly.equals(NoAlly.getSingleton())) {
                result.add(next);
            }
        }
        return result.toArray(new Tribe[]{});
    }

    public Ally[] getDefendingAllies() {
        return defendingAllies.toArray(new Ally[]{});
    }

    public Tribe[] getDefendingTribes() {
        return defendingTribes.toArray(new Tribe[]{});
    }

    public SingleAttackerStat getStatsForTribe(Tribe pTribe) {
        return attackerList.get(pTribe);
    }

    @Override
    public String toString() {

        String res = "";
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yy HH:mm");
        res += "Start: " + f.format(new Date(startTime)) + "\n";
        res += "End: " + f.format(new Date(endTime)) + "\n";
        res += "Reports: " + reportCount + "\n";
        res += "AttAllies: " + attackingAllies.size() + "\n";
        res += "DefAllies: " + defendingAllies.size() + "\n";
        res += "DefTribes: " + defendingTribes.size() + "\n";
        res += "DefVillages: " + defendingVillages.size() + "\n";
        res += "ConqueredVillages: " + conqueredVillages.size() + "\n";
        res += "-----------------------\n";
        res += " Involved Tribes\n";
        Enumeration<Tribe> keys = attackerList.keys();
        long overallLosses = 0;
        long overallKills = 0;
        while (keys.hasMoreElements()) {
            Tribe t = keys.nextElement();
            res += attackerList.get(t).toString();
            overallLosses += attackerList.get(t).getSummedLosses();
            overallKills += attackerList.get(t).getSummedKills();
        }

        res += "=====\n";
        res += "OverallLosses: " + overallLosses + "\n";
        res += "OverallKills: " + overallKills + "\n";
        res += "=====\n";
        return res;

    }
}
