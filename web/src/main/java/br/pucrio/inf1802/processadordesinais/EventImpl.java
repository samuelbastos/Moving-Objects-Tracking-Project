package br.pucrio.inf1802.processadordesinais;

import java.util.Date;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.ObjetoMovel;

public class EventImpl implements Evento {
	
	
	private String id;
	private String tipo;
	private Date inicio;
	private Date fim;
	private ObjetoMovel objetoMovel;
	private String valor;
	
	
	
	
	
	public EventImpl(String id, String tipo, Date inicio, Date fim,
			ObjetoMovel objetoMovel, String valor) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.inicio = inicio;
		this.fim = fim;
		this.objetoMovel = objetoMovel;
		this.valor = valor;
	}
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
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public ObjetoMovel getObjetoMovel() {
		return objetoMovel;
	}
	public void setObjetoMovel(ObjetoMovel objetoMovel) {
		this.objetoMovel = objetoMovel;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

	}
