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

import java.awt.Rectangle;

import difusor.evento.CommunicationEvent;

public class FrameResizedEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent {

 Rectangle rectangle;
 
 public FrameResizedEvent (Object source, Rectangle r) {
  super(source);
  rectangle = r;
  }
 
 public Rectangle getRectangle () {
  return rectangle;
  }


 public String getDescription () {
  return objetivo("corrigir as dimensões da tela quando o iGraf usa o slider para controle manual de animação.");
  }

 }
