package br.pucrio.inf1802.definicaoprocessador;

import java.util.Date;

/**
 * Classe que representa um sinal de localiza��o de um objeto movel em um dado instante
 * @author rodrigof,fmoura
 *
 */
public interface Sinal {

	/**
	 * Recurpera o identificador desse sinal
	 * @return
	 */
	String getId();

	/**
	 * Configura o identificador para esse sinal
	 * @param id
	 */
	void setId(String id);

	/**
	 * Recupera a informa��o de longitude desse sinal
	 * @return o valor da longitude na proje��o WGS 84
	 */
	Double getLon();

	/**
	 * Configura a informa��o de longitude para esse sinal
	 * @param lon valor da longitude na proje��o WGS 84
	 */
	void setLon(Double lon);

	/**
	 * Recupera a informa��o de latitude para esse sinal
	 * @return o valor da latitude na proje��o WGS 84
	 */
	Double getLat();

	/**
	 * Configura a informa��o de latitude para esse sinal
	 * @param lat o valor da latitude na proje��o WGS 84
	 */
	void setLat(Double lat);
	
	/**
	 * Objeto movel localizado por esse sinal
	 * @return o {@link ObjetoMovel} localizado por esse sinal
	 */
	ObjetoMovel getObjetoMovel();
	
	/**
	 * Configura o objeto movel localizado por esse sinal
	 * @param objetoMovel O objeto movel localizado por esse sinal
	 */
	void setObjetoMovel(ObjetoMovel objetoMovel);
	
	/**
	 * Recurpera a data em que esse sinal foi gerado
	 * @return A {@link Date data} em que esse sinal foi gerado
	 */
	Date getData();
	
	/**
	 * Configura a data em que esse sinal foi gerado
	 * @param data
	 */
	void setData(Date data);

}
