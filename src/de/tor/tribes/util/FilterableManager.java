/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util;

import java.util.LinkedList;
import java.util.List;

/**Manager implementation that allows to filter its elements
 * @author Torridity
 */
public abstract class FilterableManager<C, I extends Filter> {

    /**Cache for filtered elements*/
    private List<C> filteredList = new LinkedList<C>();
    /**List of filters*/
    private List<I> filters = new LinkedList<I>();

    /**Default constructor*/
    public FilterableManager() {
        filteredList = new LinkedList<C>();
        filters = new LinkedList<I>();
    }

    /**Get the amount of elements after filtering
     * @return int Number of filtered elements
     */
    public int getFilteredElementCount() {
        return filteredList.size();
    }

    /**Get the filtered element with the index pIndex
     * @param pIndex Index of the filtered element
     * @return C Element at index pIndex
     */
    public C getFilteredElement(int pIndex) {
        return filteredList.get(pIndex);
    }

    /**Set the list of filters that should be applied and re-filter
     * @param pFilters List of filters to apply
     */
    public void setFilters(List<I> pFilters) {
        filters = new LinkedList<I>(pFilters);
        updateFilters();
    }

    /**Get the unfiltered list of elements*/
    public abstract C[] getUnfilteredElements();

    /**Clear the list of filtered elements*/
    public void clearFilteredList() {
        filteredList = new LinkedList<C>();
    }

    /**Get the list of filtered elements*/
    public List<C> getFilteredList() {
        return filteredList;
    }

    /**Update by re-filtering all elements*/
    public void updateFilters() {
        C[] aElements = getUnfilteredElements();
        List<C> filtered = new LinkedList<C>();
        for (C c : aElements) {
            if (filters == null || filters.isEmpty()) {
                //no filters defined
                filtered.add(c);
            } else {
                //use all filters
                boolean valid = true;
                for (I f : filters) {
                    if (!f.isValid(c)) {
                        //conquer is invalid for the current filter
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    //only add if conquer is valid for all filters
                    filtered.add(c);
                }
            }
        }
        filteredList = new LinkedList<C>(filtered);
    }
}
