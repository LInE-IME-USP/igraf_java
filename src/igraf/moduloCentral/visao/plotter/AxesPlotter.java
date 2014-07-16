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
 * @date 22/05/2006
 *
 * @lastUpdate  25/10/2009
 * 
 * @description Classe que implementa a representação dos eixos coordenados do plano cartesiano;
 * encapsula as operações associadas às várias possíveis visualizações desses eixos.
 * 
 * @see igraf/moduloCentral/visao/plotter/Plotter.java
 * @see igraf/moduloCentral/visao/AreaDesenho.java: instantiate this class
 * 
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

import igraf.basico.io.ResourceReader;
import igraf.basico.util.Utilitarios;
import igraf.moduloCentral.eventos.EstadoTelaEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuEdicaoEvent;
import igraf.moduloCentral.modelo.Acao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

import difusor.evento.CommunicationEvent;


public class AxesPlotter extends Plotter {

 // Axes color
 //TODO: must allow "high contrast" => must pass AxesColor be changed (perhaps to white with blackbackgound)
 private static final Color AxesColor = new Color (150, 120, 0);  //cor eixos

 boolean  escalaVisivel = true,
 eixoVisivel   = true,
 gradeVisivel  = true;

 private boolean axesChanged = false;

 public void desenha (Graphics2D g, int w, int h) {
  super.dimensiona(g, w, h);
  clearScreen(g, w, h);
  desenha(g);
  }

 private void clearScreen (Graphics2D g2, int w, int h) {
  g2.setPaint(Color.white);
  g2.fillRect(0, 0, w, h);
  g2.setPaint(Color.black);
  }

 /**
  * Desenha os eixos coordenados com as configurações atuais.  Por padrão
  * esse desenho será feito com escala e seta.
  * @param g o contexto gráfico no qual será feito o desenho.
  * @param cx distancia entre a origem do plano cartesiano e o limite da
  *           área de desenho na direção horizontal dada em pixel's.
  * @param cy distancia entre a origem do plano cartesiano e o limite
  *           da área de desenho na direção vertical dada em pixel's.
  */
 private void desenha (Graphics2D g) {  
  if (isEixoVisivel()) 
   eixosCoordenados(g);

  if (isEscalaVisivel()) 
   escala(g);

  if (isGradeVisivel()) 
   grade(g);

  if (drawHotPoint)
   drawHotPoint(g);
  }

 /**
  * Modela uma grade baseada nos eixos coordenados.   A intersecção de
  * cada par de retas na tela representa um ponto de coordenadas
  * inteiras no plano cartesiano.
  * @param g o contexto gráfico no qual será feito o desenho.
  * @param cx distancia entre a origem do plano cartesiano e o limite da
  *           área de desenho na direção horizontal dada em pixel's.
  * @param cy distancia entre a origem do plano cartesiano e o limite
  *           da área de desenho na direção vertical dada em pixel's.
  */
 private void grade (Graphics2D g) {
  int passo = getPasso();

  g.setColor(Color.LIGHT_GRAY);

  int x = -passo;
  while (x >= xmin + getTranslationX()) {
   g.drawLine(normalizaX(x), 0, normalizaX(x), height);
   x -= passo;
   }

  int y = -passo;
  while (y >= ymin - getTranslationY()) {
   g.drawLine(0, normalizaY(y), width, normalizaY(y));
   y -= passo;
   }

  x = 0;
  while (x <= xmax + getTranslationX()) {
   g.drawLine(normalizaX(x), 0, normalizaX(x), height);     
   x += passo;
   }

  y = 0;
  while (y <= ymax - getTranslationY()) {
   g.drawLine(0, normalizaY(y), width, normalizaY(y));
   y += passo;
   }
  }



 /**
  * Desenha a escala dos eixos coordenados do plano cartesiano
  * @param g o contexto gráfico no qual será feito o desenho.
  * @param cx distancia entre a origem do plano cartesiano e o limite da
  *           área de desenho na direção horizontal dada em pixel's.
  * @param cy distancia entre a origem do plano cartesiano e o limite
  *           da área de desenho na direção vertical dada em pixel's.
  */
 private void escala (Graphics2D g) {    
  int x = getPasso();

  while (x <= xmax + getTranslationX()) {
   g.drawString(""+Utilitarios.precisao((float)x/escala), normalizaX(x), normalizaY(-12));     
   x += passo;
   }

  x = -passo;
  while(x >= xmin + getTranslationX()) {
   g.drawString(""+Utilitarios.precisao((float)x/escala), normalizaX(x), normalizaY(-12));     
   x -= passo;
   }

  int y = -passo;
  while (y >= ymin - getTranslationY()) {
   g.drawString(""+Utilitarios.precisao((float)y/escala), normalizaX(10), normalizaY(y)); 
   y -= passo;
   }

  y = passo;
  while (y <= ymax - getTranslationY()) {
   g.drawString(""+Utilitarios.precisao((float)y/escala), normalizaX(10), normalizaY(y)); 
   y += passo;
   }

  }


 private int getPasso () {
  int div = 1; 
  int k =  escala / passoMinimo;

  if (passo > 2*passoMinimo)
   div *= 2;

  passo = escala;
  return escala;
  }

 /**
  * Desenha os eixos coordenados do plano cartesiano sem escala ou setas.
  * @param g o contexto gráfico no qual serácontrasteInverteFace feito o desenho.
  * @param cx distancia entre a origem do plano cartesiano e o limite da
  *           área de desenho na direção horizontal dada em pixel's.
  * @param cy distancia entre a origem do plano cartesiano e o limite
  *           da área de desenho na direção vertical dada em pixel's.
  */
 private void eixosCoordenados (Graphics2D g ) {
  if (g == null) return;  
  g.setStroke(new BasicStroke(2));
  g.setColor(AxesColor);

  int meio = normalizaY(0);  
  g.drawLine(0,meio,width,meio);

  meio = normalizaX(0) ;      // Y
  g.drawLine(meio,0,meio,height);
  g.setStroke(new BasicStroke(1));
  g.setColor(Color.black);
  }


 /**
  * Desenha as setas dos eixos coordenados do plano cartesiano
  * @param g o contexto gráfico no qual será feito o desenho.
  * @param cx distancia entre a origem do plano cartesiano e o limite da
  *           área de desenho na direção horizontal dada em pixel's.
  * @param cy distancia entre a origem do plano cartesiano e o limite
  *           da área de desenho na direção vertical dada em pixel's.
  */
 private void desenhaSetas (Graphics2D g, int cx, int cy) {

  Polygon px = new Polygon();
  Polygon py = new Polygon();

  px.addPoint(cx-1, 0);
  px.addPoint(cx-8, -4);
  px.addPoint(cx-8, 4);

  py.addPoint(0, -cy+2);
  py.addPoint(-4, -cy+8);
  py.addPoint(4, -cy+8);

  g.fillPolygon(px);
  g.fillPolygon(py);

  g.drawString ("x", cx-8, -11);
  g.drawString ("y", 10, -cy+10);
  }


 private void drawHotPoint (Graphics2D g) {
  Color c = g.getColor();
  g.setColor(Color.yellow);
  g.fillOval(hotPointX-4, hotPointY-4, 8, 8); 
  g.setColor(Color.black);
  g.drawOval(hotPointX-4, hotPointY-4, 8, 8);
  g.setColor(c); 
  }


 public void alteraVisibilidadeGrade () {
  gradeVisivel = !gradeVisivel;
  enviarEvento(new IgrafMenuEdicaoEvent(this, IgrafMenuEdicaoEvent.CHANGE_GRID));
  }
 
 /**
  * @return <b> true </b> indicando que a grade deve ser desenhada
  */
 private boolean isGradeVisivel () {
  return gradeVisivel;
  }
 
 public void alteraVisibilidadeEixo () {
  eixoVisivel = !eixoVisivel;
  enviarEvento(new IgrafMenuEdicaoEvent(this, IgrafMenuEdicaoEvent.CHANGE_AXES));
  }

 /**
  * @return <b> true </b> indicando que os eixos devem ser desenhados
  */
 private boolean isEixoVisivel () {
  return eixoVisivel;
  }

 
 public void alteraVisibilidadeEscala () {
  escalaVisivel = !escalaVisivel;
  enviarEvento(new IgrafMenuEdicaoEvent(this, IgrafMenuEdicaoEvent.CHANGE_SCALE));
  }

 /**
  * @return <b> true </b> indicando que a escala deve ser desenhada
  */
 private boolean isEscalaVisivel () {
  return escalaVisivel;
  }

 private void gradePadrao () {
  eixoVisivel   = true;
  gradeVisivel  = true;
  escalaVisivel = true;
  }

 public void resetPlotter () {
  gradePadrao();
  }
 
 public void trataMenuEdicaoEvent (CommunicationEvent imec) { 

  if (imec.getCommand().equals(IgrafMenuEdicaoEvent.LOAD_INFO)) {
   IgrafMenuEdicaoEvent e = (IgrafMenuEdicaoEvent)imec;
   switch (e.getCodigoAcao()) {
    case Acao.exibirEixos : {eixoVisivel   = !eixoVisivel;  break; }
    case Acao.exibirGrade : {gradeVisivel  = !gradeVisivel; break; }
    case Acao.exibirEscala: {escalaVisivel = !escalaVisivel;break; }
    }
   }
  else
  if (imec.getCommand().equals(ResourceReader.msg("madZoomAmpliar"))) {
   zoom(zoomDif);
   notifyScreenChanged();
   }
  else
  if (imec.getCommand().equals(ResourceReader.msg("madZoomDiminuir"))) {
   zoom(-zoomDif);
   notifyScreenChanged();
   }
  else
  if (imec.getCommand().equals(ResourceReader.msg("madZoomPadrao"))) {
   zoomPadrao();
   notifyScreenChanged();
   }
  else
  if (imec.getCommand().equals(ResourceReader.msg("madEixosRemover")) || imec.getCommand().equals(ResourceReader.msg("madEixosExibir")) ) {
   alteraVisibilidadeEixo();
   notifyScreenChanged();
   }
  else
  if (imec.getCommand().equals(ResourceReader.msg("madEscalaRemover")) || imec.getCommand().equals(ResourceReader.msg("madEscalaExibir"))) {
   alteraVisibilidadeEscala();
   notifyScreenChanged();
   }
  else
  if (imec.getCommand().equals(ResourceReader.msg("madGradeRemover")) || imec.getCommand().equals(ResourceReader.msg("madGradeExibir"))) {
   alteraVisibilidadeGrade();
   notifyScreenChanged();
   }
  } 
 
 public void mouseDragged (MouseEvent e) {
  super.mouseDragged(e);
  axesChanged = true;
  }
 
 public void mouseMoved (MouseEvent e) {
  super.mouseMoved(e);
  axesChanged = true;
  }
 
 public boolean axesChanged () {
  return axesChanged;
  }
 
 // Indica que, depois de sofrer alguma alteração, os eixos já foram corretamente redesenhados
 public void axesFixed () {
  axesChanged =  false;
  }

 public void notifyScreenChanged () {
  axesChanged = true;
  }

 public int getCorDesenho (String funcao) { return 0; }
 public void iniciaAnimacao (boolean b) { }

 }
