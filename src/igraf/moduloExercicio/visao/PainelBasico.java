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
 * @description 
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

package igraf.moduloExercicio.visao;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JPanel;

import igraf.basico.util.EsquemaVisual;


public class PainelBasico extends JPanel {

  protected Dimension dimension;
  protected final int largura = 630;

  protected JanelaExercicio janelaExercicio;

  //DEBUG: com swing nao mais necessario - se ficar "estraga"
  //QUARENTENA: 31/05/2013 - remover
  // public void paint (Graphics g) {    g.setColor(EsquemaVisual.corMoldura);    g.drawRect(0, 0, getSize().width-1, getSize().height-1);    }
  // public Insets getInsets () {    return new Insets(2,2,2,2);    }

  }
