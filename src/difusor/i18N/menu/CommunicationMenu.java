package difusor.i18N.menu;

import javax.swing.JMenu;

import difusor.controle.CommunicationController;
import difusor.i18N.LanguageUpdatable;

/**
 * Classe que implementa um menu gen�rico, subclasse de JMenu,
 * com suporte � internacionaliza��o e adaptada ao modelo de
 * comunica��o implementado no m�dulo de broadcasting.
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
