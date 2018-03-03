package com.inventory.management.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventory.management.Constants;
import com.inventory.management.model.reference.ProductSize;

/**
 * @author Waqas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryRestDto {

	private Warehouse warehouse;
	private Product product;
	private ProductSize productSize;

	private Long qpb = Constants.DEFAULT_QUANTITY;
	private Long moq = Constants.DEFAULT_QUANTITY;
	private Long reorderPoint = Constants.DEFAULT_QUANTITY;

	private Long inStockQuantity = Constants.DEFAULT_QUANTITY;
	private Long inTransitQuantity = Constants.DEFAULT_QUANTITY;
	private Long availableQuantity = Constants.DEFAULT_QUANTITY;

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public Long getQpb() {
		return qpb;
	}

	public void setQpb(Long qpb) {
		this.qpb = qpb;
	}

	public Long getMoq() {
		return moq;
	}

	public void setMoq(Long moq) {
		this.moq = moq;
	}

	public Long getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(Long reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public Long getInStockQuantity() {
		return inStockQuantity;
	}

	public void setInStockQuantity(Long inStockQuantity) {
		this.inStockQuantity = inStockQuantity;
	}

	public Long getInTransitQuantity() {
		return inTransitQuantity;
	}

	public void setInTransitQuantity(Long inTransitQuantity) {
		this.inTransitQuantity = inTransitQuantity;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("warehouse", warehouse).append("product", product).append("productSize", productSize)
				.append("qpb", qpb).append("moq", moq).append("reorderPoint", reorderPoint)
				.append("inStockQuantity", inStockQuantity).append("inTransitQuantity", inTransitQuantity)
				.append("availableQuantity", availableQuantity);
		return builder.toString();
	}

}
