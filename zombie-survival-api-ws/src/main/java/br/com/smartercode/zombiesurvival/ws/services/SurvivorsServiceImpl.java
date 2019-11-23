/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.exceptions.ValidationFieldException;
import br.com.smartercode.zombiesurvival.ws.io.dao.ResourceDAO;
import br.com.smartercode.zombiesurvival.ws.io.dao.SurvivorDAO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import br.com.smartercode.zombiesurvival.ws.utils.WSUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author junior
 */
@Stateless
public class SurvivorsServiceImpl implements SurvivorsService {

    @Inject
    private SurvivorDAO survivorsDao;
    @Inject
    private ResourceDAO resourcesDao;

    @Inject
    private WSUtils wsUtils;

    @Override
    public SurvivorDTO create(SurvivorDTO survivor) {
        try {
            // Generate random id
            survivor.setSurvivorId(wsUtils.generateId(30));
            // Set survivor in inventory list
            survivor.getInventory().forEach(i -> i.setSurvivor(survivor));
            // Save survivor into database
            survivorsDao.create(survivor);

        } catch (ConstraintViolationException e) {
            String message = e.getConstraintViolations().stream().map(ex -> {
                StringBuilder sb = new StringBuilder();
                sb.append(ex.getPropertyPath().toString())
                        .append(": ")
                        .append(ex.getMessage());
                return sb.toString();
            }).collect(Collectors.joining(", "));

            throw new ValidationFieldException(message);
        }

        return survivor;
    }

    @Override
    public SurvivorDTO findBySurvivorId(String survivorId) {
        return survivorsDao.findBySurvivorIdWithInventory(survivorId);
    }

    @Override
    public SurvivorDTO updateLocation(SurvivorDTO survivor) {
        // Find survivor by survivorId
        SurvivorDTO storedSurvivor = survivorsDao.findBySurvivorId(survivor.getSurvivorId());
        // Update location
        storedSurvivor.setLongitude(survivor.getLongitude());
        storedSurvivor.setLatitude(survivor.getLatitude());
        // Save survivor updated values into database
        survivorsDao.edit(storedSurvivor);

        return survivor;
    }

    @Override
    public List<SurvivorDTO> listAll() {
        return survivorsDao.findAll();
    }

    @Override
    public List<SurvivorDTO> listAllWithInventory() {
        return survivorsDao.findAllWithInventory();
    }

    @Override
    public List<SurvivorDTO> listAllSurvivorsWithInventory() {
        return survivorsDao.findAllSurvivorsWithInventory();
    }
}
