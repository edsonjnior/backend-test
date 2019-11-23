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
public class ResourceGroupReportRest {

    private String resourceId;
    private String item;
    private Double value;

    public ResourceGroupReportRest() {
    }

    public ResourceGroupReportRest(String resourceId, String item, Double value) {
        this.resourceId = resourceId;
        this.item = item;
        this.value = value;
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
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

}
