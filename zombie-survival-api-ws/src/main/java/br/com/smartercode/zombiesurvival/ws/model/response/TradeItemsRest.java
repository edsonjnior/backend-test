/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.model.response;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior
 */
@XmlRootElement
public class TradeItemsRest {

    private String survivor;
    private Set<InventoryNoSurvivorRest> inventory;

    public TradeItemsRest() {
    }

    public TradeItemsRest(String survivor, Set<InventoryNoSurvivorRest> inventory) {
        this.survivor = survivor;
        this.inventory = inventory;
    }

    /**
     * @return the survivor
     */
    public String getSurvivor() {
        return survivor;
    }

    /**
     * @param survivor the survivor to set
     */
    public void setSurvivor(String survivor) {
        this.survivor = survivor;
    }

    /**
     * @return the inventory
     */
    public Set<InventoryNoSurvivorRest> getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Set<InventoryNoSurvivorRest> inventory) {
        this.inventory = inventory;
    }

}
