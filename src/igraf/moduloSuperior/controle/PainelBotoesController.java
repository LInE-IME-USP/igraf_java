/*
 * iGraf - Interactive Graphics on the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author RP, LOB
 *
 * @description Starting class to send events to special menu controller (from menus): IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * 
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloSuperior.controle;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloInferior.eventos.IgrafDescriptionEvent;
import igraf.moduloSuperior.eventos.IgrafButtonPaneEvent;
import igraf.moduloSuperior.visao.PainelBotoes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;


/**
 * Classe controladora que recebe os eventos gerados pelo painel de botões
 * e os envia para o sistema de broadcast.
 */
public class PainelBotoesController extends CommunicationController implements MouseListener {

 private static final int VAR_X = 8, LIM_X=-1, LIM_Y_MIN=-1, LIM_Y_MAX=20;

 private JButton button;
 private CommunicationEvent ie;

 private PainelBotoes painelBotoes;

 public PainelBotoesController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }


 /**
  * Devolve a descrição, uma descrição sobre finalidade do botão (menu) 
  * sobre o qual se encontra o mouse.
  * @param e
  * @return
  */
 private String getDescription (MouseEvent e) {
  JButton btton = (JButton)e.getSource();
  String dsc = btton.getActionCommand();

  //REVIEW: eliminar 'ResourceReader.msg(...)' passando a usar apenas a chave do item de menu (e.g., trocar 'ResourceReader.msg("menuGrf")' por '"menuGrf"' ?
  if (dsc.equals(ResourceReader.msg("menuGrf")))
   dsc = ResourceReader.msg("menuGrfDescr");

  if (dsc.equals(ResourceReader.msg("menuCalculo")))
   dsc = ResourceReader.msg("menuCalculoDescr");

  if (dsc.equals(ResourceReader.msg("menuAnim")))
   dsc = ResourceReader.msg("menuAnimDescr");

  if (dsc.equals(ResourceReader.msg("menuEdicao")))
   dsc = ResourceReader.msg("menuEdicaoDescr");

  if (dsc.equals(ResourceReader.msg("menuExerc")))
   dsc = ResourceReader.msg("menuExercDescr");

  if (dsc.equals(ResourceReader.msg("menuAjuda")))
   dsc = ResourceReader.msg("menuAjudaDescr");

  return dsc;
  }


 // Send event to 'at difusor.controle.CommunicationController.enviarEvento(...)' linhe 57
 // Who effective treat this:
 // - igraf.moduloCentral.ModuloCentral.filtrarEventoEntrada(ModuloCentral.java:72)
 // - 
 private void enviar (MouseEvent e, int eventType) {
  button = (JButton)e.getSource();
  enviarEvento(new IgrafButtonPaneEvent(button, eventType));
  }


 // este método será usado quando o botão receber passar a receber evento
 // para, por exemplo, mudar look-n-feel ou coisa parecida.
 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie instanceof ChangeLanguageEvent) {
   painelBotoes.updateLabels();
   }
  }

 public void setControlledObject (Object obj) {
  painelBotoes = (PainelBotoes)obj;
  }


 //__ mouse events __

 public void mouseEntered (MouseEvent e) {  
  // destacar letra
  JButton btton = (JButton)e.getSource();
  btton.setForeground(Color.gray);

  // envia o evento que controla a exibição dos menus sobre a área de desenho
  enviar(e, IgrafButtonPaneEvent.MOUSE_ENTERED);

  ie = new IgrafDescriptionEvent(this, getDescription(e));
  enviarEvento(ie);
  }

 public void mouseClicked (MouseEvent e) {
  enviar(e, IgrafButtonPaneEvent.MOUSE_CLICKED);
  }

 public void mousePressed (MouseEvent e) { }
 public void mouseReleased (MouseEvent e) { }

 public void mouseExited (MouseEvent e) {
  // volta letra ao normal
  JButton btton = (JButton)e.getSource();
  btton.setForeground(Color.black);

  // fecha qualquer menu exibido
  if (e.getX() < LIM_X || e.getY() < LIM_Y_MIN || e.getY() > btton.getBounds().width + LIM_Y_MAX)
   enviar(e, IgrafButtonPaneEvent.MOUSE_EXITED);
  }

 }
