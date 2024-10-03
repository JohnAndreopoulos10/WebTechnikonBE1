 package gr.ed.ch.webtechnikon.repositories;

import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface RepairRepositoryInterface<T, ID> {
    Optional<T> findRepairById(ID repairId);
    List<T> findRepairsByPropertyId(Long propertyId);
    List<T> findAllRepairs();
    Optional<T> saveRepair(T repair);
    boolean deleteRepairById(ID repairId);
    boolean safeDeleteRepairById(ID repairId);
    Optional<T> updateRepair(T repair);
}
