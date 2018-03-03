package com.inventory.management.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
		return new HashCodeBuilder(17, 37).append(product).append(productSize).append(warehouse).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		InventoryKey rhs = (InventoryKey) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.product, rhs.product)
				.append(this.productSize, rhs.productSize).append(this.warehouse, rhs.warehouse)
				.isEquals();
	}


	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("warehouse", warehouse).append("product", product).append("productSize", productSize);
		return builder.toString();
	}

	
}
