/**
 * 
 */
package com.revature.foodMartApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Awaab
 *
 */
@RestController
public class mainController {
	
	@GetMapping("/")
	public String index() {
		return "Greeting";
	}

}
