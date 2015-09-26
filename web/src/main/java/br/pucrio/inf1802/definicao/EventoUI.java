package br.pucrio.inf1802.definicao;

public interface EventoUI {

	/**
	 * Recupera o identificador para esse evento
	 * @return o identificador desse evento
	 */
	String getId();

	/**
	 * Configura o identificador para esse evento
	 * @param id o identificador para esse evento
	 */
	void setId(String id);

	/**
	 * Recupera o tipo desse evento.
	 * @return o tipo desse evento.
	 */
	String getTipo();

	/**
	 * Configura o tipo desse evento
	 * @param tipo o tipo para esse evento
	 */
	void setTipo(String tipo);

	/**
	 * Recupera a data de incio desse evento
	 * @return a data de inicio desse evento
	 */
	String getInicio();

	/**
	 * Configura a data de incio desse evento
	 * @param inicio a data de incio para esse evento
	 */
	void setInicio(String inicio);

	/**
	 * Recupera a data de fim desse evento
	 * @return a data de fim desse evento
	 */
	String getFim();

	/**
	 * Configura a data de fim desse evento
	 * @param fim a data de fim para esse evento
	 */
	void setFim(String fim);

	/**
	 * Recupera o valor desse evento. 
	 * O valor repesenta uma caracteristica especifica do tipo de evento, ex: Evento de excesso de velocidade, o valor seria a velocidade aferida 
	 * @return o valor desse evento
	 */
	String getValor();

	/**
	 * Configura o valor desse evento
	 * @param valor o valor para esse evento
	 */
	void setValor(String valor);

	/**
	 * Recupera o id do objeto movel que gerou esse evento.
	 * Esse é o objeto movel cujos sinais processados geraram este eveto
	 * @return o objeto movel que gerou esse evento
	 */
	String getIdObjetoMovel();

	/**
	 * Configura o id do objeto movel que gerou esse evento
	 * @param objetoMovel o obejto movel que gerou esse evento
	 */
	void setIdObjetoMovel(String objetoMovel);
}
