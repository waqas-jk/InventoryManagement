package com.inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

	@Query("from Office where country.id = :id")
	public Office findOfficeByCountryId(@Param("id") Long countryId);

	@Query("from Office where lower(country.name) like lower(:name)")
	public Office findOfficeByCountryName(@Param("name") String countryName);

}
