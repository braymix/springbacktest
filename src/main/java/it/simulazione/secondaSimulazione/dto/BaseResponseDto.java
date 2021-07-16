package it.simulazione.secondaSimulazione.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResponseDto<T> {
/*questo è una base response ovvero la base della response che poi andrà a ritornare il mio servizio*/
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private Object response;

}
