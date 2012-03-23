package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.manager.BtreeTagManager;

public class BtreeInterrogador extends Interrogador {
	private float averageTotalIterations;
	private int totalIteration;
	
	public BtreeInterrogador(int[] etiquetas, int division, float tagBitRate) {
		this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		this.tagBitRate = tagBitRate;
		int totalInterrogadores = (etiquetas[1] == 0 ? 1 : 2);
		log.info("------------------------|BTREE|------------------------");
		log.info("Tags in the environment: " + (this.etiquetas[0] + this.etiquetas[1]));
		log.info("-------------------------------------------------------");
		execute (totalInterrogadores, division);
	}
	
	public void execute (int totalInterrogadores, int division) {
		tagBitRate *= 1000;
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++){
			averageTotalIterations = 0;
			averageTotalCollision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			tags = this.etiquetas[interrogatorNumber - 1];
			for (int average = 1; average <= division; average++) {
				totalIteration = 0;
				BtreeTagManager tag = new BtreeTagManager(tags);
				do {
					totalIteration++;
					log.info("Iteration #" + totalIteration);
					log.info("Tags that replied:");
					log.info(tag.verificarSlotEnvio());
					if(tag.getSlotCont() == 0) {
						tag.success();
					} else if(tag.getSlotCont() == 1) {
						tag.success();
					} else if(tag.getSlotCont() > 1) {
						tag.fail();
					}
				} while (tag.tamanho() > 0);
				log.info("-------------------------------------------------------");
				printMethod(interrogatorNumber, tag.getTotalColisao(), tag.getTotalVazios());
				averageTotalIterations += totalIteration;
				averageTotalCollision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalIteration * 0.01;
				if (tagBitRate > 0) {
					averageTotalReadTime += (64 / tagBitRate) * totalIteration;
				}
			}
			printAverage(division);
		}
	}
	
	public void printMethod (int interrogatorNumber, int totalColisao, int totalVazios) {
		log.info("--------------------|Interrogator " + interrogatorNumber + "|-------------------");
		log.info("Performance report");
		log.info("Tags: " + tags);
		log.info("Iterations: " + totalIteration);
		log.info("Iterations with tag collision: " + totalColisao);
		log.info("Iterations with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalIteration * 0.01) + "s");
		if (tagBitRate > 0) {
			log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format((64 / tagBitRate) * totalIteration) + "s");
		}
		log.info("-------------------------------------------------------");
	}
	
	public void printAverage (int process) {
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + tags);
		log.info("Iterations: " + formatter.format(averageTotalIterations / process));
		log.info("Iterations with tag collision: " + formatter.format(averageTotalCollision / process));
		log.info("Iterations with no tag reply: " + formatter.format(averageTotalEmpty / process));
		log.info("Read Total Time (Worst Case): " + formatter.format(averageTotalTime / process) + "s");
		if (tagBitRate > 0) {
			log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format(averageTotalReadTime / process) + "s");
		}
		log.info("---------------------|BTREE - END|---------------------");
	}

	public float getAverageTotalIterations() {
		return averageTotalIterations;
	}
	
}