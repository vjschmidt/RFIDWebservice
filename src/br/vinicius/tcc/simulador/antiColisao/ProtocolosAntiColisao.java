package br.vinicius.tcc.simulador.antiColisao;

import br.vinicius.tcc.entidade.Simulacao;
import br.vinicius.tcc.simulador.antiColisao.interrogador.BtreeInterrogador;
import br.vinicius.tcc.simulador.antiColisao.interrogador.FstInterrogador;
import br.vinicius.tcc.simulador.antiColisao.interrogador.LstInterrogador;
import br.vinicius.tcc.simulador.antiColisao.interrogador.LstStandByInterrogador;
import br.vinicius.tcc.simulador.antiColisao.interrogador.QCalculatedInterrogador;
import br.vinicius.tcc.simulador.antiColisao.interrogador.QSlotToSlotInterrogador;

public class ProtocolosAntiColisao {
	public final String LST = "LST";
	public final String LSTSTANDBY = "LSTSTANDBY";
	public final String FST = "FST";
	public final String BTREE = "BTREE";
	public final String ALOHAQ = "ALOHAQ";
	public final String ALOHAQSTS = "ALOHAQSTS";
	private int[] etiquetasDivididas = new int[2];
	
	public void chamaInterrogador(String protocolo, Simulacao simulacao) {
		if(protocolo != null && !"".equals(protocolo)) {
			etiquetasDivididas[0] = simulacao.getTotalEtiquetas() / simulacao.getTotalLeitoras();
			etiquetasDivididas[1] = simulacao.getTotalEtiquetas() - etiquetasDivididas[0];
		}
		if(protocolo.equalsIgnoreCase(LST)) {
			LstInterrogador interrogador = new LstInterrogador(etiquetasDivididas, 16, 10, 40f);
			//TODO temporario
			System.out.println(interrogador.getAverageTotalRounds() / 10);
			System.out.println(interrogador.getAverageTotalSlots()  / 10);
			System.out.println(interrogador.getAverageTotalColision() / 10);
			System.out.println(interrogador.getAverageTotalEmpty() / 10);
			System.out.println(interrogador.getAverageTotalTime() / 10);
			System.out.println(interrogador.getAverageTotalReadTime() / 10);
		} else if(protocolo.equalsIgnoreCase(LSTSTANDBY)) {
			LstStandByInterrogador interrogador = new LstStandByInterrogador(etiquetasDivididas, 16, 10, 40f);
			//TODO temporario
			System.out.println(interrogador.getAverageTotalRounds() / 10);
			System.out.println(interrogador.getAverageTotalSlots() / 10);
			System.out.println(interrogador.getAverageTotalColision() / 10);
			System.out.println(interrogador.getAverageTotalEmpty() / 10);
			System.out.println(interrogador.getAverageTotalTime() / 10);
			System.out.println(interrogador.getAverageTotalReadTime() / 10);
		} else if(protocolo.equalsIgnoreCase(FST)) {
			FstInterrogador interrogador = new FstInterrogador(etiquetasDivididas, 10, 40f);
			//TODO temporario
			System.out.println(interrogador.getAverageTotalRounds() / 10);
			System.out.println(interrogador.getAverageTotalSlots() / 10);
			System.out.println(interrogador.getAverageTotalColision() / 10);
			System.out.println(interrogador.getAverageTotalEmpty() / 10);
			System.out.println(interrogador.getAverageTotalTime() / 10);
			System.out.println(interrogador.getAverageTotalReadTime() / 10);
		} else if(protocolo.equalsIgnoreCase(BTREE)) {
			BtreeInterrogador interrogador = new BtreeInterrogador(etiquetasDivididas, 10, 40f);
			//TODO temporario
			System.out.println(interrogador.getAverageTotalIterations() / 10);
			System.out.println(interrogador.getAverageTotalColision() / 10);
			System.out.println(interrogador.getAverageTotalEmpty() / 10);
			System.out.println(interrogador.getAverageTotalTime() / 10);
			System.out.println(interrogador.getAverageTotalReadTime() / 10);
		} else if(protocolo.equalsIgnoreCase(ALOHAQ)) {
			QCalculatedInterrogador interrogador = new QCalculatedInterrogador(etiquetasDivididas, 10, 1, 40f, 256);
			//TODO temporario
			System.out.println(interrogador.getAverageTotalRounds() / 10);
			System.out.println(interrogador.getAverageTotalSlots() / 10);
			System.out.println(interrogador.getAverageTotalColision() / 10);
			System.out.println(interrogador.getAverageTotalEmpty() / 10);
			System.out.println(interrogador.getAverageTotalTime() / 10);
			System.out.println(interrogador.getAverageTotalReadTime() / 10);
		} else if(protocolo.equalsIgnoreCase(ALOHAQSTS)) {
			QSlotToSlotInterrogador interrogador = new QSlotToSlotInterrogador(etiquetasDivididas, 10, 1, 40f, 256);
			//TODO temporario
			System.out.println(interrogador.getAverageTotalRounds() / 10);
			System.out.println(interrogador.getAverageTotalSlots() / 10);
			System.out.println(interrogador.getAverageTotalColision() / 10);
			System.out.println(interrogador.getAverageTotalEmpty() / 10);
			System.out.println(interrogador.getAverageTotalTime() / 10);
			System.out.println(interrogador.getAverageTotalReadTime() / 10);
		}
	}
}
