package br.vinicius.tcc.simulador.antiColisao.interrogador;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.log4j.Logger;

public abstract class Interrogador {
	protected static Logger log = Logger.getLogger(Interrogador.class);
	protected float averageTotalCollision = 0;
	protected float averageTotalEmpty = 0;
	protected float averageTotalTime = 0;
	protected float averageTotalReadTime = 0;
	protected float tagBitRate;
	protected int tags;
	protected int[] etiquetas = new int[2]; 
	protected NumberFormat formatter = new DecimalFormat("0.00");
	public abstract void execute(int totalInterrogadores, int division);
	public abstract void printMethod(int interrogatorNumber, int totalColisao, int totalVazios);
	public abstract void printAverage(int process);
	
	public float getAverageTotalColision() {
		return averageTotalCollision;
	}
	public float getAverageTotalEmpty() {
		return averageTotalEmpty;
	}
	public float getAverageTotalTime() {
		return averageTotalTime;
	}
	public float getAverageTotalReadTime() {
		return averageTotalReadTime;
	}
	
	
	
}