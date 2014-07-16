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
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
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
import igraf.moduloCentral.controle.menu.IgrafMenuPoligonoController;

import java.awt.Rectangle;

public class MenuPoligono extends IgrafMenu {

 private static final String STR_MENU_NAME = "menuPol";

 // Devido à necessidade de gravação dos menus desabilitados pelo professor
 // foi criada uma lista numérica para identificá-los de modo unívoco.
 // Novos menus devem ser acrescentados a esta lista de modo a não substituir
 // valores previamente utilizados, para que seja mantida a compatibilidade
 // com arquivos antigos. Todos os itens deste menu começam com '4'.
 public static final int POINT   = 400;
 public static final int SEGM    = 401;
 public static final int TRIAN   = 402;
 public static final int RECT    = 403;
 public static final int REG_POL = 404;
 public static final int ANY_POL = 405;
 public static final int S_RECT  = 406;
 public static final int S_POL   = 407;
 public static final int PAINT   = 408;

 public static final int commandNumberStar = 400; // 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'

 // Menu itens name
 // See: igraf/moduloSuperior/visao/PainelBotoes.java: use 'menuItensName' to build all menus
 private static final String [] menuItensName = {
   "mepPonto",
   "mepSegm",
   "mepTri",
   "mepRet",
   "mepPolReg",
   "mepPolQqr", SEP,
   "mepRetEsp",
   "mepPolEsp", SEP,
   "mepPintaPoli"
   };
 // To all Menu(*) return their options name - used in 'igraf/moduloSuperior/visao/PainelBotoes.java'
 public String [] getMenuItensNames () { return menuItensName; } // root name of each item before 'ResourceReader.msg(...)'
 public static String [] getMenuItensName () { return menuItensName; }
 public String [] getMenuItensText () { return listaOpcoes; }
 public String [] getMenuItensDescription () { return descricao; }

 // Description to tooltips: in 'PainelBotoes' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 private static final String [] menuItemsTooltips = {
    "mepDescPonto",
    "mepDescSegm",
    "mepDescTri",
    "mepDescRet",
    "mepDescPolReg",
    "mepDescPolQqr", null,
    "mepDescRetEsp",
    //"mepDescPintaGrade",
    "mepDescPolEsp", null,
    "mepDescPintaPoli"
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
 public MenuPoligono (IgrafMenuController imc, int index) {
  super((IgrafMenuPoligonoController)imc, index);
  super.setName(STR_MENU_NAME);

  updateLabels(); //04/2013: define at the first time 'String [] listaOpcoes, descricao'

  //leo setListaItens(listaOpcoes, descricao);
  //leo r = new Rectangle(2, -1, 130, listaOpcoes.length*20);
  //leo setBounds(r);
  }


 // Called by: igraf/moduloSuperior/visao/PainelBotoes.java: IgrafMenu.setPainelBotoes(PainelBotoes,JMenuItem[])
 public void completeAfterButtonsPanel () {
  //TODO: if MenuPoligono demands some 'setEnabled(false)' in one option, put it here as: setEnabledMenuItem(ResourceReader.msg("<string name in properties language>"), false);
  }

 public void updateLabels () {
  String [] labels = {
   ResourceReader.msg("mepPonto"), 
   ResourceReader.msg("mepSegm"), 
   ResourceReader.msg("mepTri"), 
   ResourceReader.msg("mepRet"),                    
   ResourceReader.msg("mepPolReg"), 
   ResourceReader.msg("mepPolQqr"), SEP,
   ResourceReader.msg("mepRetEsp"),
   //ResourceReader.msg("mepPintaGrade"),
   ResourceReader.msg("mepPolEsp"), SEP,
   ResourceReader.msg("mepPintaPoli")
   };

  String [] help = {
    ResourceReader.msg("mepDescPonto"),
    ResourceReader.msg("mepDescSegm"),
    ResourceReader.msg("mepDescTri"),
    ResourceReader.msg("mepDescRet"),
    ResourceReader.msg("mepDescPolReg"),
    ResourceReader.msg("mepDescPolQqr"), null,
    ResourceReader.msg("mepDescRetEsp"),
    //ResourceReader.msg("mepDescPintaGrade"),
    ResourceReader.msg("mepDescPolEsp"), null,
    ResourceReader.msg("mepDescPintaPoli")
    };

  //R 04/2013: redefine 'String [] listaOpcoes' and 'String [] descricao'
  updateLabels(labels, help);
  }

 public String toString () {
  return ResourceReader.msg(STR_MENU_NAME);
  }

 }
