/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author LOB
 * 
 * @description Window to present for a short time an usually warning message
 * 
 * @see igraf/moduloSuperior/controle/entrada/EntradaExpressaoController.java: private void enviaFuncaoDesenho(String funcao): AttentionToolTip.showToolTipText(entradaExpressao, msgError);
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 *
 */


package igraf.basico.event;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;


import igraf.IGraf;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloSuperior.controle.entrada.EntradaExpressaoController;


public class AttentionToolTip {

 // igraf/basico/util/EsquemaVisual.java ?
 private static final Color COLORBACK = new Color(220, 230, 250);
 private static final Font  FONT = new Font("Helvetica", Font.PLAIN, 11);

 private static Timer timer;
 private static final long delay = 500;
 private static final long DELAY = 5000;
 private static int counter = 0;
 private static String msgText = "";

 private JWindow attentionWindow; // attentionWindow = new JWindow();
 private JPanel contentPane;

 protected void sendTimedEvent (TimerTask tt) {
  if (timer != null) timer.cancel();
  timer = new Timer();
  timer.schedule(tt, 0, delay); // (tt, delay, 100);
  counter = 0;
  }
 class SendEventTask extends TimerTask {
  public void run () {
    counter++;
    //T System.out.print(counter + " ");
    if (counter > 4) { try {
      //T System.out.println(" FIM");
      attentionWindow.setVisible(false);
      timer.cancel();
      timer = null;
      return;
    } catch (RuntimeException e) { System.err.println("AttentionToolTip.java: "+e); }
      timer.cancel();
      timer = null;
      }
    }
   }


 protected void showMsgForAwhile () {
   attentionWindow.setVisible(true); //attentionWindow.contentPane.setVisible(true);
   sendTimedEvent(new SendEventTask()); // keep the message for a while...
   if (IGraf.IS_DEBUG)
     System.out.println("igraf/basico/event/AttentionToolTip.java: showMsgForAwhile(): " + attentionWindow.getClass().getName());
   }


 public static void setToolTipText (String text) {
  msgText = text;
  }


 // Show the message for a while
 public static void showToolTipText (Component jcomp, String text) {
  //TT System.out.println("src/igraf/basico/event/AttentionToolTip.java: " + text);
  AttentionToolTip attentionToolTip = new AttentionToolTip();
  attentionToolTip.showToolTipTextTimer(jcomp, text);
  }

 private void showToolTipTextTimer (Component jcomp, String text) {
  JLabel errorLabel = new JLabel(text);
  Window topLevelWin = SwingUtilities.getWindowAncestor(jcomp);
  errorLabel.setOpaque(true); //
  errorLabel.setFont(FONT); //
  errorLabel.setBackground(COLORBACK); //

  attentionWindow = new JWindow(topLevelWin);
  //T System.out.println("src/igraf/basico/event/AttentionToolTip.java: 1: " + attentionWindow.getClass().getName());
  contentPane = (JPanel) attentionWindow.getContentPane();
  contentPane.add(errorLabel);
  contentPane.setBackground(COLORBACK); //
  contentPane.setBorder(BorderFactory.createLineBorder(Color.black)); // Color.LIGHT_GRAY
  attentionWindow.pack();

  Point loc = jcomp.getLocationOnScreen();
  attentionWindow.setLocation(loc.x + 20, loc.y + 30); //TODO: calcular se esta nos cantos...

  showMsgForAwhile();

  }

 }
