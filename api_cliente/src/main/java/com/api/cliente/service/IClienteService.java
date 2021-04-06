package com.api.cliente.service;

import java.util.List;

import com.api.cliente.model.Cliente;

public interface IClienteService {

	List<Cliente> getClientes();
	
	Cliente findClienteById(Long id);

	Cliente saveCliente(Cliente cliente);
	
}
