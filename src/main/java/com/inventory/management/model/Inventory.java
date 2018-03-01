package com.inventory.management.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Inventory)) {
			return false;
		}
		Inventory other = (Inventory) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [key=" + key + ", qpb=" + qpb + ", moq=" + moq + ", reorderPoint=" + reorderPoint
				+ ", inStockQuantity=" + inStockQuantity + ", inTransitQuantity=" + inTransitQuantity
				+ ", availableQuantity=" + availableQuantity + "]";
	}

}
