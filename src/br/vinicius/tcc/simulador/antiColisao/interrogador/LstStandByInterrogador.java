package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.manager.LstStandByTagManager;

public class LstStandByInterrogador extends AveragesAloha {
	private int initRound = 0;
	
	public LstStandByInterrogador(int[] etiquetas, int roundSize, int division, float tagBitRate){
		this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		this.tagBitRate = tagBitRate;
		int totalInterrogadores = (etiquetas[1] == 0 ? 1 : 2);
		initRound = roundSize;
		log.info("---------------------|LST STANDBY|---------------------");
		log.info("Tags in the environment: " + (this.etiquetas[0] + this.etiquetas[1]));
		log.info("Initial Round Size: " + roundSize);
		log.info("-------------------------------------------------------");
		execute(totalInterrogadores, division);
	}
	
	public void execute(int totalInterrogadores, int division) {
		tagBitRate *= 1000;
		int initRound = 0;
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++) {
			averageTotalRounds = 0;
			averageTotalSlots = 0;
			averageTotalCollision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			tags = this.etiquetas[interrogatorNumber - 1];
			for (int average = 1; average <= division; average++) {
				initRound = this.initRound;
				totalRound = 0;
				totalSlot = 0;
				LstStandByTagManager tag = new LstStandByTagManager(initRound, tags);
				do {
					totalRound++;
					log.info("Round #" + totalRound);
					for (int nextSlot = 1; nextSlot <= initRound; nextSlot++){
						totalSlot++;
						tag.setSlot(nextSlot);
						log.info("Slot: " + nextSlot);
						log.info("Tags that replied:");
						log.info(tag.verificarSlotEnvio());
						if(tag.getSlotCont() == 1) {
							if(tag.getVazioCont() > tag.getTagsEncontradas()) {
								if(initRound == 8) {
									initRound = 1;
								} else if(initRound != 1) {
									initRound = initRound / 2;
								}
								if(tag.tamanho() > 0) {
									nextSlot = 0;
									totalRound++;
									tag.novoRound(initRound);
									log.info("Round #"+totalRound);
								} else {
									nextSlot = initRound;
								}
							} else if(tag.getColisaoCont() > tag.getVazioCont()){
								if(initRound == 1){
									initRound = 8;
								} else if(initRound != 256){
									initRound = initRound * 2;
								}
								if(tag.tamanho() > 0) {
									nextSlot = 0;
									totalRound++;
									tag.novoRound(initRound);
									log.info("Round #" + totalRound);
								} else {
									nextSlot = initRound;
								}
							}
						}	
					}
					if(initRound == 1) {
						initRound = 8;
					} else if(initRound == 8) {
						initRound = 16;
					} else if(initRound == 16) {
						initRound = 32;
					} else if(initRound == 32) {
						initRound = 64;
					} else if(initRound == 64) {
						initRound = 128;
					} else if(initRound == 128) {
						initRound = 256;
					}
					tag.novoRound (initRound);
				} while (tag.tamanho() > 0);
				log.info("-------------------------------------------------------");
				printMethod(interrogatorNumber, tag.getTotalColisao(), tag.getTotalVazios());
				averageTotalRounds += totalRound;
				averageTotalSlots += totalSlot;
				averageTotalCollision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalSlot * 0.01;
				if (tagBitRate > 0) {
					averageTotalReadTime += (64 / tagBitRate) * totalSlot;
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
			log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format((64 / tagBitRate) * totalSlot) + "s");
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
		log.info("------------------|LST STANDBY - END|------------------");
	}

}