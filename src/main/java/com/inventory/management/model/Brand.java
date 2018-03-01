package com.inventory.management.model;

import javax.persistence.Entity;

/**
 * @author Waqas
 */

@Entity
public class Brand extends BaseEntityNameAware {

	@Override
	public String toString() {
		return "Brand [name=" + name + ", id=" + id + "]";
	}

}
