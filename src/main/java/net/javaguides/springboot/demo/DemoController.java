package net.javaguides.springboot.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("Up and Running");
		return "<h1> Up and Running </h1>";
	}

}
