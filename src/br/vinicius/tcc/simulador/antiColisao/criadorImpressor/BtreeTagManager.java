package br.vinicius.tcc.simulador.antiColisao.criadorImpressor;

import br.vinicius.tcc.simulador.antiColisao.tag.BtreeEtiqueta;

public class BtreeTagManager extends TagManager {
	public BtreeTagManager(int valor) {
		BtreeEtiqueta etiqueta;
		for (int x=0; x<valor; x++) {
			etiqueta = new BtreeEtiqueta();
			tagRFID.addEtiqueta(etiqueta);
		}    
	}
	
	public void fail() {
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			if (tagRFID.getTagPos(pos).equals(0)) {
				if ((int)Math.random()*(2) == 1) {
					tagRFID.update(pos, tagRFID.getTagPos(pos).intValue() + 1);
				}
			} else {
				tagRFID.update(pos, tagRFID.getTagPos(pos).intValue() + 1);
			}
		}
	}
	
	public void success() {
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			tagRFID.update (pos, tagRFID.getTagPos(pos).intValue() - 1);
		}
	}
	
	public String etiqueta() {
		returns = "";
		slotCont = 0;
		if (tagRFID.tamanho() > 0) {
			for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
				if (tagRFID.getTagPos(pos).equals(0)) {
					slotCont++;
					tagPos = pos;
					returns += "| " + tagRFID.getTagSN(pos) + " | ";
				}
			}
			if (slotCont == 1) {
				tagRFID.quiet(tagPos);
			} else if (slotCont == 0) {
				totalVazios++;
				returns = "No tag replied";
			} else if (slotCont > 1) {
				totalColisao++;
			}	
		}
		return returns;
	}
}