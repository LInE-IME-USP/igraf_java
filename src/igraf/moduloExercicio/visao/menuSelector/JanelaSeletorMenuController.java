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
 * @description 
 * 
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 * @see igraf/moduloExercicio/ModuloExercicio.java: makes 'JanelaRespostaController jrc = new JanelaRespostaController(this, true);'
 * @see igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java: define the window structure
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio.visao.menuSelector;

import igraf.basico.io.ResourceReader;
import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class JanelaSeletorMenuController extends CommunicationController {

 private MenuSelectorFrame msf;
 public  MenuSelectorFrame getMenuSelectorFrame () { // src/igraf/ContentPane.java: criaRelacionamentos(...): MenuSelectorFrame menuSelectorFrame = me.getMenuSelectorFrame();
   return msf;
   }

 public JanelaSeletorMenuController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  msf = new MenuSelectorFrame(this);
  msf.setVisible(false); // necessary to create a single copy in order to get access to all menu items visibility (isEnabled)
  }

 public void tratarEventoRecebido (CommunicationEvent e) {
  if (e.getCommand().equals(ResourceReader.msg("mexExercMenuConfig")))
   msf.setVisible(true);
   // msf = new MenuSelectorFrame(this);
  }

 }
