package it.simulazione.secondaSimulazione.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.simulazione.secondaSimulazione.dao.CorridoreDao;

public interface CorridoreRepository extends JpaRepository<CorridoreDao, Integer> {

}
