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
 * @description It implements the manage of menus.
 * 
 * @see igraf/moduloSuperior/ModuloSuperior.java: initiates 'PainelBotoes' with 'painelBotoes = new PainelBotoes(pbc);'
 * 
 * @see igraf/moduloCentral/ModuloCentral.java: here is created the 'HashMap hashMapIGrafMenus' with all 'igraf.moduloCentral.visao.menu.Menu(*)'
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloSuperior.visao;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import difusor.i18N.LanguageUpdatable;
import igraf.basico.io.ResourceReader;
import igraf.moduloSuperior.controle.PainelBotoesController;

import igraf.IGraf;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.controle.menu.IgrafMenuController;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.ModuloCentral;
import igraf.moduloCentral.visao.menu.MenuAjuda;
import igraf.moduloCentral.visao.menu.MenuExercicio;
import igraf.moduloCentral.visao.menu.MenuAnimacao;
import igraf.moduloCentral.visao.menu.MenuGrafico;
import igraf.moduloCentral.visao.menu.MenuCalculo;
import igraf.moduloCentral.visao.menu.MenuPoligono;
import igraf.moduloCentral.visao.menu.MenuEdicao;
import igraf.moduloCentral.visao.menu.SubMenuIdioma;


public class PainelBotoes extends JPanel implements LanguageUpdatable {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloSuperior/visao/PainelBotoes.java";

 // In 'createPopupMenu(...)' to adde JButtons with 'button.setSize(BUTTONWIDTH,BUTTONHEIGTH); button.setLocation(position * BUTTONWIDTH2, 0);'
 private static final int
   BUTTONWIDTH = 45,    // width to JButton
   BUTTONWIDTH2 = 50,   // to calculate position of JButton (position x BUTTONWIDTH2)
   BUTTONHEIGTH = 23;   // heigth to JButton

 private ModuloCentral moduloCentral = null; // to be used to get 'igraf.moduloCentral.controle.menu.IgrafMenu(*)Controller'

 // Primary buttons
 private JButton
         grafBt      = null,
         calcBt      = null,
         animBt      = null,
         //R poliBt  = null,
         edicaoBt    = null,
         exercicioBt = null,
         ajudaBt     = null;
         // iconLanguage = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconLanguage.gif")

 //------------------------------------------------------------------------------------------------------------
 //leo New model to buttons : 04/2013
 //leo Start
 private static final String PATH_ICON = "moduloCentral/visao/img/";
 private static HashMap hashMapIcons = null; // HashMap to ImageIcon

 // Image icons to each primary button
 private static ImageIcon
   iconGraphics = null,
   iconCalculus = null,
   iconAnimation = null,
   iconEdition = null,
   iconExercise = null,
   iconHelp = null,
   iconPoligons = null,
   iconLanguage = null;

 static {
   String imgName = "";
   String msgErr = "";
   try { // ./igraf/basico/io/TrataImagem.java: public static javax.swing.ImageIcon getImageIcon
     iconGraphics = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconGraphics.gif");
     iconCalculus = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconCalculus.gif");
     iconAnimation = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconAnimation.gif");
     iconEdition = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconEdition.gif");
     iconExercise = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconExercise.gif");
     iconHelp = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconHelp.gif");
     iconPoligons = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconPoligons.gif");
     iconLanguage = igraf.basico.io.TrataImagem.getImageIcon(PATH_ICON + "iconLanguage.gif");
     hashMapIcons = new HashMap();
     hashMapIcons.put("iconGraphics", iconGraphics);
     hashMapIcons.put("iconCalculus", iconCalculus);
     hashMapIcons.put("iconAnimation", iconAnimation);
     hashMapIcons.put("iconEdition", iconEdition);
     hashMapIcons.put("iconExercise", iconExercise);
     hashMapIcons.put("iconHelp", iconHelp);
     hashMapIcons.put("iconPoligons", iconPoligons);
     hashMapIcons.put("iconLanguage", iconLanguage);
   } catch (Exception e) { System.err.println(igraf.IGraf.debugErrorMsg(IGCLASSPATH) + "Error: buttons' panel, in image " + imgName); }
   }

 public static ImageIcon getImageIcon (String nameIcon) {
   return (ImageIcon)hashMapIcons.get(nameIcon);
   }

 // Vector to JPopupMenu: to each primary menu has one with elements JMenuItem
 // Each menu will have a reference to this: in igraf/moduloCentral/visao/menu/IgrafMenu.java
 private JMenuItem [] vecSecListGraf = null;      // __  __
 private JMenuItem [] vecSecListCalculo = null;   // __  __
 private JMenuItem [] vecSecListAnim = null;      // __  __
 private JMenuItem [] vecSecListEdicao = null;    // __  __
 private JMenuItem [] vecSecListExercicio = null; // __  __
 private JMenuItem [] vecSecListAjuda = null;     // __  __
 private JMenuItem [] vetSecListSubmenus = null;  // __  __ submenu to MenuEdicao
 //ATTENTION: it is supposed that there exists only ONE submenu (under MenuEdicao) and in submenu does not have separators (all items are relavants)

 private HashMap hashMapPrimaryButtons = null; // set of primary buttons
 private HashMap hashMapPopupMenus = null; // set of PopUpMenus: each primary button has its secondary list (Vector)

 private JButton primaryPopupVisible = null; // auxiliary to dismiss popup as long as the mouse get out over this primary button

 // menuGraf | menuCalculo | menuAnim | menuEdicao | menuExerc | menuAjuda | [ menuPol   | menuLang ]
 //TODO: for while only 'MenuEdicao' has been used to launch runnable to Zoom in/out
 protected MenuGrafico mnGraf = null; // "menuGrf"
 protected MenuCalculo mnCalc = null; // "menuCalculo"
 protected MenuAnimacao mnAnim = null; // "menuAnim"
 protected MenuEdicao mnEdit = null; // "menuEdicao"
 protected MenuExercicio mnExerc = null; // "menuExerc"
 //R protected MenuPoligono mnPol = null; // "menuPol"
 protected MenuAjuda mnAjud = null; // "menuAjuda"

 // From: igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java
 // HashMap with all menus (MunuGrafico, ManuCalculo, ...). To prepare configuration of menu items
 private HashMap hashMapMenus;
 public  HashMap getHashMapMenus () { return hashMapMenus; }
 public  HashMap getHashMapPopupMenus () { return hashMapPopupMenus; }


 // Get JMenuItem from its name
 // From: igraf/moduloExercicio/visao/JanelaExercicio.java
 public JMenuItem getJMenuItem (String strName, JMenuItem [] vecJMenuItems) {
   for (int ii_0=0; ii_0<vecJMenuItems.length; ii_0++) try {
     if (strName.equals(vecJMenuItems[ii_0].getText()))
       return vecJMenuItems[ii_0];
     } catch (Exception except) { System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in recovery of menu item " + strName + ": " + except); }
   return null;
   }


 // From: igraf/moduloExercicio/visao/JanelaExercicio.java: this.painelBotoes.setEnabledMenuItem("menuExerc", "mexExercEditar", true);
 public void setEnabledMenuItem (String strIgrafMenu, String strMenuItem, boolean enable) {
  //T System.out.println("PainelBotoes.java: " + strIgrafMenu + ": " + strMenuItem + "->'" + ResourceReader.msg(strMenuItem) + "': " + enable);
  JMenuItem [] vecOfMenuItems = (JMenuItem []) hashMapPopupMenus.get(strIgrafMenu);

  JMenuItem menuItem = getJMenuItem(ResourceReader.msg(strMenuItem), vecOfMenuItems);

  //T System.out.println("PainelBotoes.java: founded in " + igrafMenu + ": " + menuItem + ": " + enable);
  setEnabledMenuItem(null, menuItem, enable); // enable this menu item
  }


 // From: igraf/moduloCentral/visao/menu/IgrafMenu.java
 public void setEnabledMenuItem (IgrafMenu igrafMenu, JMenuItem menuItem, boolean enable) {
   // IgrafMenu extends JPanel extends JComponent extends Container: use 'Container.getComponents()'
   // if (igrafMenu==this.mnGraf) System.out.println("PainelBotoes.java: " + igrafMenu + ": " + menuItem + ": " + enable);
   menuItem.setEnabled(enable);
   }


 // igraf.moduloSuperior.controle.PainelBotoesController PainelBotoesController
 public PainelBotoes (PainelBotoesController pbc, ModuloCentral modCentral) {

  // JButton is added in 'private void insertListenersNPopupMenu2button(this, final JButton button, final JPopupMenu popupMenu, final String name)'

  moduloCentral = modCentral; // to be used to get 'igraf.moduloCentral.controle.menu.IgrafMenu(*)Controller'

  buildPrimaryButtons();

  pbc.setControlledObject(this);
  }


 // Create pop-up menu (a secondary column menu, under a primary button)
 private void insertListenersNPopupMenu2button (final PainelBotoes pBotoes, final JButton button, final JPopupMenu popupMenu, final String name, int position) {

   // Listener: primary buttons: "mouse click"
   button.addActionListener(new ActionListener() {
     public void actionPerformed (ActionEvent event) {
       try { // if 'button' and 'popupMenu' are no 'final' here, the error is lauched: 'local variable button is accessed from within inner class'
        Object objEvent = event.getSource();
        //PopupMenu: show it
        popupMenu.show((java.awt.Component)objEvent, 0, button.getHeight()); // JPopupMenu popupMenu.getHeight()

       } catch (Exception e) {
        System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: insertListenersNPopupMenu2button: "+e+"\n - button="+button+"\n - popup="+popupMenu);
        }
       }}); //  void actionPerformed(ActionEvent event)

   // Listener: primary button: "mouse enter/exit"
   button.addMouseListener(new MouseAdapter() { // detect mouse over, left or right off the button: dismiss JPopupMenu
    public void mouseEntered (MouseEvent event) {
      if (popupMenu==null) { // wait for 'buildOneSecondaryPopMenu(...)'
        System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: something went wrong with button " + name + "\n" +
                           "Look at 'igraf.moduloCentral.ModuloCentral': in 'hashMapIGrafMenus' is there a correct 'put(\""+name+"\", _name_.getMenu())'?");
        return;
        }
      if (!popupMenu.isVisible()) {
        Object objEvent = event.getSource();
        //DEBUG popupMenu.setVisible(true); - bad solution: it is posicioned at (0,0)

        //PopupMenu: show it
        popupMenu.show((java.awt.Component)objEvent, 0, button.getHeight() );

        primaryPopupVisible = button;
        }
      } // void mouseEntered(MouseEvent event)
    public void mouseExited (MouseEvent event) {
      int x = event.getX(), y = event.getY(); // mouse position
      int width = button.getBounds().width, height = button.getBounds().height; // button size
      // int widthPUM = popupMenu.getBounds().width, heightPUM = popupMenu.getBounds().height; // popup menu size

      if (y<0 || x<0 || x>width) {
        popupMenu.setVisible(false);
        primaryPopupVisible = button;
        }
      else
      if (x>width) { // leave at right
        popupMenu.setVisible(false);
        primaryPopupVisible = null;
        }
      }
    });

   } // private void insertListenersNPopupMenu2button(final JButton button, final JPopupMenu popupMenu, final String name, int position)


 // Timer to be used in Edicao { madZoomAmpliar, madZoomDiminuir }
 protected Timer timer = null; // to automatically adjust zoom em Edicao
 class MouseListenerRunnableTask extends TimerTask {
   protected MenuEdicao mnEdit;
   protected String textItem = "";
   public MouseListenerRunnableTask (MenuEdicao mnEdit, String textItem) { this.mnEdit = mnEdit; this.textItem = textItem; }
   public void run () { // MenuEdicao : madZoomAmpliar; madZoomDiminuir
     mnEdit.disparaEvento(igraf.moduloCentral.visao.menu.IgrafMenuItem.MOUSE_CLICKED, textItem);
     }
   }

 // Listener to Edit items { madZoomAmpliar, madZoomDiminuir }: runnable
 // See: igraf/moduloCentral/visao/menu/IgrafMenu.java: there it also aplyed to Animacao { maVelAmplia, maVelDiminui }
 // See: igraf/moduloCentral/controle/desenho/DesenhoAnimacaoController.java: control to launches actions associated to { maVelAmplia, maVelDiminui }
 //TODO: implementar o 'runnable' de velocidade e ativar opcoes em 'DesenhoAnimacaoController.trataEvento(CommunicationEvent)'
 private void addMouseListenerRunnable (final MenuEdicao mnEdit, final JMenuItem menuItem) {
   menuItem.addMouseListener(new MouseAdapter() { // detect mouse over, left or right off the menuItem: dismiss JPopupMenu
    public void mouseEntered (MouseEvent event) {
      try {
      if (menuItem.isEnabled()) {
        timer = new Timer();
        timer.schedule(new MouseListenerRunnableTask(mnEdit, menuItem.getText()), 600, 40); // after 600 delay, run it for 40
        }
      } catch (Exception except) { //
        if (igraf.IGraf.IS_DEBUG) {
          System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: something went wrong with listener of menuItem " + menuItem.getText());
          except.printStackTrace();
          }
        }
      } // void mouseEntered(MouseEvent event)
    public void mouseExited (MouseEvent event) {
      if (menuItem.isEnabled()) timer.cancel();
      }
    }); // menuItem.addMouseListener(new MouseAdapter()
  } // private void addMouseListenerRunnable(JMenuItem menuItem)


 // Add action listener to this JMenuItem
 private void addActionListenerJMenuItem (final IgrafMenu menu, JMenuItem itemPMenu) {
   // Listener: secondary buttons

   itemPMenu.addActionListener(new ActionListener() {
    // action listener in each menu item: identified by the text in menu item (consider idiom location)
    public void actionPerformed (ActionEvent event) {
     JMenuItem item = (JMenuItem) event.getSource();
     IgrafMenuController imc = menu.getIGrafMenuController(); // igraf.moduloCentral.controle.menu.IgrafMenuController
     String strCmd = item.getActionCommand(); // name of command = text in the menu item

     // igraf.moduloCentral.controle.menu.IgrafMenuController extends CommunicationController
     // igraf.moduloCentral.controle.menu.IgrafMenu[Ajuda | Animacao | Calculo | Edicao | Exercicio | Poligono | Grafico]Controller implements IgrafMenuController
     // igraf.moduloCentral.visao.menu.IgrafMenu: IgrafMenuController imc
     imc.enviarEventoAcao(strCmd); // in 'igraf/moduloCentral/controle/menu/IgrafMenuAjudaController.java': 'strCmd' is its text (after 'ResourceReader')

   }});
  } // addActionListenerJMenuItem(final IgrafMenu menu, JMenuItem itemPMenu)


 //DEBUG: It allows to trach each JPopupMenu.
 //DEBUG: It must be lauched only one instance of each JPopupMenu. Why? Because of the listeners use 'final' variable
 //DEBUG: in 2 methods. Besides, it is not necessary to create a JPopupMenu for a IGrafMenu twice!
 private static int contPopup = 0;

 //PARTICULAR: if appears new submenu, we must generalize this version!
 //
 // Preserve this Popup to treat submenu "Language"
 private javax.swing.Popup popupMenuEdit = null; // in 'private void', 'showPopup(MouseEvent e)' and 'hidePopup()'
 //
 private void hidePopup () {
   if (popupMenuEdit!=null) popupMenuEdit.hide();
   }
 //
 private void showPopup (MouseEvent e) {
   javax.swing.PopupFactory popupFactory = javax.swing.PopupFactory.getSharedInstance();
   String descriptionLanguage = mnEdit.getMenuItensDescription()[9]; // MenuEdicao mnEdit
   javax.swing.JToolTip toolTip = new javax.swing.JToolTip();
   toolTip.setTipText(descriptionLanguage);
   popupMenuEdit = popupFactory.getPopup(igraf.IGraf.getInstanceIGraf(), toolTip, e.getX(), e.getY()); // getPopup(frame, toolTip, e.getXOnScreen(), e.getYO$
   popupMenuEdit.show();
   }
 //
 private JMenu buildSubMenuIdioma (MenuEdicao menuEdit) {
   // Edit | Language?
   // if it is menu Edit with item "Idioma" => has submenu
   SubMenuIdioma smi = menuEdit.getSubMenuIdioma(); //PARTICULAR: SubMenuIdioma getSubMenuIdioma()
   JMenu itemPMenu = null;
   JMenuItem subMenuItem = null;

   //PARTICULAR: the menu item with submenu is the last one (number 9)
   String strNameAux = menuEdit.getMenuItensText()[MenuEdicao.SUBMENUINDEX]; // MenuEdicao.SUBMENUINDEX = 12

   itemPMenu = new JMenu(strNameAux); // it will hava submenu!
   itemPMenu.setBackground(EsquemaVisual.corBackground);
   smi = this.mnEdit.getSubMenuIdioma(); // SubMenuIdioma getSubMenuIdioma()
   String [] listaOpcoesIdioma = smi.getMenuItensText(); // listaOpcoes

   vetSecListSubmenus = new JMenuItem[listaOpcoesIdioma.length]; //ATTENTION: it is supposed that in submenu does not have separators (all items are relavants)

   //T System.out.println("src/igraf/moduloSuperior/visao/PainelBotoes.java: buildSubMenuIdioma(MenuEdicao)");
   for (int jj_1=0; jj_1<listaOpcoesIdioma.length; jj_1++) {
     String strNameSubitem = listaOpcoesIdioma[jj_1];
     subMenuItem = new JMenuItem(strNameSubitem);
     subMenuItem.setBackground(EsquemaVisual.corBackground);
     ((JMenu)itemPMenu).add(subMenuItem);

     vetSecListSubmenus[jj_1] = subMenuItem; //ATTENTION: it is supposed that in submenu does not have separators (all items are relavants)
     //T System.out.println(" - " + jj_1 + ": " + subMenuItem.getText());

     // Listener: secondary buttons
     addActionListenerJMenuItem(menuEdit, subMenuItem); // Listener: secondary buttons: submenu item

     }

   itemPMenu.addMouseListener(new MouseAdapter() { // addMouseMotionListener
     public void mouseMoved(MouseEvent event) {
       hidePopup(); // if (popupMenu != null) popupMenu.hide();
       showPopup(event); //
       }
     });

   return itemPMenu;
   } // JMenu buildSubMenuIdioma(MenuEdicao menuEdit)


 // Build all secondary key value, to each primary button
 private Object [] buildOneSecondaryPopMenu (IgrafMenu iGrafMenu, boolean hasSubMenu) {
   //T System.out.println("src/igraf/moduloSuperior/visao/PainelBotoes.java: buildOneSecondaryPopMenu(" + iGrafMenu + "...): ");
   Object [] objectsJPopupMenu_and_vecJMenuItem = new Object [2];
   JMenuItem [] vecJMenuItems;
   JPopupMenu popupMenu = new JPopupMenu(""+contPopup++); // Vector vecSecList -> vecJMenuItems
   JMenuItem itemPMenu;
   String strNameAux;
   String [] vetNames = null;
   String [] vetDescriptions = null;
   int sizeOfMenuSecundary = 0;
   if (iGrafMenu==null) { // if it gets here => 'getMenuFromName(HashMap,String)' must be returned 'nulll'
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in construction of a primary menu, it is empty");
     return null;
     }

   popupMenu.setBackground(EsquemaVisual.corBackground);
   popupMenu.setOpaque(true);

   // Get all the root names of each item (before 'ResourceReader.msg(...)') - attention: it does not has submenu (like language of Edition)
   String [] localMenuItensName = iGrafMenu.getMenuItensNames(); // NOVO: devolve as raizes dos nomes por instancia (ja havia um estatico 'Menu*.getMenuItensName()'

   vetNames = iGrafMenu.getMenuItensText(); // IgrafMenu.getMenuItensText() returns 'String [] listaOpcoes'
   vetDescriptions = iGrafMenu.getMenuItensDescription();
   sizeOfMenuSecundary = vetNames.length; // number of JMenuItem in this secondary menu
   HashMap hashMapJMenuItemsAux = new HashMap(); // copy only relevants items
   int countRelevants = 0;
   //TM2 if (iGrafMenu.getId()==0) //if (iGrafMenu instanceof MenuExercicio)
   //TM2 System.out.println("src/igraf/moduloSuperior/visao/PainelBotoes.java: define 'vecSecListJMenuItems' de "+iGrafMenu.getName()+": #JMenuItem="+sizeOfMenuSecundary);

   for (int ii_0=0; ii_0<sizeOfMenuSecundary; ii_0++) {
     strNameAux = vetNames[ii_0];
     //TM2 if (iGrafMenu.getId()==0) System.out.println(" - " + ii_0 + ": " + strNameAux);
     if (strNameAux!=null && !strNameAux.equals(IgrafMenu.SEP)) { // copy only relevants items
       itemPMenu = null;

       if (hasSubMenu && ii_0==12) { //PARTICULAR: for now => iGrafMenu==mnEdit and the submenu is only in the last menu item
         // if it is menu Edit with item "Idioma" => has submenu
         // listener in each submenu - see 'buildSubMenuIdioma(MenuEdicao)'
         itemPMenu = buildSubMenuIdioma(this.mnEdit); // Edit | Language
         }
       else { // if (itemPMenu==null) // it is not 'submenu' Idioma
         itemPMenu = new JMenuItem(strNameAux);

         //TODO: para implementar acesso direto aos tratadores de eventos! Deveria haver um metodo separada para definir todos os controles
         //T Build a generic HashMap with all of menu items: indexed by its source message (before 'ResourceReader.msg(...)')
         //T hasMapAllMenuItem.put(localMenuItensName[ii_0], ??); // vetNames[]
         //T System.out.println(" - " + countRelevants + ": " + localMenuItensName[ii_0] + ""); // strNameAux = vetNames[ii_0]

         itemPMenu.setBackground(EsquemaVisual.corBackground);
         itemPMenu.setOpaque(true);
         //DEBUG: itemPMenu.setBorder(BorderFactory.createLineBorder(Color.black)); - not good...

         addActionListenerJMenuItem(iGrafMenu, itemPMenu); // Listener: secondary buttons
         }

       // Listener: secondary button Edicao { madZoomAmpliar, madZoomDiminuir }: runnable
       if (itemPMenu.isEnabled() &&
           strNameAux.equals(ResourceReader.msg("madZoomAmpliar")) || strNameAux.equals(ResourceReader.msg("madZoomDiminuir"))) {
          addMouseListenerRunnable(mnEdit, itemPMenu);
          }

       // Decoration of the JMenuItem - createLineBorder top, left, bottom, and right sides
       itemPMenu.setOpaque(true);
       // itemPMenu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray), BorderFactory.createEmptyBorder(0,3,0,3))); //

       hashMapJMenuItemsAux.put(""+countRelevants++, itemPMenu); // will define 'igraf.moduloCentral.visao.menu.IgrafMenu.vecSecListJMenuItems'

       itemPMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
       itemPMenu.setToolTipText(vetDescriptions[ii_0]); // description of this item option

       popupMenu.add(itemPMenu);
       //TM2 if (iGrafMenu.getId()==0) System.out.println(" - " + itemPMenu.getText());
       }
     else {
       popupMenu.addSeparator(); // javax.swing.JSeparator
       }
     }

   // Copy only those JMenuItem that are relevant - for now, those different of SEP
   vecJMenuItems = new JMenuItem[countRelevants];
   for (int ii_0=0; ii_0<countRelevants; ii_0++) {
     vecJMenuItems[ii_0] = (JMenuItem) hashMapJMenuItemsAux.get(""+ii_0);
     //TM2 if (iGrafMenu.getId()==0) System.out.println(" - " + ii_0 + ": " + vecJMenuItems[ii_0].getText());
     }

   // Set in 'IgrafMenu iGrafMenu' its variable 'PainelBotoes painelBotoes' and 'JMenuItem [] vecSecListJMenuItems'
   iGrafMenu.setPainelBotoes(this, vecJMenuItems);

   //TM1 sizeOfMenuSecundary = iGrafMenu.getVecSecListJMenuItems() == null ? 0 : iGrafMenu.getVecSecListJMenuItems().length;
   //TM1 try { System.out.println("src/igraf/moduloSuperior/visao/PainelBotoes.java: " + iGrafMenu.getClass() + ", id="+iGrafMenu.getId() + ", #iGrafMenu.vecSecListJMenuItems=" + sizeOfMenuSecundary );
   //TM1  System.out.println(" " + iGrafMenu.getName() + " (0="+iGrafMenu.getVecSecListJMenuItems()[0].getText() + ")\n"); } catch (Exception e) { e.printStackTrace(); }

   objectsJPopupMenu_and_vecJMenuItem[0] = popupMenu;
   objectsJPopupMenu_and_vecJMenuItem[1] = vecJMenuItems;
   return objectsJPopupMenu_and_vecJMenuItem;
   } // private JPopupMenu buildOneSecondaryPopMenu(IgrafMenu menu, boolean hasSubMenu)


 private IgrafMenu getMenuFromName (HashMap hm, String strName) {
   IgrafMenu menu = null;
   try {
     menu = (IgrafMenu)hm.get(strName);
     if (menu==null) {
       System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in buttons' panel constructor: could not find the Menu under the name '" + strName + "'\n" +
                          "Look at 'igraf.moduloCentral.ModuloCentral': in 'hashMapIGrafMenus' is there a correct 'put(\""+strName+"\", _name_.getMenu())'?");
       }
   } catch (Exception except) {
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in buttons' panel constructor: perhaps the array with all Menus is missing...: '" + strName + "'");
     except.printStackTrace();
     }
   return menu;
   }


 public void testVecSecLists (String strName, JMenuItem [] vecJMenuItems) {
   System.out.println("PainelBotoes.testVecSecLists: " + strName + ", #vecJMenuItems=" + vecJMenuItems.length);
   for (int ii_0=0; ii_0<vecJMenuItems.length; ii_0++) try {
     System.out.println(" "+ii_0+": "+ vecJMenuItems[ii_0].getText());
     } catch (Exception except) { System.out.println(" "+ii_0+": ERROR - "+ vecJMenuItems[ii_0] + ": " + except); }
   }


 // Build secondary popup menus: to each JButton
 // menuGraf | menuCalculo | menuAnim | menuEdicao | menuExerc | menuAjuda | [ menuPol   | menuLang ]
 private void buildSecondaryPopupMenus () {
   JPopupMenu popupMenu; // create pop-up menu to each primary button
   JMenuItem item;
   ImageIcon icon;
   String msg; // if 'button' and 'popupMenu' are not 'final' here, the error is lauched: 'local variable button is accessed from within inner class'
   HashMap hm = moduloCentral.getHashMapIGrafMenus(); // igraf.moduloCentral.ModuloCentral
   this.mnGraf = (MenuGrafico)getMenuFromName(hm, "menuGrf");
   this.mnCalc = (MenuCalculo)getMenuFromName(hm, "menuCalculo");
   this.mnAnim = (MenuAnimacao)getMenuFromName(hm, "menuAnim");
   this.mnEdit = (MenuEdicao)getMenuFromName(hm, "menuEdicao");
   this.mnExerc = (MenuExercicio)getMenuFromName(hm, "menuExerc");
   //R this.mnPol = (MenuPoligono)getMenuFromName(hm, "menuPol");
   this.mnAjud = (MenuAjuda)getMenuFromName(hm, "menuAjuda");

   // igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java
   hashMapMenus = new HashMap();
   hashMapMenus.put("menuGrf",this.mnGraf);     // MenuGrafico
   hashMapMenus.put("menuCalculo",this.mnCalc); // MenuCalculo
   hashMapMenus.put("menuAnim",this.mnAnim);    // MenuAnimacao
   hashMapMenus.put("menuEdicao",this.mnEdit);  // MenuEdicao
   hashMapMenus.put("menuExerc",this.mnExerc);  // MenuExercicio
   //R hashMapMenus.put("menuPol",this.mnPol);  // MenuPoligono
   hashMapMenus.put("menuAjuda",this.mnAjud);   // MenuAjuda

   //try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

   //TODO: Para implementar acesso direto aos tratadores de eventos!
   //TODO: Deveria haver um metodo separada para definir todos os controles
   //T Build a generic HashMap with all of menu items: indexed by its source message (before 'ResourceReader.msg(...)')
   //T private HashMap hashMapControllers; ?
   //T hashMapControllers.put("maMostraControle", janelaAnimacaoController); // igraf.moduloJanelasAuxiliares.controle.JanelaAnimacaoController
   //T private hasMapAllMenuItem; ?
   //T hasMapAllMenuItem.put(localMenuItensName[ii_0], ??);
   // Object object = hashMapEvents.get();
   // System.out.println("src/igraf/moduloSuperior/visao/PainelBotoes.java: addActionListenerJMenuItem(" + menu.getClass().getName() + ",...): " + strCmd);

   // Creates 'JPopupMenu' and 'JMenuItem [] vecJMenuItems'
   Object [] objectsJPopupMenu_and_vecJMenuItem;
   objectsJPopupMenu_and_vecJMenuItem = buildOneSecondaryPopMenu(mnGraf,false);  // __ Graphics __
   popupMenu = (JPopupMenu)objectsJPopupMenu_and_vecJMenuItem[0];
   vecSecListGraf = (JMenuItem [])objectsJPopupMenu_and_vecJMenuItem[1];
   insertListenersNPopupMenu2button(this, grafBt      , popupMenu, "menuGraf", 0);

   objectsJPopupMenu_and_vecJMenuItem = buildOneSecondaryPopMenu(mnCalc,false);  // __ Calculus __
   popupMenu = (JPopupMenu)objectsJPopupMenu_and_vecJMenuItem[0];
   vecSecListCalculo = (JMenuItem [])objectsJPopupMenu_and_vecJMenuItem[1];
   insertListenersNPopupMenu2button(this, calcBt      , popupMenu, "menuCalculo", 1);

   objectsJPopupMenu_and_vecJMenuItem = buildOneSecondaryPopMenu(mnAnim,false);  // __ Animation __
   popupMenu = (JPopupMenu)objectsJPopupMenu_and_vecJMenuItem[0];
   vecSecListAnim = (JMenuItem [])objectsJPopupMenu_and_vecJMenuItem[1];
   insertListenersNPopupMenu2button(this, animBt      , popupMenu, "menuAnim", 2);

   objectsJPopupMenu_and_vecJMenuItem = buildOneSecondaryPopMenu(mnEdit, true);  // __ Edition __
   popupMenu = (JPopupMenu)objectsJPopupMenu_and_vecJMenuItem[0];
   vecSecListEdicao = (JMenuItem [])objectsJPopupMenu_and_vecJMenuItem[1];
   insertListenersNPopupMenu2button(this, edicaoBt    , popupMenu, "menuEdicao", 3);

   objectsJPopupMenu_and_vecJMenuItem = buildOneSecondaryPopMenu(mnExerc,false); // __ Exercise __
   popupMenu = (JPopupMenu)objectsJPopupMenu_and_vecJMenuItem[0];
   vecSecListExercicio = (JMenuItem [])objectsJPopupMenu_and_vecJMenuItem[1];
   insertListenersNPopupMenu2button(this, exercicioBt , popupMenu, "menuExerc", 4);

   objectsJPopupMenu_and_vecJMenuItem = buildOneSecondaryPopMenu(mnAjud,false);  // __ Help __
   popupMenu = (JPopupMenu)objectsJPopupMenu_and_vecJMenuItem[0];
   vecSecListAjuda = (JMenuItem [])objectsJPopupMenu_and_vecJMenuItem[1];
   insertListenersNPopupMenu2button(this, ajudaBt     , popupMenu, "menuAjuda", 5);
   //TM1 System.out.println("src/igraf/moduloSuperior/visao/PainelBotoes.java: #vecSecListGraf="+vecSecListGraf.length+"\n");
   //TM1 testVecSecLists("menuGraf",vecSecListGraf); //testVecSecLists("menuAjuda",vecSecListAjuda);

   } // private void buildSecondaryPopupMenus()


 // Build all primary buttons with their messages
 private JButton buildOnePrimaryButton (String msgName, ImageIcon iconButton, int position) {
   JButton button = new JButton(ResourceReader.msg(msgName), iconButton);
   button.setLayout(null); //button.setSize(...) - insertListenersNPopupMenu2button
   button.setSize(BUTTONWIDTH,BUTTONHEIGTH);
   button.setLocation(position * BUTTONWIDTH2, 0);
   this.add(button);

   //CAUTION: 'igraf/basico/resource/StringsTable*.properties' MUST adopt the standard 'menu(*)' to the button name and 'menu(*)Descr' to its description
   button.setToolTipText(ResourceReader.msg(msgName+"Descr"));
   button.setBackground(Color.white);
   button.setOpaque(true);

   hashMapPrimaryButtons.put(msgName, button); // each button in HashMap with its name

   return button;   
   } // private void buildOnePrimaryButton(String msgName, String msgDescription, ImageIcon icon)


 // Build all primary buttons with their associated 
 private void buildPrimaryButtons () {
   hashMapPrimaryButtons = new HashMap();
   hashMapPopupMenus = new HashMap();

   // Build primary button: only the JButton
   grafBt      = buildOnePrimaryButton("menuGrf", iconGraphics, 0);
   calcBt      = buildOnePrimaryButton("menuCalculo", iconCalculus, 1);
   animBt      = buildOnePrimaryButton("menuAnim", iconAnimation, 2);
   //R poliBt      = buildOnePrimaryButton("menuPol", iconPoligons, );
   edicaoBt    = buildOnePrimaryButton("menuEdicao", iconEdition, 3);
   exercicioBt = buildOnePrimaryButton("menuExerc", iconExercise, 4);
   ajudaBt     = buildOnePrimaryButton("menuAjuda", iconHelp, 5);

   // Build secondary popup menus: to each JButton
   buildSecondaryPopupMenus();

   // HashMap of 'JMenuItem []' with the descriptions of its items
   hashMapPopupMenus.put("menuGrf", vecSecListGraf);
   hashMapPopupMenus.put("menuCalculo", vecSecListCalculo);
   hashMapPopupMenus.put("menuAnim", vecSecListAnim);
   //R hashMapPopupMenus.put("menuPol", vecSecListPol);
   hashMapPopupMenus.put("menuEdicao", vecSecListEdicao);
   hashMapPopupMenus.put("menuExerc", vecSecListExercicio);
   hashMapPopupMenus.put("menuAjuda", vecSecListAjuda);

   } // private void buildPrimaryButtons()

 //leo End
 //leo New model to button : 04/2013
 //------------------------------------------------------------------------------------------------------------


 //CAUTION: must be called AFTER 'igraf/moduloCentral/visao/menu/Menu(*).java' makes their updates()

 // Updates the JPopupMenu items of 'IgrafMenu iGrafMenu'
 private void updateLabels (IgrafMenu iGrafMenu, JMenuItem [] vetSecList) {
  JMenuItem itemPMenu;
  String strNameAux;
  String [] vetNames = null;
  String [] vetDescriptions = null;
  vetNames = iGrafMenu.getMenuItensText(); // IgrafMenu.getMenuItensText() returns 'String [] listaOpcoes'
  vetDescriptions = iGrafMenu.getMenuItensDescription();
  int sizeOfMenuSecundary = vetNames.length; // number of JMenuItem in this secondary menu
  int countRelevants = 0;
  //TM2 System.out.println("PainelBotoes.java: updateLabels: " + iGrafMenu.getName()); // + ", hasSubMenu=" + iGrafMenu.hasSubMenu());

  for (int ii_0=0; ii_0<sizeOfMenuSecundary; ii_0++) try {
    strNameAux = vetNames[ii_0];
    if (strNameAux!=null && !strNameAux.equals(IgrafMenu.SEP)) {
      itemPMenu = vetSecList[countRelevants];
      itemPMenu.setText(strNameAux);
      itemPMenu.setToolTipText(vetDescriptions[countRelevants]); // description of this item option

      countRelevants++;
      //TM2 System.out.println(" - " + itemPMenu.getText());
      }
    } catch (Exception except) { // for (int ii_0=0; ii_0<sizeOfMenuSecundary; ii_0++)
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: updateLabels of " + iGrafMenu + ": in index " + ii_0 +": " + except);
     }

  // 12 = MenuEdicao.SUBMENUINDEX
  if (iGrafMenu.hasSubMenu()) { //PARTICULAR: for now => iGrafMenu==mnEdit and the submenu is only in the last menu item
    SubMenuIdioma smi = ((MenuEdicao)iGrafMenu).getSubMenuIdioma();
    String [] vetNamesSub = smi.getMenuItensText(); // get 'SubMenuIdioma.String [] listaOpcoes' updated
    String [] vetDescSub = smi.getMenuItensDescription(); //((MenuEdicao)iGrafMenu).getSubMenuItensDescription(); // String [] descricao
    JMenuItem itemPSubMenu;
    int sizeOfSubmenu = vetNamesSub==null ? 0 : vetNamesSub.length;
    for (int jj_1=0; jj_1<sizeOfSubmenu; jj_1++) try {
      itemPSubMenu = vetSecListSubmenus[jj_1]; //ATTENTION: it is supposed that in submenu does not have separators (all items are relavants)
      itemPSubMenu.setText(vetNamesSub[jj_1]);
      itemPSubMenu.setToolTipText(vetDescSub[jj_1]);
      } catch (Exception except) {
        System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in submenu of " + iGrafMenu.toString() + ": #vet=" + sizeOfSubmenu + ", i=" + jj_1);
        except.printStackTrace();
        }
    }

  } // private void updateLabels (IgrafMenu iGrafMenu, JMenuItem [] vetSecList)


 // Updates the JPopupMenu items of 'IgrafMenu iGrafMenu'
 public void updateLabels () {

  grafBt.setText(ResourceReader.msg("menuGrf"));
  calcBt.setText(ResourceReader.msg("menuCalculo"));
  animBt.setText(ResourceReader.msg("menuAnim"));
  edicaoBt.setText(ResourceReader.msg("menuEdicao"));
  exercicioBt.setText(ResourceReader.msg("menuExerc"));
  //R poliBt.setText(ResourceReader.msg("menuPol"));
  ajudaBt.setText(ResourceReader.msg("menuAjuda"));

  grafBt.setToolTipText(ResourceReader.msg("menuGrfDescr"));
  calcBt.setToolTipText(ResourceReader.msg("menuCalculoDescr"));
  animBt.setToolTipText(ResourceReader.msg("menuAnimDescr"));
  edicaoBt.setToolTipText(ResourceReader.msg("menuEdicaoDescr"));
  exercicioBt.setToolTipText(ResourceReader.msg("menuExercDescr"));
  //R poliBt.setToolTipText(ResourceReader.msg("menuPolDescr"));
  ajudaBt.setToolTipText(ResourceReader.msg("menuAjudaDescr"));

  //CAUTION: 'igraf/basico/resource/StringsTable*.properties' MUST adopt the standard 'menu(*)' to the button name and 'menu(*)Descr' to its description
  updateLabels(this.mnGraf, vecSecListGraf);
  updateLabels(this.mnCalc, vecSecListCalculo);
  updateLabels(this.mnAnim, vecSecListAnim);
  updateLabels(this.mnEdit, vecSecListEdicao);
  updateLabels(this.mnExerc, vecSecListExercicio);
  updateLabels(this.mnAjud, vecSecListAjuda);
  }


 }
