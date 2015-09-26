package br.pucrio.inf1802.processadordesinais;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.pucrio.inf1802.definicaoprocessador.Evento;
import br.pucrio.inf1802.definicaoprocessador.ObjetoMovel;
import br.pucrio.inf1802.definicaoprocessador.Sinal;
import br.pucrio.inf1802.modelo.EventoUIImpl;
import br.pucrio.inf1802.modelo.SinalUIImpl;
import br.pucrio.inf1802.modelo.VeiculoImpl;
import br.pucrio.inf1802.processamento.ProcessadorDeSinais;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProcessadorThread extends Thread{

	private static final long tempoDormindo = 10; //em segundos
	
	public void run() {
		System.out.println("thread rodando");
		while(true){
			
			System.out.println("=====================");
			System.out.println("Thread acordada!!");
			
			// 1) Instaciar os Geradores de Eventos
			GeradorEventoEFence geradorEFence = new GeradorEventoEFenceImpl();
			geradorEFence.setXmin(-22.975846);
			geradorEFence.setYmin(-43.229386);
			geradorEFence.setXMax(-22.974619);
			geradorEFence.setYMax(-43.227701);

			GeradorEventoParada geradorParada = new GeradorEventoParadaImpl();
			geradorParada.setToleranciaDistancia(25.0);
			geradorParada.setTempoParado(4);

			GeradorEventoExcessoVelocidade geradorExcessoVelocidade = new GeradorEventoExcessoVelocidadeImpl();
			geradorExcessoVelocidade.setLimite(100);

			// 2) Instanciar e configurar o Processador de Sinais;
			ProcessadorDeSinais processador = new ProcessadorDeSinaisImpl();
			processador.adicionarGerador(geradorParada);
			processador.adicionarGerador(geradorExcessoVelocidade);
			processador.adicionarGerador(geradorEFence);

			// 3) Pegar sinais via ClienteRest e deserializar	
			List<Sinal> sinais = new ArrayList<Sinal>();
			
			String respostaSinal = ClienteRest.novaRequisicao("GET",
					"http://localhost:8080/web/sinal?p=false", null);
			
			Gson gson = new Gson();

			List<SinalUIImpl> sinaisDeserialized = gson.fromJson(respostaSinal, new TypeToken<List<SinalUIImpl>>(){}.getType());
			
			// 4) Popular lista de sinais para ser enviada pro processador	
			for (SinalUIImpl sinal : sinaisDeserialized)
			{
				ObjetoMovel objeto = new ObjetoMovelImpl();
				Sinal novo = new SinalImpl();
				Date  novaData = new Date();
				
				//popula a instancia de ObjetoMovel para que possa ser usada em setObjetoMovel
				objeto.setId(sinal.GetIdObjetoMovel());
				
				//popula a Date com a string recebido do sinal
				novaData.setTime(Long.parseLong(sinal.getData(), 10));
				
				//popula instancia de sinal
				novo.setId(sinal.getId());
				novo.setLat(sinal.getLat());
				novo.setLon(sinal.getLon());
				novo.setData(novaData);
				novo.setObjetoMovel(objeto);
				sinais.add(novo); 
			}
			
			// 5) Processa os sinais, retorna uma lista com os eventos
			List<Evento> eventos = processador.processarSinais(sinais);
		
			
			// 6) Salva os eventos gerados no banco de dados
			for (Evento evento : eventos)
			{
				EventoUIImpl auxiliar = new EventoUIImpl();
				auxiliar.setIdObjetoMovel(evento.getObjetoMovel().getId());
				auxiliar.setInicio(String.valueOf(evento.getInicio().getTime()));
				auxiliar.setFim(String.valueOf(evento.getFim().getTime()));
				auxiliar.setTipo(evento.getTipo());
				auxiliar.setValor(evento.getValor());
				
				String respostaEvento = ClienteRest.novaRequisicao("POST",
						"http://localhost:8080/web/evento", gson.toJson(auxiliar));	
			}
			
			// 7) Atualiza os sinais processados no banco de dados
			for (SinalUIImpl sinal : sinaisDeserialized)
			{
				String index = sinal.getId();
				
				String respostaSinalprocessado = ClienteRest.novaRequisicao("GET",
						"http://localhost:8080/web/sinal/" + index, null);
				
				String duh = respostaSinalprocessado.replace("\r", "");
				
				String processa = ClienteRest.novaRequisicao("PUT", "http://localhost:8080/web/sinal/" + index, duh);
			}
			
	
			// 8) Atualiza o ultimo sinal do devido veiculo
			String veiculos = ClienteRest.novaRequisicao("GET", "http://localhost:8080/web/veiculo", null);
			List<VeiculoImpl> veiculosDeserialized = gson.fromJson(veiculos, new TypeToken<List<VeiculoImpl>>(){}.getType());
			
			for (VeiculoImpl veiculo : veiculosDeserialized)
			{
				String idveiculo = veiculo.getId();
				String ultimoSinal = new String ();
				
				for (SinalUIImpl sinal : sinaisDeserialized)
				{
					String index = sinal.GetIdObjetoMovel();
					
					if (index.equals(idveiculo)){
						ultimoSinal = sinal.getId();	
					}
														
				}
				
				veiculo.setUltimoId(ultimoSinal);
				
				String updateveiculo = ClienteRest.novaRequisicao("PUT",
						"http://localhost:8080/web/veiculo/" + idveiculo, gson.toJson(veiculo));			
			}
						
			// 4) Print dos eventos
			for (Evento evento : eventos) {
				System.out.println("========");
				System.out.println(evento.getTipo());
				System.out.println(evento.getValor());
				System.out.println(evento.getInicio());
				System.out.println(evento.getFim());
				System.out.println(evento.getObjetoMovel().getId());
			}
			
			try {
				System.out.println("Thread dormindo por " + tempoDormindo + " segundos");
				sleep(tempoDormindo * 1000);
			} catch (InterruptedException e) {
				System.out.println("Tempo dormindo interrompido");
			}
			
		}
		
	}
}
