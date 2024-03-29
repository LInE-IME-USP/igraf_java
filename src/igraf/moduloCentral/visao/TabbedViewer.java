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
 * @description This control the tab. Each tab corresponds to a new AreaDesenho (and its controller)
 * Classe que faz o gerenciamento das v�rias poss�veis �reas de desenho visualiz�veis pelo usu�rio. 
 * 
 * @see igraf/moduloCentral/visao/PainelCentral.java: makes 'tabbedViewer = new TabbedViewer(pcc.getMc(), moduloInferior);'
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloCentral.visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import igraf.IGraf;
import igraf.basico.io.ImageFromIGraf;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.Configuracoes;
import igraf.moduloArquivo.Arquivo; // error messages
import igraf.moduloCentral.ModuloCentral;
import igraf.moduloCentral.controle.AreaDesenhoController;
import igraf.moduloInferior.ModuloInferior; // access to the "status bar" 'igraf/moduloInferior/visao/InfoPane.java'

//import igraf.moduloInferior.ModuloInferior; // to CommunicationFacade
// ModuloSuperiorDisseminavelEvent
// import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
// 

public class TabbedViewer extends JTabbedPane implements MouseListener {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/TabbedViewer.java";

 // Static variable to count the number of tab created 
 private static int tabIndex = 0;

 // This is the number of tabs created
 private int  numAba = 0;

 // This is the index of the visible tab
 private int indiceControlador = 0;

 ModuloCentral mc;

 private Vector listaControlador = new Vector();
 private int sliderHeight = 0;

 private AreaDesenhoController areaDesenhoController;
 private AreaDesenhoController areaDesenhoControllerOld; // preserve the old AreaDesenhoController in order to remove it from 'ModuloCentral mc'
 private AreaDesenhoController areaDesenhoControllerAux;

 private AreaDesenho areaDesenho;

 private ModuloInferior moduloInferior; // to allow any one above ModuloCentral to access "status bar" 'igraf/moduloInferior/visao/InfoPane.java'

 //IGraf.launchStackTrace(); //DEBUG

 // Sequence call:
 // at igraf.moduloCentral.visao.TabbedViewer.<init>(TabbedViewer.java:42)
 // at igraf.moduloCentral.visao.PainelCentral.<init>(PainelCentral.java:42)
 // at igraf.moduloCentral.ModuloCentral.<init>(ModuloCentral.java:25)
 // at igraf.ContentPane.<init>(ContentPane.java:31)
 // at igraf.IGraf.init(IGraf.java:193)
 public TabbedViewer (ModuloCentral mc, ModuloInferior moduloInferior) {
  this.mc = mc;
  this.moduloInferior = moduloInferior;
  // try{ String srt0=""; System.out.println(srt0.charAt(3)); } catch (Exception e ) { e.printStackTrace(); }

  setTabPlacement(SwingConstants.BOTTOM);
  addMouseListener(this);
  setOpaque(true);

  setForeground(Color.black); //white); // tab in first plane has the same color as AreaDesenho
  setBackground(Color.gray); // tabs not in focus is more dark

  criaAba(false); // it does 'changeControlTab(0)' - important to change control to the new tab
  }

 public void makeSliderRoom () {
  sliderHeight = 25;
  fixBounds();
  }

 public void resetDimensions () {
  sliderHeight = 0;
  fixBounds();
  }

 /**
  * Recebe um ret�ngulo 'r' e o utiliza como par�metro para a redefini��o dos limites
  * da �rea ocupada pelo painel de abas.  Este m�todo � chamado quando ocorre o 
  * redimensionamento do frame, que s� acontece no uso da vers�o desktop do iGraf.
  * @param r
  */
 public void setTabbedViewerBounds (Rectangle r) {
  //dimensionTabbedViewer = new Dimension(r.width, r.height); //L
  fixBounds();
  }

 private void fixBounds () {
  int dh = IGraf.ehApplet ? 0 : 140;
  // setBounds(0, 0, dimensionTabbedViewer.width,dimensionTabbedViewer.height);//dimWith,dimHeight);//dimensionTabbedViewer.width, dimensionTabbedViewer.height - dh - sliderHeight); //L
  }

 // From: here constructor and '/igraf/moduloCentral/visao/PainelCentral.java: void insereAba()'
 public void criaAba (boolean isChange) {
  // preserve the old AreaDesenhoController in order to remove it from 'ModuloCentral mc'
  areaDesenhoControllerAux = new AreaDesenhoController(mc, true, numAba); // this already add the listener to 'AreaDesenhoController' into the 'ModuloCentral mc'
  listaControlador.add(areaDesenhoControllerAux);
  AreaDesenho areaDesenhoOld = this.areaDesenho;
  areaDesenho = new AreaDesenho(areaDesenhoControllerAux, moduloInferior); // 'moduloInferior' to allow any one above ModuloCentral to access "status bar" 'igraf/moduloInferior/visao/InfoPane.java'
  areaDesenho.addMouseMotionListener(mc.pcc);

  int indexTab = numAba++;
  String [] msgs = { numAba + "" };
  addTab(ResourceReader.msg("msgTab") + numAba, areaDesenho); // "Tab N", where "N" is the tab number
  this.setToolTipTextAt(indexTab, ResourceReader.msgComVar("msgTabTip", "OBJ", msgs)); // msgTabTip = Click here to move to the tab number $OBJ$

  // Change view to the new created tab
  this.setSelectedIndex(numAba-1); // JTabbedPane.setSelectedIndex(int)
  indiceControlador = getSelectedIndex();

  //T test AareaDesenhoController and additional information
  //T String strAux = (areaDesenhoController!=null) ? areaDesenhoController.ID + "" : "<null>";
  //T System.out.println("\nsrc/igraf/moduloCentral/visao/TabbedViewer.java: criaAba(): tab " + tabIndex + " -> " + indiceControlador + " #addCtr=" + strAux + " #addCtrAux=" + areaDesenhoControllerAux.ID);
  //T System.out.println(" - #listaControlador="+listaControlador.size() + ", #tabs=" + getTabCount());

  if (isChange)
    changeControlTab(true,indiceControlador); // add again (remove the initial listener)
  else {
    int i = getTabCount()-1;
    if (i>0) { // it is not the first time (it is not "tab 0")
      //TA System.out.println("TabbedViewer.java: criaAba(isChange=" + isChange+"): " + areaDesenhoOld.getClass().getName()+ " id=" + areaDesenhoOld.getID());

      areaDesenhoController.setActive(false);
      mc.removeInternEventListener(areaDesenhoController); // remove from 'ModuloCentral mc' the old listener 'igraf.moduloCentral.controle.AreaDesenhoController areaDesenhoController'
      }
    areaDesenhoController = areaDesenhoControllerAux;
    areaDesenhoController.notificaTrocaAba();

    if (areaDesenhoOld!=null)
      areaDesenhoOld.stopAnimation(); // if there are animation => suspend any one
  
    tabIndex = i;
    // System.out.println(" - troca para aba nova: tabIndex="+tabIndex);
    }

  } // public void criaAba()


 private void changeControlTab (boolean isChange, int index) {
   //D System.out.println("src/igraf/moduloCentral/visao/TabbedViewer.java: changeControlTab("+index+"): tab=" + tabIndex + "->" + index + ", change=" + isChange);
   //D System.out.println(" - Plotter.ID="+areaDesenho.graphPlotter.getID()+", Plotter.translationX=" + areaDesenho.graphPlotter.getTranslationX());

  if (tabIndex==index) { 
    // user clicked in the active tab => ignore change
    return;
    }
  if (areaDesenhoController != null) {
   areaDesenhoController.setActive(false);
   mc.removeInternEventListener(areaDesenhoController); // remove from 'ModuloCentral mc' the old listener 'igraf.moduloCentral.controle.AreaDesenhoController areaDesenhoController'
   }
  else {
   //LOGS: 
   // Register error in GRF: "[ CommunicationFacade :: <this message> ], "
   String strErr = "changeControlTab(" + index + "): areaDesenhoController=null";
   if (IGraf.IS_DEBUG) {
     System.err.println("\n"+IGCLASSPATH + ": DEBUG!! Attention: the controller 'areaDesenhoController' is not supposed to be NULL in 'changeControlTab(boolean,int)'!");
     System.out.println(" " + strErr);
     }
   Arquivo.addLogError("TabbedViewer.java", strErr);
   }

  //TA System.out.println("TabbedViewer.java: changeControlTab(isChange=" + isChange+", "+index+")");

  areaDesenhoController = (AreaDesenhoController)listaControlador.get(index);
  areaDesenhoController.setActive(true);
  areaDesenhoController.notificaTrocaAba();
  // areaDDesenho.gp.notificaAlteracaoEstado(); - igraf/moduloCentral/visao/AreaDesenho.java: igraf.moduloCentral.visao.plotter.GraphPlotter gp
  // areaDDesenho.gp.notifyScreenChanged();    -  igraf/moduloCentral/visao/plotter/GraphPlotter.java
  // 
  // GraphPlotter.java: notifyScreenChanged(): adc.enviarEvento(new GraphicOnScreenChangedEvent(this));
  // GraphPlotter.java: notificaAlteracaoEstado(): desenhoFuncaoController.notificaAlteracaoEstado(); desenhoAnimacaoController.notificaAlteracaoEstado();
  // GraphPlotter.java estende Plotter.java: igraf.moduloCentral.controle.AreaDesenhoController adc;
  // Plotter.java: deve ser examinada tem muita variavel de controle dos desenhos
  // 
  // ModuloCentral mc: ModuloCentral estende difusor.CommunicationFacade
  // difusor/CommunicationFacade.java: Vector listaOuvintesInternos: listaOuvintesInternos.contains(areaDesenhoController)?

  if (isChange) {
   areaDesenho.stopAnimation(); // if there are animation => suspend any one
   mc.addInternEventListener(areaDesenhoController); // IMPORTANT: add the listener to 'AreaDesenhoController' into the 'ModuloCentral mc'
   areaDesenho = areaDesenhoController.getAreaDesenho();
   areaDesenho.startAnimation(); // if there are animation => restart all of them
   }
  // else it was just created in 'criaAba(boolean isChange)' and did 'areaDesenhoControllerAux=new AreaDesenhoController(mc,true,numAba)'
  //      thus it is already added to the listener in 'ModuloCentral mc'

  tabIndex = index;

  } // private void changeControlTab(boolean isChange, int index)


 public void mousePressed (MouseEvent e) {
  indiceControlador = getSelectedIndex(); 
  changeControlTab(true, indiceControlador);
  }

 public void nextTab () {
  int i = getTabCount()-1;
  changeControlTab(true, i);
  setSelectedIndex(i);
  }


 public static int getTabIndex () {
  return tabIndex;
  }

 public void mouseReleased (MouseEvent e) {}
 public void mouseClicked (MouseEvent e) {}
 public void mouseEntered (MouseEvent e) {}
 public void mouseExited (MouseEvent e) {}

 public void removeAba () {
  if (getTabCount() > 1){
   remove(indiceControlador);
   if (indiceControlador > 0)
    changeControlTab(true,indiceControlador-1);
   }
  }

 public void resetTabs () {
  for (indiceControlador = getTabCount()-1; indiceControlador > 0; indiceControlador--) {
   removeAba();
   }
  changeControlTab(true,0);
  }


 // From: igraf/moduloCentral/controle/PainelCentralController.java: tratarEventoRecebido(CommunicationEvent): copy construction to Image
 public void copiarImagem () {
  ImageFromIGraf.copiarImagem(getSelectedComponent()); 
  }

 // From: igraf/moduloCentral/controle/PainelCentralController.java: tratarEventoRecebido(CommunicationEvent): copy construction to Image file
 public void exportarJPG () {
  ImageFromIGraf.salvarComoJPEG(getSelectedComponent()); 
  }

 // Update tab label
 // From: igraf/moduloCentral/visao/PainelCentral.java: updateLabels()
 public void updateLabels () {
  int sizeOf = this.getTabCount();
  String [] msgs = new String[1];
  for (int ii_0=0; ii_0<sizeOf; ii_0++) {
    msgs[0] = (ii_0+1) + "";
    this.setTitleAt(ii_0, ResourceReader.msg("msgTab") + (ii_0+1));
    this.setToolTipTextAt(ii_0, ResourceReader.msgComVar("msgTabTip", "OBJ", msgs)); // msgTabTip = Click here to move to the tab number $OBJ$
    }
  }


 }
