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
 * @description Window to select function with parameters. It allow or block the animation of any specific function.
 *
 * @see igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java: tratarEventoRecebido(...)
 * @see igraf/moduloJanelasAuxiliares/visao/animacao/LabelAnimacao.java: each item in animation window, it has a one-2-one correspondence with functions with parameters
 * @see igraf/moduloCentral/visao/desenho/DesenhoAnimacao.java: mudaEstadoAnimacao(boolean): change the effective state considering animation of a function
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloJanelasAuxiliares.controle.JanelaAnimacaoController;
import igraf.moduloCentral.visao.desenho.DesenhoAnimacao;

import difusor.i18N.LanguageUpdatable;


public class JanelaAnimacao extends JPanel implements LanguageUpdatable {

 public static final Color
    bgColor = EsquemaVisual.corBackground; // backgound color to this window
 public static final Font
    fontHB11 = EsquemaVisual.fontHB11,     // font to the labels
    fontHP11 = EsquemaVisual.fontHP11;

 private JPanel superiorPanel, centralPanel;
 private JLabel labelMessage1, labelMessage2;
 private LabelAnimacao  a0, a1, a2, a3, a4, a5, a6, a7, a8, a9;
 private LabelAnimacao [] listOfFunctions = { a0, a1, a2, a3, a4, a5, a6, a7, a8, a9 };
 private JFrame frame;

 private JanelaAnimacaoController janelaAnimacaoController = null;


 public JanelaAnimacao (JanelaAnimacaoController jac) {

  // igraf.moduloCentral.visao.desenho.DesenhoAnimacao.mudaEstadoAnimacao(DesenhoAnimacao.java:174)
  //D try{String str="";System.out.print(str.charAt(3));}catch(Exception e) {e.printStackTrace();}

  this.janelaAnimacaoController = jac; // to allow access to ???

  superiorPanel = new JPanel(new GridLayout(2,1));
  centralPanel = new JPanel();
  // Select function with parameters to produce animation / You must activate animation with option: Start/stop animation
  labelMessage1 = new JLabel(ResourceReader.msg("labJanAnimExpl1"));
  labelMessage2 = new JLabel(ResourceReader.msg("labJanAnimExpl2") + ": " + ResourceReader.msg("maParaAnima"));

  this.setBackground(bgColor);
  superiorPanel.setBackground(bgColor);
  centralPanel.setBackground(bgColor);
  labelMessage1.setBackground(bgColor);
  labelMessage1.setFont(fontHB11);
  labelMessage2.setBackground(bgColor);
  labelMessage2.setFont(fontHP11);

  this.setLayout(new java.awt.BorderLayout());
  centralPanel.setLayout(new GridLayout(5,2,0,5));

  superiorPanel.add(labelMessage1);
  superiorPanel.add(labelMessage2);

  this.add("North", superiorPanel);
  this.add("Center", centralPanel);

  for (int i = 0; i < listOfFunctions.length; i++) {
   LabelAnimacao la = new LabelAnimacao(i, "A"+ (i+1) + " (x)", jac);
   //r la.setIndex(i);
   listOfFunctions[i] = la;
   centralPanel.add(listOfFunctions[i]);
   }

  frame = new JFrame("iGraf - " + ResourceReader.msg("labJanAnimTit"));
  frame.getContentPane().add(this);
  frame.pack();
  frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
    frame.setVisible(false);
    frame.dispose();
    }
   });
  frame.setVisible(true);
  }


 public void setVisible (boolean visibility) {
  frame.setVisible(visibility);
  }

 public Dimension getPreferredSize () {
  return new Dimension(450, 18*listOfFunctions.length);
  }

 public LabelAnimacao [] getListaLabel () {
  return listOfFunctions;
  }

 public void resetLabelList () {
  for (int i = 0; i < listOfFunctions.length; i++) {
   listOfFunctions[i].resetText();
   }
  }


 // Set expression describing the function
 // @see: igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java: loadLabelList(): it build the list of registered functions
 public void setLabel (String expr, int index) {
  // Get the correspondent 'igraf/moduloCentral/visao/desenho/DesenhoAnimacao.java' from 'igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java'
  DesenhoAnimacao desenhoAnimacao = janelaAnimacaoController.getDesenhoAnimacao(index);
  listOfFunctions[index].setDesenhoAnimacao(desenhoAnimacao, expr); // set a function
  }

 public void updateLabels () {
  frame.setTitle(ResourceReader.msg("labJanAnimTit"));
  labelMessage1.setText(ResourceReader.msg("labJanAnimExpl1"));
  labelMessage2.setText(ResourceReader.msg("labJanAnimExpl2") + ": " + ResourceReader.msg("maParaAnima"));
  }

 }
