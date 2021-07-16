package it.simulazione.secondaSimulazioneservices.interfaces;

import java.util.List;

import it.simulazione.secondaSimulazione.dao.GaraDao;
import it.simulazione.secondaSimulazione.dto.GaraDto;

public interface GaraServiceInterface {
	List<GaraDto> fetchAll();
	
	boolean deleteById(int id);

	boolean create(GaraDao dao);
	
	boolean update(GaraDao dao);
}
