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

package igraf.moduloExercicio.visao;


import igraf.basico.io.ResourceReader;
import igraf.basico.util.Crypto;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;


public class PainelRespostaIntervalo extends PainelRespostaPonto implements MouseListener {

 private boolean limiteInferior = true;
 private boolean limiteSuperior = true;

 //DEBUG: private String strItem; - erro ja no pai 'PainelRespostaPonto'

 private int infGab, supGab;

 public PainelRespostaIntervalo (JanelaExercicio janelaExercicio, int num) {
  super(janelaExercicio, num);
  changeBracket();
  setLabelText(ResourceReader.msg("exercRIDigLimInt")); // digite os limites para o intervalo
  itemX = ResourceReader.msg("exercRILInf") + " = "; // limite inferior 
  itemY = ResourceReader.msg("exercRILSup") + " = "; // limite superior
  // System.out.println("src/igraf/moduloExercicio/visao/PainelRespostaIntervalo.java: ");
  }


 // igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(CommunicationEvent)
 public PainelRespostaIntervalo (JanelaExercicio janelaExercicio, int num, int inf, int sup, double x1g, double x2g) {
  this(janelaExercicio, num);
  super.janelaExercicio = janelaExercicio;
  infGab = inf; supGab = sup;
  x1Gab = x1g;  x2Gab = x2g;
  }

 private void changeBracket () {
  // In 'PainelRespostaPonto.java: JLabel labelX, labelY, labelV; are to form '( , )'
  labelX.setText("[");
  labelY.setText(" ]");
  labelX.addMouseListener(this);
  labelY.addMouseListener(this);
  }

 public void mouseReleased (MouseEvent e) {
  Object source = e.getSource();
  if (source == labelX) {
   changeBracketDirection(labelX);
   limiteInferior = !limiteInferior;
   }
  else {
   changeBracketDirection(labelY);
   limiteSuperior = !limiteSuperior;
   }
  }


 private void changeBracketDirection (JLabel label) {
  if (label.getText().equals("[")) {
   label.setText(" ]");
   }
  else
   label.setText("[");
  }


 public void mouseEntered (MouseEvent e) {
  JLabel source = (JLabel) e.getSource();
  source.setForeground(Color.red); // red?
  }

 public void mouseExited (MouseEvent e) {
  JLabel source = (JLabel) e.getSource();
  source.setForeground(Color.black);
  }

 public void mousePressed (MouseEvent e) { }
 public void mouseClicked (MouseEvent e) { }


 private boolean getLimiteInferior () {
  return limiteInferior;
  }

 private boolean getLimiteSuperior () {
  return limiteSuperior;
  }

 /**
  * @return 1 se o intervalo for fechado no limite inferior e 
  * 0 em caso contrário.
  */
 public int getEstadoColcheteInferior () {
  if (getLimiteInferior())
   return 1;
  return 0;
  }

 /**
  * @return 1 se o intervalo for fechado no limite superior e 
  * 0 em caso contrário.
  */
 public int getEstadoColcheteSuperior () {
  if (getLimiteSuperior())
   return 1;
  return 0;
  }


 // He must occurs the criptograph
 public String getResposta () {
  strItem = "Item:" + String.valueOf(numQuest) + " ";
  String strInf, v1, v2, s;
  strInf = "inf:" + String.valueOf(getEstadoColcheteInferior()) + " ";
  v1  = "x1:"  + Crypto.stringToHex(String.valueOf(x1)) + " ";
  v2  = "x2:"  + Crypto.stringToHex(String.valueOf(x2)) + " ";
  s = "sup:" + String.valueOf(getEstadoColcheteSuperior());
  return strItem + strInf + v1 + v2 + s;
  }


 public int comparaResposta () {
  int a = infGab;
  int b = supGab;
  int c = getEstadoColcheteInferior();
  int d = getEstadoColcheteSuperior();

  String c1 = (c == 0)? ResourceReader.msg("exercRIAberto") : ResourceReader.msg("exercRIFechado"); // aberto : fechado
  String c2 = (d == 0)? ResourceReader.msg("exercRIAberto") : ResourceReader.msg("exercRIFechado"); // aberto : fechado 

  if (a != c) { 
   diagnostico += respostaErrada(ResourceReader.msg("exercRIInterv")+ " " + c1 + " " + ResourceReader.msg("exercRIIntEsq")) + "\r\n";
   registraDiagnostico(diagnostico); // "Intervalo " + c1 + ResourceReader.msg("")" à esquerda"
   numErros++;
   }
  else {
   diagnostico += respostaCerta(ResourceReader.msg("exercRIInterv")+ " " + c1 + " " + ResourceReader.msg("exercRIIntEsq")) + "\r\n";
   registraDiagnostico(respostaCerta(diagnostico)); // "Intervalo " + c1 + ResourceReader.msg("")" à esquerda"
   numAcertos++; // igraf.moduloExercicio.evento.DiagnosticEvent.numAcertos++
   }

  super.comparaResposta(); 
  maxNumError = 4;

  if (b != d) { 
   diagnostico += respostaErrada(ResourceReader.msg("exercRIInterv")+ " " + c2 + " " + ResourceReader.msg("exercRIIntDir")) + "\r\n";
   registraDiagnostico(respostaErrada(diagnostico));
   numErros++;
   }
  else {
   diagnostico += respostaCerta(ResourceReader.msg("exercRIInterv")+ " " + c2 + " " + ResourceReader.msg("exercRIIntDir")) + "\r\n";
   registraDiagnostico(respostaCerta(diagnostico));
   numAcertos++; // igraf.moduloExercicio.evento.DiagnosticEvent.numAcertos++
   }

  setValorCorrecao(correcao());
  setValorResposta(labelX.getText() + x1 + ", " + x2 + labelY.getText().trim());    
  return numErros;
  }


 public void updateLabels () {   
  super.updateLabels();
  setLabelText(ResourceReader.msg("exercRIDigLimInt")); // digite os limites para o intervalo
  itemX = ResourceReader.msg("exercRILInf"); // limite inferior 
  itemY = ResourceReader.msg("exercRILSup"); // limite superior   
  }

 }
