/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.model.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior
 */
@XmlRootElement
public class ReportInfectionRequestModel {

    private String reporterId;

    /**
     * @return the reporterId
     */
    public String getReporterId() {
        return reporterId;
    }

    /**
     * @param reporterId the reporterId to set
     */
    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

}
