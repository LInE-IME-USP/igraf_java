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
 * @version  intiatl in 04/07/2006
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

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import difusor.i18N.LanguageUpdatable;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.Utilitarios;
import igraf.moduloJanelasAuxiliares.controle.JanelaIntegralController;
import igraf.moduloSuperior.controle.entrada.Analisa;


public class JanelaIntegral extends Frame implements LanguageUpdatable {

 // This class has a reference to all functions in constructions
 // JanelaIntegralController.java: Vector listaDesenho, listaFuncao
 private JanelaIntegralController janelaIntegralController;

 private PainelIntegral painelIntegral;
 private PainelOpcoesIntegracao painelOpcoesIntegracao;
 private Panel painelAuxiliar;

 private double delta = 0.02, from = 0, to = 1;

 private final int
   ESQUERDA = 37,
   DIREITA = 39,
   BAIXO = 40,
   CIMA = 38;

 public JanelaIntegral (JanelaIntegralController janelaIntegralController) {
  super(ResourceReader.msg("janIntegTit"));

  this.janelaIntegralController = janelaIntegralController;

  painelIntegral = new PainelIntegral(janelaIntegralController);
  painelOpcoesIntegracao = new PainelOpcoesIntegracao(painelIntegral);
  setLayout(new GridLayout(1,2));

  painelAuxiliar = new Panel(new GridLayout(1,2)) {
   public Dimension getPreferredSize() { return new Dimension(396, 200); }
   };
  painelAuxiliar.add(painelIntegral);
  painelAuxiliar.add(painelOpcoesIntegracao);
  add(painelAuxiliar);
  pack();

  addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent arg0) {
    setVisible(false);
    dispose();
    }
   });
  setVisible(true);
  }


 public Choice getChoiceUm () {
  return painelIntegral.cp1.getChoice();
  }

 public Choice getChoiceDois () {
  return painelIntegral.cp2.getChoice();
  }

 // Ouvidor para as setas direcionais dos TextFields do painel integral
 public void keyPressed (KeyEvent e) {
  int keyCode = e.getKeyCode();

  TextField tf = (TextField)e.getSource();

  double value = getValue(tf);

  if (keyCode == ESQUERDA || keyCode == BAIXO)
   tf.setText(Utilitarios.precisao(value -= delta));

  if (keyCode == DIREITA || keyCode == CIMA)
   tf.setText(Utilitarios.precisao(value += delta));

  if (tf.getName().equals("inf")) from = value;
  if (tf.getName().equals("sup")) to   = value;
  }

 public double getValue (TextField tf) {
  String s = tf.getText();
  s = Analisa.verificaConstante(s);
  try {
   return new Double(s).doubleValue();
  } catch (NumberFormatException e) { return 0; }
  }

 public double getFrom () {
  return from;
  }

 public double getTo () {
  return to;
  }


 public void keyReleased (KeyEvent e) { }
 public void keyTyped (KeyEvent e) { }


 public void updateLabels () {
  setTitle(ResourceReader.msg("janIntegTit"));
  painelIntegral.updateLabels();
  painelOpcoesIntegracao.updateLabels();
  }

 }
