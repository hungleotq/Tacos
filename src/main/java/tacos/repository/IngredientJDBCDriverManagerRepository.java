package tacos.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.Ingredient;

@Slf4j
@Repository
//@Primary
@PropertySource("classpath:application.properties")
public class IngredientJDBCDriverManagerRepository implements IngredientRepository{

	final
	Environment env;

	public IngredientJDBCDriverManagerRepository(Environment env) {
		this.env = env;
	}

	@Override
	public List<Ingredient> findAll() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			conn = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
			statement = conn.prepareStatement("select * from Ingredient");
			statement = conn.createStatement();
			result = statement.executeQuery("select * from Ingredient"); // use statement for static sql

			while(result.next()) {
				ingredients.add(new Ingredient(result.getString("id"),
						result.getString("name"),
						Ingredient.Type.valueOf(result.getString("type")),
						result.getString("description"),
						result.getString("createdAt")));
			}
			log.info("[JdbcIngredientRepository][findAll]: {}", ingredients.toString());
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(result != null) {
						result.close();
				}
				if(statement != null) {
						statement.close();
				}
				if(conn != null) {
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ingredients;
	}

	@Override
	public Ingredient findOne(Long id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Ingredient ingredient = null;
		try {
			conn = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
			statement = conn.prepareStatement("select * from Ingredient where id=?");
			statement.setLong(1, id);
			result = statement.executeQuery();
			ingredient = new Ingredient(result.getString("id"), 
					result.getString("name"), 
					Ingredient.Type.valueOf(result.getString("type")),
					result.getString("description"),
					result.getString("createdAt"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(result != null) {
						result.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ingredient;
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
			statement = conn.prepareStatement("insert into Ingredient (name,type, description, createdAt)  " +
					"values (?,?,?,?)");
			statement.setString(1, ingredient.getName());
			statement.setString(2, ingredient.getType().toString());
			statement.setString(3, ingredient.getDescription());
			statement.setString(4, LocalDate.now().toString());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
						statement.close();
				}
				if(conn != null) {
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ingredient;
	}
}
