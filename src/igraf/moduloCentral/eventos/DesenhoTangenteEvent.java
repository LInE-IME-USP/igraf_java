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

public class DesenhoTangenteEvent extends CommunicationEvent implements ModuloJanelasDisseminavelEvent {
 
 //TODO: por qu� esse cara era ModuloCentralDisseminavelEvent? Isso gerava um looping recursivo desnecess�rio!

 String equacaoReta, fx, valorX;

 public DesenhoTangenteEvent (Object source, String funcao, String valorX, String fx) {
  super(source);
  equacaoReta = funcao;
  this.valorX = valorX;
  this.fx = fx;
  }

 public String getEquacaoReta () {
  return equacaoReta;
  }

 public String getFx () {
  return fx;
  }

 public String getValorX () {
  return valorX;
  }

 public String toString () {
  return "DesenhoTangenteEvent gerado por " + getSource();
  }

 public String getDescription () {
  return "notificar sobre altera��es nos desenhos de tangente.";
  }

 }
