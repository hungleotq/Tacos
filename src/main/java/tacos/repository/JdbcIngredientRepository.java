package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.dto.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Ingredient> findAll() {
		return jdbc.query("select * from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(Long id) {
		return jdbc.queryForObject("select * from Ingredient where id = ?", this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name, type) "
				+ "values (?,?,?)", 
				ingredient.getId(), 
				ingredient.getName(),
				ingredient.getType());
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException{
		return new Ingredient(Long.valueOf(rs.getString("id")), 
				rs.getString("name"), 
				Ingredient.Type.valueOf(rs.getString("type")));
	}
}
