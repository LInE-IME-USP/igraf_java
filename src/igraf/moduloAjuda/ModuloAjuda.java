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

package igraf.moduloAjuda;

import igraf.moduloAjuda.controle.HelpController;
import igraf.moduloAjuda.eventos.ModuloAjudaDisseminavel;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class ModuloAjuda extends CommunicationFacade {
 
 private HelpController hc = new HelpController(this, true);

 public void filtrarEventoEntrada (CommunicationEvent ie) {  
  if (ie instanceof ModuloAjudaDisseminavel)   
   disseminarEventoInternamente(ie);
  }

 //TODO: for while, there is no output event
 public void filtrarEventoSaida (CommunicationEvent ie) {}

 }
