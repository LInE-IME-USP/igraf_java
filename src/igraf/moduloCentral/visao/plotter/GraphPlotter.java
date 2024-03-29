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
 * @description This class control the drawing of all static functions.
 * This class has a runnable class ('TextTask extends TimerTask') in order to show
 * 
 * @see igraf/moduloCentral/visao/plotter/Plotter.java
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */


package igraf.moduloCentral.visao.plotter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JOptionPane;

import igraf.IGraf;
import igraf.basico.util.Configuracoes;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.GeraPoligono;
import igraf.basico.util.Utilitarios;
import igraf.moduloCentral.controle.desenho.DesenhoAnimacaoController;
import igraf.moduloCentral.controle.desenho.DesenhoCalculoController;
import igraf.moduloCentral.controle.desenho.DesenhoController;
import igraf.moduloCentral.controle.desenho.DesenhoFuncaoController;
import igraf.moduloCentral.controle.desenho.DesenhoPoligonoController;
import igraf.moduloCentral.controle.desenho.DesenhoTangenteController;
import igraf.moduloCentral.controle.desenho.DesenhoTextoController;
import igraf.moduloCentral.eventos.DesenhoTextoEvent;
import igraf.moduloCentral.eventos.EstadoTelaEvent;
import igraf.moduloCentral.eventos.GraphPlotterEvent;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuGraficoEvent;
import igraf.moduloCentral.visao.AreaDesenho;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.desenho.DesenhoAnimacao;
import igraf.moduloCentral.visao.desenho.DesenhoFuncao;
import igraf.moduloCentral.visao.desenho.DesenhoIntegral;
import igraf.moduloCentral.visao.desenho.DesenhoPoligono;
import igraf.moduloCentral.visao.desenho.DesenhoTexto;
import igraf.moduloInferior.ModuloInferior;
import igraf.moduloJanelasAuxiliares.eventos.AtualizaParametroEvent;
import igraf.moduloJanelasAuxiliares.eventos.EdicaoExpressaoEvent;
import igraf.moduloJanelasAuxiliares.eventos.JanelaTangenteEvent;
import igraf.moduloSuperior.controle.entrada.Analisa;
import igraf.moduloSuperior.eventos.EntradaExpressaoEvent;
//R import igraf.moduloSuperior.eventos.ModuloSuperiorEvent;

import difusor.evento.CommunicationEvent;

public class GraphPlotter extends Plotter {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/plotter/GraphPlotter.java";
 private static final int POSX_LST_FUNCTIONS = 20, POSY_LST_FUNCTIONS = 30; // position (x,y*i) to the i-th function expression (usually at the top left corner of iGraf)

 private AreaDesenho areaDesenho;

 private Vector listaDesenhoVisivel =  new Vector();
 private int strokeIndex = -1, ordem = 0;
 private Color corFuncao;
 private Desenho desenho;

 private ModuloInferior moduloInferior; // to allow any one above ModuloCentral to access "status bar" 'igraf/moduloInferior/visao/InfoPane.java'

 private DesenhoAnimacaoController desenhoAnimacaoController = new DesenhoAnimacaoController(this);
 private DesenhoTangenteController desenhoTangenteController = new DesenhoTangenteController(this);
 // DesenhoPoligonoController desenhoPoligonoController = new DesenhoPoligonoController(this);
 private DesenhoCalculoController  desenhoCalculoController = new DesenhoCalculoController(this);
 private DesenhoFuncaoController   desenhoFuncaoController = new DesenhoFuncaoController(this);
 private DesenhoTextoController    desenhoTextoController = new DesenhoTextoController(this);

 private DesenhoTexto desenhoTexto;
 private DesenhoTextoEvent desenhoTextoEvent;

 private boolean exibirListaFuncao = false; // show at the left top coner the list of all function in this AreaDesenho

 private FontMetrics fm;

 private Desenho functionSelected = null;

 private Desenho functionUnderMouse = null; // identify function under mouse (to show its name)
 private int xFuncao, yFuncao;              // position where the function was identifyed (put its name in this coordenates - by 'desenhaExpressao(Graphics2D gr)')

 private int textIndex = -1; // index of the text (defined in 'mouseClicked(...)')

 // From: igraf.moduloCentral.visao.AreaDesenho
 public GraphPlotter (AreaDesenho aDesenho, ModuloInferior moduloInferior) {
  this.areaDesenho = aDesenho;
  this.moduloInferior = moduloInferior;
  }

 public AreaDesenho getAreaDesenho () {
  return areaDesenho;
  }

 // igraf/moduloCentral/controle/desenho/DesenhoCalculoController.java: trataEvento(CommunicationEvent)
 public void trataCalculoEvent (CommunicationEvent ie) {
  desenhoCalculoController.trataEvento(ie);
  }


 // From: igraf.moduloCentral.visao.AreaDesenho.setGraphBuffer(AreaDesenho.java:122)
 public void desenha (Graphics2D g, int w, int h) {
  super.dimensiona(g, w, h);
  // IGraf.launchStackTrace();

  desenhoAnimacaoController.setValorA();
  // int m = 0;

  // atualizaDesenho(desenhoPoligonoController, g);
  atualizaDesenho(desenhoCalculoController, g); // igraf.moduloCentral.controle.desenho.DesenhoCalculoController
  atualizaDesenho(desenhoFuncaoController, g); // igraf.moduloCentral.controle.desenho.DesenhoFuncaoController
  atualizaDesenho(desenhoTangenteController, g); // igraf.moduloCentral.controle.desenho.DesenhoTangenteController
  atualizaDesenho(desenhoAnimacaoController, g); // igraf.moduloCentral.controle.desenho.DesenhoAnimacaoController
  atualizaDesenho(desenhoTextoController, g); // igraf.moduloCentral.controle.desenho.DesenhoTextoController

  // mostra, ao lado do cursor, a express�o correspondente ao gr�fico apontado
  if (functionUnderMouse != null)
   desenhaExpressao(g);

  // mostra na tela a lista das fun��es
  if (exibirListaFuncao)
   desenhaListaFuncao(g);
  }


 /**
  * Recebe uma string 'fun��o' e insere na lista de funcoes vis�veis, o que faz com que o gr�fico desta seja exibido na �rea de desenho.
  * @param String funcao, String desc, String dominio
  */
 public void insereDesenho (String funcao, String desc, String dominio) {

  if (funcao.indexOf("integral") == 0) {
   int k = funcao.indexOf("=");
   String strAux = funcao.substring(k+2);
   try {
    DesenhoIntegral di = new DesenhoIntegral(this, strAux, ordem++);
    desenhoCalculoController.insereDesenho(di);
    listaDesenhoVisivel.add(di);
    return;
   } catch (Exception e) { 
    if (IGraf.IS_DEBUG) e.printStackTrace();
    return;
    }
   }

  if (Analisa.temParametro(funcao)) {
   DesenhoAnimacao da = new DesenhoAnimacao(this, funcao, ordem++);
   da.setDominio(dominio);
   if (desenhoAnimacaoController.insereDesenho(da)) {
    listaDesenhoVisivel.add(da);
    notifyScreenChanged();
    }
   return;
   }

  if (desc.length() == 0)
   desenho = new DesenhoFuncao(this, funcao, ordem++); // 'igraf.moduloCentral.visao.desenho.Desenho desenho'
  else
   desenho = new DesenhoFuncao(this, funcao, ordem++, desc);

  desenho.setDominio(dominio);

  if (desenhoFuncaoController.insereDesenho(desenho)) {
   listaDesenhoVisivel.add(desenho);
   notifyScreenChanged();
   }

  } //public void insereDesenho(String funcao, String desc, String dominio)


 //TODO: rever
 // m�todo mantido por compatibilidade com arquivos antigos que contenham pol�gonos
 // o tratamento atual para pol�gonos est� em PolygonPlotter
 // na verdade este m�todo nunca mais ser� chamado e sair� daqui em breve.
 // public void inserePoligono(EntradaExpressaoEvent eee) {
 //  DesenhoPoligono dp = new DesenhoPoligono(this, eee.getExpressao(), 0, false, 0);
 //  if (desenhoPoligonoController.inserePoligono(dp))
 //   listaDesenhoVisivel.add(dp);
 //  }


 private void atualizaDesenho (DesenhoController dc, Graphics2D g) {
  int i = -1;
  try {
   for (i=0; i<dc.getNumDesenhos(); i++) {
    desenho = (Desenho)dc.getDesenho(i);
    desenho.atualizaDesenho(g);
    }
   } catch (Exception e) {
    String strErr = "";
    if (igraf.IGraf.IS_DEBUG) strErr = "igraf/moduloCentral/visao/plotter/GraphPlotter.java: ";
    System.err.println(strErr + "Error: in graph plotter, with i="+i+", desenho="+desenho);
    e.printStackTrace();
    }
  }


 // It shows at the left top coner the list of all function in this AreaDesenho, whenever 'exibirListaFuncao' is 'true'
 private void desenhaListaFuncao (Graphics2D g) {
  Color color = g.getColor();
  g.setFont(Configuracoes.fontFunctionName);

  //B int maxWidth = 0, height = 10; //TODO: NAO mais usado!
  //B fm = g.getFontMetrics();
  //B for (int i = 0; i < listaDesenhoVisivel.size(); i++) {
  //B  Desenho dsnh = (Desenho)listaDesenhoVisivel.get(i);
  //B  if (dsnh == null)
  //B   continue;
  //B  if (maxWidth < fm.stringWidth(dsnh.getDescricao()))
  //B   maxWidth = fm.stringWidth(dsnh.getDescricao());
  //B height += 19;
  //B }
  //B // corrigir bug: h� express�es que ficam fora do ret�ngulo
  //B g.setColor(new Color(210,210,250,100));
  //B g.fillRoundRect(15, 10, maxWidth+10, height, 10, 10);

  for (int i = 0; i < listaDesenhoVisivel.size(); i++) {
   Desenho dsnh = (Desenho)listaDesenhoVisivel.get(i);
   if (dsnh == null)
    continue;
   g.setColor(dsnh.getColor());
   g.drawString(dsnh.getDescricao(), POSX_LST_FUNCTIONS, POSY_LST_FUNCTIONS + POSX_LST_FUNCTIONS * i);
   //T System.out.println(IGCLASSPATH + ": desenhaListaFuncao(Graphics2D): " + dsnh.getDescricao());
   }
  g.setColor(color);
  }

 public void alternaVisibilidadeListaFuncao () {
  exibirListaFuncao = !exibirListaFuncao;
  }


 /*
  * It draw the function expression that is under the mouse
  * @param Graphics2D gr
  */
 private void desenhaExpressao (Graphics2D gr) {
  Color color = gr.getColor();
  gr.setFont(Configuracoes.fontFunctionName);
  gr.setColor(corFuncao);
  gr.drawString(functionUnderMouse.getDescricao(), xFuncao+20, yFuncao+10); // function name under: 'functionUnderMouse.toString()'
  gr.setColor(color);
  }


 /*
  * IMPORTANT: it launches the action of all primery button "Graphics"
  * @param igraf.moduloCentral.eventos.menu.IgrafMenuGraficoEvent; imge
  */
 public void trataMenuGraficoEvent (IgrafMenuGraficoEvent imge) {
  int imge_id = 0;
  String nameCommand = imge.getCommand();
  //T System.out.println(IGCLASSPATH + ": trataMenuGraficoEvent(" + imge.getClass().getName() +"): " + nameCommand);

  if (nameCommand.equals(ResourceReader.msg(IgrafMenuGraficoEvent.HIDE_GRAPH))) {
   String s = imge.getExpressao();
   if (s.length() > 0) apagaGrafico(s);
   }
  else
  if (nameCommand.equals(ResourceReader.msg(IgrafMenuGraficoEvent.DELETE_GRAPH))) {
   //RR //QUARENTENA String str = imge.getExpressao(); if (str.length() > 0) removeGrafico(str);
   removeGrafico(imge.getExpressao());  // it must have any function selected: 'functionSelected!=null'
   }
  else
  if (nameCommand.equals(ResourceReader.msg("msgMenuGrfDesTodos")))
   desenharTodos();
  else
  if (nameCommand.equals(ResourceReader.msg("msgMenuGrfEsconderUlt")))
   ocultaUltimoGrafico();
  else
  if (nameCommand.equals(ResourceReader.msg("msgMenuGrfEsconderTodos")))
   esconderTodosGraficos();
  else
  // este comando deve limpar a lista do combobox
  if (nameCommand.equals(ResourceReader.msg("msgMenuGrfRemoverTodos")))
   removerTodos();
  else
  if (nameCommand.equals(ResourceReader.msg("msgMenuGrfNovaSes")))
   enviarEvento(new ResetEvent(this, ResetEvent.RESET_TAB));

  if (imge_id == imge.id) return; imge_id = imge.id;
  if (nameCommand.equals(ResourceReader.msg("msgMenuGrfExibeListaExp"))) {
   alternaVisibilidadeListaFuncao();
   return;
   }

  notifyScreenChanged();
  }


 // Menu action:
 private void removerTodos () {
  listaDesenhoVisivel.removeAllElements();
  desenhoFuncaoController.removerTodos();
  //PL desenhoPoligonoController.removerTodos();
  desenhoAnimacaoController.removerAnimacoes();
  desenhoCalculoController.removerIntegrais();
  }

 // Menu action:
 private void esconderTodosGraficos () {
  listaDesenhoVisivel.removeAllElements();
  desenhoFuncaoController.ocultaTodosGraficos();
  //PL desenhoPoligonoController.ocultaTodosGraficos();
  desenhoAnimacaoController.apagaTodasAnimacoes();
  desenhoCalculoController.apagaTodasIntegrais();
  }

 // Menu action:
 private void ocultaUltimoGrafico () {
  try {
   Desenho dsnh = (Desenho)listaDesenhoVisivel.remove(listaDesenhoVisivel.size()-1);
   //PL if (d instanceof DesenhoPoligono) desenhoPoligonoController.ocultaDesenho(d);

   if (dsnh instanceof DesenhoAnimacao) {
    desenhoAnimacaoController.ocultaAnimacao(dsnh);
    return;
    }

   if (dsnh instanceof DesenhoIntegral) {
    desenhoCalculoController.apagaDesenho(dsnh);
    return;
    }

   if (dsnh instanceof DesenhoFuncao)
    desenhoFuncaoController.ocultaDesenho(dsnh);

   }
  catch (Exception e) { e.printStackTrace(); }
  }

 // Menu action:
 private void desenharTodos () {
  listaDesenhoVisivel.removeAllElements();
  listaDesenhoVisivel.addAll(desenhoFuncaoController.getListaDesenhoOculto());
  //PL listaDesenhoVisivel.addAll(desenhoPoligonoController.getListaDesenhoOculto());
  listaDesenhoVisivel.addAll(desenhoAnimacaoController.getListaAnimacaoOculta());
  listaDesenhoVisivel.addAll(desenhoCalculoController.getListaIntegralOculta());
  Collections.sort(listaDesenhoVisivel);
  desenhoFuncaoController.desenharTodos();
  //PL desenhoPoligonoController.desenharTodos();
  desenhoCalculoController.desenharTodasIntegrais();
  desenhoAnimacaoController.desenharTodasAnimacoes();
  }

 // Menu action:
 //TODO: deveria vir a funcao completa (talvez Desenho) e nao expressao para encotra-la!
 private void apagaGrafico (String funcao) {
  funcao = Utilitarios.retiraEspacos(funcao);
  Desenho dsnh;

  for (int i = 0; i < listaDesenhoVisivel.size(); i++) {
   dsnh = (Desenho)listaDesenhoVisivel.elementAt(i);

   String strDesenhoFunction = Utilitarios.retiraEspacos(dsnh.toString());

   if (funcao.equals(strDesenhoFunction)) {
    listaDesenhoVisivel.removeElementAt(i);

    if (dsnh instanceof DesenhoAnimacao) {
     desenhoAnimacaoController.ocultaAnimacao(dsnh);
     return;
     }

    if (desenho instanceof DesenhoFuncao) {
     desenhoFuncaoController.ocultaDesenho(dsnh);
     desenhoTangenteController.removeTangente(funcao);
     return;
     }

    //PL  if (dsnhinstanceof DesenhoPoligono) desenhoPoligonoController.ocultaDesenho(d);
    }
   }
  }

 // Menu action: "Remove graphic"
 //TODO: deveria vir a funcao completa (talvez Desenho) e nao expressao para encotra-la!
 private void removeGrafico (String funcao) {
  funcao = Utilitarios.retiraEspacos(funcao);
  Desenho dsnh;

  //System.out.println(IGCLASSPATH + ": trataMenuGraficoEvent(" + imge.getClass().getName() +"): DELETE_GRAPH, str=" + str)
  // it must have any function selected: 'functionSelected!=null'
  if (functionSelected==null) {
   moduloInferior.setStatusBarMessage(ResourceReader.msg("sbSelectedFunctionNull")); // "In order to remover function, first you must click over the function to be removed"
   return;
   }
  int indexOf = listaDesenhoVisivel.indexOf(functionSelected);
  if (indexOf>-1) { // listaDesenhoVisivel.contains(functionSelected))
   listaDesenhoVisivel.removeElementAt(indexOf);

   if (functionSelected instanceof DesenhoAnimacao) {
     desenhoAnimacaoController.removeAnimacao(functionSelected);
     functionSelected = null; // clear function selected
     moduloInferior.setDefaultInfo(); // set default message to "status bar"
     return;
     }
   if (functionSelected instanceof DesenhoFuncao) {
     String strFuncaoSelected = Utilitarios.retiraEspacos(functionSelected.getFuncaoAtual()); // it it has a tangent associated
     //T System.out.println("src/igraf/moduloCentral/visao/plotter/GraphPlotter.java: removeGrafico funcao=" + funcao + ", strFuncaoSelected=" + strFuncaoSelected);
     desenhoFuncaoController.removeDesenho(functionSelected);
     desenhoTangenteController.removeTangente(strFuncaoSelected); //TODO: is really 'funcao', is linked to 'functionSelected' (before was 'desenho' in 'desenhoFuncaoController.removeDesenho' above)
     functionSelected = null; // clear function selected
     moduloInferior.setDefaultInfo(); // set default message to "status bar"
     return;
     }
    // if (dsnh instanceof DesenhoPoligono) desenhoPoligonoController.removeDesenho(d);
   }

  //RR 
  //TODO: quarentena eliminado com as classes 'src/igraf/moduloSuperior/eventos/ModuloSuperiorEvent.java' e 'src/igraf/moduloSuperior/eventos/ModuloSuperiorDisseminavelEvent.java'
  //RR for (int i=0; i<listaDesenhoVisivel.size(); i++) {
  //RR  dsnh = (Desenho)listaDesenhoVisivel.elementAt(i);
  //RR  String strDesenhoFunction = Utilitarios.retiraEspacos(dsnh.toString());
  //RR  if (funcao.equals(strDesenhoFunction)) {
  //RR   listaDesenhoVisivel.removeElementAt(i);
  //RR   if (dsnh instanceof DesenhoAnimacao) {
  //RR    desenhoAnimacaoController.removeAnimacao(desenho);
  //RR    return;
  //RR    }
  //RR   if (dsnh instanceof DesenhoFuncao) {
  //RR    desenhoFuncaoController.removeDesenho(desenho);
  //RR    desenhoTangenteController.removeTangente(funcao);
  //RR    return;
  //RR    }
  //RR   // if (dsnh instanceof DesenhoPoligono) desenhoPoligonoController.removeDesenho(d);
  //RR   }
  //RR  } // for

  } // private void removeGrafico(String funcao)


 // "Menu Calculus" options
 // 
 public void desenhaIntegralIndefinida (String expressao) {
  // Need: src/igraf/moduloJanelasAuxiliares/controle/JanelaController.java: Vector listaDesenho, listaFuncao
  if (expressao=="") { // could come after a file is loaded => there is none function in 'DesenhoCalculoController desenhoCalculoController'
   // IgrafMenuCalculoEvent
   Vector listOfVisibleFunctions = this.getListaFuncaoVisivel(); // desenhoFuncaoController.getListaDesenho();
   String lastFunction = "";
   if (listOfVisibleFunctions!=null) {
     int sizeOfList = listOfVisibleFunctions.size();
     DesenhoFuncao desenhoFuncao = (DesenhoFuncao) listOfVisibleFunctions.elementAt(sizeOfList-1); // igraf.moduloCentral.visao.desenho.DesenhoFuncao: funcaoOriginal ou toString
     lastFunction = desenhoFuncao.toString(); // String strName = listOfVisibleFunctions.elementAt(sizeOfList-1).getClass().getName();
     }
   expressao = lastFunction;
   }

  if (expressao.length() > 0)
   insereDesenho("integral de f(x) = " + expressao, "", "");
   //T System.out.println(IGCLASSPATH + ": desenhaIntegralIndefinida('"+expressao+"')");
   }

 public void desenhaDerivada (String expressao) {
  String str = GeraPoligono.getDerivada(expressao);
  insereDesenho(str, new String("h(x) = " + str + " derivada de f(x) = " + expressao), "");
  }

 //TODO: por analogia precisa de 'desenhaAntiDerivada'?
 // public void desenhaAntiDerivada (String expressao) {
  // String s = GeraPoligono.getGraficoAntiDerivada(expressao); // nao existe hoje tal metodo em 'GeraPoligono.java'
  // insereDesenho(s, new String("integral indefinida de f(x) = " + s), "");
 // }

 public void trataDesenhoIntegral (CommunicationEvent ie) {
  desenhoCalculoController.trataEvento(ie);
  }
 // 
 // "Menu Calculus" options


 public void iniciaAnimacao (boolean b) {
  adc.iniciaAnimacao(b);
  }

 public void resetPlotter () {
  desenhoAnimacaoController.reset(); desenhoCalculoController.reset();
  desenhoFuncaoController.reset(); desenhoTangenteController.reset();
  desenhoTextoController.reset(); //desenhoPoligonoController.reset();
  ordem = 0; textIndex = -1;
  mudaEstadoTela(new EstadoTelaEvent(this, new Point(0,0), 60));
  listaDesenhoVisivel = new Vector();
  home();
  }

 public void notifyScreenChanged () {
  //TODO: eliminar difusor
  // igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java: public void tratarEventoRecebido(CommunicationEvent ie)
  adc.enviarEvento(new GraphicOnScreenChangedEvent(this)); // 'igraf.moduloCentral.controle.AreaDesenhoController adc' declared in 'Plotter.java'
  }

 public void notificaAlteracaoEstado () {
  desenhoFuncaoController.notificaAlteracaoEstado();
  desenhoAnimacaoController.notificaAlteracaoEstado();
  }

 public Vector getListaDesenhoVisivel () {
  return listaDesenhoVisivel;
  }

 public Vector getListaFuncaoVisivel () {
  return desenhoFuncaoController.getListaDesenho();
  }

 public Vector getListaAnimacaoVisivel () {
  return desenhoAnimacaoController.getListaDesenho();
  }

 public int getCorDesenho (String funcao) {
  int indiceCor = 0;
  Desenho dsnh;

  for (int i = 0; i < desenhoFuncaoController.getListaDesenho().size(); i++) {
   dsnh = (DesenhoFuncao)desenhoFuncaoController.getListaDesenho().get(i);
   if (dsnh.getFuncaoAtual().equals(funcao))
    indiceCor = dsnh.getColorIndex();
   }

  return indiceCor;
  }

 public void trataDesenhoTangente (JanelaTangenteEvent jte) {
  desenhoTangenteController.trataEvento(jte);
  }


 public void trataMenuAnimacao (CommunicationEvent event) {
  desenhoAnimacaoController.trataEvento(event);
  }

 public void trataDesenhoTexto (CommunicationEvent ie) {
  desenhoTextoController.trataEvento(ie);
  }

 public void trataMenuEdicaoEvent (CommunicationEvent event) {
  if (event.getCommand().equals(ResourceReader.msg("madZoomAmpliar")))
   zoom(zoomDif);
  else
  if (event.getCommand().equals(ResourceReader.msg("madZoomDiminuir")))
   zoom(-zoomDif);
  else
  if (event.getCommand().equals(ResourceReader.msg("madZoomPadrao")))
   zoomPadrao();
  }


 public void editaFuncaoTela (EdicaoExpressaoEvent eee) {
  String s1 = Utilitarios.retiraEspacos(eee.getFuncaoOriginal());

  for (int i = 0; i < listaDesenhoVisivel.size(); i++) {
   DesenhoFuncao d = (DesenhoFuncao)listaDesenhoVisivel.get(i);
   String s2 = Utilitarios.retiraEspacos(d.getFuncaoAtual());

   if (s2.equals(s1)) {
    d.mudaFuncao(eee.getFuncaoAtual());
    d.setColor(eee.getColor());
    }
   }
  notifyScreenChanged();
  }


 public void mouseMoved (MouseEvent e) {
  super.mouseMoved(e);

  if (cursorSobreTexto(e)) {
   cursorSobreTexto = true; // defined in 'Plotter.java'; effect in 'igraf/moduloCentral/visao/desenho/DesenhoTexto.java: enquadraString(String,Graphics)'
   return;
   }

  if (isAnyFunctionUnderMouse(e)!=null) {  // is any function under the mouse position? (to put its name in this coordenates - by 'desenhaExpressao(Graphics2D gr)')
   xFuncao = e.getX();
   yFuncao = e.getY();
   ((Component) e.getSource()).repaint(); // in order to show the function description
   }
  else if (strokeIndex > -1) {
   strokeIndex = -1;
   ((Component) e.getSource()).repaint();
   }
  }


 /*
  * Mouse clicked: identify text or function expression clicked
  */
 public void mouseClicked (MouseEvent e) {

  //T System.out.println(IGCLASSPATH + ": mouseClicked(MouseEvent): getButton=" + e.getButton() +", mouseOverPoint=" + mouseOverPoint);
  if (e.getButton() == 3 && !mouseOverPoint) {
   // button 3 => right button => window about function with parameter to animation
   if (cursorSobreTexto(e)) {
    int resp = JOptionPane.showConfirmDialog(father, ResourceReader.msg("textRemotionOptionPaneQuestion"), ResourceReader.msg("textRemotionOptionPaneTitle"), JOptionPane.YES_NO_OPTION);
    if (resp == JOptionPane.YES_OPTION) {
     desenhoTextoController.removeTexto(textIndex);
     father.repaint();
     }
    }
   else {
    enviarEvento(new AtualizaParametroEvent(this, AtualizaParametroEvent.SHOW_PARAM_PANEL));
    return;
    }
   }

  //T System.out.println("functionUnderMouse=" + isAnyFunctionUnderMouse(e) +", getClickCount=" + e.getClickCount());
  if (isAnyFunctionUnderMouse(e)!=null) {

   // any click => mark this function to future operation (remove, hide,...)
   moduloInferior.setStatusBarMessage(ResourceReader.msg("sbSelectedFunction") + ": " + functionUnderMouse.getDescricao()); // Selected function 'functionUnderMouse.toString()'
   functionSelected = functionUnderMouse; // to be used in Hide or Remove operantion (menu "Graphics | Remove graphic")

   if (e.getClickCount() == 2) { // 2 clicks: it is edition of the function expression
     GraphPlotterEvent ie = new GraphPlotterEvent(this);
     ie.setCommand(ResourceReader.msg("msgMenuGrfEditaExp"));
     enviarEvento(ie);
     }
   else { // a single click => mark this function to future operation (remove, hide,...)
     //TODO: cut this else, since the 'functionSelected' is select in any case of clicks...
     // igraf.moduloInferior.ModuloInferior.setMessageStatusBarr(String): InfoPaneController ipc .  InfoPane ip;

     //T System.out.println(" - clicada a funcao '" + functionUnderMouse.toString() + "': " + functionUnderMouse.getDescricao());

     //TODO: 23/05/2013 removido o evento 'igraf.moduloSuperior.eventos.ModuloSuperiorEvent'
     //R Apaguei a classe
     //R ModuloSuperiorEvent ie = new ModuloSuperiorEvent(this, functionUnderMouse); // igraf.moduloSuperior.eventos.ModuloSuperiorEvent
     //R ie.setCommand(ResourceReader.msg("setInputExpression"));
     //R enviarEvento(ie);
     }
   }
  else {
   functionSelected = null; // clear function selected

   //Falta: msg para texto
   // ? moduloInferior.setStatusBarMessage(ResourceReader.msg("sbSelectedFunction") + ": " + functionUnderMouse.getDescricao()); // Selected function 'functionUnderMouse.toString()'

   if (cursorSobreTexto(e) && e.getClickCount() == 2) {
    DesenhoTextoEvent desenhoTextoEvent = new DesenhoTextoEvent(this);
    DesenhoTexto desenhoTexto = (DesenhoTexto)desenhoTextoController.getDesenho(textIndex);
    desenhoTextoEvent.setDesenhoTexto(desenhoTexto);
    desenhoTextoEvent.setCommand(DesenhoTextoEvent.OPEN_EDITOR);
    desenhoTextoController.removeTexto(textIndex);
    enviarEvento(desenhoTextoEvent);
    }
   }

  } // public void mouseClicked(MouseEvent e)


 public void mousePressed (MouseEvent e) {
  cursorSobreTexto = cursorSobreTexto(e);
  if (!cursorSobreTexto) {
   super.mousePressed(e);
   }
  }

 public void mouseDragged (MouseEvent e) {
  if (!cursorSobreTexto)
   super.mouseDragged(e);
  else atualizaPosicaoTexto(e);
  }


 // Edition of text
 public void mouseReleased (MouseEvent e) {
  if (textIndex > -1) {
   //DEBUG
   //TODO: esta sendo usado???
   // System.out.println("\n\n\nsrc/igraf/moduloCentral/visao/plotter/GraphPlotter.java: mouseReleased: dispara runnable TextTask\nREVER!");
   // sendTimedEvent(new TextTask());
   }
  }

 /**
  * Verifica se o cursor do mouse se encontra sobre alguma curva na tela.
  * Devolve true caso isso ocorra.
  * @param MouseEvent e
  * @return Desenho
  */
 private Desenho isAnyFunctionUnderMouse (MouseEvent e) {
  Desenho dsnh = functionVerifyList(desenhoFuncaoController.getListaDesenho(), e);
  if (dsnh!=null)
   return dsnh;
  dsnh = functionVerifyList(desenhoCalculoController.getListaDesenho(), e);
  if (dsnh!=null)
   return dsnh;
  functionUnderMouse = null;
  return null;
  }


 private Desenho functionVerifyList (Vector lista, MouseEvent e) {
  Desenho dsnh = null;
  for (int i = 0; i < lista.size(); i++) {
   dsnh = (Desenho) lista.get(i);
   corFuncao = dsnh.getColor();

   if (dsnh.matchCoordinates(reverteNormalizaX(e.getX()), reverteNormalizaY(e.getY()))) {
    functionUnderMouse = dsnh; // dsnh.getDescricao()
    strokeIndex = i;
    return dsnh;
    }
   }
  return null;
  }

 public String getExpressaoSobCursor () {
  return functionUnderMouse.toString();
  }


 private void atualizaPosicaoTexto (MouseEvent e) {
  if (textIndex > -1) {
   desenhoTexto = (DesenhoTexto)desenhoTextoController.getListaDesenho().get(textIndex);
   desenhoTexto.setPosition(xPixelToReal(e.getX()), yPixelToReal(e.getY()));
   ((Component) e.getSource()).repaint();
   }
  }

 // o envio deste evento estava impedindo que o texto fosse removido
 // da �rea de desenho... tanto na edi��o quanto na dele��o

 // runnable
 class TextTask extends TimerTask {
  public void run() {
    counter++;
    if (counter > 4) {
     try {
      desenhoTextoEvent = new DesenhoTextoEvent(this);
      desenhoTextoEvent.setDesenhoTexto(desenhoTexto);
      desenhoTextoEvent.setCommand(DesenhoTextoEvent.UPDATE_POSITION);
      enviarEvento(desenhoTextoEvent);
     } catch (RuntimeException e) { }
     timer.cancel();
     timer = null;
     }
   }
  }


 private boolean cursorSobreTexto (MouseEvent e) {
  int sizeOf = desenhoTextoController.getListaDesenho().size();
  for (int ii_0 = 0; ii_0 < sizeOf; ii_0++) {
   DesenhoTexto dt = (DesenhoTexto)desenhoTextoController.getListaDesenho().get(ii_0);
   if (dt.matchCoordinates(reverteNormalizaX(e.getX()), reverteNormalizaY(e.getY()))) {
    textIndex = ii_0; // dt.getTextIndex();
    return true;
    }
   }
  textIndex = -1;
  return false;
  }

 public double getValorA () {
  return desenhoAnimacaoController.getValorA();
  }

 public double getValorB () {
  return desenhoAnimacaoController.getValorB();
  }

 public double getValorC () {
  return desenhoAnimacaoController.getValorC();
  }

 public double getValorM () {
  return desenhoAnimacaoController.getValorM();
  }

 public double getValorN () {
  return desenhoAnimacaoController.getValorN();
  }

 public double getValorK () {
  return desenhoAnimacaoController.getValorK();
  }

 }
