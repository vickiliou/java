package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.dao.TransporteDAO;
import br.com.fiap.dao.impl.PacoteDAOImpl;
import br.com.fiap.dao.impl.TransporteDAOImpl;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

public class PacoteTeste {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();

		PacoteDAO pacoteDao = new PacoteDAOImpl(em);
		TransporteDAO transporteDao = new TransporteDAOImpl(em);
		
		Transporte transporte = transporteDao.pesquisar(1);
		List<Pacote> lista = pacoteDao.buscarPorTransporte(transporte);
		
		for (Pacote pacote : lista) {
			System.out.println(pacote.getDescricao());
			System.out.println(pacote.getPreco());
		}
		
		
		System.out.println("BUSCAR PACOTE POR DATAS");
		Calendar inicio = new GregorianCalendar(2016, Calendar.JANUARY, 2);
		Calendar fim = new GregorianCalendar(2018,Calendar.JANUARY,2);
		lista = pacoteDao.buscarPorDatas(inicio, fim);
		for (Pacote pacote : lista) {
			System.out.println(pacote.getDescricao());
			System.out.println(pacote.getDataSaida().getTime());
		}
		
		System.out.println("CALCULAR A MEDIA DE PREÇO");
		System.out.println("Média é :" + pacoteDao.calcularMediaPreco());
		
		System.out.println("BUSCAR POR DATA");
		lista = pacoteDao.buscarPorData(
				new GregorianCalendar(2017,Calendar.JANUARY,1));
		for (Pacote pacote : lista) {
			System.out.println(pacote.getDescricao());
		}
		
		//Teste da Query Nativa
		lista = pacoteDao.buscarPorDescricao("a");
		System.out.println("BUSCAR POR DESCRIÇÃO");
		for (Pacote pacote : lista) {
			System.out.println(pacote.getDescricao());
		}
		
		em.close();
		fabrica.close();
	}
	
}
