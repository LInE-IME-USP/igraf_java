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
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: starting class to send events to special menu controller (from menus): IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new' para 'MenuGrafico(this, index)'
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf.moduloCentral.visao.menu;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.controle.menu.IgrafMenuController;
import igraf.moduloCentral.controle.menu.IgrafMenuExercicioController;


public class MenuExercicio extends IgrafMenu {

 private static final String STR_MENU_NAME = "menuExerc";

 // Devido � necessidade de grava��o dos menus desabilitados pelo professor
 // foi criada uma lista num�rica para identific�-los de modo un�voco.
 // Novos menus devem ser acrescentados a esta lista de modo a n�o substituir
 // valores previamente utilizados, para que seja mantida a compatibilidade
 // com arquivos antigos. Todos os itens deste menu come�am com '6'.
 public static final int HISTORY    = 600;
 public static final int ANSWER     = 601;
 public static final int EVALUATION = 602;
 public static final int CREATION   = 603;
 public static final int EDITING    = 604;
 public static final int CONFIG     = 605; 

 public static final int commandNumberStar = 600; // 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'

 // Menu itens name
 // See: igraf/moduloSuperior/visao/PainelBotoes.java: use 'menuItensName' to build all menus
 private static final String [] menuItensName = {
   "mexExercHistorico", SEP, 
   "mexExercResp",             // Answer / send
   "mexExercEval", SEP,        // Visualize answers
   "mexExercCriar", 
   "mexExercEditar", SEP,
   "mexExercMenuConfig"
   };
 // To all Menu(*) return their options name - used in 'igraf/moduloSuperior/visao/PainelBotoes.java' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 public String [] getMenuItensNames () { return menuItensName; } // root name of each item before 'ResourceReader.msg(...)'
 public static String [] getMenuItensName () { return menuItensName; }
 public String [] getMenuItensText () { return listaOpcoes; }
 public String [] getMenuItensDescription () { return descricao; }


 // This avoid user to lose access to menu option "Config menus"
 // In 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java': avoid the problematic optin "Config menus"
 private static final String [] menuItensNameSM = { "mexExercHistorico", SEP, "mexExercResp", "mexExercEval", SEP, "mexExercCriar", "mexExercEditar" };
 public static String [] getMenuItensNameSM () { return menuItensNameSM; }

 // Description to tooltips: in 'PainelBotoes' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 private static final String [] menuItemsTooltips = {
    "mexExercAjudaExibe", null, 
    "mexExercAjudaResp", 
    "mexExercAjudaEval",null, 
    "mexExercAjudaGab", 
    "mexExercAjudaGabEd", null,
    "mexExercAjudaMenuConfig"
    };
 public static String [] getMenuItemsTooltips () { return menuItemsTooltips; }


 // mexExercResp
 // Criar m�todo de atualiza��o do menu em fun��o do contexto com os valores:
 //  String [] historico   = { ResourceReader.msg("mexExercHistorico")  }; // Hist�rico
 //  String [] listaEnvio  = { ResourceReader.msg("mexExercHistorico"), SEP, ResourceReader.msg("mexExercResp")  }; // usada quando for exerc�cio


 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public MenuExercicio (IgrafMenuController imc, int index) {
  super((IgrafMenuExercicioController)imc, index);
  super.setName(STR_MENU_NAME);

  updateLabels(); //04/2013: define at the first time 'String [] listaOpcoes, descricao'

  }


 // Called by: igraf/moduloSuperior/visao/PainelBotoes.java: IgrafMenu.setPainelBotoes(PainelBotoes,JMenuItem[])
 // @see: IgrafMenu.hashMapDoNotRemove
 public void completeAfterButtonsPanel () {
  setEnabledMenuItem(ResourceReader.msg("mexExercHistorico"), false);  
  setEnabledMenuItem(ResourceReader.msg("mexExercEditar"), false);
  setEnabledMenuItem(ResourceReader.msg("mexExercCriar"), true);
  setEnabledMenuItem(ResourceReader.msg("mexExercResp"), false);
  setEnabledMenuItem(ResourceReader.msg("mexExercEval"), false);

  //T System.out.println("MenuExercicio.java: completeAfterButtonsPanel(): ehApplet=" + IGraf.ehApplet + ": " + IGraf.iLM_PARAM_authoring);

  // Configure "hard" enable properties: for now, only mexExercMenuConfig="Configure menus"
  //  setEnabledMenuItem("mexExercMenuConfig", false); // application => always it is visible; applet => is only visible when authorign session (teacher)
  if (IGraf.ehApplet) {
   // IGraf igraf = IGraf.getInstance();
   if (IGraf.iLM_PARAM_authoring==null || !IGraf.iLM_PARAM_authoring.equals("true")) { // "true" => authorign; otherwise, not
    // command number 605 is set 'not invisible if application' by 'IgrafMenu.setDoNotRemove(String)'
    setEnabledMenuItem(ResourceReader.msg("mexExercMenuConfig"), false); // security...
    int position = removeMenuItem("mexExercMenuConfig"); // set "not visible" the option "Configure menus" (must use with the message root - before use of 'ResourceReader.msg(.)'!)
    //T System.out.println("MenuExercicio.java: completeAfterButtonsPanel(): eliminando " + ResourceReader.msg("mexExercMenuConfig") + ": position=" + position);
    return;
    }
   }

  super.setDoNotRemove(STR_MENU_NAME, "mexExercMenuConfig");
  setEnabledMenuItem(ResourceReader.msg("mexExercMenuConfig"), true); // security... (application => always visible)

  }

 
 public void updateLabels () {
  String[] labels = {
    ResourceReader.msg("mexExercHistorico"), SEP,
    ResourceReader.msg("mexExercResp"),
    ResourceReader.msg("mexExercEval"), SEP,
    ResourceReader.msg("mexExercCriar"),
    ResourceReader.msg("mexExercEditar"), SEP,
    ResourceReader.msg("mexExercMenuConfig")
    };

  String [] help = {
    ResourceReader.msg("mexExercAjudaExibe"), null, 
    ResourceReader.msg("mexExercAjudaResp"), 
    ResourceReader.msg("mexExercAjudaEval"),null, 
    ResourceReader.msg("mexExercAjudaGab"), 
    ResourceReader.msg("mexExercAjudaGabEd"), null,
    ResourceReader.msg("mexExercAjudaMenuConfig")
    };

  //R 04/2013: redefine 'String [] listaOpcoes' and 'String [] descricao'
  updateLabels(labels, help);
  //try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

  }
 
 public String toString () {
  return ResourceReader.msg(STR_MENU_NAME);
  }

 }
