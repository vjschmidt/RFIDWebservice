package br.vinicius.tcc.simulador.antiColisao;

import java.util.ArrayList;
import java.util.List;

import br.vinicius.tcc.simulador.antiColisao.tag.Etiqueta;
public class ControladorDeEtiquetasDoAmbiente {
	private List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	
	public void addEtiqueta(Etiqueta etiqueta) {
		this.etiquetas.add(etiqueta);
	}
	
	public void update(int pos, Integer contador) {
		this.etiquetas.get(pos).setContador(contador);
	}
	
	public Integer getTagPos(int pos){
		return this.etiquetas.get(pos).getContador();
	}

	public String getTagSN(int pos){
		return this.etiquetas.get(pos).getSerialNumber();
	}

	public void quiet(int pos) {
		this.etiquetas.remove(pos);
	}

	public int tamanho() {
		return this.etiquetas.size();
	}
}