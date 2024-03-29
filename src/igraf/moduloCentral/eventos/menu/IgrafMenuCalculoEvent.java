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
 * @description Classe empacotadora criada para transportar informa��es sobre eventos gerados no menu C�lculo
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

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import igraf.moduloSuperior.eventos.ModuloSuperiorDisseminavelEvent;


public class IgrafMenuCalculoEvent extends CommunicationEvent implements ModuloSuperiorDisseminavelEvent, ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent, EventoRegistravel {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/eventos/menu/IgrafMenuCalculoEvent.java";

 public static final String DERIVATE     = ResourceReader.msg("mcDerVer");
 public static final String DRAW_TANGENT = ResourceReader.msg("mcTgVer");
 public static final String INTEGRATE    = ResourceReader.msg("mcIIVer");
 public static final String DRAW_AREA    = ResourceReader.msg("mcIICalc");
 
 private String expr = "";
 
 public IgrafMenuCalculoEvent (Object source) {
  super(source);
  }

 public IgrafMenuCalculoEvent (Object source, String cmd) {
  super(source);
  setCommand(cmd);
  }

 // igraf/moduloArquivo/modelo/ArquivoModel.java
 //  apenas 'ArquivoModel' faz 'new IgrafMenuCalculoEvent(...)'

 // From: igraf.moduloArquivo.modelo.ArquivoModel.refazGrafico(ArquivoModel.java:315)
 public IgrafMenuCalculoEvent (Object source, String arg, String cmd) {
  super(source);
  setCommand(cmd);

  int k = arg.indexOf("fx:"); // trata modelo de vers�es antigas que usavam o fx: antes da fun��o
  if(k > -1) arg = arg.substring(k+3);

  if (arg!=null) arg = arg.trim(); // clear expression
  expr = arg;
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": IgrafMenuCalculoEvent(...): expr=" + expr + " (****************************************) ");
  //T IGraf.launchStackTrace();
  } // public IgrafMenuCalculoEvent(Object source, String arg, String cmd)


 // From: igraf.moduloSuperior.controle.entrada.EntradaExpressaoController.tratarEventoRecebido(EntradaExpressaoController.java:130)
 public void setExpressao (String text) {
  // GraphPlotter.java: Vector listaDesenhoVisivel

  if (text!=null) text = text.trim(); // clear expression
  expr = text;
  }

 public String getExpressao () {
  return expr;
  }

 public String getArgumento () {
  if (getCommand().equals(ResourceReader.msg("mcDerVer")))
   return getExpressao();

  if (getCommand().equals(ResourceReader.msg("mcIIVer")))
   return getExpressao();

  return "";
  }

 public int getCodigoAcao () {
  if (getCommand().equals(ResourceReader.msg("mcDerVer")))
   return Acao.derivar;

  if (getCommand().equals(ResourceReader.msg("mcIIVer")))
   return Acao.intIndefinida;
  return 0;
  }


 public String getDescription () {
  String [] msgVet = { ""+getSource(), "MenuCalculo"  };
  return ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a intera��o (clique) do usu�rio no menu " + "MenuCalculo"

  }

 }
