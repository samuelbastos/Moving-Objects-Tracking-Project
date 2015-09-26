package br.pucrio.inf1802.processadordesinais;

import br.pucrio.inf1802.definicaoprocessador.ObjetoMovel;
import br.pucrio.inf1802.definicaoprocessador.Sinal;

public class ObjetoMovelImpl implements ObjetoMovel {

	private String id;

	private Sinal ultimoSinal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Sinal getUltimoSinal() {
		return ultimoSinal;
	}

	public void setUltimoSinal(Sinal ultimoSinal) {
		this.ultimoSinal = ultimoSinal;
	}

}
