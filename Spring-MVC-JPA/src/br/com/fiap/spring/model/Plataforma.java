package br.com.fiap.spring.model;

public enum Plataforma {

	PS4("Play Station 4"), XBOX("Xbox"), PC("Computador");
	
	private final String label;
	
	private Plataforma(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
