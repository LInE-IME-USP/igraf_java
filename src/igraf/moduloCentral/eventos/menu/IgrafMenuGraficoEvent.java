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
 * @description Classe empacotadora para notificar o sistema sobre eventos gerados no menu gráfico
 * 
 * @see igraf.moduloCentral.controle.menu.IgrafMenuGraficoController.enviarEventoAcao(IgrafMenuGraficoController.java:66): instantiate this class
 * 
 * @see src/igraf/moduloSuperior/visao/PainelBotoes.java: creates every JButton
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java
 * @see igraf/moduloCentral/visao/menu/MenuGrafico.java
 * 
 * @see igraf/moduloArquivo/modelo/ArquivoModel.java:
 * @see igraf/moduloCentral/visao/plotter/GraphPlotter.java:
 * @see igraf/moduloSuperior/controle/entrada/EntradaExpressaoController.java:
 * 
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.eventos.menu;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import igraf.moduloSuperior.eventos.ModuloSuperiorDisseminavelEvent;
import difusor.evento.CommunicationEvent;

public class IgrafMenuGraficoEvent extends CommunicationEvent implements ModuloSuperiorDisseminavelEvent, ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent, EventoRegistravel {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/eventos/menu/IgrafMenuGraficoEvent.java";

 //TODO: remover a representacao interna com a mensagem! Eh preciso deixar apenas o tipo
 // Used in: 
 //  + src/igraf/moduloArquivo/modelo/ArquivoModel.java: HIDE_GRAPH; DELETE_ALL; HIDE_ALL; INSERT_TAB; DRAW_ALL
 //  + src/igraf/moduloCentral/visao/plotter/GraphPlotter.java:220: DELETE_GRAPH; HIDE_GRAPH
 //  + src/igraf/moduloSuperior/controle/entrada/EntradaExpressaoController.java: DELETE_GRAPH; HIDE_GRAPH
 public static final String SHOW_EXPRESSION_EDITOR = "msgMenuGrfEditaExp"; // ResourceReader.msg("msgMenuGrfEditaExp")
 public static final String DRAW_ALL = "msgMenuGrfDesTodos"; // ResourceReader.msg("msgMenuGrfDesTodos")
 public static final String HIDE_GRAPH = "msgMenuGrfEsconder"; // ResourceReader.msg("msgMenuGrfEsconder")
 public static final String HIDE_LAST = "msgMenuGrfEsconderUlt"; // ResourceReader.msg("msgMenuGrfEsconderUlt")
 public static final String HIDE_ALL = "msgMenuGrfEsconderTodos"; // ResourceReader.msg("msgMenuGrfEsconderTodos")
 public static final String DELETE_GRAPH = "msgMenuGrfRemoverGrafico"; // ResourceReader.msg("msgMenuGrfRemoverGrafico")
 public static final String DELETE_ALL = "msgMenuGrfRemoverTodos"; // ResourceReader.msg("msgMenuGrfRemoverTodos")
 public static final String INSERT_TAB = "msgMenuGrfNovaAba"; // ResourceReader.msg("msgMenuGrfNovaAba")

 public static int id = 0;
 
 private String exp = "";

 // From: igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: enviarEventoAcao(String cmd): ie=new IgrafMenuGraficoEvent(this); - removed, used the next constructor
 public IgrafMenuGraficoEvent (Object source) {
  super(source);
  id++;
  //T System.out.println("\n"+IGCLASSPATH + ": IgrafMenuGraficoEvent(...): 1: source="+source.getClass().getName());
  //T try{ String srt0=""; System.out.println(srt0.charAt(3)); } catch (Exception e ) { e.printStackTrace(); }
  }

 // From: igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: enviarEventoAcao(String cmd): ie=new IgrafMenuGraficoEvent(this, cmd);
 public IgrafMenuGraficoEvent (Object source, String cmd) {
  super(source, cmd);
  id++;
  //T System.out.println("\n"+IGCLASSPATH + ": IgrafMenuGraficoEvent(...): 2: source="+source.getClass().getName()+", cmd="+cmd);
  }

 public IgrafMenuGraficoEvent (Object source, String exp, String cmd) {
  this(source, cmd);
  this.exp = exp;
  //T System.out.println("\n"+IGCLASSPATH + ": IgrafMenuGraficoEvent(...): 3: exp="+exp);
  }
 
 public String getExpressao () {
  return this.exp;
  }
 
 public void setExpressao (String strFnc) {
  this.exp = strFnc;
  //T System.out.println("\n"+IGCLASSPATH + ": setExpressao(...): exp="+this.exp);
  }
 
 //public String toString () { String s = "IgrafMenuGraficoEvent\nGerado quando o mouse é clicado sobre o menu " + getCommand(); return s; }

 public String getArgumento () {
  return exp;
  }

 public int getCodigoAcao () {
  String aux = getCommand();
  if (aux.equals(DRAW_ALL))
   return Acao.desenharTodos;

  if (aux.equals(HIDE_GRAPH))
   return Acao.apagarGrafico;

  if (aux.equals(HIDE_LAST))
   return Acao.apagarUltimo;

  if (aux.equals(HIDE_ALL))
   return Acao.apagarTodos;

  if (aux.equals(DELETE_ALL))
   return Acao.removerTodos;

  if (aux.equals(INSERT_TAB))
   return Acao.inserirAba;

  return -1;
  }

 public boolean isLoading () {
  if (getCommand().equals(INSERT_TAB))
   return true;
  return false;
  }


 public String getDescription () {
  String [] msgVet = { ""+getSource(), "MenuGrafico" };
  return ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a interação (clique) do usuário no menu " + "MenuGrafico"
  }

 }
