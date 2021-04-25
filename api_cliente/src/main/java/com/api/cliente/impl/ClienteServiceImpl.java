package com.api.cliente.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.api.cliente.dao.IClienteDao;
import com.api.cliente.model.Cliente;
import com.api.cliente.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	private JdbcTemplate jdbcTemplate;
	
	public ClienteServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Cliente> getClientes() {
		
		return clienteDao.findAll();
	}

	@Override
	public Cliente saveCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	public Cliente findClienteById(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public List<Cliente> clientesProcesados() {
		
		String sql = "SELECT * FROM CLIENTES WHERE ESTADO = 1";
		
		List<Cliente> listClientes = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(Cliente.class));
		
		return listClientes;
	}

	
}
