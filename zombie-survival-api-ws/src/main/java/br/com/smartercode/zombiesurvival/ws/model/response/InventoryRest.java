/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.model.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior
 */
@XmlRootElement
public class InventoryRest {

    private String resourceId;
    private String survivorId;
    private String item;
    private int quantity;

    public InventoryRest() {
    }

    public InventoryRest(String resourceId, String survivorId, String item, int quantity) {
        this.resourceId = resourceId;
        this.survivorId = survivorId;
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * @return the resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
