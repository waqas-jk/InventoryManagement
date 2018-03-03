package com.inventory.management.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.inventory.management.Constants;

/**
 * @author Waqas
 */

@Entity
public class Inventory {

	@EmbeddedId
	private InventoryKey key;

	@Column(name = "qpb", nullable = false)
	private Long qpb = Constants.DEFAULT_QUANTITY;

	@Column(name = "moq", nullable = false)
	private Long moq = Constants.DEFAULT_QUANTITY;

	@Column(name = "reorderPoint", nullable = false)
	private Long reorderPoint = Constants.DEFAULT_QUANTITY;

	@Column(name = "in_stock_quantity", nullable = false)
	private Long inStockQuantity = Constants.DEFAULT_QUANTITY;

	@Column(name = "in_transit_quantity", nullable = false)
	private Long inTransitQuantity = Constants.DEFAULT_QUANTITY;

	@Column(name = "available_quantity", nullable = false)
	private Long availableQuantity = Constants.DEFAULT_QUANTITY;

	public InventoryKey getKey() {
		return key;
	}

	public void setKey(InventoryKey key) {
		this.key = key;
	}

	public Long getQpb() {
		return qpb;
	}

	public void setQpb(Long qpb) {
		this.qpb = qpb;
	}

	public Long getMoq() {
		return moq;
	}

	public void setMoq(Long moq) {
		this.moq = moq;
	}

	public Long getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(Long reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public Long getInStockQuantity() {
		return inStockQuantity;
	}

	public void setInStockQuantity(Long inStockQuantity) {
		this.inStockQuantity = inStockQuantity;
	}

	public Long getInTransitQuantity() {
		return inTransitQuantity;
	}

	public void setInTransitQuantity(Long inTransitQuantity) {
		this.inTransitQuantity = inTransitQuantity;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(key).toHashCode();
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
		Inventory rhs = (Inventory) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.key, rhs.key).isEquals();
	}

	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("key", key).append("qpb", qpb).append("moq", moq).append("reorderPoint", reorderPoint)
				.append("inStockQuantity", inStockQuantity).append("inTransitQuantity", inTransitQuantity)
				.append("availableQuantity", availableQuantity);
		return builder.toString();
	}

	
}
