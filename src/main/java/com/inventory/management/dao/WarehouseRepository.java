package com.inventory.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	@Query("from Warehouse where isDeleted = :deleted")
	public List<Warehouse> findAllWarehouses(@Param("deleted") Boolean isDeleted);

	@Query("from Warehouse where office.id = :officeId and isDeleted = :deleted")
	public List<Warehouse> findAllWarehousesByOffice(@Param("officeId") Long officeId,
			@Param("deleted") Boolean isDeleted);

	@Query("from Warehouse where office.country.id = :countryId and isDeleted = :deleted")
	public List<Warehouse> findAllWarehousesByCountryId(@Param("countryId") Long countryId,
			@Param("deleted") Boolean isDeleted);

	@Query("from Warehouse where lower(office.country.name) = lower(:name) and isDeleted = :deleted")
	public List<Warehouse> findAllWarehousesByCountryName(@Param("name") String countryName,
			@Param("deleted") Boolean isDeleted);
}
