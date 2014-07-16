package moduloColor;

import java.awt.Color;

import difusor.evento.CommunicationEvent;

public class ColorEvent extends CommunicationEvent {

	private Color color;
	
	public static final String CLOSE_COLOR_PANE = "close color pane";
	public static final String OPEN_COLOR_PANE = "open color pane";
	public static final String CHANGING_COLOR = "changing color";

	public ColorEvent(Object source, Color color) {
		super(source);
		this.color = color;
		setCommand(CHANGING_COLOR);
	}
	
	public ColorEvent(Object source, String cmd) {
		super(source);
		setCommand(cmd);
	}

	public Color getColor() {
		return color;
	}
	
	public String getDescription() {
		return "Evento gerado em " + getSource() + " sempre que o usuário seleciona uma cor.";
	}

}
