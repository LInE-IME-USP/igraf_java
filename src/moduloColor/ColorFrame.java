package moduloColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorFrame extends JFrame implements ChangeListener{

	JColorChooser chooser = new JColorChooser();
	private ColorChangeListener listener;	
	private ColorController controller;
	
	public ColorFrame(ColorController controller) {
		this();
		this.controller = controller;	
	}
		
	
	public ColorFrame(ColorChangeListener listener){
		this();
		this.listener = listener;
	}


	public ColorFrame() {
		setSize(new Dimension(450, 360));		
		chooser.getSelectionModel().addChangeListener(this);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				notificaFechamento();
			}
		});
		
		add(chooser);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// evento padrão do Java aciona o controlador
	public void stateChanged(ChangeEvent e) {
		Color color = chooser.getColor();
		
		if(controller != null)
			controller.enviarEvento(new ColorEvent(this, color));
		else
			listener.updateColor(new ColorEvent(this, color));
	}
	
	private void notificaFechamento(){
		if(controller != null)
			controller.enviarEvento(new ColorEvent(this, ColorEvent.CLOSE_COLOR_PANE));
	}
}
