/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Classe que transporta dados relativos a eventos gerados no menu Arquivo 
 * 
 * @see igraf/moduloJanelasAuxiliares/visao/JanelaHistorico.java: this is the window where sessions' action is presented
 * @see igraf/moduloArquivo/modelo/ArquivoModel.java: register history session from any GRF file
 * 
 * @author RP, LOB
 * 
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 * 
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 * 
 */

package igraf.moduloArquivo.eventos;

import difusor.evento.CommunicationEvent;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;

public class IgrafMenuArquivoEvent extends CommunicationEvent implements ModuloArquivoDisseminavelEvent, ModuloJanelasDisseminavelEvent, ModuloCentralDisseminavelEvent {
 // ModuloJanelasDisseminavelEvent: para notificar a janela do histórico

 public static final String REDO_GRAPHIC = "redo graphic";
 
 public IgrafMenuArquivoEvent (Object source) {
  super(source);
  }

 public IgrafMenuArquivoEvent (Object source, String cmd) {
  super(source);
  setCommand(cmd);
  }

 public String getDescription () {
  String [] msgVet = { ""+getSource(), "MenuArquivo" };
  return objetivo(ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet));
  // return objetivo(ResourceReader.msg("msgInternalTang")); // objetivo("comunicar eventos ocorridos no Menu Arquivo.");
  }

 }
