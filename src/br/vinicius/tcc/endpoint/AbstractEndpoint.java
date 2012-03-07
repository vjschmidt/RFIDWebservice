package br.vinicius.tcc.endpoint;

import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;

import br.vinicius.tcc.service.SimulacaoService;

public abstract class AbstractEndpoint extends AbstractMarshallingPayloadEndpoint {
	protected SimulacaoService simulacaoService;
	
	public void setSimulacaoService(SimulacaoService simulacaoService) {
		this.simulacaoService = simulacaoService;
	}
	
	protected abstract Object invokeInternal(Object request) throws Exception;
}
