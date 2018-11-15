package br.com.fiap.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fiap.spring.dao.JogoDAO;
import br.com.fiap.spring.model.Jogo;

@Repository
public class JogoDAOImpl extends GenericDAOImpl<Jogo, Integer> implements JogoDAO{

	@Override
	public void disponibilizar(int codigo) {
		Jogo jogo = buscar(codigo);
		jogo.setDisponivel(true);
		atualizar(jogo);
	}

	@Override
	public List<Jogo> buscarPorGenero(int genero) {
		return em.createQuery("from Jogo where genero.codigo = :codigo",Jogo.class)
				.setParameter("codigo", genero).getResultList();
	}

	@Override
	public List<Jogo> buscarPorNome(String nome) {
		return em.createQuery("from Jogo where upper(nome) like upper(:nome)",Jogo.class).
				setParameter("nome", "%"+ nome + "%").getResultList();
	}

	


}
