package br.vinicius.tcc.simulador.antiColisao.criadorImpressor;

import br.vinicius.tcc.simulador.antiColisao.tag.AlohaEtiqueta;

public class LstTagManager extends TagManager {
	public LstTagManager(int initRound, int valor) {
		AlohaEtiqueta etiqueta;
		for (int x = 0; x < valor; x++) {
			etiqueta = new AlohaEtiqueta (initRound);
			tagRFID.addEtiqueta(etiqueta);
		}    
	}
	
	public void novoRound(int initRound) {
		for (int x = 0; x < tagRFID.tamanho(); x++) {
			tagRFID.update (x, 1+(int)(Math.random()*(initRound)));
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
				returns = "No tag replied";
			} else if (slotCont == 1) {
				tagRFID.quiet(tagPos);
			} else if (slotCont > 1) {
				totalColisao++;
			}
		} else {
			totalVazios++;
			returns = "All tags already replied";
		}
		return returns;
	}
	
	public String getSUID (String tagUID) {
		return tagUID.substring(2, 4) + tagUID.substring(8, 16);
	}
}