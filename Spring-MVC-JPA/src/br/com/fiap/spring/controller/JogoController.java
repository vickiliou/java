package br.com.fiap.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.spring.dao.GeneroDAO;
import br.com.fiap.spring.dao.JogoDAO;
import br.com.fiap.spring.exception.RegistroNaoEncontradoException;
import br.com.fiap.spring.model.Genero;
import br.com.fiap.spring.model.Jogo;
import br.com.fiap.spring.model.Plataforma;

@Controller
@RequestMapping("jogo")
public class JogoController {

	@Autowired
	private JogoDAO jogoDao;
	
	@Autowired
	private GeneroDAO generoDao;
	
	@GetMapping("cadastrar")
	public ModelAndView cadastrar(Jogo jogo) {
		return new ModelAndView("jogo/cadastro")
				.addObject("generos", generoDao.listar())
				.addObject("plataformas", Plataforma.values());
	}

	@Transactional
	@PostMapping("cadastrar")
	public ModelAndView cadastrar(@Valid Jogo jogo, BindingResult result, RedirectAttributes r) {
		if (result.hasErrors()) {
			cadastrar(jogo);
		}
		jogoDao.cadastrar(jogo);
		r.addFlashAttribute("msg", "Cadastrado com sucesso");
		return new ModelAndView("redirect:/jogo/cadastrar");
	}
	
	@GetMapping("listar")
	public ModelAndView listar() {
		return new ModelAndView("jogo/lista")
				.addObject("jogos", jogoDao.listar())
				.addObject("generos", generoDao.listar())
				.addObject("genero", new Genero());
	}
	
	@GetMapping("editar/{id}")
	public ModelAndView editar(@PathVariable("id") int codigo) {
		return new ModelAndView("jogo/edicao")
				.addObject("jogo", jogoDao.buscar(codigo))
				.addObject("generos", generoDao.listar())
				.addObject("plataformas", Plataforma.values());
	}
	
	@Transactional
	@PostMapping("editar")
	public ModelAndView editar(@Valid Jogo jogo, BindingResult result, RedirectAttributes r) {
		if (result.hasErrors()) {
			new ModelAndView("jogo/edicao")
			.addObject("jogo", jogo)
			.addObject("generos", generoDao.listar())
			.addObject("plataformas", Plataforma.values());
		}
		jogoDao.atualizar(jogo);
		r.addFlashAttribute("msg", "Atualizado com sucesso");
		return new ModelAndView("redirect:/jogo/listar");
	}
	
	@Transactional
	@PostMapping("remover")
	public String remover(int codigo, RedirectAttributes r) {
		try {
			jogoDao.excluir(codigo);
			r.addFlashAttribute("msg","Removido com sucesso!");
		} catch (RegistroNaoEncontradoException e) {
			e.printStackTrace();
		}
		return "redirect:/jogo/listar";
	}
	
	@Transactional
	@PostMapping("disponibilizar")
	public String disponibilizar(int codigo, RedirectAttributes r) {
		jogoDao.disponibilizar(codigo);
		r.addFlashAttribute("msg", "Disponibilizado com sucesso!");
		return "redirect:/jogo/listar";
	}
	
	@GetMapping("buscarPorNome")
	public ModelAndView bucsarPorNome(String nome) {
		return new ModelAndView("jogo/lista")
				.addObject("jogos", jogoDao.buscarPorNome(nome))
				.addObject("generos", generoDao.listar())
				.addObject("genero", new Genero());
	}
	
	@GetMapping("buscarPorGenero")
	public ModelAndView buscarPorGenero(int codigo) {
		return new ModelAndView("jogo/lista")
				.addObject("jogos", jogoDao.buscarPorGenero(codigo))
				.addObject("generos", generoDao.listar()).addObject("genero", new Genero());
	}
	
}
