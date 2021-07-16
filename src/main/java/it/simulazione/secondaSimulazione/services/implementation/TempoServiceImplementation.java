package it.simulazione.secondaSimulazione.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.simulazione.secondaSimulazione.dao.TempoDao;
import it.simulazione.secondaSimulazione.dto.TempoDto;
import it.simulazione.secondaSimulazione.repository.TempoRepository;
import it.simulazione.secondaSimulazioneservices.interfaces.TempoServiceInterface;

@Service
@Transactional
public class TempoServiceImplementation implements TempoServiceInterface {

	@Autowired
	TempoRepository tempoRepository;

	@Override
	public List<TempoDto> fetchAll() {
		List<TempoDto> list = new ArrayList<>();
		try {
			for (TempoDao prodotti : tempoRepository.findAll()) {
				list.add(prodotti.convertToDto());
			}
		} catch (Exception e) {
			return null;
		}
		return list;
	}

	@Override
	public boolean deleteById(int id) {
		if (tempoRepository.existsById(id)) {
			tempoRepository.deleteById(id);
			this.fetchAll();
			return true;

		} else {
			return false;
		}
	}

	@Override
	public boolean create(TempoDao dao) {
		try {
			tempoRepository.save(dao);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean update(TempoDao dao) {
		if (tempoRepository.existsById(dao.getId())) {
			try {
				tempoRepository.save(dao);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}
/*questo ritorna la classifica già ordinata e senza ripetizioni, una vera e propria classifica*/
	public List<TempoDto> getClassifica(int gara) {

		List<TempoDao> dao = tempoRepository.findByGaraOrderByTempo(gara);

		List<TempoDto> dto = new ArrayList();

		for (int i = 0; i < dao.size(); i++) {
			if (!(dao.get(i).getTempo() == null))
				dto.add(dao.get(i).convertToDto());
		}
		List<TempoDto> classifica = new ArrayList();
		classifica.add(dto.get(0));
		boolean flag = false;
		for (int i = 0; i < dto.size(); i++) {
			flag = false;
			for (int k = 0; k < classifica.size(); k++) {
				if (dto.get(i).getCorridore() == classifica.get(k).getCorridore()) {
					flag = true;
				}

			}
			if (!flag) {
				classifica.add(dto.get(i));
			}

		}

		return classifica;
	}
}
