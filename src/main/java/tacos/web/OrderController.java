package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.Order;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid @ModelAttribute("order") Order order, Errors error, Model model) {
		if(error.hasErrors()) {
			log.info("Validation failed");
			return "orderForm";
		}
		
		return "";
	}
}
