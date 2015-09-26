package br.pucrio.inf1802.processadordesinais;

import br.pucrio.inf1802.processamento.GeradorDeEvento;

/**
 * Gerador de eventos de excesso de velocidade
 * 
 * @author rodrigof, fmoura
 *
 */
public interface GeradorEventoExcessoVelocidade extends GeradorDeEvento {

	/**
	 * Método que determinar o limite de velocidade do gerador de eventos
	 * 
	 * @param limite
	 */
	void setLimite(double limite);

}