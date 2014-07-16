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
 * @description Control do expressions. Classe controladora dos eventos enviados e recebidos pelo objeto EntradaExpressao
 * 
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloSuperior.controle.entrada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.JTextComponent;

import igraf.IGraf;
import igraf.basico.event.AttentionToolTip;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuGraficoEvent;
import igraf.moduloInferior.visao.InfoPane;
import igraf.moduloSuperior.eventos.EntradaExpressaoEvent;
//RR import igraf.moduloSuperior.eventos.ModuloSuperiorEvent;
import igraf.moduloSuperior.visao.EntradaExpressao;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;


public class EntradaExpressaoController extends CommunicationController implements ActionListener, KeyListener {

 // os eventos esperados pela entrada de expressão devem provocar definição da
 // expressão exibida no textfield e, no futuro, mudança de cor de letra e fundo
 // eventos enviados deverão solicitar o desenho do gráfico na área de plotagem

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloSuperior/controle/entrada/EntradaExpressaoController.java";

 private boolean funcaoCorreta, enterPressed;

 private JTextComponent editor;
 private String lastExpr = "";


 private IgrafTabUpdateEvent igrafTabUpdateEvent = new IgrafTabUpdateEvent(this, IgrafTabUpdateEvent.EXPRESSION_CHANGED);

 private EntradaExpressao entradaExpressao;
 private InfoPane infoPane; // access to "status bar"

 public void setInfoPanel (InfoPane infoPane) { // ContentPane set access to the 'igraf.moduloInferior.visao.InfoPane'
  this.infoPane = infoPane; // igraf/ContentPane.java: relationsModulesToBroadcaster(IGraf,boolean): ms = new ModuloSuperior(ehApplet, mc); ms.entradaExpressaoController.setInfoPanel(infoPane);
  }

 public EntradaExpressaoController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }

 // envia eventos de entrada do iGraf a cada evento de ação gerado sobre a entrada de expressão
 public void actionPerformed (ActionEvent e) {
  String funcao = (String) entradaExpressao.getSelectedItem();
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + " actionPerformed(" + funcao + ")");
  if (funcao != null && funcao.length() > 0 && enterPressed) {
   enviaFuncaoDesenho(funcao);
   enterPressed = false;
   }
  }
 
 private void enviaFuncaoDesenho (String funcao) {
  if (funcao.equals(""))
   return;

  funcaoCorreta = Analisa.verificaFuncao(funcao);
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + " enviaFuncaoDesenho(" + funcao + "): funcaoCorreta=" + funcaoCorreta);

  if (funcaoCorreta && Analisa.getErrorMessage()=="") { // && !entradaExpressao.temFuncao(funcao)
   igrafTabUpdateEvent.setExpression(funcao);
   enviarEvento(igrafTabUpdateEvent); // IgrafTabUpdateEvent

   entradaExpressao.setText(funcao);
   enviarEvento(new EntradaExpressaoEvent(this, funcao));
   }
  else {
   String msgError = ResourceReader.msg("exercRAErroExpInv") + "\"" + funcao + "\" - " + Analisa.getErrorMessage(); // Invalid expression:
   System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: enviaFuncaoDesenho(" + funcao + "): " + msgError);

   AttentionToolTip.showToolTipText(entradaExpressao, msgError); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

   entradaExpressao.setToolTipText(funcao);
   infoPane.setStatusBarMessage(msgError);
   }

  }


 public void keyPressed (KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_ENTER)
   enterPressed = true;
  }

 
 /* 
  * Este método é de extrema importância, pois recebe e trata eventos que solicitam conhecimento
  * sobre a expressão atualmente na área de edição 
  */
 public void tratarEventoRecebido (CommunicationEvent ie) {
  String strCommand = ie.getCommand();
  if (strCommand.equals(ResourceReader.msg("msgMenuGrfNovaSes"))) { // um IgrafMenuGraficoEvent transporta este comando
   editor.setText(""); 
   }
  else
  if (strCommand.equals(ResourceReader.msg("setInputExpression"))) { // "set input expression"
   //TODO: must be reviewed
   //RR ModuloSuperiorEvent mse = (ModuloSuperiorEvent)ie;
   //RR editor.setText(mse.getExpressao());
   System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: event erased!!! " + ie.getClass().getName());
   }

  if (ie instanceof IgrafMenuGraficoEvent) {
   IgrafMenuGraficoEvent imge = (IgrafMenuGraficoEvent) ie;
 
   if (!imge.getExpressao().equals(""))
    return;

   String strCommand2 = imge.getCommand();
   if (strCommand2.equals(ResourceReader.msg("msgMenuGrfDes")))
    enviaFuncaoDesenho(editor.getText());
   else
   if (strCommand2.equals(IgrafMenuGraficoEvent.HIDE_GRAPH) || strCommand2.equals(IgrafMenuGraficoEvent.DELETE_GRAPH) ) { // bit mask
    // ? para o caso abaixo, 'imge' literalmente, sai do controlador de menus, vem até aqui
    // ? buscar a expressão que deve ser oculta e vai para o plotter levando esta informacao
    String funcao = editor.getText();
    if (funcao.length() > 0) {
     imge.setExpressao(editor.getText());
     enviarEvento(imge);
     }
    }
   } // if (ie instanceof IgrafMenuGraficoEvent)

  if (ie instanceof IgrafMenuCalculoEvent) {
   IgrafMenuCalculoEvent imce = (IgrafMenuCalculoEvent)ie; 
 
   if (!imce.getExpressao().equals("")) return;

   if (imce.getCommand().equals(ResourceReader.msg("mcDerVer")) ||
      imce.getCommand().equals(ResourceReader.msg("mcIIVer")) ) {
    String funcao = editor.getText();
    if (funcao.length() > 0) {
     imce.setExpressao(funcao);
     enviarEvento(imce); 
     }
    }
   }

  if (ie instanceof IgrafTabUpdateEvent) {
   IgrafTabUpdateEvent igrafTabUpdateEvent = (IgrafTabUpdateEvent)ie;
 
   if (igrafTabUpdateEvent.getCommand().equals(IgrafTabUpdateEvent.GENERAL_UPDATE))
    editor.setText(igrafTabUpdateEvent.getExpression());
 
   } // if (ie instanceof IgrafTabUpdateEvent)
  } // public void tratarEventoRecebido(CommunicationEvent ie)


 public void keyReleased (KeyEvent e) {
  String expr = editor.getText().trim();

  if (expr.length() < 1 | expr.equals(lastExpr)) return;

  //QUARENTENA: 04/06/2013
  // Deixar processamento apenas para apos um ENTER!!! Vide o 'enviaFuncaoDesenho(String funcao)' acima
  // funcaoCorreta = Analisa.verificaFuncao(expr);
  // igrafTabUpdateEvent.setExpression(editor.getText());
  // enviarEvento(igrafTabUpdateEvent); // IgrafTabUpdateEvent
  lastExpr = expr;
  }


 public void keyTyped (KeyEvent e) {
  //T if ("0123456789x /r".indexOf(e.getKeyChar())<0) e.consume();
  //T if ("-+a".indexOf(e.getKeyChar())>0) e.consume();
  }

 
 public void setControlledObject (Object obj) {
  entradaExpressao = (EntradaExpressao)obj;
  editor = entradaExpressao.getTextEditor();
  }

 }
