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
 * @description  Recebe a string 'funcao' f(x) e desenha o gráfico de linha* da primitiva P(x), integral de f(x) calculado numericamente
 * 
 * @see
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloCentral.visao.desenho;

import igraf.moduloCentral.visao.plotter.Plotter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class DesenhoRegiaoPlano extends DesenhoPoligono {

 private float x1, y1, w, h;
 
 public DesenhoRegiaoPlano (Plotter plotter, float x1, float y1, int colorIndex, int vertexMode) {
  super(plotter, "[("+ x1 +","+ y1 +")]", colorIndex, true, vertexMode);
  this.x1 = x1;  this.y1 = y1;
  }
 
 public void atualizaDesenho (Graphics2D gr) {
  gr.setColor(Color.magenta);
  normaliza(polygonToBeDrawn);
  gr.fillPolygon(polygonToBeDrawn); // @see Desenho.java: protected Polygon polygonToBeDrawn
  super.atualizaDesenho(gr);
  }


 private void normaliza (Polygon pol) {
  for (int i = 0; i < p.npoints; i++) {
   pol.xpoints[i] = plotter.normalizaX(pol.xpoints[i])+1;
   pol.ypoints[i] = plotter.normalizaY(-pol.ypoints[i]-1);
   }
  }

 // public void insereVertice(float x2, float y2) { vertices = "[("+ x1 +","+ y1 +")("+ x1 +","+ y2 +")("+ x2 +","+ y2 +")("+ x2 +","+ y1 +")]"; }

 }
