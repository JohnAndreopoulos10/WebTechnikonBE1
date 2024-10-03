package gr.ed.ch.webtechnikon.resources;

import gr.ed.ch.webtechnikon.models.Owner;
import gr.ed.ch.webtechnikon.models.Property;
import gr.ed.ch.webtechnikon.models.Repair;
import gr.ed.ch.webtechnikon.services.AdminService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/admin")
public class AdminRC {

    @Inject
    private AdminService adminService;

    // ---------------------------- Owner Endpoints ----------------------------

    @POST
    @Path("/owners")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response createOwner(Owner owner) {
        Optional<Owner> createdOwner = adminService.createOwner(owner);
        return createdOwner.map(value -> Response.status(Response.Status.CREATED).entity(value).build())
                .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @PUT
    @Path("/owners")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response updateOwner(Owner owner) {
        Optional<Owner> updatedOwner = adminService.updateOwner(owner);
        return updatedOwner.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/owners/{ownerId}")
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response deleteOwner(@PathParam("ownerId") Long ownerId) {
        if (adminService.deleteOwner(ownerId)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/owners/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"USER", "ADMIN"}) 
    public Response getOwnerById(@PathParam("ownerId") Long ownerId) {
        Optional<Owner> owner = adminService.getOwnerById(ownerId);
        return owner.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN") 
    public List<Owner> getAllOwners() {
        return adminService.getAllOwners();
    }

    
    // ---------------------------- Property Endpoints -------------------------

    @POST
    @Path("/properties")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN")
    public Response createProperty(Property property) {
        Optional<Property> createdProperty = adminService.createProperty(property);
        return createdProperty.map(value -> Response.status(Response.Status.CREATED).entity(value).build())
                .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @PUT
    @Path("/properties")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response updateProperty(Property property) {
        Optional<Property> updatedProperty = adminService.updateProperty(property);
        return updatedProperty.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/properties/{propertyId}")
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response deleteProperty(@PathParam("propertyId") Long propertyId) {
        if (adminService.deleteProperty(propertyId)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/properties/{propertyId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"USER", "ADMIN"}) 
    public Response getPropertyById(@PathParam("propertyId") Long propertyId) {
        Optional<Property> property = adminService.getPropertyById(propertyId);
        return property.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @GET
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER") 
    public List<Property> getAllProperties() {
        return adminService.getAllProperties();
    }

    @GET
    @Path("/owners/{ownerId}/properties")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER") 
    public List<Property> getPropertiesByOwnerId(@PathParam("ownerId") Long ownerId) {
        return adminService.getPropertiesByOwnerId(ownerId);
    }

    // ---------------------------- Repair Endpoints ----------------------------

    @POST
    @Path("/repairs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response createRepair(Repair repair) {
        Optional<Repair> createdRepair = adminService.createRepair(repair);
        return createdRepair.map(value -> Response.status(Response.Status.CREATED).entity(value).build())
                .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @PUT
    @Path("/repairs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response updateRepair(Repair repair) {
        Optional<Repair> updatedRepair = adminService.updateRepair(repair);
        return updatedRepair.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/repairs/{repairId}")
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response deleteRepair(@PathParam("repairId") Long repairId) {
        if (adminService.deleteRepair(repairId)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
