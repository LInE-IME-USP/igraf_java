package igraf.moduloSuperior.eventos;

import difusor.evento.CommunicationEvent;

public class ModuloSuperiorEvent extends CommunicationEvent implements
		ModuloSuperiorDisseminavelEvent {

	String expr;
	public ModuloSuperiorEvent(Object source, String expr) {
		super(source);	
		this.expr = expr;
	}

	public String getExpressao() {
		return expr.substring(expr.indexOf("=")+2);
	}

	public String getDescription() {
		return objetivo("notificar sobre eventos no módulo superior.");
	}
}
