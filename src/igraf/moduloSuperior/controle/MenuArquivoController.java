/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 *
 * @description 
 * 
 * @see igraf/moduloSuperior/visao/MenuFile.java
 *
 */

package igraf.moduloSuperior.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.IgrafMenuArquivoEvent;
import igraf.moduloSuperior.visao.PainelSuperior;
import igraf.moduloSuperior.visao.MenuFile;


/**
 * Classe controladora do menu 'Arquivo', posicionado na barra de título do
 * programa e que só é visível na versão aplicativo. 
 * @author Administrador
 *
 */
public class MenuArquivoController extends CommunicationController implements ActionListener, MouseListener {

 private PainelSuperior ps;
 private MenuFile menu;
 
 public MenuArquivoController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }
 
 public void actionPerformed (ActionEvent e) {
  String cmd = e.getActionCommand();
  if (cmd.equals(ResourceReader.msg("arqMenuSaia"))) { // "Sair"
   // System.exit(0);
   IGraf.getInstanceIGraf().fecharIGraf();
   }
  else {
   enviarEvento(new IgrafMenuArquivoEvent(this, cmd));
   }
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie != null) {
   if (ie instanceof ChangeLanguageEvent) {
    if (!IGraf.ehApplet)menu.updateLabels(); 
    ps.updateLabels();
    }
   }
  }

 public void mouseClicked (MouseEvent e) {
  menu.show((JLabel)e.getSource(), 0, 27);
  }

 public void mouseEntered (MouseEvent e) { }
 public void mouseExited(MouseEvent e) { }
 public void mousePressed(MouseEvent e) { }
 public void mouseReleased(MouseEvent e) { }

 public void setMenu (MenuFile menu) {
  this.menu = menu;
  }

 public void setControlledObject (Object obj) {
  ps = (PainelSuperior)obj; 
  }

 }
