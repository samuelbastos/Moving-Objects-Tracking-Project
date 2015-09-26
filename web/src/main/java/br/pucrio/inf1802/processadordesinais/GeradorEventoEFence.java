package br.pucrio.inf1802.processadordesinais;

import br.pucrio.inf1802.processamento.GeradorDeEvento;

/**
 * Gerador de eventos de cerca eletrônica.
 * 
 * Deve gerar eventos de entrada e saída de uma determinada área de interesse.
 * 
 * @author rodrigof, fmoura
 *
 */
public interface GeradorEventoEFence extends GeradorDeEvento {

	/**
	 * Coordenada x mínima da área de interesse em WGS84
	 * 
	 * @param xmin
	 */
	void setXmin(double xmin);

	/**
	 * Coordenada x máxima da área de interesse em WGS64
	 * 
	 * @param xmax
	 */
	void setXMax(double xmax);

	/**
	 * Coordenada y mínima da área de interesse em WGS64
	 * 
	 * @param ymin
	 */
	void setYmin(double ymin);

	/**
	 * Coordenada y máxima da área de interesse em WGS64
	 * 
	 * @param ymax
	 */
	void setYMax(double ymax);

}
