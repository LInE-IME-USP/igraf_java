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
 * @description Panel used to communication between teacher and student
 * 
 * @see igraf/moduloCentral/controle/menu/IgrafMenuController.java - extends this 'abstract class'
 * @see igraf/moduloCentral/visao/menu/MenuExercicio.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;

import difusor.i18N.LanguageUpdatable;


public class PainelComentario extends PainelBasico implements MouseListener, KeyListener, LanguageUpdatable {

 private static final Font FONTE = new Font("Arial", Font.BOLD, 12);
 private static final Color
   COLORBACKCOMMENT = Color.white,
   COLORBUTTONENTER = Color.darkGray,
   COLORBUTTONFACE = Color.white, // must has contrast with 'COLORBUTTONENTER'
   COLORBUTTON = Color.gray; // must has contrast with 'COLORBUTTONFACE'

 private JTextArea textArea;
 private JButton buttonTopHelp, buttonTopInsertComment, buttonTopMsgTeacher; // top buttons to this window: Help | Insert comment | Comment received
 private String
   strDiscursiveAnswer,           // message from student to answer discursive exercise or to communicate something to the teacher
   strHelpAboutDiscursiveAnswer,  // message about the use o discursive answer or send message related to the exercise
   textoUsuario = "", comentario; 
 boolean clicked;

 private JPanel northPanel;
 private String comentarioRecebido;
 int modo;

 /**
  * Panel used to communication between teacher and student
  */
 public PainelComentario (JanelaExercicio janelaExercicio) {
  super.janelaExercicio = janelaExercicio;
  setLayout(new BorderLayout());

  textArea = new JTextArea();
  textArea.setFont(FONTE);
  textArea.setBackground(EsquemaVisual.corAreaDesenho);
  textArea.setForeground(EsquemaVisual.corLetraAreaTexto);
  textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(0,1,0,1)));
  textArea.setToolTipText(ResourceReader.msg("exercPainelComentResp2"));
  textArea.addKeyListener(this);

  this.add("Center",textArea);

  northPanel = new JPanel(new BorderLayout());
  northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(0,1,0,1)));
  JPanel auxPanel = configureMenu();
  northPanel.add("West", auxPanel);

  this.add("North", northPanel);

  // default: it is not an exercise
  setMode(1);

  }


 private JPanel configureMenu () { // menuAjuda, exercPainelComentInsComent, exercPainelComentRec
  JPanel auxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 2)); // 5: space beween each button; 4: space from borders

  buttonTopHelp = getNewButtonTop("menuAjuda", "menuAjudaTooltip"); // Click here to get some help about this window
  // exercPainelComentCriar1= Observations about the answer submission
  // - If the requered answer is a text, after your graphic or text is done, please, click on "Answer / send"
  // - If the exercise is divided by itens with direct answer, enter the value that you consider correct in each of the correspondent field, then click on "Answer / send"
  buttonTopInsertComment = getNewButtonTop("exercPainelComentInsComent", "exercPainelComentInsComentTooltip");
  buttonTopMsgTeacher = getNewButtonTop("exercPainelComentRec", "exercPainelComentRecTooltip");

  buttonTopHelp.addMouseListener(this);
  buttonTopInsertComment.addMouseListener(this);  
  buttonTopMsgTeacher.addMouseListener(this);

  auxPanel.add(buttonTopHelp);
  auxPanel.add(buttonTopInsertComment);
  auxPanel.add(buttonTopMsgTeacher);

  return auxPanel;
  }

 private JButton getNewButtonTop (String buttonText, String buttonTooltip) {
  JButton buttonTop = new JButton(ResourceReader.msg(buttonText));
  buttonTop.setBackground(COLORBUTTON);
  buttonTop.setForeground(COLORBUTTONFACE);
  buttonTop.setOpaque(true);
  buttonTop.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(0,2,0,2)));
  buttonTop.setToolTipText(ResourceReader.msg(buttonTooltip));
  return buttonTop;
  }


 // Personilize condition of authoring or use
 public void setMode (int modo) {
  //T System.out.println("src/igraf/moduloExercicio/visao/ PainelComentario.java: setMode(" + modo +")");
  if (modo == 1) { // Creating exercise
   // buttonTopMsgTeacher.setEnabled(false); - this efect seem not good...
   strHelpAboutDiscursiveAnswer = ResourceReader.msg("exercPainelComentCriar1");
   strDiscursiveAnswer = ResourceReader.msg("exercPainelComentCriar1"); // "  Digite nesta área as respostas discursivas, justificativas e/ou comentários para o professor"
   }
  else
  if (modo == 0) { // Using an exercise
   strHelpAboutDiscursiveAnswer = ResourceReader.msg("exercPainelComentResp1");
   strDiscursiveAnswer = ""; // ResourceReader.msg("exercPainelComentResp2"); // This text area must be used to send aditional comment related to this exercise to your teacher
   }
  textArea.setText(strDiscursiveAnswer);// a mensagem deve ser exibida apenas na abertura da tela
  textArea.selectAll();
  this.modo = modo;
  }


 // Pushed button: Help
 void exibeAjuda () {
  textArea.setText(strHelpAboutDiscursiveAnswer);
  textArea.setEditable(false);
  textArea.setBackground(COLORBACKCOMMENT);
  }


 // Pushed button: Insert comment
 // Change to the text area to send comments about the exercise
 void exibeComentarioEnviar () {
  textArea.setBackground(COLORBACKCOMMENT);
  textArea.setText(textoUsuario);
  textArea.setEditable(true);
  }

 public void setComentario (String str) {
  comentarioRecebido = str;
  }


 // Pushed button: Comment received
 void exibeComentarioRecebido () {
  //System.out.println("PainelComentario.java: " + ResourceReader.msg("exercPainelComentProfNaoComent"));
  if (comentarioRecebido == null)
   comentarioRecebido = ResourceReader.msg("exercPainelComentProfNaoComent"); // The teacher didn't add any hint or comment about this exercise
  textArea.setText(comentarioRecebido);
  textArea.setBackground(COLORBACKCOMMENT);
  textArea.setEditable(false);
  }

 public String getComentario () {  
  return textoUsuario;
  }

 public Dimension getPreferredSize () {
  return new Dimension(largura, 250);
  }


 public void reset () {
  textArea.setText("");
  textoUsuario = "";
  } 

 // Treat click in top buttons: Help | Insert comment | Comment received

 public void mouseEntered (MouseEvent e) { //System.out.println("PainelComentario.java: mouseEntered");
  JButton selButton = (JButton) e.getSource();
  selButton.setBackground(COLORBUTTONENTER);
  selButton.setForeground(COLORBUTTONFACE);
  }

 public void mousePressed (MouseEvent e) { //System.out.println("PainelComentario.java: mousePressed");
  JButton selButton = (JButton) e.getSource();
  String str = selButton.getText();
  clicked = true;

  if (str.equals(ResourceReader.msg("menuAjuda"))) { // pushed button: Help
   exibeAjuda();
   }
  else
  if (str.equals( ResourceReader.msg("exercPainelComentInsComent"))) { // pushed button: Insert comment
   exibeComentarioEnviar(); // text provided by the student
   }
  else
  if (str.equals( ResourceReader.msg("exercPainelComentRec"))) { // pushed button: Comment received
   exibeComentarioRecebido();
   }
  //R auxPanel.validate();
  } // public void mousePressed(MouseEvent e)

 public void mouseExited (MouseEvent e) { //System.out.println("PainelComentario.java: mouseExited");
  JButton selButton = (JButton) e.getSource();
  selButton.setBackground(COLORBUTTON); // getBackground()
  selButton.setForeground(COLORBUTTONFACE); // Color.black
  }

 public void mouseReleased (MouseEvent e) {  }
 public void mouseClicked (MouseEvent e) {  }


 // Treat text area insetion of text
 public void keyReleased (KeyEvent e) {
  textoUsuario = textArea.getText(); // update text inserted by the student
  }
 public void keyTyped (KeyEvent e) { }
 public void keyPressed (KeyEvent e) { }


 // Update to new language
 public void updateLabels () {
  buttonTopHelp.setText(ResourceReader.msg("menuAjuda"));
  buttonTopInsertComment.setText(ResourceReader.msg("exercPainelComentInsComent"));
  buttonTopMsgTeacher.setText(ResourceReader.msg("exercPainelComentRec"));

  textArea.setToolTipText(ResourceReader.msg("exercPainelComentResp2"));

  buttonTopHelp.setToolTipText(ResourceReader.msg("menuAjudaTooltip")); // Click here to get some help about this window
  buttonTopInsertComment.setToolTipText(ResourceReader.msg("exercPainelComentInsComentTooltip")); // Click here to insert your own comments about this exercise
  buttonTopMsgTeacher.setToolTipText(ResourceReader.msg("exercPainelComentRecTooltip")); // Click here to see the teacher comments about this exercise

  }

 }
