/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.shared.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "survivors")
public class SurvivorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survivor_id")
    private String survivorId;
    @NotNull(message = "This field is required")
    @Size(min = 3, message = "This field must at least 3 characters")
    private String name;
    @NotNull(message = "This field is required")
    @Min(value = 1, message = "This field must be greater than 0")
    private Integer age;
    @NotNull(message = "This field is required")
    private String gender;
    @NotNull(message = "This field is required")
    private String latitude;
    @NotNull(message = "This field is required")
    private String longitude;
    @OneToMany(mappedBy = "survivor", cascade = CascadeType.ALL)
    private Set<InventoryDTO> inventory;
    private boolean infected;

    public SurvivorDTO() {
    }

    public SurvivorDTO(String survivorId) {
        this.survivorId = survivorId;
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
     * @return the last longitude locatioin
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
    public Set<InventoryDTO> getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Set<InventoryDTO> inventory) {
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SurvivorDTO other = (SurvivorDTO) obj;
        return Objects.equals(this.id, other.id);
    }

}
