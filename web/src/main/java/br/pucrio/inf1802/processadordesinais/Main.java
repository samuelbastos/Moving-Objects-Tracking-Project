package br.pucrio.inf1802.processadordesinais;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		ProcessadorThread thread = new ProcessadorThread();
		
		thread.start();
		thread.join();

	}

}
