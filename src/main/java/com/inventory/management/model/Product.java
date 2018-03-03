package com.inventory.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

import com.inventory.management.model.reference.ProductType;

/**
 * @author Waqas
 */

@Entity
public class Product extends BaseEntityNameAware {

	@Column(name = "description", nullable = true)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_type_id", nullable = false)
	private ProductType productType;

	@Column(name = "is_deleted", nullable = false)
	@Type(type = "true_false")
	private Boolean isDeleted = Boolean.FALSE;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isAvailable) {
		this.isDeleted = isAvailable;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString()).append("description", description).append("brand", brand).append("productType", productType)
				.append("isDeleted", isDeleted);
		return builder.toString();
	}

}
