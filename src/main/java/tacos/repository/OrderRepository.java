package tacos.repository;

import java.sql.SQLException;
import java.util.List;

import tacos.dto.Order;

public interface OrderRepository{

	Order save(Order order) throws SQLException;

	List<Order> findAll();
}
