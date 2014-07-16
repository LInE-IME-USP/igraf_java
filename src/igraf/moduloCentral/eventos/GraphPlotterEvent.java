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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloCentral.eventos;

import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import difusor.evento.CommunicationEvent;


public class GraphPlotterEvent extends CommunicationEvent implements ModuloJanelasDisseminavelEvent {

 public GraphPlotterEvent (Object source) {
  super(source);
  }
 
 public GraphPlotterEvent (Object source, String command) {
  super(source);
  setCommand(command);
  }

 public String getDescription () {
  return objetivo("notificar o sistema sobre altera��es realizadas na �rea de desenho do iGraf.");
  }

 }
