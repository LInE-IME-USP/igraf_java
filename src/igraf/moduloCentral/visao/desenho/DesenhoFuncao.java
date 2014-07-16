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
 * @description Draws animation to functions with parameters
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

import igraf.basico.util.GeraPoligono;
import igraf.moduloCentral.visao.plotter.Plotter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class DesenhoFuncao extends Desenho {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/visao/desenho/DesenhoFuncao.java";

 protected String funcaoOriginal, funcaoAtual, dominio = "", comentario = "";
 private boolean dominioDefinido;
 private int intervalMinimumInt , intervalMaximumInt;
 private double intervalMinimumScale, intervalMaximumScale; // minimum and maximum after adjust by the scale

 private String functionID = Desenho.getNewId(); // function name 'A', 'B', ... 'Z', 'FA', 'FB', ...

 private boolean aIndefinido, bIndefinido;

 public DesenhoFuncao (Plotter plotter, String funcao, int colorIndex) {
  this(plotter, funcao, colorIndex, funcao);
  }

 public DesenhoFuncao (Plotter plotter, String funcao, int colorIndex, String desc) {
  super(plotter, colorIndex);
  if (funcao!=null)
    funcao = funcao.trim();
  funcaoOriginal = funcao;
  funcaoAtual = funcao;
  //L System.out.println("DesenhoFuncao.DesenhoFuncao: id="+id+": "+funcao);
  //R setDescricao(getFunctionName() +"(x) = " + desc);
  setDescricao(functionID +"(x) = " + desc);
  }


 protected String getFunctionName () {
   return functionID; // requested in 'DesenhoAnimacao' constructor to define its name
   }

 //_ // Build the function name: like 'A' to a function with description 'A(x) = x'
 //_  private static String getFunctionName () {
 //_   if (id == 91) // last letter => add a prefix to the function name
 //_    id = 65;
 //_   functionID = (char) id++; // defined in 'Desenho': used to define function name: staring from the first letter in ASCII table
 //_   return Desenho.getPrefix() + functionID;
 //_   } 

 // Set function domain (optional): "x [-1,2]" will set 'dominio="[-1,2]"'
 public void setDominio (String domFromTextField) {
  //T System.out.println(IGCLASSPATH + ": setDominio(String): domFromTextField="+domFromTextField);
  //T if (domFromTextField==null) try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

  if (domFromTextField == null) return;

  int k = domFromTextField.indexOf(']');
  if (k == -1) return;

  int vg = domFromTextField.indexOf(',');
  try {
   String xMin = domFromTextField.substring(1, vg).trim();   // minimum from the interval
   String xMax = domFromTextField.substring(vg+1, k).trim(); // maximum from the interval

   if (xMin.length() == 0) {
    intervalMinimumScale =  getXMin()/getEscala();
    aIndefinido = true;
    }
   else
    intervalMinimumScale = Double.valueOf(xMin).doubleValue();

   if (xMax.length() == 0) {
    intervalMaximumScale =  getXMax()/getEscala();
    bIndefinido = true;
    }
   else
    intervalMaximumScale = Double.valueOf(xMax).doubleValue();

  } catch (NumberFormatException e) { e.printStackTrace(); }

  dominioDefinido = true;

  this.dominio = domFromTextField;
  } // public void setDominio(String domFromTextField)


 // Function with no parameters
 public void atualizaDesenho (Graphics2D gr) {
  Polygon pol_aux = null; //L
  // System.out.println("\nsrc/igraf/moduloCentral/visao/desenho/DesenhoFuncao.java: atualizaDesenho(): funcaoAtual="+funcaoAtual);

  if (dominioDefinido) { // "x [-1,1]" => 'dominioDefinido' is 'true' and 'intervalMinimumInt' is -1 and 'intervalMaximumInt' is 1
   intervalMinimumInt = aIndefinido ? getXMin() : (int)(intervalMinimumScale * getEscala());
   intervalMaximumInt = bIndefinido ? getXMax() : (int)(intervalMaximumScale * getEscala());
   pol_aux = GeraPoligono.getGrafico(this.funcaoAtual, intervalMinimumInt, intervalMaximumInt, getEscala());
   }
  else { // getXMin() (or getXMax()) in 'Desenho.getXMin()' and 'Plotter.getXMin()'
   // getXMin() = Desenho.java: plotter.getXMin() / getXMax() = Desenho.java: plotter.getXMax()
   pol_aux = GeraPoligono.getGrafico(this.funcaoAtual, getXMin(), getXMax(), getEscala());
   }

  if (pol_aux==null) {
   // System.out.println("\nError: DesenhoFuncao.atualizaDesenho tentando atribuir poligono vazio a "+super.id+": "+this.funcaoAtual);
   // igraf.basico.util.Utilitarios.rastrearError(); //L
   return;
   }
  polygonToBeDrawn = pol_aux;  // @see Desenho.java: protected Polygon polygonToBeDrawn
  renderiza(polygonToBeDrawn, gr);
  }


 public boolean editaGrafico (String funcao, Color cor) {
  try {
   setColor(cor);
  } catch (Exception e) {}

  String aux = ""; //verificaFuncao(funcao);

  if (aux!= null & aux.length()>0 & !aux.equals(this.funcaoAtual)) {
   this.funcaoAtual = aux;
   return true;
   }

  if (aux.equals(this.funcaoAtual) & dominioDefinido)
   return true;

  return false;
  }


 /**
  * @return a função que este objeto exibe na tela
  */
 public String getFuncaoAtual () {
  return funcaoAtual;
  }

 public String getFuncaoOriginal () {
  return funcaoOriginal;
  }

 public String toString () {
  if (dominio.length()==0)
   return funcaoAtual;
  return funcaoAtual + "  " + dominio;
  }

 public void mudaFuncao (String novaFuncao) {
  //L String funcao0 = this.funcaoAtual;
  funcaoAtual = novaFuncao;
  setDescricao(functionID + "(x) = " + funcaoAtual);
  //L System.out.println("DesenhoFuncao.mudaFuncao: "+id+" | de "+funcao0+" -> "+funcaoAtual);
  }

 }
