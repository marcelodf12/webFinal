/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.DetalleVentaFacade;
import compraventa.entity.DetalleVenta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author marcelo
 */
@Stateless
@Path("detalleventa")
public class DetalleVentaFacadeREST {
    
    @EJB
    private DetalleVentaFacade detalleVenta;
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(DetalleVenta entity) {
        detalleVenta.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, DetalleVenta entity) {
        detalleVenta.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        detalleVenta.remove(detalleVenta.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public DetalleVenta find(@PathParam("id") Integer id) {
        return detalleVenta.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<DetalleVenta> findAll() {
        return detalleVenta.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<DetalleVenta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return detalleVenta.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(detalleVenta.count());
    }
   
}
