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
 * @version Created on 22/05/2011, 12:01:31
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
import java.awt.Component;
import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;

import difusor.i18N.LanguageUpdatable;

public class Attempt extends JPanel implements LanguageUpdatable {

 private static final Color
   backColorPanels = EsquemaVisual.corBackPanel; // corAcesa; // corDesFundoBotoes;

 private String msgTry = ResourceReader.msg("try");
 // private StringBuffer strBuffer = new StringBuffer();

 private JPanel discPane;
 private JPanel objPane;

 public Attempt (Vector answerList, String attemptNumber) {
  this.msgTry += attemptNumber; // keep the previous attempt

  initComponents();

  for (int ii_0=0; ii_0<answerList.size(); ii_0++) {
   Answer answer = (Answer) answerList.elementAt(ii_0);
   if (answer instanceof ObjectiveAnswer)
    objPane.add(answer);
   else
    discPane.add(answer);
   }        
  }



 private void initComponents () {
  objPane = new JPanel();
  discPane = new JPanel();

  objPane.setBackground(backColorPanels);
  discPane.setBackground(backColorPanels);

  setBorder(BorderFactory.createTitledBorder(msgTry));
  setLayout(new BorderLayout(0, 2));

  objPane.setLayout(new java.awt.GridLayout(0, 1, 0, 2));
  add(objPane, BorderLayout.CENTER);

  discPane.setLayout(new BorderLayout());
  add(discPane, BorderLayout.PAGE_END);

  this.setBackground(backColorPanels);

  }


 public void updateLabels () {
  this.msgTry = ResourceReader.msg("try");  
  }

 }
