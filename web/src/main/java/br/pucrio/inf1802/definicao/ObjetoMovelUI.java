package br.pucrio.inf1802.definicao;

/**
 * Objeto Móvel
 * Qualquer objeto que possa ser localizado no globo terrestre
 * Sua localização se altera com o tempo
 * @author sbastos
 *
 */
public interface ObjetoMovelUI {

	/**
	 * Configura o identificador desse objeto movel
	 * @param id O valor do identificador a ser configurado
	 */
	void setId (String id);
	
	/**
	 * Recupera o identificador desse objeto movel
	 * @return O valor do identificador desse objeto movel
	 */
	String getId ();
	
	/**
	 * Configura o ultimo identificador desse objeto movel
	 * @param ultimoId
	 */
	void setUltimoId (String ultimoId);
	
	/**
	 * Recupera o ultimo identificador desse objeto movel
	 * @return O valor do ultimo identificador desse objeto movel
	 */
	String getUltimoId ();
	
	void setPlaca (String placa);
	
	String getPlaca ();

	void setModelo (String modelo);
	
	String getModelo ();
}
