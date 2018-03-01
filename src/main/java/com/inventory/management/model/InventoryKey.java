package com.inventory.management.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inventory.management.model.reference.ProductSize;

/**
 * @author Waqas
 */

@Embeddable
public class InventoryKey implements Serializable {

	private static final long serialVersionUID = -4270915287894826860L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id", nullable = false)
	private Warehouse warehouse;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_size_id", nullable = false)
	private ProductSize productSize;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductSize getSize() {
		return productSize;
	}

	public void setSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((productSize == null) ? 0 : productSize.hashCode());
		result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryKey other = (InventoryKey) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productSize == null) {
			if (other.productSize != null)
				return false;
		} else if (!productSize.equals(other.productSize))
			return false;
		if (warehouse == null) {
			if (other.warehouse != null)
				return false;
		} else if (!warehouse.equals(other.warehouse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InventoryKey [warehouse=" + warehouse + ", product=" + product + ", productSize=" + productSize + "]";
	}

}
