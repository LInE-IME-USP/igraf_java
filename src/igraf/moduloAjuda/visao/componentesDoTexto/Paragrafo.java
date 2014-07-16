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
 * @description Help integrated to iGraf.
 * 
 * @see igraf/moduloAjuda/visao/Help.java: starting point of this iGraf manual
 * @see igraf/moduloAjuda/visao/componentesDoTexto/ComponenteDoTexto.java: base to Paragrafo.java (and Topico.java)
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.visao.componentesDoTexto;

import igraf.moduloAjuda.visao.Help;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Paragrafo extends ComponenteDoTexto {

  private static final java.awt.Color COLORFACE = java.awt.Color.black;

  private FontMetrics fm;
  private final int BSFNT = Help.BASICFONTSIZE; // basic font size of paragraphs
  private int lineHeight;
  private String rootTextName;  
  private Font font;
  private JTextArea jTextF;

  //TODO: Here it is used the hypothesis that, usually, each character has height equals to the half of it font.
  //TODO: This works but must be changed to someghing more stable.
  //From: Paragrafo paragrafo = new Paragrafo(text, largura);
  // @param String text: text to be inserted as paragraph
  // @param int largura: the width maximal to be used - actually it is fixed, I will use its source at Help.TOPICSWIDTH = 620 (see ComponenteDoTexto.WIDTHPARAGRAPH)
  public Paragrafo (String text, int largura) {
    super(largura);
    // setLayout(new BorderLayout()); ja em 'ComponenteDoTexto.java' - inserido em 'igraf/moduloAjuda/modelo/JPanelBasisTopic.java'
    //T System.out.println("Paragrafo: " + text);
    this.rootTextName = text;
    font = new Font("Arial", Font.PLAIN, BSFNT);
    setFont(font);

    // To ensure the same background color use the same color in:
    // - 'igraf/moduloAjuda/visao/navegador/PainelConteudo.java'
    // - 'igraf/moduloAjuda/modelo/JPanelBasisTopic.java'
    // - 'igraf/moduloAjuda/visao/componentesDoTexto/Topico.java'
    setBackground(EsquemaVisual.corFundoParagrafos);
    altura = (text.length()*(BSFNT/2)/(WIDTHPARAGRAPH)+3)*BSFNT;
    setParagraph();
    }

  public void setText (String text) {
    this.rootTextName = text;
    }


  //ST private static int cont=0, NUMP = 63;
  //DEBUG: test the biggest paragraph (Sintax - Introduction - cont=63)
  //DEBUG: WIDTHPARAGRAPH=568
  private void setParagraph () {
    String strAuxLine = "", strAux = "", strLine = "", strProcessedText = "";
    String strText = ResourceReader.msg(rootTextName);
    StringTokenizer stLines, st;
    int countLines = 0, lineWidth;
    int n;

    fm = getFontMetrics(this.getFont());
    //ST if (cont==NUMP) System.out.println("Paragrafo: largura="+largura+", margem="+margem+", #=" + strText.length() + "\n" + strText);

    // Lines
    stLines = new StringTokenizer(strText, "\n", false); // get the text after ResourceReader
    while (stLines.hasMoreTokens()) {

      // Each line
      strAuxLine = stLines.nextToken();
      //ST if (cont==NUMP) System.out.println("---------\n " + countLines + ": " + strAuxLine);

      st = new StringTokenizer(strAuxLine, " ", true);
      lineWidth = 0;
      n = 1;

      while (st.hasMoreTokens()) {
        strAux = st.nextToken();
        int sizeToken = fm.stringWidth(strAux);
        if (lineWidth + (2*sizeToken) > WIDTHPARAGRAPH) {
           //ST if (cont==NUMP && countLines==0) System.out.println("\n n=" + n + ": '" + strAux + "': lineWidth=" + lineWidth + ", sizeToken=" + sizeToken + ", WIDTHPARAGRAPH=" + WIDTHPARAGRAPH + "");
           strProcessedText += strLine + "\n";
           sizeToken = lineWidth = 0;
           strLine = "";
           n++;
           }
        //ST else if (cont==NUMP) System.out.print("" + strAux + " (#" + (lineWidth + strAux.length()) + "," + sizeToken + ")+");

        strLine += strAux;
        lineWidth += sizeToken; // strAux.length();
        } // while (st.hasMoreTokens())

      if (stLines.hasMoreTokens()) {
         //ST if (cont==NUMP) System.out.println(" add: " + strLine);
         strProcessedText += strLine + "\n";
         strLine = "";
         lineWidth = 0;
         }

      countLines++;
      } // while (stLines.hasMoreTokens())

    strProcessedText += strLine; // get last not apended token

    // strProcessedText = strText;

    jTextF = new JTextArea(strProcessedText);
    // jTextF.setBackground(EsquemaVisual.corFundoParagrafos); // ensure the same background color
    jTextF.setColumns(80);

    jTextF.setForeground(COLORFACE);
    jTextF.setEditable(false);
    // jTextF.setLineWrap(true); jTextF.setWrapStyleWord(true); getColumns() getRowHeight() getColumnWidth()
    //ST if (cont==NUMP) System.out.println("\nLine: n="+n+", strAux='" + strAux + "',  strLine='" + strLine + "'\n'" + strProcessedText + "'\n#jTextF=" + jTextF.getRows());

    //ST cont++;

    add("West", jTextF);
    }


  public int getAltura () {
    return  altura;
    }

  void setAltura (int altura) {}

  }
