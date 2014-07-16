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
  return objetivo("notificar o sistema sobre alterações realizadas na área de desenho do iGraf.");
  }

 }
