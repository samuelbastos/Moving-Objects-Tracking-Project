package br.pucrio.inf1802.modelo;
import br.pucrio.inf1802.definicao.ObjetoMovelUI;

public class VeiculoImpl implements ObjetoMovelUI{

	
	private String id;
	private String ultimoId;
	private String placa;
	private String modelo;
	
	
	public void setId(String id) {
		this.id = id;
		
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setUltimoId(String ultimoId) {
		this.ultimoId = ultimoId;
		
	}

	
	public String getUltimoId() {
		
		return ultimoId;
	}

	
	public void setPlaca(String placa) {
		this.placa = placa;
		
	}

	
	public String getPlaca() {
		
		return placa;
	}

	
	public void setModelo(String modelo) {
		this.modelo = modelo;
		
	}

	
	public String getModelo() {

		return modelo;
	}
	

}
