package it.simulazione.secondaSimulazione.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import it.simulazione.secondaSimulazione.dao.CorridoreDao;
import it.simulazione.secondaSimulazione.dao.GaraDao;
import it.simulazione.secondaSimulazione.dao.TempoDao;
import it.simulazione.secondaSimulazione.dto.BaseResponseDto;
import it.simulazione.secondaSimulazione.dto.CorridoreDto;
import it.simulazione.secondaSimulazione.dto.GaraDto;
import it.simulazione.secondaSimulazione.dto.ResultDto;
import it.simulazione.secondaSimulazione.dto.TempoDto;
import it.simulazione.secondaSimulazione.services.implementation.CorridoreServiceImplementation;
import it.simulazione.secondaSimulazione.services.implementation.GaraServiceImplementation;
import it.simulazione.secondaSimulazione.services.implementation.TempoServiceImplementation;

@RestController
@RequestMapping(value = "api/tempo")
public class CorsaController {

	@Autowired
	TempoServiceImplementation tempoServiceImplementation;
	@Autowired
	CorridoreServiceImplementation corridoreServiceImplementation;
	@Autowired
	GaraServiceImplementation garaServiceImplementation;
	
	/*questo servizio fa in modo che al momento della aggiunta di un nuovo tempo fa due tipi di controlli
	 * il primo controllo guarda se il corridore è iscritto alla gara
	 * mentre il secondo controlla che il corridore non sia stato squalificato*/
	@PostMapping(path = "GARA")
	public BaseResponseDto<TempoDto> aggiungiTempo(@RequestBody TempoDao tempo){
		
		BaseResponseDto<TempoDto> response = new BaseResponseDto<TempoDto>();
		response.setTimestamp(new Date());
		//qui avviene il controllo se il corridore è iscritto alla gara
		final String uri = "https://itspd.altervista.org/check/?id=" + tempo.getCorridore();

	    RestTemplate restTemplate = new RestTemplate();
	    ResultDto result = restTemplate.getForObject(uri, ResultDto.class);
	    
	    if (!result.getStatus().equals("ok")) {
	    	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage("corridore non iscritto alla gara");
			return response;
	    }
		//qui viene fatto il controllo se il corridore è stato squalificato
		if(corridoreServiceImplementation.isSqualificato(tempo.getCorridore())) {
			tempo.setTempo(null);
			tempoServiceImplementation.create(tempo);
			
			response.setResponse(tempo);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("corridore squalificato");
		}else {
			tempoServiceImplementation.create(tempo);
			
			response.setResponse(tempo);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("tempo aggiunto correttamente");
		}
		
		return response;
	}
	
	/*questo servizio mi serve per aggiungere una gara*/
	@PostMapping(path = "aggiungiGara")
	public BaseResponseDto<GaraDto> aggiungiGara(@RequestBody GaraDao gara){
		
		BaseResponseDto<GaraDto> response = new BaseResponseDto<GaraDto>();
		response.setTimestamp(new Date());
		
		garaServiceImplementation.create(gara);
		
		response.setResponse(gara);
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("gara aggiunta correttamente");
		return response;
	}
	/*questo servizio mi serve per aggiungere un corridore*/
	@PostMapping(path = "aggiungiCorridore")
	public BaseResponseDto<CorridoreDto> aggiungiTempo(@RequestBody CorridoreDao corridore){
		
		BaseResponseDto<CorridoreDto> response = new BaseResponseDto<CorridoreDto>();
		response.setTimestamp(new Date());

		
		corridoreServiceImplementation.create(corridore);
		
		response.setResponse(corridore);
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("corridore aggiunto correttamente");
		return response;
	}
	/*questo servizio mi serve a squalificare un corridore dato il suo id*/
	@GetMapping(path = "squalifica/{id}")
	public BaseResponseDto<String> squalifica(@PathVariable int id){
		BaseResponseDto<String> response = new BaseResponseDto<String>();
		response.setTimestamp(new Date());
		//qui prendo il corridore dall'id e lo setto ad uno poi faccio un update
		CorridoreDao corridore = corridoreServiceImplementation.findById(id);
		corridore.setSqualificato(1);
		corridoreServiceImplementation.update(corridore);
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("corridore squalificato correttamente");
		return response;
	}
	/*questo servizio mi serve a toglie la squalifica da un corridore*/
	@GetMapping(path = "togliSqualifica/{id}")
	public BaseResponseDto<String> togliSqualifica(@PathVariable int id){
		BaseResponseDto<String> response = new BaseResponseDto<String>();
		response.setTimestamp(new Date());
		//qui prendo il corridore dall'id e lo setto a zero poi faccio un update
		CorridoreDao corridore = corridoreServiceImplementation.findById(id);
		corridore.setSqualificato(0);
		corridoreServiceImplementation.update(corridore);
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("squalifica tolta correttamente");
		return response;
	}
	
	/**/
	@GetMapping(path = "classifica/{gara}")
	public BaseResponseDto<List<TempoDto>> classifica(@PathVariable int gara){
		
		BaseResponseDto<List<TempoDto>> response = new BaseResponseDto<>();
		
		response.setResponse(tempoServiceImplementation.getClassifica(gara));
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("squalifica tolta correttamente");
		return response;
	}
	
}
