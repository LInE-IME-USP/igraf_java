/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author RP, LOB
 * 
 * @description Starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * O painel superior é um objeto de fachada. Por meio dele, o painel de botões, a entrada de expressões e o
 * menu arquivo se comunicam com o mundo enviando e recebendo eventos. Assim, este objeto tem características
 * de gerador e de receptor de eventos. Por ser um receptor de eventos do iGraf, este objeto implementa a interface IgrafEventListener.
 * Todo evento que recebe é propagado para os objetos que estão sob sua responsabilidade.<br>
 * Por ser um gerador de eventos, possui métodos para adicionar e remover ouvintes. Pela arquitetura atual do sistema,
 * o único objeto que "ouve" os eventos gerados a partir do painel superior é o Broadcaster. 
 * 
 * @see igraf/moduloSuperior/PainelSuperior.java: it is the superior panel basis
 * @see igraf/moduloSuperior/PainelTitulo.java: panel with iGraf decorations
 * @see igraf/moduloSuperior/PanelBackImage.java: auxiliary panel to presents JPanel with background image
 * 
 * @see igraf/moduloCentral/ModuloCentral.java: here is created the 'HashMap hashMapIGrafMenus' with all 'igraf.moduloCentral.visao.menu.Menu(*)'
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 *
 */

package igraf.moduloSuperior;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.Configuracoes;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.ModuloCentral;
import igraf.moduloSuperior.controle.MenuArquivoController;
import igraf.moduloSuperior.controle.PainelBotoesController;
import igraf.moduloSuperior.controle.entrada.EntradaExpressaoController;
import igraf.moduloSuperior.eventos.ModuloSuperiorDisseminavelEvent;
import igraf.moduloSuperior.visao.EntradaExpressao;
import igraf.moduloSuperior.visao.PainelBotoes;
import igraf.moduloSuperior.visao.PainelSuperior;
import igraf.moduloSuperior.visao.PainelTitulo;


public class ModuloSuperior extends CommunicationFacade {

 private MenuArquivoController menuArquivoController = new MenuArquivoController(this, true);
 private PainelBotoesController panelPrimaryButtonsController = new PainelBotoesController(this, true);
 private EntradaExpressaoController entradaExpressaoController = new EntradaExpressaoController(this, true); // controller to any user entrance - instantiate the visual component
 
 // igraf/moduloCentral/ModuloCentral.java: has 'IgrafMenuGraficoController imgc, ... IgrafMenuAjudaController imaj' that has access respectivelly to 'MenuGrafico, ..., MenuAjuda'
 private ModuloCentral moduloCentral = null;

 private PainelSuperior panelGeneral;

 private PainelBotoes panelPrimaryButtons;
 public  PainelBotoes getPainelBotoes () { return panelPrimaryButtons; } // from: igraf/ContentPane.java: criaRelacionamentos(...)

 private EntradaExpressao panelExpression;

 public EntradaExpressaoController getEntradaExpressaoController () { return entradaExpressaoController; }

 public ModuloSuperior (boolean ehApplet, ModuloCentral modCentral) {

  // igraf.moduloSuperior.visao.PainelBotoes: needs access to 'ModuloCentral.Menu(*)' under 'ModuloCentral.getHashMapIGrafMenus()'
  moduloCentral = modCentral; // to inform 'PainelBotoes'

  // Create basic panels
  panelExpression = new EntradaExpressao(entradaExpressaoController); // panel to enter functions descriptions
  panelPrimaryButtons = new PainelBotoes(panelPrimaryButtonsController, modCentral); // menu buttons: primary menu

  // Organize the 3 top panels of iGraf
  if (IGraf.LAYOUTNOVO) {
    panelGeneral = new PainelSuperior(true, menuArquivoController, panelPrimaryButtons);  // create superior panel
    panelGeneral.criaPainelTitulo(ehApplet, panelExpression); // create superior panel
    }
  else {
    panelGeneral = new PainelSuperior(false, menuArquivoController, panelPrimaryButtons);  // create superior panel: do not put primary buttons on the top panel
    panelGeneral.criaPainelTitulo(ehApplet, panelExpression); // create superior panel
    }
    
  }


 public Component getPane () {
  return panelGeneral;
  }

 // Filters all events related to ModuloSuperior (like, update all labels in language changes)
 public void filtrarEventoEntrada (CommunicationEvent ie) {
  if (ie instanceof ModuloSuperiorDisseminavelEvent)
   disseminarEventoInternamente(ie);
  }

 public void filtrarEventoSaida (CommunicationEvent ie) {
  // escrever o código do filtro (try/catch)   
  disseminarEventoExternamente(ie);
  }

 }
