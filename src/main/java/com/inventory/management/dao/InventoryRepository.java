package com.inventory.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.management.model.Inventory;
import com.inventory.management.model.InventoryKey;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryKey> {

	@Query("from Inventory where key.warehouse.id = :wId and key.product.isDeleted is false and key.warehouse.isDeleted is false")

	public List<Inventory> findAllInventoriesByWarehouseId(@Param("wId") Long warehouseId);

	@Query("from Inventory where key.product.id = :pId and key.product.isDeleted is false and key.warehouse.isDeleted is false")
	public List<Inventory> findAllInventoriesByProduct(@Param("pId") Long productId);

	@Query("from Inventory where key.product.id = :pId and key.warehouse.id = :wId and key.product.isDeleted is false and key.warehouse.isDeleted is false")
	public List<Inventory> findInventories(@Param("pId") Long productId, @Param("wId") Long warehouseId);

	@Query("from Inventory where key.warehouse.office.id = :oId and key.product.isDeleted is false and key.warehouse.isDeleted is false")
	public List<Inventory> findAllInventoriesByOffice(@Param("oId") Long officeId);

}
