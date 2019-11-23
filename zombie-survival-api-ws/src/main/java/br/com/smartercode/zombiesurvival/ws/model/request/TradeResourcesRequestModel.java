/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.model.request;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior
 */
@XmlRootElement
public class TradeResourcesRequestModel {

    private Set<InventoryRequestModel> items;
    private SurvivorTradeRequestModel survivor;

    /**
     * @return the items
     */
    public Set<InventoryRequestModel> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Set<InventoryRequestModel> items) {
        this.items = items;
    }

    /**
     * @return the survivor
     */
    public SurvivorTradeRequestModel getSurvivor() {
        return survivor;
    }

    /**
     * @param survivor the survivor to set
     */
    public void setSurvivor(SurvivorTradeRequestModel survivor) {
        this.survivor = survivor;
    }

}
