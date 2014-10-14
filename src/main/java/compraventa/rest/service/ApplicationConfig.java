/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author marcelo
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(compraventa.rest.service.ClientesFacadeREST.class);
        resources.add(compraventa.rest.service.ComprasFacadeREST.class);
        resources.add(compraventa.rest.service.DetalleCompraFacadeREST.class);
        resources.add(compraventa.rest.service.DetalleVentaFacadeREST.class);
        resources.add(compraventa.rest.service.FacturasFacadeREST.class);
        resources.add(compraventa.rest.service.ProductosFacadeREST.class);
        resources.add(compraventa.rest.service.ProveedoresFacadeREST.class);
        resources.add(compraventa.rest.service.VentasFacadeREST.class);
    }
    
}
