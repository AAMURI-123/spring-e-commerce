package com.pheonix.ecommerce.repository;

import com.pheonix.ecommerce.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface StateRepositotry extends JpaRepository<State,Long> {
    Page<State> findByCountryId(@Param("id") Long id, Pageable pageable);
    Page<State> findByCountryName(@Param("name") String name, Pageable pageable);
}
