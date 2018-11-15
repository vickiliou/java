package br.com.fiap.spring.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@SequenceGenerator(name="genero",allocationSize=1)
public class Genero {

	@Id
	@GeneratedValue(generator="genero",strategy=GenerationType.SEQUENCE)
	private int codigo;
	
	@Size(min=5)
	@NotBlank(message="O nome é obrigatório")
	private String nome;
	
	@OneToMany(mappedBy="genero")
	private List<Jogo> jogos;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	
}
