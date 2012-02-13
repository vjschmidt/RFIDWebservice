package br.vinicius.tcc.entidade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"tamanhoX", "tamanhoY", "tamanhoZ"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Dimensao {

	private float tamanhoX;
	private float tamanhoY;
	private float tamanhoZ;
	
	public Dimensao() {

	}
	
	public Dimensao(float tamanhoX, float tamanhoY, float tamanhoZ) {
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
		this.tamanhoZ = tamanhoZ;
	}
	
	public float getTamanhoX() {
		return tamanhoX;
	}
	
	public void setTamanhoX(float tamanhoX) {
		this.tamanhoX = tamanhoX;
	}
	
	public float getTamanhoY() {
		return tamanhoY;
	}
	
	public void setTamanhoY(float tamanhoY) {
		this.tamanhoY = tamanhoY;
	}
	
	public float getTamanhoZ() {
		return tamanhoZ;
	}
	
	public void setTamanhoZ(float tamanhoZ) {
		this.tamanhoZ = tamanhoZ;
	}
	
}
