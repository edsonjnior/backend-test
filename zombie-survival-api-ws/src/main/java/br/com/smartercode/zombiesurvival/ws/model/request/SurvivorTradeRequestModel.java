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
public class SurvivorTradeRequestModel {

    private String survivorId;
    private Set<InventoryRequestModel> items;

    public SurvivorTradeRequestModel() {
    }

    public SurvivorTradeRequestModel(String survivorId, Set<InventoryRequestModel> items) {
        this.survivorId = survivorId;
        this.items = items;
    }

    /**
     * @return the survivorId
     */
    public String getSurvivorId() {
        return survivorId;
    }

    /**
     * @param survivorId the survivorId to set
     */
    public void setSurvivorId(String survivorId) {
        this.survivorId = survivorId;
    }

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

}
