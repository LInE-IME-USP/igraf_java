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
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new' para 'MenuGrafico(this, index)'
 * @see igraf/moduloCentral/controle/desenho/DesenhoAnimacaoController.java: control to launch animation options
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
import igraf.moduloCentral.controle.menu.IgrafMenuController;

import java.awt.Rectangle;


public class MenuAnimacao extends IgrafMenu {

 private static final String STR_MENU_NAME = "menuAnim";

 // Devido à necessidade de gravação dos menus desabilitados pelo professor
 // foi criada uma lista numérica para identificá-los de modo unívoco.
 // Novos menus devem ser acrescentados a esta lista de modo a não substituir
 // valores previamente utilizados, para que seja mantida a compatibilidade
 // com arquivos antigos. Todos os itens deste menu começam com '3'.
 public static final int STOP_ANIM  = 300;
 public static final int SHOW_CONTR = 301;
 public static final int SHOW_SLID  = 302;
 public static final int FUNC_TRACK = 303;
 public static final int INC_VEL    = 304;
 public static final int DEC_VEL    = 305;

 public static final int commandNumberStar = 300; // 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'

 // Menu itens name
 // @see: igraf/moduloSuperior/visao/PainelBotoes.java: use 'menuItensName' to build all menus
 // @see: igraf/moduloCentral/controle/desenho/DesenhoAnimacaoController.java: control to lauche animations options
 //TODO: retornar as opcoes de velocidade de animacao (SEP, maVelAmplia, maVelDiminui) apos implementar e ativar em 'DesenhoAnimacaoController.trataEvento(CommunicationEvent)'
 //TODO: idem abaixo em 'updateLabels()'
 private static final String [] menuItensName = {
   "maParaAnima", SEP,
   "maMostraControle",
   "maMostraCDesl", SEP,
   "maRastro"//TODO: , SEP,
   //TODO: "maVelAmplia",
   //TODO: "maVelDiminui"
   };
 // To all Menu(*) return their options name - used in 'igraf/moduloSuperior/visao/PainelBotoes.java'
 public String [] getMenuItensNames () { return menuItensName; } // root name of each item before 'ResourceReader.msg(...)'
 public static String [] getMenuItensName () { return menuItensName; }
 public String [] getMenuItensText () { return listaOpcoes; }
 public String [] getMenuItensDescription () { return descricao; }

 // Description to tooltips: in 'PainelBotoes' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 private static final String [] menuItemsTooltips = {
    "maParaAnimaTodos", null,
    "maMostraControleIndividual",
    "maMostraCDeslParado", null, 
    "maRastrearAnimacao"//TODO:, null,
    //TODO: "maVelAmpliaStr", 
    //TODO: "maVelDiminuiStr"
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
 public MenuAnimacao (IgrafMenuController imc, int index) {
  super(imc, index);
  super.setName(STR_MENU_NAME);
  updateLabels(); //04/2013: define at the first time 'String [] listaOpcoes, descricao'

  //leo setListaItens(listaOpcoes, descricao);
  //leo r = new Rectangle(280, -1, 180, listaOpcoes.length*20);
  //leo setBounds(r);  
  //leo setEnableAllMenuItem(false); -> 'completeAfterButtonsPanel()'
  }

 // Called by: igraf/moduloSuperior/visao/PainelBotoes.java: IgrafMenu.setPainelBotoes(PainelBotoes,JMenuItem[])
 public void completeAfterButtonsPanel () {
  setEnableAllMenuItem(false);
  }

 public void updateLabels () {
  //T System.out.println("MenuAnimacao.java: updateLabels()");Me
  String [] labels = {
    ResourceReader.msg("maParaAnima"), SEP,
    ResourceReader.msg("maMostraControle"),
    ResourceReader.msg("maMostraCDesl"), SEP,
    ResourceReader.msg("maRastro")//TODO: , SEP,
    //TODO: ResourceReader.msg("maVelAmplia"), 
    //TODO: ResourceReader.msg("maVelDiminui")
    };

  String [] help = {
    ResourceReader.msg("maParaAnimaTodos"), null,
    ResourceReader.msg("maMostraControleIndividual"), 
    ResourceReader.msg("maMostraCDeslParado"), null, 
    ResourceReader.msg("maRastrearAnimacao")//TODO:, null,
    //TODO: ResourceReader.msg("maVelAmpliaStr"), 
    //TODO: ResourceReader.msg("maVelDiminuiStr")
    };

  //R 04/2013: redefine 'String [] listaOpcoes' and 'String [] descricao'
  updateLabels(labels, help);  
  }

 public String toString () {
  return ResourceReader.msg(STR_MENU_NAME);
  }

 }
