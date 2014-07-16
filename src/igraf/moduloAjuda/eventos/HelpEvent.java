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
 * @see igraf/moduloAjuda/controle/HelpController.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.eventos;

import igraf.basico.io.ResourceReader;
import difusor.evento.CommunicationEvent;

public class HelpEvent extends CommunicationEvent implements ModuloAjudaDisseminavel {

 //X public static final String SHOW_CONTENT = ResourceReader.msg("majCont");
 //X public static final String SHORTCUT_LIST = ResourceReader.msg("majBusca");
 //X public static final String SEARCH = ResourceReader.msg("majAtalhos"); 
 //X public static final String ABOUT = ResourceReader.msg("majSobre");

 public HelpEvent (Object source, String cmd) {
  super(source, cmd);
  }


 public String getDescription () {  
  return "Event launched in " + getSource() + " with purpose to open the Help window of iGraf";
  }

 //TODO: is better to use directly the options name: majCont, majBusca, majAtalhos, majSobre
 //X // Used whenever the language is changed
 //X public void updateLabels () {
 //X  SHOW_CONTENT = ResourceReader.msg("majCont");
 //X  SHORTCUT_LIST = ResourceReader.msg("majBusca");
 //X  SEARCH = ResourceReader.msg("majAtalhos"); 
 //X  ABOUT = ResourceReader.msg("majSobre");
 //X  }

 }
