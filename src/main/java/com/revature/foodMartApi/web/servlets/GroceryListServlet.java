package com.revature.foodMartApi.web.servlets;

import com.revature.foodMartApi.models.GroceryList;
import com.revature.foodMartApi.services.GroceryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groceryLists")
public class GroceryListServlet {

    private final GroceryListService groceryListService;

    @Autowired
    public GroceryListServlet(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GroceryList createGroceryList(@RequestBody GroceryList groceryList) {
        return groceryListService.addGroceryList(groceryList);
    }

    @GetMapping("/id")
    public Optional<GroceryList> findGroceryListById(@RequestParam Long id) {
        return groceryListService.findGroceryById(id);
    }

    @GetMapping("/userList")
    public Optional<GroceryList> findGroceryListByUserListId(@RequestParam Long id) {
        return groceryListService.findGroceryListByUserListId(id);
    }

    @GetMapping
    public List<GroceryList> findAllGroceryLists() {
        return groceryListService.findAllGroceryLists();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroceryList(@RequestBody GroceryList groceryList) {
        groceryListService.deleteGroceryList(groceryList);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroceryListById(@RequestParam Long id) {
        groceryListService.deleteGroceryListById(id);
    }
}