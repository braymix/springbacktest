package it.simulazione.secondaSimulazione.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.simulazione.secondaSimulazione.dao.CorridoreDao;
import it.simulazione.secondaSimulazione.dto.CorridoreDto;
import it.simulazione.secondaSimulazione.repository.CorridoreRepository;
import it.simulazione.secondaSimulazioneservices.interfaces.CorridoreServiceInterface;
@Service
@Transactional
public class CorridoreServiceImplementation implements CorridoreServiceInterface {

	
	@Autowired 
	CorridoreRepository corridoreRepository;

	
	@Override
	public List<CorridoreDto> fetchAll() {
		List<CorridoreDto> list = new ArrayList<>();
		try {
			for (CorridoreDao prodotti : corridoreRepository.findAll()) {
				list.add(prodotti.convertToDto());
			}
		} catch (Exception e) {
			return null;
		}
		return list;
	}

	@Override
	public boolean deleteById(int id) {
		if (corridoreRepository.existsById(id)) {
			corridoreRepository.deleteById(id);
			this.fetchAll();
			return true;
			
		} else {
			return false;
		}
	}

	@Override
	public boolean create(CorridoreDao dao) {

		try {
			corridoreRepository.save(dao);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean update(CorridoreDao dao) {
		if (corridoreRepository.existsById(dao.getId())) {
			try {
				corridoreRepository.save(dao);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}
	/*qui ricerco se il corridore è stato squalificato oppure no*/
	public boolean isSqualificato(int corridore) {
		Optional<CorridoreDao> dato = corridoreRepository.findById(corridore);
		boolean isSqual = false;
		//per controllare se il corridore è stato squalificato faccio un controllo per il suo "flag di squalifica" se è a 0 non è stato squalificato altrimenti si
		if(dato.get().getSqualificato() == 0) {
			isSqual = false;
		}else {
			isSqual = true;
		}
		return isSqual;
	}

	public CorridoreDao findById(int id) {
		Optional<CorridoreDao> dato = corridoreRepository.findById(id);
		return dato.get();
	}



	
	
	
	

}
