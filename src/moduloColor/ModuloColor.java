package moduloColor;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class ModuloColor extends CommunicationFacade {

	// cria��o do controlador de uma classe deste m�dulo
	ColorController sc = new ColorController(this, true);
	
	// para impedir a entrada de eventos indesej�veis
	public void filtrarEventoEntrada(CommunicationEvent ie) {		
		if(ie instanceof ColorEvent)
			disseminarEventoInternamente(ie);
	}

	// para impedir a sa�da desnecess�ria de eventos
	public void filtrarEventoSaida(CommunicationEvent ie) {
		disseminarEventoExternamente(ie);
	}
}
