package moduloColor;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class ModuloColor extends CommunicationFacade {

	// criação do controlador de uma classe deste módulo
	ColorController sc = new ColorController(this, true);
	
	// para impedir a entrada de eventos indesejáveis
	public void filtrarEventoEntrada(CommunicationEvent ie) {		
		if(ie instanceof ColorEvent)
			disseminarEventoInternamente(ie);
	}

	// para impedir a saída desnecessária de eventos
	public void filtrarEventoSaida(CommunicationEvent ie) {
		disseminarEventoExternamente(ie);
	}
}
