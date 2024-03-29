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
 * @description This extends JPanel and has all other iGraf inaterface panels. It has the "superior panel" ModuloSuperior (with Graphics, Calculus, ...)
 *              access to help window (ModuloAjuda), the "central main panel" (ModuloCentral), and "inferior panel" (ModuloInferior), among other.
 * 
 * @see src/igraf/moduloSuperior/visao/PainelBotoes.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import igraf.moduloAjuda.ModuloAjuda;
import igraf.moduloArquivo.ModuloArquivo;
import igraf.moduloCentral.ModuloCentral;
import igraf.moduloExercicio.ModuloExercicio;
import igraf.moduloExercicio.visao.menuSelector.MenuSelectorFrame;
import igraf.moduloInferior.ModuloInferior;
import igraf.moduloInferior.visao.InfoPane;
import igraf.moduloJanelasAuxiliares.ModuloJanelasAuxiliares;
import igraf.moduloSuperior.ModuloSuperior;
import igraf.moduloSuperior.visao.PainelBotoes;

import difusor.Broadcaster;


public class ContentPane extends JPanel {

 private ModuloSuperior ms;
 private ModuloAjuda maj = new ModuloAjuda();
 private ModuloInferior mi = new ModuloInferior();
 private ModuloCentral mc = new ModuloCentral(mi); // reference to ModuloInferior in order to allow access to "status bar" 'igraf/moduloInferior/visao/InfoPane.java'
 private ModuloArquivo ma = new ModuloArquivo();
 private ModuloExercicio me = new ModuloExercicio(mi); // ModuloExercicio needs iGraf...; reference to ModuloInferior to use "status bar" in Exercises
 private ModuloJanelasAuxiliares mj = new ModuloJanelasAuxiliares();

 private Broadcaster b = new Broadcaster();

 private InfoPane infoPane = mi.getInfoPane(); // igraf/moduloInferior/visao/InfoPane.java
 public InfoPane getInfoPane () { return infoPane; }


 public ContentPane (IGraf igraf, boolean ehApplet) {
  //ATTENTION: stablish relations between modules and broadcaster (e.g. necessary to change language)
  // do not try here '?' - let it to 'igraf/IGraf.java' constructor call this after its 'init()' and after the reading file
  relationsModulesToBroadcaster(igraf,ehApplet);

  setLayout(new BorderLayout());
  add(ms.getPane(), BorderLayout.NORTH);
  add(mc.getPane());
  add(mi.getPane(), BorderLayout.SOUTH);
  }


 /**
  * This method help iGraf to stablish some fixed relations between modules. E.g., for now, it is necessary to connect listeners (IgrafBroadcastEventListener)
  * and the events broadcaster (Broadcaster). The parameter 'boolean ehApplet' is necessary to build correct version application or "applet".
  * From: igraf.IGraf.init() that instantiate 'ContentPane'
  * @param IGraf igraf, boolean ehApplet
  */
 public void relationsModulesToBroadcaster (IGraf igraf, boolean ehApplet) {

  // igraf.moduloCentral.ModuloCentral:                        has from 'igraf/moduloCentral/controle/menu/IgrafMenu(*)Controller'
  // igraf.moduloCentral.controle.menu.IgrafMenu(*)Controller: has {MenuGrafico,MenuCalculo,MenuAnimacao,MenuEdicao,MenuExercicio,MenuPoligono,MenuAjudaController}
  // igraf.moduloSuperior.ModuloSuperior:                      has 'PainelBotoes painelBotoes' and 'ModuloCentral moduloCentral'
  // igraf.moduloSuperior.visao.PainelBotoes:                  needs 'ModuloCentral mc' build popup menus over 'MenuGrafico, MenuCalculo, MenuAnimacao, MenuEdicao, MenuExercicioo,  //MenuPoligono, MenuAjuda'
  ms = new ModuloSuperior(ehApplet, mc);

  // ContentPane makes 'ModuloExercicio me = new ModuloExercicio()'
  // ModuloExercicio make 'JanelaExercicioController   jec = new JanelaExercicioController(this, true)
  // JanelaExercicioController needs in 'actionPerformed(ActionEvent e)' access to IGraf to send answer to the server when click "Evaluation"
  me.setIGraf(igraf); // ModuloExercicio

  // ModuloJanelasAuxiliares mj
  // ModuloJanelasAuxiliares.java: private JanelaAnimacaoController jac = new JanelaAnimacaoController(this, true);
  // src/igraf/moduloCentral/controle/desenho/DesenhoController.java: 'protected Vector listaDesenho' is ' DesenhoPoligonoController; ... DesenhoTextoController'
  // 
  // src/igraf/moduloSuperior/ModuloSuperior.java:
  //  + EntradaExpressaoController entradaExpressaoController = new EntradaExpressaoController(this, true);
  //  + public EntradaExpressaoController getEntradaExpressaoController()
  // src/igraf/moduloSuperior/controle/entrada/EntradaExpressaoController.java: 
  // AQUI!!!!

  // Allow 'igraf.moduloInferior.controle.InfoPaneController' to have access to "status bar"
  ms.getEntradaExpressaoController().setInfoPanel(infoPane);

  // To allow 'igraf.moduloExercicio.visao.JanelaExercicio' to access menu panel in 'igraf.moduloSuperior.visao.PainelBotoes'
  me.setPainelBotoes(ms.getPainelBotoes()); // 'igraf.moduloExercicio.ModuloExercicio'

  // conex�o das fachadas com o broadcaster; essa separa��o foi pensada para
  // contemplar as situa��es em que um m�dulo queira ouvir, mas n�o enviar eventos, ou ainda,
  // para que um m�dulo possa ouvir ou enviar eventos de/para mais que um broadcaster
 
  // torna o broadcaster 'b' ouvinte dos eventos gerados pelos m�dulos 
  ms.addBroadcaster(b);
  ma.addBroadcaster(b); 
  mc.addBroadcaster(b);
  mj.addBroadcaster(b);
  me.addBroadcaster(b);
  // mi.addIgrafModuleEventListener(b); [igraf/moduloInferior/ModuloInferior.java: mi n�o gera eventos, momentaneamente]
 
  // habilita os m�dulos a receberem eventos distribu�dos pelo broadcaster 'b'  
  b.addModule(ma);
  b.addModule(mc);
  b.addModule(me);
  b.addModule(mi);
  b.addModule(mj);
  b.addModule(ms);
  b.addModule(maj);
  }


 /*
  * This method help iGraf to stablish some elations between modules. It is necessary to make relation between:
  * menus (igraf/moduloSuperior/visao/PainelBotoes.java) and its configuration (igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java).
  * exercise (igraf/moduloExercicio/ModuloExercicio.java) and status bar (igraf/moduloInferior/controle/InfoPaneController.java)
  * From: igraf.IGraf.IGraf(String[] args)
  * @param IGraf igraf, boolean ehApplet
  */
 public void relationsModulesDinamics (IGraf igraf, boolean ehApplet) {

  // ContentPane must set a reference to the panel with "status bar" (at the bottom of iGraf)
  // Since the "status bar" is unique 'igraf.moduloInferior.visao.InfoPane', put in each instance of 'igraf.moduloCentral.visao.plotter.GraphPlotter' a reference to InfoPan
  // + GraphPlotter: ModuloCentral mc . PainelCentral painelCentral . TabbedViewer tabbedViewer . AreaDesenho areaDesenho . GraphPlotter graphPlotter
  // + InfoPane    : ModuloInferior mi . InfoPaneController ipc .  InfoPane ip;

  // In order to be possible to configure manu items through 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
  // it is necessary access to menus in 'igraf/moduloSuperior/visao/PainelBotoes.java'
  PainelBotoes painelBotoes = ms.getPainelBotoes();                            // ModuloSuperior ms: igraf/moduloSuperior/ModuloSuperior.java:
  MenuSelectorFrame menuSelectorFrame = me.getMenuSelectorFrame();             // ModuloExercicio me: igraf/moduloExercicio/ModuloExercicio.java
  // menuSelectorFrame.setHashMapMenus(painelBotoes.getHashMapMenus());           // igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java
  //X menuSelectorFrame.setHashMapPopupMenus(painelBotoes.getHashMapPopupMenus());
  menuSelectorFrame.setMenuItemsVisible(painelBotoes.getHashMapPopupMenus()); // NAO resolve tentar pegar estado dos menus agora, pois ainda nao foi montado se leitura de arquivo
  //T java.util.HashMap hashMap = painelBotoes.getHashMapPopupMenus();
  //T System.out.println("src/igraf/ContentPane.java: #hashMap=" + hashMap.size() + "");

  }
   

 /**
  * M�todo especialmente criado para fazer a conex�o da classe iGraf com os demais m�dulos do programa
  * @param igc
  */
 public void addIgrafController (IGrafController igc) {
  igc.addBroadcaster(b);
  b.addModule(igc);
  }

 }
