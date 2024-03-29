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
 * @description Base class to each primary menu item ("primary buttons")
 * Classe que implementa um menu do iGraf. Basicamente este menu apresenta uma lista de op��es sobre as quais o usu�rio pode
 * clicar para utilizar alguma fun��o do sistema. Cada menu tem uma lista de op��es e esta pode ser estendida ou reduzida a qualquer tempo.
 * Para alterar a lista de op��es exibidas por um menu, adicione/remova uma string na subclasse IgrafMenuXXX e para que esta
 * op��o passe a enviar eventos, adicione a mesma no m�todo IgrafMenuXXXController.enviarEventoAcao.
 * IgrafMenu.java is extended by: MenuGrafico.java; MenuCalculo.java; MenuAnimacao.java; MenuEdicao.java; MenuExercicio.java; MenuAjuda.java; MenuPoligono.java; SubMenuIdioma.java
 * 
 * @see igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java: disable items in accordance of the teacher
 * @see igraf/moduloCentral/visao/menu/IgrafMenu.java: where it is instantiated in
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

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import igraf.basico.io.ResourceReader;
import difusor.evento.CommunicationEvent;
import igraf.moduloCentral.controle.menu.IgrafMenuController;
import igraf.moduloCentral.controle.menu.IgrafMenuGraficoController;
import igraf.moduloCentral.controle.menu.IgrafMenuCalculoController;
import igraf.moduloCentral.controle.menu.IgrafMenuAnimacaoController;
import igraf.moduloCentral.controle.menu.IgrafMenuEdicaoController;
import igraf.moduloCentral.controle.menu.IgrafMenuExercicioController;
import igraf.moduloCentral.controle.menu.IgrafMenuAjudaController;
import igraf.moduloCentral.controle.menu.IgrafMenuPoligonoController;
import igraf.moduloCentral.eventos.menu.IgrafSubMenuEvent;
import igraf.moduloInferior.eventos.IgrafDescriptionEvent;

//leo
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import igraf.moduloSuperior.visao.PainelBotoes;
import igraf.IGraf;
//leo


public class IgrafMenu extends JPanel {

 //DEBUG
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/menu/IgrafMenu.java"; //DEBUG: if IGraf.IS_DEBUG get a complete path of this class

 //DEBUG try{ String str=""; System.err.println(str.charAt(3)); }catch(Exception e) {e.printStackTrace();}

 private static int COUNTINSTANCES = 0;
 private int id = COUNTINSTANCES++;
 public  int getId () { return id; }

 // Separator to JMenuItem - use javax.swing.JSeparator ?
 public static final String SEP = null; // ""; // also used in 'igraf/moduloSuperior/visao/PainelBotoes.java'


 //TODO: Melhorar! Deveria transformar esta variavel em 'singleton'
 //TODO: estatica nao eh bom devido a eventuais varias copias do iGraf => daria conflito
 // Deveria ser "Singleton": IgrafMenu has a single reference to PainelBotoes
 private PainelBotoes painelBotoes = null; // to be used to disable buttons (menu items)
 // private HashMap hashMapJMenuItem = new HashMap(); // HashMap to receive all JMenuItem of this Menu
 // public  HashMap getHashMapJMenuItem () { return hashMapJMenuItem; } -> JMenuItem [] vecSecListJMenuItems

 private JMenuItem [] vecSecListJMenuItems = null; // to be defined by 'PainelBotoes.buildOneSecondaryVector(...)' with a reference of 'PainelBotoes.vecSecList(*)'
 public JMenuItem [] getVecSecListJMenuItems () { return vecSecListJMenuItems; } // teste: src/igraf/moduloSuperior/visao/PainelBotoes.java: buildOneSecondaryVector(...)

 private HashMap hashMapDoNotRemove = new HashMap(); // each Menu(*) puts here any of its item that can not be 'invisible' (even its command number is in file)

 // The menu items are constructed in 'igraf/moduloSuperior/visao/PainelBotoes.java'
 // Grafico  | Calculo     | Animacao | Edicoes    | Exercicio | Ajuda     | [ Poligonos | Idioma   ]
 // menuGrf  | menuCalculo | menuAnim | menuEdicao | menuExerc | menuAjuda | [ menuPol   | menuLang ]
 private String menuName = null; // it will be { menuGraf | menuCalculo | menuAnim | menuEdicao | menuExerc | menuAjuda | [ menuPol   | menuLang] }
 public String getName () { return menuName; }
 public void setName (String strName) { menuName = strName; } // to each 'Menu(*)'

 private FontMetrics fm;

 protected Rectangle r;
 protected boolean isSubMenu = false;  //PARTICULAR: the menu item with submenu is the last one (number 9 = number 12 considering all the separators)
 private   boolean hasSubMenu = false; //PARTICULAR: only the MenuEdicao has submenu

 //leo int height = 20, x;
 //leo protected String selected; - para que era usado???
 protected CommunicationEvent communicationEvent;

 // Define text associated with each menu item to the JPopupMenu
 // protected String [] menuItensName; // Array with the names (roots) of all texts to this JPopupMenu
 protected String [] listaOpcoes; // Array with all texts to this JPopupMenu. ATTENTION: the separator "String SEP" is also here!
 protected String [] descricao; // Array with all description to this JPopupMenu. In any position with SEP its value is undefined.

 protected IgrafMenuController imc; // used in 'MenuEdicao'

 public boolean isSubMenu () { //PARTICULAR: only the MenuEdicao has submenu
  return isSubMenu;
  }
 public boolean hasSubMenu () { //PARTICULAR: only the MenuEdicao has submenu
  return hasSubMenu;
  }

 public IgrafMenuController getIGrafMenuController () {
  // Used in: igraf/moduloSuperior/visao/PainelBotoes.java: buildOneSecondaryVector(IgrafMenu menu, Vector vecSecList): addActionListener
  return imc;
  }

 // To all Menu(*) return their options name - used in 'igraf/moduloSuperior/visao/PainelBotoes.java'
 public static String [] getMenuItensName () { return null; }
 public static String [] getMenuItensTooltips () { return null; }
 public String [] getMenuItensNames () { return null; } // return menuItensName; }    // root name of each item before 'ResourceReader.msg(...)'
 public String [] getMenuItensText () { return listaOpcoes; }       // item name after 'ResourceReader.msg(...)'
 public String [] getMenuItensDescription () { return descricao; }

 // Description to tooltips: in 'PainelBotoes' and 'igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java'
 public static String [] getMenuItemsTooltips () { return null; }

 // at igraf.moduloCentral.visao.menu.IgrafMenu.<init>(IgrafMenu.java:133)
 // at igraf.moduloCentral.visao.menu.IgrafMenu.<init>(IgrafMenu.java:123)
 // at igraf.moduloCentral.visao.menu.MenuEdicao.<init>(MenuEdicao.java:91)
 // at igraf.moduloCentral.controle.menu.IgrafMenuEdicaoController.<init>(IgrafMenuEdicaoController.java:43)
 // at igraf.moduloCentral.ModuloCentral.<init>(ModuloCentral.java:57)
 // at igraf.ContentPane.<init>(ContentPane.java:44)
 // at igraf.IGraf.init(IGraf.java:246)
 // at igraf.IGraf.IGraf(IGraf.java:155)
 // at igraf.IGraf.main(IGraf.java:420)
 public IgrafMenu (IgrafMenuController imc, int index, boolean hasSubmenu) {
  this(imc,index);
  this.hasSubMenu = hasSubmenu;
  }
   

 public IgrafMenu (IgrafMenuController imc, int index) {

  //DEVEL
  // The adtion of new Menu implies that it must pass in the next test
  // Create a new controller to the new Menu and add it to the list bellow

  //CAUTION: here the order defined in 'igraf/moduloCentral/ModuloCentral.java' is essential
  // Constructor in: igraf/moduloCentral/ModuloCentral.java
  // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
  // Attention to: 'IgrafMenu.IgrafMenu(IgrafMenuController imc, int index)' uses this order 0=>IgrafMenuGraficoController; ...
  //   0: IgrafMenuGraficoController
  //   1: IgrafMenuCalculoController
  //   2: IgrafMenuAnimacaoController
  //   3: IgrafMenuEdicaoController
  //   4: IgrafMenuExercicioController
  //   5: IgrafMenuPoligonoController
  //   6: IgrafMenuAjudaController
  try { // launches the correct Menu controller
    switch (index) {
     case 0: this.imc = (IgrafMenuGraficoController)imc; break;
     case 1: this.imc = (IgrafMenuCalculoController)imc; break;
     case 2: this.imc = (IgrafMenuAnimacaoController)imc; break;
     case 3: this.imc = (IgrafMenuEdicaoController)imc; break;
     case 4: this.imc = (IgrafMenuExercicioController)imc; break;
     case 5: this.imc = (IgrafMenuPoligonoController)imc; break;
     case 6: this.imc = (IgrafMenuAjudaController)imc; break;
     }
  } catch (Exception e) { e.printStackTrace(); }

  //imc.setControlledObject(this);

  // why?
  communicationEvent = new IgrafDescriptionEvent(this, "");

  setBackground(Color.white);
  setOpaque(true);
  } // public IgrafMenu(IgrafMenuController imc)

 private void defaultMessage () {
  //communicationEvent.setDescription(null);
  imc.enviarEvento(communicationEvent);
  }


 // hashMapJMenuItem  public  HashMap getHashMapJMenuItem () { return hashMapJMenuItem; }
 // Set in: igraf/moduloSuperior/visao/PainelBotoes.java: private void buildSecondaryPopupMenus(): this.mn(*).setPainelBotoes(this);
 public void setPainelBotoes (PainelBotoes pBotoes, JMenuItem [] vecSecListJMI) {
  painelBotoes = pBotoes; // to be used to disable buttons (menu items)
  vecSecListJMenuItems = vecSecListJMI;

  if (vecSecListJMI==null || vecSecListJMI[0]==null)
   System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: setPainelBotoes(...): ERRO em "+menuName+": #vecSecListJMenuItems="+vecSecListJMenuItems.length+" (0="+this.vecSecListJMenuItems[0]+") xxxxxxxxxxxxxxxxxxxxxxxxxxx \n\n");

  completeAfterButtonsPanel(); // eventually defines menu items as not enabled

  }


 // Called by: igraf/moduloSuperior/visao/PainelBotoes.java
 public void completeAfterButtonsPanel () {
  // to be implemented in each Menu(*) with commands like 'setEnableAllMenuItem(false);' and ''
  }


 // Secondary menu item with timer
 //TODO: eliminar, pois passei para 'PainelBotoes' e nao estou usando os 'maVelAmplia,maVelDiminui'
 // It was moved to: igraf/moduloSuperior/visao/PainelBotoes.java
 private static boolean usaTimer (String item) {
  if (item == null)
   return false;

  String [] lista = {
     ResourceReader.msg("maVelAmplia"), // MenuAnimacao
     ResourceReader.msg("maVelDiminui"), // MenuAnimacao
     ResourceReader.msg("madZoomAmpliar"), // MenuEdicao
     ResourceReader.msg("madZoomDiminuir") // MenuEdicao
     };

  for (int i = 0; i < lista.length; i++) {
   if (lista[i].equals(item))
    return true;
   }

  return false;
  }

 /**
  * Define para um menu quais s�o suas op��es bem como a lista de descri��es sobre
  * a a��o gerada pelo clique sobre um item do menu. 
  * @param vecName
  * @param d
  */
 protected void setListaItens (String [] vecName, String [] vecDescription) {

  listaOpcoes = vecName;
  descricao = vecDescription;

  //leo new version: eliminar codigo abaixo de versao anterior
  //  for (int i=0; i<vecName.length; i++) {
  //   IgrafMenuItem e = new IgrafMenuItem(vecName[i], vecDescription[i], usaTimer(vecName[i])); // IgrafMenuItem extends JLabel implements MouseListener
  //   add(e); }
  }


 /**
  * M�todo utilizado pelas subclasses de IgrafMenu para definirem suas
  * pr�prias listas de descri��es de fun��es.  Estas listas cont�m strings que
  * explicam o que causa um clique sobre um item espec�fico do menu.  Estas
  * descri��es s�o exibidas na barra inferior quando o usu�rio posiciona o mouse
  * sobre um item de menu.
  * @param descricao
  */
 protected void setDescricao (String[] descricao) {
  this.descricao = descricao;
  }

 // If JMenuItem has one submenu: it is under JMenuItem index 'k'
 protected void setSubMenu (SubMenuIdioma smi, int k) {
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "setSubMenu: TODO: k=" + k + ": " + this.menuName);
  //NV Component lista [] = getComponents(); //leo RR new version do not use 'IgrafMenuItem'
  //NV   IgrafMenuItem imi;
  //NV   for (int i=0; i<lista.length; i++) {
  //NV    imi = (IgrafMenuItem)lista[i];
  //NV    String s = imi.getText();
  //NV    if (s != null && s.equals(smi.toString()))
  //NV     imi.setSubMenu(smi);
  //NV    } //leo RR
  } 

 public boolean [] getEnabledMenuItem () {
  int sizeOf = vecSecListJMenuItems.length;
  boolean [] vetEnabled = new boolean[sizeOf];
  for (int ii_0=0; ii_0<sizeOf; ii_0++) {
   JMenuItem jmenuItem = vecSecListJMenuItems[ii_0];
   if (jmenuItem==null)
     vetEnabled[ii_0] = false;
   if (jmenuItem.isEnabled())
     vetEnabled[ii_0] = true;
   else
     vetEnabled[ii_0] = false;
   } // for (int ii_0=0; ii_0<sizeOf; ii_0++)
  return vetEnabled;
  }



 //TTofI static int cont=0;
 // Set visibility property of JMenuItem
 // @param String item, boolean enable: the 'String item' must has it value after aplication of 'ResourceReader.msg(.)'
 public void setEnabledMenuItem (String item, boolean enable) {
  //TTofI if (this instanceof MenuExercicio) {
  //TTofI System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "setEnabledMenuItem("+item+","+enable+"): " + cont);
  //TTofI //System.out.println("\nsrc/igraf/moduloCentral/visao/menu/" + this.toString() + ".java: hasSubMenu=" + hasSubMenu);
  //TTofI //if (!enable && item.equals(ResourceReader.msg("mexExercMenuConfig")))
  //TTofI if (cont==7||cont==79) try{ String str=""; System.err.println(str.charAt(3)); }catch(Exception e) {e.printStackTrace();}
  //TTofI cont++;}

  // 'JMenuItem [] vecSecListJMenuItems' is set in: igraf/moduloSuperior/visao/PainelBotoes.java: private void buildSecondaryPopupMenus(): this.mn(*).setPainelBotoes(this);
  if (vecSecListJMenuItems==null) {
   System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in setEnabledMenuItem of " + this.getClass() + " with item '" + item + "': vecSecListJMenuItems==null");
   return;
   }

  // Found the JMenuItem with text 'item'
  for (int ii_0=0; ii_0<vecSecListJMenuItems.length; ii_0++) {
   JMenuItem jmenuItem = vecSecListJMenuItems[ii_0];
   if (jmenuItem==null) {
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: setEnabledMenuItem of " + this.getClass() + " with item '" + item + "': jmenuItem==null! #vecSecListJMenuItems="+vecSecListJMenuItems.length);
     continue;
     }
   String strText = jmenuItem.getText();
   if (strText!=null && strText.equals(item)) {
    jmenuItem.setEnabled(enable);
    return; // It was found! Break the loop
    }
   }
  }


 public void setEnableAllMenuItem (boolean enable) {
  //T System.out.println("\nigraf/moduloCentral/visao/menu/IgrafMenu.java: setEnableAllMenuItem:  "+menuName+": #vet="+vecSecListJMenuItems.length+": all to "+enable); // selected="+selected+",
  if (vecSecListJMenuItems==null) {
    System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: setEnableAllMenuItem: in " + this.getClass() + " with vecSecListJMenuItems==null");
    return;
    }
  JMenuItem jmenuItem = null;
  for (int ii_0=0; ii_0<vecSecListJMenuItems.length; ii_0++) try {
   jmenuItem = vecSecListJMenuItems[ii_0];
   if (!jmenuItem.equals(SEP))
     jmenuItem.setEnabled(enable);
   // acho que NAO precisa de: this.painelBotoes.setEnabledMenuItem(this, menuItem, enable);
   } catch (Exception except) {
    System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: setEnableAllMenuItem: in " + this.getClass() + " with jmenuItem=" + jmenuItem + " #vecSecListJMenuItems="+vecSecListJMenuItems.length);
    System.err.println("Error type: " + except.toString());
    }
  //leo
  //  Component listComponents [] = getComponents();
  //  for (int i = 0; i < getComponents().length; i++) {   ((IgrafMenuItem)getComponents()[i]).setEnabled(enable);   }
  //  setEnabledMenuItem(ResourceReader.msg("msgMenuGrfNovaAba"), true);
  //  setEnabledMenuItem(ResourceReader.msg("msgMenuGrfRemoverAba"), true);
  }

 
 /**
  * Define a posi��o na horizontal em que o menu ser� exibido.
  * Note que posi��o vertical � a mesma para todos e est� definida nos atributos do
  * objeto Rectangle r que � inicializado no construtor de cada subclasse de IgrafMenu.
  * @param x
  */
 public void setPositionX (int x) {
  if (r.x != x) {
   r.x = x;
   setBounds(r);
   }
  }
 
 /**
  * M�todo que notifica ao sistema a ocorr�ncia de um evento sobre algum menu do iGraf.
  * Recebe o inteiro eventType, que define o tipo de evento, e uma string cmd que � o r�tulo
  * de um item do menu e identifica o comando que acionou o mecanismo de eventos.<br>
  * Chamadas a este m�todo ocorrem quando o mouse se movimenta ou � clicado sobre um menu e
  * os tipos de eventos podem ser: <br>
  * <b>IgrafMenuItem.MOUSE_ENTERED</b> para exibir orienta��es ao usu�rio no painel inferior<br>
  * <b>IgrafMenuItem.MOUSE_CLICKED</b> para enviar o comando indicado no r�tulo ao sistema
  * 
  * @see igraf/moduloSuperior/visao/PainelBotoes.java: mnEdit.disparaEvento(igraf.moduloCentral.visao.menu.IgrafMenuItem.MOUSE_CLICKED, textItem);
  * 
  * @param int eventType
  * @param String cmd
  */
 public void disparaEvento (int eventType, String cmd) {
  //T System.out.println("\n\nIgrafMenu.java: disparaEvento: " + eventType + ", " + cmd + ", ");
  // int k = 0;
  if (eventType == IgrafMenuItem.MOUSE_ENTERED) {
   communicationEvent = new IgrafDescriptionEvent(this, cmd);
   imc.enviarEvento(communicationEvent);
   }
  if (eventType == IgrafMenuItem.MOUSE_CLICKED) {
   imc.enviarEventoAcao(cmd); // igraf.moduloCentral.controle.menu.IgrafMenuController imc
   }
  }

 public void disparaEventoSubMenu (IgrafSubMenuEvent isme) {
  imc.enviarEvento(isme);
  }


 // Each menu constructor, in its 'completeAfterButtonsPanel()' calls this method => do NOT set this item is 'invisible'
 // For now: only 'mexExercMenuConfig' of 'MenuExercicio' calls it whenever it is 'applicative'
 protected void setDoNotRemove (String menuName, String strNameItem) {
  int sizeOf = hashMapDoNotRemove.size();
  hashMapDoNotRemove.put(menuName+sizeOf, strNameItem);
  if (IGraf.IS_DEBUG) System.out.println("IgrafMenu.java: setDoNotRemove("+menuName+","+strNameItem+"): NAO pode ser removido!");
  }


 // From: igraf/moduloCentral/controle/menu/IgrafMenuAjudaController.java: tratarEventoRecebido(CommunicationEvent communicationEvent) -> removeDisabledMenuItens(CommunicationEvent ce)
 //        igraf/moduloCentral/visao/menu/MenuExercicio.java: to remove option "Configure menu" when is applet and not authoring
 // @param
 // @return index of removed item; -1 if it is inexistent; -2 if the item could NOT be removed
 public int removeMenuItem (String item) {
  JMenuItem [] lista = vecSecListJMenuItems;
  JMenuItem jmenuItem;
  String locateMessage;
  //T System.out.println("IgrafMenu.java: removeMenuItem("+item+")?");

  if (hashMapDoNotRemove.containsValue(item)) { // this item could NOT be removed!
   //T System.out.println("IgrafMenu.java: removeMenuItem("+item+"): NAO pode ser removido!");
   return -2;
   }
  if (lista==null)
   return -1; // not removed

  locateMessage = ResourceReader.msg(item);
  for (int ii_0 = 0; ii_0<lista.length; ii_0++) {
   jmenuItem = vecSecListJMenuItems[ii_0]; //leo
   String str = jmenuItem.getText();
   if (str != null && str.equals(locateMessage)) {
    //T System.out.println("IgrafMenu.java: removeMenuItem("+item+"): encontrado em ii_0="+ii_0);
    lista[ii_0].setVisible(false);
    return ii_0;
    }
   //T else System.out.println(" "+ii_0+": "+str);
   } // for (int ii_0 = 0; ii_0<lista.length; ii_0++)
  return -1;
  }


 // 04/2013: new version using JPopupMenu
 // Change all text items from IgrafMenu after language switch
 public void updateLabels (String [] labels, String [] help) {
  //T System.out.println("src/igraf/moduloCentral/visao/menu/IgrafMenu.java: updateLabels(...): " + labels[0]);
  listaOpcoes = labels;
  descricao = help;
  }

 }
