package br.pucrio.inf1802.modelo;
import br.pucrio.inf1802.definicao.SinalUI;

public class SinalUIImpl implements SinalUI{

	private String id;
	private Double lon;
	private Double lat;
	private String idObjetoMovel;
	private String data;
	
	
	public String getId() {
		
		return id;
	}


	public void setId(String id) {
		this.id = id;
		
	}

	
	public Double getLon() {
		
		return lon;
	}

	
	public void setLon(Double lon) {
		this.lon = lon;
		
	}

	
	public Double getLat() {
		
		return lat;
	}

	
	public void setLat(Double lat) {
		this.lat = lat;
		
	}

	
	public String GetIdObjetoMovel() {
		
		return idObjetoMovel;
	}

	
	public void setIdObjetoMovel(String idObjetoMovel) {
		this.idObjetoMovel = idObjetoMovel;
		
	}

	
	public String getData() {
		
		return data;
	}

	
	public void setData(String data) {
		this.data = data;
		
	}

}
