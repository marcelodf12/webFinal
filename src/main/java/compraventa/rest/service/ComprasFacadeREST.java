/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.ComprasFacade;
import compraventa.entity.Compras;
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
@Path("compras")
public class ComprasFacadeREST {
    @EJB
    private ComprasFacade compra;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Compras entity) {
        compra.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Compras entity) {
        compra.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        compra.remove(compra.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Compras find(@PathParam("id") Integer id) {
        return compra.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Compras> findAll() {
        return compra.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Compras> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return compra.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(compra.count());
    }
}
