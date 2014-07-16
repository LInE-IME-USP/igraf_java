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
 * @description Opem an extension of JFrame to edit menu options (deselect menu items that will not appear).
 * To save a GRF file with personalized menus. Attention: the option "Configure menu" must be "always visible" when application;
 * but never visible when "applet" (except if it is authoring).
 * 
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: [03/2013] precisaria estar ligado agora com esta classe!!!
 * @see igraf/moduloExercicio/visao/menuSelector/JanelaSeletorMenuController.java: instantiate 'MenuSelectorFrame'
 * @see igraf/moduloExercicio/ModuloExercicio.java: instantiate 'JanelaSeletorMenuController' with ' JanelaSeletorMenuController jsm=new JanelaSeletorMenuController($
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio.visao.menuSelector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuAjuda;
import igraf.moduloCentral.visao.menu.MenuAnimacao;
import igraf.moduloCentral.visao.menu.MenuCalculo;
import igraf.moduloCentral.visao.menu.MenuEdicao;
import igraf.moduloCentral.visao.menu.MenuExercicio;
import igraf.moduloCentral.visao.menu.MenuGrafico;
import igraf.moduloCentral.visao.menu.MenuPoligono;

import difusor.controle.CommunicationController;


public class MenuSelectorFrame extends JFrame implements ActionListener {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java";
 private static final boolean DEBUG = false; // if setted => when iGraf is loaded (eventually after file content read) the menus configuration is presented (System.out.println)
 //D System.err.println(IGraf.debugErrorMsg(IGCLASSPATH)+"Erro: " ...);
 //D IGraf.launchStackTrace()
 //D System.out.println("MenuSelectorFrame.java: iniciaMenuExercicio() **************** mexExercHistorico = " + ResourceReader.msg("mexExercHistorico"));
 //D // //Dtry{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
 //D static int cont=0;

 public static final Color
   corBackground = EsquemaVisual.corBackground, // new Color( 0, 25, 50)
   corForeground = EsquemaVisual.corForeground,
   corTitle      = EsquemaVisual.corTitle;     // color in 'MenuItemPanel.java' to each title of block of menus

 private JPanel buttonPanel;
 private JButton cancelButton;
 private JPanel centerPanel;
 private JButton confirmButton;
 private JPanel jPanel1;
 private JLabel labelTopInformation; // label with an explanation of this configuration panel: item not clicked => it will not appear in menu options

 private CommunicationController cc;

 private MenuItemPanel menuItemPanel1;
 private MenuItemPanel menuItemPanel2;
 private MenuItemPanel menuItemPanel3;
 private MenuItemPanel menuItemPanel4; //PP
 private MenuItemPanel menuItemPanel5;
 private MenuItemPanel menuItemPanel6;
 private MenuItemPanel menuItemPanel7;

 private StringBuffer stringInvisiblesButtons = new StringBuffer(); // used in 'getDisableList()' after confirm menu configuration changes
 private boolean hasMenuItemChange = false;

 //--------------------------------------------------
 // igraf/moduloCentral/visao/menu/[MenuGrafico | MenuCalculo | MenuAnimacao | MenuEdicao | MenuExercicio | MenuAjuda].java
 // igraf/moduloSuperior/visao/PainelBotoes.java: hashMapPopupMenus.put(msgName, vecSecList); // HashMap of 'JMenuItem []' with the descriptions of its items 

 // References from: igraf/ContenPane.java: relationsModulesDinamics(IGraf igraf, boolean ehApplet)
 private HashMap hashMapMenus = new HashMap(); // HashMap with <"mmenuGrf", MenuGrafico>, ...
 private HashMap hashMapPopupMenus = new HashMap(); // HashMap with <"mmenuGrf", Vector[JMenuItem []]>, ...
 private HashMap hashMapOfConfigItems = new HashMap(); // to register <"menuGrf", DisabableMenuItem []>, <"menuCalculo", DisabableMenuItem []>, ...
 private static final String [] vectorOfMenuNames = { "menuGrf", "menuCalculo", "menuAnim", "menuEdicao", "menuExerc", "menuAjuda" }; //PP "menuPol",
 //
 public void setHashMapMenus (HashMap hashMapMenus) { // came from: src/igraf/ContentPane.java: public void criaRelacionamentos (boolean ehApplet)
  this.hashMapMenus = hashMapMenus;
  }
 //
 public void setHashMapPopupMenus (HashMap hashMapPopupMenus) {
  this.hashMapPopupMenus = hashMapPopupMenus; 
  }
 //
 public void setMenuItemsVisible (HashMap hashMapPopupMenus) { // From: igraf/ContentPane.java
  this.hashMapPopupMenus = hashMapPopupMenus; // "menuGrf", "menuCalculo", "menuAnim", "menuEdicao", "menuExerc", ["menuPol", ] "menuAjuda"
  setMenuItemsVisible("menuGrf"); setMenuItemsVisible("menuCalculo"); setMenuItemsVisible("menuAnim");
  setMenuItemsVisible("menuEdicao"); setMenuItemsVisible("menuExerc"); setMenuItemsVisible("menuAjuda");
  }
 public void setMenuItemsVisible (String strName) { // get visibility in 'igraf.moduloCentral.visao.menu.Menu(*)'
  //TODO: em 'DisabableMenuItem' passar o IgrafMenu correspondente
  JMenuItem [] vecJMenuItem = (JMenuItem []) this.hashMapPopupMenus.get(strName);
  DisabableMenuItem [] vecDisMenuItem = (DisabableMenuItem []) this.hashMapOfConfigItems.get(strName);
  JMenuItem jmenuItem;
  DisabableMenuItem dMenuItem;

  //ATTENTION: do not user 'vecJMenuItem.length' since 'vecDisMenuItem' (of 'MenuExercicio') does not have 'SEP,mexExercMenuConfig' - vecJMenuItem.length
  int sizeOf = vecDisMenuItem.length;

  boolean isVisible = true;
  if (DEBUG) System.out.println("MenuSelectorFrame.java: " + strName + ": #=" + sizeOf);
  int error=0;
  for (int ii_0=0; ii_0<sizeOf; ii_0++) {
     if (vecJMenuItem!=null) {
       dMenuItem = vecDisMenuItem[ii_0];
       jmenuItem = (JMenuItem) vecJMenuItem[ii_0]; // vecJMenuItem.elementAt(ii_0);
       isVisible = jmenuItem.isVisible(); // 

       dMenuItem.setItemChecked(isVisible); // set condition to item check

     if (DEBUG) System.out.println(" - " + ii_0 + ": " + isVisible + ": " + jmenuItem.getText());
     } else error++;
     }

  if (error>0)
    System.out.println(IGraf.debugErrorMsg(IGCLASSPATH)+"Error: in config menus window: the menu item vector is missing..." + error +": vecJMenuItem="+vecJMenuItem);

  } // public void setMenuItemsVisible(String strName)



 // igraf/moduloExercicio/ModuloExercicio.java: JanelaSeletorMenuController jsm = new JanelaSeletorMenuController(this, true);
 // igraf/moduloExercicio/visao/menuSelector/JanelaSeletorMenuController.java:  private MenuSelectorFrame msf;: msf = new MenuSelectorFrame(this);
 // igraf/moduloCentral/visao/menu/(MenuGrafico, MenuCalculo, MenuAnimacao, MenuEdicao, MenuExercicio, [MenuPoligono, ] MenuAjuda).java

 // Build a block panel: a panel associate with one iGraf primary button.
 // @param String strName: must be "menuGrf", "menuCalculo", "menuAnim", "menuEdicao", "menuExerc", ["menuPol", ] "menuAjuda"
 private void buildItemPanel (String strName, MenuItemPanel menuItemPanel, String [] vetNames, String [] vetTooltips, int startComNumber) {
  int sizeOf = vetNames.length, jj_0 = 0, numberCommand = startComNumber;
  //T System.out.println("MenuSelectorFrame.java: #" + strName + "=" + sizeOf);
  String strAuxName, strAuxTooltip, strSep = IgrafMenu.SEP;
  DisabableMenuItem [] vecDisMenuItem = new DisabableMenuItem[sizeOf];
  DisabableMenuItem disMenuItem;
  for (int ii_0=0; ii_0<sizeOf; ii_0++) {
    strAuxName = vetNames[ii_0];
    if (strAuxName!=null && !strAuxName.equals(strSep)) {
     strAuxTooltip = vetTooltips[ii_0];
     disMenuItem = new DisabableMenuItem(ResourceReader.msg(strAuxName), ResourceReader.msg(strAuxTooltip), numberCommand++);

     disMenuItem.setBackground(corBackground);
     disMenuItem.setForeground(corForeground);

     //T System.out.println(" - " + ii_0 + ": " + strAuxName);
     vecDisMenuItem[jj_0++] = disMenuItem;
     menuItemPanel.add(disMenuItem);
     }
    }

  // Register references to all menu configuration items. It allow later (after file content read) 'ContenPane.relationsModulesDinamics(IGraf,boolean)'
  // calls 'MenuSelectorFrame.setMenuItemsVisible(HashMap)' to update these configuration in accordance with visibles menu.
  // References from: igraf/ContenPane.java: relationsModulesDinamics(IGraf igraf, boolean ehApplet)
  DisabableMenuItem [] vecDisMenuItemClear = new DisabableMenuItem[jj_0];
  for (int ii_0=0; ii_0<jj_0; ii_0++) { // get only those that is not a separator
    vecDisMenuItemClear[ii_0] = vecDisMenuItem[ii_0];
    }
  hashMapOfConfigItems.put(strName, vecDisMenuItemClear);
  } // private void buildItemPanel(String strName, MenuItemPanel menuItemPanel, String [] vetNames, String [] vetTooltips, int startComNumber)


 // String root names to menu items: menuGrf menuCalculo menuAnim [menuPol] menuEdicao menuExerc menuAjuda
 private void buildAllMenus () { // MenuGrafico
  buildItemPanel("menuGrf", menuItemPanel1, MenuGrafico.getMenuItensName(), MenuGrafico.getMenuItemsTooltips(), MenuGrafico.commandNumberStar); // MenuGrafico
  buildItemPanel("menuCalculo", menuItemPanel2, MenuCalculo.getMenuItensName(), MenuCalculo.getMenuItemsTooltips(), MenuCalculo.commandNumberStar); // MenuCalculo
  buildItemPanel("menuAnim", menuItemPanel3, MenuAnimacao.getMenuItensName(), MenuAnimacao.getMenuItemsTooltips(), MenuAnimacao.commandNumberStar); // MenuAnimacao
  // buildItemPanel("menuPol", menuItemPanel4, MenuPoligono.getMenuItensName(), MenuPoligono.getMenuItemsTooltips(), MenuPoligono.commandNumberStar); // MenuPoligono
  buildItemPanel("menuEdicao", menuItemPanel5, MenuEdicao.getMenuItensName(), MenuEdicao.getMenuItemsTooltips(), MenuEdicao.commandNumberStar); // MenuEdicao
  buildItemPanel("menuExerc", menuItemPanel6, MenuExercicio.getMenuItensNameSM(), MenuExercicio.getMenuItemsTooltips(), MenuExercicio.commandNumberStar); // MenuExercicio
  buildItemPanel("menuAjuda", menuItemPanel7, MenuAjuda.getMenuItensName(), MenuAjuda.getMenuItemsTooltips(), MenuAjuda.commandNumberStar); // MenuAjuda
  }
 //--------------------------------------------------


 // Opem JFrame to edit menu options
 public MenuSelectorFrame (CommunicationController cc) {
  this();
  this.cc = cc;
  setVisible(false);
  }

 public MenuSelectorFrame () {
  initComponents();
  buildAllMenus(); // build all menus: MenuGrafico; MenuCalculo; MenuAnimacao; [MenuPoligono;] MenuEdicao; MenuExercicio; MenuAjuda

  this.setBackground(corBackground); // = EsquemaVisual.corBackground; // new Color( 0, 25, 50), // text color in highlight: igraf/moduloCentral/visao/desenho/DesenhoTexto.java
  this.buttonPanel.setBackground(corBackground);
  this.centerPanel.setBackground(corBackground);
  this.cancelButton.setBackground(corBackground);
  this.confirmButton.setBackground(corBackground);
  this.labelTopInformation.setBackground(corBackground);
  this.jPanel1.setBackground(corBackground);

  this.setForeground(corForeground);
  this.buttonPanel.setForeground(corForeground);
  this.centerPanel.setForeground(corForeground);
  this.cancelButton.setForeground(corForeground);
  this.confirmButton.setForeground(corForeground);
  this.labelTopInformation.setForeground(corForeground);
  this.jPanel1.setForeground(corForeground);

  confirmButton.addActionListener(this);
  cancelButton.addActionListener(new ActionListener() {
   public void actionPerformed (ActionEvent e) {
    goAway();
    }
   });
  }


 private void initComponents () {

  this.setTitle("iGraf - " + ResourceReader.msg("menuCfgTitle")); // Configure items of menu
  this.getContentPane().setBackground(corBackground);

  // A quick explanation of this configuration menus window
  labelTopInformation = new JLabel();
  labelTopInformation.setText(ResourceReader.msg("menuCfgTopMessage1")); // If you want to avoid an specific menu item, let it unmarked in the list bellow
  labelTopInformation.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 1));
  this.getContentPane().add(labelTopInformation, BorderLayout.PAGE_START);

  // Build any block ckeck items: one to each popup menu of iGraf
  centerPanel = new JPanel();
  centerPanel.setLayout(new GridLayout(2, 4, 2, 2));
  menuItemPanel1 = new MenuItemPanel(ResourceReader.msg("menuGrf"));
  menuItemPanel2 = new MenuItemPanel(ResourceReader.msg("menuCalculo"));
  menuItemPanel3 = new MenuItemPanel(ResourceReader.msg("menuAnim"));
  //PP menuItemPanel4 = new MenuItemPanel(ResourceReader.msg("menuPol"));
  menuItemPanel5 = new MenuItemPanel(ResourceReader.msg("menuEdicao"));
  menuItemPanel6 = new MenuItemPanel(ResourceReader.msg("menuExerc"));
  menuItemPanel7 = new MenuItemPanel(ResourceReader.msg("menuAjuda"));
  centerPanel.add(menuItemPanel1);
  centerPanel.add(menuItemPanel2);
  centerPanel.add(menuItemPanel3);
  //PP centerPanel.add(menuItemPanel4);
  centerPanel.add(menuItemPanel5);
  centerPanel.add(menuItemPanel6);
  centerPanel.add(menuItemPanel7);
  this.getContentPane().add(centerPanel, BorderLayout.CENTER);

  // Panel with options to this configuration menus window: cancel or confirm
  jPanel1 = new JPanel();
  buttonPanel = new JPanel();
  cancelButton = new JButton();
  confirmButton = new JButton();
  buttonPanel.setLayout(new BorderLayout());
  cancelButton.setText(ResourceReader.msg("msgCancelar")); // Cancel
  confirmButton.setText(ResourceReader.msg("menuCfgConfim")); // Confirm
  jPanel1.add(cancelButton);
  jPanel1.add(confirmButton);
  buttonPanel.add(jPanel1, BorderLayout.EAST);
  this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

  setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  pack();

  }


 public void updateLabes () { // menuGrf menuCalculo menuAnim [menuPol] menuEdicao menuExerc menuAjuda
  this.setTitle("iGraf - " + ResourceReader.msg("menuCfgTitle")); // Configure items of menu
  menuItemPanel1.setText(ResourceReader.msg("menuGrf"));
  menuItemPanel2.setText(ResourceReader.msg("menuCalculo"));
  menuItemPanel3.setText(ResourceReader.msg("menuAnim"));
  //PP menuItemPanel4.setText(ResourceReader.msg("menuPol"));
  menuItemPanel5.setText(ResourceReader.msg("menuEdicao"));
  menuItemPanel6.setText(ResourceReader.msg("menuExerc"));
  menuItemPanel7.setText(ResourceReader.msg("menuAjuda"));
  labelTopInformation.setText(ResourceReader.msg("menuCfgTopMessage1")); // If you want to avoid an specific menu item, let it unmarked in the list bellow
  cancelButton.setText(ResourceReader.msg("msgCancelar")); // Cancel
  confirmButton.setText(ResourceReader.msg("menuCfgConfim")); // Confirm

  }



 private void goAway () {
  setVisible(false);
  dispose();
  }


 /* 03/06/2013
  * DEBUG: usar modo mais eficiente - sem lancar evento para TODOS os modulos olharem...
  * DEBUG: como estava NAO conseguiu "voltar" um item de menu que havia sido escondido...
 private void generateDisableList () {
  Component [] vecComponentList = centerPanel.getComponents(), vecComponentMenus;
  DisabableMenuItem disabableMenuItem;
  int sizeOfCList = vecComponentList.length, sizeOfCMenus;
  for (int i=0; i<sizeOfCList; i++) {
   vecComponentMenus = ((MenuItemPanel)vecComponentList[i]).getComponents();
   sizeOfCMenus = vecComponentMenus.length;
   for (int j=0; j<sizeOfCMenus; j++) {
    disabableMenuItem = (DisabableMenuItem)vecComponentMenus[j];
    if (!disabableMenuItem.isSelected()) { // append menu item code to be set "invisible"
     stringInvisiblesButtons.append(String.valueOf(disabableMenuItem.getMenuCode()) + ", ");
     hasMenuItemChange = true; // at least one invisible
     }
    }
   } // for (int i=0; i<sizeOfCList; i++)
  } */


 // After "Confirm": update all menu items (if necessary)
 public void processChangesToMenus () { // get visibility in 'igraf.moduloCentral.visao.menu.Menu(*)'
  int sizeOfMenus = vectorOfMenuNames.length;
  String strMenuName;
  JMenuItem [] vecJMenuItem; // menu items (igraf.moduloCentral.visao.menu.Menu(*)')
  DisabableMenuItem [] vecDisMenuItem; // menu items configuration (MenuSelectorFrame)
  boolean isVisibleMenu, isSelectedConfig;
  stringInvisiblesButtons = new StringBuffer("");

  //T System.out.println("MenuSelectorFrame.java: processChangesToMenus(): #vectorOfMenuNames=" + sizeOfMenus);

  for (int ii_0=0; ii_0<sizeOfMenus; ii_0++) { // "menuGrf",  "menuCalculo",  "menuAnim", "menuEdicao",  "menuExerc", ["menuPol",] "menuAjuda"
   strMenuName = vectorOfMenuNames[ii_0]; // get the menu name
   vecJMenuItem = (JMenuItem []) this.hashMapPopupMenus.get(strMenuName);
   vecDisMenuItem = (DisabableMenuItem []) this.hashMapOfConfigItems.get(strMenuName);

   //ATTENTION: do not user 'vecJMenuItem.length' since 'vecDisMenuItem' (of 'MenuExercicio') does not have 'SEP,mexExercMenuConfig' - vecJMenuItem.length
   int sizeOf = vecDisMenuItem.length;

   for (int jj_0=0; jj_0<sizeOf; jj_0++) {
    isVisibleMenu = vecJMenuItem[jj_0].isVisible();
    isSelectedConfig = vecDisMenuItem[jj_0].isSelected();
    if (isVisibleMenu!=isSelectedConfig) {
      hasMenuItemChange = true; // at least one invisible
      vecJMenuItem[jj_0].setVisible(isSelectedConfig);
      //T System.out.println(" - " + strMenuName + ": (" + ii_0 + "," + jj_0 + "): " + isVisibleMenu + " -> " + isSelectedConfig);
      }
    if (!isSelectedConfig) {
      // append menu item code to be set "invisible"
      stringInvisiblesButtons.append(String.valueOf(vecDisMenuItem[jj_0].getMenuCode()) + ", ");
      }
    } // for (int jj_0=0; jj_0<sizeOf; jj_0++)
   } // for (int ii_0=0; ii_0<sizeOfMenus; ii_0++)

  //TODO: eliminar uso de 'cc.enviarEvento(new DisableMenuEvent(this, getDisableList()));' em 'actionPerformed(ActionEvent)'
  //
  // Para jogar fora o difusor aqui (cc.enviarEvento(new DisableMenuEvent(this, getDisableList()));)
  // Falta fazer: src/igraf/moduloCentral/controle/AreaDesenhoController.java: registraEvento(CommunicationEvent ie):
  // areaDesenhoController.registraEvento(new DisableMenuEvent(this, getDisableList()));
  //
  // src/igraf/moduloCentral/visao/PainelCentral.java: public PainelCentral(PainelCentralController pcc, ModuloInferior moduloInferior):
  //     tabbedViewer=new TabbedViewer(pcc.getMc(), moduloInferior)
  // src/igraf/moduloCentral/visao/TabbedViewer.java: public void criaAba(boolean isChange): areaDesenhoControllerAux = new AreaDesenhoController(mc, true, numAba);
  //
  //
  // src/igraf/moduloExercicio/ModuloExercicio.java: JanelaSeletorMenuController jsm = new JanelaSeletorMenuController(this, true);
  // src/igraf/moduloExercicio/visao/menuSelector/JanelaSeletorMenuController.java
  //     public JanelaSeletorMenuController (CommunicationFacade module, boolean getEvents): msf = new MenuSelectorFrame(this);

  //T System.out.println("MenuSelectorFrame.java: processChangesToMenus(): stringInvisiblesButtons=" + stringInvisiblesButtons);

  } // public void processChangesToMenus()


 // Button "Confirm" was clicked
 public void actionPerformed (ActionEvent e) {
  processChangesToMenus();
  //R Bad solution: ineficient and do not return any menu item that was invisible. See bellow a direct access to menus changes
  //R generateDisableList();
  //TODO: remove - start
  if (hasMenuItemChange) {
   // All 'igraf/moduloExercicio/visao/menuSelector/DisableMenuEvent.java' events are treated in: 
   // * igraf/moduloCentral/controle/menu/IgrafMenuAnimacaoController.java
   // * igraf/moduloCentral/controle/menu/IgrafMenuAjudaController.java
   // * igraf/moduloCentral/controle/menu/IgrafMenuCalculoController.java
   // * igraf/moduloCentral/controle/menu/IgrafMenuEdicaoController
   // * igraf/moduloCentral/controle/menu/IgrafMenuExercicioController.java
   // * igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java
   // * igraf/moduloCentral/controle/menu/IgrafMenuPoligonoController.java
   // * igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java
   cc.enviarEvento(new DisableMenuEvent(this, getDisableList()));
   }
  //TODO: remove - end
  goAway();
  }


 public String getDisableList () {
  if (stringInvisiblesButtons.toString().length() > 2)
   return stringInvisiblesButtons.toString().substring(0, stringInvisiblesButtons.length()-2);
  return "";
  }



 /**
 * @param args the command line arguments
 */
 public static void main (String args[]) {
  // java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new MenuSelectorFrame().setVisible(true); } });
  new MenuSelectorFrame().setVisible(true);
  }

 }
