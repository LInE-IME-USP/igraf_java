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
 * @description Desenha integral entre curvas
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

package igraf.moduloCentral.visao.desenho;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import igraf.IGraf;
import igraf.basico.util.Funcao;
import igraf.moduloCentral.visao.plotter.Plotter;


public class DesenhoRegiaoIntegral extends Desenho {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/desenho/DesenhoRegiaoIntegral.java";

 private double start, end; // x axis
 private double escala;
 private int fx, gx;

 private Color corPositiva = new Color(180,100,255),
               corNegativa = new Color(255,100,180),
               lastColor;

 private Funcao funcaoDRI;
 private String[] listaFuncoes = new String[2];
 private double[] listaResultados = new double[2];
 private boolean pintaAreaTotal;

 private String expr0, expr1;


 public DesenhoRegiaoIntegral (Plotter plotter, String[] listaFuncoes, double start, double end, boolean pintaAreaTotal) {
  super(plotter, 0);

  if (polygonToBeDrawn==null) // @see Desenho.java: protected Polygon polygonToBeDrawn
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: DesenhoRegiaoIntegral.DesenhoRegiaoIntegral: "+listaFuncoes);

  expr0 = listaFuncoes[0];
  expr1 = listaFuncoes[1];

  this.pintaAreaTotal = pintaAreaTotal;
  funcaoDRI = new Funcao(1);
  atualizaEscala();
  ordenaLimitesDeIntegracao(start, end);
  }


 // usar um objeto list para selecionar as express�es resolve o problema de espa�o
 // uma caracter�stica interessante da ordena��o � que insere NAN na �ltima posi��o

 private void calculaOrdenadaListaFuncao (double x) {
  for (int i = 0; i < 2; i++) {
   funcaoDRI.constroiExpressao(getListaFuncao()[i]);
   listaResultados[i]= -funcaoDRI.f(x/escala)*escala;
   }
  fx = (int) listaResultados[0];
  gx = (int) listaResultados[1];
  }

 private String[] getListaFuncao () {
  // se for anima��o, pegar a fun��o atual e colocar na listaFuncoes
  // sen�o, passar a lista sem altera��es
  listaFuncoes[0] = expr0;
  listaFuncoes[1] = expr1;
  return listaFuncoes;
  }

 // apenas para evitar problemas caso o usu�rio digite um intervalo decrescente
 private void ordenaLimitesDeIntegracao (double start, double end) {
  if (end < start) {
   double aux = end;
   end = start;
   start = aux;
   }
  this.start = start;
  this.end = end;
  }


 public void desenhaRegiao (Graphics gr) {
  lastColor = gr.getColor();
  gr.setColor(corPositiva);
  for (double x = start*escala; x <= end*escala; x += 4) {   
    calculaOrdenadaListaFuncao(x);
    gr.drawLine(plotter.normalizaX((int)x), plotter.normalizaY(-fx), plotter.normalizaX((int)x), plotter.normalizaY(-gx));
    }
  gr.setColor(lastColor);
  }


 public void desenhaRegioesDistintas (Graphics2D gr) {
  lastColor = gr.getColor();
  gr.setColor(corPositiva);
  for (double x = start*escala; x <= end*escala; x += 4) {
    calculaOrdenadaListaFuncao(x);
    pintaSegmento(gr, (int)x, fx, gx);
    pintaSegmento(gr, (int)x, gx, fx);
    }
  gr.setColor(lastColor);
  }

 private void pintaSegmento (Graphics2D gr, int x, int y1, int y2) {
  gr.setColor(y1 < 0 ? corPositiva : corNegativa);

  if (y1*y2 > 0)
   gr.drawLine(plotter.normalizaX((int)x), plotter.normalizaY(-y1), plotter.normalizaX((int)x), plotter.normalizaY(-y2));
  else
   gr.drawLine(plotter.normalizaX((int)x), plotter.normalizaY(-y1), plotter.normalizaX((int)x), plotter.normalizaY(-0));
  }

 public void atualizaDesenho (Graphics2D gr) {
  atualizaEscala();
  if (pintaAreaTotal)
   desenhaRegiao(gr);
  else
   desenhaRegioesDistintas(gr);
  }

 /**
  * Alterna entre a pintura da �rea (toda a regi�o da mesma cor) e da integral (cores
  * distintas para regi�es acima ou abaixo do eixo x)
  * @param b
  */
 public void alteraModoPintura (boolean b) {
  pintaAreaTotal = b;
  }

 private void atualizaEscala () {
  escala = getEscala();
  }

 public String toString () {
  return "";
  }

 public String getFuncaoAtual () { return ""; }
 //public String getFuncaoAtual () { return funcaoAtual; }
 
 public String getDescricao () {
  return "�rea de integra��o entre as curvas: f(x) = " + listaFuncoes[0] + " e g(x) = " + listaFuncoes[1];
  }

 }
