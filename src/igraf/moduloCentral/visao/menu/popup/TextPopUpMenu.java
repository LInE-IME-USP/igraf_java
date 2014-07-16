package igraf.moduloCentral.visao.menu.popup;

import igraf.basico.io.ResourceReader;
import difusor.controle.CommunicationController;
import difusor.i18N.menu.CommunicationMenuItem;
import difusor.i18N.menu.CommunicationPopupMenu;

public class TextPopUpMenu extends CommunicationPopupMenu {	
	private CommunicationMenuItem  editMenuItem;
	private CommunicationMenuItem  deleteMenuItem;
	
	CommunicationController controller;
	public TextPopUpMenu(CommunicationController controller) {
		super(controller);		
		editMenuItem   = new CommunicationMenuItem(controller);
		deleteMenuItem = new CommunicationMenuItem(controller);
		updateLabels();
	}
		
	public void updateLabels() {
		editMenuItem.setText(ResourceReader.msg("USO"));
		deleteMenuItem.setText(ResourceReader.msg("USO"));
	}
}
