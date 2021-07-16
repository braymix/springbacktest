package it.simulazione.secondaSimulazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.simulazione.secondaSimulazione.dao.TempoDao;
import it.simulazione.secondaSimulazione.dto.TempoDto;

public interface TempoRepository extends JpaRepository<TempoDao, Integer> {

	List<TempoDao> findByGaraOrderByTempo(int gara);

}
