package com.revature.foodMartApi.web.servlets;

import com.revature.foodMartApi.models.GroceryList;
import com.revature.foodMartApi.services.GroceryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *GroceryListServlet which handles mapping and watches for calls
 */
@RestController
@RequestMapping("/groceryLists")
public class GroceryListServlet {

    private final GroceryListService groceryListService;

    /**
     * @param groceryListService
     * Constructor for the Servlet
     */
    @Autowired
    public GroceryListServlet(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    /**
     * @param groceryList
     * @return
     * Creates and returns a groceryList
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GroceryList createGroceryList(@RequestBody GroceryList groceryList) {
        return groceryListService.addGroceryList(groceryList);
    }

    /**
     * @param id
     * @return
     * id mapping used and returns a GroceryList
     */
    @GetMapping("/id")
    public Optional<GroceryList> findGroceryListById(@RequestParam Long id) {
        return groceryListService.findById(id);
    }

    /**
     * @param id
     * @return
     * userList id used to find and return a list of GroceryLists
     */
    @GetMapping("/userList")
    public List<GroceryList> findGroceryListByUserListId(@RequestParam int id) {
        return groceryListService.findByUserListId(id);
    }

    /**
     * @return
     * find all GroceryLists and return them in a List
     */
    @GetMapping
    public List<GroceryList> findAllGroceryLists() {
        return groceryListService.findAll();
    }

    /**
     * @param groceryList
     * Delete a groceryList by passing in the json of one
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroceryList(@RequestBody GroceryList groceryList) {
        groceryListService.delete(groceryList);
    }

    /**
     * @param id
     * delete a groceryList by using the Id. This is a request param
     */
    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroceryListById(@RequestParam Long id) {
        groceryListService.deleteById(id);
    }
}
