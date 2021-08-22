package tacos.web;

import java.util.List;
import java.util.stream.Collectors;

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

import lombok.extern.slf4j.Slf4j;
import tacos.dto.Ingredient;
import tacos.dto.Ingredient.Type;
import tacos.dto.Order;
import tacos.dto.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {

	private IngredientRepository ingredientRepository;

	private TacoRepository tacoRepository;

	@Autowired
	public DesignController(IngredientRepository ingredientRepository,
							TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new  Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		prepareForDesign(model);
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("taco") Taco taco, Errors error, 
			Model model, @ModelAttribute Order order) {
		if(error.hasErrors()) {
			log.info("Validation failed");
			prepareForDesign(model);
			return "design";
		} 
		taco = (Taco) model.getAttribute("taco"); //???? model mapping from request ???
		Taco saved = tacoRepository.save(taco);
		order.addTaco(saved); 
		
		log.info("Processing design: " + taco);
		return "redirect:/order/current";
	}
	
	private void prepareForDesign(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), 
					ingredients.stream().filter(c -> c.getType().equals(type)).collect(Collectors.toList()));
		}
	}
}
