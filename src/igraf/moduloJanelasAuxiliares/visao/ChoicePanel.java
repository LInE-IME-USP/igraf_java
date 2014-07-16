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
 * @description Draws animation to functions with parameters
 * 
 * @see igraf/moduloJanelasAuxiliares/visao/integral/PainelIntegral.java
 * @see igraf/moduloJanelasAuxiliares/visao/tangente/PainelTangente.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */


package igraf.moduloJanelasAuxiliares.visao;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import igraf.IGraf;

public class ChoicePanel extends JPanel {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "src/igraf/moduloJanelasAuxiliares/visao/ChoicePanel.java";

 private Dimension dimension = new Dimension(192, 60);

 private Color corLetra = Color.black;
 private boolean clicked;

 private String strChoice;
 private Choice choiceItem;

 // Attention: the choice item are not inserted here
 // See: igraf/moduloJanelasAuxiliares/controle/JanelaIntegralController.java: estende 'JanelaController'
 //      - atualizaListas(): preencheChoiceFuncoes(janelaIntegral.getChoiceUm()); preencheChoiceFuncoes(janelaIntegral.getChoiceDois());
 //      igraf/moduloJanelasAuxiliares/controle/JanelaController.java: preencheChoiceFuncoes():
 public ChoicePanel (String str) {
  this.strChoice = str;
  //TSystem.out.println("\nsrc/igraf/moduloJanelasAuxiliares/visao/ChoicePanel.java: stringm=" + str);
  //IGraf.launchStackTrace();

  choiceItem = new Choice();
  setLayout(new BorderLayout());
  choiceItem.setForeground(corLetra);
  add(choiceItem, BorderLayout.SOUTH);
  }

 public boolean contemFuncao (String funcao) {
  for (int i = 0; i < choiceItem.getItemCount(); i++) {
   if (choiceItem.getItem(i).equals(funcao))
    return true;
   }
  return false;
  }

 public Choice getChoice () {
  return choiceItem;
  }

 //System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": Error:");
 public void addItemListener (ItemListener i) {
  choiceItem.addItemListener(i);
  }

 public Dimension getPreferredSize () {
  return dimension;
  }

 public void addItem (String item) {
  choiceItem.add(item);
  }

 public void limpaLista () {
  choiceItem.removeAll();
  }

 public String getItem (int index) {
  return choiceItem.getItem(index);
  }

 public String getSelectedItem () {
  return choiceItem.getSelectedItem();
  }

 public int getSelectedIndex () {
  return choiceItem.getSelectedIndex();
  }

 public void setName (String name) {
  choiceItem.setName(name);
  }

 public void setLabel (String label) {
  strChoice = label;
  repaint();
  }

 public int getItemCount () {
  return choiceItem.getItemCount();
  }

 public void select (int item) {
  try {
   //T
System.out.println("\nsrc/igraf/moduloJanelasAuxiliares/visao/ChoicePanel.java: select("+item+"): #choiceItem=" + choiceItem.size());
   choiceItem.select(item);
   } catch (RuntimeException e) {
   // trata o erro gerado ao iniciar painel integral que tenta selecionar
   // uma posição em uma lista vazia
   }
  }

 public void paint (Graphics g) {
  g.setColor(Color.white);
  g.fillRect(0, 0, getSize().width-1, getSize().height-1);
  g.setColor(Color.blue);
  g.drawString(strChoice, 10, 20);
  g.setColor(Color.black);
  g.drawRect(0, 0, getSize().width-1, getSize().height-1);
  }

 public Insets getInsets () {
  return new Insets(0,5,5,4);
  }

 }
