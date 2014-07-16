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
 * @description 
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

package igraf.moduloJanelasAuxiliares.visao.tangente;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.Utilitarios;
import igraf.moduloCentral.eventos.DesenhoTangenteEvent;
import igraf.moduloJanelasAuxiliares.controle.JanelaTangenteControler;
import igraf.moduloJanelasAuxiliares.eventos.JanelaTangenteEvent;
import igraf.moduloJanelasAuxiliares.visao.ChoicePanel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Vector;

import difusor.i18N.LanguageUpdatable;

public class PainelTangente extends Panel implements ActionListener, TextListener, ItemListener, KeyListener, FocusListener, LanguageUpdatable {

 private Panel aux, p1;
 private Button plotButton = new Button(ResourceReader.msg("btFxTg"));
 private Button eraseButton = new Button(ResourceReader.msg("btErTg"));
 private Checkbox multiTangenteCheckBox = new Checkbox(ResourceReader.msg("cbVarTg"), false);
 private Checkbox animCheckBox = new Checkbox(ResourceReader.msg("cbAnimTg"), false);
 private Dimension d = new Dimension(192, 60);

 private TextField entradaX, saidaX;
 private Label equationLabel, erro, fx;

 private Color corLabel = new Color(230, 230, 230);
 private Color corLetra = Color.blue;
 private boolean variasTangentes = false;
 private double valorX;
 private final double DELTA = 0.02;

 private final int ESQUERDA = 37,
                   DIREITA = 39,
                   BAIXO = 40,
                   CIMA = 38;

 private ChoicePanel choice;
 private Vector listaAbscissas = new Vector(),
           listaLocalDeFuncoes = new Vector();

 // Impede o desenho de tangentes quando o campo de entrada dos valores de x está vazio
 private String funcaoAtual;

 //private boolean insercaoBloqueada;

 JanelaTangenteControler jtc;

 boolean animando = false;

 public PainelTangente (JanelaTangenteControler jtc) {
  this.jtc = jtc;

  aux = new Panel(new GridLayout(0,1,0,2)) {
   public Insets getInsets () { return new Insets(0,0,10,0); }
   };

  linhaUm();
  linhaDois();
  linhaTres();
  linhaQuatro();
  linhaCinco();
  add(aux);

  setFuncaoAtual(choice.getSelectedItem());
  try {
   setValorX();
  } catch (RuntimeException e) {
   if (igraf.IGraf.IS_DEBUG) System.err.println("igraf/moduloJanelasAuxiliares/visao/tangente/PainelTangente.java: construtor, em 'setValorX()': " + e.toString());
   }
  }

 /**
  * Configura a região da janela que contém o choice para seleção das funções
  */
 private void linhaUm () {
  choice = new ChoicePanel(ResourceReader.msg("chSelFun"));
  choice.addItemListener(this);
  aux.add(choice);
  choice.getChoice().addFocusListener(this);
  }

 /**
  * Configura a região da janela que contém o campo de entrada de valores para 'x'
  * e o rótulo que exibe os correspondentes valores de 'f(x)'
  */
 private void linhaDois () {
  p1 = new Panel(new GridLayout(0,1,0,2)) {
  public Dimension getPreferredSize () {
   return d;
   }
  public void paint(Graphics g) {
   g.setColor(Color.blue);
   g.drawString("x  =", 40, 20);
   g.drawString("f ( x ) =", 22, 48);
   g.setColor(Color.black);
   g.drawRect(0, 0, getSize().width-1, getSize().height-1);
   }
  public Insets getInsets () {
   return new Insets(5,75,5,5);
   }
  };

  fx = new Label();
  fx.setForeground(Color.red);
  fx.setBackground(corLabel);

  entradaX = new TextField();
  entradaX.setForeground(Color.red);
  entradaX.addActionListener(this);
  entradaX.addTextListener(this);
  entradaX.addKeyListener(this);
  entradaX.addFocusListener(this);

  saidaX = new TextField();
  saidaX.setEditable(false);

  p1.add(entradaX);
  p1.add(fx);
  aux.add(p1);
  }


 /**
  * Configura a região da janela que contém o rótulo onde é exibida a
  * equação da reta tangente a 'f(x)' no ponto 'x' atual
  */
 private void linhaTres () {
  Panel p51 = new Panel(new BorderLayout()) {
   public Dimension getPreferredSize () {
    return d;
    }
   };
  Panel p5 = new Panel(new GridLayout(2,1)) {
   public Dimension getPreferredSize () {
    return new Dimension(192, 30);
    }
   public void paint(Graphics g) {
    g.setColor(Color.black);
    g.drawRect(0, 0, getSize().width-1, getSize().height-1);
    }
   public Insets getInsets () {
    return new Insets(3,3,3,3);
    }
   };
  equationLabel = new Label();
  equationLabel.setForeground(Color.red);
  equationLabel.setBackground(corLabel);
  equationLabel.setAlignment(Label.CENTER);

  erro = new Label(ResourceReader.msg("lbEqReta"));
  erro.setForeground(Color.blue);
  erro.setAlignment(Label.CENTER);

  p5.add(erro);
  p5.add(equationLabel);
  p51.add(p5);
  aux.add(p51);
  }

 /**
  * Configura a região da janela que contém os checkboxes através dos quais o
  * usuário pode optar por animação da tangente e desenho automático
  */
 private void linhaQuatro () {
  Panel p31 = new Panel(new BorderLayout()) {
   public Dimension getPreferredSize () { return d; }
   };
  Panel p3 = new Panel(new GridLayout(2,1)) {
   public Dimension getPreferredSize () { return new Dimension(192, 30); }
   public void paint(Graphics g) {
    g.setColor(Color.black);
    g.drawRect(0, 0, getSize().width-1, getSize().height-1);
    }
   public Insets getInsets () { return new Insets(3,3,3,3); }
   };
  multiTangenteCheckBox.addItemListener(this);
  multiTangenteCheckBox.setForeground(corLetra);
  animCheckBox.addItemListener(this);
  animCheckBox.setForeground(corLetra);
  animCheckBox.setEnabled(false);

  p3.add(animCheckBox);
  p3.add(multiTangenteCheckBox); 

  p31.add(p3);
  aux.add(p31);
  }

 /**
  * Configura a região da janela que contém os botões de desnho "manual" e
  * para apagar a reta tangente.
  */
 private void linhaCinco () {
  Panel p31 = new Panel(new BorderLayout()) {
   public Dimension getPreferredSize () { return d; }
   };
  Panel p3 = new Panel(new GridLayout(2,1)) {
   public Dimension getPreferredSize () { return new Dimension(192, 30); }
   public void paint(Graphics g) {
    g.setColor(Color.black);
    g.drawRect(0, 0, getSize().width-1, getSize().height-1);
    }
   public Insets getInsets () { return new Insets(3,3,3,3); }
   };

  plotButton.setForeground(Color.black);
  plotButton.addActionListener(this);
  eraseButton.setForeground(Color.black);
  eraseButton.addActionListener(this);

  p3.add(plotButton);
  p3.add(eraseButton);
  p31.add(p3);
  aux.add(p31);
  }
  
 // ajusta o tamanho do painel principal
 public Dimension getPreferredSize () {
  return new Dimension(200, 315);
  }

 private void registraParFuncaoAbscissa (double x) {
  int k = choice.getSelectedIndex();

  if (k > listaAbscissas.size())
   listaAbscissas.setSize(k);

  try {
   listaAbscissas.removeElementAt(k);
  } catch (Exception e) {
    // só remove se tiver algo...
    if (igraf.IGraf.IS_DEBUG) System.err.println("igraf/moduloJanelasAuxiliares/visao/tangente/PainelTangente.java: registraParFuncaoAbscissa("+x+"): " + e.toString());
    }

  listaAbscissas.insertElementAt(String.valueOf(x), k);
   }  
  
 /**
   * Insere na sessão os pares x/f(x) para que o desenho da tangente a f(x)
   * no ponto x possa ser recuperado a partir de algum arquivo
   */
 private void registraEstadoTangentes () {
  for (int i = 0; i < listaAbscissas.size(); i++) {
   String s = "";
   s = "vx:" + listaAbscissas.elementAt(i) + " ";
   s += "fx:" + listaLocalDeFuncoes.elementAt(i);
   //mf.sessao.insereAcao(Acao.desenhaTangente, s);
   }
  }

 /**
  * Método chamado durante a reconstrução da sessão.  Refaz o gráfico de uma função
  * que foi registrada em uma sessão e desenha a reta tangente a essa função no ponto
  * recuperado do registro.
  * @param s
  */
 public void recuperaEstadoTangentes (String s) {
  int ini = 0, fim = 0;
  String aux = "";
  try {    
   ini = s.indexOf("vx:");
   fim = s.indexOf(" ", ini);
   aux += s.substring(ini, fim) + " ";
   valorX = Double.valueOf(s.substring(ini+3, fim)).doubleValue();
 
   ini = s.indexOf("fx:");
   aux += s.substring(ini);
   funcaoAtual = s.substring(ini+3);    
   }
  catch (Exception e) {
   if (igraf.IGraf.IS_DEBUG)
    System.err.println("igraf/moduloJanelasAuxiliares/visao/tangente/PainelTangente.java: recuperaEstadoTangentes("+s+"): " + e.toString());
   System.err.println("Tangent panel: recover state: the function expression is out off the standard\n");
   e.printStackTrace();
   }  
  desenhaTangente();
  } // void recuperaEstadoTangentes(String s)

 private void desenhaTangente () {
  jtc.enviarEvento(new JanelaTangenteEvent(this, funcaoAtual, valorX));
  }


 private void apagaTangente () {
  if (!variasTangentes)
   jtc.enviarEvento(new JanelaTangenteEvent(this));
  }

 void animaTangente (boolean b) {
  animando = b;
  jtc.enviarEvento(new JanelaTangenteEvent(this, funcaoAtual, b));
  entradaX.setEnabled(!b);
  }


 // ouve o botão fixar tangente
 public void actionPerformed (ActionEvent e) {
  Object source = e.getSource();
  if (source == eraseButton)
   apagaTangente();
  else { // fixar tangente
   jtc.enviarEvento(new JanelaTangenteEvent(this, true));
   desenhaTangente();
   entradaX.requestFocus();
   }
  }

 // ouve o textfield
 public void textValueChanged (TextEvent e) {
  try {
   setValorX();
   registraParFuncaoAbscissa(valorX);
   desenhaTangente();
  } catch(Exception ex) {
    if (igraf.IGraf.IS_DEBUG) System.err.println("igraf/moduloJanelasAuxiliares/visao/tangente/PainelTangente.java: textValueChanged("+e+"): " + e.toString());
   }
  }


 // Listener to choice and checkbox
 public void itemStateChanged (ItemEvent e) {
  Object source = e.getSource();

  if (source == animCheckBox) {
   animaTangente(animCheckBox.getState());
   //multiTangenteCheckBox.setEnabled(!animCheckBox.getState());
   }

  if (source == choice.getChoice()) {
   setFuncaoAtual(choice.getSelectedItem());
   apagaTangente();
   }

  if (source == multiTangenteCheckBox) {
   variasTangentes = !variasTangentes;
   apagaTangente();
   }
  entradaX.requestFocus();
  }

 /**
  * Atribui o valor digitado pelo usuário à variável de controle do desenho
  */
 private void setValorX () {
  try {
   valorX = Double.valueOf(entradaX.getText()).doubleValue();
   habilitarAnimacao(true); 
  } catch (Exception e) {
   habilitarAnimacao(false);
   throw new IllegalArgumentException(ResourceReader.msg("digValX"));
   }
  }

 private void setFuncaoAtual (String funcao) {
  funcaoAtual = funcao;
  }

 // Listener to keyboard directions
 public void keyPressed (KeyEvent e) {
  int keyCode = e.getKeyCode();

  if (keyCode == ESQUERDA || keyCode == BAIXO) // esquerda - baixo
   entradaX.setText(Utilitarios.precisao(valorX -= DELTA));

  if (keyCode == DIREITA || keyCode == CIMA) // direita - cima
    entradaX.setText(Utilitarios.precisao(valorX += DELTA));
  }

 private void habilitarAnimacao(boolean b) {
  animCheckBox.setEnabled(b);
  }

 // parece altamente dispensável...
 public void close () {
  registraEstadoTangentes();
  }

 public void keyReleased(KeyEvent e) { }
 public void keyTyped(KeyEvent e) { }

 public Choice getChoice () {
  return choice.getChoice();
  }

 public void focusGained (FocusEvent e) {
  funcaoAtual = choice.getSelectedItem();
  }

 public void focusLost (FocusEvent e) { }

 public void atualizaLabels (DesenhoTangenteEvent dte) {
  equationLabel.setText("y = " + dte.getEquacaoReta());
  fx.setText(dte.getFx());

  if (animando)
   entradaX.setText(dte.getValorX());
  }

 public void updateLabels () {
  plotButton.setLabel(ResourceReader.msg("btFxTg"));
  eraseButton.setLabel(ResourceReader.msg("btErTg"));
  multiTangenteCheckBox.setLabel(ResourceReader.msg("cbVarTg"));
  animCheckBox.setLabel(ResourceReader.msg("cbAnimTg"));
  choice.setLabel(ResourceReader.msg("chSelFun"));
  erro.setText(ResourceReader.msg("lbEqReta"));
  }

 }
