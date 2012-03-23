package br.vinicius.tcc.simulador.antiColisao.manager;

import br.vinicius.tcc.simulador.antiColisao.tag.AlohaEtiqueta;

public class LstStandByTagManager extends SlotTimeManager {
	public LstStandByTagManager(int initRound, int total) {
		AlohaEtiqueta etiqueta;
		for (int x = 0; x < total; x++){
			etiqueta = new AlohaEtiqueta (initRound);
			tagRFID.addEtiqueta(etiqueta);
		}    
	}
	
	public String verificarSlotEnvio() {
		returns = "";
		slotCont = 0;
		if (tagRFID.tamanho() > 0){
			for (int pos = 0; pos < tagRFID.tamanho(); pos++){
				if (tagRFID.getTagPos(pos).equals(slot)){
					slotCont++;
					tagPos = pos;
					returns += "| " + getSUID(tagRFID.getTagSN(pos)) + " | ";
				}
			}
			if (slotCont == 0) {
				totalVazios ++;
				vazioCont++;
				returns = "No tag replied";
			} else if (slotCont == 1){
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
	
}