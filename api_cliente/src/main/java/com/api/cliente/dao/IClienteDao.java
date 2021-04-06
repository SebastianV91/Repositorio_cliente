package com.api.cliente.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cliente.model.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	

}
