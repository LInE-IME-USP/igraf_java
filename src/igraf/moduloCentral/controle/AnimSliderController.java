/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @description It implements the
 * 
 * @see
 * 
 * @author RP, LOB
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.controle;

import igraf.moduloCentral.ModuloCentral;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.visao.AnimSlider;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;


public class AnimSliderController extends CommunicationController implements AdjustmentListener{

 private AnimSlider as;
 
 public AnimSliderController (ModuloCentral mc) {
  super(mc);
  }

 public void setControlledObject (Object obj) {
  as = (AnimSlider)obj;
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie.getCommand().equals(IgrafTabUpdateEvent.GENERAL_UPDATE)){
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)ie;
   as.setBublePosition(iue.getSliderValue());
   }
  
  if (ie.getCommand().equals(IgrafTabUpdateEvent.UNDO)){
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)ie;
   as.setBublePosition(0);
   }
  
  if (ie.getCommand().equals(IgrafTabUpdateEvent.REDO)){
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)ie;
   as.setBublePosition(iue.getSliderValue());
   }
    
  }

 public void adjustmentValueChanged (AdjustmentEvent e) {  
  IgrafTabUpdateEvent iue = new IgrafTabUpdateEvent(this, IgrafTabUpdateEvent.SLIDER_VALUE_CHANGED);
  iue.setSliderValue(as.getValue());
  enviarEvento(iue);
  }

 }
