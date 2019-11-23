/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.ui.endpoints;

import br.com.smartercode.zombiesurvival.ws.model.response.ResourceGroupReportRest;
import br.com.smartercode.zombiesurvival.ws.services.SurvivorsService;
import br.com.smartercode.zombiesurvival.ws.shared.dto.InventoryDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.ResourceDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author junior
 */
@Path("inventories")
@Api("/Inventory Service")
@SwaggerDefinition(tags = {
    @Tag(name = "Inventory service", description = "REST Endpoint for Inventory Service")})
public class InventoriesEndpoint {

    @EJB
    private SurvivorsService survivorService;

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public Response proccessReport(
            @DefaultValue("false") @QueryParam("infected") Boolean infected,
            @DefaultValue("avg") @QueryParam("query") String query) {
        List<SurvivorDTO> allSurvivors = survivorService.listAllWithInventory();

        if (query.equalsIgnoreCase("avg")) {
            Map<ResourceDTO, Double> group = allSurvivors.stream()
                    .flatMap(s -> s.getInventory().stream())
                    .collect(Collectors.groupingBy(InventoryDTO::getResource, Collectors.averagingInt(InventoryDTO::getQuantity)));

            List<ResourceGroupReportRest> list = group.entrySet().stream().map(res -> {
                return new ResourceGroupReportRest(res.getKey().getResourceId(), res.getKey().getItem(), res.getValue());
            }).collect(Collectors.toList());

            return Response.ok().entity(list).build();
        } else if (query.equalsIgnoreCase("points")) {
            Map<ResourceDTO, Double> group = allSurvivors.stream().filter(s -> infected ? s.isInfected() : !s.isInfected())
                    .flatMap(s -> s.getInventory().stream())
                    .collect(
                            Collectors.groupingBy(
                                    InventoryDTO::getResource,
                                    Collectors.summingDouble(i -> {
                                        return i.getQuantity() * i.getResource().getPoints();
                                    })));

            List<ResourceGroupReportRest> list = group.entrySet().stream().map(res -> {
                return new ResourceGroupReportRest(res.getKey().getResourceId(), res.getKey().getItem(), res.getValue());
            }).collect(Collectors.toList());

            return Response.ok().entity(list).build();
        }

        return Response.ok().build();
    }
}
