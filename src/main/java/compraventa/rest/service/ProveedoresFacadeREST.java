/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.ProveedoresFacade;
import compraventa.entity.Proveedores;
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
@Path("proveedores")
public class ProveedoresFacadeREST {
    @EJB
    private ProveedoresFacade proveedor;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Proveedores entity) {
        proveedor.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Proveedores entity) {
        proveedor.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        proveedor.remove(proveedor.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Proveedores find(@PathParam("id") Integer id) {
        return proveedor.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Proveedores> findAll() {
        return proveedor.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Proveedores> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return proveedor.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(proveedor.count());
    }

}
