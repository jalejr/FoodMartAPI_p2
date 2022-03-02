package com.revature.foodMartApi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="grocery_lists")
public class GroceryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grocery_list_id", nullable = false)
    private Long grocery_list_id;

    @JoinColumn(name = "list_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private UserList list_id;

    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private GroceryItem item_id;

    @Column(name = "item_count", nullable = false)
    private int item_count;

    public GroceryList() {
    }

    public GroceryList(Long grocery_list_id, UserList list_id, GroceryItem item_id, int item_count) {
        this.grocery_list_id = grocery_list_id;
        this.list_id = list_id;
        this.item_id = item_id;
        this.item_count = item_count;
    }

    public Long getGrocery_list_id() {
        return grocery_list_id;
    }

    public void setGrocery_list_id(Long grocery_list_id) {
        this.grocery_list_id = grocery_list_id;
    }

    public UserList getList_id() {
        return list_id;
    }

    public void setList_id(UserList list_id) {
        this.list_id = list_id;
    }

    public GroceryItem getItem_id() {
        return item_id;
    }

    public void setItem_id(GroceryItem item_id) {
        this.item_id = item_id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "grocery_list_id=" + grocery_list_id +
                ", list_id=" + list_id +
                ", item_id=" + item_id +
                ", item_count=" + item_count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryList that = (GroceryList) o;
        return item_count == that.item_count && grocery_list_id.equals(that.grocery_list_id) && list_id.equals(that.list_id) && item_id.equals(that.item_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grocery_list_id, list_id, item_id, item_count);
    }
}
