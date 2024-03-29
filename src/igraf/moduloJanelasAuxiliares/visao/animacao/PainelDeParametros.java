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

package igraf.moduloJanelasAuxiliares.visao.animacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;

import igraf.basico.util.EsquemaVisual;
import igraf.basico.io.ResourceReader;
import igraf.moduloJanelasAuxiliares.controle.JanelaParametrosController;
import difusor.i18N.LanguageUpdatable;


public class PainelDeParametros extends JPanel implements LanguageUpdatable {

 public static final int
    WIDTH = JanelaParametros.WIDTH-10, HEIGHT = JanelaParametros.HEIGHT; // this panel size

 public static final Color
    bgColor = EsquemaVisual.corBackground,       // backgound color to this window
    bgParam = EsquemaVisual.corBackPanel;

 public static final Font labelFont = new Font("Tahoma", Font.PLAIN, 11);


 private Vector listaParam;
 // private boolean b = false;

 private IntervaloParametro paramA = new IntervaloParametro('a', true, 0);
 private IntervaloParametro paramB = new IntervaloParametro('b', false, 1);
 private IntervaloParametro paramC = new IntervaloParametro('c', false, 2);
 private IntervaloParametro paramK = new IntervaloParametro('k', false, 3);
 private IntervaloParametro paramM = new IntervaloParametro('m', false, 4);
 private IntervaloParametro paramN = new IntervaloParametro('n', false, 5);

 private JPanel bottonPanel, centerPanel, topPanel;
 private JLabel labelTopLeft, labelTopRight;
 private JLabel labelMidTitle, labelMidCteD, labelMidCteE, labelMidCteP;


 private void addLabel (JPanel panel, JLabel label, String side) {
  panel.add(label, side);
  label.setBackground(bgColor);
  label.setForeground(Color.black);
  label.setFont(labelFont);
  }

 private static JLabel addLabel (JPanel panel, String strText) {
  JLabel label =  new JLabel(strText);
  panel.add(label);
  label.setBackground(bgColor);
  label.setForeground(Color.black);
  label.setFont(labelFont);
  return label;
  }


 protected PainelDeParametros (JanelaParametrosController jpc) {
  setLayout(new BorderLayout());

  configureCenter();
  configureBotton();
  configureTop();

  paramA.insereObservador(jpc);
  paramB.insereObservador(jpc);
  paramC.insereObservador(jpc);
  paramK.insereObservador(jpc);
  paramM.insereObservador(jpc);
  paramN.insereObservador(jpc);

  }


 // Top panel
 private void configureTop () {
  topPanel = new JPanel();
  // topPanel.setPreferredSize(new Dimension(0, 30));
  topPanel.setLayout(new BorderLayout());
  topPanel.setBackground(bgColor);
  labelTopLeft = new JLabel(ResourceReader.msg("labParam"));
  labelTopRight = new JLabel(ResourceReader.msg("labInterv"));
  addLabel(topPanel, labelTopLeft, BorderLayout.WEST);
  addLabel(topPanel, labelTopRight, BorderLayout.EAST);
  this.add(topPanel, BorderLayout.NORTH);
  }


 // Center panel
 private void configureCenter () {
  centerPanel = new JPanel(new GridLayout(6,1));

  centerPanel.add(paramA);
  centerPanel.add(paramB);
  centerPanel.add(paramC);
  centerPanel.add(paramK);
  centerPanel.add(paramM);
  centerPanel.add(paramN);

  centerPanel.setBackground(bgParam);
  this.add(centerPanel, BorderLayout.CENTER);
  }


 // Botton panel
 private void configureBotton () {
  bottonPanel = new JPanel(new GridLayout(4,1));

  labelMidTitle = addLabel(bottonPanel, ResourceReader.msg("labConst"));
  labelMidCteD = addLabel(bottonPanel, "D = pi / 180");
  labelMidCteE = addLabel(bottonPanel, "E = 2.7183");
  labelMidCteP = addLabel(bottonPanel, "P = 3.1415");

  bottonPanel.setBackground(bgColor);
  this.add(bottonPanel, BorderLayout.SOUTH);
  }


 public Dimension getPreferredSize () {
  return new Dimension(WIDTH, HEIGHT);
  }


 public void updateLabels () {

  labelTopLeft.setText(ResourceReader.msg("labParam"));
  labelTopRight.setText(ResourceReader.msg("labInterv"));
  // 
  labelTopLeft.setText(ResourceReader.msg("labParam"));
  labelTopRight.setText(ResourceReader.msg("labInterv"));
  labelMidTitle.setText(ResourceReader.msg("labConst"));
  bottonPanel.repaint();
  }

 }
