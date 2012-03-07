package br.vinicius.tcc.simulador.antiColisao;

import br.vinicius.tcc.entidade.Simulacao;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProtocolosAntiColisao protocolosAntiColisao = new ProtocolosAntiColisao();
		Simulacao  simulacao = new Simulacao();
		simulacao.setTotalEtiquetas(10);
		simulacao.setTotalLeitoras(1);
		
		protocolosAntiColisao.chamaInterrogador("LST", simulacao);
		protocolosAntiColisao.chamaInterrogador("LSTSTANDBY", simulacao);
		protocolosAntiColisao.chamaInterrogador("FST", simulacao);
		protocolosAntiColisao.chamaInterrogador("BTREE", simulacao);
		protocolosAntiColisao.chamaInterrogador("ALOHAQ", simulacao);
		protocolosAntiColisao.chamaInterrogador("ALOHAQSTS", simulacao);

	}

}
