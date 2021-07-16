package it.simulazione.secondaSimulazioneservices.interfaces;

import java.util.List;

import it.simulazione.secondaSimulazione.dao.TempoDao;
import it.simulazione.secondaSimulazione.dto.TempoDto;

public interface TempoServiceInterface {
	List<TempoDto> fetchAll();
	
	boolean deleteById(int id);

	boolean create(TempoDao dao);
	
	boolean update(TempoDao dao);
}
