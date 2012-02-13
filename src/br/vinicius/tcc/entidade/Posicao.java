package br.vinicius.tcc.entidade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"posicaoX", "posicaoY"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Posicao {

	private float posicaoX;
	private float posicaoY;
	
	public Posicao() {

	}

	public Posicao(float posicaoX, float posicaoY) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}

	public float getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(float posicaoX) {
		this.posicaoX = posicaoX;
	}
	
	public float getPosicaoY() {
		return posicaoY;
	}
	
	public void setPosicaoY(float posicaoY) {
		this.posicaoY = posicaoY;
	}
	
}
