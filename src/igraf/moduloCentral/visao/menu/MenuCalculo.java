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
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: starting class to send events to special menu controller (from menus): IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new' para 'MenuGrafico(this, index)'
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 * 
 */

package igraf.moduloCentral.visao.menu;


import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.controle.menu.IgrafMenuCalculoController;
import igraf.moduloCentral.controle.menu.IgrafMenuController;

import java.awt.Rectangle;


public class MenuCalculo extends IgrafMenu {

 private static final String STR_MENU_NAME = "menuCalculo";

 // Devido à necessidade de gravação dos menus desabilitados pelo professor
 // foi criada uma lista numérica para identificá-los de modo unívoco.
 // Novos menus devem ser acrescentados a esta lista de modo a não substituir
 // valores previamente utilizados, para que seja mantida a compatibilidade
 // com arquivos antigos. Todos os itens deste menu começam com '2'.
 public static final int DERIVATIVE  = 200;
 public static final int TANGENT     = 201;
 public static final int IND_INTEG   = 202;
 public static final int DEF_INTEG   = 203;

 public static final int commandNumberStar = 200; // 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'

 // Menu itens name
 // See: igraf/moduloSuperior/visao/PainelBotoes.java: use 'menuItensName' to build all menus
 private static final String [] menuItensName = {
   // Visualize derivative, Visualize tangent line, Visualize indefinite integral, Calculate definite integral
   "mcDerVer", "mcTgVer", "mcIIVer", SEP, "mcIICalc"
   };
 // To all Menu(*) return their options name - used in 'igraf/moduloSuperior/visao/PainelBotoes.java'
 public String [] getMenuItensNames () { return menuItensName; } // root name of each item before 'ResourceReader.msg(...)'
 public static String [] getMenuItensName () { return menuItensName; }
 public String [] getMenuItensText () { return listaOpcoes; }
 public String [] getMenuItensDescription () { return descricao; }

 // Description to tooltips: in 'PainelBotoes' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 private static final String [] menuItemsTooltips = {
    "mcMsgDesGrafDer",
    "mcMsgMostraTg", 
    "mcMsgMostraInt", null, 
    "mcMsgMostraPII"
    };
 public static String [] getMenuItemsTooltips () { return menuItemsTooltips; }

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public MenuCalculo(IgrafMenuController imc, int index) {
  super((IgrafMenuCalculoController)imc, index);
  super.setName(STR_MENU_NAME);

  updateLabels(); //04/2013: define at the first time 'String [] listaOpcoes, descricao'

  //leo setListaItens(listaOpcoes, descricao);  
  //leo r = new Rectangle(140, -1, 180, listaOpcoes.length*20);
  //leo setBounds(r);
  //leo setEnableAllMenuItem(false); -> 'completeAfterButtonsPanel()'
  }

 // Called by: igraf/moduloSuperior/visao/PainelBotoes.java: IgrafMenu.setPainelBotoes(PainelBotoes,JMenuItem[])
 public void completeAfterButtonsPanel () {
  setEnableAllMenuItem(false);
  }

 public void updateLabels () {
  String [] labels = {
    ResourceReader.msg("mcDerVer"), 
    ResourceReader.msg("mcTgVer"), 
    ResourceReader.msg("mcIIVer"), SEP, 
    ResourceReader.msg("mcIICalc")
    };
  
  String [] help = {
    ResourceReader.msg("mcMsgDesGrafDer"), 
    ResourceReader.msg("mcMsgMostraTg"), 
    ResourceReader.msg("mcMsgMostraInt"), null, 
    ResourceReader.msg("mcMsgMostraPII")
    };
  
  //R 04/2013: redefine 'String [] listaOpcoes' and 'String [] descricao'
  updateLabels(labels, help);  
  }

 public String toString () {
  return ResourceReader.msg(STR_MENU_NAME);
  }

 }
