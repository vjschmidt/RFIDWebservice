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

	public Antena(String modelo, List<Posicao> posicoes) {
		this.modelo = modelo;
		this.posicoes = posicoes;
	}

	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public List<Posicao> getPosicoes() {
		return posicoes;
	}
	
	public void setPosicoes(List<Posicao> posicoes) {
		this.posicoes = posicoes;
	}
	
}
