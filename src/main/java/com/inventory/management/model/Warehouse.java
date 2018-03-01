package com.inventory.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Waqas0
 */

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Warehouse extends BaseEntityNameAware {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "office_id", nullable = false)
	private Office office;

	@Column(name = "is_deleted", nullable = false)
	@Type(type = "true_false")
	private Boolean isDeleted = Boolean.FALSE;

	@Column(name = "city", nullable = false)
	@NotNull(message = "Please provide city name.")
	@NotBlank(message = "Please provide city name.")
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isAvailable) {
		this.isDeleted = isAvailable;
	}

	@Override
	public String toString() {
		return "Warehouse [city=" + city + ", office=" + office + ", isDeleted=" + isDeleted + ", name=" + name
				+ ", id=" + id + "]";
	}

}
