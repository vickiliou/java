package br.com.fiap.spring.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.fiap.spring.dao.GeneroDAO;
import br.com.fiap.spring.model.Genero;

@Repository
public class GeneroDAOImpl extends GenericDAOImpl<Genero, Integer> implements GeneroDAO{

}
