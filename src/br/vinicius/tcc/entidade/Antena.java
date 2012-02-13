package br.vinicius.tcc.entidade;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"modelo", "antena"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Antena {
	private String modelo;
	private List<Posicao> antena;
	
	public Antena() {

	}

	public Antena(String modelo, List<Posicao> antena) {
		this.modelo = modelo;
		this.antena = antena;
	}

	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public List<Posicao> getAntena() {
		return antena;
	}
	
	public void setAntena(List<Posicao> antena) {
		this.antena = antena;
	}
	
}
