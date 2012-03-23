package br.vinicius.tcc.simulador.antiColisao.manager;

import br.vinicius.tcc.simulador.antiColisao.tag.QEtiqueta;

public class QTagManager extends SlotManager {
	private int round = 0;
	private int q = 0;
	
	private float qfp = 0;
	private float c = 0;
	
	public QTagManager(int round, int total) {
		QEtiqueta etiqueta;
		for (int x = 0; x < total; x++){
			etiqueta = new QEtiqueta (round);
			tagRFID.addEtiqueta(etiqueta);
		}    
	}
	
	public void query() {
		q = Math.round(qfp);
		round = 1;
		if (q != 0){
			for (int x = 1; x <= q; x++) {
				round *= 2;
			}
		}
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			tagRFID.update (pos, (int)(Math.random()*(round)));
			tagsEncontradas = 0;
			vazioCont = 0;
			colisaoCont = 0;
		}
	}
	
	public void queryAdjust(String operation) {
		if (q == 0 && operation.equals("011")) {
			operation = "000";
		} else if (q == 15 && operation.equals("110")) {
			operation="000";
		}
		if (operation.equals("110")) {
			q++;
		} else if (operation.equals("011")) {
			q--;
		}
		round = 1;
		if (q != 0) {
			for (int x = 1; x <= q; x++) {
				round *= 2;
			}
		}
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			tagRFID.update (pos, (int)(Math.random()*(round)));
		}
		tagsEncontradas = 0;
		vazioCont = 0;
		colisaoCont = 0;
	}
	
	public void queryRep() {
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			tagRFID.update (pos, tagRFID.getTagPos(pos).intValue() - 1);
		}
	}
	
	public void collision() {
		for (int pos = 0; pos < tagRFID.tamanho(); pos++){
			if (tagRFID.getTagPos(pos).equals(0)){
				tagRFID.update (pos, 32767);
			}
		}
	}
	
	public void c(float qfp) {
		c = 0.8f / Math.round(qfp);
		if (c > 0.5) {
			c = 0.5f;
		} else if (c < 0.1) {
			c = 0.1f;
		}
	}
	
	public void setQfp(float qfp) {
		this.qfp = qfp;
	}
	
	public void setQ(int q) {
		this.q = q;
	}
	
	public int getRound() {
		return round;
	}
	
	public float getC() {
		return c;
	}
	
	public String verificarSlotEnvio() {
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
				tagsEncontradas++;
				tagRFID.quiet(tagPos);
			} else if (slotCont == 0) {
				totalVazios++;
				vazioCont++;
				c(qfp);
				qfp = Math.max(0, qfp - c);
				returns = "No tag replied";
			} else if (slotCont > 1) {
				totalColisao++;
				colisaoCont++;
				tagsEncontradas += 2;
				collision();
				c(qfp);
				qfp = Math.min(15, qfp + c);
			}
		} else {
			totalVazios++;
			vazioCont++;
			returns = "All tags already replied";
		}
		return returns;
	}
	
}