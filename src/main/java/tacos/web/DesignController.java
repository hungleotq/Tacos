package tacos.web;

import java.util.*;
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

import lombok.extern.slf4j.Slf4j;
import tacos.dto.Ingredient;
import tacos.dto.Taco;
import tacos.repository.IngredientRepository;
import tacos.dto.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {

	@Autowired
	private IngredientRepository ingredientRepository;
	
//	@ModelAttribute
//	public void addIngredientsToModel(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(
//		  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//		  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//		  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//		  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//		  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//		  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//		  new Ingredient("CHED", "Cheddar", Type.CHEESE),
//		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//		  new Ingredient("SLSA", "Salsa", Type.SAUCE),
//		  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//		);
		
//	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = ingredientRepository.findAll();
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), 
					ingredients.stream().filter(c -> c.getType().equals(type)).collect(Collectors.toList()));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors error, Model model) {
		if(error.hasErrors()) {
			log.info("Validation failed");
			return "design";
		} 
		
		log.info("Processing design: " + design);
		return "redirect:/order/current";
	}
	
}
