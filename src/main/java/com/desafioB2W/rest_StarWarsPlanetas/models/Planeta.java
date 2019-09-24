package com.desafioB2W.rest_StarWarsPlanetas.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

//import lombok.Data;


//@Data
public class Planeta {
	@Id
	private ObjectId _id;
	
	@Indexed(unique = true)
    @NotBlank(message="Nome não definido")
	private String nome;
	
	@NotBlank(message="Clima não definido")
	private String clima;
	
	@NotBlank(message="Terreno não definido")
	private String terreno;
	
	@NotNull(message="Quantidade de aparição em filmes não definida")
    @Min(0)
	private int filmes;
	
	//construtores
	public Planeta() {}
	
	public Planeta(ObjectId _id, String nome, String clima, String terreno, int filmes) {
		this._id = _id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.filmes = filmes;
	}

	
	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getFilmes() {
		return filmes;
	}

	public void setFilmes(int filmes) {
		this.filmes = filmes;
	}
	
	

}
