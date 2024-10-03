package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Repair;
import java.util.List;
import java.util.Optional;

public interface RepairServiceInterface {
    Optional<Repair> getRepairById(Long repairId);
    List<Repair> getRepairsByPropertyId(Long propertyId);
    List<Repair> getAllRepairs();
    Optional<Repair> createRepair(Repair repair);
    boolean removeRepairById(Long repairId);
    boolean safeRemoveRepairById(Long repairId);
    Optional<Repair> updateRepair(Repair repair);
}
