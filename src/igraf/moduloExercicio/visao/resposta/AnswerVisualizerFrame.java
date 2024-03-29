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
 * @version Created on 22/05/2011, 12:14:25
 * 
 * @description Window to the student see his answer or to the teacher see the student's answer
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

package igraf.moduloExercicio.visao.resposta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import difusor.i18N.LanguageUpdatable;


public class AnswerVisualizerFrame extends JFrame implements LanguageUpdatable {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloExercicio/visao/resposta/AnswerVisualizerFrame.java";
 //D try { String srtA=""; System.out.print(srtA.charAt(3));  } catch(Exception e) { e.printStackTrace();  }

 private static final int
   WIDTH_MIN = 500, HEIGHT_MIN = 200,
   WIDTH_MAX = 570, HEIGHT_MAX = 500;
 private static final Color
   backColorTitleTextArea = EsquemaVisual.corBackground;

 private JPanel generalPanel;
 private JLabel answerLabel;
 private JLabel gabLabel;
 private JScrollPane jScrollPane1;
 private JPanel labelPanel;
 private JLabel statusLabel;

 public AnswerVisualizerFrame (Vector attemptList) {
  int ii_0;
  initComponents();
  setTitle("iGraf - " + ResourceReader.msg("janVisTentTitulo")); // Visualizer to the student's answers

  for (ii_0 = 0; ii_0<attemptList.size(); ii_0++) {
   Attempt attempt = (Attempt)attemptList.get(ii_0);
   generalPanel.add(attempt);
   }

  if (ii_0 > 2)
   this.setSize(WIDTH_MAX, HEIGHT_MAX); 
  else {
   this.setSize(new Dimension(WIDTH_MIN, HEIGHT_MIN)); // setPreferredSize
   // this.pack(); - better to ensure a minimal size
   }
  // System.out.println("AnswerVisualizerFrame.java: ii_0=" + ii_0);

  setVisible(true);
  setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }


 //
 private void initComponents () {
  jScrollPane1 = new JScrollPane();
  generalPanel = new JPanel();
  labelPanel = new JPanel();
  answerLabel = new JLabel();
  gabLabel = new JLabel();
  statusLabel = new JLabel();

  setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

  jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  jScrollPane1.setMaximumSize(new Dimension(320, 400));
  jScrollPane1.setMinimumSize(new Dimension(320, 40));
  jScrollPane1.setBackground(backColorTitleTextArea);

  generalPanel.setLayout(new GridLayout(0, 1, 3, 10));
  jScrollPane1.setViewportView(generalPanel);
  generalPanel.setBackground(backColorTitleTextArea);

  getContentPane().add(jScrollPane1, BorderLayout.CENTER);  

  labelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
  labelPanel.setLayout(new BorderLayout());
  labelPanel.setBackground(backColorTitleTextArea);

  answerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
  answerLabel.setText(ResourceReader.msg("aluTent")); // Student answer
  answerLabel.setPreferredSize(new Dimension(110, 14));
  answerLabel.setBackground(backColorTitleTextArea);
  labelPanel.add(answerLabel, BorderLayout.WEST);

  gabLabel.setHorizontalAlignment(SwingConstants.CENTER);
  gabLabel.setText(ResourceReader.msg("respEsper")); // Answer model
  gabLabel.setBackground(backColorTitleTextArea);
  labelPanel.add(gabLabel, BorderLayout.CENTER);

  statusLabel.setText(ResourceReader.msg("status")); // Status
  statusLabel.setPreferredSize(new Dimension(90, 20));
  statusLabel.setRequestFocusEnabled(false);
  statusLabel.setBackground(backColorTitleTextArea);
  labelPanel.add(statusLabel, BorderLayout.EAST);
  statusLabel.getAccessibleContext().setAccessibleName("null");

  getContentPane().add(labelPanel, BorderLayout.NORTH);
  getContentPane().setBackground(backColorTitleTextArea);
  }


 public void updateLabels () {
  setTitle("iGraf - " + ResourceReader.msg("janVisTentTitulo"));
  gabLabel.setText(ResourceReader.msg("respEsper"));
  statusLabel.setText(ResourceReader.msg("status"));
  answerLabel.setText(ResourceReader.msg("aluTent"));
  
  Object[] attemptList = getComponents();
  for (int i = 0; i < attemptList.length; i++) {
    Attempt attempt = (Attempt)attemptList[i];
    attempt.updateLabels();
    }
  }

 }
