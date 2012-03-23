package br.vinicius.tcc.simulador.antiColisao.manager;

public abstract class SlotTimeManager extends SlotManager {
	protected int slot;
	
	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public void novoRound(int initRound) {
		for (int pos = 0; pos < tagRFID.tamanho(); pos++) {
			tagRFID.update (pos, 1 + (int)(Math.random()*(initRound)));
			tagsEncontradas = 0;
			vazioCont = 0;
			colisaoCont = 0;
		}
	}
	
	public String getSUID(String tagUID) {
		return tagUID.substring(2, 4) + tagUID.substring(8, 16);
	}
}
