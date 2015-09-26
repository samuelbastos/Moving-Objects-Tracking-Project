package br.pucrio.inf1802.definicaoprocessador;

import java.util.Date;

/**
 * Classe que representa um evento. 
 * Eventos são acontecimentos extraidos a partir do processamento dos {@link Sinal sinais} brutos recebidos.
 * Um evento responde às seguintes perguntas para um acontecimento:
 * Qual o evento?
 * Quando ocorreu o evento?
 * Quem gerou o evento?
 * @author fmoura,rodrigof
 *
 */
public interface Evento {

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
	 * Recupera a {@link Date data} de incio desse evento
	 * @return a data de inicio desse evento
	 */
	Date getInicio();

	/**
	 * Configura a {@link Date data} de incio desse evento
	 * @param inicio a data de incio para esse evento
	 */
	void setInicio(Date inicio);

	/**
	 * Recupera a {@link Date data} de fim desse evento
	 * @return a data de fim desse evento
	 */
	Date getFim();

	/**
	 * Configura a {@link Date data} de fim desse evento
	 * @param fim a data de fim para esse evento
	 */
	void setFim(Date fim);

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
	 * Recupera o {@link ObjetoMovel objeto movel} que gerou esse evento.
	 * Esse é o objeto movel cujos {@link Sinal sinais} processados geraram este eveto
	 * @return o objeto movel que gerou esse evento
	 */
	ObjetoMovel getObjetoMovel();

	/**
	 * Configura o {@link ObjetoMovel objeto movel} que gerou esse evento
	 * @param objetoMovel o obejto movel que gerou esse evento
	 */
	void setObjetoMovel(ObjetoMovel objetoMovel);

}
