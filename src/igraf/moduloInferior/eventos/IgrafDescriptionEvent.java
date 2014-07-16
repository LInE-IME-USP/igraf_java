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
 * @see ...
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf.moduloInferior.eventos;

import igraf.moduloInferior.ModuloInferiorDisseminavelEvent;
import difusor.evento.CommunicationEvent;


public class IgrafDescriptionEvent extends CommunicationEvent implements ModuloInferiorDisseminavelEvent {

 String description;

 public IgrafDescriptionEvent (Object source, String description) {
  super(source);
  this.description = description;
  }

 public String getDescription () {
  return description;
  }

 protected String debug () {
  return objetivo("notificar ao usu�rio, pela barra de informa��es, a fun��o dos objetos da tela do iGraf.");
  }

 }
