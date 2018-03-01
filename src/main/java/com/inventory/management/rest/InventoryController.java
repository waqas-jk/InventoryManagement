package com.inventory.management.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.management.Constants;
import com.inventory.management.exception.BadRequestBodyException;
import com.inventory.management.model.Inventory;
import com.inventory.management.model.InventoryRestDto;
import com.inventory.management.model.Office;
import com.inventory.management.model.Product;
import com.inventory.management.model.Warehouse;
import com.inventory.management.services.InventoryService;
import com.inventory.management.services.OfficeService;
import com.inventory.management.services.ProductService;
import com.inventory.management.services.WarehouseService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private ProductService productService;
	private InventoryService inventoryService;
	private InventoryBeanConverter convertor;
	private WarehouseService warehouseService;
	private OfficeService officeService;

	@Autowired
	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Autowired
	public void setInventoryBeanConverter(InventoryBeanConverter convertor) {
		this.convertor = convertor;
	}

	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@Autowired
	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<InventoryRestDto> listAllInventories(@PathVariable("productId") Long productId) {

		if (productService.getProduct(productId) == null) {
			throw new EntityNotFoundException("Requested product can not be found.");
		}

		List<Inventory> inventories = new ArrayList<>();

		warehouseService.getAllWarehouses().forEach(warehouse -> {
			inventories.addAll(inventoryService.getInventory(productId, warehouse.getId()));
		});

		return convertInventories(inventoryService.getAllInventories());
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/warehouse/{warehouseId}/product/{productId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<InventoryRestDto> getInventoryByWarehouse(@PathVariable("warehouseId") Long warehouseId,
			@PathVariable("productId") Long productId) {

		if (warehouseService.getWarehouse(warehouseId) == null || productService.getProduct(productId) == null) {
			throw new EntityNotFoundException("Requested product or warehouse can not be found.");
		}

		return convertInventories(inventoryService.getInventory(productId, warehouseId));
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/office/{officeId}/product/{productId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<InventoryRestDto> getInventoryByOffice(@PathVariable("officeId") Long officeId,
			@PathVariable("productId") Long productId) {

		if (officeService.getOffice(officeId) == null || productService.getProduct(productId) == null) {
			throw new EntityNotFoundException("Requested product or office can not be found.");
		}

		List<Inventory> inventories = new ArrayList<>();
		List<Warehouse> warehouses = warehouseService.getAllWarehouses();
		if (warehouses != null) {
			warehouses.forEach(warehouse -> {
				inventories.addAll(inventoryService.getInventory(productId, warehouse.getId()));
			});
		}

		return convertInventories(inventoryService.getAllInventories());
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "set/warehouse/{warehouseId}", method = RequestMethod.PUT, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public List<InventoryRestDto> setWarehouseInventory(@RequestBody InventoryRestDto inventoryDto,
			@PathVariable("warehouseId") Long warehouseId) {

		Warehouse warehouse = warehouseService.getWarehouse(warehouseId);

		if (warehouse == null) {
			throw new EntityNotFoundException("Requested warehouse can not be found.");
		}

		Inventory paramInventory = convertor.convert(inventoryDto);
		Product product = paramInventory.getKey().getProduct();
		if (inventoryDto == null || product == null) {
			// Bad request ... no body found missing request information
		}

		return convertInventories(
				inventoryService.updateProductInventoryInWarehouse(paramInventory, product, warehouse));
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "set/office/{officeId}", method = RequestMethod.PUT, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public List<InventoryRestDto> setOfficeInventory(@RequestBody InventoryRestDto inventoryDto,
			@PathVariable("officeId") Long officeId) {

		Office office = officeService.getOffice(officeId);

		if (office == null) {
			throw new EntityNotFoundException("Requested office can not be found.");
		}

		Inventory paramInventory = convertor.convert(inventoryDto);
		Product product = paramInventory.getKey().getProduct();
		if (inventoryDto == null || product == null) {
			// Bad request ... no body found missing request information
		}

		return convertInventories(inventoryService.updateProductInventoryInOffice(paramInventory, product, office));
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 * @throws BadRequestBodyException
	 */
	@RequestMapping(value = "set/all", method = RequestMethod.PUT, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public List<InventoryRestDto> setCompanyInventory(@RequestBody InventoryRestDto inventoryDto)
			throws BadRequestBodyException {

		Inventory paramInventory = convertor.convert(inventoryDto);
		Product product = paramInventory.getKey().getProduct();
		if (inventoryDto == null || product == null) {
			throw new BadRequestBodyException("Request body does not contain correct information.");
		}

		return convertInventories(inventoryService.updateProductInventoryInCompany(paramInventory, product));
	}

	/**
	 * 
	 * @param inventories
	 * @return
	 */
	private List<InventoryRestDto> convertInventories(List<Inventory> inventories) {
		if (inventories == null) {
			return new ArrayList<>();
		}

		return inventories.stream().map(item -> convertor.convert(item)).collect(Collectors.toList());
	}
}
