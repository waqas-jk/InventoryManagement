package com.inventory.management.model;

import javax.persistence.Entity;
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
		return new HashCodeBuilder(17, 37).append(product).append(productSize).toHashCode();
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
		ProductSizeMapping rhs = (ProductSizeMapping) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.product, rhs.product)
				.append(this.productSize, rhs.productSize).isEquals();
	}


	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString()).append("product", product).append("productSize", productSize);
		return builder.toString();
	}


}
