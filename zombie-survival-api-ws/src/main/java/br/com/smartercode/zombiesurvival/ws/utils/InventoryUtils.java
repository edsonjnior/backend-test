/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.utils;

import br.com.smartercode.zombiesurvival.ws.model.request.InventoryRequestModel;
import br.com.smartercode.zombiesurvival.ws.services.ResourcesService;
import br.com.smartercode.zombiesurvival.ws.shared.dto.ResourceDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.InventoryDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author junior
 */
@Dependent
public class InventoryUtils {

    @Inject
    private ResourcesService resourcesService;

    /**
     * Converts InventoryRequestModel to InventoryDTO
     *
     * @param inventoryList
     * @return Set of InventoryDTO
     */
    public Set<InventoryDTO> toInventoryDTO(Set<InventoryRequestModel> inventoryList) {
        List<ResourceDTO> allResources = resourcesService.listAllResources();

        Set<ResourceDTO> collect = allResources.stream().filter(
                res -> inventoryList.stream()
                        .anyMatch(inv -> inv.getResourceId().equals(res.getResourceId())))
                .collect(Collectors.toSet());

        Set<InventoryDTO> inventory = inventoryList.stream().map(irm -> {
            InventoryDTO inventoryDTO = new InventoryDTO();
            ResourceDTO resource = collect.stream().filter(resDTO -> resDTO.getResourceId().equals(irm.getResourceId())).findFirst().orElse(null);
            inventoryDTO.setResource(resource);
            inventoryDTO.setQuantity(irm.getQuantity());

            return inventoryDTO;
        }).collect(Collectors.toSet());

        return inventory;
    }
}
