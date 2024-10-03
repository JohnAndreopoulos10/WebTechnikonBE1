package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Owner;
import gr.ed.ch.webtechnikon.models.Property;
import gr.ed.ch.webtechnikon.models.Repair;

import java.util.List;
import java.util.Optional;

public interface AdminServiceInterface {

    // Owner functionalities
    Optional<Owner> createOwner(Owner owner);

    Optional<Owner> updateOwner(Owner owner);

    boolean deleteOwner(Long ownerId);

    boolean softDeleteOwner(Long ownerId);

    Optional<Owner> getOwnerById(Long ownerId);
    
    List<Owner> getAllOwners();

    // Property functionalities
    Optional<Property> createProperty(Property property);

    Optional<Property> updateProperty(Property property);

    boolean deleteProperty(Long propertyId);

    boolean softDeleteProperty(Long propertyId);

    Optional<Property> getPropertyById(Long propertyId);

    List<Property> getAllProperties();

    List<Property> getPropertiesByOwnerId(Long ownerId);

    // Repair functionalities
    Optional<Repair> createRepair(Repair repair);

    Optional<Repair> updateRepair(Repair repair);

    boolean deleteRepair(Long repairId);

    boolean softDeleteRepair(Long repairId);

    Optional<Repair> getRepairById(Long repairId);
    
    List<Repair> getAllRepairs();

    List<Repair> getRepairsByPropertyId(Long propertyId);
}
