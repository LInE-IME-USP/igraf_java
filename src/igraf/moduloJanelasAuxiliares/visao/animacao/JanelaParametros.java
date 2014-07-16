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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloJanelasAuxiliares.visao.animacao;


import igraf.basico.io.ResourceReader;
import igraf.moduloJanelasAuxiliares.controle.JanelaParametrosController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import igraf.basico.util.EsquemaVisual;
import difusor.i18N.LanguageUpdatable;


public class JanelaParametros extends JFrame implements ActionListener, LanguageUpdatable {

 public static final int
    WIDTH = 200, HEIGHT=300; // base size to this panel: used in 'PainelDeParametros.java'
    // WIDTH_BUTTON = 55, HEIGTH_BUTTON = 25; // button OK size - if used, attention to the 'igraf/basico/resource/StringsTable_*.properties ! labOK' length

 public static final Color
    bgColor = EsquemaVisual.corBackground, // backgound color to this window
    bgButton = EsquemaVisual.corBackPanel;

 private PainelDeParametros painelDeParametros;
 private JButton OK;

 public JanelaParametros (JanelaParametrosController jpc) {
  super("iGraf - " + ResourceReader.msg("labJanParamTit")); // Parameters
  // this.setLocation(200,200);
  this.setBackground(bgColor);

  painelDeParametros = new PainelDeParametros(jpc);

  this.getContentPane().add(painelDeParametros);

  JPanel southPanel = new JPanel(); // auxiliary to keep OK button with a fixed size
  southPanel.setBackground(bgColor);

  OK = new JButton(ResourceReader.msg("labOK")); // OK
  OK.setToolTipText(ResourceReader.msg("labOKtooltip")); // click here to dismiss this window
  // OK.setPreferredSize(new Dimension(WIDTH_BUTTON, HEIGTH_BUTTON));
  OK.addActionListener(this);
  OK.setBackground(bgButton);

  southPanel.add(OK);
  getContentPane().add("South", southPanel);
  pack();
  
  addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent arg0) {
    setVisible(false);
    dispose();
    }
   });
  setVisible(true);
  }


 public PainelDeParametros getPainelDeParametros () {
  return painelDeParametros;
  }


 public void actionPerformed (ActionEvent e) {
  setVisible(false);
  dispose();
  }

 public void updateLabels () {
  setTitle("iGraf - " + ResourceReader.msg("labJanParamTit"));
  OK.setText(ResourceReader.msg("labOK")); // OK
  OK.setToolTipText(ResourceReader.msg("labOKtooltip")); // click here to dismiss this window
  painelDeParametros.updateLabels();
  }

 }
