package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente,Integer>{

	//List<Cliente> listar();
	
	long contarQuantidadeReserva(int codigo);
	
	List<Cliente> buscarPorEstados(List<String> estados);
	
	List<Cliente> buscar(String nome, String cidade);
	
	List<Cliente> buscarPorNome(String nome);
	
	List<Cliente> buscarPorEstado(String estado);
	
	List<Cliente> buscarPorDiasReserva(int dias);
	
	long contarTotal();
	
}