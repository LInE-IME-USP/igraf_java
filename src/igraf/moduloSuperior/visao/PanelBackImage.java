/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author RP, LOB
 * 
 * @description JPanel with an image as background. The image is copied N times, to cover all the JPanel
 * 
 * @see igraf/moduloSuperior/PaineSuperior.java: use this PanelBackImage to construct a JPanel with background image
 * @see igraf/moduloSuperior/PainelTitulo.java: use this PanelBackImage to construct a JPanel with background image
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 *
 */

package igraf.moduloSuperior.visao;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelBackImage extends JPanel {

 private Image backImage = null;

 private int dx=0, dy=0;

 // Exclusive from 'PainelTitulo.java'
 public PanelBackImage (Image bgImage, int dx, int dy) {
  this.backImage = bgImage;
  this.dx = dx;
  this.dy = dy;
  }

 public PanelBackImage (Image bgImage) {
  this.backImage = bgImage;
  }


 // Put the background image 'bgImage' from 'PainelTitulo.java' or other
 public void paintComponent (java.awt.Graphics gr) {
  super.paintComponent(gr);
  gr.drawImage(backImage, 0, 0, this.getWidth(), this.getHeight(), this);
  // gr.drawImage(backImage, dx, dy, this.getWidth(), this.getHeight(), this);
  // gr.drawImage(backImage, dx, dy, this.backImage.getWidth(this), this.backImage.getHeight(this), this);
  // System.out.println("PanelBackImage.java: ("+this.backImage.getWidth()+","+this.backImage.getHeight()+")");
  }

 }
