package difusor.i18N.menu;

import difusor.evento.CommunicationEvent;

public class CommunicationMenuEvent extends CommunicationEvent {

	public CommunicationMenuEvent(Object source, String command) {
		super(source, command);
	}

	public String getDescription() {
		return "Evento criado com o objetivo de notificar a intera��o (clique) do usu�rio " +
				"com o menu " + getSource() + ".\nGerado na classe pr�pria " + getSource() + " " +
				"sem um destino espec�fico; todas as classes que tiverem interesse no comando " +
				getCommand() + " poder�o se registrar e receber c�pias deste evento.";
	}
}
