package it.simulazione.secondaSimulazione.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import it.simulazione.secondaSimulazione.dto.CorridoreDto;
import lombok.Data;

@Entity
@Table(name = "Corridore")
@Data
public class CorridoreDao {
	/*questa classe contiene il corridore che contiene l'identificativo "ad esempio la pettorina" e se è o no squalificato*/
	@Id
	@Column(name = "Id")
	private int id;

	@Column(name = "Identificativo")
	private String Identificativo;
	
	@Column(name = "Squalificato")
	private int squalificato;

	public CorridoreDto convertToDto() {
		CorridoreDto dto = new CorridoreDto(id,Identificativo,squalificato);
		return dto;
	}
	
}
