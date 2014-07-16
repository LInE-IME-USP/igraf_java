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
 * @description janela para edição de textos
 *
 * @see igraf/moduloCentral/visao/desenho/DesenhoAnimacao.java: has all the functions with parameters
 * @see igraf/moduloJanelasAuxiliares/visao/animacao/JanelaAnimacao.java: open this text edition window: LabelAnimacao la = new LabelAnimacao("A"+ (i+1) + " (x)", jac);
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

import igraf.moduloCentral.eventos.menu.IgrafMenuAnimacaoEvent;
import igraf.moduloJanelasAuxiliares.controle.JanelaAnimacaoController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox; // Checkbox
import javax.swing.JLabel;
import javax.swing.JPanel;

import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.visao.desenho.DesenhoAnimacao;


public class LabelAnimacao extends JPanel implements ItemListener {

 private static final int CHKW = 20, CHKH = 20;
 private static final Color
    bgColor = EsquemaVisual.corBackground, // backgound color to this window
    corNotAnimation = Color.gray;          // color to functions that are selected to hava "not anamation"

 public static final Font
    fontHB11 = EsquemaVisual.fontHB11;     // font to the labels

 private JCheckBox chkBox = null;
 private JLabel labelToFunc;
 
 private  boolean checked = true;
 private boolean apagado;
 private int index;
 private Color corFuncao;
 private String strNameFunction;

 private JanelaAnimacaoController janelaAnimacaoController;
 private DesenhoAnimacao desenhoAnimacao; // to access animation of each function with parameters

 public LabelAnimacao (int index, String text, JanelaAnimacaoController jac) {
  // It must corresponds to its index in 'igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java': Vector listOfDesenhoAnimacao with 'igraf.moduloCentral.visao.desenho.DesenhoAnimacao'
  this.index = index;
  this.strNameFunction = text;
  this.janelaAnimacaoController = jac;
  createPanel(text);
  }

 private void createPanel (String text) {
  this.setLayout(new BorderLayout());

  chkBox = new JCheckBox("", true);
  chkBox.setName(String.valueOf(index));

  chkBox.setHorizontalAlignment(JLabel.LEFT);
  chkBox.addItemListener(this);

  labelToFunc = new JLabel(text);
  labelToFunc.setBackground(bgColor);
  labelToFunc.setHorizontalAlignment(JLabel.LEFT);
  labelToFunc.setFont(fontHB11);

  chkBox.setBackground(bgColor);
  this.setBackground(bgColor);
  this.add("West", chkBox);
  this.add("Center", labelToFunc);
  this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.lightGray)); // (int type, Color highlight, Color shadow)

  hasFunction(false); // default: not enabled (it can be changed by 'JanelaAnimacao.setLabel(String,int)' after 'JanelaAnimacaoController.loadLabelList()')

  }


 public void setCorFuncao (Color cor) {
  corFuncao = cor;
  labelToFunc.setForeground(cor);
  }

 protected void resetText () {
  labelToFunc.setText("");
  }


 // Set expression describing the function
 // From: igraf.moduloJanelasAuxiliares.visao.animacao.JanelaAnimacao.setLabel(JanelaAnimacao.java:120)
 public void setDesenhoAnimacao (DesenhoAnimacao desenhoAnimacao, String funcao) {
  this.desenhoAnimacao = desenhoAnimacao;
  labelToFunc.setText("");
  labelToFunc.setText(strNameFunction + " = " + funcao);
  hasFunction(true); // enable this option: it now has a function
  }
 
 public String getText () {
  if (labelToFunc==null || labelToFunc.getText()==null)
   return null;
  String strText = labelToFunc.getText();
  if (strText.length()<9)
   return strText;
  return labelToFunc.getText().substring(9);
  }
 
 public boolean isChecked () {
  return checked;
  }


 // Highlight functions that are marked (enabled to to animation)
 private void changeCheckedState () {
  checked = !checked;
  apagado = !apagado;
  if (apagado) {
   labelToFunc.setForeground(corFuncao);
   }
  else {
   labelToFunc.setForeground(corNotAnimation);
   }
  }


 // By default: set labels with no function as "not enabled"
 // From 'JanelaAnimacao.setLabel(String,int)' it can change this to "enabled" whenever there is a function associated
 private void hasFunction (boolean isEnabled) {
  //T System.out.println("LabelAnimacao.java: ("+index+","+getText()+"): " + isEnabled);
  apagado = isEnabled;
  if (apagado) {
   labelToFunc.setForeground(corFuncao);
   }
  else {
   labelToFunc.setForeground(Color.lightGray);
   }
  checked = isEnabled;
  chkBox.setEnabled(isEnabled);
  }


 public void itemStateChanged (ItemEvent e) {
  changeCheckedState();
  desenhoAnimacao.animate(checked);
  //TODO: Eficiencia: eliminado lancamento de evento e agora faz diretamente a manipulacao de 'igraf.moduloCentral.visao.desenho.DesenhoAnimacao.mudaEstadoAnimacao(boolean)'
  //TODO: IgrafMenuAnimacaoEvent imae = new IgrafMenuAnimacaoEvent(this, getText()); 
  //TODO: janelaAnimacaoController.enviarEvento(imae);
  // @see igraf/moduloCentral/visao/desenho/DesenhoAnimacao.java: has all the functions with parameters
  
  }

 public void resetLabel () {
  labelToFunc.setText(strNameFunction);  
  }


 }
