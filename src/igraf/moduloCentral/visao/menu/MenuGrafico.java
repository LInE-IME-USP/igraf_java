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
 * @description It implements the menu to "Graphics" options
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
import igraf.moduloCentral.controle.menu.IgrafMenuController;
import igraf.moduloCentral.controle.menu.IgrafMenuGraficoController;

import java.awt.Rectangle;

//leo
import javax.swing.ImageIcon;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//leo

public class MenuGrafico extends IgrafMenu {

 private static final String STR_MENU_NAME = "menuGrf";

 // Devido à necessidade de gravação dos menus desabilitados pelo professor
 // foi criada uma lista numérica para identificá-los de modo unívoco.
 // Novos menus devem ser acrescentados a esta lista de modo a não substituir
 // valores previamente utilizados, para que seja mantida a compatibilidade
 // com arquivos antigos. Todos os itens deste menu começam com '1'.
 public static final int DRAW        = 100;
 public static final int DRAW_ALL    = 101;
 public static final int EXPR_EDIT   = 102;
 public static final int SHOW_LIST   = 103;
 public static final int HIDE        = 104;
 public static final int HIDE_LAST   = 105;
 public static final int HIDE_ALL    = 106;
 public static final int REMOVE      = 107;
 public static final int REMOVE_ALL  = 108;
 public static final int NEW_TAB     = 109;
 public static final int REMOVE_TAB  = 110;
 public static final int NEW_SESSION = 111;

 public static final int commandNumberStar = 100; // 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'

 // Menu itens name
 // See: igraf/moduloSuperior/visao/PainelBotoes.java: use 'menuItensName' to build all menus
 private static final String [] menuItensName = {
   "msgMenuGrfDes",                      //  0 :  1
   "msgMenuGrfDesTodos", SEP,            //  1 :  2
   "msgMenuGrfEditaExp",                 //  3 :  3
   "msgMenuGrfExibeListaExp", SEP,       //  4 :  4
   "msgMenuGrfEsconder",                 //  6 :  5
   "msgMenuGrfEsconderUlt",              //  7 :  6
   "msgMenuGrfEsconderTodos", SEP,       //  8 :  7
   "msgMenuGrfRemoverGrafico",           // 10 :  8
   "msgMenuGrfRemoverTodos", SEP,        // 11 :  9
   "msgMenuGrfNovaAba",                  // 13 : 10
   "msgMenuGrfRemoverAba", SEP,          // 14 : 11
   "msgMenuGrfNovaSes"                   // 16 : 12
   };

 // Description to tooltips: in 'PainelBotoes' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 private static final String [] menuItemsTooltips = {
   "descrMenuGrfDesEdicao",              //  0 :  1
   "descrMenuGrfDesTodosOcultos", null,  //  1 :  2
   "descrMenuGrfJanFunc",                //  3 :  3
   "descrMenuGrfExibeListaExp", null,    //  4 :  4
   "descrMenuGrfEscondeGrafEdicao",      //  6 :  5
   "descrMenuGrfEscondeUltDes",          //  7 :  6
   "descrMenuGrfEscondeTodos", null,     //  8 :  7
   "descrMenuGrfRemoverGrafico",         // 10 :  8
   "descrMenuGrfRemoverTodos", null,     // 11 :  9
   "descrMenuGrfNovaAba",                // 13 : 10
   "descrMenuGrfRemoverAba", null,       // 14 : 11
   "descrMenuGrfIniNovaSes"              // 16 : 12
   };


 // To all Menu(*) return their options name - used in 'igraf/moduloSuperior/visao/PainelBotoes.java'
 public String [] getMenuItensNames () { return menuItensName; } // root name of each item before 'ResourceReader.msg(...)'
 public static String [] getMenuItensName () { return menuItensName; }
 public static String [] getMenuItemsTooltips () { return menuItemsTooltips; }

 public String [] getMenuItensText () { return listaOpcoes; }
 public String [] getMenuItensDescription () { return descricao; }


 // Quem efetivamente cria os botoes eh:
 // - src/igraf/moduloSuperior/visao/PainelBotoes.java

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public MenuGrafico (IgrafMenuController imc, int index) {
  super((IgrafMenuGraficoController)imc, index);
  super.setName(STR_MENU_NAME);

  updateLabels(); //04/2013: define at the first time 'String [] listaOpcoes, descricao'

  //leo r = new Rectangle(2, -1, 130, listaOpcoes.length*20);
  //leo setIcons(iconGraphics, "menuGrf");
  //leo setListaItens(listaOpcoes, descricao); - updateLabels() agora faz isso...
  //leo r = new Rectangle(2, -1, 130, listaOpcoes.length*20);
  //leo setBounds(r);
  //leo setEnableAllMenuItem(false); -> 'completeAfterButtonsPanel()'
  }


 // Called by: igraf/moduloSuperior/visao/PainelBotoes.java: IgrafMenu.setPainelBotoes(PainelBotoes,JMenuItem[])
 // Always the option "New tab" must be enable (unless it was removed by the teacher from edition of "Configuration menu")
 public void completeAfterButtonsPanel () {
  setEnableAllMenuItem(false);
  setEnabledMenuItem(ResourceReader.msg("msgMenuGrfNovaAba"), true); // only enabled "New tab"
  }


 public void updateLabels () {
  //System.out.println("src/igraf/moduloCentral/visao/menu/MenuGrafico.java: updateLabels(): msgMenuGrfDes=" + ResourceReader.msg("msgMenuGrfDes"));
  String [] labels = {
   ResourceReader.msg("msgMenuGrfDes"),                      //  0 :  1
   ResourceReader.msg("msgMenuGrfDesTodos"), SEP,            //  1 :  2
   ResourceReader.msg("msgMenuGrfEditaExp"),                 //  3 :  3
   ResourceReader.msg("msgMenuGrfExibeListaExp"), SEP,       //  4 :  4
   ResourceReader.msg("msgMenuGrfEsconder"),                 //  6 :  5
   ResourceReader.msg("msgMenuGrfEsconderUlt"),              //  7 :  6
   ResourceReader.msg("msgMenuGrfEsconderTodos"), SEP,       //  8 :  7
   ResourceReader.msg("msgMenuGrfRemoverGrafico"),           // 10 :  8
   ResourceReader.msg("msgMenuGrfRemoverTodos"), SEP,        // 11 :  9
   ResourceReader.msg("msgMenuGrfNovaAba"),                  // 13 : 10
   ResourceReader.msg("msgMenuGrfRemoverAba"), SEP,          // 14 : 11
   ResourceReader.msg("msgMenuGrfNovaSes")                   // 16 : 12
   };

  String [] help = {
   ResourceReader.msg("descrMenuGrfDesEdicao"),              //  0 :  1
   ResourceReader.msg("descrMenuGrfDesTodosOcultos"), null,  //  1 :  2
   ResourceReader.msg("descrMenuGrfJanFunc"),                //  3 :  3
   ResourceReader.msg("descrMenuGrfExibeListaExp"), null,    //  4 :  4
   ResourceReader.msg("descrMenuGrfEscondeGrafEdicao"),      //  6 :  5
   ResourceReader.msg("descrMenuGrfEscondeUltDes"),          //  7 :  6
   ResourceReader.msg("descrMenuGrfEscondeTodos"), null,     //  8 :  7
   ResourceReader.msg("descrMenuGrfRemoverGrafico"),         // 10 :  8
   ResourceReader.msg("descrMenuGrfRemoverTodos"), null,     // 11 :  9
   ResourceReader.msg("descrMenuGrfNovaAba"),                // 13 : 10
   ResourceReader.msg("descrMenuGrfRemoverAba"), null,       // 14 : 11
   ResourceReader.msg("descrMenuGrfIniNovaSes")              // 16 : 12
   };

  // 04/2013: redefine 'String [] listaOpcoes' and 'String [] descricao'    
  updateLabels(labels, help); // in 'IgrafMenu' updates: this.listaOpcoes = labels;  this.descricao = help;
  }

 public String toString () {
  // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  return ResourceReader.msg(STR_MENU_NAME);
  }

 }
