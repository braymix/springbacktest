package it.simulazione.secondaSimulazione.dto;

import it.simulazione.secondaSimulazione.dao.CorridoreDao;
import it.simulazione.secondaSimulazione.dao.GaraDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempoDto {
	private int id;
	private Float tempo;
	private int corridore;
	private int gara;
	private GaraDao garaOBJ;
	private CorridoreDao corridoreOBJ;
}
