package br.pucrio.inf1802.processadordesinais;

import java.util.Date;

import br.pucrio.inf1802.definicaoprocessador.ObjetoMovel;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

public class SinalImpl implements Sinal {

	private String id;

	private Double lon;

	private Double lat;

	private ObjetoMovel objetoMovel;

	private Date data;

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

	public ObjetoMovel getObjetoMovel() {
		return objetoMovel;
	}

	public void setObjetoMovel(ObjetoMovel objetoMovel) {
		this.objetoMovel = objetoMovel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}