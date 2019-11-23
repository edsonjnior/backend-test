/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.io.dao;

import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author junior
 */
@Dependent
public class SurvivorDAO extends DAO<SurvivorDTO> {

    @PersistenceContext(unitName = "zombie-survival-ws-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SurvivorDAO() {
        super(SurvivorDTO.class);
    }

    public List<SurvivorDTO> listAll() {
        return null;
    }

    public SurvivorDTO findBySurvivorId(String survivorId) {
        try {
            TypedQuery<SurvivorDTO> query = em.createQuery("FROM SurvivorDTO s WHERE s.survivorId = :pSurvivorId", SurvivorDTO.class);
            query.setParameter("pSurvivorId", survivorId);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<SurvivorDTO> findAllSurvivors() {
        TypedQuery<SurvivorDTO> query = em.createQuery("SELECT s FROM SurvivorDTO s WHERE s.infected = FALSE ", SurvivorDTO.class);

        return query.getResultList();
    }

    public SurvivorDTO findBySurvivorIdWithInventory(String survivorId) {
        try {
            TypedQuery<SurvivorDTO> query = em.createQuery("SELECT s FROM SurvivorDTO s JOIN FETCH s.inventory WHERE s.survivorId = :pSurvivorId", SurvivorDTO.class);
            query.setParameter("pSurvivorId", survivorId);

            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<SurvivorDTO> findAllSurvivorsWithInventory() {
        TypedQuery<SurvivorDTO> query = em.createQuery("SELECT DISTINCT(s) FROM SurvivorDTO s JOIN FETCH s.inventory WHERE s.infected = FALSE", SurvivorDTO.class);

        return query.getResultList();
    }

    public List<SurvivorDTO> findAllWithInventory() {
        TypedQuery<SurvivorDTO> query = em.createQuery("SELECT DISTINCT(s) FROM SurvivorDTO s JOIN FETCH s.inventory", SurvivorDTO.class);
        return query.getResultList();
    }

}
