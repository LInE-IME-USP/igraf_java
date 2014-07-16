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

package igraf.moduloCentral.eventos.menu;

import difusor.evento.CommunicationEvent;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import igraf.moduloSuperior.controle.entrada.Analisa;

public class IgrafMenuAnimacaoEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent {

 public static final String SLIDER_ADJUSTMENT = "sliderAdjustment"; 
 public static final String CHANGE_ANIMATION_STATE = "changeAnimationState";
 
 String funcaoOriginal = "";
 double sliderValue;
 
 public IgrafMenuAnimacaoEvent (Object source) {
  super(source);
  }

 public IgrafMenuAnimacaoEvent (Object source, double sliderValue) {
  super(source);
  this.sliderValue = sliderValue;
  setCommand(SLIDER_ADJUSTMENT);
  }
 
 public IgrafMenuAnimacaoEvent (Object source, String funcaoOriginal){
  super(source);
  setCommand(CHANGE_ANIMATION_STATE);
  setFuncaoOriginal(funcaoOriginal);
  }
  
 public String getFuncaoOriginal () {
  return funcaoOriginal;
  }

 public void setFuncaoOriginal (String funcaoOriginal) {
  if (Analisa.temParametro(funcaoOriginal))
  this.funcaoOriginal = funcaoOriginal;
  }
 
 public double getSliderValue () {
  return sliderValue;
  }

 public String getDescription () {
  String [] msgVet = { ""+getSource(), "MenuAnimacao"  };
  return ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a intera��o (clique) do usu�rio no menu " + "MenuAnimacao"
  }

 }
