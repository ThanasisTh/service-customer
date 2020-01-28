package dtu.services.rest.resources;

import core.CustomerPool;
import dtupay.DtuPayCustomerRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api/customer")
public class CustomerResource
{
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") String cpr)
    {
        DtuPayCustomerRepresentation c = CustomerPool.get(cpr);
        if (c == null)
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
        else
        {
            DtuPayCustomerRepresentation customer = CustomerPool.get(cpr);
            return Response.status(Response.Status.OK).entity(customer).build();
        }
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        if (CustomerPool.getAll().isEmpty())
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
        else
        {
            List<DtuPayCustomerRepresentation> customers = new ArrayList<>(CustomerPool.getAll());
            return Response.status(Response.Status.OK).entity(customers).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(DtuPayCustomerRepresentation c)
    {
        if (CustomerPool.create(c))
        {
            DtuPayCustomerRepresentation customer = CustomerPool.get(c.getCprNumber());
            return Response.status(Response.Status.CREATED).entity(customer).build();
        }
        else
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(DtuPayCustomerRepresentation c)
    {
        if (CustomerPool.update(c))
        {
            DtuPayCustomerRepresentation customer = CustomerPool.get(c.getCprNumber());
            return Response.status(Response.Status.ACCEPTED).entity(customer).build();
        }
        else
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String cpr)
    {
        if (!CustomerPool.delete(cpr))
        {
            return Response.status(Response.Status.NOT_FOUND).entity(false).build();
        }
        else
        {
            return Response.status(Response.Status.OK).entity(true).build();
        }
    }
}
