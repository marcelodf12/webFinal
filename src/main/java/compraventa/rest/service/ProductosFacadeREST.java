/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.rest.service;

import compraventa.ejb.ProductosFacade;
import compraventa.entity.Productos;
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
@Path("productos")
public class ProductosFacadeREST  {
    @EJB
    private ProductosFacade producto;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Productos entity) {
        producto.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Productos entity) {
        producto.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        producto.remove(producto.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Productos find(@PathParam("id") Integer id) {
        return producto.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Productos> findAll() {
        return producto.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Productos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return producto.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(producto.count());
    }

}
