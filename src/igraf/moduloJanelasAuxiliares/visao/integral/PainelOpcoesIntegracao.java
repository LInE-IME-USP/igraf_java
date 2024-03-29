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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloJanelasAuxiliares.visao.integral;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.Calculo;
import igraf.basico.util.Utilitarios;


public class PainelOpcoesIntegracao extends Panel implements ItemListener, ActionListener {

 private Checkbox colorModeCB, areaCB, integralCB;
 private CheckboxGroup cbg;
 
 private Color corLetra = Color.blue, corPositiva = new Color(180,100,255), corNegativa = new Color(255,100,180);

 private Label label03, label01, label02;
 private Button buttonIntegrates;

 private Dimension dimension = new Dimension(192, 180);
 private Panel painelVazio, painelFinal;
 
 private PainelIntegral painelIntegral;

 private String limiteInf, limiteSup, fSuperior, fInferior;

 private double intSuperior, intInferior, de, a;
 private String [] listOfAvailableFunctions;

 public PainelOpcoesIntegracao (PainelIntegral pint) {
  setBackground(new Color (247, 247, 247));
  setLayout(new GridLayout(0, 1, 0, 4));

  this.painelIntegral = pint;

  configuraOpcoes();
  configuraResultados();
  }
 
 
 private void configuraResultados () {
  Panel painelResultados = new Panel(new GridLayout(0, 1, 0, 0)) {
   public void paint(Graphics g) {
    g.drawRect(2, 2, getSize().width-4, getSize().height-4);
    }
   public Insets getInsets() {
    return new Insets(4,6,3,4);
    }
   };
  buttonIntegrates = new Button(ResourceReader.msg("btInteg"));
  label01 = new Label();
  label02 = new Label();
  label03 = new Label();

  label01.setForeground(corPositiva);
  label03.setForeground(corLetra);

  buttonIntegrates.addActionListener(this);

  painelResultados.add(label01);
  painelResultados.add(label02);
  painelResultados.add(label03);
  painelResultados.add(buttonIntegrates);

  add(painelResultados);
  }
 
 
 private void configuraOpcoes () {
  Panel painelOpcoes = new Panel(new GridLayout(0, 1, 0, 0)) {
   public void paint(Graphics g) {
    g.drawRect(2, 2, getSize().width-4, getSize().height-4);
    }
   public Insets getInsets() {
    return new Insets(4,6,2,4);
    }
   };

  cbg = new CheckboxGroup();
  colorModeCB = new Checkbox(ResourceReader.msg("colCB"), true);
  integralCB = new Checkbox(ResourceReader.msg("intCB"), cbg, true);
  areaCB = new Checkbox(ResourceReader.msg("areCB"), cbg, false);

  colorModeCB.setForeground(corLetra);
  integralCB.setForeground(corLetra);
  areaCB.setForeground(corLetra);

  colorModeCB.addItemListener(this);
  integralCB.addItemListener(this);
  areaCB.addItemListener(this);

  painelOpcoes.add(colorModeCB);
  painelOpcoes.add(integralCB);
  painelOpcoes.add(areaCB);
  add(painelOpcoes);
  }


 public Insets getInsets () {
  return new Insets(3,2,2,4);
  }
 
 public Dimension getPreferredSize () {
  return dimension;
  }


 public void itemStateChanged (ItemEvent e) {
  Checkbox c = (Checkbox)e.getSource();

  if (c.equals(colorModeCB)) {
   if (colorModeCB.getState())
    painelIntegral.pintaRegiao(listOfAvailableFunctions, de, a, areaCB.getState());
   else
    painelIntegral.limpaRegiao();
   }
  else {
   if (c.equals(areaCB))
    setLabelArea();
   else
    setLabelIntegral();
   painelIntegral.mudaPinturaRegiao(areaCB.getState());
   }
  }

 private void setLabelArea () {
  label01.setText("");
  label02.setForeground(corLetra);
  label02.setText("   " + ResourceReader.msg("area") + "    = " + Utilitarios.precisao((intSuperior-intInferior)));
  label03.setText("");
  }

 private void setLabelIntegral () {
  label01.setText("     A1     = " + Utilitarios.precisao(intSuperior));
  label02.setForeground(corNegativa);
  label02.setText("     A2     = " + Utilitarios.precisao(-intInferior));
  label03.setText("A1 - A2 = " + Utilitarios.precisao((intSuperior+intInferior)));
  }


 public void actionPerformed (ActionEvent e) {
  Object source = e.getSource();
  if (source == buttonIntegrates)
   integrar();
  }


 public void integrar () {
  intSuperior = intInferior = 0;
  listOfAvailableFunctions = painelIntegral.getListaFuncoesSelecionadas();

  de = painelIntegral.getFrom();
  a  = painelIntegral.getTo();

  if (listOfAvailableFunctions[0] == null | 
     listOfAvailableFunctions[0].length() < 1)
   return;

  int k = listOfAvailableFunctions[0].indexOf('"');
  if (k > -1)
   listOfAvailableFunctions[0] = listOfAvailableFunctions[0].substring(0, k); 

  Calculo.integralDefinida(listOfAvailableFunctions[0], de, a);  
  intSuperior += Calculo.getPartePositivaIntegral();
  intInferior += Calculo.getParteNegativaIntegral();

  k = listOfAvailableFunctions[1].indexOf('"');
  if (k > -1)
   listOfAvailableFunctions[1] = listOfAvailableFunctions[1].substring(0, k);

  Calculo.integralDefinida(listOfAvailableFunctions[1], de, a);
  intSuperior += Calculo.getPartePositivaIntegral();
  intInferior += Calculo.getParteNegativaIntegral();

  if (colorModeCB.getState()) {
   boolean pintaArea = areaCB.getState();
   painelIntegral.pintaRegiao(listOfAvailableFunctions, de, a, pintaArea);

   if (pintaArea)
    setLabelArea();
   else
    setLabelIntegral();
   }
  }


 public void updateLabels () {
  buttonIntegrates.setLabel(ResourceReader.msg("btInteg"));
  colorModeCB.setLabel(ResourceReader.msg("colCB"));
  integralCB.setLabel(ResourceReader.msg("intCB"));
  areaCB.setLabel(ResourceReader.msg("areCB"));
  }

 }
