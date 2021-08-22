package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.dto.Order;
import tacos.dto.Taco;

@Repository
public class OrderSpringJDBCRepository implements OrderRepository{

	private SimpleJdbcInsert orderInserter;

	private SimpleJdbcInsert tacoOrderInserter; // also parts of spring jdbc

	private ObjectMapper mapper;

	JdbcTemplate jdbc;

	@Autowired
	NamedParameterJdbcTemplate namedJDBC;

	@Autowired
	TacoJDBCDataSourceRepository tacoRepository;

	@Autowired
	IngredientRepository ingredientRepository;

	@Autowired
	public OrderSpringJDBCRepository(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
		orderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("order_taco")
						.usingGeneratedKeyColumns("id");
		tacoOrderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("order_taco_tacos");
		mapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order){

		@SuppressWarnings("unchecked")
		Map<String, Object> values = mapper.convertValue(order, Map.class);
		values.put("createdAt", Calendar.getInstance().getTime().toString());
		Long orderID = orderInserter.executeAndReturnKey(values).longValue();
		order.setId(orderID);
		for(Taco obj : order.getTacos()) {
			values = new HashMap<String, Object>();
			values.put("tacoID", obj.getId());
			values.put("orderID", orderID);
			tacoOrderInserter.execute(values);
		}
		return order;
	}

	@Override
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<Order>();
		Iterator<Map<String,Object>> ite = jdbc.queryForList("select * from order_taco order by ordername asc").iterator();
		while(ite.hasNext()) {
			Map<String,Object> item = ite.next();
			Order order = new Order();
			order.setId(Long.valueOf(item.get("id").toString()));
			order.setOrderName((String)item.get("orderName"));
			order.setCreatedAt((String)item.get("CreatedAt"));
			order.setTacos(tacoListByOrderID(order.getId()));
			orders.add(order);
		}
		return orders;
	}

	private List<Taco> tacoListByOrderID(Long id) {
		List<Taco> tacos = new ArrayList<Taco>();
		Map<String, List<Long>> tacoIDs = new HashMap<String, List<Long>>();
		Iterator<Map<String,Object>> ite = jdbc.queryForList("select * from order_taco_tacos "
				+ "where orderid = ?", id).iterator();
		List<Long> tacoIDList = new ArrayList<Long>();
		while(ite.hasNext()) {
			Map<String,Object> item = ite.next();
			tacoIDList.add(Long.valueOf(item.get("tacoid").toString()));
		}
		tacoIDs.put("ids", tacoIDList);

		tacos = namedJDBC.query("select * from taco "
				+ "where id in (:ids)", tacoIDs, new ResultSetExtractor<List<Taco>>() {

					@Override
					public List<Taco> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Taco> list = new ArrayList<Taco>();
						while(rs.next()) {
							Taco taco = new Taco();
							taco.setId(rs.getLong("id"));
							taco.setName(rs.getString("name"));
							taco.setIngredients(ingredientListByTacoID(taco.getId()));
							list.add(taco);
						}
						return list;
					}

				});
		return tacos;
	}


	private List<String> ingredientListByTacoID(Long id) {
		List<String> ingredients = new ArrayList<String>();
		Map<String, List<String>> ingreIDs = new HashMap<String, List<String>>();
		Iterator<Map<String,Object>> ite = jdbc.queryForList("select * from TACO_INGREDIENTS "
				+ "where TACO = ?", id).iterator();
		List<String> ingreIDList = new ArrayList<String>();
		while(ite.hasNext()) {
			Map<String,Object> item = ite.next();
			ingreIDList.add(item.get("INGREDIENT").toString());
		}
		ingreIDs.put("ids", ingreIDList);

		ingredients = namedJDBC.query("select * from INGREDIENT "
				+ "where id in (:ids)", ingreIDs, new ResultSetExtractor<List<String>>() {

					@Override
					public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<String> list = new ArrayList<String>();
						while(rs.next()) {
							list.add(rs.getString("name"));
						}
						return list;
					}

				});
		return ingredients;
	}
}
