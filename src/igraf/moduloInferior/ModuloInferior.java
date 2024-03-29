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
 * @see ...
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf.moduloInferior;

import java.awt.Component;

import igraf.moduloInferior.controle.InfoPaneController;
import igraf.moduloInferior.eventos.IgrafDescriptionEvent;
import igraf.moduloInferior.visao.InfoPane;
import igraf.moduloSuperior.visao.PainelBotoes;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;


public class ModuloInferior extends CommunicationFacade {

 // To allow 'igraf.moduloExercicio.visao.JanelaExercicio' to access menu panel in 'igraf.moduloSuperior.visao.PainelBotoes'                                               
 private PainelBotoes painelBotoes; // 'igraf/moduloSuperior/visao/PainelBotoes.java' is to access "menu exercise"
 public PainelBotoes getPainelBotoes () { return painelBotoes; }
 public void setPainelBotoes (PainelBotoes painelBotoes) { this.painelBotoes = painelBotoes; }

 private InfoPane infoPane = null; // igraf/moduloInferior/visao/InfoPane.java

 private InfoPaneController ipc = new InfoPaneController(this); // igraf/moduloInferior/controle/InfoPaneController.java
 public InfoPane getInfoPane () { return ipc.getInfoPane(); }


 // From: igraf.ContentPane(): ModuloInferior mi = new ModuloInferior();
 public ModuloInferior () {
  // try{String str="";System.out.println(str.charAt(3)); } catch(Exception e) {e.printStackTrace();}
  addInternEventListener(ipc); // defined in 'difusor/CommunicationFacade.java': public void addInternEventListener(CommunicationController iel)
  }

 // From: igraf/moduloCentral/visao/plotter/GraphPlotter.java: void mouseClicked(MouseEvent e)
 // From: igraf/moduloExercicio/ModuloExercicio.java: void setStatusBarMessage(String strMessage)
 public void setStatusBarMessage (String strMessage) {
  // ipc.setMessage(strMessage); // put message into 'igraf/moduloInferior/visao/InfoPane.java: JLabel'
  if (infoPane==null)
    infoPane = getInfoPane();
  infoPane.setStatusBarMessage(strMessage);
  }
 public void setDefaultInfo () {
  ipc.setDefaultInfo();
  }

 public Component getPane () {
  return ipc.getPane();
  }

 public void filtrarEventoEntrada(CommunicationEvent ie) {
  if (ie instanceof ModuloInferiorDisseminavelEvent)
   disseminarEventoInternamente((IgrafDescriptionEvent)ie);
  }

 // m�todo, momentaneamente, desnecess�rio pois o m�dulo inferior
 // s� recebe eventos de descri��o e exibe seu conte�do no InfoPane
 public void filtrarEventoSaida (CommunicationEvent ie) {
  //disseminarEventoExternamente(ie);
  }

 }
