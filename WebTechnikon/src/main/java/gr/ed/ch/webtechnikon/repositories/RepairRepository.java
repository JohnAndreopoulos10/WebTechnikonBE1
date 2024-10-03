package gr.ed.ch.webtechnikon.repositories;

import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import gr.ed.ch.webtechnikon.models.Repair;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;


/**
 * Repository class responsible for managing Repair entities.
 * Provides CRUD operations and supports both hard and soft deletion.
 * This class interacts with the database using JPA and ensures that 
 * deleted owners are flagged rather than removed permanently.
 * @author Johnnie
 */
@Slf4j
public class RepairRepository implements RepairRepositoryInterface<Repair, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Repair> findRepairById(Long repairId) {
        return Optional.ofNullable(entityManager.find(Repair.class, repairId));
    }

    @Override
    public List<Repair> findRepairsByPropertyId(Long propertyId) {
        TypedQuery<Repair> query = entityManager.createQuery(
                "SELECT r FROM Repair r WHERE r.property.propertyId = :propertyId AND r.deletedRepair = false", Repair.class);
        query.setParameter("propertyId", propertyId);
        return query.getResultList();
    }

    @Override
    public List<Repair> findAllRepairs() {
        TypedQuery<Repair> query = entityManager.createQuery(
                "SELECT r FROM Repair r WHERE r.deletedRepair = false", Repair.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Optional<Repair> saveRepair(Repair repair) {
        entityManager.persist(repair);
        return Optional.of(repair);
    }

    @Override
    @Transactional
    public boolean deleteRepairById(Long repairId) {
        Repair repair = entityManager.find(Repair.class, repairId);
        if (repair != null) {
            entityManager.remove(repair);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean safeDeleteRepairById(Long repairId) {
        Repair repair = entityManager.find(Repair.class, repairId);
        if (repair != null) {
            repair.setDeletedRepair(true);
            entityManager.merge(repair);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Optional<Repair> updateRepair(Repair repair) {
        return Optional.ofNullable(entityManager.merge(repair));
    }
}
