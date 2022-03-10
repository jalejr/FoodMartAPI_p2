package com.revature.foodMartApi.web.servlets;

import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.services.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *GroceryItem servlet with various end points for API access
 */
@RestController
@RequestMapping("/groceryItems")
public class GroceryItemServlet {

    private final GroceryItemService groceryItemService;

    /**
     * @param groceryItemService
     * Constructor
     */
    @Autowired
    public GroceryItemServlet(GroceryItemService groceryItemService) { this.groceryItemService = groceryItemService; }

    /**
     * @param groceryItem
     * @return
     * creates a grocery item and returns it. Takes a groceryList json
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GroceryItem createGroceryItem(@RequestBody GroceryItem groceryItem) {
        return groceryItemService.addGroceryItem(groceryItem);
    }

    /**
     * @return
     * finds all GroceryItems and returns as List
     */
    @GetMapping
    public List<GroceryItem> findAllGroceryItems() { return groceryItemService.findAllItems(); }

    /**
     * @param id
     * @return
     * finds GroceryItem by id and returns it
     */
    @GetMapping("/id")
    public Optional<GroceryItem> findGroceryItemById(@RequestParam int id) {
        return groceryItemService.findItemById(id);
    }

    /**
     * @param groceryItem
     * deletse groceryItem and wants a groceryItem json
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroceryItem(@RequestBody GroceryItem groceryItem) {
        groceryItemService.deleteGroceryItem(groceryItem);
    }
}
