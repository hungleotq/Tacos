package tacos.repository;

import java.util.List;

import tacos.dto.Ingredient;

public interface IngredientRepository {

	List<Ingredient> findAll();
	
	Ingredient findOne (Long id);
	
	Ingredient save(Ingredient ingredient);
}
