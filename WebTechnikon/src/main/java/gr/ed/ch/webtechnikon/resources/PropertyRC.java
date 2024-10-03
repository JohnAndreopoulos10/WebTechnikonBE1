package gr.ed.ch.webtechnikon.resources;

import gr.ed.ch.webtechnikon.dtos.ApiResult;
import gr.ed.ch.webtechnikon.dtos.PropertyDTO;
import gr.ed.ch.webtechnikon.models.Property;
import gr.ed.ch.webtechnikon.services.AdminServiceInterface;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/properties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyRC {

    @Inject
    private AdminServiceInterface adminService;

    @POST
    @Transactional
    @RolesAllowed("ADMIN")
    public Response createProperty(@Valid PropertyDTO propertyDTO) {
        Property property = new Property(); 
        // will be patched :) 
        Optional<Property> createdProperty = adminService.createProperty(property);
        if (createdProperty.isPresent()) {
            return Response.ok(ApiResult.success("Property created successfully", createdProperty.get(), 1, 1)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(ApiResult.failure("Error creating property")).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response updateProperty(@Valid PropertyDTO propertyDTO) {
        Property property = new Property(); 
        // will be pathced

        Optional<Property> updatedProperty = adminService.updateProperty(property);
        if (updatedProperty.isPresent()) {
            return Response.ok(ApiResult.success("Property updated successfully", updatedProperty.get(), 1, 1)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(ApiResult.failure("Error updating property")).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response deleteProperty(@PathParam("id") Long propertyId) {
        if (adminService.deleteProperty(propertyId)) {
            return Response.ok(ApiResult.success("Property deleted successfully", null, 0, 0)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(ApiResult.failure("Property not found")).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"}) 
    public Response getPropertyById(@PathParam("id") Long propertyId) {
        Optional<Property> property = adminService.getPropertyById(propertyId);
        if (property.isPresent()) {
            return Response.ok(ApiResult.success("Property retrieved successfully", property.get(), 1, 1)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(ApiResult.failure("Property not found")).build();
    }

    @GET
    @RolesAllowed("USER") 
    public Response getAllProperties() {
        List<Property> properties = adminService.getAllProperties();
        return Response.ok(ApiResult.success("Properties retrieved successfully", properties, properties.size(), 1)).build();
    }
}
