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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio.visao.resposta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;


public class DiscursiveAnswer extends Answer {

 private static final Color
   backColorTitleTextArea = EsquemaVisual.corBackground,
   backColorPanels = EsquemaVisual.corBackPanel;

 private static final int
   WIDTH_WINDOW = 420, HEIGHT_WINDOW = 95,
   HEIGHT_TAREA = 85;

 private JTextArea area;
 private JScrollPane jScrollPane1;
 private JLabel title;

 public DiscursiveAnswer (String ans) {
  initComponents();
  title.setText(ResourceReader.msg("msgExercDA")); // Resposta discursiva
  area.setText(ans);
  area.setEnabled(false);
  }

 public String resposta () {
  return area.getText();
  }

 private void initComponents () {
  this.setLayout(new BorderLayout());
  this.setBackground(backColorPanels);

  jScrollPane1 = new JScrollPane();
  area = new JTextArea();
  title = new JLabel();

  jScrollPane1.setBackground(backColorPanels);
  area.setBackground(backColorTitleTextArea);
  title.setBackground(backColorPanels);

  setPreferredSize(new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW));
  area.setMinimumSize(new Dimension(WIDTH_WINDOW, HEIGHT_TAREA));

  area.setDisabledTextColor(Color.black);
  jScrollPane1.setViewportView(area);

  add(jScrollPane1, BorderLayout.CENTER);

  title.setText("jLabel1");
  add(title, BorderLayout.PAGE_START);
  }

 }
