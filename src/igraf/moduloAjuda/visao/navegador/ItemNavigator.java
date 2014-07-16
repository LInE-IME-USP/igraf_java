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

package igraf.moduloAjuda.visao.navegador;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import igraf.basico.util.EsquemaVisual;
import igraf.moduloAjuda.visao.Help;
import igraf.moduloAjuda.eventos.NavigatorPanelEvent;
import igraf.moduloAjuda.modelo.ListaTextoAjuda;


// igraf/moduloAjuda/visao/navegador/ItemNavigator.java
public class ItemNavigator extends NavigatorPanel {

 NavigatorLabel [] listaLabel; // each item of menu at the left side of this manual

 JPanel auxPanelFixedHeight = new JPanel(); // auxiliary panel to help to adjust all items into the top of the panel

 public ItemNavigator () {
  int num = ListaTextoAjuda.numTopicos;

  GridLayout gdLayout = new GridLayout(num,1, 0, 15); // vgap=15
  auxPanelFixedHeight.setLayout(gdLayout); // this must be used to get JLabel of items adjust at the top
  auxPanelFixedHeight.setBackground(EsquemaVisual.corFundoParagrafos); // ensure the same background color of 'igraf/moduloAjuda/visao/componentesDoTexto/Topico.java' and 'Paragrafo.java'

  insereLabel();

  this.add("West", auxPanelFixedHeight);

  addNavigatorPanelListener(this);
  listaLabel[0].selecionaLabel(0);
  }

 
 private void insereLabel () {
  int num = ListaTextoAjuda.numTopicos;
  listaLabel = new NavigatorLabel[num];
  NavigatorLabel nl;

  for (int i = 0; i < num; i++) {
   nl = new NavigatorLabel(ListaTextoAjuda.getTexto(i).toString(), i, this);
   listaLabel[i] = nl;
   auxPanelFixedHeight.add(nl);
   //T System.out.println(" - " + i + ": " + ListaTextoAjuda.getTexto(i).toString() + ": " + listaLabel[i].name + ": " + listaLabel[i].getForeground()  + " , " + listaLabel[i].getBackground());
   }
  }


 public void mudouItemSelecionado (NavigatorPanelEvent npe) {
  textIndex = npe.getSelectedTextIndex();
  //T System.out.println("\nItemNavigator.java: mudouItemSelecionado: textIndex=" + textIndex);

  for (int i = 0; i < listaLabel.length; i++) {
   listaLabel[i].selecionaLabel(textIndex); // NavigatorLabel []
   }
  }

 }
