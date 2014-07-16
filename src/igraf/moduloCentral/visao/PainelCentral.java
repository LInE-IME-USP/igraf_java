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
 * @description O objeto painelCentral é um contêiner que recebe um painel com abas e os menus.
 * O painel com abas cuida da exibição das várias possíveis áreas de desenho, enquanto
 * o painelCentral gerencia a exibição dos menus.
 * 
 * @see 
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloCentral.visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLayeredPane;

import igraf.IGraf;
import igraf.basico.util.Configuracoes;
import igraf.moduloCentral.controle.AnimSliderController;
import igraf.moduloCentral.controle.PainelCentralController;
import igraf.moduloCentral.eventos.FrameResizedEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloInferior.ModuloInferior;
import difusor.evento.CommunicationEvent;


public class PainelCentral extends JLayeredPane {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/PainelCentral.java: ";

 private TabbedViewer tabbedViewer;

 AnimSliderController asc; // = new AnimSliderController();
 private AnimSlider slider; // = new AnimSlider(asc);

 private IgrafMenu [] menuList;

 protected IgrafMenu menuExibido;
 boolean temMenuVisivel = false;

 private ModuloInferior moduloInferior; // to allow any one above ModuloCentral to access "status bar" 'igraf/moduloInferior/visao/InfoPane.java'

 private boolean sliderVisivel = false;

 public PainelCentral (PainelCentralController pcc, ModuloInferior moduloInferior) {
  pcc.setControlledObject(this);
  this.moduloInferior = moduloInferior;

  asc = new AnimSliderController(pcc.getMc());
  slider = new AnimSlider(asc);
  tabbedViewer = new TabbedViewer(pcc.getMc(), moduloInferior);
  setLayout(new java.awt.BorderLayout()); //
  add(tabbedViewer, BorderLayout.CENTER, -1); // original
  }

 public void setModuloInferior () {
  }


 public void mudaExibicaoSlider () {
  if (sliderVisivel) {
   remove(slider);
   tabbedViewer.resetDimensions();
   }
  else {
   add(slider, BorderLayout.SOUTH);
   tabbedViewer.makeSliderRoom();
   }
  validate();
  sliderVisivel = !sliderVisivel;
  }

 public void removeMenu (IgrafMenu im) {
//System.out.println("PainelCentral.java: ******** ");
  if (im != null) {
   remove(im);
   temMenuVisivel = false;
   validate();
   repaint();
   }
  }

 public void removeMenuAtual () {
  if (menuExibido != null && menuExibido.isSubMenu()){
   for (int i = 0; i < menuList.length; i++)
    removeMenu(menuList[i]);
   return;
   }

  removeMenu(menuExibido);
  }


 // Change the menu under the mouse: remove last one, put the new ont
 public void exibeMenu (IgrafMenu im, int x) {
  // System.out.println("PainelCentral.java: exibeMenu x=" + x);
  if (im==null) {
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "exibeMenu(IgrafMenu,int): ERROR: im null!\n");
     return; //SECURITY: it must not reach this point with NULL...
     }

  if (temMenuVisivel && !im.isSubMenu())
     removeMenu(menuExibido);

  im.setPositionX(x);
  temMenuVisivel = true;
  menuExibido = im;

  //TODO
  //TODO We must review this, it is throwing the 'IllegalArgumentException' but without this the layout became strange...
  //java.lang.IllegalArgumentException: cannot add to layout: constraint must be a string (or null)
  // ...
  // at igraf.moduloCentral.visao.PainelCentral.exibeMenu(PainelCentral.java:93)
  try {
    add(im, new Integer(3), -1); // show menu over AreaDeDesenho - original
                                 // Container.java: void add(Component comp, Object constraints, int index)
  } catch (Exception e) { }

  //if(im.isSubMenu())setComponentZOrder(im, 0);
  }

 public void setMenuList (IgrafMenu [] menuList) {
//System.out.println("PainelCentral.java: ***** "); // igraf.moduloCentral.visao.menu.IgrafMenu
  this.menuList = menuList;
  }

 public IgrafMenu [] getMenuList () {
  return menuList;
  }

 public boolean temMenuVisivel () {
  return temMenuVisivel;
  }

 public void insereAba () {
  tabbedViewer.criaAba(false); // creates a new item in TabbedViewer
  }

 public void removeAba () {
  tabbedViewer.removeAba();
  }

 public void resetTabs () {
  tabbedViewer.resetTabs();
  }

 public void nextTab () {
  tabbedViewer.nextTab();
  }

 public void setTabbedViewerBounds (CommunicationEvent ie) {
  FrameResizedEvent fre = (FrameResizedEvent)ie;
  slider.setBounds(fre.getRectangle());
  tabbedViewer.setTabbedViewerBounds(fre.getRectangle());
  validate();
  }

 public void copiarImagem () {
  tabbedViewer.copiarImagem();
  }

 public void exportarJPG () {
  tabbedViewer.exportarJPG();
  }


 // Update tab label
 // From: igraf/moduloCentral/controle/PainelCentralController.java: tratarEventoRecebido(CommunicationEvent ie)
 public void updateLabels () {
  //T System.out.println("\n\nsrc/igraf/moduloCentral/visao/PainelCentral.java: updateLabels()");
  tabbedViewer.updateLabels(); // igraf/moduloCentral/visao/TabbedViewer.java
  }

 }
