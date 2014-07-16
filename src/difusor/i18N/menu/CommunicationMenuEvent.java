package difusor.i18N.menu;

import difusor.evento.CommunicationEvent;

public class CommunicationMenuEvent extends CommunicationEvent {

	public CommunicationMenuEvent(Object source, String command) {
		super(source, command);
	}

	public String getDescription() {
		return "Evento criado com o objetivo de notificar a interação (clique) do usuário " +
				"com o menu " + getSource() + ".\nGerado na classe própria " + getSource() + " " +
				"sem um destino específico; todas as classes que tiverem interesse no comando " +
				getCommand() + " poderão se registrar e receber cópias deste evento.";
	}
}
