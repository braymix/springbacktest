package it.simulazione.secondaSimulazione.dao;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import it.simulazione.secondaSimulazione.dto.GaraDto;
import lombok.Data;
@Entity
@Table(name = "Gara")
@Data
public class GaraDao {
	/*qui ho la gare dove ho un titolo che serve all'utente se avesse bisogno di intitolare le gare
	 * un identificativo che serve all'utente nel caso ne avesse bisogno
	 *  ed il luogo nella quale viene svolta*/
	@Id
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Titolo")
	private String titolo;
	
	@Column(name = "Identificativo")
	private String identificativo;
	
	@Column(name = "Luogo")
	private String luogo;

	public GaraDto convertToDto() {
		GaraDto dto = new GaraDto(id,titolo,identificativo,luogo);
		return dto;
	}
}
