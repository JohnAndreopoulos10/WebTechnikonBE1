package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Property;
import gr.ed.ch.webtechnikon.repositories.PropertyRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;



/**
 * This service class is all about managing properties. 
 * The PropertyService provides methods to create, update, delete, 
 * and retrieve property records. It also has soft delete capabilities, 
 * so you can safely remove properties without a permanent erase.
 * 
 * We use transactions to ensure all database changes happen together, 
 * and error handling is included to deal with any hiccups that might arise.
 * Logging is also in place to keep track of what's happening and 
 * to catch any issues that pop up.
 * 
 * @author Johnnie
 */
@Slf4j
public class PropertyService implements PropertyServiceInterface {

    @Inject
    private PropertyRepository propertyRepository;

    @Override
    public Optional<Property> getPropertyById(Long propertyId) {
        try {
            return propertyRepository.findPropertyById(propertyId);
        } catch (Exception e) {
            log.error("Error retrieving property with ID {}: {}", propertyId, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Property> getPropertiesByOwnerId(Long ownerId) {
        try {
            return propertyRepository.findPropertiesByOwnerId(ownerId);
        } catch (Exception e) {
            log.error("Error retrieving properties for owner with ID {}: {}", ownerId, e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Property> getAllProperties() {
        try {
            return propertyRepository.findAllProperties();
        } catch (Exception e) {
            log.error("Error retrieving all properties: {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    @Transactional
    public Optional<Property> createProperty(Property property) {
        try {
            return propertyRepository.saveProperty(property);
        } catch (Exception e) {
            log.error("Error saving property: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean removePropertyById(Long propertyId) {
        try {
            return propertyRepository.deletePropertyById(propertyId);
        } catch (Exception e) {
            log.error("Error deleting property with ID {}: {}", propertyId, e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean safeRemovePropertyById(Long propertyId) {
        try {
            return propertyRepository.safeDeletePropertyById(propertyId);
        } catch (Exception e) {
            log.error("Error soft deleting property with ID {}: {}", propertyId, e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Optional<Property> updateProperty(Property property) {
        try {
            return propertyRepository.updateProperty(property);
        } catch (Exception e) {
            log.error("Error updating property with ID {}: {}", property.getPropertyId(), e.getMessage());
            return Optional.empty();
        }
    }
}
