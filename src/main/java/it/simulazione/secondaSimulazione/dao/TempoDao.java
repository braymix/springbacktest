package it.simulazione.secondaSimulazione.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.simulazione.secondaSimulazione.dto.TempoDto;
import lombok.Data;

@Entity
@Table(name = "Tempo")
@Data
public class TempoDao {
	/*qui ho i tempi dei corridori salvati relativi alla gara*/
	@Id
	@Column(name = "Id")
	private int id;
	/*siccome nella traccia non cera scritto nulla sul come salvare un tempo ho preferito salvarlo come float*/
	@Column(name = "Tempo",nullable = true)
	private Float tempo;
	
	@Column(name = "Corridore")
	private int corridore;
	
	@Column(name = "Gara")
	private int gara;
	
	@ManyToOne
    @JoinColumn(name="Gara",nullable=false,insertable=false, updatable=false)
	private GaraDao garaOBJ;
	
	@ManyToOne
    @JoinColumn(name="Corridore",nullable=false,insertable=false, updatable=false)
	private CorridoreDao corridoreOBJ;

	public TempoDto convertToDto() {
		TempoDto dto = new TempoDto(id,tempo,corridore,gara,garaOBJ,corridoreOBJ);
		return dto;
	}

}
