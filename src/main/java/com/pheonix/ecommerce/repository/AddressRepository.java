package com.pheonix.ecommerce.repository;

import com.pheonix.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Address", path = "address")
public interface AddressRepository extends JpaRepository<Address,Long> {
}
