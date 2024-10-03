package gr.ed.ch.webtechnikon.repositories;

import gr.ed.ch.webtechnikon.exceptions.OwnerNotFoundException;
import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import gr.ed.ch.webtechnikon.models.Owner;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;


/**
 * Repository class responsible for managing Owner entities.
 * Provides CRUD operations, including both hard and soft deletion.
 * Interacts with the database using JPA and ensures that 
 * deleted properties are flagged instead of being permanently removed.
 * @author Johnnie
 */
@Slf4j
public class OwnerRepository implements OwnerRepositoryInterface<Owner, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Owner> findOwnerById(Long ownerId) {
        return Optional.ofNullable(entityManager.find(Owner.class, ownerId));
    }

    @Override
    public Optional<Owner> findOwnerByVatNumber(Long vatNumber) {
        TypedQuery<Owner> query = entityManager.createQuery(
                "SELECT o FROM Owner o WHERE o.vatNumber = :vatNumber AND o.deletedOwner = false", Owner.class);
        query.setParameter("vatNumber", vatNumber);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Owner> findOwnerByEmail(String email) {
        TypedQuery<Owner> query = entityManager.createQuery(
                "SELECT o FROM Owner o WHERE o.email = :email AND o.deletedOwner = false", Owner.class);
        query.setParameter("email", email);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    @Transactional
    public Optional<Owner> saveOwner(Owner owner) {
        entityManager.persist(owner);
        return Optional.of(owner);
    }

    @Override
    @Transactional
    public boolean deleteOwnerById(Long ownerId) {
        Owner owner = entityManager.find(Owner.class, ownerId);
        if (owner != null) {
            entityManager.remove(owner);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean safeDeleteOwnerById(Long ownerId) {
        Owner owner = entityManager.find(Owner.class, ownerId);
        if (owner != null) {
            owner.setDeletedOwner(true);
            entityManager.merge(owner);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Optional<Owner> updateOwner(Owner owner) {
        return Optional.ofNullable(entityManager.merge(owner));
    }

    @Override
    public List<Owner> findAllOwners() throws ResourceNotFoundException {
        TypedQuery<Owner> query = entityManager.createQuery(
                "SELECT o FROM Owner o WHERE o.deletedOwner = false", Owner.class);
        return query.getResultList();
    }
}
