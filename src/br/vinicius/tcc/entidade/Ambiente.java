package br.vinicius.tcc.entidade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"tipo", "dimensoes"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Ambiente {

	private String tipo;
	private Dimensao dimensoes;
	
	public Ambiente() {

	}
	
	public Ambiente(String tipo, Dimensao dimensoes) {
		this.tipo = tipo;
		this.dimensoes = dimensoes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Dimensao getDimensoes() {
		return dimensoes;
	}
	
	public void setDimensoes(Dimensao dimensoes) {
		this.dimensoes = dimensoes;
	}
	
}
