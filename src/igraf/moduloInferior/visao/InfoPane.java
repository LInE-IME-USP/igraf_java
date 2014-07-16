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
 * @description Status bar. Is is a panel to be used at bottom of iGraf to present status messages
 * 
 * @see igraf/moduloInferior/controle/InfoPaneController.java: controller that change messages in the status bar
 * @see igraf/moduloCentral/visao/plotter/GraphPlotter.java: it launches some status messages (like edition of expression)
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloInferior.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import igraf.basico.util.Configuracoes;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloInferior.controle.InfoPaneController;


public class InfoPane extends JPanel {

 private static int larg = Configuracoes.lTFP;
 
 private JLabel jLabelStatusBar = new JLabel();

 public InfoPane (InfoPaneController ipc) {
  ipc.setControlledObject(this);
  
  setPreferredSize(new Dimension(larg, 22));
  setLayout(new BorderLayout());
  
  jLabelStatusBar.setBackground(EsquemaVisual.corBarraSupInf);
  jLabelStatusBar.setForeground(Color.white);
  jLabelStatusBar.setFont(EsquemaVisual.fontHP10);
  jLabelStatusBar.setText(EsquemaVisual.msgBarra);
  jLabelStatusBar.setOpaque(true);
  add(jLabelStatusBar);
  }

 public void setStatusBarMessage (String strMessage) {
  jLabelStatusBar.setText(strMessage);  
  }

 public void setDefaultInfo () {
  jLabelStatusBar.setText(EsquemaVisual.msgBarra);
  }

 }
