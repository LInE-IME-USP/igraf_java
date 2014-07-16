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
 * Created on 09/01/2012, 18:00:11
 * 
 * @description 
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

package igraf.moduloExercicio.visao.menuSelector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JLabel;

import igraf.basico.util.EsquemaVisual;

public class MenuItemPanel extends JPanel {

 public static final Color
   corBackground = EsquemaVisual.corBackground, // new Color( 0, 25, 50)
   corForeground = EsquemaVisual.corForeground, // Color.black;
   COLORBORDER = new Color( 74,  74, 184),
   COLORTITLE = COLORBORDER; // EsquemaVisual.corTitle;     // color in 'MenuItemPanel.java' to each title of block of menus - new Color(204, 204, 204);

 private static final Font  FONT = new Font("Tahoma", 0, 12);
 private static final int WIDTH = 200, HEIGHT = 200;

 private String menuName;
 // private JLabel label;

 public MenuItemPanel (String menuName) {
  initComponents(menuName);
  }

 private void initComponents (String menuName) {
  this.menuName = menuName;
  // this.label = new JLabel(menuName);
  // this.label.setBackground(COLORTITLE);
  this.setForeground(Color.black);
  this.setBorder(BorderFactory.createTitledBorder(null, menuName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, FONT, COLORBORDER));
  this.setLayout(new GridLayout(12, 1, 2, 2));
  this.setBackground(corBackground);
  this.setForeground(corForeground);
  // add(this.label);
  this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

 // Here is the name of the block (with option from one iGraf primary button)
 protected void setText (String strName) {
  this.menuName = strName;
  setBorder(BorderFactory.createTitledBorder(null, menuName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, FONT, COLORBORDER));
  }

 }
