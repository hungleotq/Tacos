package tacos.repository;

import java.util.List;

import tacos.dto.Taco;

public interface TacoRepository {

	List<Taco> findAll();
	Taco findByID(String id);
	Taco save(Taco design);
	List<Taco> findByIDs(List<Long> ids);
}
