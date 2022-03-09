package com.revature.foodMartApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="grocery_lists")
public class GroceryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grocery_list_id", nullable = false)
    private Long groceryListId;

    @JoinColumn(name = "list_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
    @JsonIgnoreProperties({"user"})
    private UserList listId;

    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
    @JsonIgnoreProperties({"itemName","description", "itemPrice", "inventoryCount"})
    private GroceryItem itemId;

    @Column(name = "item_count", nullable = false)
    private int itemCount;

    public GroceryList() {
    }

    public GroceryList(Long groceryListId, UserList listId, GroceryItem itemId, int itemCount) {
        this.groceryListId = groceryListId;
        this.listId = listId;
        this.itemId = itemId;
        this.itemCount = itemCount;
    }

    public Long getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(Long grocery_list_id) {
        this.groceryListId = grocery_list_id;
    }

    public UserList getListId() {
        return listId;
    }

    public void setListId(UserList list_id) {
        this.listId = list_id;
    }

    public GroceryItem getItemId() {
        return itemId;
    }

    public void setItemId(GroceryItem item_id) {
        this.itemId = item_id;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int item_count) {
        this.itemCount = item_count;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "groceryListId=" + groceryListId +
                ", listId=" + listId +
                ", itemId=" + itemId +
                ", itemCount=" + itemCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryList that = (GroceryList) o;
        return itemCount == that.itemCount && groceryListId.equals(that.groceryListId) && listId.equals(that.listId) && itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groceryListId, listId, itemId, itemCount);
    }
}