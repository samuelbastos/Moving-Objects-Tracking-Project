package br.pucrio.inf1802.processadordesinais;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

import com.vividsolutions.jts.geom.Coordinate;

public class GeradorEventoExcessoVelocidadeImpl implements
		GeradorEventoExcessoVelocidade {
	
	private Map<String,Sinal> sinaisAnteriores = new HashMap<String,Sinal>();
	private double limite = 0;

	public Evento gerarEvento(Sinal sinal) {
		Sinal sinalAterior = sinaisAnteriores.get(sinal.getObjetoMovel().getId());
		sinaisAnteriores.put(sinal.getObjetoMovel().getId(), sinal);
		
		if(sinalAterior == null){
			return null;
		}
		
		double deltaT = (sinal.getData().getTime() - sinalAterior.getData().getTime())/1000.0;
		deltaT = deltaT/3600.0;
		
		try{
			CoordinateReferenceSystem crs = CRS.decode("EPSG:3857",true);
			MathTransform transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, crs);
			
			
			Coordinate s1 = JTS.transform(new Coordinate(sinalAterior.getLon(),sinalAterior.getLat()), null, transform);
			Coordinate s2 = JTS.transform(new Coordinate(sinal.getLon(),sinal.getLat()), null, transform);
			
			double distance = Math.sqrt(Math.pow(s2.x-s1.x, 2)+Math.pow(s2.y-s1.y, 2))/1000.0;
			
			double velocity = distance/deltaT;
			
			if(velocity > limite){
				return new EventImpl(UUID.randomUUID().toString(), TipoEvento.EXCESSO_VELOCIDADE.name(), sinal.getData(), sinal.getData(), sinal.getObjetoMovel(), ""+velocity);
			}
			
		}catch(Exception e){
			System.err.println("Erro ao converter as coordenasdas");
			e.printStackTrace(System.err);
		}
		return null;
		
		
	}

	public void setLimite(double limite) {
		this.limite = limite;

	}

}
