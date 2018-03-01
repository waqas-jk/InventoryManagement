package com.inventory.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.management.dao.WarehouseRepository;
import com.inventory.management.model.Warehouse;

/**
 * @author Waqas
 */

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;

	public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
		this.warehouseRepository = warehouseRepository;
	}

	/**
	 * Get all warehouses using office id
	 * 
	 * @return
	 */
	public List<Warehouse> getAllWarehouses() {
		return warehouseRepository.findAllWarehouses(Boolean.FALSE);
	}

	/**
	 * Get all warehouses using office id
	 * 
	 * @return
	 */
	public List<Warehouse> getAllWarehousesByOffice(Long officeId) {
		return warehouseRepository.findAllWarehousesByOffice(officeId, Boolean.FALSE);
	}

	/**
	 * Get all warehouses by country id
	 * 
	 * @return
	 */
	public List<Warehouse> getAllWarehousesByCountryId(Long countryId) {
		return warehouseRepository.findAllWarehousesByCountryId(countryId, Boolean.FALSE);
	}

	/**
	 * Get all warehouses by country name
	 * 
	 * @return
	 */
	public List<Warehouse> getAllWarehousesByCountryName(String countryName) {
		return warehouseRepository.findAllWarehousesByCountryName(countryName, Boolean.FALSE);
	}

	/**
	 * Get warehouse information by Id
	 * 
	 * @return
	 */
	public Warehouse getWarehouse(Long warehouseId) {
		return warehouseRepository.findOne(warehouseId);
	}

	/**
	 * Save warehouse information or add new warehouse
	 * 
	 * @return
	 */
	public Warehouse saveWarehouse(Warehouse wh) {
		return warehouseRepository.save(wh);
	}

	/**
	 * Mark warehouse as deleted.
	 * 
	 * @return
	 */
	public Warehouse deleteWarehouse(Warehouse wh) {
		wh.setIsDeleted(Boolean.TRUE);
		return saveWarehouse(wh);
	}

}
