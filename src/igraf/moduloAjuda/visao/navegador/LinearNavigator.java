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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloAjuda.visao.navegador;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import igraf.basico.io.ResourceReader;

import igraf.moduloAjuda.visao.Help; // configurations
import igraf.moduloAjuda.eventos.NavigatorPanelEvent;
import igraf.moduloAjuda.modelo.ListaTextoAjuda;
import igraf.moduloAjuda.modelo.JPanelBasisTopic;


public class LinearNavigator extends NavigatorPanel implements ActionListener {

 private HelpButton
   buttonVoltar,
   buttonAvancar,
   buttonInicial,
   buttonImprimir;

 public LinearNavigator () {
  setLayout(new FlowLayout(FlowLayout.LEFT));
  //T System.out.println("LinearNavigator: corLetrasBotoes="+igraf.basico.util.EsquemaVisual.corLetrasBotoes+", corFundoBotoes="+igraf.basico.util.EsquemaVisual.corFundoBotoes);

  buttonVoltar = new HelpButton(ResourceReader.msg("manualTopPback")); // "Voltar"
  buttonAvancar = new HelpButton(ResourceReader.msg("manualTopPforw")); // "Avan�ar"
  buttonInicial = new HelpButton(ResourceReader.msg("manualTopPstar")); // "In�cio"
  buttonImprimir = new HelpButton(ResourceReader.msg("manualTopPprint")); // "Imprimir"

  buttonVoltar.addActionListener(this);  
  buttonAvancar.addActionListener(this);
  buttonInicial.addActionListener(this);
  buttonImprimir.addActionListener(this);

  buttonVoltar.setEnabled(false);
  buttonInicial.setEnabled(false);

  add(buttonVoltar);
  add(buttonAvancar);
  add(buttonInicial);
  add(buttonImprimir);
  
  setConteudoAjuda(0);
  }


// public Dimension getPreferredSize () {  return new Dimension(Help.BUTTONWIDTH, Help.BUTTONHEIGHT);  }


 private void disableApropriatedButton (int i) {
 
  if (i == 0) {
   buttonVoltar.setEnabled(false);
   buttonInicial.setEnabled(false);
   buttonAvancar.setEnabled(true);
   }
  else
  if (i == ListaTextoAjuda.numTopicos-1) {
   buttonAvancar.setEnabled(false);
   buttonVoltar.setEnabled(true);
   buttonInicial.setEnabled(true);
   }  
  else {
   buttonVoltar.setEnabled(true);
   buttonInicial.setEnabled(true);
   buttonAvancar.setEnabled(true);
   }
  
  }


 private void imprimir () {
  JFrame f = new JFrame();
  JPanelBasisTopic ta = getConteudoSelecionado();

  PrintJob pjob = getToolkit().getPrintJob(f, ta.toString(), null);

  if (pjob != null) {
    Graphics pg = pjob.getGraphics();
    if (pg != null) {
      ta.printAll(pg);
      pg.dispose(); // flush page
      }
    pjob.end();
    }
  }


 public void actionPerformed (ActionEvent e) {
  String s = e.getActionCommand();

  if (s.equals(ResourceReader.msg("manualTopPback"))) // "Voltar"
   setConteudoAjuda(--textIndex);
  else
  if (s.equals(ResourceReader.msg("manualTopPforw"))) // "Avan�ar"
   setConteudoAjuda(++textIndex);
  else
  if (s.equals(ResourceReader.msg("manualTopPstar"))) // "In�cio"
   setConteudoAjuda(textIndex = 0);
  else
  if (s.equals(ResourceReader.msg("manualTopPprint"))) // "Imprimir"
   imprimir();

  disableApropriatedButton(textIndex);
  }


 public void mudouItemSelecionado (NavigatorPanelEvent npe) {
  textIndex = npe.getSelectedTextIndex();
  disableApropriatedButton(textIndex);
  }


 // Used whenever the language is changed
 public void updateLabels () {
  buttonVoltar.setText(ResourceReader.msg("manualTopPback")); // "Voltar"
  buttonAvancar.setText(ResourceReader.msg("manualTopPforw")); // "Avan�ar"
  buttonInicial.setText(ResourceReader.msg("manualTopPstar")); // "In�cio"
  buttonImprimir.setText(ResourceReader.msg("manualTopPprint")); // "Imprimir"
  }


 }
