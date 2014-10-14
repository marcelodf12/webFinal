/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.FacturasFacade;
import compraventa.entity.Facturas;
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
@Path("facturas")
public class FacturasFacadeREST {
    @EJB
    private FacturasFacade factura;
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Facturas entity) {
        factura.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Facturas entity) {
        factura.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        factura.remove(factura.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Facturas find(@PathParam("id") Integer id) {
        return factura.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Facturas> findAll() {
        return factura.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Facturas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return factura.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(factura.count());
    }
    
}
