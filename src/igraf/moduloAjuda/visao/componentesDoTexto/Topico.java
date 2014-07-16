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
 * @description Help integrated to iGraf. One instance of "Topico" is a portion of text to
 * the manual. The "Topico" could be "Paragrafo".
 * 
 * @see igraf/moduloAjuda/visao/componentesDoTexto/Topico.java: extends ComponenteDoTexto
 * @see igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java: extends ComponenteDoTexto
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

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloAjuda.visao.Help;
import igraf.moduloAjuda.modelo.UnsuportedLevelException;

public class Topico extends ComponenteDoTexto {

  private int nivel;
  
  public static final int  
    TITULO = 0, SUBTITULO = 1,
    SESSAO = 2;

  private static final int BSFNT = Help.BASICFONTSIZE + 10; // basic font size

  private String nomeTopico;  
  private Font font;
  private JLabel jlabelTopic;

  /**
   * Um tópico é um pequeno texto de, no máximo, uma linha que nomeia um bloco de
   * um texto.  O tópico pode ser um título, um subtitulo ou uma sessão.   A distinção
   * entre essas três opções se dá, principalmente, pelo tamanho e estilo da fonte.
   * @param String nomeTopico
   * @param int nivel
   * @param int largura
   * @throws UnsuportedLevelException
   */
  public Topico (String nomeTopico, int nivel, int largura) throws UnsuportedLevelException {
    super(largura);
    //T System.out.println("Topico.java: nomeTopico=" + nomeTopico);

    // Size is defined in 'igraf/moduloAjuda/modelo/JPanelBasisTopic.java': important to fill all horizontal space
    this.setLayout(new BorderLayout()); // igraf/moduloAjuda/visao/componentesDoTexto/Topico.java

    if (nivel < 0 || nivel > 2)
       throw new UnsuportedLevelException();
    this.nivel = nivel;
    this.nomeTopico = nomeTopico;

    font = new Font("Arial", Font.BOLD, BSFNT - 5*nivel);
    jlabelTopic = new JLabel(ResourceReader.msg(nomeTopico));
    jlabelTopic.setFont(font);
    jlabelTopic.setForeground(EsquemaVisual.corLetras); //

    this.add("West", jlabelTopic);

    this.setFont(font);
    setForeground(EsquemaVisual.corLetras); //
    setBackground(EsquemaVisual.corBarraSupInf); // must have contrast with 'Paragrafo.java' backgound color

    // setAltura(nivel);

    }


  public void setText (String text) {
    this.nomeTopico = text;
    }

  // void setAltura (int nivel) {    altura -= 5*nivel;    }

  }
