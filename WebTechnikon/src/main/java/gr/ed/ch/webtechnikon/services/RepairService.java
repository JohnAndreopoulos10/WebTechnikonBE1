package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Repair;
import gr.ed.ch.webtechnikon.repositories.RepairRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


/**
 * This service class focuses on handling repairs. 
 * The RepairService allows you to create, update, delete, 
 * and get details about repairs. It includes soft delete functionality, 
 * letting you safely remove repair records without full deletion.
 * 
 * We ensure transactions are used to keep database operations consistent, 
 * and error handling is there to manage any issues that might come up. 
 * Logging is also part of the mix, helping us track actions and errors along the way.
 * 
 * @author Johnnie
 */
@Slf4j
public class RepairService implements RepairServiceInterface {

    @Inject
    private RepairRepository repairRepository;

    @Override
    public Optional<Repair> getRepairById(Long repairId) {
        return repairRepository.findRepairById(repairId);
    }

    @Override
    public List<Repair> getRepairsByPropertyId(Long propertyId) {
        return repairRepository.findRepairsByPropertyId(propertyId);
    }

    @Override
    public List<Repair> getAllRepairs() {
        return repairRepository.findAllRepairs();
    }

    @Override
    @Transactional
    public Optional<Repair> createRepair(Repair repair) {
        try {
            return repairRepository.saveRepair(repair);
        } catch (Exception e) {
            log.error("Error saving repair: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean removeRepairById(Long repairId) {
        return repairRepository.deleteRepairById(repairId);
    }

    @Override
    @Transactional
    public boolean safeRemoveRepairById(Long repairId) {
        return repairRepository.safeDeleteRepairById(repairId);
    }

    @Override
    @Transactional
    public Optional<Repair> updateRepair(Repair repair) {
        try {
            return repairRepository.updateRepair(repair);
        } catch (Exception e) {
            log.error("Error updating repair: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
