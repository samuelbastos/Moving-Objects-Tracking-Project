package br.pucrio.inf1802.processamento;

import java.util.List;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

/**
 * 
 * Classe responsável por processar sinais utilizando os geradores de evento
 * 
 * @author rodrigof, fmoura
 *
 */
public interface ProcessadorDeSinais {

	/**
	 * Registrar um novo gerador de Evento no processador de sinais
	 * 
	 * @param geradorDeEvento
	 */
	void adicionarGerador(GeradorDeEvento geradorDeEvento);

	/**
	 * Método que processa os sinais utilizando os geradores de evento
	 * registrados
	 * 
	 * @param sinais
	 * @return lista de eventos
	 */
	List<Evento> processarSinais(List<Sinal> sinais);
}
