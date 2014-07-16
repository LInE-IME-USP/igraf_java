/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 *
 * @description Class to evaluate functions
 * 
 * @version
 * 
 * @see igraf/basico/util/GeraPoligono.java
 * 
 * @author RP, LOB
 * 
 */

package igraf.basico.util;

import igraf.edu.hws.jcm.data.Expression;
import igraf.edu.hws.jcm.data.ParseError;
import igraf.edu.hws.jcm.data.Parser;
import igraf.edu.hws.jcm.data.Variable;

import igraf.IGraf;

public class Funcao implements Cloneable {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/basico/util/Funcao.java";

 private String funcao;
 private Expression expr, exprDerivada;
 private Variable x,y ; 
 private Parser parser;

 private static int contErrFx = 0;
 
 public Funcao (int var) {
  parser = new Parser(Parser.STANDARD_FUNCTIONS | Parser.OPTIONAL_STARS);

  parser.remove("trunc");
  parser.remove("round");

  x = new Variable("x"); // f(x)   - var != 2
  y = new Variable("y"); // f(x,y) - var  = 2

  parser.add(x);
  if (var==2) parser.add(y);
  }
 
 public int getNumVar () {
  return parser.get("y") == null ? 1 : 2;
  }
 
 public boolean constroiExpressao (String f) {
  funcao = f;
  try {
   expr = parser.parse(funcao);
  } catch(ParseError e) { // erro de entrada
   return false;
   }
  return true;
  }

 // calcula f(x)
 public double f (double xv) {
  x.setVal(xv);
  if (expr!=null)
   return expr.getVal(); // igraf.edu.hws.jcm.data.Expression expr
  if (contErrFx++<2) { // only 2 errors...
   System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: in expression evaluation at point " + xv + ": " + expr);
   //DEBUG: try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
   }
  return xv;
  }

 // calcula f(x,y)
 public double f (double xv, double yv) {
  if (parser.get("y")==null) {
   System.out.println("Problems, not working with 2 vars");
   return -1;
   }
  x.setVal(xv);
  y.setVal(yv);
  return expr.getVal();
  }
 
 // Apenas 1 var
 // deriva expressão entrada
 // ordem: 1 = 1ªDerivada , 2 = 2ªderivada 
 public void derivaFuncao (byte ordem) {
  exprDerivada = expr.derivative(x);
  if(ordem==2) exprDerivada = exprDerivada.derivative(x);
  }
 
 // Evaluate calcula f'(x) ou f''(x) 
 public double fD (double num) {
  x.setVal(num);
  return exprDerivada.getVal();
  }
 
 // Returns the original expression of this function (almost as it was entered)
 public String toStringFuncao () {
  return String.valueOf(expr);
  }
 
 // Returns the derivative of the function
 public String toStringDerivada () {
  return String.valueOf(exprDerivada);
  }
 
 public Object clone () {
  try {
   return super.clone();
  } catch (CloneNotSupportedException e) { return null; }
  }

 //T public static void mainD (String args[]) {  Funcao f = new Funcao(2);  f.constroiExpressao("x^2");  f.derivaFuncao((byte)1);  System.out.println("f(5) = " + f.f(5));  System.out.println("f'(5) = " + f.fD(5));  }
 
 }
