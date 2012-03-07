package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.criadorImpressor.BtreeTagManager;

public class BtreeInterrogador extends Interrogador{
	private float averageTotalIterations = 0;
	private int totalIteration = 0;
	
	public BtreeInterrogador(int[] etiquetas, String division, boolean fieldTest, boolean adjust, String tagSpeed, double expositionDistance, float tagBitrate){
		this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		int soma = this.etiquetas[0] + this.etiquetas[1];
		this.adjust = adjust;
		do {
			log.info("Tags in the environment: " + (this.etiquetas[0] + this.etiquetas[1]));
			if (fieldTest) {
				log.info("Tag moving speed: " + tagSpeed);
				log.info("Total tag exposition distance to the interrogator read range: " + formatter.format(expositionDistance * 2));
			}
			if (this.adjust && (this.etiquetas[0] + this.etiquetas[1]) != soma) { 
				log.info("CAUTION: The amount of tags in the environment can't be fully readed with the given configuration");
				log.info("** Recommendation: the RFID-Env tested many possibilities and found that the recomended *maximum* amount of tags to the given environment parameters is: "+this.etiquetas);
				log.info("** The following test were performed with the recomended maximum amount of tags:");
			}
			execute (etiquetas.length, division, fieldTest, tagSpeed, expositionDistance, tagBitrate);
		} while (this.adjust);
	}
	
	public void execute (int totalInterrogadores, String division, boolean fieldTest, String tagSpeed, double expositionDistance, float tagBitrate) {
		tagBitrate *= 1000;
		int valor = 0;
		int process = Integer.parseInt(division);
		log.info("------------------------|BTREE|------------------------");
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++){
			averageTotalIterations = 0;
			averageTotalColision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			for (int average = 1; average <= process; average++) {
				totalIteration = 0;
				if (totalInterrogadores == 2 && adjustFlag) {
					adjust = true;
				}
				BtreeTagManager tag = new BtreeTagManager(valor);
				do {
					totalIteration++;
					log.info("Iteration #" + totalIteration);
					log.info("Tags that replied:");
					log.info(tag.etiqueta());
					if(tag.getSlotCont() == 0) {
						tag.success();
					} else if(tag.getSlotCont() == 1) {
						tag.success();
					} else if(tag.getSlotCont() > 1) {
						tag.fail();
					}
				} while (tag.tamanho() > 0);
				log.info("-------------------------------------------------------");
				printMethod(interrogatorNumber, valor, tag.getTotalColisao(), tag.getTotalVazios(), tagBitrate, tagSpeed, expositionDistance, fieldTest);
				averageTotalIterations += totalIteration;
				averageTotalColision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalIteration * 0.01;
				averageTotalReadTime += (64 / tagBitrate) * totalIteration;
			}
			printAverage(valor, process, tagBitrate, fieldTest);
			if (adjust && expositionTime > 0.01 && expositionTime<(averageTotalTime / process)) {
				adjust();
				break;
			} else {
				adjust = false;
			}
		}
	}
	
	public void printMethod (int interrogatorNumber, int valor, int totalColisao, int totalVazios, float tagBitrate,
			String tagSpeed, double expositionDistance, boolean fieldTest) {
		log.info("------------------------|BTREE|------------------------");
		log.info("--------------------|Interrogator " + interrogatorNumber + "|-------------------");
		log.info("Performance report");
		log.info("Tags: " + valor);
		log.info("Iterations: " + totalIteration);
		log.info("Iterations with tag collision: " + totalColisao);
		log.info("Iterations with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalIteration * 0.01) + "s");
		if (tagBitrate > 0) {
			log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format((64 / tagBitrate) * totalIteration) + "s");
		}
		if (fieldTest && Float.parseFloat(tagSpeed) > 0) {
			expositionTime = (expositionDistance / Float.parseFloat(tagSpeed)) * 2;
			log.info("Exposition Total Time: " + formatter.format(expositionTime) + "s");
			log.info("-------------------------------------------------------");
			if (expositionTime < (totalIteration * 0.01)) {
				log.info("With this speed the group of tags couldn't be fully read");
			}
		}
		log.info("-------------------------------------------------------");
	}
	
	public void printAverage (int valor, int process, float tagBitrate, boolean fieldTest){
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + valor);
		log.info("Iterations: " + formatter.format(averageTotalIterations / process));
		log.info("Iterations with tag collision: " + formatter.format(averageTotalColision / process));
		log.info("Iterations with no tag reply: " + formatter.format(averageTotalEmpty / process));
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
		log.info("---------------------|BTREE - END|---------------------");
	}
}