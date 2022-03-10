package com.revature.foodMartApi.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model for GroceryItems meant to represent any item held by a grocery store.
 */
@Entity
@Table(name="item")
public class GroceryItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="item_price")
	private double itemPrice;
	
	@Column(name="inventory_count")
	private int inventoryCount;

	public GroceryItem() {
		super();
	}



	public GroceryItem(int itemId, String itemName, String description, double itemPrice, int inventoryCount) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.itemPrice = itemPrice;
		this.inventoryCount = inventoryCount;
	}



	public int getItemId() {
		return itemId;
	}



	public void setItemId(int itemId) {
		this.itemId = itemId;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getItemPrice() {
		return itemPrice;
	}



	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}



	public int getInventoryCount() {
		return inventoryCount;
	}



	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}



	@Override
	public String toString() {
		return "GroceryItem [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description
				+ ", itemPrice=" + itemPrice + ", inventoryCount=" + inventoryCount + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(description, inventoryCount, itemId, itemName, itemPrice);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroceryItem other = (GroceryItem) obj;
		return Objects.equals(description, other.description) && inventoryCount == other.inventoryCount
				&& itemId == other.itemId && Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(itemPrice) == Double.doubleToLongBits(other.itemPrice);
	}
	
	
}
