package it.simulazione.secondaSimulazione.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.simulazione.secondaSimulazione.dao.GaraDao;

public interface GaraRepository extends JpaRepository<GaraDao, Integer> {

}
