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
  return objetivo("corrigir as dimens�es da tela quando o iGraf usa o slider para controle manual de anima��o.");
  }

 }
