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
 * @see 
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloCentral.eventos;

import igraf.moduloExercicio.eventos.ModuloExercicioDisseminavelEvent;
import igraf.moduloSuperior.eventos.ModuloSuperiorDisseminavelEvent;

import difusor.evento.CommunicationEvent;

public class ResetEvent extends CommunicationEvent implements ModuloSuperiorDisseminavelEvent, ModuloCentralDisseminavelEvent, ModuloExercicioDisseminavelEvent {

 public static final String RESET = "reset";
 public static final String RESET_TAB = "reset tab";
 public static final String VIEW_HISTORY = "view history";
 
 public ResetEvent (Object source) {
  super(source, ResetEvent.RESET);
  }
 
 public ResetEvent (Object source, String cmd) {
  super(source, cmd);
  }


 public String getDescription () {
  return objetivo("notificar outras partes do programa sobre a necessidade de restabelecerem suas condições iniciais.");
  }

 }
