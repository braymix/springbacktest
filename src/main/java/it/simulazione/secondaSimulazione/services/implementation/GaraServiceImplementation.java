package it.simulazione.secondaSimulazione.services.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.simulazione.secondaSimulazione.dao.GaraDao;
import it.simulazione.secondaSimulazione.dto.GaraDto;
import it.simulazione.secondaSimulazione.repository.GaraRepository;
import it.simulazione.secondaSimulazioneservices.interfaces.GaraServiceInterface;
@Service
@Transactional
public class GaraServiceImplementation implements GaraServiceInterface {

	@Autowired
	GaraRepository garaRepository;
	
	@Override
	public List<GaraDto> fetchAll() {
		List<GaraDto> list = new ArrayList<>();
		try {
			for (GaraDao prodotti : garaRepository.findAll()) {
				list.add(prodotti.convertToDto());
			}
		} catch (Exception e) {
			return null;
		}
		return list;
	}

	@Override
	public boolean deleteById(int id) {
		if (garaRepository.existsById(id)) {
			garaRepository.deleteById(id);
			this.fetchAll();
			return true;
			
		} else {
			return false;
		}
	}

	@Override
	public boolean create(GaraDao dao) {

		try {
			garaRepository.save(dao);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean update(GaraDao dao) {
		if (garaRepository.existsById(dao.getId())) {
			try {
				garaRepository.save(dao);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}

}
