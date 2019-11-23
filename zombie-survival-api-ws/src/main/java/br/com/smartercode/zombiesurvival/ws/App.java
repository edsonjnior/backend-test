/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws;

import br.com.smartercode.zombiesurvival.ws.ui.endpoints.InventoriesEndpoint;
import br.com.smartercode.zombiesurvival.ws.ui.endpoints.ResourcesEndpoint;
import br.com.smartercode.zombiesurvival.ws.ui.endpoints.SurvivorsEndpoint;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author junior
 */
@ApplicationPath("api/v1")
public class App extends Application {

    public App() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath("/zombie-survival-api-ws");
        beanConfig.setHost("localhost;8080");
        beanConfig.setTitle("Survival Zombie Social Net Documentation");
        beanConfig.setResourcePackage("br.com.smartercode.zombiesurvival.ws");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setVersion("1.0");
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(SurvivorsEndpoint.class);
        resources.add(ResourcesEndpoint.class);
        resources.add(InventoriesEndpoint.class);

        //classes do swagger...
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        return resources;
    }

}
