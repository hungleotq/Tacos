package tacos.dto;

import java.sql.Date;

//import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Ingredient {

//	@Id
	private String id;
	
	@NotBlank(message="Name is required")
	private String name;
	
	@NotNull(message="Type is required")
	private Type type;
	
	private String description;
	
	private String createdAt;

	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}
