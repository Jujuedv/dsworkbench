/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util;

import de.tor.tribes.io.ServerManager;
import de.tor.tribes.io.UnitHolder;
import de.tor.tribes.types.Attack;
import de.tor.tribes.types.Village;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Charon
 */
public class AttackToBBCodeFormater {

    public static final String STANDARD_TEMPLATE = "%TYPE% von %ATTACKER% aus %SOURCE% mit %UNIT% auf %DEFENDER% in %TARGET% startet am [color=#ff0e0e]%SEND%[/color] und kommt am [color=#2eb92e]%ARRIVE%[/color] an (%PLACE%)";

    public static String formatAttack(Attack pAttack, String pServerURL, boolean pExtended) {
        String sendtime = null;
        String arrivetime = null;
        String template = GlobalOptions.getProperty("attack.bbexport.template");
        if (template == null) {
            template = STANDARD_TEMPLATE;
        }

        Date aTime = pAttack.getArriveTime();
        Date sTime = new Date(aTime.getTime() - (long) (DSCalculator.calculateMoveTimeInSeconds(pAttack.getSource(), pAttack.getTarget(), pAttack.getUnit().getSpeed()) * 1000));
        if (pExtended) {
            sendtime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.'[size=8]'SSS'[/size]'").format(sTime);
            arrivetime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.'[size=8]'SSS'[/size]'").format(aTime);
        } else {
            sendtime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.SSS").format(sTime);
            arrivetime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.SSS").format(aTime);
        }

        switch (pAttack.getType()) {
            case Attack.CLEAN_TYPE: {
                template = template.replaceAll("%TYPE%", "Angriff (Clean-Off)");
                break;
            }
            case Attack.FAKE_TYPE: {
                template = template.replaceAll("%TYPE%", "Angriff (Fake)");
                break;
            }
            case Attack.SNOB_TYPE: {
                template = template.replaceAll("%TYPE%", "Angriff (AG)");
                break;
            }
            case Attack.SUPPORT_TYPE: {
                template = template.replaceAll("%TYPE%", "Unterstützung");
                break;
            }
            default: {
                template = template.replaceAll("%TYPE%", "Angriff");
            }
        }

        if (pAttack.getSource().getTribe() != null) {
            template = template.replaceAll("%ATTACKER%", pAttack.getSource().getTribe().toBBCode());
        } else {
            template = template.replaceAll("%ATTACKER%", "Barbaren");
        }
        template = template.replaceAll("%SOURCE%", pAttack.getSource().toBBCode());
        if (pExtended) {
            template = template.replaceAll("%UNIT%", "[img]" + pServerURL + "/graphic/unit/unit_" + pAttack.getUnit().getPlainName() + ".png[/img]");
        } else {
            template = template.replaceAll("%UNIT%", pAttack.getUnit().getName());
        }
        if (pAttack.getTarget().getTribe() != null) {
            template = template.replaceAll("%DEFENDER%", pAttack.getTarget().getTribe().toBBCode());
        } else {
            template = template.replaceAll("%DEFENDER%", "Barbaren");
        }
        template = template.replaceAll("%TARGET%", pAttack.getTarget().toBBCode());
        template = template.replaceAll("%SEND%", sendtime);
        template = template.replaceAll("%ARRIVE%", arrivetime);
        //replace place var
        String baseURL = ServerManager.getServerURL(GlobalOptions.getSelectedServer()) + "/";
        String placeURL = baseURL + "game.php?village=";
        int uvID = GlobalOptions.getUVID();
        if (uvID >= 0) {
            placeURL = baseURL + "game.php?t=" + uvID + "&village=";
        }
        placeURL += pAttack.getSource().getId() + "&screen=place&mode=command&target=" + pAttack.getTarget().getId();

        String placeLink = "[url=\"" + placeURL + "\"]Versammlungsplatz[/url]";
        template = template.replaceAll("%PLACE%", placeLink);
        template += "\n";
        return template;
    }

    public static String formatAttack(Village pSource, Village pTarget, UnitHolder pUnit, Date pSendTime, int pType, String pServerURL, boolean pExtended) {
        String sendtime = null;
        String arrivetime = null;
        String template = GlobalOptions.getProperty("attack.bbexport.template");
        if (template == null) {
            template = "%TYPE% von %ATTACKER% aus %SOURCE% mit %UNIT% auf %DEFENDER% in %TARGET% startet am [color=red]%SEND%[/color] und kommt am [color=green]%ARRIVE%[/color] an";
        }

        Date aTime = new Date(pSendTime.getTime() + (long) (DSCalculator.calculateMoveTimeInSeconds(pSource, pTarget, pUnit.getSpeed()) * 1000));

        if (pExtended) {
            sendtime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.'[size=8]'SSS'[/size]'").format(pSendTime);
            arrivetime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.'[size=8]'SSS'[/size]'").format(aTime);
        } else {
            sendtime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.SSS").format(pSendTime);
            arrivetime = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss.SSS").format(aTime);
        }

        switch (pType) {
            case Attack.CLEAN_TYPE: {
                template = template.replaceAll("%TYPE%", "Angriff (Clean-Off)");
                break;
            }
            case Attack.FAKE_TYPE: {
                template = template.replaceAll("%TYPE%", "Angriff (Fake)");
                break;
            }
            case Attack.SNOB_TYPE: {
                template = template.replaceAll("%TYPE%", "Angriff (AG)");
                break;
            }
            case Attack.SUPPORT_TYPE: {
                template = template.replaceAll("%TYPE%", "Unterstützung");
                break;
            }
            default: {
                template = template.replaceAll("%TYPE%", "Angriff");
            }
        }

        if (pSource.getTribe() != null) {
            template = template.replaceAll("%ATTACKER%", pSource.getTribe().toBBCode());
        } else {
            template = template.replaceAll("%ATTACKER%", "Barbaren");
        }
        template = template.replaceAll("%SOURCE%", pSource.toBBCode());
        if (pExtended) {
            template = template.replaceAll("%UNIT%", "[img]" + pServerURL + "/graphic/unit/unit_" + pUnit.getPlainName() + ".png[/img]");
        } else {
            template = template.replaceAll("%UNIT%", pUnit.getName());
        }
        if (pTarget.getTribe() != null) {
            template = template.replaceAll("%DEFENDER%", pTarget.getTribe().toBBCode());
        } else {
            template = template.replaceAll("%DEFENDER%", "Barbaren");
        }
        template = template.replaceAll("%TARGET%", pTarget.toBBCode());
        template = template.replaceAll("%SEND%", sendtime);
        template = template.replaceAll("%ARRIVE%", arrivetime);
        //replace place var
        String baseURL = ServerManager.getServerURL(GlobalOptions.getSelectedServer()) + "/";
        String placeURL = baseURL + "game.php?village=";
        int uvID = GlobalOptions.getUVID();
        if (uvID >= 0) {
            placeURL = baseURL + "game.php?t=" + uvID + "&village=";
        }
        placeURL += pSource.getId() + "&screen=place&mode=command&target=" + pTarget.getId();

        String placeLink = "[url=\"" + placeURL + "\"]Versammlungsplatz[/url]";
        template = template.replaceAll("%PLACE%", placeLink);
        template += "\n";

        return template;
    }
}
