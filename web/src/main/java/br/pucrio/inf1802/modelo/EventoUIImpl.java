package br.pucrio.inf1802.modelo;
import  br.pucrio.inf1802.definicao.EventoUI;

public class EventoUIImpl implements EventoUI {

	
	private String id;
	private String tipo;
	private String inicio;
	private String fim;
	private String valor;
	private String idObjetoMovel;
	
	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getTipo() {
		return tipo;
	}

	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public String getInicio() {
		return inicio;
	}

	
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	
	public String getFim() {
		return fim;
	}

	
	public void setFim(String fim) {
		this.fim = fim;
	}

	
	public String getValor() {
		return valor;
	}

	
	public void setValor(String valor) {
		this.valor = valor;
	}

	
	public String getIdObjetoMovel() {
		return idObjetoMovel;
	}

	
	public void setIdObjetoMovel(String idObjetoMovel) {
		this.idObjetoMovel = idObjetoMovel;
	}
	
	

}
