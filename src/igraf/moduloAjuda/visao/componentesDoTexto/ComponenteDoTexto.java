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
 * @description Help integrated to iGraf. One instance of "ComponenteDeTexto" is a panel with
 * title, subtitle, session, paragraph, or figure.
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

package igraf.moduloAjuda.visao.componentesDoTexto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import igraf.moduloAjuda.visao.Help;


public abstract class ComponenteDoTexto extends JPanel {

  protected static final int WIDTHPARAGRAPH = Help.TOPICSWIDTH - 2 * Help.MARGIN;

  // A altura m�nima de um componente de texto
  int altura = 40;

  // A largura do painel sobre o qual esse componente de texto ser� desenhado
  int largura; //TODO: use a fixed Help.TOPICSWIDTH

  // A dist�ncia m�nima que um componente de texto mant�m da borda esquerda do painel no qual ser� desenhado
  int margem = Help.MARGIN; // 20

  /** Recebe a largura do painel sobre o qual esse componente de texto ser� desenhado
   * e cria um objeto componente de texto em fun��o dessa largura.   A largura L do
   * componente de texto � calculada como L = largura - 2*margem.
   * @param largura
   */
  public ComponenteDoTexto (int largura) {
    this.largura = largura;
    // do not set Layout - only necessary in 'Topico.java'
    }

  // Devolve Retorna a altura do componente de texto
  public int getAltura () {
    return altura;
    }

  int getLargura () {
    return largura;
    }

  }
