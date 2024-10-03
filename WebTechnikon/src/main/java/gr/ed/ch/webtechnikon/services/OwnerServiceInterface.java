package gr.ed.ch.webtechnikon.services;

import gr.ed.ch.webtechnikon.models.Owner;
import java.util.List;
import java.util.Optional;

public interface OwnerServiceInterface {
    Optional<Owner> getOwnerById(Long ownerId);
    Optional<Owner> getOwnerByVatNumber(Long vatNumber);
    Optional<Owner> getOwnerByEmail(String email);
    Optional<Owner> createOwner(Owner owner);
    boolean removeOwnerById(Long ownerId);
    boolean safeRemoveOwnerById(Long ownerId);
    Optional<Owner> updateOwner(Owner owner);
    List<Owner> getAllOwners();
}
