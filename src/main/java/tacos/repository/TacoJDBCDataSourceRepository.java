package tacos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tacos.dto.Taco;

@Repository
public class TacoJDBCDataSourceRepository implements TacoRepository{

	DataSource dataSource; // use with JNDI

	@Autowired
	public TacoJDBCDataSourceRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Taco save(Taco design) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(new StringBuilder("insert into Taco (")
					.append("values (").append(design.getId()).append(",")
					.append(design.getName()).append(",")
					.append(new Date()).append(")").toString());
			statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return design;
	}

	@Override
	public List<Taco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taco findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Taco> findByIDs(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
