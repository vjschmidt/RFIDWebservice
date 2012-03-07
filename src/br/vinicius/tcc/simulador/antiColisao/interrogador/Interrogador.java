package br.vinicius.tcc.simulador.antiColisao.interrogador;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.log4j.Logger;

public abstract class Interrogador{
	static Logger log = Logger.getLogger(Interrogador.class);
	protected float averageTotalColision = 0;
	protected float averageTotalEmpty = 0;
	protected float averageTotalTime = 0;
	protected float averageTotalReadTime = 0;
	protected int[] etiquetas = new int[2]; 
	protected double expositionTime = 0;
	protected boolean adjust;
	protected boolean adjustFlag = false;
	protected NumberFormat formatter = new DecimalFormat("0.00");
	public abstract void execute(int totalInterrogadores, String division, boolean fieldTest, String tagSpeed, double expositionDistance, float tagBitrate);
	public abstract void printMethod(int interrogatorNumber, int valor, int totalColisao, int totalVazios, float tagBitrate, String tagSpeed, double expositionDistance, boolean fieldTest);
	public abstract void printAverage(int valor, int process, float tagBitrate, boolean fieldTest);
	
	public void adjust(){
		int etiquetas = 0;
		if (this.etiquetas.length == 1 || this.etiquetas[1] == 0) {
			this.etiquetas[0] *= 0.9;
		} else {
			etiquetas = this.etiquetas[0] + this.etiquetas[1];
			etiquetas *= 0.9;
			this.etiquetas[0] = etiquetas / 2;
			this.etiquetas[1] = etiquetas - this.etiquetas[0];
		}
	}
}