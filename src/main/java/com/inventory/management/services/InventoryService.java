package com.inventory.management.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.management.dao.InventoryRepository;
import com.inventory.management.dao.OfficeRepository;
import com.inventory.management.dao.ProductSizeMappingRepository;
import com.inventory.management.dao.WarehouseRepository;
import com.inventory.management.model.Inventory;
import com.inventory.management.model.InventoryKey;
import com.inventory.management.model.Office;
import com.inventory.management.model.Product;
import com.inventory.management.model.Warehouse;

/**
 * @author Waqas
 */

@Service
public class InventoryService {

	private OfficeRepository officeRepository;
	private WarehouseRepository warehouseRepository;
	private InventoryRepository inventoryRepository;
	private ProductSizeMappingRepository mappingRepository;

	@Autowired
	public void setInventoryRepository(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Autowired
	public void setProdctSizeMappingRepository(ProductSizeMappingRepository mappingRepository) {
		this.mappingRepository = mappingRepository;
	}

	@Autowired
	public void setOfficeRepository(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}

	@Autowired
	public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
		this.warehouseRepository = warehouseRepository;
	}

	/**
	 * Get information about all products in a warehouse
	 * 
	 * @return
	 */
	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	/**
	 * Get information about all products in a warehouse
	 * 
	 * @return
	 */
	public List<Inventory> getAllInventoriesByWarehouse(Long warehouseId) {
		return inventoryRepository.findAllInventoriesByWarehouseId(warehouseId);
	}

	/**
	 * Get information of product in all warehouses
	 * 
	 * @return
	 */
	public List<Inventory> getAllInventoriesByOffice(Long officeId) {
		return inventoryRepository.findAllInventoriesByOffice(officeId);
	}

	/**
	 * Get information about a product in single warehouse
	 * 
	 * @return
	 */
	public List<Inventory> getInventory(Long productId, Long warehouseId) {
		return inventoryRepository.findInventories(productId, warehouseId);
	}

	/**
	 * Get information about a product in single warehouse
	 * 
	 * @return
	 */
	public Inventory getInventory(InventoryKey inventoryKey) {
		return inventoryRepository.findOne(inventoryKey);
	}

	/**
	 * Add or updated product inventory in warehouse.
	 * 
	 * @param inventory
	 * @return
	 */
	public Inventory saveInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	/**
	 * 
	 * @param paramInventory
	 * @param product
	 * @param office
	 * @return
	 */
	public List<Inventory> updateProductInventoryInOffice(Inventory paramInventory, Product product, Office office) {
		List<Inventory> inventories = new ArrayList<>();

		if (office == null || product == null) {
			return inventories;
		}

		List<Warehouse> warehouses = warehouseRepository.findAllWarehousesByOffice(office.getId(), Boolean.FALSE);
		if (warehouses == null) {
			return inventories;
		}

		warehouses.forEach(wh -> {
			inventories.addAll(updateProductInventoryInWarehouse(paramInventory, product, wh));
		});

		return inventories;
	}

	/**
	 * 
	 * @param paramInventory
	 * @param product
	 * @return
	 */
	public List<Inventory> updateProductInventoryInCompany(Inventory paramInventory, Product product) {
		List<Inventory> inventories = new ArrayList<>();

		if (product == null) {
			return inventories;
		}

		officeRepository.findAll().forEach(office -> {
			inventories.addAll(updateProductInventoryInOffice(paramInventory, product, office));
		});

		return inventories;
	}

	/**
	 * Set product quantity in warehouse.
	 * 
	 * @param paramInventory
	 * @param product
	 * @param warehouse
	 * @return
	 */
	public List<Inventory> updateProductInventoryInWarehouse(Inventory paramInventory, Product product,
			Warehouse warehouse) {

		List<Inventory> inventories = new ArrayList<>();

		if (warehouse == null || product == null) {
			return inventories;
		}

		// If Product Size is not present then update all sizes of product
		if (paramInventory.getKey() == null || paramInventory.getKey().getSize() == null) {
			mappingRepository.findSizesByProduct(product.getId()).forEach(mapping -> {

				InventoryKey inventoryKey = new InventoryKey();
				inventoryKey.setWarehouse(warehouse);
				inventoryKey.setProduct(mapping.getProduct());
				inventoryKey.setSize(mapping.getProductSize());

				Inventory inventory = new Inventory();
				inventory.setKey(inventoryKey);

				populateInventoryValues(paramInventory, inventory);
				inventories.add(inventoryRepository.save(inventory));
			});

			// If Size is present then only update a specific size of product
		} else {
			InventoryKey inventoryKey = new InventoryKey();
			inventoryKey.setWarehouse(warehouse);
			inventoryKey.setProduct(product);
			inventoryKey.setSize(paramInventory.getKey().getSize());

			Inventory inventory = new Inventory();
			inventory.setKey(inventoryKey);
			populateInventoryValues(paramInventory, inventory);
			inventories.add(inventoryRepository.save(inventory));
		}

		return inventories;
	}

	/**
	 * Copy quantities in Inventory beans
	 * 
	 * @param srcInventory
	 * @param targetInventory
	 */
	private void populateInventoryValues(Inventory srcInventory, Inventory targetInventory) {
		targetInventory.setMoq(srcInventory.getMoq());
		targetInventory.setQpb(srcInventory.getQpb());
		targetInventory.setReorderPoint(srcInventory.getReorderPoint());
		targetInventory.setInStockQuantity(srcInventory.getInStockQuantity());
		targetInventory.setAvailableQuantity(srcInventory.getAvailableQuantity());
		targetInventory.setInTransitQuantity(srcInventory.getInTransitQuantity());
	}
}
