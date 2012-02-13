package br.vinicius.tcc.entidade;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"velocidadeDeslocamento", "modeloEtiqueta", "materialEtiquetado", "posicaoInicialEtiqueta", "posicaoFinalEtiqueta", "antenas", "ambiente", "obstaculos"})
@XmlAccessorType(XmlAccessType.FIELD)
public class DadosAvancados {

	private float velocidadeDeslocamento;
	private String modeloEtiqueta;
	private String materialEtiquetado;
	private Posicao posicaoInicialEtiqueta;
	private Posicao posicaoFinalEtiqueta;
	private Antena antenas;
	private Ambiente ambiente;
	private List<Obstaculo> obstaculos;
	
	public DadosAvancados() {

	}

	public DadosAvancados(float velocidadeDeslocamento, String modeloEtiqueta,
			String materialEtiquetado, Posicao posicaoInicialEtiqueta,
			Posicao posicaoFinalEtiqueta, Antena antenas, Ambiente ambiente,
			List<Obstaculo> obstaculos) {
		this.velocidadeDeslocamento = velocidadeDeslocamento;
		this.modeloEtiqueta = modeloEtiqueta;
		this.materialEtiquetado = materialEtiquetado;
		this.posicaoInicialEtiqueta = posicaoInicialEtiqueta;
		this.posicaoFinalEtiqueta = posicaoFinalEtiqueta;
		this.antenas = antenas;
		this.ambiente = ambiente;
		this.obstaculos = obstaculos;
	}

	public float getVelocidadeDeslocamento() {
		return velocidadeDeslocamento;
	}
	
	public void setVelocidadeDeslocamento(float velocidadeDeslocamento) {
		this.velocidadeDeslocamento = velocidadeDeslocamento;
	}
	
	public String getModeloEtiqueta() {
		return modeloEtiqueta;
	}
	
	public void setModeloEtiqueta(String modeloEtiqueta) {
		this.modeloEtiqueta = modeloEtiqueta;
	}
	
	public String getMaterialEtiquetado() {
		return materialEtiquetado;
	}
	
	public void setMaterialEtiquetado(String materialEtiquetado) {
		this.materialEtiquetado = materialEtiquetado;
	}
	
	public Posicao getPosicaoInicialEtiqueta() {
		return posicaoInicialEtiqueta;
	}
	
	public void setPosicaoInicialEtiqueta(Posicao posicaoInicialEtiqueta) {
		this.posicaoInicialEtiqueta = posicaoInicialEtiqueta;
	}
	
	public Posicao getPosicaoFinalEtiqueta() {
		return posicaoFinalEtiqueta;
	}
	
	public void setPosicaoFinalEtiqueta(Posicao posicaoFinalEtiqueta) {
		this.posicaoFinalEtiqueta = posicaoFinalEtiqueta;
	}
	
	public Antena getAntenas() {
		return antenas;
	}
	
	public void setAntenas(Antena antenas) {
		this.antenas = antenas;
	}
	
	public Ambiente getAmbiente() {
		return ambiente;
	}
	
	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}
	
	public List<Obstaculo> getObstaculos() {
		return obstaculos;
	}
	
	public void setObstaculos(List<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}
	
}
