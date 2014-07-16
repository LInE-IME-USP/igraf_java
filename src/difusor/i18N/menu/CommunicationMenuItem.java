package difusor.i18N.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import difusor.controle.CommunicationController;

/**
 * Item de menu que implementa o modelo de comunicação por broadcasting
 * definido como padrão para os programas da família iMA. Um objeto desta
 * classe, por padrão, envia um evento do tipo CommunicationMenuEvent e
 * define para este evento um comando (string), acessível pela chamada 
 * ao seu método getCommand(), de mesmo nome que seu rótulo. 
 * 
 * @author Reginaldo do Prado
 *
 */
public class CommunicationMenuItem extends JMenuItem 
implements ActionListener{

	private CommunicationController controller;
	private boolean hasFather = false;
	
	public CommunicationMenuItem(CommunicationController controller) {
		this.controller = controller;
		addActionListener(this);
	}
	
	private JMenu father;
	public CommunicationMenuItem(CommunicationController controller, JMenu father) {
		this.controller = controller;
		addActionListener(this);
		this.father = father;
		hasFather = true;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(!hasFather)
			controller.enviarEvento(new CommunicationMenuEvent(this, getActionCommand()));
		else
			controller.enviarEvento(new CommunicationMenuEvent(this, father.getActionCommand() + " " +getActionCommand()));
	}	
	
}
