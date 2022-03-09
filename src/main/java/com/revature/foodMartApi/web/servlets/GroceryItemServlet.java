package com.revature.foodMartApi.web.servlets;

import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.services.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groceryItems")
public class GroceryItemServlet {

    private final GroceryItemService groceryItemService;

    @Autowired
    public GroceryItemServlet(GroceryItemService groceryItemService) { this.groceryItemService = groceryItemService; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GroceryItem createGroceryItem(@RequestBody GroceryItem groceryItem) {
        return groceryItemService.addGroceryItem(groceryItem);
    }

    @GetMapping
    public List<GroceryItem> findAllGroceryItems() { return groceryItemService.findAllItems(); }

    @GetMapping("/id")
    public Optional<GroceryItem> findGroceryItemById(@RequestParam int id) {
        return groceryItemService.findItemById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroceryItem(@RequestBody GroceryItem groceryItem) {
        groceryItemService.deleteGroceryItem(groceryItem);
    }
}
