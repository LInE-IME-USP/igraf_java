package difusor.i18N.menu;

import java.awt.Component;

import javax.swing.JMenuBar;

import difusor.controle.CommunicationController;
import difusor.i18N.LanguageUpdatable;

/**
 * Barra de menu gen�rica que implementa o modelo de internacionaliza��o
 * definido para ser reutilizado pelos programas da fam�lia iMA.  Para que
 * um jframe utilize uma barra de menu que herda desta classe, basta que
 * o jframe receba a classe herdeira desta como argumento pelo seu construtor.
 * A referida classe herdeira deve ser criada como campo do mesmo controlador
 * do frame.
 * 
 * @author Reginaldo do Prado
 *
 */
public abstract class CommunicationMenuBar extends JMenuBar 
implements LanguageUpdatable{

	private CommunicationController controller;
	public CommunicationMenuBar(CommunicationController controller) {
		this.controller = controller;
	}
	
	/**
	 * M�todo respons�vel pela atualiza��o dos labels dos
	 * menus que fazem parte desta barra de menu. A defini��o
	 * de quais r�tulos ou quais valores ser�o utilizados na
	 * atualiza��o � de responsabilidade do menu que dever�
	 * ser subclasse de CommunicationMenu.
	 */
	public void updateLabels(){
		Component[] menus = getComponents();
		
		for (int i = 0; i < menus.length; i++) {
			CommunicationMenu menu = (CommunicationMenu)menus[i];
			menu.updateLabels();
		}
		repaint();
	}
}
