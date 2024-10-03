package gr.ed.ch.webtechnikon.repositories;

import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import gr.ed.ch.webtechnikon.models.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;


/**
 * Repository class responsible for managing Property entities.
 * Provides CRUD operations, including both hard and soft deletion.
 * Interacts with the database using JPA and ensures that 
 * deleted properties are flagged instead of being permanently removed.
 * @author Johnnie
 */
@Slf4j
public class PropertyRepository implements PropertyRepositoryInterface<Property, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Property> findPropertyById(Long propertyId) {
        return Optional.ofNullable(entityManager.find(Property.class, propertyId));
    }

    @Override
    public List<Property> findPropertiesByOwnerId(Long ownerId) {
        TypedQuery<Property> query = entityManager.createQuery(
                "SELECT p FROM Property p WHERE p.owner.ownerId = :ownerId AND p.deletedProperty = false", Property.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }

    @Override
    public List<Property> findAllProperties() {
        TypedQuery<Property> query = entityManager.createQuery(
                "SELECT p FROM Property p WHERE p.deletedProperty = false", Property.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Optional<Property> saveProperty(Property property) {
        entityManager.persist(property);
        return Optional.of(property);
    }

    @Override
    @Transactional
    public boolean deletePropertyById(Long propertyId) {
        Property property = entityManager.find(Property.class, propertyId);
        if (property != null) {
            entityManager.remove(property);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean safeDeletePropertyById(Long propertyId) {
        Property property = entityManager.find(Property.class, propertyId);
        if (property != null) {
            property.setDeletedProperty(true);
            entityManager.merge(property);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Optional<Property> updateProperty(Property property) {
        return Optional.ofNullable(entityManager.merge(property));
    }
}
