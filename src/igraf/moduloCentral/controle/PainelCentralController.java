/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @description Classe que controla os eventos gerados e recebidos pelo painel central principal.
 * A este painel recebe a área de desenho, todos os "menus" associados ao painel de botões e o
 * visualizador de expressões editadas.
 * Embora manipule a inserção e remoção destes componentes, os eventos gerados por eles são 
 * tratados em seus respectivos controladores.
 * 
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * 
 * @author RP, LOB
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.controle;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.ModuloCentral;
import igraf.moduloCentral.eventos.FrameResizedEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuGraficoEvent;
import igraf.moduloCentral.eventos.menu.IgrafSubMenuEvent;
import igraf.moduloCentral.visao.PainelCentral;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloSuperior.eventos.IgrafButtonPaneEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class PainelCentralController extends CommunicationController implements MouseMotionListener {

 private PainelCentral componentPanelCentral;
 private ModuloCentral componentModuloCentral;

 public void setControlledObject (Object obj) {
  componentPanelCentral = (PainelCentral)obj;
  }

 public PainelCentralController (ModuloCentral mc, boolean getEvents) {
  super(mc, getEvents);
  this.componentModuloCentral = mc;
  }
 
 public ModuloCentral getMc () {
  return componentModuloCentral;
  }

 // From: difusor.CommunicationFacade.disseminarEventoInternamente(CommunicationFacade.java:89)
 public void tratarEventoRecebido (CommunicationEvent ie) {

  //TODO
  // verificar como resetar as abas sem causar efeitos colaterais
  // if (ie instanceof ResetEvent) componentPanelCentral.resetTabs();

  //T System.out.println("\n\nsrc/igraf/moduloCentral/controle/PainelCentralController.java: tratarEventoRecebido: " + ie.getClass().getName());

  if (ie instanceof ChangeLanguageEvent) {
   componentPanelCentral.updateLabels(); // igraf.moduloCentral.visao.PainelCentral componentPanelCentral
   return;
   }

  if (ie instanceof FrameResizedEvent)
   componentPanelCentral.setTabbedViewerBounds(ie);
  else
  if (ie instanceof IgrafButtonPaneEvent || ie instanceof IgrafSubMenuEvent) {
     //System.out.println("igraf/moduloCentral/controle/PainelCentralController.java: " + ie.getCommand());
     alteraVisualizacaoMenu((CommunicationEvent)ie);
     }

  String strCommand = ie.getCommand();
  if (strCommand.equals(ResourceReader.msg("maMostraCDesl")))
   componentPanelCentral.mudaExibicaoSlider();
  else
  if (strCommand.equals(ResourceReader.msg("msgMenuGrfNovaAba"))) {
   componentPanelCentral.insereAba();
   if (((IgrafMenuGraficoEvent)ie).isLoading())
    componentPanelCentral.nextTab();
   }
  else
  if (strCommand.equals(ResourceReader.msg("msgMenuGrfRemoverAba")))
   componentPanelCentral.removeAba();
  else
  //  if(strCommand.equals(ResourceReader.msg("RESET_TABS")))//      componentPanelCentral.resetTabs();
  if (strCommand.equals(ResourceReader.msg("arqMenuCopia")))
   componentPanelCentral.copiarImagem();
  else
  if (strCommand.equals(ResourceReader.msg("arqMenuExporta")))
   componentPanelCentral.exportarJPG();
  } // public void tratarEventoRecebido(CommunicationEvent ie)


 private void alteraVisualizacaoMenu (CommunicationEvent evt) {
  IgrafMenu im = null;
  im = getMenu(evt.getCommand());
  int eventType = evt.getEventType();

  if (eventType == CommunicationEvent.MOUSE_ENTERED) {
   componentPanelCentral.exibeMenu(im, evt.getX()+2);  
   }

  if (eventType == CommunicationEvent.MOUSE_EXITED) {
   componentPanelCentral.removeMenu(im);                      
   }
  } 
 
 private IgrafMenu getMenu (String name) {
  // igraf.moduloCentral.visao.PainelCentral componentPanelCentral: igraf.moduloCentral.visao.menu.IgrafMenu [] menuList;
  IgrafMenu [] menuList = componentPanelCentral.getMenuList();

  IgrafMenu m;
  for (int i = 0; i < menuList.length; i++) {
   m = (IgrafMenu)menuList[i];
   if (m.toString().equals(name)) {
    return m;
    }
   }

  return null;
  } 

 public void mouseMoved (MouseEvent e) {
  componentPanelCentral.removeMenuAtual();
  }
 
 public void mouseDragged (MouseEvent e) {}

 }
