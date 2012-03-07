package br.vinicius.tcc.entidade;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"modelo", "posicoes"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Antena {
	private String modelo;
	private List<Posicao> posicoes;
	
	public Antena() {

	}

	public Antena(String modelo, List<Posicao> antena) {
		this.modelo = modelo;
		this.posicoes = antena;
	}

	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public List<Posicao> getAntena() {
		return posicoes;
	}
	
	public void setAntena(List<Posicao> antena) {
		this.posicoes = antena;
	}
	
}
