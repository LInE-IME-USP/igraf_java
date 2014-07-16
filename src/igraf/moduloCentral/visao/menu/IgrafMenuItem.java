/*
 * 
 * TODO: com versao de menus de 03/2013 esta classe foi desativada
 *       ainda precisa verificar se todas as funcionalidades foram passadas para 'PainelBotoes' ou outras
 * VEJA: src/igraf/moduloSuperior/visao/PainelBotoes.java
 * 
 * 
 * iGraf : interactive Graphics in the Internet
 * LInE - line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Basic class for any "secondary menu".
 * 
 */

package igraf.moduloCentral.visao.menu;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.menu.IgrafSubMenuEvent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;


public class IgrafMenuItem extends JLabel { //leo implements MouseListener

 public static final int // used in 'igraf/moduloCentral/visao/menu/IgrafMenu.java'; 'igraf/moduloSuperior/visao/PainelBotoes.java'
   MOUSE_CLICKED = 1,
   MOUSE_ENTERED = 2,
   MOUSE_EXITED  = 3;
 private static final int
   VAR_X = 8,       // reduce the right position of the submenuitem
   LIM_X = -1,      // left variation accepted (keep the menuitem on the screen)
   LIM_Y_MIN = -1,  // botton variation accepted
   LIM_Y_MAX = 20;  // reight variation accepted

 private String description;

 private static final Color cinzaMedio = new Color(210, 210, 210);

 private boolean useTimer;
 private Timer timer;

 private IgrafMenu im;
 private boolean hasSubMenu = false;

 public IgrafMenuItem (String s, String d, boolean useTimer) {
  this(s, d);
  //R this.useTimer = useTimer;
  // System.out.println("IgrafMenuItem.java: " + s + "," + d);
  }

 public IgrafMenuItem (String s, String d) {
  super(s);
  description = d;
  setToolTipText(d);
  //leo addMouseListener(this);
  setBackground(Color.white);
  setOpaque(true);
  }

 protected void paintComponent (Graphics g) {
  super.paintComponent(g);

  if (description == null)
   insereSeparador(g);
  }

 private void insereSeparador (Graphics g) {
  int h = getSize().height;
  int w = getSize().width;

  g.setColor(Color.black);
  g.drawLine(2, h/2, w-3, h/2);
  g.setColor(Color.gray);
  g.drawLine(2, h/2, w-3, h/2);
  g.setColor(cinzaMedio);
  g.drawLine(2, h/2, w-3, h/2);
  }


 private void textoAlternativo () {
  if (getText() == null)
   return;

  if (getText().equals(ResourceReader.msg("madEixosRemover")))
   setText(ResourceReader.msg("madEixosExibir"));
  else
  if (getText().equals(ResourceReader.msg("madEixosExibir")))
   setText(ResourceReader.msg("madEixosRemover"));

  if (getText().equals(ResourceReader.msg("madEscalaRemover")))
   setText(ResourceReader.msg("madEscalaExibir"));
  else
  if (getText().equals(ResourceReader.msg("madEscalaExibir")))
   setText(ResourceReader.msg("madEscalaRemover"));

  if (getText().equals(ResourceReader.msg("madGradeRemover")))
   setText(ResourceReader.msg("madGradeExibir"));
  else
  if (getText().equals(ResourceReader.msg("madGradeExibir")))
   setText(ResourceReader.msg("madGradeRemover"));
  else
  if (getText().equals(ResourceReader.msg("mepPintaPoli")))
   setText(ResourceReader.msg("mepNPintaPoli"));
  else
   if (getText().equals(ResourceReader.msg("mepNPintaPoli")))
    setText(ResourceReader.msg("mepPintaPoli"));
  }

 public String getDescription () {
  if (description == null)
   return "";
  return description;
  }

 public void setDescription (String description) {
  this.description = description;
  }

 //leo private void removeMenu () { getContainer().autoRemove(); }

 private IgrafMenu getContainer () {
  return (IgrafMenu)getParent();
  }

 private void corNormal () {
  setBackground(Color.white);
  setForeground(Color.black);
  }

 public void setSubMenu (IgrafMenu im) {
  hasSubMenu = true;
  this.im = im;
  }

 //TODO 12/04/2013
 //TODO Usually unnecessary, the item listener already did this! Removing...
 //TODO This is useful only in the case of option 'zoom in, out', but this means an "anomalous" behaviour (unique with it)
 // Launches an event over any menu item
 //R class RemindTask extends TimerTask {
 //R   public void run() {
 //R     System.out.print("."); // "IgrafMenuItem.java: RemindTask: " + getText());
 //R     getContainer().disparaEvento(IgrafMenuItem.MOUSE_CLICKED, getText());
 //R     }
 //R   }

 //__ Mouse events __ start

 public void mouseEntered (MouseEvent e) {
  //T System.out.println("IgrafMenuItem.java: mouseEntered: " + getText());
  if (description != null) {
   setBackground(Color.blue);
   setForeground(Color.white);
   getContainer().disparaEvento(IgrafMenuItem.MOUSE_ENTERED, getDescription());
   }

  if (hasSubMenu) {
   IgrafMenu containerIgrafMenu = getContainer();
   int x = containerIgrafMenu.getX() + containerIgrafMenu.getWidth() - VAR_X; // position of the submenuitem
   containerIgrafMenu.disparaEventoSubMenu(new IgrafSubMenuEvent(this, 2, im.toString(), x));
   }

  //R 05/05/2013: src/igraf/moduloSuperior/visao/PainelBotoes.java: ja esta em
  //R             class MouseListenerRunnableTask extends TimerTask, addMouseListenerRunnable(...), buildOneSecondaryVector(...)
  //R if (useTimer & isEnabled()) { timer = new Timer(); timer.schedule(new RemindTask(), 600, 40); }
  }

 //__ Mouse events __ end

 }
