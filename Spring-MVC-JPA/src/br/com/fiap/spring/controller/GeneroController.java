package br.com.fiap.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.spring.dao.GeneroDAO;
import br.com.fiap.spring.model.Genero;

@Controller
@RequestMapping("genero")
public class GeneroController {

	@Autowired
	private GeneroDAO dao;

	@GetMapping("cadastrar")
	public String cadastrar(Genero genero) {
		return "genero/cadastro";
	}

	@Transactional
	@PostMapping("cadastrar")
	public String cadastrar(@Valid Genero genero, BindingResult result, RedirectAttributes r) {
		if (result.hasErrors()) {
			return cadastrar(genero);
		}
		
		dao.cadastrar(genero);
		r.addFlashAttribute("msg", "Cadastrado com sucesso");
		return "redirect:/genero/cadastrar";
	}

}
