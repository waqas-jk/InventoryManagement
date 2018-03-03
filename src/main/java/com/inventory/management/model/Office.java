package com.inventory.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inventory.management.model.reference.Country;

/**
 * @author Waqas
 */

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Office extends BaseEntityNameAware {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", nullable = false)
	@JsonProperty
	@NotNull(message = "Please provide office country.")
	private Country country;

	@Column(name = "city", nullable = false)
	@JsonProperty
	@NotNull(message = "Please provide city name.")
	@NotBlank(message = "Please provide city name.")
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString()).append("country", country).append("city", city);
		return builder.toString();
	}

}
