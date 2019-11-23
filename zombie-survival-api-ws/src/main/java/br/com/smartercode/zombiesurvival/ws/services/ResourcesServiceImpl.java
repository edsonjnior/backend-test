/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.exceptions.AlreadyExistsException;
import br.com.smartercode.zombiesurvival.ws.exceptions.ErrorMessages;
import br.com.smartercode.zombiesurvival.ws.exceptions.ValidationFieldException;
import br.com.smartercode.zombiesurvival.ws.io.dao.ResourceDAO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.ResourceDTO;
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
public class ResourcesServiceImpl implements ResourcesService {

    @Inject
    private ResourceDAO resourcesDao;

    @Inject
    private WSUtils wsUtils;

    @Override
    public ResourceDTO createResource(ResourceDTO resource) {
        try {
            ResourceDTO storedResource = resourcesDao.findByItemName(resource.getItem());
            if (storedResource != null) {
                throw new AlreadyExistsException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
            }

            // Generate random survivor id
            resource.setResourceId(wsUtils.generateId(30));
            // Save resource into database
            resourcesDao.create(resource);

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

        return resource;
    }

    @Override
    public List<ResourceDTO> listAllResources() {
        return resourcesDao.findAll();
    }

}
