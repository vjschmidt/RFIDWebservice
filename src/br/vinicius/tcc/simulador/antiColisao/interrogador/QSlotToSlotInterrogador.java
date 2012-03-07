package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.criadorImpressor.QTagManager;

public class QSlotToSlotInterrogador extends Interrogador {
	private float averageTotalRounds = 0;
	private float averageTotalSlots = 0;
	private int tagBitAmount = 0;
	private int totalRound = 0;
	private int totalSlot = 0;
	private int q = 0;
	
	public QSlotToSlotInterrogador(int[] etiquetas, int division, int qStarter, float tagBitrate, int tagBitAmount){
		q = (int)Math.pow(2, qStarter);
		this.tagBitAmount=tagBitAmount;
    	this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		int totalInterrogadores = etiquetas[1] == 0 ? 1 : 2;
		log.info("------------------|Q - SLOT TO SLOT|-------------------");
		log.info("Tags in the environment: " + etiquetas);
		log.info("Initial Round Size: " + q);
		log.info("-------------------------------------------------------");
		execute(totalInterrogadores, division, tagBitrate);
    }

	public void execute (int totalInterrogadores, int division, float tagBitrate){
    	tagBitrate *= 1000;
		int valor = 0;
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++){
			averageTotalRounds = 0;
			averageTotalSlots = 0;
			averageTotalColision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			for (int average = 1; average <= division; average++) {
				totalRound = 0;
				totalSlot = 0;
				int round = 1;
				valor = this.etiquetas[interrogatorNumber - 1];
				QTagManager tag = new QTagManager(round, valor);
				tag.setQ(q);
				tag.setQfp(q);
				do {
					totalRound++;
					log.info("Round #"+totalRound);
					for (int nextSlot = 1; nextSlot <= round; nextSlot++) {
						totalSlot++;
						log.info("Tags that replied:");
						log.info(tag.etiqueta());
						if(tag.getSlotCont() != 1) {
							if(tag.getSlotCont() == 0 && tag.tamanho() > 0) {
								nextSlot = 0;
								totalRound++;
								tag.queryAdjust("011");
								round = tag.getRound();
								log.info("Round #"+totalRound);
							} else if(tag.getSlotCont() > 1 && tag.tamanho() > 0) {
								nextSlot = 0;
								totalRound++;
								tag.queryAdjust("110");
								round = tag.getRound();
								log.info("Round #"+totalRound);
							} else {
								nextSlot = round;
							}
						} else {
							tag.queryAdjust("000");
							round = tag.getRound();
							if(tag.tamanho() > 0) {
								nextSlot = 0;
								totalRound++;
								log.info("Round #"+totalRound);
							} else {
								nextSlot = round;
							}
						}
					}
					tag.query();
					round = tag.getRound();
				} while (tag.tamanho() > 0);
				log.info("-------------------------------------------------------");
				printMethod (interrogatorNumber, valor, tag.getTotalColisao(), tag.getTotalVazios(), tagBitrate);
				averageTotalRounds += totalRound;
				averageTotalSlots += totalSlot;
				averageTotalColision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalSlot * 0.01;
				averageTotalReadTime += (tagBitAmount / tagBitrate) * totalSlot;
			}
			printAverage (valor, division, tagBitrate);
		}
	}
	public void printMethod (int interrogatorNumber, int valor, int totalColisao, int totalVazios, float tagBitrate) {
		log.info("--------------------|Interrogator " + interrogatorNumber + "|-------------------");
		log.info("Performance report");
		log.info("Tags: " + valor);
		log.info("Rounds: " + totalRound);
		log.info("Slots needed to all tags reply: " + totalSlot);
		log.info("Slots with tag collision: " + totalColisao);
		log.info("Slots with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalSlot * 0.01) + "s");
		log.info("Read Total Time (Best Case): " + formatter.format((((tagBitAmount / tagBitrate) * valor) + (16/ tagBitrate) * (totalSlot - valor))) + "s");
		log.info("-------------------------------------------------------");
	}
	public void printAverage (int valor, int process, float tagBitrate){
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + valor);
		log.info("Rounds: " + formatter.format(averageTotalRounds / process));
		log.info("Slots needed to all tags reply: " + formatter.format(averageTotalSlots / process));
		log.info("Slots with tag collision: " + formatter.format(averageTotalColision / process));
		log.info("Slots with no tag reply: " + formatter.format(averageTotalEmpty / process));
		log.info("Read Total Time (Worst Case): " + formatter.format(averageTotalTime / process) + "s");
		log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format(averageTotalReadTime / process) + "s");
		log.info("---------------|Q - SLOT TO SLOT - END|----------------");
	}

	public float getAverageTotalRounds() {
		return averageTotalRounds;
	}

	public float getAverageTotalSlots() {
		return averageTotalSlots;
	}
	
}