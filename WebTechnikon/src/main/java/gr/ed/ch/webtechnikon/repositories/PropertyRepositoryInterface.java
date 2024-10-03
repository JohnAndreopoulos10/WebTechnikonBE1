package gr.ed.ch.webtechnikon.repositories;

import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface PropertyRepositoryInterface<T, ID> {
    Optional<T> findPropertyById(ID propertyId);
    List<T> findPropertiesByOwnerId(Long ownerId);
    List<T> findAllProperties();
    Optional<T> saveProperty(T property);
    boolean deletePropertyById(ID propertyId);
    boolean safeDeletePropertyById(ID propertyId);
    Optional<T> updateProperty(T property);
}
