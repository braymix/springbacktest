package it.simulazione.secondaSimulazione.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GaraDto {
	private int id;
	private String titolo;
	private String identificativo;
	private String luogo;
}
