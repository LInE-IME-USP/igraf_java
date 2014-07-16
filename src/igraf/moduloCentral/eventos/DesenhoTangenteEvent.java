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

public class DesenhoTangenteEvent extends CommunicationEvent implements ModuloJanelasDisseminavelEvent {
 
 //TODO: por quê esse cara era ModuloCentralDisseminavelEvent? Isso gerava um looping recursivo desnecessário!

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
  return "notificar sobre alterações nos desenhos de tangente.";
  }

 }
