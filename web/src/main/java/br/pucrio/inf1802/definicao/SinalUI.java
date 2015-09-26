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
	 * Recupera a informação de longitude desse sinal
	 * @return o valor da longitude na projeção WGS 84
	 */
	Double getLon();

	/**
	 * Configura a informação de longitude para esse sinal
	 * @param lon valor da longitude na projeção WGS 84
	 */
	void setLon(Double lon);

	/**
	 * Recupera a informação de latitude para esse sinal
	 * @return o valor da latitude na projeção WGS 84
	 */
	Double getLat();

	/**
	 * Configura a informação de latitude para esse sinal
	 * @param lat o valor da latitude na projeção WGS 84
	 */
	void setLat(Double lat);
	
	/**
	 * Recupera a informação do id do objeto movel associado ao sinal
	 * @return id do objeto movel associado ao sinal
	 */
	String GetIdObjetoMovel ();
	
	/**
	 * Configura a informação do id do objeto movel associado ao sinal
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
