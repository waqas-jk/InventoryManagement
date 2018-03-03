package com.inventory.management.model;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Waqas
 */

@Entity
public class Brand extends BaseEntityNameAware {

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		return builder.toString();
	}
}
