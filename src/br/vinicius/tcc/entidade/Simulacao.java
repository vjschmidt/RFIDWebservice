package br.vinicius.tcc.entidade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "simulacao", namespace = "http://www.testes.example/RFID")
@XmlType(propOrder = {"totalEtiquetas", "protocolo", "totalLeitoras", "totalAntenas", "avancado"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Simulacao {

	private int totalEtiquetas;
	private String protocolo;
	private int totalLeitoras;
	private int totalAntenas;
	private DadosAvancados avancado;
	
	public Simulacao() {

	}
	
	public Simulacao(int totalEtiquetas, String protocolo, int totalLeitoras,
			int totalAntenas, DadosAvancados avancado) {
		this.totalEtiquetas = totalEtiquetas;
		this.protocolo = protocolo;
		this.totalLeitoras = totalLeitoras;
		this.totalAntenas = totalAntenas;
		this.avancado = avancado;
	}

	public int getTotalEtiquetas() {
		return totalEtiquetas;
	}
	
	public void setTotalEtiquetas(int totalEtiquetas) {
		this.totalEtiquetas = totalEtiquetas;
	}
	
	public String getProtocolo() {
		return protocolo;
	}
	
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	public int getTotalLeitoras() {
		return totalLeitoras;
	}
	
	public void setTotalLeitoras(int totalLeitoras) {
		this.totalLeitoras = totalLeitoras;
	}
	
	public int getTotalAntenas() {
		return totalAntenas;
	}
	
	public void setTotalAntenas(int totalAntenas) {
		this.totalAntenas = totalAntenas;
	}
	
	public DadosAvancados getAvancado() {
		return avancado;
	}
	
	public void setAvancado(DadosAvancados avancado) {
		this.avancado = avancado;
	}
	
}
