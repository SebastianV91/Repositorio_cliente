package com.api.cliente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cliente.model.Cliente;
import com.api.cliente.service.IClienteService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.getClientes();
	}
	
	@GetMapping("/clientesProcesados")
	public List<Cliente> clientesProcesados(){
		return clienteService.clientesProcesados();
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cliente cliente = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			cliente = clienteService.findClienteById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de acceso a la base de datos");
			response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente no se encuentra en el sistema");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		
		Cliente clienteNuevo = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			clienteNuevo = clienteService.saveCliente(cliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente creado con exito");
		response.put("cliente", clienteNuevo);
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/procesar/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		
		Cliente clien = clienteService.findClienteById(id);
		Cliente clienteActualizado = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(clien == null) {
			response.put("Mensaje", "No se ha podido actualizar cliente porque no existe en el sistema.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			clien.setEstado(1);
			
			clienteActualizado = clienteService.saveCliente(clien);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente");
			response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Cliente actualizado con exito");
		response.put("clienteActualizado", clienteActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	
}
