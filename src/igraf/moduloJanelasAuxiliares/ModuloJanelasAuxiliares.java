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
 * @description Extension of CommunicationFacade to distribute internal iGraf events
 *
 * @see difusor/CommunicationFacade.java: this extends it
 * @see igraf/moduloJanelasAuxiliares/controle/Janela(*)Controller.java
 * @see igraf/ContentPane.java: ModuloJanelasAuxiliares mj = new ModuloJanelasAuxiliares();
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloJanelasAuxiliares;

import moduloColor.ColorEvent;
import igraf.moduloCentral.eventos.DesenhoTextoEvent;
import igraf.moduloJanelasAuxiliares.controle.JanelaAnimacaoController;
import igraf.moduloJanelasAuxiliares.controle.JanelaEdicaoExpressaoController;
import igraf.moduloJanelasAuxiliares.controle.JanelaEntradaTextoController;
import igraf.moduloJanelasAuxiliares.controle.JanelaHistoricoController;
import igraf.moduloJanelasAuxiliares.controle.JanelaIntegralController;
import igraf.moduloJanelasAuxiliares.controle.JanelaParametrosController;
import igraf.moduloJanelasAuxiliares.controle.JanelaTangenteControler;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;


public class ModuloJanelasAuxiliares extends CommunicationFacade {

 private JanelaEdicaoExpressaoController jeec = new JanelaEdicaoExpressaoController(this, true); 
 private JanelaEntradaTextoController jetc = new JanelaEntradaTextoController(this, true);  
 private JanelaParametrosController jpc = new JanelaParametrosController(this, true);
 private JanelaHistoricoController jhc = new JanelaHistoricoController(this, true);
 private JanelaIntegralController jic = new JanelaIntegralController(this, true);
 private JanelaAnimacaoController jac = new JanelaAnimacaoController(this, true);
 private JanelaTangenteControler jtc = new JanelaTangenteControler(this, true);

 public void filtrarEventoEntrada (CommunicationEvent ie) {  
  if (ie instanceof ModuloJanelasDisseminavelEvent | ie instanceof ColorEvent) {
   disseminarEventoInternamente(ie);
   }
  }

 public void filtrarEventoSaida (CommunicationEvent ie) {
  try {
   disseminarEventoInternamente((DesenhoTextoEvent)ie);
  } catch (Exception e) { }
  
  disseminarEventoExternamente(ie);
  }

 }
