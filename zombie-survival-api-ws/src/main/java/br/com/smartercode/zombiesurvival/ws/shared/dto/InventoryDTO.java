/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.shared.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "inventory")
public class InventoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survivor_id")
    private SurvivorDTO survivor;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private ResourceDTO resource;

    private int quantity;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the survivor
     */
    public SurvivorDTO getSurvivor() {
        return survivor;
    }

    /**
     * @param survivor the survivor to set
     */
    public void setSurvivor(SurvivorDTO survivor) {
        this.survivor = survivor;
    }

    /**
     * @return the resource
     */
    public ResourceDTO getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(ResourceDTO resource) {
        this.resource = resource;
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
