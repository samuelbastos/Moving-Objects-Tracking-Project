package br.pucrio.inf1802.definicao;

public interface SinalUI {

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
	 * Recupera a informa��o do id do objeto movel associado ao sinal
	 * @return id do objeto movel associado ao sinal
	 */
	String GetIdObjetoMovel ();
	
	/**
	 * Configura a informa��o do id do objeto movel associado ao sinal
	 * @param idObjetoMovel
	 */
	void setIdObjetoMovel (String idObjetoMovel);
	
	/**
	 * Recurpera a data em que esse sinal foi gerado
	 * @return A data em que o sinal foi gerado
	 */
	String getData();
	
	/**
	 * Configura a data em que esse sinal foi gerado
	 * @param data
	 */
	void setData(String data);
}
