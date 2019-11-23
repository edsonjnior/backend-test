/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "report_infection")
public class ReportInfectionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "infected_survivor_id")
    private SurvivorDTO infected;
    @ManyToOne
    @JoinColumn(name = "reporter_survivor_id")
    private SurvivorDTO reporter;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReport;

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
     * @return the infected
     */
    public SurvivorDTO getInfected() {
        return infected;
    }

    /**
     * @param infected the infected to set
     */
    public void setInfected(SurvivorDTO infected) {
        this.infected = infected;
    }

    /**
     * @return the reporter
     */
    public SurvivorDTO getReporter() {
        return reporter;
    }

    /**
     * @param reporter the reporter to set
     */
    public void setReporter(SurvivorDTO reporter) {
        this.reporter = reporter;
    }

    /**
     * @return the dateReport
     */
    public Date getDateReport() {
        return dateReport;
    }

    /**
     * @param dateReport the dateReport to set
     */
    public void setDateReport(Date dateReport) {
        this.dateReport = dateReport;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final ReportInfectionDTO other = (ReportInfectionDTO) obj;
        if (!Objects.equals(this.infected, other.infected)) {
            return false;
        }
        if (!Objects.equals(this.reporter, other.reporter)) {
            return false;
        }
        return true;
    }

}
