package br.pucrio.inf1802.processadordesinais;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

public class GeradorEventoEFenceImpl implements GeradorEventoEFence {
	
	
	private Map<String,Boolean> estadosAtuais = new HashMap<String, Boolean>();
	
	double xmin,xmax,ymin,ymax;

	public Evento gerarEvento(Sinal sinal) {
		boolean estavaDentro = estadosAtuais.get(sinal.getObjetoMovel().getId()) != null ? estadosAtuais.get(sinal.getObjetoMovel().getId()) : false ;
		 
		
		boolean estaDentro = false;
		if(sinal.getLon() >= xmin && sinal.getLon() <= xmax){
			if(sinal.getLat() >= ymin && sinal.getLat() <= ymax){
				estaDentro = true;
			}
		}
		
		estadosAtuais.put(sinal.getObjetoMovel().getId(), estaDentro);
		
		String tipoEvento = null;
		if(estavaDentro && !estaDentro){
			tipoEvento = TipoEvento.SAIDA_AREA_CONHECIDA.name();
		}
		else if(!estavaDentro && estaDentro){
			tipoEvento = TipoEvento.ENTRADA_AREA_CONHECIDA.name();
		}else{
			return null;
		}
		
		return new EventImpl(UUID.randomUUID().toString(),tipoEvento,sinal.getData(),sinal.getData(),sinal.getObjetoMovel(),""+sinal.getLon()+","+sinal.getLat());
		
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public void setXMax(double xmax) {
		this.xmax = xmax;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}

	public void setYMax(double ymax) {
		this.ymax = ymax;
	}

}
