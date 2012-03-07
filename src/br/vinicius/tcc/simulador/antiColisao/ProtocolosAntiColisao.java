package br.vinicius.tcc.simulador.antiColisao;

import br.vinicius.tcc.entidade.Simulacao;
import br.vinicius.tcc.simulador.antiColisao.interrogador.LstInterrogador;

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
			//TODO LstInterrogador
			//new LstInterrogador(etiquetasDivididas, 16, 10, fieldTest, adjust, tagSpeed, expositionDistance, tagBitrate)
		} else if(protocolo.equalsIgnoreCase(LSTSTANDBY)) {
			//TODO LstStandByInterrogador
		} else if(protocolo.equalsIgnoreCase(FST)) {
			//TODO FstInterrogador
		} else if(protocolo.equalsIgnoreCase(BTREE)) {
			//TODO BtreeInterrogador
		} else if(protocolo.equalsIgnoreCase(ALOHAQ)) {
			//TODO QCalclulatedInterrogador
		} else if(protocolo.equalsIgnoreCase(ALOHAQSTS)) {
			//TODO QSlotToSlotInterrogador
		}
	}
}
