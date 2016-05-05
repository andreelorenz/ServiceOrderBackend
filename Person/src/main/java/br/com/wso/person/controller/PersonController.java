package br.com.wso.person.controller;

import br.com.wso.person.entity.Person;
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

@Path("person")
public class PersonController {

    PersonRepository repository = new PersonRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPeople() throws Exception {
        return repository.findAllPerson();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarChamado(@PathParam("id") Long id) {
        Person person = repository.findPerson(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(person).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Person person) throws Exception {
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Person ret = repository.addPerson(person);
            return Response.ok(ret).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Person person) throws Exception {
        if (person == null || person.getId() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            repository.editPerson(person);
            return Response.ok(person).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response remover(@PathParam("id") Long id) {
        Person person = repository.findPerson(id);
        if (person == null || person.getId() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            repository.removePerson(person);
            return Response.ok(person).build();
        }
    }
}
