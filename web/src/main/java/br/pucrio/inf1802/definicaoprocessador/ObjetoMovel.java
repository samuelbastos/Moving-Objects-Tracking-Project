package br.pucrio.inf1802.definicaoprocessador;

/**
 * A classe objeto movel representa um objeto que se move na superficie do globo terrestre
 * @author fmoura,rodrigof
 *
 */
public interface ObjetoMovel {

	/**
	 * Recupera o identificador desse objeto movel
	 * @return O valor do identificador desse objeto movel
	 */
	String getId();
	
	/**
	 * Configura o identificador desse objeto movel
	 * @param id O valor do identificador a ser configurado
	 */
	void setId(String id);
	
	/**
	 * Recupera o ultimo sinal para esse objeto movel. Utilizado para descobrir rapidamente a localizacao atual 
	 * desse objeto em um sistema de tempo real
	 * @return o {@link Sinal} que representa o ultimo sinal recebido para este objeto movel
	 */
	Sinal getUltimoSinal();
	
	/**
	 * Configura o ultimo sinal recebido para esse obejto movel
	 * @param ultimoSinal O ultimo {@link Sinal} recebido
	 */
	void setUltimoSinal(Sinal ultimoSinal);
	
}
