package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.criadorImpressor.QTagManager;

public class QSlotToSlotInterrogador extends Interrogador {
	private float averageTotalRounds = 0;
	private float averageTotalSlots = 0;
	private int tagBitAmount=0;
	private int totalRound=0;
	private int totalSlot=0;
	private int q=0;
	
	public QSlotToSlotInterrogador(int[] etiquetas, String division, String qStarter, boolean fieldTest, boolean adjust, String tagSpeed, double expositionDistance, float tagBitrate, int tagBitAmount){
		q=(int)Math.pow(2, Integer.parseInt(qStarter));
		this.tagBitAmount=tagBitAmount;
    	this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		int soma = this.etiquetas[0] + this.etiquetas[1];
		this.adjust = adjust;
		do {
			log.info("Tags in the environment: " + etiquetas);
			log.info("Initial Round Size: " + q);
			if (fieldTest){
				log.info("Tag moving speed: " + tagSpeed);
				log.info("Total tag exposition distance to the interrogator read range: " + formatter.format(expositionDistance * 2));
			}
			if (this.adjust && (this.etiquetas[0] + this.etiquetas[1]) != soma) {
				log.info("CAUTION: The amount of tags in the environment can't be fully readed with the given configuration");
				log.info("** Recommendation: the RFID-Env tested many possibilities and found that the recomended *maximum* amount of tags to the given environment parameters is: " + this.etiquetas);
				log.info("** The following test were performed with the recomended maximum amount of tags:");
			}
			execute(etiquetas.length, division, fieldTest, tagSpeed, expositionDistance, tagBitrate);
		} while (this.adjust);
    }

	public void execute (int totalInterrogadores, String division, boolean fieldTest, String tagSpeed, double expositionDistance, float tagBitrate){
    	tagBitrate *= 1000;
		int valor = 0;
		int process = Integer.parseInt(division);
		log.info("------------------|Q - SLOT TO SLOT|-------------------");
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++){
			averageTotalRounds = 0;
			averageTotalSlots = 0;
			averageTotalColision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			for (int average = 1; average <= process; average++) {
				totalRound = 0;
				totalSlot = 0;
				int round = 1;
				valor = this.etiquetas[interrogatorNumber - 1];
				if (totalInterrogadores == 2 && adjustFlag) {
					adjust = true;
				}
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
				printMethod (interrogatorNumber, valor, tag.getTotalColisao(), tag.getTotalVazios(), tagBitrate, tagSpeed, expositionDistance, fieldTest);
				averageTotalRounds += totalRound;
				averageTotalSlots += totalSlot;
				averageTotalColision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalSlot * 0.01;
				averageTotalReadTime += (tagBitAmount / tagBitrate) * totalSlot;
			}
			printAverage (valor, process, tagBitrate, fieldTest);
			if(adjust && expositionTime > 0.01 && expositionTime < (averageTotalTime / process)) {
				adjust();
				break;
			} else {
				adjust = false;
			}
		}
	}
	public void printMethod (int interrogatorNumber, int valor, int totalColisao, int totalVazios, float tagBitrate,
			String tagSpeed, double expositionDistance, boolean fieldTest) {
		log.info("------------------|Q - SLOT TO SLOT|-------------------");
		log.info("--------------------|Interrogator " + interrogatorNumber + "|-------------------");
		log.info("Performance report");
		log.info("Tags: " + valor);
		log.info("Rounds: " + totalRound);
		log.info("Slots needed to all tags reply: " + totalSlot);
		log.info("Slots with tag collision: " + totalColisao);
		log.info("Slots with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalSlot * 0.01) + "s");
		if (tagBitrate > 0) {
			log.info("Read Total Time (Best Case): " + formatter.format((((tagBitAmount / tagBitrate) * valor) + (16/ tagBitrate) * (totalSlot - valor))) + "s");
		}
		if (fieldTest && Float.parseFloat(tagSpeed) > 0) {
			expositionTime = (expositionDistance / Float.parseFloat(tagSpeed)) * 2;
			log.info("Exposition Total Time: " + formatter.format(expositionTime) + "s");
			log.info("-------------------------------------------------------");
			if (expositionTime < (totalSlot * 0.01)) {
				log.info("With this speed the group of tags couldn't be fully read");
			}
		}
		log.info("-------------------------------------------------------");
	}
	public void printAverage (int valor, int process, float tagBitrate, boolean fieldTest){
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + valor);
		log.info("Rounds: " + formatter.format(averageTotalRounds / process));
		log.info("Slots needed to all tags reply: " + formatter.format(averageTotalSlots / process));
		log.info("Slots with tag collision: " + formatter.format(averageTotalColision / process));
		log.info("Slots with no tag reply: " + formatter.format(averageTotalEmpty / process));
		log.info("Read Total Time (Worst Case): " + formatter.format(averageTotalTime / process) + "s");
		if (tagBitrate > 0) {
			log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format(averageTotalReadTime / process) + "s");
		}
		if (fieldTest && expositionTime > 0) {
			log.info("Exposition Total Time: " + formatter.format(expositionTime) + "s");
			if (expositionTime < 0.01) {
				log.info("With this configuration not even one tag can be read");
			}
		}
		log.info("---------------|Q - SLOT TO SLOT - END|----------------");
	}
}