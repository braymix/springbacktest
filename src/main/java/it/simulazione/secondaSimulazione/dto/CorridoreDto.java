package it.simulazione.secondaSimulazione.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorridoreDto {

	private int id;


	private String Identificativo;
	

	private int squalificato;
}
