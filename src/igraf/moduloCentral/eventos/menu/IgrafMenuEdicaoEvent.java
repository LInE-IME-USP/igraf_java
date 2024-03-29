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
 * @see igraf/moduloCentral/visao/menu/MenuEdicao.java
 * @see igraf/moduloCentral/controle/menu/IgrafMenuEdicaoEvent.java
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
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;

public class IgrafMenuEdicaoEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, EventoRegistravel, ModuloJanelasDisseminavelEvent {

 String arg = "";

 // o ideal � deixar o comando dos menus baseado nos nomes j� que, assim, ao se acrescentar novos menus, o teste fica trivial
 public static final String CHANGE_AXES   = "Alternate between show, or not, the axes on screen";
 public static final String CHANGE_SCALE  = "Alternate between show, or not, the axes with scale";
 public static final String CHANGE_GRID   = "Alternate between show, or not, the grid on screen";
 public static final String INSERT_TEXT   = "Show window for text insertion";
 public static final String EDIT_TEXT     = "Show window for text edition";
 public static final String LOAD_INFO     = "data loaded from file";

 public IgrafMenuEdicaoEvent (Object source) {
  super(source);
  }

 public IgrafMenuEdicaoEvent (Object source, String cmd) {
  super(source, cmd);
  }

 private int acao;
 public IgrafMenuEdicaoEvent (Object source, String cmd, int code) {
  super(source, cmd);
  acao = code;
  }

 public String getArgumento () {
  return arg;
  }

 public int getCodigoAcao () {
  String aux = getCommand();

  if (aux.equals(CHANGE_AXES))
   return Acao.exibirEixos;

  if (aux.equals(CHANGE_GRID))
   return Acao.exibirGrade;

  if (aux.equals(CHANGE_SCALE))
   return Acao.exibirEscala;

  if (aux.equals(LOAD_INFO))
   return acao;

  return -1;
  }


 public String getDescription () {
  String [] msgVet = { ""+getSource(), "MenuEdicao" };
  return ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a intera��o (clique) do usu�rio no menu " + "MenuEdicao"
  }

 }
