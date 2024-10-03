package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import gr.ed.ch.webtechnikon.models.Owner;
import gr.ed.ch.webtechnikon.repositories.OwnerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


/**
 * This service class takes care of everything related to owners. 
 * The OwnerService lets you fetch, create, update, and delete owner records.
 * It even supports soft deletes, so you can remove owners without permanently 
 * deleting their info.
 * 
 * Transactions are in play to ensure that all database operations go smoothly 
 * together, and we've got error handling to catch any bumps along the way.
 * Plus, all actions and errors are logged for easy tracking.
 * 
 * @author Johnnie
 */
@Slf4j
public class OwnerService implements OwnerServiceInterface {

    @Inject
    private OwnerRepository ownerRepository;

    @Override
    public Optional<Owner> getOwnerById(Long ownerId) {
        try {
            return ownerRepository.findOwnerById(ownerId);
        } catch (ResourceNotFoundException e) {
            log.error("Owner with ID {} not found: {}", ownerId, e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error retrieving owner with ID {}: {}", ownerId, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Owner> getOwnerByVatNumber(Long vatNumber) {
        try {
            return ownerRepository.findOwnerByVatNumber(vatNumber);
        } catch (ResourceNotFoundException e) {
            log.error("Owner with VAT number {} not found: {}", vatNumber, e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error retrieving owner with VAT number {}: {}", vatNumber, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Owner> getOwnerByEmail(String email) {
        try {
            return ownerRepository.findOwnerByEmail(email);
        } catch (ResourceNotFoundException e) {
            log.error("Owner with email {} not found: {}", email, e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error retrieving owner with email {}: {}", email, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Owner> createOwner(Owner owner) {
        try {
            return ownerRepository.saveOwner(owner);
        } catch (Exception e) {
            log.error("Error saving owner: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean removeOwnerById(Long ownerId) {
        try {
            return ownerRepository.deleteOwnerById(ownerId);
        } catch (ResourceNotFoundException e) {
            log.error("Owner with ID {} not found: {}", ownerId, e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Error deleting owner with ID {}: {}", ownerId, e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean safeRemoveOwnerById(Long ownerId) {
        try {
            return ownerRepository.safeDeleteOwnerById(ownerId);
        } catch (ResourceNotFoundException e) {
            log.error("Owner with ID {} not found for soft delete: {}", ownerId, e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Error soft deleting owner with ID {}: {}", ownerId, e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Optional<Owner> updateOwner(Owner owner) {
        try {
            return ownerRepository.updateOwner(owner);
        } catch (ResourceNotFoundException e) {
            log.error("Owner with ID {} not found for update: {}", owner.getOwnerId(), e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error updating owner with ID {}: {}", owner.getOwnerId(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        try {
            return ownerRepository.findAllOwners();
        } catch (ResourceNotFoundException e) {
            log.error("No owners found: {}", e.getMessage());
            return List.of();
        } catch (Exception e) {
            log.error("Error retrieving all owners: {}", e.getMessage());
            return List.of();
        }
    }
}
