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
 * @description Method to distribute event treatment
 * Classe que define as opera��es b�sicas que todo m�dulo do iGraf deve implementar.   Estas opera��es
 * tratam do envio de eventos para o ambiente externo ao m�dulo para que sejam disseminados pelo sistema
 * e da recep��o de eventos gerados em outras parte do programa.
 * 
 * @see ...
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf.moduloCentral.controle;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.DesenhoTextoEvent;
import igraf.moduloCentral.eventos.EstadoTelaEvent;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.eventos.IgrafSessaoEvent;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuAnimacaoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuEdicaoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuGraficoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuPoligonoEvent;
import igraf.moduloCentral.modelo.EstadoIgraf;
import igraf.moduloCentral.modelo.Sessao;
import igraf.moduloCentral.visao.AreaDesenho;
import igraf.moduloExercicio.eventos.RespostaAlgebricaEvent;
import igraf.moduloJanelasAuxiliares.eventos.AtualizaParametroEvent;
import igraf.moduloJanelasAuxiliares.eventos.EdicaoExpressaoEvent;
import igraf.moduloJanelasAuxiliares.eventos.JanelaIntegralEvent;
import igraf.moduloJanelasAuxiliares.eventos.JanelaTangenteEvent;
import igraf.moduloSuperior.eventos.EntradaExpressaoEvent;

public class AreaDesenhoController extends CommunicationController {

 private AreaDesenho areaDDesenho;
 private EstadoIgraf ea = new EstadoIgraf(); // igraf.moduloCentral.modelo.EstadoIgraf
 private Sessao sessao;
 private boolean isActive = true;

 private int tabIndex; // number of the tab associated

 //DEBUG
 private static int count = 0;
 public final int ID = count++;

 // igraf.moduloCentral.visao.TabbedViewer.criaAba(TabbedViewer.java:115)
 public AreaDesenhoController (CommunicationFacade module, boolean getEvents, int tabIndex) {
  super(module, getEvents);
  this.sessao = new Sessao(tabIndex);

  this.tabIndex = tabIndex; // number of the tab associated (0, 1, ...)
  //T System.out.println("\n\nsrc/igraf/moduloCentral/controle/AreaDesenhoController.java: #"+ID+", module="+module.getClass().getName()+", getEvents="+getEvents+", tabIndex="+tabIndex);
  //T try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

  }

 //TODO: por uniformidade, nao deve criar sua 'AreaDesenho'
 //QUARENTENA: 23/05/2013 remover apos tempo de uso e desenvolvimento sem precisar do metodo...
 //RR public AreaDesenhoController (int tabIndex) {  sessao = new Sessao(tabIndex);  areaDDesenho = new Area Desenho(this);  } "Area Desenho" sem espaco!

 public AreaDesenho getAreaDesenho () {
  return areaDDesenho;
  }

 public int getTabIndex () { return tabIndex; } // number of the tab associated

 public void setActive (boolean active) {
  isActive = active;
  }


 public void setControlledObject (Object obj) {
  areaDDesenho = (AreaDesenho)obj;
  }

 //TODO:
 //TODO: Tremendamente INEFICIENTE!!!!
 //TODO: eliminar difusor ao menos de atualizacao de AreaDeDesenho que eh de longe o mais pesado metodo!!!
 //TODO:   
 // Treat all events associated with AreaDeDesenho
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido: igraf.moduloArquivo.eventos.LoadingIGrafEvent
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido: igraf.moduloCentral.eventos.FrameResizedEvent
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido: igraf.moduloCentral.eventos.IgrafTabUpdateEvent
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido: igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido: igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido: igraf.moduloSuperior.eventos.EntradaExpressaoEvent
 public void tratarEventoRecebido (CommunicationEvent ie) {
  String expressao = "";  // vari�vel criada para evitar duplica��o de desenho da mesma fun��o
  String strCmd = ie.getCommand();

  if (!isActive)
   return;

  if (strCmd.equals(GraphicOnScreenChangedEvent.CLEAR_SCREEN)) {
   areaDDesenho.limpaRastro();
   }
  else
  if (strCmd.equals(AtualizaParametroEvent.UPDATE))  //
   areaDDesenho.graphPlotter.trataMenuAnimacao(ie);
  else
  if (ie instanceof IgrafMenuAnimacaoEvent) {
   if (strCmd.equals(ResourceReader.msg("maRastro")))
    areaDDesenho.mudaEstadoRastro();
   else
    areaDDesenho.graphPlotter.trataMenuAnimacao(ie);
   }
  else
  if (strCmd.equals(RespostaAlgebricaEvent.GET_LIMITS))
   areaDDesenho.graphPlotter.reportLimits();
  else
  if (ie instanceof ResetEvent) {
   areaDDesenho.graphPlotter.resetPlotter();
   areaDDesenho.polygonPlotter.resetPlotter();
   areaDDesenho.eixos.resetPlotter();
   sessao.reset();
   expressao = "";
   }
  else
  // tratamento para atualiza��es individuais do estado do sistema
  if (ie instanceof IgrafTabUpdateEvent) {
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent) ie;
 
   if (!iue.getCommand().equals(IgrafTabUpdateEvent.GENERAL_UPDATE))
    ea.atualizaEstadoIgraf(iue);
   if (iue.getCommand().equals(IgrafTabUpdateEvent.SLIDER_VALUE_CHANGED))
    areaDDesenho.graphPlotter.trataMenuAnimacao(ie);
   } 
  else
  // must draw a new function: after an ENTER into the TextField area
  if (ie instanceof EntradaExpressaoEvent) {
   EntradaExpressaoEvent eee = (EntradaExpressaoEvent) ie;
   if (eee.getCommand().equals(EntradaExpressaoEvent.DRAW_POLYGON))
    areaDDesenho.polygonPlotter.inserePoligono(eee);
   else {
    if (!expressao.equals(eee.getExpressao())) {
     areaDDesenho.graphPlotter.insereDesenho(eee.getExpressao(), "", eee.getDominio());
     expressao = eee.getExpressao();
     }
    }
   }
  else
  if (ie instanceof EdicaoExpressaoEvent) { // tratamento da edi��o de express�o
   areaDDesenho.graphPlotter.editaFuncaoTela((EdicaoExpressaoEvent)ie);
   }
  else
  if (ie instanceof EstadoTelaEvent) {
   areaDDesenho.graphPlotter.mudaEstadoTela((EstadoTelaEvent)ie);
   areaDDesenho.eixos.mudaEstadoTela((EstadoTelaEvent)ie);
   }
  else
  if (ie instanceof DesenhoTextoEvent)
   areaDDesenho.graphPlotter.trataDesenhoTexto(ie);
  else
  if (ie instanceof IgrafMenuEdicaoEvent) {
   areaDDesenho.graphPlotter.trataMenuEdicaoEvent(ie);
   areaDDesenho.eixos.trataMenuEdicaoEvent(ie);
   }
  else
  if (ie instanceof IgrafMenuGraficoEvent) { // tratamento de eventos gerados pelo menu Gr�fico
   areaDDesenho.graphPlotter.trataMenuGraficoEvent((IgrafMenuGraficoEvent)ie);
   areaDDesenho.polygonPlotter.trataMenuGraficoEvent(ie);
   } 
  else
  if (ie instanceof IgrafMenuCalculoEvent) { // tratamento dos itens do menu C�lculo
   // igraf/moduloCentral/visao/AreaDesenho.java: igraf.moduloCentral.visao.plotter.GraphPlotter gp
   // igraf/moduloCentral/visao/plotter/GraphPlotter.java: trataCalculoEvent(CommunicationEvent ie): desenhoCalculoController.trataEvento(ie)
   // igraf/moduloCentral/controle/desenho/DesenhoCalculoController.java: trataEvento(ie)
   areaDDesenho.graphPlotter.trataCalculoEvent((IgrafMenuCalculoEvent)ie); // GraphPlotter.java: trataCalculoEvent(CommunicationEvent ie): desenhoCalculoController.trataEvento(ie);
   }
  else
  if (ie instanceof JanelaIntegralEvent) {
   areaDDesenho.graphPlotter.trataCalculoEvent((JanelaIntegralEvent)ie);
   if (strCmd.equals(JanelaIntegralEvent.ERASE))
    return;
   }
  else
  if (ie instanceof JanelaTangenteEvent) {
   JanelaTangenteEvent jte = (JanelaTangenteEvent)ie; // igraf.moduloJanelasAuxiliares.eventos.JanelaTangenteEvent
   areaDDesenho.graphPlotter.trataDesenhoTangente(jte);

   if (jte.getCommand().equals(JanelaTangenteEvent.ANIMATE))
    iniciaAnimacao(jte.getAnimationMode()); 
   }

  areaDDesenho.atualizaDesenho(); // evita duplica��o de chamadas a repaint 

  if ((ie instanceof EventoRegistravel)) 
   registraEvento(ie);
  }
 
 // int k = 1;
 private void enviarSessao () {
  enviarEvento(new IgrafSessaoEvent(this, sessao)); 
  }
 
 public void registraEvento (CommunicationEvent ie) {
  //System.out.println("ADC.186: " + ie);
  if (sessao.registraEvento(ie)) {
   enviarSessao();
   }
  }


 // New tab or change between tabs
 public void notificaTrocaAba () {
  //T System.out.println("\n\n\nsrc/igraf/moduloCentral/controle/AreaDesenhoController.java: 'IgrafTabUpdateEvent' com " + IgrafTabUpdateEvent.GENERAL_UPDATE);
  IgrafTabUpdateEvent iue = new IgrafTabUpdateEvent(this, IgrafTabUpdateEvent.GENERAL_UPDATE); // "general update"

  iue.setExpression(ea.getConteudoEntradaExpressao());
  iue.setExercise(sessao.isItExercise()); // igraf.moduloCentral.modelo.Sessao sessao
  iue.setSliderValue(ea.getValorSlider());
  areaDDesenho.graphPlotter.notificaAlteracaoEstado(); // igraf/moduloCentral/visao/AreaDesenho.java: igraf.moduloCentral.visao.plotter.GraphPlotter gp
  areaDDesenho.graphPlotter.notifyScreenChanged(); // igraf/moduloCentral/visao/plotter/GraphPlotter.java
  enviarEvento(iue);
  }

 public void iniciaAnimacao (boolean b) {
  areaDDesenho.animar(b);
  }

 }
 //D if (ie instanceof IgrafMenuPoligonoEvent) {
 //D  areaDDesenho.polygonPlotter.trataMenuPoligonoEvent(ie);
 //D  if (!strCmd.equals(ResourceReader.msg("mepPintaPoli")) &&
 //D     !strCmd.equals(ResourceReader.msg("mepNPintaPoli")) &&
 //D     !strCmd.equals(IgrafMenuPoligonoEvent.UNDO) &&
 //D     !strCmd.equals(IgrafMenuPoligonoEvent.REDO) &&
 //D     !strCmd.equals(IgrafMenuPoligonoEvent.LOAD_POLYGON))
 //D   areaDDesenho.changeCursor();
 //D
 //D  if (strCmd.equals(IgrafMenuPoligonoEvent.LOAD_POLYGON)) {
 //D      areaDDesenho.polygonPlotter.inserePoligono((IgrafMenuPoligonoEvent)ie);
 //D      registraEvento(ie);
 //D   }
 //D
 //D  if (strCmd.equals(IgrafMenuPoligonoEvent.UNDO) &&
 //D     ie.getSource().toString().indexOf("ArquivoModel") > -1) {
 //D   areaDDesenho.polygonPlotter.undo();
 //D      registraEvento(ie);
 //D   }
 //D
 //D  if (strCmd.equals(IgrafMenuPoligonoEvent.REDO) &&
 //D        ie.getSource().toString().indexOf("ArquivoModel") > -1) {
 //D   areaDDesenho.polygonPlotter.redo();
 //D   registraEvento(ie);
 //D   }
 //D
 //D  if (strCmd.equals(IgrafMenuPoligonoEvent.DRAW_POLYGON))
 //D   areaDDesenho.defaultCursor(); 
 //D  else return; // evita gravar o evento ao clique no menu pol�gono 
 //D  }
