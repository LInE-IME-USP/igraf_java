package difusor.i18N.menu;

import javax.swing.JPopupMenu;

import difusor.controle.CommunicationController;
import difusor.i18N.LanguageUpdatable;

public abstract class CommunicationPopupMenu extends JPopupMenu 
implements LanguageUpdatable{
	
	private CommunicationController controller;
	public CommunicationPopupMenu(CommunicationController controller) {
		this.controller = controller;
	}

}
