package gr.ed.ch.webtechnikon.resources;

import gr.ed.ch.webtechnikon.dtos.ApiResult;
import gr.ed.ch.webtechnikon.dtos.RepairDTO;
import gr.ed.ch.webtechnikon.models.Repair;
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

@Path("/repairs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RepairRC {

    @Inject
    private AdminServiceInterface adminService;

    @POST
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response createRepair(@Valid RepairDTO repairDTO) {
        Repair repair = new Repair(); 
        // will be patched
        Optional<Repair> createdRepair = adminService.createRepair(repair);
        if (createdRepair.isPresent()) {
            return Response.ok(ApiResult.success("Repair created successfully", createdRepair.get(), 1, 1)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(ApiResult.failure("Error creating repair")).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response updateRepair(@Valid RepairDTO repairDTO) {
        Repair repair = new Repair(); 
        //will be patched
        Optional<Repair> updatedRepair = adminService.updateRepair(repair);
        if (updatedRepair.isPresent()) {
            return Response.ok(ApiResult.success("Repair updated successfully", updatedRepair.get(), 1, 1)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(ApiResult.failure("Error updating repair")).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADMIN") 
    public Response deleteRepair(@PathParam("id") Long repairId) {
        if (adminService.deleteRepair(repairId)) {
            return Response.ok(ApiResult.success("Repair deleted successfully", null, 0, 0)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(ApiResult.failure("Repair not found")).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"}) 
    public Response getRepairById(@PathParam("id") Long repairId) {
        Optional<Repair> repair = adminService.getRepairById(repairId);
        if (repair.isPresent()) {
            return Response.ok(ApiResult.success("Repair retrieved successfully", repair.get(), 1, 1)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(ApiResult.failure("Repair not found")).build();
    }

    @GET
    @RolesAllowed("ADMIN") 
    public Response getAllRepairs() {
        List<Repair> repairs = adminService.getAllRepairs();
        return Response.ok(ApiResult.success("Repairs retrieved successfully", repairs, repairs.size(), 1)).build();
    }
}
