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
 * @description Repaint the drawing area
 * 
 * @see igraf/moduloCentral/visao/TabbedViewer.java
 * @see igraf/moduloCentral/controle/AreaDesenhoController.java
 * @see igraf/moduloCentral/visao/AreaDesenho.java
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

import java.util.Vector;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.visao.AreaDesenho;
import igraf.moduloCentral.visao.plotter.GraphPlotter;

import difusor.evento.CommunicationEvent;


public class AreaDesenhoEvent extends CommunicationEvent {

 private GraphPlotter graphPlotter;
 private AreaDesenho areaDesenho;
 
 public AreaDesenhoEvent (Object ad, Object p) {
  super(p);
  this.graphPlotter = (GraphPlotter)p;
  this.areaDesenho = (AreaDesenho)ad;
  }
 
 public Vector getListaDesenhoVisivel () {
  return graphPlotter.getListaFuncaoVisivel();
  }

 public AreaDesenho getAreaDesenho () {
  return areaDesenho;
  }

 public String getDescription () {
  return objetivo(ResourceReader.msg("msgInternalChangeADD")); // "notificar o sistema sobre altera��es ocorridas na �rea de desenho do iGraf."
  }

 }
