/*
 * iGraf - interactive Graphics in the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author RP, LOB
 *
 * @description Classe que realiza as opera��es de an�lise da express�o digitada pelo
 * usu�rio visando a torn�-la compreens�vel ao contexto do programa; gera,
 * ao final do processo o objeto (Polygon) que ser� desenhado na tela.
 * 
 * @version 23/05/2006
 * 
 * @see igraf/moduloCentral/visao/desenho/DesenhoFuncao.java
 * 
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 */

package igraf.moduloSuperior.eventos;

import igraf.basico.util.Utilitarios;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;
import difusor.evento.CommunicationEvent;

public class EntradaExpressaoEvent extends CommunicationEvent  implements ModuloCentralDisseminavelEvent, EventoRegistravel { //RR ModuloSuperiorDisseminavelEvent,

 public static final String DRAW_POLYGON = "draw_polygon";
 
 private String expressao = null;
 private String dominio = null;
 
 public EntradaExpressaoEvent (Object source, String expressao) {
  super(source);
  expressao = Utilitarios.retiraEspacos(expressao);
  int i = expressao.indexOf('[');
  if (i == 0)
   setCommand(DRAW_POLYGON);

  if (i > 0) {
   dominio = expressao.substring(i);
   expressao = expressao.substring(0, i);
   }     
  this.expressao = expressao; 
  }

 public String getExpressao () {
  return expressao;
  }
 
 public String getDominio () {
  return dominio;
  }
 
 public String getDescription () {
  String s = "Comando para o desenho do gr�fico de uma fun��o\n" +
     "Evento gerado pelo campo de texto (JComboBox) onde s�o digitadas as express�es matem�ticas de entrada para o desenho do iGraf."; 
  return s;
  }

 public String getArgumento () {  
  return getExpressao();
  }

 public int getCodigoAcao () {
  return Acao.desenhar;
  }

 }
