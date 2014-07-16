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

package igraf.moduloJanelasAuxiliares.visao.tangente;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.DesenhoTangenteEvent;
import igraf.moduloJanelasAuxiliares.controle.JanelaTangenteControler;

import java.awt.Choice;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import difusor.i18N.LanguageUpdatable;


public class JanelaTangente extends Frame implements LanguageUpdatable {

 public PainelTangente pt;

 public JanelaTangente (JanelaTangenteControler jtc) {
  super(ResourceReader.msg("mcTgPane"));
  //T System.out.println("\nsrc/igraf/moduloJanelasAuxiliares/visao/tangente/JanelaTangente.java: " + jtc);
  setLocation(200, 100);
  setResizable(false);

  pt = new PainelTangente(jtc);
  add(pt);
  pack();

  addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    pt.animaTangente(false);
    setVisible(false);
    dispose();
    }
   });
  setVisible(true);
  }

 public Choice getChoice () {
  return pt.getChoice();
  }

 public void atualizaLabels (DesenhoTangenteEvent dte) {
  pt.atualizaLabels(dte);
  }

 public void updateLabels () {
  setTitle(ResourceReader.msg("mcTgPane"));
  pt.updateLabels();
  }

 }
