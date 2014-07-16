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
 * @description Classe que notifica sobre atualiza��es em dados associados ao contexto de cada aba.
 * Existem duas situa��es de atualiza��o: uma � pontual, ocorre a cada mudan�a simples como a entrada de
 * uma fun��o, outra � geral, ocorre quando o usu�rio clica em uma aba mudando a �rea de desenho exibida.
 * No �ltimo caso, todas as informa��es do contexto da aba selecionada devem ser restabelecidas.
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

import igraf.moduloSuperior.eventos.ModuloSuperiorDisseminavelEvent;

import java.util.Vector;

import difusor.evento.CommunicationEvent;


public class IgrafTabUpdateEvent extends CommunicationEvent implements  ModuloSuperiorDisseminavelEvent, ModuloCentralDisseminavelEvent {

 // Base name of commands
 public static final String ANIMATION_LIST_CHANGED = "changeAnimationList"; 
 public static final String FUNCTION_LIST_CHANGED = "changeFunctionList"; 
 public static final String POLYGON_LIST_CHANGED = "changePolygonList";
 public static final String SLIDER_VALUE_CHANGED = "changeSliderValue";
 public static final String EXPRESSION_CHANGED = "changeExpression";
 public static final String TEXT_LIST_CHANGED = "textListChanged";
 public static final String UNDO = "undo";
 public static final String REDO = "redo";
 
 // comando de atualiza��o geral
 public static final String  GENERAL_UPDATE = "general update";
 
 private String expression;
 private double sliderValue;

 private Vector animationList = new Vector(), 
                functionList = new Vector(),
                polygonList = new Vector();

 private int numTextoVisivel;

 private boolean isItExercise = false;
 
 private boolean temDesenhoOculto = false;
 private boolean changed;
 
 public IgrafTabUpdateEvent (Object source) {
  super(source);
  }

 public IgrafTabUpdateEvent (Object source, String cmd) {
  this(source);
  setCommand(cmd);
  }

 public boolean temTextoVisivel () {
  return numTextoVisivel > 0;
  }

 public void setTextListSize (int textListSize) {
  numTextoVisivel = textListSize;
  }
 
 public String getExpression () {
  return expression;
  }

 public void setExpression (String expression) {
  this.expression = expression;
  }

 public double getSliderValue () {
  return sliderValue;
  }

 public void setSliderValue (double sliderValue) {
  this.sliderValue = sliderValue;
  }

 public Vector getAnimationList () {
  return animationList;
  }

 public void setAnimationList (Vector animationList) {
  this.animationList = animationList;
  }

 public Vector getFunctionList () {
  return functionList;
  }

 public void setFunctionList (Vector functionList) {
  this.functionList = functionList;
  } 
 
 public Vector getPolygonList () {
  return polygonList;
  }
 
 public void setPolygonList (Vector polygonList) {
  this.polygonList = polygonList;
  }

 public boolean temDesenhoOculto () {
  return temDesenhoOculto;
  }
 
 public void notificaDesenhoOculto (boolean b) {
  temDesenhoOculto = b;
  }
 
 public void setExercise (boolean isItExercise) {
  this.isItExercise = isItExercise;
  }

 public boolean isItExercise () {
  return isItExercise;
  }


 public String getDescription () {
  return objetivo("notificar sobre a mudan�a de aba.");
  }

 public void polygonListChanged (boolean b) {
  changed = b;
  }
 
 public boolean isPolygonListChanged () {
  return changed;
  }

 }
