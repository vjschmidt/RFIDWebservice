package br.vinicius.tcc.simulador.antiColisao.criadorImpressor;

import br.vinicius.tcc.simulador.antiColisao.tag.AlohaEtiqueta;

public class FstTagManager extends TagManager {
	private int vazioCont = 0;
	private int colisaoCont = 0;
	private int tagsEncontradas = 0;
	
	public FstTagManager(int valor) {
		AlohaEtiqueta etiqueta;
		for (int pos = 0; pos < valor; pos++) {
			etiqueta = new AlohaEtiqueta(16);
			tagRFID.addEtiqueta(etiqueta);
		}    
	}
	
	public void novoRound(int initRound) {
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			tagRFID.update (pos, 1 + (int)(Math.random()*(initRound)));
			tagsEncontradas = 0;
			vazioCont = 0;
			colisaoCont = 0;
		}
	}
	
	public String etiqueta(int slot) {
		returns = "";
		slotCont = 0;
		if (tagRFID.tamanho() > 0) {
			for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
				if (tagRFID.getTagPos(pos).equals(slot)) {
					slotCont++;
					tagPos = pos;
					returns += "| " + getSUID(tagRFID.getTagSN(pos)) + " | ";
				}
			}
			if (slotCont == 0) {
				totalVazios++;
				vazioCont++;
				returns = "No tag replied";
			} else if (slotCont == 1) {
				tagsEncontradas++;
				tagRFID.quiet(tagPos);
			} else if (slotCont > 1) {
				tagsEncontradas += 2;
				totalColisao++;
				colisaoCont++;
			}
		} else {
			totalVazios++;
			returns = "All tags already replied";
		}
		return returns;
	}
	
	public String getSUID(String tagUID) {
		return tagUID.substring(2, 4) + tagUID.substring(8, 16);
	}
	
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