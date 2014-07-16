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
 * @version 1.0 - 31/12/2011
 *
 * @description PolygonPlotter é responsável por apresentar os polígonos desenhados pelo iGraf à AreaDesenho.<br>
 * As ações que emanam desta classe são provocadas por cliques sobre as opções do menu 'Polígono'.<br>
 *
 * @see igraf/moduloCentral/visao/plotter/Plotter.java
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

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import igraf.IGraf;
import igraf.moduloCentral.controle.desenho.DesenhoPoligonoController;
import igraf.moduloCentral.eventos.menu.IgrafMenuPoligonoEvent;
import igraf.moduloCentral.visao.desenho.DesenhoPoligono;
import igraf.moduloSuperior.eventos.EntradaExpressaoEvent;
import difusor.evento.CommunicationEvent;

public class PolygonPlotter extends Plotter {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/plotter/PolygonPlotter.java";

 private int w = 0, h = 0;
 private BufferedImage image;
 private Graphics2D graphics;
 private DesenhoPoligonoController dpc = new DesenhoPoligonoController(this);

 private DesenhoPoligono d;
 
 private boolean freshMoving = false;
 private Cursor hd = new Cursor(Cursor.HAND_CURSOR);
 private Cursor df = new Cursor(Cursor.DEFAULT_CURSOR);

 /**
  * Configura a área de desenho do plotter e, se possível, faz o desenho.
  * @param img
  * @param w
  * @param h
  */
 public void setDrawingArea (BufferedImage img, int w, int h) {
  this.w = w;
  this.h = h;
  image = img;
  graphics = image.createGraphics();
  desenha(graphics, w, h);
  }

 public BufferedImage getImage () {
  return image;
  }

 public void inserePoligono (CommunicationEvent impe) {
  DesenhoPoligono dp = null;
 
  if (impe instanceof IgrafMenuPoligonoEvent) { 
   try {
    IgrafMenuPoligonoEvent e = (IgrafMenuPoligonoEvent)impe;
    dp = new DesenhoPoligono(this, e.getVertexList(), e.getStrFillColor(), e.getStrLineColor(), e.getPolygonType(), e.getFillingMode(), e.getVertexMode());
   } catch (Exception e) {
    return;
    }
   }
 
  if (impe instanceof EntradaExpressaoEvent) {
   String arg = ((EntradaExpressaoEvent)impe).getExpressao();
   dp = new DesenhoPoligono(this, arg, 0, 0);
   }
 
  if (dp != null) {
   dpc.inserePoligono(dp);
   dpc.modoPadrao();
   }
  }


 // Whenevr 'igraf/moduloCentral/visao/AreaDesenho.java' is rapainted calls this updater
 public void desenha (Graphics2D g, int w, int h) {
   //T System.err.println(IGraf.debugErrorMsg(IGCLASSPATH)+": desenha(...): (" + w + "," + h + ")");
  for (int i=0; i<dpc.getNumDesenhos(); i++) {
   d = (DesenhoPoligono)dpc.getDesenho(i); // igraf.moduloCentral.visao.desenho.DesenhoPoligono dpc
   d.atualizaDesenho(g);
   }
  }

 public void trataMenuPoligonoEvent (CommunicationEvent ie) {
  dpc.trataEvento(ie);
  }

 public void trataMenuGraficoEvent (CommunicationEvent ie) {
  dpc.trataEvento(ie);
  }

 public void resetPlotter () {
  dpc.reset();
  }

 public void keyPressed (KeyEvent e) {
  int keyCode = e.getKeyCode();
  if (keyCode == 27) {
   dpc.modoPadrao();
   dpc.removeDesenho();
   }
 
  // ctrl + z 68
  if (e.getModifiers() == 2 && (e.getKeyCode() == 90 || e.getKeyCode() == 68)) {
   if (dpc.undo())enviarEvento(new IgrafMenuPoligonoEvent(IgrafMenuPoligonoEvent.UNDO, this));;
   }

  // ctrl + r
  if (e.getModifiers() == 2 && (e.getKeyCode() == 82 || e.getKeyCode() == 89)) {
   if (dpc.redo()) enviarEvento(new IgrafMenuPoligonoEvent(IgrafMenuPoligonoEvent.REDO, this));
   }
  }

 public void undo () {
  dpc.undo();
  }

 public void redo () {
  dpc.redo();
  }

 public void mousePressed (MouseEvent e) {
  super.mousePressed(e); 
  if (mouseOverPoint && e.getButton() == MouseEvent.BUTTON1 && !freshMoving) {
   dpc.postNewEdit();  
   }
  }

 public void mouseMoved (MouseEvent e) {
  // se não estiver no modo de entrada de polígono, nem em
  // freshMoving, verifique se está sobre um vértice
  if (!dpc.modoCriacao && !freshMoving) {

   // se sim, transforme o cursor em mão
   mouseOverPoint = dpc.look4Point(xPixelToReal(e.getX()), yPixelToReal(e.getY()));
   if (mouseOverPoint) father.setCursor(hd);
   else father.setCursor(df);
   }

  if (freshMoving)
   dpc.dragPoint(e.getX(), e.getY());   
  }

 public void mouseDragged (MouseEvent e) {
  // se mouse sobre ponto, atualize suas coordenadas
  if (mouseOverPoint)
   dpc.dragPoint(e.getX(), e.getY()); 
  }

 public void mouseClicked (MouseEvent e) {
  if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
   if (dpc.modoCriacao) {
    dpc.insereVertice(e.getX(), e.getY());    
    }
   else
    if (mouseOverPoint)
    freshMoving = !freshMoving;
   }
 
  if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
   if (dpc.getCreationType() == dpc.POLYGON ||
      dpc.getCreationType() == dpc.S_POLYGON) {
      dpc.encerraModoEntrada();
    }
  }

 public void notifyScreenChanged () { }
 public int getCorDesenho (String funcao) {return 0; }
 public void iniciaAnimacao (boolean b) { }

 }
