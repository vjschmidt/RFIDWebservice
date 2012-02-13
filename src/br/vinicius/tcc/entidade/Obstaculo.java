package br.vinicius.tcc.entidade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"tipo", "posicao", "dimensoes"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Obstaculo {

	private String tipo;
	private Posicao posicao;
	private Dimensao dimensoes;
	
	public Obstaculo() {

	}

	public Obstaculo(String tipo, Posicao posicao, Dimensao dimensoes) {
		this.tipo = tipo;
		this.posicao = posicao;
		this.dimensoes = dimensoes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Posicao getPosicao() {
		return posicao;
	}
	
	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}
	
	public Dimensao getDimensoes() {
		return dimensoes;
	}
	
	public void setDimensoes(Dimensao dimensoes) {
		this.dimensoes = dimensoes;
	}
	
}
