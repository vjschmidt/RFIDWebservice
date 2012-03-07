package br.vinicius.tcc.simulador.antiColisao.interrogador;

import br.vinicius.tcc.simulador.antiColisao.criadorImpressor.BtreeTagManager;

public class BtreeInterrogador extends Interrogador{
	private float averageTotalIterations = 0;
	private int totalIteration = 0;
	
	public BtreeInterrogador(int[] etiquetas, int division, float tagBitrate){
		this.etiquetas[0] = etiquetas[0];
		this.etiquetas[1] = (etiquetas.length == 2 ? etiquetas[1] : 0);
		int totalInterrogadores = etiquetas[1] == 0 ? 1 : 2;
		log.info("------------------------|BTREE|------------------------");
		log.info("Tags in the environment: " + (this.etiquetas[0] + this.etiquetas[1]));
		log.info("-------------------------------------------------------");
		execute (totalInterrogadores, division, tagBitrate);
	}
	
	public void execute (int totalInterrogadores, int division, float tagBitrate) {
		tagBitrate *= 1000;
		int valor = 0;
		for (int interrogatorNumber = 1; interrogatorNumber <= totalInterrogadores; interrogatorNumber++){
			averageTotalIterations = 0;
			averageTotalColision = 0;
			averageTotalEmpty = 0;
			averageTotalTime = 0;
			averageTotalReadTime = 0;
			for (int average = 1; average <= division; average++) {
				totalIteration = 0;
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
				printMethod(interrogatorNumber, valor, tag.getTotalColisao(), tag.getTotalVazios(), tagBitrate);
				averageTotalIterations += totalIteration;
				averageTotalColision += tag.getTotalColisao();
				averageTotalEmpty += tag.getTotalVazios();
				averageTotalTime += totalIteration * 0.01;
				averageTotalReadTime += (64 / tagBitrate) * totalIteration;
			}
			printAverage(valor, division, tagBitrate);
		}
	}
	
	public void printMethod (int interrogatorNumber, int valor, int totalColisao, int totalVazios, float tagBitrate) {
		log.info("--------------------|Interrogator " + interrogatorNumber + "|-------------------");
		log.info("Performance report");
		log.info("Tags: " + valor);
		log.info("Iterations: " + totalIteration);
		log.info("Iterations with tag collision: " + totalColisao);
		log.info("Iterations with no tag reply: " + totalVazios);
		log.info("Read Total Time (Worst Case): " + formatter.format(totalIteration * 0.01) + "s");
		log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format((64 / tagBitrate) * totalIteration) + "s");
		log.info("-------------------------------------------------------");
	}
	
	public void printAverage (int valor, int process, float tagBitrate){
		log.info("Performance report: average of " + process + " processes");
		log.info("Tags: " + valor);
		log.info("Iterations: " + formatter.format(averageTotalIterations / process));
		log.info("Iterations with tag collision: " + formatter.format(averageTotalColision / process));
		log.info("Iterations with no tag reply: " + formatter.format(averageTotalEmpty / process));
		log.info("Read Total Time (Worst Case): " + formatter.format(averageTotalTime / process) + "s");
		log.info("Read Total Time (Best Case for selected Bit Rate): " + formatter.format(averageTotalReadTime / process) + "s");
		log.info("---------------------|BTREE - END|---------------------");
	}

	public float getAverageTotalIterations() {
		return averageTotalIterations;
	}
	
}