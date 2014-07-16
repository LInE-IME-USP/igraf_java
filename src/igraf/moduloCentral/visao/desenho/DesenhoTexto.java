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


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.StringTokenizer;

import igraf.moduloCentral.visao.plotter.Plotter;
import igraf.basico.util.EsquemaVisual; // get colors


public class DesenhoTexto extends Desenho {

 private static final Font FONT = new Font("Arial", Font.PLAIN, 11);

 private String strTextInArea; // the string with the text information

 private int line, fontSize = 11, width = 0;
 private int left, top, height, id;
 float x, y;
 
 private FontMetrics fm;
 private Color colorFont, fontColor = Color.black, corMoldura = Color.lightGray;

 private Font font = FONT, font2;

 // Main class
 public DesenhoTexto (Plotter plotter, String str, int ordem) {
  super(plotter, 1);
  setText(str);
  id = ordem;
  }

 public String getText () {
  return this.strTextInArea;
  }

 public void setText (String str) {
  this.strTextInArea = str;
  }

 public void setFontColor (Color color) {
  fontColor = color;
  }
 
 public Color getFontColor () {
  return fontColor;
  }

 public void setFontSize (int size) {
  this.fontSize = size;
  font = new Font("Arial", Font.PLAIN, size);
  }

 public int getFontSize () {
  return fontSize;
  }
 
 public void setFrameColor (Color color) {
  corMoldura = color;
  }

 private boolean destaca = false;
 private void setDestaca (boolean bool) { destaca = bool;  }

 //D private static int count=0;


 // Origin in: igraf.moduloCentral.visao.plotter.GraphPlotter.atualizaDesenho(GraphPlotter.java:214)
 // after    : igraf.moduloCentral.visao.AreaDesenho.paintComponent(AreaDesenho.java:177) -> setGraphBuffer(...) -> GraphPlotter.desenha(...) -> ...
 private void enquadraString (String str0, Graphics2D gr) {
  //D if (count==0 || count==5)  try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
  //D count++;

  String str = str0;
  fm = gr.getFontMetrics();
  line = fm.getHeight();
  StringTokenizer st = new StringTokenizer(str, "\n");
  String strAuxToDraw;

  while (st.hasMoreTokens()) {
   strAuxToDraw = st.nextToken();
   
   if (destaca) {
    // mouse is over a text: highlight it
    gr.setColor(EsquemaVisual.corHighlightText); // Color.green
    gr.drawString(strAuxToDraw, left, top+height);
    }
   else {
    gr.setColor(fontColor);
    gr.drawString(strAuxToDraw, left, top+height);
    }
   width = Math.max(width, fm.stringWidth(strAuxToDraw)+10); //leo width = Math.max(width, fm.stringWidth(s)+10);
   height += line;
   } 
  if (destaca)
   desenhaBorda(gr);
  }


 // Draw a rectangle around a text under the mouse
 private void desenhaBorda (Graphics2D gr) {
  if (borda) { // draw a border around the text under the mouse/pointer
   Color aux = gr.getColor();
   gr.setColor(EsquemaVisual.corHighlightTextBorder); // Color.magenta
   gr.drawRect(left-4, top-line+10, width, height);
   gr.setColor(aux);
   }
  }
 
 // delimitar o texto em um retângulo
 // fazer um string tokenizer que saiba o tamanho máximo e o tamanho do
 // texto do usuário... ao desenhar, medir cada substring, guardar o
 // quanto já foi escrito e quando não couber mais, trocar de linha
 // aumentar a altura a cada mudança de linha e desenhar o retângulo
 // por último com a altura determinada dinamicamente.
 public void atualizaDesenho (Graphics2D gr) {
  line = 0; height = 15;

  colorFont = gr.getColor(); 
  font2 = gr.getFont(); 

  gr.setFont(font);
  updatePosition();
  enquadraString(this.strTextInArea, gr);

  gr.setColor(colorFont);
  gr.setFont(font2); 
  }


 public void updatePosition () {
  left = plotter.normalizaX((int)(x * plotter.getEscala()));
  top  = plotter.normalizaY((int)(y * plotter.getEscala()));
  }

 public void setPosition (float x, float y) {  
  this.x = x;
  this.y = y;  
  }

 public float getX () {
  return x;
  }

 public float getY () {
  return y;
  }

 public void setFont (Font font) {
  if (font != null)
   this.font = font;
  else
   this.font = font;
  }

 public Font getFont () {
  return font;
  }

 public String getFuncaoAtual () {
  return "";
  }


 boolean borda;
 public boolean matchCoordinates (int x, int y) {
  x = plotter.normalizaX(x);
  y = plotter.normalizaY(-y);
  
  if (x >= left-2 && x < left+width-8 && y >= top+5 && y < top+height+2) { 
   destacar(true);   
   return true; 
   }
  destacar(false);
  return false;
  }
 
 private void destacar (boolean b) {
  borda = b;
  destaca = b;
  }
 
 public void setTextSelected () {
  setFontColor(Color.gray);
  }

 public int getTextIndex () {
  return id ;
  }  
 
 public String toString () {
  return this.strTextInArea;
  }

 }
