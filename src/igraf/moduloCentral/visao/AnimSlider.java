/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Manual integrated to iGraf.
 * Classe que define o comportamento b<E1>sico de todos os objetos criados para exibir o texto da ajuda.
 * 
 * @see 
 * 
 */

package igraf.moduloCentral.visao;

import igraf.basico.util.Configuracoes;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.controle.AnimSliderController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;


public class AnimSlider extends Panel implements AdjustmentListener {

 private int ini = -100, fim = 101;
 Scrollbar scroll;
 private TextField tfAtual;
 double precisao = 100;
 double multiplicador = 100;

 BorderLayout bl = new BorderLayout();
 private Panel aux;
 private Label label;

 static final java.awt.Font fontHB12 = EsquemaVisual.fontHB12; 
 private Dimension d = new Dimension(Configuracoes.lTFP, 25);

 public AnimSlider (AnimSliderController asc) {  
  aux = new Panel(new BorderLayout());
  label = new Label(" a = ");

  setBounds(0, 435, d.width, d.height);
  
  setLayout(bl);
  configureScroll(asc);
  configureTextField();
  label.setFont(fontHB12);
  aux.add(label, BorderLayout.WEST);
  add(aux, BorderLayout.EAST);
  asc.setControlledObject(this);
  }

 private void configureScroll (AnimSliderController asc) {
  scroll = new Scrollbar(Scrollbar.HORIZONTAL, 0, 0, ini, fim );
  scroll.addAdjustmentListener(this);
  scroll.addAdjustmentListener(asc);
  add(scroll);
  }

 private void configureTextField () {
  tfAtual = new TextField() {
   public Dimension getPreferredSize() { return new Dimension(80, 0);  }
   };
  tfAtual.setForeground(Color.black);
  tfAtual.setEditable(false);
  
  setValue(ini);
  aux.add(tfAtual);
  }

 public Dimension getPreferredSize () {
  return new Dimension(0, 20);
  }

 public double getValue () {
  return scroll.getValue()/precisao;
  }

 public void setValue (double num) {
  tfAtual.setText(String.valueOf(num/precisao));
  }
 
 public void setBublePosition (double num){
  double x = num*precisao;  
  int m = (int)x;
  
  scroll.setValue(m);
  setValue(x);  
  }

 public TextField getTf () {
  return tfAtual;
  }

 public Insets getInsets () {
  return new Insets(1,2,1,2);
  }

 public void adjustmentValueChanged (AdjustmentEvent e) {
  tfAtual.setText(String.valueOf(getValue()));
  }
 
 public void setBounds (Rectangle r) {
  setBounds(0, r.height-165, r.width, d.height);
  }

 }
