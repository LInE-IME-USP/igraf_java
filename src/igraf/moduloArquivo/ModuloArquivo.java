/*
 * iGraf : interactive Graphics in the Internet
 * LInE - line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 *
 * @description
 *
 * @see igraf/moduloArquivo/controle/ArquivoController.java
 * 
 */

package igraf.moduloArquivo;

import igraf.moduloArquivo.controle.ArquivoController;
import igraf.moduloArquivo.eventos.ModuloArquivoDisseminavelEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class ModuloArquivo extends CommunicationFacade {

 ArquivoController ac = new ArquivoController(this, true);

 // filtra os eventos de entrada para evitar excesso de tratamento de erros
 public void filtrarEventoEntrada (CommunicationEvent communicationEvent) {
  // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  if (communicationEvent instanceof ModuloArquivoDisseminavelEvent)
   disseminarEventoInternamente(communicationEvent);
  }

 public void filtrarEventoSaida (CommunicationEvent communicationEvent) {
  disseminarEventoExternamente(communicationEvent);
  }

 }
