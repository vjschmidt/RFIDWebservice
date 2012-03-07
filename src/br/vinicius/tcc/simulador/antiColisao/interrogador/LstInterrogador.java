package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.criadorImpressor.LstTagManager;

public class LstInterrogador extends Interrogador {
	private float averageTotalRounds = 0;
	private float averageTotalSlots = 0;
	private int initRound = 0;
	private int totalRound = 0;
	private int totalSlot = 0;
	
	public LstInterrogador(int[] etiquetas, int roundSize, int division, float tagBitrate){
		this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		int totalInterrogadores = etiquetas[1] == 0 ? 1 : 2;
		initRound = roundSize;
		log.info("-------------------------|LST|-------------------------");
		log.info("Tags in the environment: " + (this.etiquetas[0] + this.etiquetas[1]));
		log.info("Initial Round Size: " + roundSize);
		log.info("-------------------------------------------------------");
		execute(totalInterrogadores, division, tagBitrate);
	}
	
	public void execute(int totalInterrogadores, int division, float tagBitrate){
		tagBitrate *= 1000;
		int valor = 0;
		int initRound = 0;
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++){
			averageTotalRounds = 0;
			averageTotalSlots = 0;
			averageTotalColision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			for (int average = 1; average <= division; average++) {
				initRound = this.initRound;
				totalRound = 0;
				totalSlot = 0;
				valor = this.etiquetas[interrogatorNumber-1];
				LstTagManager tag = new LstTagManager(initRound, valor);
				do {
					totalRound++;
					log.info("Round #" + totalRound);
					for (int nextSlot = 1; nextSlot <= initRound; nextSlot++) {
						totalSlot += 1;
						log.info("Slot: " + nextSlot);
						log.info("Tags that replied:");
						log.info(tag.etiqueta(nextSlot));
					}
					if (initRound == 1) {
						initRound = 8;
					} else if (initRound != 256) {
						initRound = initRound * 2;
					}
					tag.novoRound(initRound);
				} while(tag.tamanho() > 0);
				log.info("-------------------------------------------------------");
				printMethod(interrogatorNumber, valor, tag.getTotalColisao(), tag.getTotalVazios(), tagBitrate);
				averageTotalRounds += totalRound;
				averageTotalSlots += totalSlot;
				averageTotalColision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalSlot * 0.01;
				averageTotalReadTime += (64 / tagBitrate) * totalSlot;
			}
			printAverage(valor, division, tagBitrate);
		}
	}
	
	public void printMethod(int interrogatorNumber, int valor, int totalColisao, int totalVazios, float tagBitrate) {
		log.info("--------------------|Interrogator "+interrogatorNumber+"|-------------------");
		log.info("Performance report");
		log.info("Tags: " + valor);
		log.info("Rounds: " + totalRound);
		log.info("Slots needed to all tags reply: " + totalSlot);
		log.info("Slots with tag collision: " + totalColisao);
		log.info("Slots with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalSlot * 0.01) + "s");
		log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format((64 / tagBitrate) * totalSlot) + "s");
		log.info("-------------------------------------------------------");
	}
	
	public void printAverage(int valor, int process, float tagBitrate) {
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + valor);
		log.info("Rounds: " + formatter.format(averageTotalRounds / process));
		log.info("Slots needed to all tags reply: " + formatter.format(averageTotalSlots / process));
		log.info("Slots with tag collision: " + formatter.format(averageTotalColision / process));
		log.info("Slots with no tag reply: " + formatter.format(averageTotalEmpty / process));
		log.info("Read Total Time (Worst Case): " + formatter.format(averageTotalTime / process) + "s");
		log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format(averageTotalReadTime / process) + "s");
		log.info("----------------------|LST - END|----------------------");
	}

	public float getAverageTotalRounds() {
		return averageTotalRounds;
	}

	public float getAverageTotalSlots() {
		return averageTotalSlots;
	}
	
}