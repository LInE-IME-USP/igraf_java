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
 * @description This is the window to present the history session (as text lines)
 * 
 * @see igraf/IGraf.java: start any construction in GRF file
 * @see igraf/moduloArquivo/modelo/ArquivoModel.java: register history session from any GRF file
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloJanelasAuxiliares.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
//import java.awt.Label;
//import java.awt.Panel;
//import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.HashMap;

import difusor.i18N.LanguageUpdatable;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.eventos.IgrafSessaoEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloJanelasAuxiliares.controle.JanelaHistoricoController;
import igraf.moduloJanelasAuxiliares.eventos.JanelaHistoricoEvent;

public class JanelaHistorico extends JFrame implements MouseListener, LanguageUpdatable { // , EsquemaVisual 


 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "src/igraf/moduloJanelasAuxiliares/visao/JanelaHistorico.java";

 private static HashMap hashMapButtons = null; // HashMap to register all JButtons indexed by their name
   
 private JPanel mainJPanel;

 private JButton stepFront, stepBack, forward, backward;

 private JTextArea windowHistTextArea = new JTextArea();

 private static final Color
  COLOR_BG_WINDOW = EsquemaVisual.corAreaDesenho,
  COLOR_FG_WINDOW = EsquemaVisual.corLetrasBotoes,
  COLOR_BG_BUTTONS = EsquemaVisual.corFundoBotoes,
  COLOR_DISABLED_BUTTON =  EsquemaVisual.corDesFundoBotoes;

 private boolean isVisible;

 private JanelaHistoricoController jhc;

 // Construtor
 public JanelaHistorico (JanelaHistoricoController jhc) {
  super("iGraf - " + ResourceReader.msg("titleWinHistSession")); // "Session's history"
  setBackground(COLOR_BG_WINDOW);
  this.jhc = jhc;

  mainJPanel = new JPanel(new BorderLayout());
  createButtons();

  setFont(EsquemaVisual.fontHB12);

  windowHistTextArea.setSize(390, 85);
  windowHistTextArea.setLocation(5, 35);
  windowHistTextArea.setEditable(false);
  windowHistTextArea.setBackground(EsquemaVisual.corAreaDesenho);
  mainJPanel.add(windowHistTextArea);
  getContentPane().add(mainJPanel); 

  insereControles(); 
  closer();
  pack();
  setVisible(true);
  }


 private void createButtons () {
  stepFront = new JButton(" > ");
  stepBack  = new JButton(" < ");
  forward   = new JButton(" >> ");
  backward  = new JButton(" << ");
  stepFront.setToolTipText(ResourceReader.msg("toolTipHSstepFront")); // Step forward in this session's history
  stepBack.setToolTipText(ResourceReader.msg("toolTipHSstepBack")); // Step back in this session's history
  forward.setToolTipText(ResourceReader.msg("toolTipHSforward")); // Go to end this session's history
  backward.setToolTipText(ResourceReader.msg("toolTipHSbackward")); // Go to beginning of this session's history

  setDisabledButton(stepBack); //TODO: por enquanto NAO implementado! Usar 'undo'?

  hashMapButtons = new HashMap();
  hashMapButtons.put("stepFront", stepFront);
  hashMapButtons.put("stepBack", stepBack);
  hashMapButtons.put("forward", forward);
  hashMapButtons.put("backward", backward);
  }

 public void visibilidade () {
  if (!isVisible)
   setVisible(true);
  else
   setVisible(false);
  isVisible = !isVisible;
  }

 public Dimension getPreferredSize () {
  return new Dimension(500, 275);
  }

 private void insertJButton (JPanel jPanelAux, JButton jButton) {
  jButton.setBackground(COLOR_BG_BUTTONS);
  jButton.setForeground(COLOR_FG_WINDOW);
  jButton.setOpaque(true);
  // jButton.setAlignment(1);
  jButton.addMouseListener(this);
  jPanelAux.add(jButton);
  }


 private void insereControles () {
  //leo Panel jPanelAux = new Panel(new GridLayout(1,4,2,0)) {   public void paint(Graphics g) { g.drawRect(1, 1, getSize().width-2, getSize().height-2);  }
  //leo  public Dimension getPreferredSize () { return new Dimension(0, 30);  }   public Insets getInsets () { return new Insets(4,4,3,1);  }   };
  //leo backward.setBackground(COLOR_BG_BUTTONS);  backward.setForeground(COLOR_FG_WINDOW);  backward.setAlignment(1);  backward.addMouseListener(this);
  //leo stepBack.setBackground(COLOR_BG_BUTTONS);  stepBack.setForeground(COLOR_FG_WINDOW);  stepBack.setAlignment(1);  stepBack.addMouseListener(this);
  //leo stepFront.setBackground(COLOR_BG_BUTTONS);  stepFront.setForeground(COLOR_FG_WINDOW);  stepFront.setAlignment(1);  stepFront.addMouseListener(this);
  //leo forward.setBackground(COLOR_BG_BUTTONS);  forward.setForeground(COLOR_FG_WINDOW);  forward.setAlignment(1);  forward.addMouseListener(this);
  //leo p.add(backward);  p.add(stepBack);  p.add(stepFront);  p.add(forward);
  JPanel jPanelAux = new JPanel(new GridLayout(1,4,2,0));
  jPanelAux.setBorder(javax.swing.border.LineBorder.createGrayLineBorder());
  insertJButton(jPanelAux, backward);
  insertJButton(jPanelAux, stepBack);
  insertJButton(jPanelAux, stepFront);
  insertJButton(jPanelAux, forward);

  mainJPanel.add("South", jPanelAux);
  }

 // public void paint (Graphics g) {  g.drawRect(6, 31, getSize().width-12, getSize().height-37);  }

 private void closer () {
  addWindowListener(new WindowAdapter () {
   public void windowClosing (WindowEvent e) {
    restabeleceSessao();
    setVisible(false);
    dispose();
    }
   });
  }


 public void mouseClicked (MouseEvent e) {
  JButton source = (JButton)e.getSource();

  if (!source.isEnabled())
   return; // ignore command if button is disabled

  if (source == stepFront) { // " > "
   jhc.enviarEvento(new JanelaHistoricoEvent(this, JanelaHistoricoEvent.STEP_REDRAW)); // 
   setEnabledButtons(false); // set enabled backs
   }
  else
  if (source == stepBack) { // " < "
   //TOTO: NAO implementado! voltar e desfazer a��o?
   setEnabledButtons(true); // set enabled forwards
   }
  else
  if (source == forward) { // " >> "
   restabeleceSessao();
   setEnabledButtons(false); // set enabled backs
   setDisabledButtons(true); // set disabled all forward button
   }
  else
  if (source == backward) { // " << "
   historico();
   setEnabledButtons(true);   // set enabled forwards
   setDisabledButtons(false); // set disabled all back button
   }

  }

 private void restabeleceSessao () {
  jhc.enviarEvento(new JanelaHistoricoEvent(this, JanelaHistoricoEvent.REDRAW_ALL));
  }

 // Examina hist�rico da sess�o
 public void historico () {  
  windowHistTextArea.setText("");
  jhc.iniciaHistorico();
  }

 public void mouseEntered (MouseEvent e) {
  JButton source = (JButton)e.getSource();
  if (source.isEnabled()) {
   source.setBackground(COLOR_FG_WINDOW);
   source.setForeground(COLOR_BG_BUTTONS);
   }
  else {
   source.setBackground(COLOR_DISABLED_BUTTON);
   source.setForeground(COLOR_BG_BUTTONS);
   }
  }

 public void mouseExited (MouseEvent e) {
  JButton source = (JButton)e.getSource();
  if (source.isEnabled()) {
   source.setBackground(COLOR_BG_BUTTONS);
   source.setForeground(COLOR_FG_WINDOW);
   }
  else {
   source.setBackground(COLOR_DISABLED_BUTTON);
   source.setForeground(COLOR_BG_BUTTONS);
   }
  }

 public void mousePressed (MouseEvent e) { }
 public void mouseReleased (MouseEvent e) { }

 // igraf.moduloJanelasAuxiliares.controle.JanelaHistoricoController.tratarEventoRecebido(JanelaHistoricoController.java:28)
 public void setNextStep (String description) {
  windowHistTextArea.append(description + "\n");
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "setNextStep(" + description.trim() + ")");
  // JButton stepFront, stepBack, forward, backward
  if (description!=null && description.trim().equals(ResourceReader.msg("FIM"))) { //  + "\n"
   setDisabledButtons(true); // disable 'stepFront' and 'forward'
   }
  // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  }


 public void setEnabledButtons (boolean isFront) {
  if (isFront) {
   stepFront.setEnabled(true); stepFront.setBackground(COLOR_BG_BUTTONS); stepFront.setForeground(COLOR_FG_WINDOW);
   forward.setEnabled(true);   forward.setBackground(COLOR_BG_BUTTONS);   forward.setForeground(COLOR_FG_WINDOW);
   }
  else {
   // stepBack.setEnabled(true); stepBack.setBackground(COLOR_BG_BUTTONS); stepBack.setForeground(COLOR_FG_WINDOW);
   backward.setEnabled(true); backward.setBackground(COLOR_BG_BUTTONS); backward.setForeground(COLOR_FG_WINDOW);
   }
  }
 public void setDisabledButtons (boolean isFront) {
  if (isFront) {
   stepFront.setEnabled(false);
   forward.setEnabled(false);
   stepFront.setBackground(COLOR_DISABLED_BUTTON);
   forward.setBackground(COLOR_DISABLED_BUTTON);
   }
  else {
   stepBack.setEnabled(false);
   backward.setEnabled(false);
   stepBack.setBackground(COLOR_DISABLED_BUTTON);
   backward.setBackground(COLOR_DISABLED_BUTTON);
   }
  }
 public void setDisabledButton (JButton jButton) {
  jButton.setEnabled(false);
  jButton.setBackground(COLOR_DISABLED_BUTTON);
  }
 // public void setEnabledButton (String strButton, boolean enabled) {  // colocar String num HashMap  JButton jButton = (JButton)hashMapButtons.get(strButton);  jButton.setEnabled(enabled);  }


 public void updateLabels () {
  setTitle(ResourceReader.msg("titleWinHistSession"));
  stepFront.setToolTipText(ResourceReader.msg("toolTipHSstepFront")); // Step forward in this session's history
  stepBack.setToolTipText(ResourceReader.msg("toolTipHSstepBack")); // Step back in this session's history
  forward.setToolTipText(ResourceReader.msg("toolTipHSforward")); // Go to end this session's history
  backward.setToolTipText(ResourceReader.msg("toolTipHSbackward")); // Go to beginning of this session's history
  }

 }
