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
 * @see igraf/moduloAjuda/visao/navegador/ItemNavigator.java: extends 'NavigatorPanel'
 * @see igraf/moduloAjuda/visao/navegador/NavigatorLabel.java: each JLabel with access to the manual topic
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

import igraf.basico.util.EsquemaVisual;
import igraf.moduloAjuda.eventos.NavigatorPanelEvent;
import igraf.moduloAjuda.eventos.NavigatorPanelListener;
import igraf.moduloAjuda.modelo.ListaTextoAjuda;
import igraf.moduloAjuda.modelo.JPanelBasisTopic;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public abstract class NavigatorPanel extends JPanel implements NavigatorPanelListener {

  private Vector listaDeOuvintes;
  // private JPanelBasisTopic contentOfItem;
  protected int textIndex = 0; // in 'igraf/moduloAjuda/visao/navegador/LinearNavigator.java' and 'igraf/moduloAjuda/visao/navegador/ItemNavigator.java'
 
  public NavigatorPanel () {
    listaDeOuvintes = new Vector();
    setBackground(EsquemaVisual.corAreaDesenho);
    }

  // From: igraf/moduloAjuda/visao/Help.java:
  //not necessary: public void setFirstContent () {   setConteudoAjuda(0);   }
   

  // From: igraf/moduloAjuda/visao/navegador/NavigatorLabel.java: mouseClicked(MouseEvent e)
  protected void setConteudoAjuda (int i) {
    textIndex = i;
    if (textIndex < 0) textIndex = 0;
    if (textIndex > ListaTextoAjuda.numTopicos-1) 
       textIndex = ListaTextoAjuda.numTopicos-1;
    disparaEventoMudouItemSelecionado();
    }
  
  public JPanelBasisTopic getConteudoSelecionado () {
    return ListaTextoAjuda.getTexto(textIndex);
    }
  
  public int getTextIndex () {
    return textIndex;
    }

  
  public synchronized void addNavigatorPanelListener (NavigatorPanelListener hl) {
    listaDeOuvintes.addElement(hl);
    }
  
  //R public synchronized void removeNavigatorPanelListener (NavigatorPanelListener hl) {    listaDeOuvintes.removeElement(hl);    }


  private void disparaEventoMudouItemSelecionado () {
    // System.out.println("NavigatorPanel.java: disparaEventoMudouItemSelecionado()");
    Vector v;
    synchronized (this) {
      v = (Vector)listaDeOuvintes.clone();
      }
    
    NavigatorPanelEvent evento = new NavigatorPanelEvent(this);
    
    for (int i = 0; i < v.size(); i++) {
      NavigatorPanelListener item = (NavigatorPanelListener) v.elementAt(i);
      item.mudouItemSelecionado(evento);      
      }
    }

  }
