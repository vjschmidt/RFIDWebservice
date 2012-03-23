package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.manager.QTagManager;

public class QCalculatedInterrogador extends AveragesAloha {
	private int tagBitAmount = 0;
	private int q = 0;
	
    public QCalculatedInterrogador(int[] etiquetas, int division, int qStarter, float tagBitRate, int tagBitAmount) {
    	q = (int)Math.pow(2, qStarter);
    	this.tagBitAmount=tagBitAmount;
    	this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		this.tagBitRate = tagBitRate;
		int totalInterrogadores = (etiquetas[1] == 0 ? 1 : 2);
		log.info("-------------------|Q - CALCULATED|--------------------");
		log.info("Tags in the environment: " + etiquetas);
		log.info("Initial Round Size: " + q);
		log.info("-------------------------------------------------------");
    	execute(totalInterrogadores, division);
    }

    public void execute (int totalInterrogadores, int division) {
    	tagBitRate *= 1000;
		int round = 0;
		for (int interrogatorNumber = 1; interrogatorNumber<=totalInterrogadores; interrogatorNumber++){
			averageTotalRounds = 0;
			averageTotalSlots = 0;
			averageTotalCollision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			tags = this.etiquetas[interrogatorNumber - 1];
			for (int average = 1; average <= division; average++) {
				totalRound = 0;
				totalSlot = 0;
				round = 1;
				QTagManager tag = new QTagManager(round, tags);
				tag.setQ(q);
				tag.setQfp(q);
				do {
					totalRound++;
					log.info("Round #" + totalRound);
					for (int nextSlot = 1; nextSlot <= round; nextSlot++){
						totalSlot++;
						log.info("Tags that replied:");
						log.info(tag.verificarSlotEnvio());
						
						if(tag.getSlotCont() != 1) {
							if(tag.getVazioCont() > tag.getTagsEncontradas()) {
								tag.queryAdjust("011");
								round = tag.getRound();
								if(tag.tamanho() > 0) {
									nextSlot = 0;
									totalRound++;
									log.info("Round #" + totalRound);
								} else {
									nextSlot = round;
								}
							} else if(tag.getColisaoCont() > tag.getVazioCont()) {
								tag.queryAdjust("110");
								round = tag.getRound();
								if(tag.tamanho() > 0) {
									nextSlot = 0;
									totalRound++;
									log.info("Round #" + totalRound);
								} else {
									nextSlot = round;
								}
							} else {
								tag.queryRep();
							}
						} else {
							tag.queryRep();
						}
					}
					tag.query();
					round = tag.getRound();
				} while (tag.tamanho() > 0);
				log.info("-------------------------------------------------------");
				printMethod(interrogatorNumber, tag.getTotalColisao(), tag.getTotalVazios());
				averageTotalRounds += totalRound;
				averageTotalSlots += totalSlot;
				averageTotalCollision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalSlot * 0.01;
				if (tagBitRate > 0) {
					averageTotalReadTime += (tagBitAmount / tagBitRate) * totalSlot;
				}
			}
			printAverage(division);
		}
	}
	public void printMethod (int interrogatorNumber, int totalColisao, int totalVazios) {
		log.info("--------------------|Interrogator " + interrogatorNumber + "|-------------------");
		log.info("Performance report");
		log.info("Tags: " + tags);
		log.info("Rounds: " + totalRound);
		log.info("Slots needed to all tags reply: " + totalSlot);
		log.info("Slots with tag collision: " + totalColisao);
		log.info("Slots with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalSlot * 0.01) + "s");
		if (tagBitRate > 0) {
			log.info("Read Total Time (Best Case): " + formatter.format((((tagBitAmount / tagBitRate) * tags) + (16 / tagBitRate) * (totalSlot - tags))) + "s");
		}
		log.info("-------------------------------------------------------");
	}
	public void printAverage (int process) {
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + tags);
		log.info("Rounds: " + formatter.format(averageTotalRounds / process));
		log.info("Slots needed to all tags reply: " + formatter.format(averageTotalSlots / process));
		log.info("Slots with tag collision: " + formatter.format(averageTotalCollision / process));
		log.info("Slots with no tag reply: " + formatter.format(averageTotalEmpty / process));
		log.info("Read Total Time (Worst Case): " + formatter.format(averageTotalTime / process) + "s");
		if (tagBitRate > 0) {
			log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format(averageTotalReadTime / process) + "s");
		}
		log.info("----------------|Q - CALCULATED - END|-----------------");
	}

}