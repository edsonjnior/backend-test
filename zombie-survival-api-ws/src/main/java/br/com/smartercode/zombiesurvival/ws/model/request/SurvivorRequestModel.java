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
public class SurvivorRequestModel {

    private String name;
    private Integer age;
    private String gender;
    private String latitude;
    private String longitude;
    private Set<InventoryRequestModel> inventory;
    private boolean infected;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the last latitude location
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the last latitude location to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the last longitude location
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the last longitude location to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the infected
     */
    public boolean isInfected() {
        return infected;
    }

    /**
     * @param infected the infected to set
     */
    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    /**
     * @return the inventory
     */
    public Set<InventoryRequestModel> getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Set<InventoryRequestModel> inventory) {
        this.inventory = inventory;
    }
}
