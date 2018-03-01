package com.inventory.management.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inventory.management.model.reference.ProductSize;

/**
 * @author Waqas
 */

@Entity(name = "product_size_mapping")
public class ProductSizeMapping extends BaseEntity {

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

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((productSize == null) ? 0 : productSize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSizeMapping other = (ProductSizeMapping) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "ProductSizeMapping [product=" + product + ", productSize=" + productSize + "]";
	}

}
