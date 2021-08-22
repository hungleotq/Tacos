package tacos.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.Order;
import tacos.repository.OrderRepository;

import java.sql.SQLException;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {

	@Autowired
	OrderRepository repository;
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors error, SessionStatus sessionStatus) {
		if(error.hasErrors()) {
			log.info("Validation failed");
			return "orderForm";
		}
		try {
			repository.save(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
