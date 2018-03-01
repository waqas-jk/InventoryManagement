package com.inventory.management.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventory.management.model.Inventory;
import com.inventory.management.model.InventoryKey;
import com.inventory.management.model.InventoryRestDto;
import com.inventory.management.services.ProductService;
import com.inventory.management.services.ReferenceEntityService;
import com.inventory.management.services.WarehouseService;

/**
 * @author Waqas
 */

@Component
public class InventoryBeanConverter {

	private ProductService productService;
	private ReferenceEntityService refSerivce;
	private WarehouseService warehouseService;

	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@Autowired
	public void setReferenceEntityService(ReferenceEntityService refSerivce) {
		this.refSerivce = refSerivce;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public InventoryRestDto convert(Inventory inventory) {
		InventoryRestDto dto = new InventoryRestDto();

		if (inventory == null) {
			return dto;
		}

		dto.setProduct(inventory.getKey().getProduct());
		dto.setProductSize(inventory.getKey().getSize());
		dto.setWarehouse(inventory.getKey().getWarehouse());
		dto.setMoq(inventory.getMoq());
		dto.setQpb(inventory.getQpb());
		dto.setReorderPoint(inventory.getReorderPoint());
		dto.setInStockQuantity(inventory.getInStockQuantity());
		dto.setInTransitQuantity(inventory.getInTransitQuantity());
		dto.setAvailableQuantity(inventory.getAvailableQuantity());

		return dto;
	}

	public Inventory convert(InventoryRestDto inventoryDto) {

		InventoryKey inventoryKey = new InventoryKey();
		Inventory inventory = new Inventory();
		inventory.setKey(inventoryKey);

		if (inventoryDto == null) {
			return inventory;
		}

		if (inventoryDto.getProduct() != null && inventoryDto.getProduct().getId() != null) {
			inventoryKey.setProduct(productService.getProduct(inventoryDto.getProduct().getId()));
		}

		if (inventoryDto.getProductSize() != null && inventoryDto.getProductSize().getId() != null) {
			inventoryKey.setSize(refSerivce.getProductSize(inventoryDto.getProductSize().getId()));
		}

		if (inventoryDto.getWarehouse() != null && inventoryDto.getWarehouse().getId() != null) {
			inventoryKey.setWarehouse(warehouseService.getWarehouse(inventoryDto.getWarehouse().getId()));
		}

		if (inventoryDto.getMoq() != null) {
			inventory.setMoq(inventoryDto.getMoq());
		}

		if (inventoryDto.getQpb() != null) {
			inventory.setQpb(inventoryDto.getQpb());
		}

		if (inventoryDto.getReorderPoint() != null) {
			inventory.setReorderPoint(inventoryDto.getReorderPoint());
		}

		if (inventoryDto.getInStockQuantity() != null) {
			inventory.setInStockQuantity(inventoryDto.getInStockQuantity());
		}

		if (inventoryDto.getInTransitQuantity() != null) {
			inventory.setInTransitQuantity(inventoryDto.getInTransitQuantity());
		}

		if (inventoryDto.getAvailableQuantity() != null) {
			inventory.setAvailableQuantity(inventoryDto.getAvailableQuantity());
		}

		return inventory;
	}
}
