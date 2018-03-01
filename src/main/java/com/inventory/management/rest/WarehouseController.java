package com.inventory.management.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.management.Constants;
import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;
import com.inventory.management.services.OfficeService;
import com.inventory.management.services.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

	private OfficeService officeService;
	private WarehouseService warehouseService;

	@Autowired
	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}

	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<Warehouse> listAllWarehouses() {

		return warehouseService.getAllWarehouses();
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "view/{warehouseId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public Warehouse getWarehouseById(@PathVariable("warehouseId") Long warehouseId) {

		Warehouse warehouse = warehouseService.getWarehouse(warehouseId);

		if (warehouse == null) {
			throw new EntityNotFoundException("Requested warehouse can not be found.");
		}

		return warehouse;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "delete/{warehouseId}", method = RequestMethod.PUT, produces = Constants.CONTENT_TYPE_JSON)
	public Warehouse deleteWarehouse(@PathVariable("warehouseId") Long warehouseId) {

		Warehouse warehouse = warehouseService.getWarehouse(warehouseId);

		if (warehouse == null) {
			throw new EntityNotFoundException("Requested warehouse can not be found.");
		}

		return warehouseService.deleteWarehouse(warehouse);
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "edit/{warehouseId}", method = RequestMethod.PUT, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public Warehouse editWarehouse(@Valid @RequestBody Warehouse warehouse,
			@PathVariable("warehouseId") Long warehouseId) {

		Warehouse exisitngWarehouse = warehouseService.getWarehouse(warehouseId);

		if (exisitngWarehouse != null) {
			exisitngWarehouse.setName(warehouse.getName());
			exisitngWarehouse.setCity(warehouse.getCity());
			exisitngWarehouse.setOffice(officeService.getOffice(warehouse.getOffice().getId()));
			return warehouseService.saveWarehouse(exisitngWarehouse);
		}

		throw new EntityNotFoundException("Requested warehouse can not be found.");
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public Warehouse addNewWarehouse(@Valid @RequestBody Warehouse warehouse) {

		Office office = officeService.getOffice(warehouse.getOffice().getId());

		if (office != null) {
			warehouse.setOffice(office);
			return warehouseService.saveWarehouse(warehouse);
		}

		throw new EntityNotFoundException("Office is not present.");
	}

}
