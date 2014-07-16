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
 * @see igraf/moduloAjuda/visao/navegador/LinearNavigator.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.visao.navegador;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.BorderFactory;

import igraf.basico.util.EsquemaVisual;
import igraf.moduloAjuda.visao.Help; // configurations


public class HelpButton extends JButton implements MouseListener {

  public HelpButton (String rotulo) {
    super(rotulo);
    // setToolTipText("");
    setForeground(EsquemaVisual.corLetrasBotoes); // white
    setBackground(EsquemaVisual.corFundoBotoes); // 0,90,180
    setFont(EsquemaVisual.fontHB12);
    this.setOpaque(true);
    this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray), BorderFactory.createEmptyBorder(0,3,0,3))); 
    addMouseListener(this);
    }

 // public Dimension getPreferredSize () {    return new Dimension(70, 50);    }
 public Dimension getPreferredSize () {  return new Dimension(Help.BUTTONWIDTH, Help.BUTTONHEIGHT);  }

  public void mouseEntered (MouseEvent e) {
    setForeground(EsquemaVisual.corAcesa); //
    }

  public void mouseExited (MouseEvent e) {
    setForeground(EsquemaVisual.corLetrasBotoes);  
    }

  public void mouseClicked (MouseEvent e) {}
  public void mousePressed (MouseEvent e) {}
  public void mouseReleased (MouseEvent e) {}

  }
