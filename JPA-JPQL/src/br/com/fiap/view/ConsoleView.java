package br.com.fiap.view;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;

public class ConsoleView {

	public static void main(String[] args) {
		//Testar a listagem de cliente
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		ClienteDAO clienteDao = new ClienteDAOImpl(em);
		
		List<Cliente> lista = clienteDao.listar();
		//Exibir os clientes
		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome());
		}
		
		System.out.println("BUSCAR POR NOME");
		lista = clienteDao.buscarPorNome("thiago");
		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome());
		}
		
		System.out.println("BUSCAR POR ESTADO");
		lista = clienteDao.buscarPorEstado("BA");
		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome());
		}
		
		System.out.println("BUSCAR POR QTD DIAS RESERVA");
		lista = clienteDao.buscarPorDiasReserva(10);
		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome());
		}
		
		System.out.println("TOTAL DE CLIENTES: ");
		System.out.println(clienteDao.contarTotal());
		
		System.out.println("BUSCAR CLIENTE POR NOME E CIDADE");
		lista = clienteDao.buscar("a", "a");
		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome() + " " + 
					cliente.getEndereco().getCidade().getNome());			
		}
		
		
		System.out.println("BUSCAR CLIENTE POR ESTADOS");
		List<String> estados = new ArrayList<>();
		estados.add("SP");
		estados.add("RJ");
		estados.add("BA");
		estados.add("MG");
		
		lista = clienteDao.buscarPorEstados(estados);
		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome() + " " + 
					cliente.getEndereco().getCidade().getUf());			
		}
		
		System.out.println("Reservas: " +
						clienteDao.contarQuantidadeReserva(1));
		
		em.close();
		fabrica.close();
	}

}