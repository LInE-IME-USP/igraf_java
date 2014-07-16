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
 * @description Controller to put messages into the bottom bar (the status bar)
 * 
 * @see igraf/moduloInferior/visao/InfoPane.java: the panel with JLabel to show the message
 * @see igraf/moduloCentral/visao/plotter/GraphPlotter.java: it launches some status messages (like edition of expression)
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloInferior.controle;

import java.awt.Component;

import igraf.moduloInferior.visao.InfoPane;
import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class InfoPaneController extends CommunicationController {

 //DEBUG try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }

 private InfoPane ip;
 public InfoPane getInfoPane () { return ip; }

 public InfoPaneController (CommunicationFacade module) {
  super(module);
  ip = new InfoPane(this);
  }

 public void setControlledObject (Object obj) {
  ip = (InfoPane)obj;
  }

 //TODO: para eliminar modelo ineficiente de 'difusor/'
 public void setMessage (String strMsg) {
  ip.setStatusBarMessage(strMsg);    
  }
 public void setDefaultInfo () {
  ip.setDefaultInfo();
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie.getDescription() != null)
   ip.setStatusBarMessage(ie.getDescription()); // write message into the message "bar status"
  else
   ip.setDefaultInfo();
  }

 public Component getPane () {
  return ip;
  }

 }
