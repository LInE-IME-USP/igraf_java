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
 * @version Created on 31/05/2011, 10:02:21
 * 
 * @description 
 * 
 * @see igraf/moduloExercicio/visao/resposta/AnswerVisualizerFrame.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloExercicio.visao.resposta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import igraf.basico.util.EsquemaVisual;


public class ObjectiveAnswer extends Answer {

 private static final Color
   backColorPanels = EsquemaVisual.corBackPanel,
   borderColor = Color.lightGray;

 private static final Font baseFont = new Font("Tahoma", 0, 11);

 private JLabel itemLabel;
 private JPanel jPanel1;
 private JLabel profLabel;
 private JLabel statusLabel;
 private JLabel studentLabel;
 

 // Creates new form Answer
 public ObjectiveAnswer (int item, String studentAns, String profAns, String status) {
  initComponents();
  itemLabel.setText(" Item " + String.valueOf(item));
  studentLabel.setText(studentAns);
  profLabel.setText(profAns);
  statusLabel.setText(status);
  }
 
 public String resposta () {
  return studentLabel.getText();
  }

 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 private void initComponents () {
  itemLabel = new JLabel();
  statusLabel = new JLabel();
  jPanel1 = new JPanel();
  studentLabel = new JLabel();
  profLabel = new JLabel();

  this.setBorder(BorderFactory.createLineBorder(borderColor));
  //setPreferredSize(new Dimension(520, 22));
  this.setLayout(new BorderLayout(2, 0));
  itemLabel.setFont(baseFont);
  itemLabel.setMinimumSize(new Dimension(50, 14));
  itemLabel.setPreferredSize(new Dimension(50, 14));
  itemLabel.setVerifyInputWhenFocusTarget(false);
  this.add(itemLabel, BorderLayout.LINE_START);

  statusLabel.setFont(baseFont);
  this.add(statusLabel, BorderLayout.EAST);

  jPanel1.setPreferredSize(new Dimension(250, 20));
  jPanel1.setLayout(new GridLayout(1, 3, 2, 0));

  studentLabel.setFont(baseFont);
  jPanel1.add(studentLabel);

  profLabel.setFont(baseFont);
  jPanel1.add(profLabel);

  this.add(jPanel1, BorderLayout.CENTER);

  itemLabel.setBackground(backColorPanels);
  statusLabel.setBackground(backColorPanels);
  jPanel1.setBackground(backColorPanels);
  studentLabel.setBackground(backColorPanels);
  profLabel.setBackground(backColorPanels);
  this.setBackground(backColorPanels);
  }


 }
