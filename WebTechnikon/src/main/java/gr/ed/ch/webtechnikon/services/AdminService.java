package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Owner;
import gr.ed.ch.webtechnikon.models.Property;
import gr.ed.ch.webtechnikon.models.Repair;
import gr.ed.ch.webtechnikon.repositories.OwnerRepository;
import gr.ed.ch.webtechnikon.repositories.PropertyRepository;
import gr.ed.ch.webtechnikon.repositories.RepairRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * Service class providing administrative functionalities for managing 
 * owners, properties, and repairs within the system. The AdminService 
 * offers methods to create, update, delete, and retrieve entities related 
 * to owners, properties, and repairs. Soft delete operations are also 
 * supported, allowing safe removal without permanent deletion. 
 * Transactions are used to ensure the atomicity of database operations.
 * This service interacts with repositories to handle the persistence 
 * and retrieval of data, with exception handling to manage errors 
 * during operations. Logging is implemented for tracking actions and errors. 
 * @author Johnnie
 */
@Slf4j
public class AdminService implements AdminServiceInterface {

    @Inject
    private OwnerRepository ownerRepository;

    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private RepairRepository repairRepository;

    // ---------------------------- Owner Functionalities ----------------------------

    @Override
    @Transactional
    public Optional<Owner> createOwner(Owner owner) {
        try {
            log.info("Creating new owner: {}", owner);
            return ownerRepository.saveOwner(owner);
        } catch (Exception e) {
            log.error("Error creating owner: {}", owner, e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Owner> updateOwner(Owner owner) {
        try {
            log.info("Updating owner with ID: {}", owner.getOwnerId());
            return ownerRepository.updateOwner(owner);
        } catch (Exception e) {
            log.error("Error updating owner with ID: {}", owner.getOwnerId(), e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean deleteOwner(Long ownerId) {
        try {
            log.info("Deleting owner with ID: {}", ownerId);
            return ownerRepository.deleteOwnerById(ownerId);
        } catch (Exception e) {
            log.error("Error deleting owner with ID: {}", ownerId, e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean softDeleteOwner(Long ownerId) {
        try {
            log.info("Soft deleting owner with ID: {}", ownerId);
            return ownerRepository.safeDeleteOwnerById(ownerId);
        } catch (Exception e) {
            log.error("Error soft deleting owner with ID: {}", ownerId, e);
            return false;
        }
    }

    @Override
    public Optional<Owner> getOwnerById(Long ownerId) {
        try {
            log.info("Retrieving owner with ID: {}", ownerId);
            return ownerRepository.findOwnerById(ownerId);
        } catch (Exception e) {
            log.error("Error retrieving owner with ID: {}", ownerId, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        try {
            log.info("Retrieving all owners");
            return ownerRepository.findAllOwners();
        } catch (Exception e) {
            log.error("Error retrieving all owners", e);
            return List.of(); 
        }
    }

    // ---------------------------- Property Functionalities ----------------------------

    @Override
    @Transactional
    public Optional<Property> createProperty(Property property) {
        try {
            log.info("Creating new property: {}", property);
            return propertyRepository.saveProperty(property);
        } catch (Exception e) {
            log.error("Error creating property: {}", property, e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Property> updateProperty(Property property) {
        try {
            log.info("Updating property with ID: {}", property.getPropertyId());
            return propertyRepository.updateProperty(property);
        } catch (Exception e) {
            log.error("Error updating property with ID: {}", property.getPropertyId(), e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean deleteProperty(Long propertyId) {
        try {
            log.info("Deleting property with ID: {}", propertyId);
            return propertyRepository.deletePropertyById(propertyId);
        } catch (Exception e) {
            log.error("Error deleting property with ID: {}", propertyId, e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean softDeleteProperty(Long propertyId) {
        try {
            log.info("Soft deleting property with ID: {}", propertyId);
            return propertyRepository.safeDeletePropertyById(propertyId);
        } catch (Exception e) {
            log.error("Error soft deleting property with ID: {}", propertyId, e);
            return false;
        }
    }

    @Override
    public Optional<Property> getPropertyById(Long propertyId) {
        try {
            log.info("Retrieving property with ID: {}", propertyId);
            return propertyRepository.findPropertyById(propertyId);
        } catch (Exception e) {
            log.error("Error retrieving property with ID: {}", propertyId, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Property> getAllProperties() {
        try {
            log.info("Retrieving all properties");
            return propertyRepository.findAllProperties();
        } catch (Exception e) {
            log.error("Error retrieving all properties", e);
            return List.of();
        }
    }

    @Override
    public List<Property> getPropertiesByOwnerId(Long ownerId) {
        try {
            log.info("Retrieving properties by owner ID: {}", ownerId);
            return propertyRepository.findPropertiesByOwnerId(ownerId);
        } catch (Exception e) {
            log.error("Error retrieving properties by owner ID: {}", ownerId, e);
            return List.of();
        }
    }

    // ---------------------------- Repair Functionalities ----------------------------

    @Override
    @Transactional
    public Optional<Repair> createRepair(Repair repair) {
        try {
            log.info("Creating new repair: {}", repair);
            return repairRepository.saveRepair(repair);
        } catch (Exception e) {
            log.error("Error creating repair: {}", repair, e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Repair> updateRepair(Repair repair) {
        try {
            log.info("Updating repair with ID: {}", repair.getRepairId());
            return repairRepository.updateRepair(repair);
        } catch (Exception e) {
            log.error("Error updating repair with ID: {}", repair.getRepairId(), e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean deleteRepair(Long repairId) {
        try {
            log.info("Deleting repair with ID: {}", repairId);
            return repairRepository.deleteRepairById(repairId);
        } catch (Exception e) {
            log.error("Error deleting repair with ID: {}", repairId, e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean softDeleteRepair(Long repairId) {
        try {
            log.info("Soft deleting repair with ID: {}", repairId);
            return repairRepository.safeDeleteRepairById(repairId);
        } catch (Exception e) {
            log.error("Error soft deleting repair with ID: {}", repairId, e);
            return false;
        }
    }

    @Override
    public Optional<Repair> getRepairById(Long repairId) {
        try {
            log.info("Retrieving repair with ID: {}", repairId);
            return repairRepository.findRepairById(repairId);
        } catch (Exception e) {
            log.error("Error retrieving repair with ID: {}", repairId, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Repair> getAllRepairs() {
        try {
            log.info("Retrieving all repairs");
            return repairRepository.findAllRepairs();
        } catch (Exception e) {
            log.error("Error retrieving all repairs", e);
            return List.of();
        }
    }

    @Override
    public List<Repair> getRepairsByPropertyId(Long propertyId) {
        try {
            log.info("Retrieving repairs for property ID: {}", propertyId);
            return repairRepository.findRepairsByPropertyId(propertyId);
        } catch (Exception e) {
            log.error("Error retrieving repairs for property ID: {}", propertyId, e);
            return List.of();
        }
    }
}
