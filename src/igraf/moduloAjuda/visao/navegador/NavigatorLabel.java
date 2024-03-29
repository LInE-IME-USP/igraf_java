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
 * @description Help integrated to iGraf. This is the left side panel, with shortcuts to any topic in the Manual.
 *              Graphics | Animation | Calculus | Edition | Sintax | Concepts
 * 
 * @see igraf/moduloAjuda/visao/navegador/ItemNavigator.java: create each 'NavigatorLabel'
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */


package igraf.moduloAjuda.visao.navegador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloAjuda.visao.Help;

public class NavigatorLabel extends JLabel implements MouseListener {

  private static final Color colorForeActive = EsquemaVisual.corLetrasAtivoBotoes; // color to menu item active (its topic is presented)
  private static final Color colorFore = EsquemaVisual.corLetrasBotoes; // color to each manu item (at the left side of manual)
  private static final Color colorBack = EsquemaVisual.corFundoBotoes; // Color.gray; //
  private static final Color colorMoseOver = EsquemaVisual.corAcesa; // color of the menu item under the mouse

  private static final int WIDTH = Help.MENUITEMWIDTH, HEIGHT = Help.MENUITEMHEIGHT;

  private ItemNavigator itemNavigator;
  private int index;
  private boolean selecionado;
  public String name; // text in this menu item

  //D try{String str="";System.out.print(str.charAt(3));}catch(Exception e) {e.printStackTrace();}

  //D public void setForeground (Color color) { super.setForeground(color); //System.out.println("NavigatorLabel: setForeground " + color + " : " + this.name); } 
  //D public void setBackground (Color color) { super.setBackground(color); System.out.println("NavigatorLabel: setBackground " + color + " : " + this.name); }

  public NavigatorLabel (String strResouceLabel, int index, ItemNavigator in) {
    super("   " + ResourceReader.msg(strResouceLabel)); // label title: usually the menu item of iGraf
    this.index = index;
    this.itemNavigator = in;
    this.name = strResouceLabel;
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // igraf/moduloAjuda/visao/Help.java
    this.setOpaque(true); // IMPORTANT: without this, the JLabel will inherit the backgroud color from the JPanel (white in white)
    this.setFont(EsquemaVisual.fontHB12);
    this.setStandardColors();
    this.addMouseListener(this);
    }


  // Set default colors to each (nom selected) item
  private void setStandardColors () {
    setForeground(colorFore);
    setBackground(colorBack);
    }

  void selecionaLabel (int i) {
    //T System.out.println("NavigatorLabel: selecionaLabel" + i);
    if (i == index) {
       setForeground(colorForeActive);
       setBackground(colorBack);
       selecionado = true;
       }
    else {
       setStandardColors();
       selecionado = false;
       }
    }


  public void mouseClicked (MouseEvent e) {
    itemNavigator.setConteudoAjuda(index);
    selecionaLabel(index);
    }

  public void mouseEntered (MouseEvent e) {
    // System.out.println("NavigatorLabel: mouseEntered");
    if (!selecionado)
       setForeground(colorMoseOver); // 
    }

  public void mouseExited (MouseEvent e) {
    if (!selecionado) {
       setStandardColors(); // setForeground(colorFore); setBackground(colorBack);
       }
    }

  public void mousePressed (MouseEvent e) { }
  public void mouseReleased (MouseEvent e) { }

  }
