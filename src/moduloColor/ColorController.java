package moduloColor;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class ColorController extends CommunicationController {
	
	// criação do objeto controlado
	ColorFrame cf; // = new ColorFrame(this);

	public ColorController(CommunicationFacade module, boolean getEvents) {
		super(module, getEvents);
	}

	// tratamento de eventos pelo objeto controlado 
	public void tratarEventoRecebido(CommunicationEvent e) {
		String cmd = e.getCommand();
		
		if(cmd.equals(ColorEvent.OPEN_COLOR_PANE))
			cf = new ColorFrame(this);
	}

}
