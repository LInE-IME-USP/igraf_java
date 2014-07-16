/*
 * iGraf - interactive Graphics in the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author RP, LOB
 *
 * @description Classe que realiza as operações de análise da expressão digitada pelo
 * usuário visando a torná-la compreensível ao contexto do programa; gera,
 * ao final do processo o objeto (Polygon) que será desenhado na tela.
 * 
 * @version 23/05/2006
 * 
 * @see igraf/moduloCentral/visao/desenho/DesenhoFuncao.java
 * 
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 */

package igraf.basico.util;

import java.awt.Polygon;

import igraf.IGraf;

public class GeraPoligono {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/basico/util/GeraPoligono.java";

 static int start, end, // x axis
            cte, num = 0;

 static Funcao fnc = new Funcao(2);

 static Polygon polygonToDraw; // it will provides the path to draw the function's graphics

 static double y;

 static String funcaoDerivada;


 /** 
  * Recebe uma string 'posfix' contendo uma função matemática ,
  * substitui x por seu valor atual, calcula f(x) e cria o par (x, f(x)).
  * Devolve um objeto Polygon contendo todos os pares (x, f(x)) que vão
  * formar na área de desenho o gráfico da função representada por 'posfix'.
  **/

 private static boolean onePrint = false; //DEBUG
 //D 
static int contS = 0, contS2 = 0;

 /*
  * A escala utilizada é 64:1; assim, a unidade no eixo cartesiano representa
  * 64 pixels na tela. Desse modo, sendo "f(.)" a funcao a ser desenhada e sendo
  * "g(.)" sua representacão interna no iGraf: para desenhar o ponto (x,f(x))
  * será calculado (x,g(1/64)), já que escala == 64.
  * Considere uma função f, tal que f(1)= k. O ponto P na tela tem coordenadas (1, k),
  * mas já sabemos que cada unidade corresponde a 64 pixels, portanto, as coordenadas
  * em pixels reais serão P(64, k*64), por esse motivo é necessária a multiplicação do
  * valor de f(x) pela escala atual ANTES de desenhar esse ponto na tela. 
  */
 // ./igraf/moduloCentral/visao/desenho/DesenhoFuncao.java: public void atualizaDesenho(Graphics2D g): 
 public static Polygon getGrafico (String funcao, int start, int end, double escala) {

  if (funcao==null || funcao=="") {
   return null;
   }

  preparacao(funcao); //TODO: NAO usar String, eh preciso jah usar a funcao "digerida"!!

  //D //System.out.println(IGCLASSPATH + ": GeraPoligono.java: funcao="+funcao+", start="+start+", end="+end+", escala="+escala);
  //D if (contS2++%10==0) { System.out.println(IGCLASSPATH + ": getGrafico(...): (2) funcao="+funcao+" ["+start+","+end+"]"); }
  //D if (contS2++%15==0) IGraf.launchStackTrace();

  for (int x=start; x<end; x++) try {

   // f(x,y) - use "f(x/escala) * escala" in order to put graphic in visible window using a correct scale
   y = -fnc.f(x/escala); // Funcao fnc: uses igraf.edu.hws.jcm.data.Expression expr

   if (Double.isNaN(y))
    continue;

   addPoint(x, y * escala); // add point to 'polygonToDraw'
  } catch (Exception e) {
   if (!onePrint) {
    System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": Error: in polygon definition: func='" + funcao + "', fnc='" + fnc + "', " + funcaoDerivada);
    e.printStackTrace();
    onePrint = true;
    }
   // igraf/basico/util/GeraPoligono.java: Error: in polygon definition: {000,, null
   }

  return polygonToDraw;
  }


 //TODO: NAO esta em uso!!!!
 /*
 public static Polygon getGraficoDerivada (String funcao, int start, int end, double escala) {
  System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": getGraficoDerivada: func='" + funcao + "', fnc='" + fnc);

  if (funcao==null || funcao=="") {
   //System.err.println("Erro: funcao derivada vazia (interpolador de poligono): "+funcao+"");
   return null;
   }

  preparacao(funcao); //TODO: NAO usar String, eh preciso jah usar a funcao "digerida"!!

  fnc.derivaFuncao((byte)1); // Funcao fnc: uses igraf.edu.hws.jcm.data.Expression expr
  setFuncaoDerivada(fnc.toStringDerivada());

  for (int x = start; x < end; x += 2) {

   // fnc.fD is the function derivative
   y = -fnc.fD(x/escala);

   if (Double.isNaN(y))
    continue;

   addPoint(x, y * escala); // add point to 'polygonToDraw'
   }
  return polygonToDraw;
  } */

 private static void setFuncaoDerivada (String funcao) {
  funcaoDerivada = funcao;
  }

 public static String getFuncaoDerivada () {
  return funcaoDerivada;
  }

 public static String getDerivada (String funcao) {

  preparacao(funcao); //TODO: NAO usar String, eh preciso jah usar a funcao "digerida"!!

  fnc.derivaFuncao((byte)1);
  setFuncaoDerivada(fnc.toStringDerivada());
  return getFuncaoDerivada();
  }

 //TODO: NAO usar String, eh preciso jah usar a funcao "digerida"!!
 // From: igraf/moduloCentral/visao/desenho/DesenhoIntegral.java: atualizaDesenho(Graphics2D g): 'polygonToDraw = GeraPoligono.getGraficoAntiDerivada(funcaoAtual, getXMin(), getXMax(), getEscala());
 // Method implemented: http://en.wikipedia.org/wiki/Integral
 //  - Definitions:
 //    * I_a^b f(x)dx = integral of f(.), from a to b
 //    * S_{i=a}^b x_i = x_a + x_{a+1} + ... + x_{b-1} + x_b
 // 
 //  - I_a^b f(x)dx = lim_{n->oo} S_{i=0}^n f(x_i) (b-a)/n
 //  - F(x) is the antiderivative of f(.) in [a,b] => F(w) = I_a^w f(x)dx ~= S_{i=1}^N f(x_i) (w-a)/N (big N and x_0=a and x_N=w)
 //    F(w) = (w-a)/N * S_{i=1}^N f(x_i)
 // 
 public static Polygon getGraficoAntiDerivada (String funcao, int start, int end, double escala) {
  int cont = 0;
  int cte = 0; // this constant is used to make a correct y-shift to draw the function

  preparacao(funcao); // faz: y = 0; polygonToDraw = new Polygon();

  // F(w) = (w-a)/N * S_{i=1}^N f(x_i)
  //        a = start;
  //        w in [start, end]
  int w; // F(w)
  double doubleEscala = 1; ///escala;
  for (w=start; w<end; w++) { //
    // Numerical method: Riemann sumation
    double sum = 0;
    int count = 0; // number of rectangules
    for (int xi=start; xi<w; xi++) { // delta x = 1
      sum += -fnc.f(xi/escala); // Funcao fnc: uses igraf.edu.hws.jcm.data.Expression expr
      count++;
      }

    if (Double.isNaN(y))
      continue; // skip this point

    if (w==0) // Get the midpoint in order make a correct shift: this an heuristic to define the starting point x_0 of int_{w=x_0}^x
      cte = new Double(sum).intValue();

    addPoint(w, (w - start) * sum / count * doubleEscala);

    } // escala=" + escala

  polygonToDraw.translate(0, -cte); // move coordenate to the correct location

  return polygonToDraw;
  }

 private static void preparacao (String funcao) {
  y = 0;
  // polygonToDraw = new Polygon();
  try {
   if (!fnc.constroiExpressao(funcao)) {
    //System.err.println("GeraPoligono - Erro: em calculo de funcao "+funcao+": incorreta");
    return;
    }
   } catch (Exception e) {
   System.err.println("Erro: em interpolar funcao: "+funcao+": "+e);
   return;
   }
  polygonToDraw = new Polygon();
  }

 private static int contErrFx = 0;

 private static void addPoint (int x, double y) {
  if (polygonToDraw==null) {
   if (contErrFx++<2) // only 2 errors...
    System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in add point ("+x+","+y+") at polygon!");
   return;
   }
  polygonToDraw.addPoint(x, new Double(y).intValue());
  }

 // public static void mainD (String[] args)  {
 // String s = "sqrt(3)+cos(0)+3";  String t = "sqrt(2)";  Funcao f = new Funcao(1);
 // f.constroiExpressao(s);  System.out.println(f.f(0));
 // f.constroiExpressao(t);  System.out.println(f.f(0));  }

 }

