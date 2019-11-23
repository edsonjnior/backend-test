/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.io.dao;

import br.com.smartercode.zombiesurvival.ws.shared.dto.InventoryDTO;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author junior
 */
@Dependent
public class InventoryDAO extends DAO<InventoryDTO> {

    @PersistenceContext(unitName = "zombie-survival-ws-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventoryDAO() {
        super(InventoryDTO.class);
    }
}
