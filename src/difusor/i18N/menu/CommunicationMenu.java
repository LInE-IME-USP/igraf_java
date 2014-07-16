package difusor.i18N.menu;

import javax.swing.JMenu;

import difusor.controle.CommunicationController;
import difusor.i18N.LanguageUpdatable;

/**
 * Classe que implementa um menu genérico, subclasse de JMenu,
 * com suporte à internacionalização e adaptada ao modelo de
 * comunicação implementado no módulo de broadcasting.
 * 
 * @author Reginaldo do Prado
 *
 */
public abstract class CommunicationMenu extends JMenu 
implements LanguageUpdatable{

	private CommunicationController controller;
	public CommunicationMenu(CommunicationController controller) {
		this.controller = controller;
	}	
}
