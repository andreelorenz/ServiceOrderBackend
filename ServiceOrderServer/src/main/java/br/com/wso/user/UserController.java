/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wso.user;

import br.com.wso.entity.User;
import com.mongodb.DBObject;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andre
 */
@Path("user")
public class UserController {

    UserRepository repository = new UserRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() throws Exception {
        return repository.findAllUser();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarChamado(@PathParam("id") Long id) {
        User user = repository.findUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(user).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(User user) throws Exception {
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            User ret = repository.addUser(user);
            return Response.ok(ret).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(User user) throws Exception {
        if (user == null || user.getId() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            repository.editUser(user);
            return Response.ok(user).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response remover(@PathParam("id") Long id) {
        User user = repository.findUser(id);
        if (user == null || user.getId() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            repository.remove(user);
            return Response.ok(user).build();
        }
    }
}
