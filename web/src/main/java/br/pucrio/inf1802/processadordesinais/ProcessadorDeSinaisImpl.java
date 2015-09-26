package br.pucrio.inf1802.processadordesinais;

import java.util.ArrayList;
import java.util.List;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.Sinal;
import br.pucrio.inf1802.processamento.GeradorDeEvento;
import br.pucrio.inf1802.processamento.ProcessadorDeSinais;

public class ProcessadorDeSinaisImpl implements ProcessadorDeSinais {

	List<GeradorDeEvento> geradoresDeEventos = new ArrayList<GeradorDeEvento>();
	
	public void adicionarGerador(GeradorDeEvento geradorDeEvento) {
		this.geradoresDeEventos.add(geradorDeEvento);
		
	}

	public List<Evento> processarSinais(List<Sinal> sinais) {
		List<Evento> eventos = new ArrayList<Evento>();
		
		for(Sinal sinal : sinais){
			for(GeradorDeEvento gerador : geradoresDeEventos){
				Evento evento = gerador.gerarEvento(sinal);
				
				if(evento != null){
					
					eventos.add(evento);
				}
			}
		}
		
		return eventos;
	}

}
