/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author RP, LOB
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 * @description Treat text in drawing area
 *
 * @see
 *
 */

package igraf.moduloCentral.eventos;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import igraf.basico.util.EsquemaVisual;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloCentral.visao.desenho.DesenhoTexto;
import igraf.moduloJanelasAuxiliares.eventos.ModuloJanelasDisseminavelEvent;
import difusor.evento.CommunicationEvent;


public class DesenhoTextoEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, ModuloJanelasDisseminavelEvent, EventoRegistravel {

 public static final String INSERT_TEXT = "insertText";
 public static final String ERASE_TEXT = "eraseText";
 public static final String EDIT_TEXT = "editText";
 public static final String OPEN_EDITOR = "openEditor";
 public static final String UPDATE_POSITION = "updatePosition";

 private static final int
  cR2 = EsquemaVisual.cR2, cG2 = EsquemaVisual.cG2, cB2 = EsquemaVisual.cB2, //      2:  r=  0; g=  0; b=200;
  cR3 = EsquemaVisual.cR3, cG3 = EsquemaVisual.cG3, cB3 = EsquemaVisual.cB3, //      3:  r=  0; g=200; b=  0;
  cR4 = EsquemaVisual.cR4, cG4 = EsquemaVisual.cG4, cB4 = EsquemaVisual.cB4, //      4:  r=  0; g=200; b=200;
  cR5 = EsquemaVisual.cR5, cG5 = EsquemaVisual.cG5, cB5 = EsquemaVisual.cB5, //      5:  r=  0; g=100; b=  0;
  cR1 = EsquemaVisual.cR1, cG1 = EsquemaVisual.cG1, cB1 = EsquemaVisual.cB1; // others:  r=  0; g=  0; b=  0;

 Font font;
 String textoAtual, textoOriginal = null;
 Color textColor;

 float x, y;
 Vector listaEdicao;
 DesenhoTexto dt;
 private int fontSize = 10, index;
 private boolean versaoAntiga = false;
 public static int textId;

 public DesenhoTextoEvent (Object source) {
  super(source);
  }

 // construtor usado para recuperação de texto do arquivo
 public DesenhoTextoEvent (Object source, String arg, String cmd) {
  this(source);
  setCommand(cmd);

  if (cmd.equals(ERASE_TEXT))
   setEraseIndex(arg);
  else
  if (cmd.equals(INSERT_TEXT))
   inserirTexto(arg);
  }

 private void setEraseIndex (String arg) {
  int ini = arg.indexOf("i:");
  index = Integer.parseInt(arg.substring(ini+2));
  }

 public int getEraseIndex () {
  return index;
  }

 public boolean versaoAntiga () {
  return versaoAntiga;
  }

 public void clearVersao () {
  versaoAntiga = false;
  }

 public DesenhoTextoEvent (Object source, DesenhoTexto dt) {
  super(source);
  this.dt = dt;
  setData(dt);
  setCommand(DesenhoTextoEvent.EDIT_TEXT);
  }

 public void setDesenhoTexto (DesenhoTexto dt) {
  this.dt = dt;
  setData(dt);
  }

 public DesenhoTexto getDesenhoTexto () {
  return dt;
  }


 public String getTextoAtual () {
  return textoAtual;
  }

 public void setTextoAtual (String text) {
  this.textoAtual = text;

  if (textoOriginal == null)
  textoOriginal = text;
  }

 public void editarTexto (String text) {
  textoOriginal = textoAtual;
  textoAtual = text;
  }

 public String getTextoOriginal () {
  return textoOriginal;
  }

 public void setTextPosition (float x, float y) {
  this.x = x;
  this.y = y;
  }

 public float getTextPositionX () {
  return x;
  }

 public float getTextPositionY () {
  return y;
  }

 public Font getFont () {
  return font;
  }

 public void setFont (Font font) {
  this.font = font;
  }

 public Color getFontColor () {
  return textColor;
  }

 public void setFontColor (Color fontColor) {
  this.textColor = fontColor;
  }

 public void setTextList (Vector listaEdicao) {
  this.listaEdicao = listaEdicao;
  }

 public Vector getListaEdicao () {
  return listaEdicao;
  }

 private void setData (DesenhoTexto dt) {
  setFontSize(dt.getFontSize());
  setFontColor(dt.getFontColor());
  setTextoAtual(dt.getText());
  setTextPosition(dt.getX(), dt.getY());
  }

 //  r:255 g:0 b:0 x:0 y:0 tamanho:20 texto:xxx
 public String getArgumento () {

  //REVIW: must be reviwed...
  // fix it refactoring Sessao.atualizaTexto()
  if (getCommand().equals(DesenhoTextoEvent.ERASE_TEXT)) {
   textoOriginal = " ";
   return " ";
   }

  String r     = "r:" + String.valueOf(getFontColor().getRed()) + " ";
  String g     = "g:" +  String.valueOf(getFontColor().getGreen()) + " ";
  String b     = "b:" +  String.valueOf(getFontColor().getBlue()) + " ";
  String X     = "x:" + String.valueOf(x)+ " ";
  String Y     = "y:" + String.valueOf(y)+ " ";
  String texto = "texto:" + textoAtual + " "; // must be unique, do not use 'ResourceReader.msg("msgTexto")'
  String tamanho  = "tamanho:" + fontSize + " ";  // must be unique, do not use 'ResourceReader.msg("msgTam")'

  return r + g + b + X + Y + tamanho + texto;
  }

 public int getCodigoAcao () {
  String strCommand = getCommand();
  if (strCommand.equals(DesenhoTextoEvent.EDIT_TEXT))
   return Acao.editarTexto;
  
  if (strCommand.equals(DesenhoTextoEvent.ERASE_TEXT))
   return Acao.apagarTexto;

  if (strCommand.equals(DesenhoTextoEvent.INSERT_TEXT))
   return Acao.inserirTexto;

  if (strCommand.equals(DesenhoTextoEvent.UPDATE_POSITION))
   return Acao.atualizarPosicaoTexto;

  return -1;
  }


 /**
  * Recebe a String arg que contém todos os argumentos para a inserção de texto
  * na área de desenho.   Método específico para a reconstrução dos gráficos com
  * o uso do histórico ou a partir da leitura de um arquivo.
  * @param arg
  */
 public void inserirTexto (String arg) {
  int ini = 0, fim = 0,
  r = 0, g = 0, b = 0, size = 10;

  float x = 0, y = 0;
  //T System.out.println("\nsrc/igraf/moduloCentral/eventos/DesenhoTextoEvent.java: inserirTexto(" + arg + ")");

  try {
   ini = arg.indexOf("r:");
   fim = arg.indexOf(" ", ini);
   r = Integer.parseInt(arg.substring(ini+2, fim));

   ini = arg.indexOf("g:");
   fim = arg.indexOf(" ", ini);
   g = Integer.parseInt(arg.substring(ini+2, fim));

   ini = arg.indexOf("b:");
   fim = arg.indexOf(" ", ini);
   b = Integer.parseInt(arg.substring(ini+2, fim));
  } catch (Exception excpt) {
   // Até versão 1.8.6 sintaxe (de 18/01/2008) era: "cor: 5" e não 'r:255 g:0 b:0'
   //  - em 'TextInput.setColor(int i)' fazia '2: Color.blue', '3: Color.green', '4: Color.magenta', '5: Color.red', '0: Color.black'
   try { // tenta versao até 1.8.6
    ini = arg.indexOf("cor:");
    int icor = Integer.parseInt(arg.substring(ini+4, ini+5));
    if (icor==2) { r = cR2; g = cG2; b = cB2; } // 0,  0,200
    else
    if (icor==3) { r = cR3; g = cG3; b = cB3; } // 0,200,  0
    else
    if (icor==4) { r = cR4; g = cG4; b = cB4; } // 0,100,100
    else
    if (icor==5) { r = cR5; g = cG5; b = cB5; } // 0,100,  0
    else         { r = cR1; g = cG1; b = cB1; } // 0,  0,  0
    //System.out.println("\nsrc/igraf/moduloCentral/eventos/DesenhoTextoEvent.java: inserirTexto(" + arg + "): " + icor);

   } catch (Exception excpt2) {
    System.err.println("Error: during a text insertiong: the argument "+arg+" is out of standart: " + excpt2);
    excpt2.printStackTrace();
    }
   }

  try {
   ini = arg.indexOf("x:");
   fim = arg.indexOf(" ", ini);
   String sx = arg.substring(ini+2, fim);
   if (sx.indexOf(".") == -1) versaoAntiga = true;

   x = Float.parseFloat(sx);

   ini = arg.indexOf("y:");
   fim = arg.indexOf(" ", ini);
   y = Float.parseFloat(arg.substring(ini+2, fim));

   ini = arg.indexOf("tamanho:"); // must be unique, do not use 'ResourceReader.msg("msgTam")'
   fim = arg.indexOf(" ", ini);
   size = Integer.parseInt(arg.substring(ini+8, fim));
  } catch (Exception excpt3) {
   System.err.println("Error: during a text insertiong: problem with the description of its position or size: "+arg+": " + excpt3);
   excpt3.printStackTrace();
   }
  try {
   ini = arg.indexOf("texto:");
   fim =  arg.indexOf(" ", ini);
   textoAtual = arg.substring(ini+6);
  } catch (Exception excpt4) {
   System.err.println("Error: during a text insertiong: it was found a problem inside the text: "+arg+": " + excpt4);
   excpt4.printStackTrace();
   }
  setFontColor(new Color(r,g,b));
  setTextPosition(x, y);
  setTextoAtual(textoAtual);
  setFontSize(size);
  }

 public void setFontSize (int fontSize) {
  this.fontSize  = fontSize;
  }

 public void setTextoOriginal (String texto) {
  textoOriginal = texto;
  }

 public int getFontSize () {
  return fontSize;
  }

 public String getDescription () {
  return objetivo("notify iGraf about the insertion of text into the drawing area");
  }

 }
