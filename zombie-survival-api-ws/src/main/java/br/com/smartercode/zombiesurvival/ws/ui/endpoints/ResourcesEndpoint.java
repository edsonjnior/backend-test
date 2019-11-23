/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.ui.endpoints;

import br.com.smartercode.zombiesurvival.ws.model.request.ResourceRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.response.ResourceRest;
import br.com.smartercode.zombiesurvival.ws.services.ResourcesService;
import br.com.smartercode.zombiesurvival.ws.shared.dto.ResourceDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author junior
 */
@Path("resources")
@Api("/Resources Service")
@SwaggerDefinition(tags = {
    @Tag(name = "Resources service", description = "REST Endpoint for Resources Service")})
public class ResourcesEndpoint {

    @EJB
    private ResourcesService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid ResourceRequestModel model) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(model, resourceDTO);

        resourceDTO = service.createResource(resourceDTO);

        return Response.created(UriBuilder.fromResource(ResourcesEndpoint.class)
                .path(String.valueOf(resourceDTO.getResourceId())).build()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResourceRest> listAll() {
        List<ResourceDTO> allResources = service.listAllResources();

        List<ResourceRest> resourcesRest = allResources.stream().map(s -> {
            ResourceRest sr = new ResourceRest();
            BeanUtils.copyProperties(s, sr);
            return sr;
        }).collect(Collectors.toList());

        return resourcesRest;
    }
}
