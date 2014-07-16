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
/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @description Event click in submenu (for while => MenuEdicao)
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
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;

public class IgrafSubMenuEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent {

 public IgrafSubMenuEvent (Object source, int eventType, String cmd, int x) {
  super(source);
  this.x = x;
  setCommand(cmd);
  this.eventType = eventType;
  }

 public String getDescription () {
   return objetivo(ResourceReader.msg("msgInternalClickSubmenu")); // "notificar o sistema sobre um clique do usu�rio em um submenu."
  }

 }
