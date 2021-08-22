//package tacos.repository;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import lombok.extern.slf4j.Slf4j;
//import tacos.dto.Ingredient;
//
//@Slf4j
//@Repository
//@Primary
//public class IngredientJDBCTemplateRepository implements IngredientRepository{
//	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
////	
//	@Override
//	public List<Ingredient> findAll() {
//		List<Ingredient> result = new ArrayList<Ingredient>();
//		Iterator<Map<String,Object>> ite = jdbcTemplate.queryForList("select * from Ingredient order by cast(id as number) asc").iterator();
//		while(ite.hasNext()) {
//			Map<String,Object> item = ite.next();
//			result.add(new Ingredient(String.valueOf(item.get("id")), 
//					String.valueOf(item.get("name")), 
//					Ingredient.Type.valueOf(String.valueOf(item.get("type"))),
//					String.valueOf(item.get("description")),
//					String.valueOf(item.get("createdAt"))));
//		}
//		log.info("[JDBCTemplateIngredientRepository][findAll]: {}", result.toString());
//		return result;
//	}
//
//	@Override
//	public Ingredient findOne(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ingredient save(Ingredient ingredient) {
////		String id = jdbcTemplate.queryForObject("SELECT SEQ_INGREDIENT_ID.NEXTVAL AS SEQ FROM DUAL", String.class);
////		ingredient.setId(id);
//		ingredient.setCreatedAt(new Date().toString());
//		log.info("IngredientRepository save : {}", ingredient.toString());
//		jdbcTemplate.update("insert into Ingredient (id,name,type,description,createdAt) "
//				+ "values (next value for SEQ_INGREDIENT_ID,?,?,?,?)", 
////				ingredient.getId(), 
//				ingredient.getName(), 
//				ingredient.getType().toString(),
//				ingredient.getDescription(),
//				new Date().toString());
//		return ingredient;
//	}
//
//	
//}
