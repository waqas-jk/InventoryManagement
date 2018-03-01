package com.inventory.management.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Waqas
 */
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntityNameAware extends BaseEntity {

	@Column(name = "name", nullable = false)
	@JsonProperty
	@NotNull(message = "Please provide name.")
	@NotBlank(message = "Please provide name.")
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [name=" + name + ", id=" + id + "]";
	}

}
