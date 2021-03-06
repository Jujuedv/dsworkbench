/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.types;

import de.tor.tribes.types.ext.Village;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Torridity
 */
 public class Transport {

        private Village target = null;
        private List<Resource> resourceTransports;

        public Transport(Village pTarget, List<Resource> pResourceTransports) {
            target = pTarget;
            setSingleTransports(pResourceTransports);
        }

        public Transport(List<Resource> pResourceTransports) {
            setSingleTransports(pResourceTransports);
        }

        /**
         * @return the amount
         */
        public List<Resource> getSingleTransports() {
            return resourceTransports;
        }

        /**
         * @param amount the amount to set
         */
        public void setSingleTransports(List<Resource> pTransports) {
            resourceTransports = new LinkedList<Resource>();
            resourceTransports.add(new Resource(0, Resource.Type.WOOD));
            resourceTransports.add(new Resource(0, Resource.Type.CLAY));
            resourceTransports.add(new Resource(0, Resource.Type.IRON));
            for (Resource r : pTransports) {
                if (r.getType() == Resource.Type.WOOD) {
                    resourceTransports.get(0).setAmount(r.getAmount());
                } else if (r.getType() == Resource.Type.CLAY) {
                    resourceTransports.get(1).setAmount(r.getAmount());
                } else if (r.getType() == Resource.Type.IRON) {
                    resourceTransports.get(2).setAmount(r.getAmount());
                }
            }
        }

        public boolean hasGoods() {
            return resourceTransports.get(0).getAmount() > 0 || resourceTransports.get(1).getAmount() > 0 || resourceTransports.get(2).getAmount() > 0;
        }
        
        public Village getTarget(){
            return target;
        }
    }