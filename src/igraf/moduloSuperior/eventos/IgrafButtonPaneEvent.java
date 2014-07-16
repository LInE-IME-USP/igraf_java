package igraf.moduloSuperior.eventos;

import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;

import javax.swing.JButton;

import difusor.evento.CommunicationEvent;



public class IgrafButtonPaneEvent extends CommunicationEvent
implements ModuloCentralDisseminavelEvent{
	
	public IgrafButtonPaneEvent(JButton source, int eventType) {
		super(source);
		this.x = source.getX();
		this.eventType = eventType;
		setCommand(source.getActionCommand());
	}
	
	public String getDescription() {
		return objetivo("notificar sobre o clique no painel de botões.");
	}

}
