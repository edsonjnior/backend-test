/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.io.dao;

import br.com.smartercode.zombiesurvival.ws.shared.dto.ResourceDTO;
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
public class ResourceDAO extends DAO<ResourceDTO> {

    @PersistenceContext(unitName = "zombie-survival-ws-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResourceDAO() {
        super(ResourceDTO.class);
    }

    public ResourceDTO findByItemName(String itemName) {
        try {
            TypedQuery<ResourceDTO> query = em.createQuery("FROM ResourceDTO r where r.item = :pItemName", ResourceDTO.class);
            query.setParameter("pItemName", itemName);
            return query.setMaxResults(1).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

}
