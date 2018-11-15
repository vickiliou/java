package br.com.fiap.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

public class PacoteDAOImpl extends GenericDAOImpl<Pacote,Integer> implements PacoteDAO{

	public PacoteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Pacote> buscarPorTransporte(Transporte transporte) {
		TypedQuery<Pacote> query = em.createQuery(
			"from Pacote p where p.transporte = :t",Pacote.class);
		query.setParameter("t", transporte);
		return query.getResultList();
	}

	@Override
	public List<Pacote> buscarPorDatas(Calendar inicio, Calendar fim) {
		return em.createQuery("from Pacote p where p.dataSaida between :i and :f",Pacote.class)
				.setParameter("i", inicio)
				.setParameter("f", fim)
				.getResultList();
	}

	@Override
	public double calcularMediaPreco() {
		return em.createQuery("select avg(p.preco) from Pacote p",Double.class)
				.getSingleResult();
	}

	@Override
	public List<Pacote> buscarPorData(Calendar data) {
		return em.createNamedQuery("Pacote.porData",Pacote.class)
				.setParameter("data", data)
				.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pacote> buscarPorDescricao(String descricao) {		
		return em.createNativeQuery("SELECT * FROM PACOTE "
				+ "WHERE DESCRICAO like :d",Pacote.class)
				.setParameter("d", "%" + descricao + "%")
				.getResultList();
	}

}