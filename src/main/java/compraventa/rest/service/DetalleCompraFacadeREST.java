/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.DetalleCompraFacade;
import compraventa.entity.DetalleCompra;
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
@Path("detallecompra")
public class DetalleCompraFacadeREST {
    @EJB
    private DetalleCompraFacade detalleCompra;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(DetalleCompra entity) {
        detalleCompra.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, DetalleCompra entity) {
        detalleCompra.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        detalleCompra.remove(detalleCompra.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public DetalleCompra find(@PathParam("id") Integer id) {
        return detalleCompra.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<DetalleCompra> findAll() {
        return detalleCompra.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<DetalleCompra> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return detalleCompra.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(detalleCompra.count());
    }    
}
