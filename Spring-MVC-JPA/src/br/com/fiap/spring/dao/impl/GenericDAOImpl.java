package br.com.fiap.spring.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fiap.spring.dao.GenericDAO;
import br.com.fiap.spring.exception.RegistroNaoEncontradoException;

public abstract class GenericDAOImpl <Tabela, Chave>
					implements GenericDAO<Tabela, Chave>{

	@PersistenceContext
	protected EntityManager em;
	
	private Class<Tabela> clazz;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		clazz = (Class<Tabela>)
			((ParameterizedType)
				getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
	}

	@Override
	public void cadastrar(Tabela tabela) {
		em.persist(tabela);
	}

	@Override
	public void atualizar(Tabela tabela) {
		em.merge(tabela);
	}

	@Override
	public void excluir(Chave codigo) throws RegistroNaoEncontradoException {
		Tabela t  = buscar(codigo);
		if (t == null) {
			throw new RegistroNaoEncontradoException("Registro não encontrado");
		}
		em.remove(t);
	}

	@Override
	public Tabela buscar(Chave codigo) {
		return em.find(clazz, codigo);
	}
	
	@Override
	public List<Tabela> listar(){
		return em.createQuery("from  " + clazz.getName()+ " order by " + clazz.getDeclaredFields()[0].getName(),clazz)
				.getResultList();
	}
	

}







