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

package igraf.moduloJanelasAuxiliares.visao.animacao;

import igraf.basico.util.EsquemaVisual;
import igraf.moduloJanelasAuxiliares.controle.JanelaParametrosController;
import igraf.moduloJanelasAuxiliares.eventos.AtualizaParametroEvent;
import igraf.moduloSuperior.controle.entrada.Analisa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox; //java.awt.Checkbox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class IntervaloParametro extends JPanel implements ItemListener, KeyListener {

 public static final Color
    bgColor = EsquemaVisual.corBackground,       // backgound color to this window
    bgParam = EsquemaVisual.corBackPanel,
    frColor = Color.black;

 private static final Font labelFont = new Font("Tahoma", Font.PLAIN, 11); // Helvetica

 public JTextField tfIni, tfFim;
 private double ini = -1, fim = 1;
 private boolean checked;
 //x private boolean isEnabledPanel;
 private JCheckBox cb;
 private JLabel labelItem, labelPts;
 private char charParameter;
 private int index = 0;

 private boolean enviarValorFim = true, enviarValorIni = true;

 private JanelaParametrosController jpc;


 private static JLabel createLabel (JPanel panel, String str) {
  JLabel label = new JLabel(str);
  label.setBackground(bgColor);
  label.setForeground(Color.black);
  label.setFont(labelFont);
  return label;
  }

 private static JTextField createTextField (IntervaloParametro panel, String str) {
  JTextField textField = new JTextField(str);
  textField.setBackground(bgColor);
  textField.setForeground(Color.black);
  textField.setFont(labelFont);
  textField.addKeyListener(panel);
  return textField;
  }


 protected IntervaloParametro (char chr, boolean bool0, int index) {
  this(chr, -1, 1, bool0);
  this.index = index;
  this.setBackground(bgColor);
  }

 protected IntervaloParametro (char chr, double ini, double fim, boolean bool0) {
  // System.out.println("IntervaloParametro.java: " + chr + ": " + ini + " - " + fim);
  this.charParameter = chr; // parameter name: a, b, c, k, m, n
  //x this.isEnabledPanel = bool0;
  this.ini = ini;
  this.fim = fim;

  this.setLayout(new GridLayout(1,5));

  cb = new JCheckBox();
  cb.setBackground(bgColor);
  cb.addItemListener(this);
  this.add(cb);

  labelItem = createLabel(this, String.valueOf(charParameter));
  this.add(labelItem);

  tfIni = createTextField(this, String.valueOf(ini));
  this.add(tfIni);

  labelPts = createLabel(this, " .. ");
  this.add(labelPts);

  tfFim = createTextField(this,String.valueOf(fim));
  this.add(tfFim);

  enablePanel(bool0);
  
  this.setBackground(bgColor);

  // javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED);
  // this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
  // this.setBorder(BorderFactory.createLineBorder(Color.black),1,1,0,0);
  this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.lightGray)); // (int type, Color highlight, Color shadow)

  }


 private void enablePanel () {
  tfIni.setEnabled(checked); // true => can edit initial value of this parameter
  tfFim.setEnabled(checked); // true => can edit final value of this parameter
  checked = !checked;
  }

 // Change permision to edit parameter
 private void enablePanel (boolean boolToSet) {
  cb.setSelected(boolToSet);
  enablePanel();
  checked = !boolToSet;
  }

 public void itemStateChanged (ItemEvent e) {
  enablePanel();
  notificaAlteracao();
  }


 public double getFim () {
  return fim;
  }

 public void setFim () {
  //if (b) {
  try {
    String str = tfFim.getText();
    str = Analisa.verificaConstante(str);
    fim = Double.valueOf(str).doubleValue();
    enviarValorFim = true;
  } catch (Exception e) { enviarValorFim = false; }
  // }
  }

 public double getIni () {
  return ini;
  }

 public void setIni () {
  String str = "";
  try {
   str = tfIni.getText();
   str = Analisa.verificaConstante(str);
   ini = Double.valueOf(str).doubleValue();
   enviarValorIni = true;
  } catch (Exception e) { enviarValorIni = false; }
  }

 public boolean isChecked () {
  return checked;
  }


 // From: 'PainelDeParametros.java'
 void insereObservador (JanelaParametrosController jpc) {
  this.jpc = jpc;
  }


 public int getIndex () {
  return index;
  }

 public void keyReleased (KeyEvent e) {
  notificaAlteracao();
  }

 private void notificaAlteracao () {
  setIni();
  setFim();
  if (enviarValorIni && enviarValorFim && jpc!=null) { // when instantiate => jpc is null
   jpc.enviaAtualizacaoParametro(new AtualizaParametroEvent(this));
   }
  }

 public void keyPressed (KeyEvent e) { }
 public void keyTyped (KeyEvent e) { }

 public boolean getState () {
  return cb.isSelected();
  }

 }
