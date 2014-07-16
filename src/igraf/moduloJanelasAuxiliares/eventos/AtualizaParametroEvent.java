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

package igraf.moduloJanelasAuxiliares.eventos;

import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloJanelasAuxiliares.visao.animacao.IntervaloParametro;
import difusor.evento.CommunicationEvent;

public class AtualizaParametroEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent {

 public static final String SHOW_PARAM_PANEL = "show param panel";
 public static final String UPDATE = "update";
 
 IntervaloParametro ip;
 boolean state;
 
 public AtualizaParametroEvent (Object source) {
  super(source);
  this.ip = (IntervaloParametro)source;
  setCommand(UPDATE);
  }
 
 public AtualizaParametroEvent (Object source, String cmd) {
  super(source);
  setCommand(cmd);
  }
 
 public int getIndex () {
  return ip.getIndex();
  }
 
 public double getIni () {
  return ip.getIni();
  }
 
 public double getFim () {
  return ip.getFim();
  }

 public boolean getState () {
  return ip.getState();
  }

 public String getDescription () {
  return objetivo("notificar sobre altera��o nos valores dos par�metros de anima��o.");
  }

 }
