package br.vinicius.tcc.service;

import br.vinicius.tcc.entidade.Simulacao;

public interface SimulacaoService {
	//talvez terá que mudar para retornar a id do objeto salvo no banco
	public void simularAmbiente (Simulacao simulacao) throws SimulacaoException;
	public Simulacao buscarResultados (Integer id) throws SimulacaoException;
}
