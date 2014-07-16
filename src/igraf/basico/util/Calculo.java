/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 *
 * @description  * Classe que implementa os métodos utilizados para que o iGraf
 * faça as operações de Cálculo Diferencial e Integral
 * 
 * @version first version of 04/07/2006
 * 
 * @see 
 * 
 * @author RP, LOB
 * 
 */

package igraf.basico.util;


import java.util.Vector;


public class Calculo {

  static double parteNegativaIntegral, partePositivaIntegral, dx;

  private static Vector
    listaNegativa,
    listaPositiva;

  /**
   * Calcula a integral definida da função f representada pela string 'f'
   * no intervalo de inf (limite inferior) a sup (limite superior) utilizando
   * o método dos trapézios.<br>
   * Recebe uma string contendo uma função na forma posfixada, o limite inferior
   * inf e o limite superior sup do intervalo de integração.   Devolve um valor
   * de precisão dupla que é a integral da função f calculada no intervalo
   * [inf .. sup].
   * @param posfix
   * @param inf
   * @param sup
   * @return
   */
  public static double integralDefinida (String f, double inf, double sup) {
    reset();

    int numIteracoes = 400;
    dx = (sup - inf)/numIteracoes;
    double fa, fb, soma = 0;

    Funcao funcao = new Funcao(1); // entrada/modelo/Funcao.java
    if (!funcao.constroiExpressao(f)) {
       System.err.println("Erro: em calculo de funcao "+f+": incorreta");
       return 0.0;
       }

    fa = funcao.f(inf);
    acumula(fa);

    for (int i = 1; i < numIteracoes; i++) {
        inf += dx;
        soma += acumula(funcao.f(inf));
        }

    fb = funcao.f(sup);
    acumula(fb);

    return  dx/2*(fa + fb + 2*soma);
    }


  /*
   * começa o cálculo; guarda o valor inicial
   * quando mudar de sinal, calcula a área para aquele intervalo
   * e acrescenta ao acumulador de mesmo sinal
   *
   * usar uma lista para guardar todos os valores e aplicar a fórmula
   * do trapézio aos mesmos deve funcionar para calcular a área da
   * subregião
   */
  private static double acumula (double d) {
    if (d < 0)
       listaNegativa.addElement(new Double(d));
    else
       listaPositiva.addElement(new Double(d));
    return d;
    }


  public static double getParteNegativaIntegral () {
   try {
     parteNegativaIntegral += ((Double)listaNegativa.elementAt(0)).doubleValue();
     parteNegativaIntegral += ((Double)listaNegativa.lastElement()).doubleValue();
   } catch (ArrayIndexOutOfBoundsException e) { return 0; }

   for (int i = 1; i < listaNegativa.size()-1; i++) {
     parteNegativaIntegral += 2*((Double)listaNegativa.elementAt(i)).doubleValue();
     }

   return dx*parteNegativaIntegral/2;
   }


  public static double getPartePositivaIntegral () {
   try {
     partePositivaIntegral += ((Double)listaPositiva.elementAt(0)).doubleValue();
     partePositivaIntegral += ((Double)listaPositiva.lastElement()).doubleValue();
   } catch (ArrayIndexOutOfBoundsException e) {return 0;}

   for (int i = 1; i < listaPositiva.size()-1; i++) {
      partePositivaIntegral += 2*((Double)listaPositiva.elementAt(i)).doubleValue();
      }

  return dx*partePositivaIntegral/2;
  }


  private static void reset () {
    parteNegativaIntegral = 0;
    partePositivaIntegral = 0;
    listaNegativa = new Vector(100);
    listaPositiva = new Vector(100);
    }

  // parte negativa = -156.2527  x  -160.656
  // parte positiva = 4.0004  x  4.285
  // total = -152.2523  x  152.276
  public static void mainD (String[] args) { //DEBUG
    double y;
    y = Calculo.integralDefinida("cos(x)", -1.57, 1.57);
    String s = Utilitarios.precisao(y);

    System.out.println("parte positiva: " + Utilitarios.precisao(getPartePositivaIntegral()));
    System.out.println("parte negativa: " + Utilitarios.precisao(getParteNegativaIntegral()));
    System.out.println("int [0..1] = " + s);
    }

  }
