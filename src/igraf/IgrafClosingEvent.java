package igraf;

import igraf.moduloArquivo.eventos.ModuloArquivoDisseminavelEvent;
import difusor.evento.CommunicationEvent;

public class IgrafClosingEvent extends CommunicationEvent implements ModuloArquivoDisseminavelEvent{
	
	public IgrafClosingEvent(Object source) {
		super(source);
	}

	public String getDescription() {
		return objetivo("notificar que o programa, em sua forma aplicativo, está sendo encerrado");
	}

}
