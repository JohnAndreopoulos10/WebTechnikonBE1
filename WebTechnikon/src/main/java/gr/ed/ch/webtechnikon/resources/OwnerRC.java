package gr.ed.ch.webtechnikon.resources;

import gr.ed.ch.webtechnikon.models.Owner;
import gr.ed.ch.webtechnikon.services.OwnerService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnerRC {

    @Inject
    private OwnerService ownerService;

    @POST
    @Transactional
//    @PermitAll 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Owner createOwner(Owner owner) {
        Optional<Owner> createdOwner = ownerService.createOwner(owner);
        return createdOwner.get();
//        return createdOwner.map(value -> Response.status(Response.Status.CREATED).entity(value).build())
//                .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @PUT
    @Transactional
//    @RolesAllowed("USER") 
    public Response updateOwner(Owner owner) {
        Optional<Owner> updatedOwner = ownerService.updateOwner(owner);
        return updatedOwner.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
//    @RolesAllowed("ADMIN") 
    public Response deleteOwner(@PathParam("id") Long ownerId) {
        if (ownerService.removeOwnerById(ownerId)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
//    @RolesAllowed({"USER", "ADMIN"}) 
    public Response getOwnerById(@PathParam("id") Long ownerId) {
        Optional<Owner> owner = ownerService.getOwnerById(ownerId);
        return owner.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @GET
//    @RolesAllowed("ADMIN") 
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }
}
