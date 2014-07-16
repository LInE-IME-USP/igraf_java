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
 * @description Um objeto do tipo plotter tem a responsabilidade de obter acesso a uma área de
 * desenho e fornecer meios para que algo seja desenhado nesta área. Quem decide o que será desenhado
 * é o controlador que sabe qual é o tipo de desenho e o como produzir as nuances sobre este desenho.
 * 
 * @see igraf/moduloCentral/visao/desenho/Desenho.java
 * @see igraf/moduloCentral/visao/plotter/AxesPlotter.java    : extends Plotter
 * @see igraf/moduloCentral/visao/plotter/GraphPlotter.java   : extends Plotter
 * @see igraf/moduloCentral/visao/plotter/PolygonPlotter.java : extends Plotter
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloCentral.visao.plotter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import igraf.IGraf;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloCentral.controle.AreaDesenhoController;
import igraf.moduloCentral.eventos.EstadoTelaEvent;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.visao.AreaDesenho;

import difusor.evento.CommunicationEvent;


public abstract class Plotter implements MouseListener, MouseMotionListener, KeyListener {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/plotter/Plotter.java";

 //DEBUG:
 private static int staticCounter = 0;
 private int ID = staticCounter++;
 public int getID () { return ID; }

 int width = 0, height = 0, zoomDif = 2;
 int oldX, oldY, newX, newY;
 protected int xmin, xmax, ymin, ymax, escala = 60, passo = 50,
               passoMinimo = 50, ordem = 0;

 // Variables to control any plane translation
 // Used in: ./igraf/moduloCentral/visao/plotter/AxesPlotter.java
 private int translationX = 0, translationY = 0;
 protected int getTranslationX () { return translationX; }
 protected int getTranslationY () { return translationY; }

 //IMPORTANT: to draw functions in scale and in the right position
 public int getXMax () {
  return xmax + translationX;
  }
 public int getXMin () {
  return xmin + translationX;
  }
 public int getEscala () {
  return escala;
  }


 private boolean definirOrigemPadrao = true;
 protected static boolean cursorSobreTexto; // setted in 'GraphPlotter.java'
 boolean mouseOverPoint = false;

 protected Component father;

 // Color to draw graphics' functions - also in 'igraf/moduloCentral/visao/desenho/Desenho.java'
 private static final Color laranja = EsquemaVisual.COLOR_laranja; // new Color(170,  40, 245)
 private static final Color roxo    = EsquemaVisual.COLOR_roxo;    // new Color( 55, 140, 100) // 2013: foi para azul claro (255, 140,   0)
 private static final Color bordo   = EsquemaVisual.COLOR_bordo;   // new Color(105, 155, 240) // 2013: foi para azul claro (185,  55, 160)
 private static final Color marinho = EsquemaVisual.COLOR_marinho; // new Color( 30, 125, 210)
 private static final Color marrom  = EsquemaVisual.COLOR_marrom;  // new Color(150,  90,  90)

 // The index of 'colorList' and 'colorNames' must be the same in order to a correct treatment of colors
 protected static final Color [] colorList = { Color.blue, Color.black, marinho, Color.red, laranja, roxo, Color.darkGray, bordo, marrom };

 protected static final String [] colorNames = { "azul", "preto", "marinho", "vermelho", "laranja", "roxo", "cinza-escuro", "azul-claro", "marrom" };
 public static final int escalaPadrao = 60;

 private int colorIndex = 0;

 protected AreaDesenhoController adc;

 private final int mouseDragging = 0;
 private final int arrowPressed  = 1;

 EstadoTelaEvent ete;
 long delay = 500;

 private float eps = 0.05f;
 protected boolean drawHotPoint;
 protected int hotPointX;
 protected int hotPointY;

 boolean isHotPointOn = true;

 private boolean paintMode = false;

 //T to teste which Plotter is this: PolygonPlotter, AxesPlotter, or GraphPlotter
 //T puublic Plotter () { System.out.println(""+IGCLASSPATH + ": #ID="+ID+": "+this.getClass().getName()); }

 protected Color getNextColor () {
  return colorList[colorIndex++ % colorList.length];
  }


 protected Color getColor (int colorIndex) {
  return colorList[colorIndex];
  }

 /**
  * Método que deve ser sobrescrito por todas as classes que desejarem fazer um desenho na AreaDesenho do iGraf.
  * A primeira linha do método sobrescrito DEVE ser a chamada ao mesmo método da classe pai para que seja feita a configuração dos limites de desenho.
  * @param Graphics2D gr2d (NAO usado!)
  * @param int w
  * @param int h
  */
 public void dimensiona (Graphics2D g2d, int w, int h) { //TODO: 'Graphics2D g2d,' NAO eh usado!
  if (definirOrigemPadrao || w != width || h != height) {
   width = w;
   height = h;
   xmax = w/2;
   xmin = -xmax;
   ymax = h/2;
   ymin = -ymax;
   }
  definirOrigemPadrao = false;
  }



 public void resetOrigem () {
  definirOrigemPadrao  = true;
  notificaAlteracaoTela();
  }

 /**
  * Promove o deslocamento vertical do plano cartesiano
  * @param shift
  */
 public void vertical (int shift, int modo) {// shift deve ser calculado como valor real de x
  translationY -= shift;

  if (modo == mouseDragging)
   sendTimedEvent(new SendEventTask());
  if (modo == arrowPressed)
   notificaAlteracaoTela();
  }

 protected void setPosVertical (int posY) {
  translationY = posY;
  }

 /**
  * Promove o deslocamento horizontal do plano cartesiano.
  * @param shift
  */
 public void horizontal (int shift, int modo) {
  //T System.out.println(""+IGCLASSPATH + ": #ID="+ID+", horizontal("+shift+","+modo+"): translationX=" + translationX);

  translationX += shift;
  if (modo == mouseDragging)
   sendTimedEvent(new SendEventTask());
  if (modo == arrowPressed)
   notificaAlteracaoTela();
  }

 protected void setPosHorizontal (int posX) {
  translationX = posX;
  }

 // Atualiza valores dos deslocamentos em x e em y para zero, fazendo com que a origem do plano cartesiano seja reposicionada no centro da área de desenho.
 public void home () {
  translationX = translationY = 0;
  resetOrigem();
  }


 public Point getCoordenadasOrigem () {
  return new Point(translationX, translationY);
  }

 /**
  * Aplica zoom à grade visível na área de desenho; o incremento do zoom usa valores
  * positivos ou valores negativos.  Valores negativos diminuem o zoom.
  * @param v: incremento (inteiro) a ser aplicado ao zoom
  */
 public void zoom (int v) {
  sendTimedEvent(new SendEventTask());
  escala += v;

  if (escala < 20)
   escala = 20;
  }

 protected void setEscala(int escala) {
  this.escala = escala;
  }

 /**
  * Método criado para tratar o registro/envio de eventos que precisam ser registrados
  * apenas depois que a alteração se torna significativa.   Por exemplo, quando o usuário
  * arrasta a área de desenho, não existe a necessidade de registrar na sessão cada posição
  * intermediária entre o estado inicial e o estado final do écrã, assim, este tipo de
  * interação só é registrado depois de um determinado delay para garantir que o usuário
  * já terminou aquela interação e, logo, ela já pode ser registrada na sessão.
  * @param t
  */
 Timer timer;
 int counter = 0;
 protected void sendTimedEvent (TimerTask t) {
  if (timer == null) {
   timer = new Timer();
   timer.schedule(t, delay, 100);
   }
  else if (counter > 0) counter = 0;
  }
 class SendEventTask extends TimerTask {
   public void run () {
     counter++;
     if (counter > 4) { try { notificaAlteracaoTela(); } catch (RuntimeException e) { }
       timer.cancel();
       timer = null;
       }
     }
   }


 private void notificaAlteracaoTela () {
  ete = new EstadoTelaEvent(this, new Point(translationX, translationY), escala);
  ete.setCommand(EstadoTelaEvent.CHANGE_CARTESIAN_PLANE);
  enviarEvento(ete);
  }

 public void zoomPadrao () {
  escala = 60;
  notificaAlteracaoTela();
  }


 public void mudaEstadoTela (EstadoTelaEvent event) {
  if (!IGraf.loading) return;
  Point p = event.getCoordenadasOrigem();
  if (p == null) return;
  setEscala(event.getEscala());
  setPosHorizontal(event.getCoordenadasOrigem().x);
  setPosVertical(event.getCoordenadasOrigem().y);
  notifyScreenChanged();
  }

 /**
  * Converte coordenas x calculadas para a origem defult, com (0,0) no canto
  * superior esquerdo da tela para coordenadas da área de desenho, que utiliza
  * a origem no centro do plano.  Método de conveniência para que se possa usar
  * coordenadas cartesianas de modo trivial.
  * @param x: uma coordenada gerada com referência ao (0,0) padrão dos componentes Java
  * @return xn: uma coordenada gerada com referência ao (0,0) no centro do plano
  */
 public int normalizaX (int x) {
  int xn = x + width/2 - translationX;
  return xn;
  }


 protected int reverteNormalizaX (int xn) {
  int x = xn + translationX - width/2;
  return x;
  }

 /**
  * Converte coordenas y calculadas para a origem defult, com (0,0) no canto
  * superior esquerdo da tela para coordenadas da área de desenho, que utiliza
  * a origem no centro do plano.  Método de conveniência para que se possa usar
  * coordenadas cartesianas de modo trivial.
  * @param y: uma coordenada gerada com referência ao (0,0) padrão dos componentes Java
  * @return y: uma coordenada gerada com referência ao (0,0) no centro do plano
  */
 public int normalizaY (int y) {
  int yn = height/2 - y - translationY;
  return yn;
  }

 protected int reverteNormalizaY (int yn) {
  int y = height/2 - yn - translationY;
  return -y;
  }


 public float xPixelToReal (int px) {
  return (px - xmax + translationX)/(float)escala;
  }

 public float yPixelToReal (int py) {
  return (ymax - py - translationY)/(float)escala;
  }

 public void mouseDragged (MouseEvent e) {
  int newX = e.getX();
  int newY = e.getY();

  if (!cursorSobreTexto) {
   if (drawHotPoint) {
    zoom(newX - oldX);
    setHotCoordinates(e);
    }
   else if (!mouseOverPoint) {
    horizontal(oldX - newX, mouseDragging);
    vertical  (newY - oldY, mouseDragging);
    }
   }
  oldX = newX;
  oldY = newY;

  enviarEvento(new GraphicOnScreenChangedEvent(this, GraphicOnScreenChangedEvent.CLEAR_SCREEN));
 
  ((AreaDesenho) e.getSource()).atualizaDesenho();
  } // public void mouseDragged(MouseEvent e)


 public void mousePressed (MouseEvent e) {
  oldX = e.getX();
  oldY = e.getY();
  ((Component) e.getSource()).requestFocus();
  }

 public void mouseMoved (MouseEvent e) {
  if (!mouseOverPoint)
  if (overHotPoint(e))
   setHotCoordinates(e);
  else if (drawHotPoint)
   drawHotPoint = false;
  refazDesenho();
  }

 public void refazDesenho () {
  if (drawHotPoint == isHotPointOn) {
   isHotPointOn = !isHotPointOn;
   }
  father.repaint();
  }

 private void setHotCoordinates (MouseEvent e) {
  hotPointX = normalizaX((int)Math.round(xPixelToReal(e.getX()))*escala);
  hotPointY = normalizaY((int)Math.round(yPixelToReal(e.getY()))*escala);
  }

 private boolean overHotPoint (MouseEvent e) {
  if ((Math.abs(Math.round(xPixelToReal(e.getX())) - xPixelToReal(e.getX())) < eps) &&
     (Math.abs(Math.round(yPixelToReal(e.getY())) - yPixelToReal(e.getY())) < eps))
   return drawHotPoint = true;
  return false;
  }


 public void keyPressed (KeyEvent e) {
  int keyCode = e.getKeyCode();
  if (keyCode == 36) // home
   home();
  else if (keyCode == 37) // esquerda
   horizontal(15, arrowPressed);
  else if (keyCode == 39)  // direita
   horizontal(-15, arrowPressed);
  else if (keyCode == 38) // cima
   vertical(-15, arrowPressed);
  else if (keyCode == 40) // baixo
   vertical(15, arrowPressed);
  else if (keyCode == 107) // mais
   zoom(4);
  else if (keyCode == 109) // menos
   zoom(-4);
  else if (keyCode == 78 && e.isAltDown()) // alt + n
   zoomPadrao();
  else {e.consume();  return; }
  refazDesenho();
  }



 public void keyReleased (KeyEvent e) {
  drawHotPoint = false;
  }

 public void keyTyped (KeyEvent e) { }

 public void mouseReleased (MouseEvent e) { }
 public void mouseEntered (MouseEvent e) {
  if (father == null)
   father = (Component)e.getSource();
  }
 public void mouseClicked (MouseEvent e) { }
 public void mouseExited (MouseEvent e) { }


 public void reportLimits () {
  adc.enviarEvento(new EstadoTelaEvent(this));
  }

 public void setController (AreaDesenhoController adc) {
  this.adc = adc;
  }

 public void enviarEvento (CommunicationEvent ie) {
  adc.enviarEvento(ie);
  }

 public abstract void notifyScreenChanged ();
 public abstract void desenha (Graphics2D g, int w, int h);

 // this is crap and must be removed... soon
 // created in 03/01/12 to shoot a trouble (or break a branch :)
 public abstract int getCorDesenho (String funcao); // used only in graphPlotter
 public abstract void iniciaAnimacao (boolean b);   // move to animPlotter

 public void setPaintMode (boolean mode) {
  paintMode = mode;
  }

 public boolean isInPaintMode () {
  return paintMode;
  }

 }
