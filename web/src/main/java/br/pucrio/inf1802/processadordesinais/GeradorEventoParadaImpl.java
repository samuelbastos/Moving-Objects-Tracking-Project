package br.pucrio.inf1802.processadordesinais;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Coordinate;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

public class GeradorEventoParadaImpl implements GeradorEventoParada {

	Map<String,List<Sinal>> mapaSinaisPassados = new HashMap<String, List<Sinal>>() ;
	Map<String,Boolean> mapaEstavaParado = new HashMap<String, Boolean>();
	Map<String,Evento> mapaEventoAnterior = new HashMap<String, Evento>();
	
	private double toleranciaDistancia;
	private int tempoParado;
	
	

	public Evento gerarEvento(Sinal sinal) {
		
		if(!mapaSinaisPassados.containsKey(sinal.getObjetoMovel().getId())){
			mapaSinaisPassados.put(sinal.getObjetoMovel().getId(), new ArrayList<Sinal>());
		}
		
		
		List<Sinal> sinaisPassados = mapaSinaisPassados.get(sinal.getObjetoMovel().getId());
		
		long tempoGuardadoNaFila = sinaisPassados.size() == 0? 0 : (  (sinal.getData().getTime() - sinaisPassados.get(0).getData().getTime()) / 1000  );
		
		while(tempoGuardadoNaFila > tempoParado){
			sinaisPassados.remove(0);
			tempoGuardadoNaFila = sinaisPassados.size() == 0? 0 : (  (sinal.getData().getTime() - sinaisPassados.get(0).getData().getTime()) / 1000  );
		}
		
		sinaisPassados.add(sinal);
		
		boolean estaParado = estaParado(sinaisPassados);
		boolean estavaParado = mapaEstavaParado.get(sinal.getObjetoMovel().getId()) != null ? mapaEstavaParado.get(sinal.getObjetoMovel().getId()) : false ;
		
		mapaEstavaParado.put(sinal.getObjetoMovel().getId(), estaParado);
		 

		String tipoEvento = null;
		if(estavaParado && !estaParado){
			tipoEvento = TipoEvento.MOVIMENTO.name();
		}
		else if(!estavaParado && estaParado){
			tipoEvento = TipoEvento.PARADA.name();
		}else{
			return null;
		}
		
		Evento evento = new EventImpl(UUID.randomUUID().toString(),tipoEvento,sinal.getData(),null,sinal.getObjetoMovel(),""+sinal.getLon()+","+sinal.getLat());
		
		Evento eventoAnterior = mapaEventoAnterior.get(sinal.getObjetoMovel().getId());
		
		mapaEventoAnterior.put(sinal.getObjetoMovel().getId(), evento);
		
		if(eventoAnterior != null){
			eventoAnterior.setFim(sinal.getData());
		}
		
		return eventoAnterior;
		
	}
	
	
	private boolean estaParado(List<Sinal> sinais){
		
		if(sinais.size() <= 1) return false;
		
		for(Sinal sinaCorrente : sinais){
			for(Sinal sinal : sinais){
				double distancia = calcDistance(sinal, sinaCorrente);
				if(distancia > toleranciaDistancia) return false;
			}
		}
		return true;
		
	}
	
	
	

	public void setToleranciaDistancia(double toleranciaDistancia) {
		this.toleranciaDistancia = toleranciaDistancia;

	}

	public void setTempoParado(int tempoParado) {
		this.tempoParado = tempoParado;

	}
	
	double calcDistance(Sinal sinalAnterior, Sinal sinal){
		
		
		try {
			CoordinateReferenceSystem crs = CRS.decode("EPSG:3857",true);
			MathTransform transform;
			transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, crs);
			Coordinate s1 = JTS.transform(new Coordinate(sinalAnterior.getLon(),sinalAnterior.getLat()), null, transform);
			Coordinate s2 = JTS.transform(new Coordinate(sinal.getLon(),sinal.getLat()), null, transform);
			
			return Math.sqrt(Math.pow(s2.x-s1.x, 2)+Math.pow(s2.y-s1.y, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0.0;
	
	}

}
