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

import igraf.moduloArquivo.eventos.ModuloArquivoDisseminavelEvent;
import igraf.moduloCentral.modelo.Sessao;
import difusor.evento.CommunicationEvent;

public class IgrafSessaoEvent extends CommunicationEvent implements ModuloArquivoDisseminavelEvent {

 public static final String UPDATE_SESSION_DATA = "update session data";
 public static final String REVIEW_SESSION_DATA = "review session data";
 private Sessao sessao;

 public IgrafSessaoEvent (Object source, Sessao sessao) {
  super(source, UPDATE_SESSION_DATA);
  this.sessao = sessao;
  }
 
 public IgrafSessaoEvent (Object source) {
  super(source, REVIEW_SESSION_DATA);
  }
 
 public Sessao getSessao () {
  return sessao;
  }
 
 public String getDescription () {
  return objetivo("transportar dados da sess�o atual do iGraf a outras partes do sistema.");
  }

 }
