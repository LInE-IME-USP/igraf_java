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

package igraf.moduloJanelasAuxiliares.eventos;

import igraf.moduloArquivo.eventos.ModuloArquivoDisseminavelEvent;
import difusor.evento.CommunicationEvent;

public class JanelaHistoricoEvent extends CommunicationEvent implements ModuloArquivoDisseminavelEvent {

 public static final String REDRAW_ALL = "redraw all";
 public static final String STEP_REDRAW = "step redraw";

 public JanelaHistoricoEvent (Object source, String cmd) {
  super(source, cmd);
  }


 public String getDescription () {
  return objetivo("notificar sobre alterações na janela de histórico do sistema.");
  }

 }
