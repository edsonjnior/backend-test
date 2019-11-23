/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.io.dao;

import br.com.smartercode.zombiesurvival.ws.shared.dto.ReportInfectionDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author junior
 */
@Dependent
public class ReportInfectionDAO extends DAO<ReportInfectionDTO> {

    @PersistenceContext(unitName = "zombie-survival-ws-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportInfectionDAO() {
        super(ReportInfectionDTO.class);
    }

    public List<ReportInfectionDTO> countReportsFromSurvivor(SurvivorDTO survivor) {
        TypedQuery<ReportInfectionDTO> query = em.createQuery("SELECT r FROM ReportInfectionDTO r WHERE r.infected = :pSurvivor", ReportInfectionDTO.class);
        query.setParameter("pSurvivor", survivor);

        return query.getResultList();
    }

}
