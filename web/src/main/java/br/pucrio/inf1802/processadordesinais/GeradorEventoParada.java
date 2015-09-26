package br.pucrio.inf1802.processadordesinais;

import br.pucrio.inf1802.processamento.GeradorDeEvento;

/**
 * Gerador de eventos responsável por detectar se um Objeto Móvel está parado ou
 * em movimento.
 * 
 * Dada uma tolerancia, o gerador criará eventos de Parada ou Movimento de
 * acordo com os dados dos sinais fornecidos.
 * 
 * @author rodrigof, fmoura
 *
 */
public interface GeradorEventoParada extends GeradorDeEvento {

	/**
	 * Define a distância de tolerancia em metros para a geração de eventos de Parada ou
	 * de Movimento
	 * 
	 * @param tolerancia
	 */
	void setToleranciaDistancia(double toleranciaDistancia);
	
	/**
	 * Define o tempo em segundos que um Objeto Movel deve
	 * permanecer "no mesmo local" para que seja considerado
	 * parado.
	 * 
	 * @param tempoParado
	 */
	void setTempoParado(int tempoParado);

}
