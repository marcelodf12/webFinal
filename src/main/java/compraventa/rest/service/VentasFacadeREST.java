/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.VentasFacade;
import compraventa.entity.Ventas;
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
@Path("ventas")
public class VentasFacadeREST {
    @EJB
    private VentasFacade venta;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Ventas entity) {
        venta.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Ventas entity) {
        venta.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        venta.remove(venta.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Ventas find(@PathParam("id") Integer id) {
        return venta.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Ventas> findAll() {
        return venta.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Ventas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return venta.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(venta.count());
    }
    
}
