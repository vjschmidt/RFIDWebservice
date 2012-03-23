package br.vinicius.tcc.simulador.antiColisao.manager;

import br.vinicius.tcc.simulador.antiColisao.ControladorDeEtiquetasDoAmbiente;

public abstract class TagManager {
	protected ControladorDeEtiquetasDoAmbiente tagRFID = new ControladorDeEtiquetasDoAmbiente ();
	protected int tagPos = 0;
	protected int slotCont = 0;
	protected int totalColisao = 0;
	protected int totalVazios = 0;
	protected String returns = "";
	
	public int getTagPos() {
		return tagPos;
	}

	public int getSlotCont() {
		return slotCont;
	}

	public int getTotalColisao() {
		return totalColisao;
	}

	public int getTotalVazios() {
		return totalVazios;
	}
	
	public int tamanho() {
		return tagRFID.tamanho();
	}
	
	public abstract String verificarSlotEnvio();
}