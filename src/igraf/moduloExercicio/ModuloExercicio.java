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
 * @see difusor/Broadcaster.java: broadcastEvent(CommunicationEvent e) { for (Iterator iter=listaModulos.iterator(); iter.hasNext();) { CommunicationFacade modulo=(CommunicationFacade) iter.next(); modulo.filtrarEventoEntrada(e);  }
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.moduloExercicio.controle.JanelaExercicioController;
import igraf.moduloExercicio.controle.JanelaRespostaController;
import igraf.moduloExercicio.eventos.ModuloExercicioDisseminavelEvent;
import igraf.moduloExercicio.visao.menuSelector.JanelaSeletorMenuController;
import igraf.moduloExercicio.visao.menuSelector.MenuSelectorFrame;
import igraf.moduloInferior.ModuloInferior;
import igraf.moduloInferior.visao.InfoPane;
import igraf.moduloSuperior.visao.PainelBotoes;


public class ModuloExercicio extends CommunicationFacade {

 private IGraf igraf; // to send exercise when user push Evaluation internal button

 JanelaExercicioController   jec = null; // new JanelaExercicioController(this, true); // from: igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 JanelaRespostaController    jrc = new JanelaRespostaController(this, true);
 JanelaSeletorMenuController jsm = new JanelaSeletorMenuController(this, true);

 // To access 'PainelBotoes' in 'igraf.moduloInferior.ModuloInferior'
 private PainelBotoes painelBotoes; // to access "menu exercise"
 public PainelBotoes getPainelBotoes () { return painelBotoes; }

 // To access 'InfoPane' in 'igraf.moduloInferior.ModuloInferior'
 private InfoPane infoPane;
 public InfoPane getInfoPane () { return infoPane; }
 public void setStatusBarMessage (String strMessage) {
   infoPane.setStatusBarMessage(strMessage); // moduloInferior.setStatusBarMessage(strMessage);
   }

 public MenuSelectorFrame getMenuSelectorFrame () { // src/igraf/ContentPane.java: criaRelacionamentos(...)
   return jsm.getMenuSelectorFrame(); // igraf.moduloExercicio.visao.menuSelector.JanelaSeletorMenuController jsm
   }


 public ModuloExercicio (ModuloInferior modInf) {
   // igraf/moduloInferior/ModuloInferior.java
   this.infoPane = modInf.getInfoPane();
   jec = new JanelaExercicioController(this, true);
   //T System.out.println("src/igraf/moduloExercicio/ModuloExercicio.java: infoPane=" + this.infoPane);
   }

 // ContentPane makes 'ModuloExercicio me = new ModuloExercicio()'
 // ModuloExercicio make 'JanelaExercicioController   jec = new JanelaExercicioController(this, true)
 // JanelaExercicioController needs in 'actionPerformed(ActionEvent e)' access to IGraf to send answer to the server when click "Evaluation"
 public void setIGraf (IGraf igraf) {
  this.igraf = igraf; // Exercise
  jec.setIGraf(igraf);
  }


 // From: igraf/ContentPane.java: relationsModulesToBroadcaster(IGraf igraf, boolean ehApplet): me.setPainelBotoes(ms.getPainelBotoes());
 public void  setPainelBotoes (PainelBotoes painelBotoes) {
  this.painelBotoes = painelBotoes; // to 'igraf/moduloExercicio/visao/JanelaExercicio.java'
  jec.setPainelBotoes(painelBotoes);
  }


 public void filtrarEventoEntrada (CommunicationEvent ie) {
  if (ie instanceof ModuloExercicioDisseminavelEvent) {
   //T System.out.println("ModuloExercicio.filtrarEventoEntrada: ModuloExercicioDisseminavelEvent = " + ie);
   disseminarEventoInternamente(ie);
   // calls 10. 'difusor.CommunicationFacade.disseminarEventoInternamente(CommunicationFacade.java:112)' and returns to 'ModuloExercicio'
   // calls 11. 'igraf.moduloExercicio.controle.JanelaExercicioController.tratarEventoRecebido(JanelaExercicioController.java:70)'
   }

  }

 public void filtrarEventoSaida (CommunicationEvent ie) {
  disseminarEventoExternamente(ie); // difusor/CommunicationFacade.java: for (int ii_0=0; ii_0<listaOuvintesExternos.size(); ii_0++)
  }

 }
