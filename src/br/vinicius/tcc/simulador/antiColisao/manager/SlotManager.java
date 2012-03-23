package br.vinicius.tcc.simulador.antiColisao.manager;

public abstract class SlotManager extends TagManager {
	protected int vazioCont = 0;
	protected int colisaoCont = 0;
	protected int tagsEncontradas = 0;
	
	public int getVazioCont() {
		return vazioCont;
	}
	
	public int getColisaoCont() {
		return colisaoCont;
	}
	
	public int getTagsEncontradas() {
		return tagsEncontradas;
	}

}
