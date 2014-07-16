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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.visao.componentesDoTexto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import igraf.moduloAjuda.visao.Help;


public abstract class ComponenteDoTexto extends JPanel {

  protected static final int WIDTHPARAGRAPH = Help.TOPICSWIDTH - 2 * Help.MARGIN;

  // A altura mínima de um componente de texto
  int altura = 40;

  // A largura do painel sobre o qual esse componente de texto será desenhado
  int largura; //TODO: use a fixed Help.TOPICSWIDTH

  // A distância mínima que um componente de texto mantém da borda esquerda do painel no qual será desenhado
  int margem = Help.MARGIN; // 20

  /** Recebe a largura do painel sobre o qual esse componente de texto será desenhado
   * e cria um objeto componente de texto em função dessa largura.   A largura L do
   * componente de texto é calculada como L = largura - 2*margem.
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
