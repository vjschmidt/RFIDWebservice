package br.vinicius.tcc.simulador.antiColisao.interrogador;

public abstract class AveragesAloha extends Interrogador {
	protected float averageTotalRounds = 0; 
	protected float averageTotalSlots = 0;
	protected int totalRound = 0;
	protected int totalSlot = 0;

	public float getAverageTotalRounds() {
		return averageTotalRounds;
	}
	public float getAverageTotalSlots() {
		return averageTotalSlots;
	}
	
}
