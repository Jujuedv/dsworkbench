/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Jejkal
 */
public class JOptionPaneHelper {
  
    public static void showInformationBox(Component pParent, String pMessage, String pTitle) {
        JOptionPane.showMessageDialog(pParent, pMessage, pTitle, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarningBox(Component pParent, String pMessage, String pTitle) {
        JOptionPane.showMessageDialog(pParent, pMessage, pTitle, JOptionPane.WARNING_MESSAGE);
    }

    public static void showErrorBox(Component pParent, String pMessage, String pTitle) {
        JOptionPane.showMessageDialog(pParent, pMessage, pTitle, JOptionPane.ERROR_MESSAGE);
    }

    public static int showQuestionConfirmBox(Component pParent, String pMessage, String pTitle, String pLeftOption, String pRightOption) {
        UIManager.put("OptionPane.noButtonText", pLeftOption);
        UIManager.put("OptionPane.yesButtonText", pRightOption);
        int result = JOptionPane.showConfirmDialog(pParent, pMessage, pTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        return result;
    }

    /**Results: YES_OPTION = Middle
     * NO_OPTION = Left
     * CANCEL_OPTION = Right
     */
    public static int showQuestionThreeChoicesBox(Component pParent, String pMessage, String pTitle, String pLeftOption, String pMiddleOption, String pRightOption) {
        UIManager.put("OptionPane.noButtonText", pLeftOption);
        UIManager.put("OptionPane.yesButtonText", pMiddleOption);
        UIManager.put("OptionPane.cancelButtonText", pRightOption);
        int result = JOptionPane.showConfirmDialog(pParent, pMessage, pTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        return result;
    }

    public static int showInformationConfirmBox(Component pParent, String pMessage, String pTitle, String pLeftOption, String pRightOption) {
        UIManager.put("OptionPane.noButtonText", pLeftOption);
        UIManager.put("OptionPane.yesButtonText", pRightOption);
        int result = JOptionPane.showConfirmDialog(pParent, pMessage, pTitle, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        return result;
    }

    public static int showWarningConfirmBox(Component pParent, String pMessage, String pTitle, String pLeftOption, String pRightOption) {
        UIManager.put("OptionPane.noButtonText", pLeftOption);
        UIManager.put("OptionPane.yesButtonText", pRightOption);
        int result = JOptionPane.showConfirmDialog(pParent, pMessage, pTitle, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        return result;
    }

    public static int showErrorConfirmBox(Component pParent, String pMessage, String pTitle, String pLeftOption, String pRightOption) {
        UIManager.put("OptionPane.noButtonText", pLeftOption);
        UIManager.put("OptionPane.yesButtonText", pRightOption);
        int result = JOptionPane.showConfirmDialog(pParent, pMessage, pTitle, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        return result;
    }
}