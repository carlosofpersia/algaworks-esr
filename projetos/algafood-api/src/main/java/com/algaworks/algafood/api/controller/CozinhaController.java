package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

// @Controller
// @ResponseBody
@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar1() {
		System.out.println("Listar 1");
		return cozinhaRepository.listar();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public 	CozinhasXmlWrapper listar2() {
		System.out.println("Listar 2");
		return new CozinhasXmlWrapper(cozinhaRepository.listar());
	}
	
	@GetMapping(value = "/buscar1/{cozinhaId}", produces = {MediaType.APPLICATION_JSON_VALUE
			, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
		System.out.println("param: " + id);
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
//		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//		return ResponseEntity.status(HttpStatus.OK).build();
//		return ResponseEntity.ok(cozinha);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "http://localhost:8087/cozinhas/buscar2/2");
		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(cozinha);
		
	}
	
	@GetMapping(value = "/buscar2/{cozinhaId}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Cozinha> outraFormaBuscar(@PathVariable Long cozinhaId) {
		System.out.println("param: " + cozinhaId);
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if( cozinha != null ) {
			return ResponseEntity.ok(cozinha);
//			return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		}

		return ResponseEntity.notFound().build();
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cozinha> adicionar (@RequestBody Cozinha cozinha) {

		System.out.println(cozinha);
		cozinha = cozinhaRepository.salvar(cozinha);

		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId
			, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		if( cozinhaAtual != null ) {
			// cozinhaAtual.setNome(cozinha.getNome());
			// cozinhaAtual.setTwitter(cozinha.getTwitter());
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = cozinhaRepository.salvar(cozinhaAtual);
			return ResponseEntity.ok().body(cozinhaAtual);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {

		try {
			Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
			if( cozinha != null ) {
				cozinhaRepository.remover(cozinha);
				// ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	
}
