package tacos.dto;

import java.sql.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

	private final Long id;
	private Date createdAt;
	private final String name;
	private final Type type;

	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
