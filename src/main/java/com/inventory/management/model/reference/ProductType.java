package com.inventory.management.model.reference;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.inventory.management.model.BaseEntityNameAware;

/**
 * @author Waqas
 */

@Entity(name = "product_type")
public class ProductType extends BaseEntityNameAware {

	public static final Long FINISHED_PRODUCT_ID = 1L;
	public static final Long COMPONENT_ID = 2L;
	public static final Long PACKAGE_MATERIAL_ID = 3L;

	@Column(name = "is_internal")
	@Type(type = "true_false")
	private Boolean isInternal;

	public Boolean getIsInternal() {
		return isInternal;
	}

	public void setIsInternal(Boolean isInternal) {
		this.isInternal = isInternal;
	}

	@Override
	public String toString() {
		return "ProductType [isInternal=" + isInternal + ", name=" + name + ", id=" + id + "]";
	}

}
