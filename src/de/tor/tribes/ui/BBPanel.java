/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BBPanel.java
 *
 * Created on Feb 17, 2011, 10:48:36 PM
 */
package de.tor.tribes.ui;

import de.tor.tribes.util.Constants;
import java.awt.Color;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.DefaultStyledDocument;

/**
 *
 * @author Torridity
 */
public class BBPanel extends javax.swing.JPanel {

    private String sBuffer = "";

    /** Creates new form BBPanel */
    public BBPanel() {
        initComponents();
        //((HTMLEditorKit) jTextPane1.getEditorKit()).setLinkCursor(new Cursor(Cursor.HAND_CURSOR));
        jTextPane1.setBackground(Constants.DS_BACK_LIGHT);
        jTextPane1.addHyperlinkListener(new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                System.out.println(e.getURL());
                System.out.println(e.getDescription());
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jBoldButton = new javax.swing.JButton();
        jItalicButton = new javax.swing.JButton();
        jUnderlineButton = new javax.swing.JButton();
        jStrokeButton = new javax.swing.JButton();
        jTribeButton = new javax.swing.JButton();
        jAllyButton = new javax.swing.JButton();
        jVillageButton = new javax.swing.JButton();
        jQuoteButton = new javax.swing.JButton();
        jLinkButton = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/replace2.png"))); // NOI18N
        jToggleButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireStateChangeEvent(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane1);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jBoldButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/bold.gif"))); // NOI18N
        jBoldButton.setEnabled(false);
        jBoldButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jBoldButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jBoldButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jBoldButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireAddContentEvent(evt);
            }
        });
        jPanel1.add(jBoldButton);

        jItalicButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/italic.gif"))); // NOI18N
        jItalicButton.setEnabled(false);
        jItalicButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jItalicButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jItalicButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jItalicButton);

        jUnderlineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/underline.gif"))); // NOI18N
        jUnderlineButton.setEnabled(false);
        jUnderlineButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jUnderlineButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jUnderlineButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jUnderlineButton);

        jStrokeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/stroked.gif"))); // NOI18N
        jStrokeButton.setEnabled(false);
        jStrokeButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jStrokeButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jStrokeButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jStrokeButton);

        jTribeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/tribe.gif"))); // NOI18N
        jTribeButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jTribeButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jTribeButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jTribeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireAddContentEvent(evt);
            }
        });
        jPanel1.add(jTribeButton);

        jAllyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/ally.gif"))); // NOI18N
        jAllyButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jAllyButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jAllyButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jAllyButton);

        jVillageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/village.gif"))); // NOI18N
        jVillageButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jVillageButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jVillageButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jVillageButton);

        jQuoteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/quote.gif"))); // NOI18N
        jQuoteButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jQuoteButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jQuoteButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jQuoteButton);

        jLinkButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/link.gif"))); // NOI18N
        jLinkButton.setMaximumSize(new java.awt.Dimension(20, 20));
        jLinkButton.setMinimumSize(new java.awt.Dimension(20, 20));
        jLinkButton.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jLinkButton);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/image.gif"))); // NOI18N
        jButton10.setMaximumSize(new java.awt.Dimension(20, 20));
        jButton10.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton10.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jButton10);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/color.gif"))); // NOI18N
        jButton9.setMaximumSize(new java.awt.Dimension(20, 20));
        jButton9.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton9.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jButton9);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/size.gif"))); // NOI18N
        jButton12.setMaximumSize(new java.awt.Dimension(20, 20));
        jButton12.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton12.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(jButton12);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fireStateChangeEvent(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fireStateChangeEvent
        setEditMode(jToggleButton1.isSelected());
        jBoldButton.setEnabled(jToggleButton1.isSelected());
        jItalicButton.setEnabled(jToggleButton1.isSelected());
    }//GEN-LAST:event_fireStateChangeEvent

    private void fireAddContentEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireAddContentEvent
        String code = "";
        if (evt.getSource() == jBoldButton) {
code = "[b]";
   
        } else if (evt.getSource() == jItalicButton) {
        }
        try {
            int s = jTextPane1.getSelectionStart();
            int e = jTextPane1.getSelectionEnd();
            String t = ((DefaultStyledDocument) jTextPane1.getDocument()).getText(s, e - s);
            ((DefaultStyledDocument) jTextPane1.getDocument()).remove(s, e - s);
            ((DefaultStyledDocument) jTextPane1.getDocument()).insertString(s, "[b]" + t + "[/b]", null);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }//GEN-LAST:event_fireAddContentEvent

    private void setEditMode(boolean pToEditMode) {
        if (pToEditMode) {
            jTextPane1.setContentType("text/plain");
            jTextPane1.setText(sBuffer);
        } else {
            sBuffer = jTextPane1.getText();
            buildFormattedCode();
        }
        jTextPane1.setEditable(pToEditMode);
    }

    private void buildFormattedCode() {
        jTextPane1.setContentType("text/html");
        //replace standard HTML items
        Map<String, String> bbMap = new TreeMap<String, String>();
        bbMap.put("(\r\n|\r|\n|\n\r)", "<br/>");
        bbMap.put("\\[b\\](.+?)\\[/b\\]", "<strong>$1</strong>");
        bbMap.put("\\[s\\](.+?)\\[/s\\]", "<s>$1</s>");
        bbMap.put("\\[i\\](.+?)\\[/i\\]", "<span style='font-style:italic;'>$1</span>");
        bbMap.put("\\[u\\](.+?)\\[/u\\]", "<span style='text-decoration:underline;'>$1</span>");
        /* bbMap.put("\\[h1\\](.+?)\\[/h1\\]", "<h1>$1</h1>");
        bbMap.put("\\[h2\\](.+?)\\[/h2\\]", "<h2>$1</h2>");
        bbMap.put("\\[h3\\](.+?)\\[/h3\\]", "<h3>$1</h3>");
        bbMap.put("\\[h4\\](.+?)\\[/h4\\]", "<h4>$1</h4>");
        bbMap.put("\\[h5\\](.+?)\\[/h5\\]", "<h5>$1</h5>");
        bbMap.put("\\[h6\\](.+?)\\[/h6\\]", "<h6>$1</h6>");*/
        bbMap.put("\\[quote\\](.+?)\\[/quote\\]", "<blockquote>$1</blockquote>");
        /*  bbMap.put("\\[center\\](.+?)\\[/center\\]", "<div align='center'>$1");
        bbMap.put("\\[align=(.+?)\\](.+?)\\[/align\\]", "<div align='$1'>$2");*/
        bbMap.put("\\[color=(.+?)\\](.+?)\\[/color\\]", "<span style='color:$1;'>$2</span>");
        bbMap.put("\\[size=(.+?)\\](.+?)\\[/size\\]", "<span style='font-size:$1;'>$2</span>");
        bbMap.put("\\[img\\](.+?)\\[/img\\]", "<img src='$1' />");
        bbMap.put("\\[img=(.+?),(.+?)\\](.+?)\\[/img\\]", "<img width='$1' height='$2' src='$3' />");
        /*  bbMap.put("\\[email\\](.+?)\\[/email\\]", "<a href='mailto:$1'>$1</a>");
        bbMap.put("\\[email=(.+?)\\](.+?)\\[/email\\]", "<a href='mailto:$1'>$2</a>");*/
        bbMap.put("\\[url\\](.+?)\\[/url\\]", "<a href='$1'>$1</a>");
        bbMap.put("\\[url=(.+?)\\](.+?)\\[/url\\]", "<a href='$1'>$2</a>");

        String html = sBuffer;
        int lBefore = 0;
        do {
            //do several times to get wrapped contents
            lBefore = html.length();
            for (Map.Entry entry : bbMap.entrySet()) {
                html = html.replaceAll(entry.getKey().toString(), entry.getValue().toString());
            }
        } while (html.length() != lBefore);

        bbMap.clear();

        //replace special items
        bbMap.put("\\[tribe\\](.+?)\\[/tribe\\]", "$1");
        bbMap.put("\\[player\\](.+?)\\[/player\\]", "$1");
        bbMap.put("\\[ally\\](.+?)\\[/ally\\]", "$1");
        bbMap.put("\\[coord\\](.+?)\\[/coord\\]", "$1");
        bbMap.put("\\[village\\](.+?)\\[/village\\]", "$1");

        do {
            //do several times to get wrapped contents
            lBefore = html.length();
            for (Map.Entry entry : bbMap.entrySet()) {
                String key = entry.getKey().toString();
                Pattern p = Pattern.compile(key);
                Matcher m = p.matcher(html);
                //replace special items by links
                while (m.find()) {
                    String newValue = null;
                    if (key.indexOf("tribe") > -1 || key.indexOf("player") > -1) {
                        String tribe = html.substring(m.start(), m.end()).replaceAll(entry.getKey().toString(), "$1");
                        newValue = "<a href='#" + tribe + "'>" + tribe + "</a>";
                    } else if (key.indexOf("ally") > -1) {
                        String ally = html.substring(m.start(), m.end()).replaceAll(entry.getKey().toString(), "$1");
                        newValue = "<a href='##" + ally + "'>" + ally + "</a>";
                    } else if (key.indexOf("coord") > -1 || key.indexOf("village") > -1) {
                        String coord = html.substring(m.start(), m.end()).replaceAll(entry.getKey().toString(), "$1");
                        newValue = "<a href='###" + coord + "' class='ds_link'>" + coord + "</a>";
                    }
                    if (newValue != null) {
                        html = html.replaceAll(key, newValue);
                    }
                }
            }
        } while (html.length() != lBefore);

        String style = "<style type='text/css'>"
                + ".ds_link{ color:#804000;font-weight:700;text-decoration:none}"
                + "a {color:#4040d0;text-decoration:none}"
                + "blockquote {background-color:#FFFFFF;}"
                + "</style>";
        System.out.println(html);
        jTextPane1.setText("<html><head>" + style + "</head><body>" + html + "</body></html>");
    }

    private void buildFormattedCode2() {
        jTextPane1.setContentType("text/html");
        String content = sBuffer.replaceAll("\\[b\\]", "<b>").replaceAll("\\[/b\\]", "</b>").replaceAll("\\[i\\]", "<i>").replaceAll("\\[/i\\]", "</i>");
        StringTokenizer t = new StringTokenizer(content, "[]");
        StringBuilder b = new StringBuilder();
        b.append("<html><body>");
        boolean lookForTribeToken = false;
        String tribeToken = null;
        boolean lookForAllyToken = false;
        String allyToken = null;
        boolean lookForCoordToken = false;
        String coordToken = null;
        while (t.hasMoreTokens()) {
            String token = t.nextToken();
            if (token.toLowerCase().equals("tribe")) {
                tribeToken = "";
                token = "";
                lookForTribeToken = true;
            } else if (token.toLowerCase().equals("/tribe")) {
                tribeToken = tribeToken.trim();
                b.append("<a href='##").append(tribeToken).append("'>").append(tribeToken).append("</a>");
                tribeToken = null;
            } else if (token.toLowerCase().equals("ally")) {
                allyToken = "";
                token = "";
                lookForAllyToken = true;
            } else if (token.toLowerCase().equals("/ally")) {
                allyToken = allyToken.trim();
                b.append("<a href='#").append(allyToken).append("'>").append(allyToken).append("</a>");
                allyToken = null;
            } else if (token.toLowerCase().equals("coord")) {
                coordToken = "";
                token = "";
                lookForCoordToken = true;
            } else if (token.toLowerCase().equals("/coord")) {
                coordToken = coordToken.trim();
                b.append("<a href='###").append(coordToken).append("'>").append(coordToken).append("</a>");
                coordToken = null;
            }


            if (lookForTribeToken) {
                tribeToken += token;
            } else if (lookForAllyToken) {
                allyToken += token;
            } else if (lookForCoordToken) {
                coordToken += token;
            } else {
                b.append(token);
            }
        }

        b.append("</body></html>");
        jTextPane1.setText(b.toString().replaceAll("\n", "<BR/>"));
    }

    private void buildFormattedCode1() {
        jTextPane1.setContentType("text/html");
        StringTokenizer t = new StringTokenizer(sBuffer, " \t");
        StringBuilder b = new StringBuilder();
        b.append("<html><body>");
        String tribeString = null;
        while (t.hasMoreTokens()) {
            String token = t.nextToken();
            token = token.replaceAll("\n", "<BR/>").replaceAll("\r", "").trim();
            if (token.matches("\\[coord\\]\\(*[0-9]{1,3}\\|[0-9]{1,3}\\)*\\[/coord\\]")) {
                //replace coord
                String coords = token.replaceAll("\\[coord\\]", "").replaceAll("\\[/coord\\]", "").replaceAll("<BR/>", "");
                b.append(" <a href='#").append(coords).append("'>").append(coords).append("</a>");
            } else if (token.matches("\\[tribe\\].{3,}\\[/tribe\\]")) {
                //replace full tribe
                String tribe = token.replaceAll("\\[tribe\\]", "").replaceAll("\\[/tribe\\]", "").replaceAll("<BR/>", "");
                b.append(" <a href='##").append(tribe).append("'>").append(tribe).append("</a>");
            } else if (token.matches("\\[tribe\\].*")) {
                //replace tribe start
                String tribe = token.replaceAll("\\[tribe\\]", "").replaceAll("<BR/>", "").trim();
                tribeString = tribe;
            } else if (token.matches(".*\\[/tribe\\]")) {
                //replace tribe end
                String tribe = token.replaceAll("\\[/tribe\\]", "").replaceAll("<BR/>", "").trim();
                tribeString += " " + tribe;
                b.append(" <a href='##").append(tribeString).append("'>").append(tribeString).append("</a>");
                tribeString = null;
            } else if (token.matches("\\[ally\\].{3,}\\[/ally\\]")) {
                //replace full ally
                String ally = token.replaceAll("\\[ally\\]", "").replaceAll("\\[/ally\\]", "").replaceAll("<BR/>", "");
                b.append(" <a href='###").append(ally).append("'>").append(ally).append("</a>");
            } else if (token.matches("\\[b\\].*\\[/b\\]")) {
                //replace bold test
                String text = token.replaceAll("\\[b\\]", "").replaceAll("\\[/b\\]", "");
                b.append(" <b>").append(text).append("</b>");
            } else if (token.matches("\\[b\\].*")) {
                //replace bold start
                String text = token.replaceAll("\\[b\\]", "");
                b.append(" <b>").append(text);
            } else if (token.matches(".*\\[/b\\]")) {
                //replace bold end
                String text = token.replaceAll("\\[/b\\]", "");
                b.append(" ").append(text).append("</b>");
            } else if (token.matches("\\[i\\].*\\[/i\\]")) {
                //replace full italic
                String text = token.replaceAll("\\[i\\]", "").replaceAll("\\[/i\\]", "");
                b.append(" <i>").append(text).append("</i>");
            } else if (token.matches("\\[i\\].*")) {
                //replace italic start
                String text = token.replaceAll("\\[i\\]", "");
                b.append(" <i>").append(text);
            } else if (token.matches(".*\\[/i\\]")) {
                //replace italic end
                String text = token.replaceAll("\\[/i\\]", "");
                b.append(" ").append(text).append("</i>");
            } else {
                //normal token
                if (tribeString == null) {
                    b.append(" ").append(token);
                } else {
                    tribeString += " " + token;
                }
            }

        }
        b.append("</body></html>");
        jTextPane1.setText(b.toString());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAllyButton;
    private javax.swing.JButton jBoldButton;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jItalicButton;
    private javax.swing.JButton jLinkButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jQuoteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jStrokeButton;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton jTribeButton;
    private javax.swing.JButton jUnderlineButton;
    private javax.swing.JButton jVillageButton;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        f.add(new BBPanel());
        f.setVisible(true);
        /* String test = "[b]Tester[/b]";
        //"\\[b\\](.+?)\\[/b\\]", "<strong>$1</strong>")
        Pattern p = Pattern.compile("\\[b\\](.+?)\\[/b\\]");
        Matcher m = p.matcher(test);*/


    }
}
/*[tribe]Amr al as[/tribe]
[i]test test
[/i]
 */
