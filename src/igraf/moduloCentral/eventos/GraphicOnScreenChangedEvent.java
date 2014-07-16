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
 * @see igraf.moduloExercicio.controle.JanelaExercicioController: tratarEventoRecebido(...)
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

import java.util.Vector;

import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.visao.plotter.GraphPlotter;
import igraf.moduloCentral.visao.plotter.Plotter;
import igraf.moduloExercicio.eventos.ModuloExercicioDisseminavelEvent;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;

public class GraphicOnScreenChangedEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent, ModuloExercicioDisseminavelEvent {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/eventos/GraphicOnScreenChangedEvent.java";

 public static final String SCREEN_CHANGED = "screen changed";
 public static final String CLEAR_SCREEN = "clear screen";
 private GraphPlotter gp;
 private boolean editing = false;

 //TODO: eliminar difusor
 // From: igraf.moduloCentral.visao.plotter.GraphPlotter.notifyScreenChanged(GraphPlotter.java:516)
 //       from 'igraf.IGraf.init(IGraf.java:328) -> ... igraf.moduloCentral.controle.AreaDesenhoController.notificaTrocaAba(AreaDesenhoController.java:232)'
 //       from 'igraf.moduloSuperior.visao.PainelBotoes$4.actionPerformed(PainelBotoes.java:323)'
 public GraphicOnScreenChangedEvent (GraphPlotter gp) {
  super(gp);
  this.gp = gp;
  setCommand(SCREEN_CHANGED);
  //D try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
  }

 public GraphicOnScreenChangedEvent (Plotter plotter, String mode) {
  super(plotter);
  setCommand(mode);
  //D try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
  }

 public boolean isEditing () {
  return editing;
  }

 public Vector getListaFuncaoVisivel () {
  if (gp==null) { // if there is no function at all, return immediatly!
     return null;
     }
  return gp.getListaFuncaoVisivel();
  }

 public Vector getListaAnimacaoVisivel () {
  if (gp==null) { // if there is no function at all, return immediatly!
     if (igraf.IGraf.IS_DEBUG) 
        System.err.println("igraf/moduloCentral/eventos/GraphicOnScreenChangedEvent.java: getListaDesenhoVisivel(): GraphPlotter gp is empty!");
     return null;
     }
  return gp.getListaAnimacaoVisivel();
  }

 public Vector getListaDesenhoVisivel () {
  if (gp==null) { // if there is no function at all, return immediatly!
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "getListaDesenhoVisivel(): 'GraphPlotter gp' is empty!");
     return null;
     }
  return gp.getListaDesenhoVisivel();
  }

 public String getDescription () {
  return objetivo(ResourceReader.msg("msgInternalChangeADD")); // "notificar o sistema sobre alterações ocorridas na área de desenho do iGraf."
  }

 }
