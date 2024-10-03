package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyServiceInterface {
    Optional<Property> getPropertyById(Long propertyId);
    List<Property> getPropertiesByOwnerId(Long ownerId);
    List<Property> getAllProperties();
    Optional<Property> createProperty(Property property);
    boolean removePropertyById(Long propertyId);
    boolean safeRemovePropertyById(Long propertyId);
    Optional<Property> updateProperty(Property property);
}
