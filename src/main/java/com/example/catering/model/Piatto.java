package com.example.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String descrizione;

	@ManyToOne
	private Buffet buffet;

	
	@OneToMany(mappedBy = "piatto", cascade = CascadeType.ALL)
	private List<Ingrediente> ingredienti;
	
	public Piatto() {
		this.ingredienti = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Buffet getBuffet() {
		return this.buffet;
	}
	
	public Long getIdBuffet() {
		return this.buffet.getId();
	}

	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

}
