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

package igraf.moduloCentral.eventos.menu;

import difusor.evento.CommunicationEvent;

import igraf.basico.io.ResourceReader;
import igraf.moduloExercicio.eventos.ModuloExercicioDisseminavelEvent;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;

public class IgrafMenuExercicioEvent extends CommunicationEvent implements ModuloJanelasDisseminavelEvent, ModuloExercicioDisseminavelEvent {

 public static final String HISTORICO = "historico";
 public static final String EDITAR = "editar";
 public static final String CRIAR = "criar";

 public IgrafMenuExercicioEvent (Object source) {
  super(source);
  }
 
 public IgrafMenuExercicioEvent (Object source, String cmd) {
  super(source);
  setCommand(cmd);
  }

 public String getDescription () {
  String [] msgVet = { ""+getSource(), "MenuExercicio" };
  return objetivo(ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet)); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a intera��o (clique) do usu�rio no menu " + "MenuExercicio"
  }

 }
