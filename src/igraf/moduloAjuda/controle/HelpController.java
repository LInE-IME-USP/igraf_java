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
 * @see igraf/moduloAjuda/visao/Help.java
 * @see igraf/moduloAjuda/eventos/HelpEvent.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.controle;

import igraf.basico.io.ResourceReader;
import igraf.moduloAjuda.eventos.HelpEvent;
import igraf.moduloAjuda.visao.Help;
import igraf.moduloAjuda.visao.JanelaSobre;
import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class HelpController extends CommunicationController {

 private Help help;

 public HelpController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  String str = ie.getCommand();

  //T System.out.println("src/igraf/moduloAjuda/controle/HelpController.java: tratarEventoRecebido(...): " + str + "=" + HelpEvent.SHOW_CONTENT + "=" + ResourceReader.msg("majCont") + "?")
  if (str.equals(ResourceReader.msg("majCont"))) // Content
   new Help();
  else
  if (str.equals(ResourceReader.msg("majSobre"))) // About
   new JanelaSobre();
  }

 public void setControlledObject (Object obj) { }

 }
