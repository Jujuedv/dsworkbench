/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util.bb;

import de.tor.tribes.types.FightReport;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Torridity
 */
public class ReportListFormatter extends BasicFormatter<FightReport> {

    private final String[] VARIABLES = new String[]{LIST_START, LIST_END, ELEMENT_COUNT, ELEMENT_ID};
    private final String STANDARD_TEMPLATE = new FightReport().getStandardTemplate();
    private final String TEMPLATE_PROPERTY = "report.list.bbexport.template";

    @Override
    public String getPropertyKey() {
        return TEMPLATE_PROPERTY;
    }

    @Override
    public String getStandardTemplate() {
        return STANDARD_TEMPLATE;
    }

    @Override
    public String formatElements(List<FightReport> pElements, boolean pExtended) {
        StringBuilder b = new StringBuilder();
        int cnt = 1;
        NumberFormat f = getNumberFormatter(pElements.size());
        String beforeList = getHeader();
        String listItemTemplate = getLineTemplate();
        String afterList = getFooter();
        String replacedStart = StringUtils.replaceEach(beforeList, new String[]{ELEMENT_COUNT}, new String[]{f.format(pElements.size())});
        b.append(replacedStart);
        for (FightReport r : pElements) {
            String[] replacements = r.getReplacements(pExtended);
            String itemLine = StringUtils.replaceEach(listItemTemplate, r.getBBVariables(), replacements);
            itemLine = StringUtils.replaceEach(itemLine, new String[]{ELEMENT_ID, ELEMENT_COUNT}, new String[]{f.format(cnt), f.format(pElements.size())});
            b.append(itemLine).append("\n");
            cnt++;
        }
        String replacedEnd = StringUtils.replaceEach(afterList, new String[]{ELEMENT_COUNT}, new String[]{f.format(pElements.size())});
        b.append(replacedEnd);
        return b.toString();
    }

    @Override
    public String[] getTemplateVariables() {
        List<String> vars = new LinkedList<String>();
        for (String var : VARIABLES) {
            vars.add(var);
        }
        for (String var : new FightReport().getBBVariables()) {
            vars.add(var);
        }
        return vars.toArray(new String[vars.size()]);
    }
}
