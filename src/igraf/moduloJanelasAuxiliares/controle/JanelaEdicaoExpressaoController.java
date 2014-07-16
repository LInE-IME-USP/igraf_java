/**
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author RP, LOB
 *
 * @description Janela para edição de expressões/funções
 *
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
 * @see igraf/moduloJanelasAuxiliares/visao/tangente/PainelTangente.java
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 **/

package igraf.moduloJanelasAuxiliares.controle;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.Utilitarios;
import igraf.moduloCentral.eventos.GraphPlotterEvent;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.desenho.DesenhoFuncao;
import igraf.moduloCentral.visao.plotter.GraphPlotter;
import igraf.moduloJanelasAuxiliares.eventos.EdicaoExpressaoEvent;
import igraf.moduloJanelasAuxiliares.visao.JanelaEdicaoExpressao;
import igraf.moduloSuperior.controle.entrada.Analisa;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import moduloColor.ColorChangeListener;
import moduloColor.ColorEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class JanelaEdicaoExpressaoController extends JanelaController implements KeyListener, ActionListener,  ItemListener, ColorChangeListener {

 private String funcaoOriginal, funcaoAtual, novaFuncao;
 private Color corAtual, corOriginal;
 private JanelaEdicaoExpressao jee;


 public JanelaEdicaoExpressaoController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  } 


 public void tratarEventoRecebido (CommunicationEvent ie) {
  super.tratarEventoRecebido(ie);

  if (ie instanceof ChangeLanguageEvent) {
   if (jee != null) jee.updateLabels();
   }
  else
  // atualiza a lista de funções na janela de edição
  if (ie instanceof GraphicOnScreenChangedEvent && jee != null) {
   //if (((GraphicOnScreenChangedEvent)ie).isEditing()) return;
   try {    
    atualizaChoices();
    } catch (Exception e) { }
   }  
  
  int k = 0;
  // exibe na tela a janela de edição ao duplo-clique sobre a curva
  if (ie instanceof GraphPlotterEvent &&ie.getCommand().equals(ResourceReader.msg("msgMenuGrfEditaExp"))) {
   exibeJanelaEdicao();
   String expr = ((GraphPlotter)ie.getSource()).getExpressaoSobCursor();
   k = getExprIndex(expr);
   iniciaEdicao(k);   
   return;
   }   

  //TODO: eliminar 'ResourceReader.msg(...)' passando a usar apenas a chave do item de menu (e.g., trocar 'ResourceReader.msg("msgMenuGrfEditaExp")' por '"msgMenuGrfEditaExp"'
  // exibe na tela a janela de edição após seleção no menu 'Gráfico'
  if (ie.getCommand().equals(ResourceReader.msg("msgMenuGrfEditaExp"))) {
   exibeJanelaEdicao();
   iniciaEdicao(k);
   }
  }
 
 private void exibeJanelaEdicao () {  
  jee = new JanelaEdicaoExpressao(this);
  atualizaChoices();
  jee.setVisible(true);
  }
 
 // variável que impede o início do looping infinito
 private boolean sendEvent;
 private void atualizaChoices () {
  sendEvent = false; 
  jee.setFunctionList(getListaDesenho());
  sendEvent = true;
  }

 private int getExprIndex (String expr) {  
  if (expr == null) return 0;
  
  for (int i = 0; i < listaDesenho.size(); i++) {
   Desenho d = (DesenhoFuncao)listaDesenho.get(i);
   if (d.getDescricao().equals(expr)) {
    jee.setFunctionIndex(i);
    return i;
    }
   }
  return 0;
  }

 private void iniciaEdicao (int i) {
  Desenho d = (DesenhoFuncao)listaDesenho.get(i);
  String s = Utilitarios.retiraEspacos(d.getFuncaoAtual()) ;
  setChoiceCores(i);
  corAtual = d.getColor();  
  funcaoOriginal = s;    
  funcaoAtual = s;
  jee.setText(s);     
  }
 
 private void setChoiceCores (int i) {
  sendEvent = false;
  Desenho d = (DesenhoFuncao)listaDesenho.get(i);
  Color cor = d.getColor();
  corOriginal = cor;
  jee.setColorIndex(Desenho.getColorIndex(corOriginal));
  sendEvent = true;
  }
 

 public void keyReleased (KeyEvent e) {
  String aux = Utilitarios.retiraEspacos(jee.getText());
  if ((!funcaoAtual.equals(aux)) && Analisa.sintaxeCorreta(aux)) {
   novaFuncao = aux;
   atualizaPlotter();
   }   
  }

 public void actionPerformed (ActionEvent e) {
  String cmd = e.getActionCommand();
  if (cmd.equals(ResourceReader.msg("msgCancelar"))) {
   novaFuncao = funcaoOriginal;
   corAtual = corOriginal;
   atualizaPlotter();
   }
  jee.setVisible(false);
  }

 public void itemStateChanged (ItemEvent e) {
  JComboBox c = (JComboBox) e.getSource();
  if (!c.isShowing()) return;
  
  if (c.getName().equals("colorComboBox")) {
   try {
    mudaCor(Desenho.getColor(jee.getColorIndex()));
    } catch (Exception e1) { }
   }
  else if (c.getName().equals("functionComboBox")) {
    iniciaEdicao(c.getSelectedIndex()); // prepara os dados para a nova edição
    }
  }


 public void mudaCor (Color c) {
  if (c != null) {
   novaFuncao = funcaoAtual;
   corAtual = c;
   atualizaPlotter();
   }
  }


 // Troca a função (ou cor da função)
 private void atualizaPlotter () {
  if (sendEvent) {
   enviarEvento(new EdicaoExpressaoEvent(this));
   funcaoAtual = novaFuncao;
   }
  }

 public String getFuncaoOriginal () {
  return funcaoOriginal;
  }

 public String getFuncaoAtual () {
  return funcaoAtual;
  }
 
 public String getNovaFuncao () {
  return novaFuncao;
  }
 
 public void setColor (Color c) {
  corAtual = c;
  }

 public Color getColor () {
  return corAtual;
  }
 
 public void keyTyped (KeyEvent e) { }
 public void keyPressed (KeyEvent e) { }

 public void updateColor (ColorEvent ce) {
  mudaCor(ce.getColor());
  }

 }
