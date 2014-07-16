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
 * @description Event change in language location
 * 
 * @see igraf/IGraf.java: public void changeLanguage(int lang)
 * @see igraf/IGrafController.java: difusor/CommunicationFacade.java: public void disseminarEventoExternamente(CommunicationEvent ie)
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.basico.event;

import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloExercicio.eventos.ModuloExercicioDisseminavelEvent;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import igraf.moduloSuperior.eventos.ModuloSuperiorDisseminavelEvent;
import difusor.evento.CommunicationEvent;

public class ChangeLanguageEvent extends CommunicationEvent implements ModuloSuperiorDisseminavelEvent, ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent, ModuloExercicioDisseminavelEvent { //RR

 public ChangeLanguageEvent (Object source) {
  super(source);
  }

 public String getDescription () {
  return objetivo("notificar a todas as partes do sistema que houve a mudan�a de idioma.");
  }

 }
