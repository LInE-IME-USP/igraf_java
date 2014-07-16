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
 * @description Painel que contém a lista de opções de respostas dentre as quais o usuário pode
 * selecionar tanto para enviar resposta (aluno) quanto para configurar exercício (professor) 
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

package igraf.moduloExercicio.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloExercicio.controle.JanelaExercicioController;

import difusor.i18N.LanguageUpdatable;


public class PainelSeletorTipoResposta extends PainelBasico implements LanguageUpdatable {

 private static final String SEP = ""; // "-----------------------------------------",
 private static final Color COLORREMOVELAST = Color.orange;

 private JLabel label;
 //R private Choice choice;
 private JComboBox choiceList;


 //DEBUG: if you decide to use a separator in JComboBox, please, take care with its index
 //DEBUG: Index of the separator if used - necessary in 'igraf/moduloExercicio/controle/JanelaExercicioController.java: actionPerformedItemStateChanged(JComboBox choiceOrigin, ActionEvent e)'
 //DEBUG: public static final String SEPINDEX = 5;

 //ATTENTION: Take care with position here, it is strongly used in
 //ATTENTION: - igraf/moduloExercicio/visao/PainelListaResposta.java: alteraPainelResposta(int index): 'if (index == 5) { removePainelResposta(); atualizaAltura(-1); return; }'
 //ATTENTION: - igraf/moduloExercicio/controle/JanelaExercicioController.java: itemStateChanged(ItemEvent e): 'if (index!=0) { janelaExercicio.alteraPainelResposta(index); if (index==4) enviarEvento(new RespostaAlgebricaEvent(this)); }'
 private String [] listOfElements () {
  String strList [] = {                      // index | action
   ResourceReader.msg("exercPSSelTipoResp"), // 0     | "Select the type of the answer"
   ResourceReader.msg("exercPSNum"),         // 1     | insert item "Number"
   ResourceReader.msg("exercPSParOrd"),      // 2     | insert item "Ordered Pair (x, y)"
   ResourceReader.msg("exercPSInterv"),      // 3     | insert item "Numeric Interval [a, b]"
   ResourceReader.msg("exercPSEA"),          // 4     | insert item "Math Expression to function"
   ResourceReader.msg("exercPSRemoveItem")   // 5     | remove last inserted item "Remove the last inserted item"
   };
  return strList;
  }


 /**
  * Build the autoring panel: where teacher choose its type of required answers.
  * When used by student (not authoring): this panel has only a message.
  * @param JanelaExercicioController janelaExercicioController
  */
 public PainelSeletorTipoResposta (JanelaExercicioController janelaExercicioController) {
  //T System.out.println("\nsrc/igraf/moduloExercicio/visao/PainelSeletorTipoResposta.java: construtor");
  setLayout(new BorderLayout());

  label = new JLabel(ResourceReader.msg("exercPSRespObj")); // "Objective answers"
  label.setForeground(EsquemaVisual.corLetraRotulo);
  this.add(label);

  //R choice = new Choice();  choice.addItemListener(janelaExercicioController);  resetChoice();  this.add("East", choice);
  choiceList = new JComboBox(listOfElements());

  // Listener in: igraf/moduloExercicio/controle/JanelaExercicioController.java
  //R choiceList.addItemListener(janelaExercicioController);
  choiceList.addActionListener(janelaExercicioController); //DEBUG: ItemListener after convertion to Swing results in error: each item is duplicated

  choiceList.setMaximumRowCount(6); // choiceList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
  choiceList.setBackground(EsquemaVisual.corAreaDesenho);
  this.add("East", choiceList);

  this.setBackground(EsquemaVisual.corAreaDesenho);
  }


 public Dimension getPreferredSize () {
  return new Dimension(largura, 30);
  }


 // 0 => reading an exercise; 1 => not reading exercise
 public void setMode (int modo) {
  if (modo == JanelaExercicio.ENVIO) {
   // if is exercise and it is not in authoring mode => block creation of new exercises
   if (IGraf.ehApplet && (IGraf.iLM_PARAM_authoring==null || !IGraf.iLM_PARAM_authoring.equals("true")))
    choiceList.setEnabled(false); //R choice.setEnabled(false);
   }
  }

 public void reset () {
  //T getItemCount
  //T String strAux = (choice==null ? "<null>" : choice.countItems()+", " + choice.getItem(0));
  //T System.out.println("\nsrc/igraf/moduloExercicio/visao/PainelSeletorTipoResposta.java: reset(): #choice=" + strAux);

  //R choice.select(0);  choice.setEnabled(true);
  choiceList.setSelectedIndex(0);
  choiceList.setEnabled(true);
  }

 public void updateLabels () {
  //T System.out.println("\nsrc/igraf/moduloExercicio/visao/PainelSeletorTipoResposta.java: updateLabels(): choice=" + choice);
  label.setText(ResourceReader.msg("exercPSRespObj")); // "Objective answers"
  resetChoice();
  }


 private void resetChoice () {
  //T System.out.println("src/igraf/moduloExercicio/visao/PainelSeletorTipoResposta.java: resetChoice(): choice=" + choice);
  String strList [] = listOfElements();

  for (int ii_0=0; ii_0<strList.length; ii_0++) {
    //R choice.insert(strList[ii_0], ii_0);
    choiceList.insertItemAt(strList[ii_0], ii_0);
    }
  //TODO: como mudar cor de fundo o ultimo? setBackground(COLORREMOVELAST);

  }

 }
