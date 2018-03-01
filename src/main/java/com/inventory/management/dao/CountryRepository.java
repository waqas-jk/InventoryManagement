package com.inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.reference.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
