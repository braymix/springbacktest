package it.simulazione.secondaSimulazioneservices.interfaces;

import java.util.List;

import it.simulazione.secondaSimulazione.dao.CorridoreDao;
import it.simulazione.secondaSimulazione.dto.CorridoreDto;

public interface CorridoreServiceInterface {
	List<CorridoreDto> fetchAll();
	
	boolean deleteById(int id);

	boolean create(CorridoreDao dao);
	
	boolean update(CorridoreDao dao);
}
