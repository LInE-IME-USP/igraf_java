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
 * @description  Recebe a string 'funcao' f(x) e desenha o gr�fico de linha* da primitiva P(x), integral de f(x) calculado numericamente
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

package igraf.moduloCentral.visao.desenho;


import java.awt.Graphics2D;

import igraf.basico.util.Funcao;
import igraf.basico.util.GeraPoligono;
import igraf.basico.util.Utilitarios;
import igraf.moduloCentral.eventos.DesenhoTangenteEvent;
import igraf.moduloCentral.visao.plotter.Plotter;


public class DesenhoTangente extends DesenhoFuncao {

 private double valorX, valorY, coefAngular, 
 coefLinear, i = 0.04;

 private String equacaoReta;

 private Funcao f;
 private boolean animacao;

 Plotter plotter;

 public DesenhoTangente (Plotter plotter, String funcao, int colorIndex, double valorX) {
  super(plotter, funcao, colorIndex); //, " \" derivada de f(x) = " + funcao 
  this.plotter = plotter;

  f = new Funcao(1);
  f.constroiExpressao(funcao);
  f.derivaFuncao((byte)1);
  setValorX(valorX);
  }

 public void setValorX (double valorX) {
  this.valorX = valorX;

  calculaCoeficientes();
  determinaEquacao();
  }

 private void calculaCoeficientes () { 
  valorY = f.f(valorX);
  coefAngular = f.fD(valorX);   
  coefLinear = valorY - coefAngular*valorX;

  }

 private void determinaEquacao () {
  String sinal = "+";
  String b = Utilitarios.precisao(coefLinear);

  if(b.indexOf(45) != -1){
   sinal = null; 
   sinal = "";
   }
  equacaoReta = Utilitarios.precisao(coefAngular) + " * x "+
  sinal + " " + b;
  }

 public String getEquacaoReta () {
  return equacaoReta;
  }

 public String getValorX () {
  return Utilitarios.precisao(valorX);
  }

 public String getFx () {
  return Utilitarios.precisao(valorY);
  }


 // Update function
 public void atualizaDesenho (Graphics2D gr) {
  animar();

  polygonToBeDrawn = GeraPoligono.getGrafico(equacaoReta,  getXMin(), getXMax(), getEscala());
  renderiza(polygonToBeDrawn, gr);
  }


 private void animar () {
  int k = 0;
  if (animacao) { 
   setValorX(valorX += i); 
   if (valorX < getXMin()/(double)getEscala() || valorX > getXMax()/(double)getEscala())
    i = -i; 
   }
  plotter.enviarEvento(new DesenhoTangenteEvent(this, getEquacaoReta(), getValorX(), getFx()));
  }

 public void setAnimacao (boolean b) {
  animacao = b;
  }
 
 public String getFuncaoAtual () {
  return equacaoReta;
  }

 }
