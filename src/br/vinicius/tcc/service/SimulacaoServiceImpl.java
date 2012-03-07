package br.vinicius.tcc.service;

import br.vinicius.tcc.entidade.Simulacao;
import br.vinicius.tcc.simulador.antiColisao.ProtocolosAntiColisao;

public class SimulacaoServiceImpl implements SimulacaoService{

	private ProtocolosAntiColisao protocolosAntiColisao = new ProtocolosAntiColisao();
	
	@Override
	public void simularAmbiente(Simulacao simulacao) throws SimulacaoException {
		// TODO criar toda a rotina de salvar no banco e chamar as classes de simulação
		protocolosAntiColisao.chamaInterrogador(simulacao.getProtocolo(), simulacao);
	}

	@Override
	public Simulacao buscarResultados(Integer id) throws SimulacaoException {
		// TODO criar rotina para pegar os resultados no banco
		return null;
	}

}
