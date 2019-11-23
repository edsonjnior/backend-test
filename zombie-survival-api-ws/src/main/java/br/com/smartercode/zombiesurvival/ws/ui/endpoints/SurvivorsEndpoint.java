/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.ui.endpoints;

import br.com.smartercode.zombiesurvival.ws.model.request.LocationRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.request.ReportInfectionRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.request.SurvivorRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.request.SurvivorTradeRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.request.TradeResourcesRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.response.InventoryNoSurvivorRest;
import br.com.smartercode.zombiesurvival.ws.model.response.InventoryRest;
import br.com.smartercode.zombiesurvival.ws.model.response.SurvivorRest;
import br.com.smartercode.zombiesurvival.ws.model.response.TradeItemsRest;
import br.com.smartercode.zombiesurvival.ws.services.SurvivorsService;
import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.InventoryDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.ReportInfectionDTO;
import br.com.smartercode.zombiesurvival.ws.utils.InventoryUtils;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.springframework.beans.BeanUtils;
import br.com.smartercode.zombiesurvival.ws.services.ReportInfectionsService;
import br.com.smartercode.zombiesurvival.ws.services.TradeItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import java.util.Arrays;
import java.util.function.ToIntFunction;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 *
 * @author junior
 */
@Path("survivors")
@Api("/Survivors Service")
@SwaggerDefinition(tags = {
    @Tag(name = "Survivors service", description = "REST Endpoint for Survivors Service")})
public class SurvivorsEndpoint {

    @EJB
    private SurvivorsService survivorService;

    @EJB
    private ReportInfectionsService infectionService;

    @EJB
    private TradeItemsService tradeService;

    @Inject
    private InventoryUtils inventoryUtils;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(SurvivorRequestModel model) {

        SurvivorDTO survivorDTO = new SurvivorDTO();
        BeanUtils.copyProperties(model, survivorDTO);

        Set<InventoryDTO> inventory = inventoryUtils.toInventoryDTO(model.getInventory());
        survivorDTO.setInventory(inventory);
        survivorDTO = survivorService.create(survivorDTO);

        return Response.created(
                UriBuilder.fromResource(SurvivorsEndpoint.class)
                        .path(String.valueOf(survivorDTO.getSurvivorId())).build()).build();
    }

    @GET
    @Path("/{survivorId:[0-9A-Za-z]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("survivorId") String survivorId) {
        // Needs to be implemented
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SurvivorRest> listAll() {
        List<SurvivorDTO> allSurvivors = survivorService.listAllSurvivorsWithInventory();

        List<SurvivorRest> survivorsRest = allSurvivors.stream().map(s -> {
            SurvivorRest sr = new SurvivorRest();
            BeanUtils.copyProperties(s, sr);

            sr.setInventory(s.getInventory().stream().map(i -> {
                InventoryRest inventoryRest = new InventoryRest(i.getResource().getResourceId(), sr.getSurvivorId(), i.getResource().getItem(), i.getQuantity());
                return inventoryRest;
            }).collect(Collectors.toSet()));
            return sr;
        }).collect(Collectors.toList());

        return survivorsRest;
    }

    @PUT
    @Path("/{survivorId:[0-9A-Za-z]*}/update-location")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLocation(@PathParam("survivorId") String survivorId, LocationRequestModel model) {
        SurvivorDTO survivorDTO = new SurvivorDTO();
        survivorDTO.setSurvivorId(survivorId);

        BeanUtils.copyProperties(model, survivorDTO);
        survivorDTO = survivorService.updateLocation(survivorDTO);

        return Response.created(
                UriBuilder.fromResource(SurvivorsEndpoint.class)
                        .path(String.valueOf(survivorDTO.getSurvivorId())).build()).build();
    }

    @POST
    @Path("/{infectedId:[0-9A-Za-z]*}/report-infection")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportInfection(@PathParam("infectedId") String infectedId, ReportInfectionRequestModel model) {
        ReportInfectionDTO infectionDTO = new ReportInfectionDTO();
        infectionDTO.setInfected(new SurvivorDTO(infectedId));
        infectionDTO.setReporter(new SurvivorDTO(model.getReporterId()));

        infectionService.createReport(infectionDTO);

        return Response.ok().entity(null).build();
    }

    @POST
    @Path("/{survivorId:[0-9A-Za-z]*}/trade-items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tradeItems(@PathParam("survivorId") String survivorId, TradeResourcesRequestModel model) {
        SurvivorTradeRequestModel survivorTradeOrigin = new SurvivorTradeRequestModel(survivorId, model.getItems());
        SurvivorTradeRequestModel survivorTradeDest = new SurvivorTradeRequestModel(model.getSurvivor().getSurvivorId(), model.getSurvivor().getItems());

        tradeService.makeTrade(survivorTradeOrigin, survivorTradeDest);

        SurvivorDTO survivor1 = survivorService.findBySurvivorId(survivorId);
        SurvivorDTO survivor2 = survivorService.findBySurvivorId(model.getSurvivor().getSurvivorId());

        TradeItemsRest trade1 = new TradeItemsRest(survivor1.getName(),
                survivor1.getInventory().stream()
                        .map((InventoryDTO i) -> {
                            return new InventoryNoSurvivorRest(i.getResource().getResourceId(), i.getResource().getItem(), i.getQuantity());
                        }).collect(Collectors.toSet()));

        TradeItemsRest trade2 = new TradeItemsRest(survivor2.getName(),
                survivor2.getInventory().stream()
                        .map((InventoryDTO i) -> {
                            return new InventoryNoSurvivorRest(i.getResource().getResourceId(), i.getResource().getItem(), i.getQuantity());
                        }).collect(Collectors.toSet()));

        List<TradeItemsRest> list = Arrays.asList(trade1, trade2);

        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public Response proccessReport(
            @DefaultValue("false") @QueryParam("infected") Boolean infected,
            @DefaultValue("avg") @QueryParam("query") String query) {
        List<SurvivorDTO> all = survivorService.listAll();

        if (query.equalsIgnoreCase("avg")) {
            double avg = 0;
            ToIntFunction<SurvivorDTO> fn = infected ? (s -> s.isInfected() ? 1 : 0) : (s -> s.isInfected() ? 0 : 1);
            avg = all.stream().mapToInt(fn).average().orElse(0);
            return Response.ok().entity(avg).build();
        }

        return Response.ok().build();
    }
}
