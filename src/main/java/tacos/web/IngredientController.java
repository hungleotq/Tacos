package tacos.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import tacos.dto.Ingredient.Type;
import tacos.dto.Order;
import tacos.repository.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	IngredientRepository ingredientRepository;
	
	@GetMapping
	public String showIngredient(Model model) {
		prepareForIngredient(model);
		model.addAttribute("ingredient", new Ingredient(null, null, null, null, null));
		return "ingredient";
	}
	static Errors error = null;
	@PostMapping
	public String processAdd(@Valid @ModelAttribute("ingredient") Ingredient ingredient, Errors error ,Model model){
		if(error.hasErrors()) {
			prepareForIngredient(model);
			return "ingredient";
		}
		log.info("[IngredientController][processAdd] : {}",  ingredient.toString());
		ingredientRepository.save(ingredient);
		return "redirect:/ingredient";
	}
	
	private void prepareForIngredient(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		log.info("[IngredientController][showIngredient] : {}",  ingredients.toString());
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("types", Arrays.asList(Ingredient.Type.values()));
	}
}
