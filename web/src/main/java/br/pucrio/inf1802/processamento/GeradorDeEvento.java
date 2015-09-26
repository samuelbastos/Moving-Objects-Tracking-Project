package br.pucrio.inf1802.processamento;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

/**
 * Classe responsável por gerar um tipo de evento de acordo com os sinais
 * recebidos.
 * 
 * @author rodrigof, fmoura
 *
 */
public interface GeradorDeEvento {

	/**
	 * Método para gerar eventos de acordo com o sinal recebido.
	 * 
	 * @param sinal
	 * @return evento
	 */
	Evento gerarEvento(Sinal sinal);

}
