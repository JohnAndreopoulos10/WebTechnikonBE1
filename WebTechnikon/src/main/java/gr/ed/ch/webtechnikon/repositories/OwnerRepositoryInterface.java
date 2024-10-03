package gr.ed.ch.webtechnikon.repositories;

import gr.ed.ch.webtechnikon.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface OwnerRepositoryInterface<T, ID> {
    Optional<T> findOwnerById(ID ownerId);
    Optional<T> findOwnerByVatNumber(Long vatNumber);
    Optional<T> findOwnerByEmail(String email);
    Optional<T> saveOwner(T owner);
    boolean deleteOwnerById(ID ownerId);
    boolean safeDeleteOwnerById(ID ownerId);
    Optional<T> updateOwner(T owner);
    List<T> findAllOwners() throws ResourceNotFoundException;
}
