package difusor;


public abstract class Comunicador {
	// cria o comunicador
	private Broadcaster b = new Broadcaster();
	
	public Comunicador(String tableLocation){
		// inicia o leitor de strings
		new ResourceReader(tableLocation);
	}
	
	/**
	 * Método que recebe como parâmetro um módulo e o habilita a enviar
	 * mensagens para outras partes do sistema.
	 * @param modulo
	 */
	public void habilitarEnvioMensagem(CommunicationFacade modulo){
		modulo.addBroadcaster(b);
	}
	
	/**
	 * Método que recebe como parâmetro um módulo e o habilita a receber
	 * mensagens de outras partes do sistema.
	 * @param modulo
	 */
	public void habilitarRecepcaoMensagem(CommunicationFacade modulo){
		b.addModule(modulo);
	}

}
