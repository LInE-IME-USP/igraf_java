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
 * @see igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(...)
 * @see igraf/moduloArquivo/modelo/ArquivoModel.java
 * @see igraf/moduloCentral/controle/AreaDesenhoController.java
 * @see igraf/moduloCentral/eventos/EstadoTelaEvent.java
 * @see igraf/moduloCentral/visao/plotter/AxesPlotter.java
 * @see igraf/moduloCentral/visao/plotter/Plotter.java
 * @see igraf/moduloCentral/visao/plotter/GraphPlotter.java
 * @see igraf/moduloExercicio/controle/JanelaExercicioController.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */


package igraf.moduloCentral.eventos;

import java.awt.Point;

import difusor.evento.CommunicationEvent;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.Utilitarios;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloCentral.visao.plotter.Plotter;
import igraf.moduloExercicio.eventos.ModuloExercicioDisseminavelEvent;

public class EstadoTelaEvent extends CommunicationEvent implements EventoRegistravel, ModuloCentralDisseminavelEvent, ModuloExercicioDisseminavelEvent {

 public static final String CHANGE_CARTESIAN_PLANE = "change cartesian plane";
 public static final String REPORT_LIMITS = "report limits";
 
 private String arg = "", aux;

 int escala  = Plotter.escalaPadrao;
 Plotter p;
 Point origin;
 private boolean dadosCarregadosCorretamente = false;
 
 public EstadoTelaEvent (Object source, String arg) {
  super(source);
  setCommand(CHANGE_CARTESIAN_PLANE);
  quebraArgumento(arg);
  }

 public EstadoTelaEvent (Object source, Point origin, int escala) {
  super(source);
  this.origin = origin;
  this.escala = escala;
  setCommand(CHANGE_CARTESIAN_PLANE);
  dadosCarregadosCorretamente = true;
  }

 
 Plotter plotter;

 public EstadoTelaEvent (Object source) {
  super(source);
  plotter = (Plotter)source;
  setCommand(REPORT_LIMITS);
  }


 //IMPORTANT: to draw functions in scale e right position
 public int getXMax () {
  return plotter.getXMax();
  }
 public int getXMin () {
  return plotter.getXMin();
  }
 public int getEscala () {
  return escala;
  }


 private void quebraArgumento (String arg) {
  int k = arg.indexOf("dx:");
  int m = arg.indexOf("dy:");
  int n = arg.indexOf("esc:");
  
  //System.out.println("." + arg.substring(k+3, m-1) + ". ." + arg.substring(m+3, n) + ".");
  
  if (k < 0 & m < 0) return;
  if (n < 0) n = arg.length();

  String aux = Utilitarios.retiraEspacos(arg.substring(m+3, n));

  try {
   int x = Integer.parseInt(arg.substring(k+3, m-1));
   int y = Integer.parseInt(aux);

   origin = new Point(x, y);
   escala = Integer.parseInt(arg.substring(n+4)); 
   dadosCarregadosCorretamente = true;
  } catch (Exception e) { }  
  }

 public Point getCoordenadasOrigem () {
  return origin;
  }

 public void setCommand (String cmd) {
  super.setCommand(cmd);
  aux = cmd;
  }

 public String getArgumento () {
  if (dadosCarregadosCorretamente) {
   arg = "dx:" + String.valueOf(getCoordenadasOrigem().x) + " dy:" + String.valueOf(getCoordenadasOrigem().y) + " esc:" + getEscala();
   }
  return arg;
  }

 public int getCodigoAcao () {
  if (getCommand().equals(REPORT_LIMITS))
   return -1;

  return Acao.mudaPlanoCartesiano;
  }

 public String getDescription () {
  return objetivo(ResourceReader.msg("msgInternalChangeADD")); // "notificar o sistema sobre alterações ocorridas na área de desenho do iGraf."
   }

 }
