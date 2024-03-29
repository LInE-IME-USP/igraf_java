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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloExercicio.visao;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.Crypto;
import igraf.basico.util.Funcao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel; // Label
import javax.swing.JPanel; // Panel
import javax.swing.JTextField; // TextField


public class PainelRespostaPonto extends PainelResposta {

 double x1, x2, x1Gab, x2Gab;

 // From: 'src/igraf/moduloExercicio/visao/PainelRespostaIntervalo.java'
 protected JLabel labelX, labelY, labelV; // labels to form '( , )'

 private JTextField textFieldX, textFieldY;

 protected String strItem; // used in 'igraf/moduloExercicio/visao/PainelRespostaIntervalo.java'

 // From: 'src/igraf/moduloExercicio/visao/PainelRespostaIntervalo.java'
 protected String itemX = ResourceReader.msg("exercRAcoofdX"), itemY = ResourceReader.msg("exercRAcoofdY");

// private Dimension dimension = new Dimension(70,20);
 private JPanel auxPanel;
 
// protected void setDimension () { // from 'PainelRespostaIntervalo.java'   dimension = new Dimension(70,30);   }

 // just to avoid error in the subclass PainelListaResposta compilation
 // public PainelRespostaPonto () { }

 public PainelRespostaPonto (JanelaExercicio janelaExercicio, int num) {
  super.janelaExercicio = janelaExercicio;
//this.setLayout(new java.awt.BorderLayout());
  labelX = new JLabel("(");
  labelV = new JLabel(" , ");
  labelY = new JLabel(" )");

  labelX.setFont(PainelResposta.fonte);
  labelV.setFont(PainelResposta.fonte);
  labelY.setFont(PainelResposta.fonte);

  setLabelText(ResourceReader.msg("exercRAparOrd"));

  configurePanelAtRight(); // auxPanel = createRightPanel(labelX, labelV, labelY); // Right panel with: "[    ] , [    ]"

  adicionaConteudoDireito(auxPanel);
  setNumeroResposta(num);
  }


 // igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(CommunicationEvent)
 public PainelRespostaPonto (JanelaExercicio janelaExercicio, int num, double x1, double x2) {
  this(janelaExercicio, num);
  x1Gab = x1;
  x2Gab = x2;
  }

 // In 'igraf/moduloExercicio/visao/PainelResposta.java':
 // - protected JPanel createRightPanel (JLabel labelLeft, JLabel labelCenter, JLabel labelRight)
 // - protected JTextField createTextField (JPanel auxPanel, JLabel labelXorY)
 protected void configurePanelAtRight () {
  auxPanel = createRightPanel(labelX, labelV, labelY); // Right panel with: "[    ] , [    ]"
  }

 //protected abstract JTextField createTextField (JPanel auxPanel, JLabel labelXorY);
 // Right panel with: "[    ] , [    ]"
 private JPanel createRightPanel (JLabel labelLeft, JLabel labelCenter, JLabel labelRight) {
  JPanel auxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5)); // JPanel(new FlowLayout(FlowLayout.CENTER, 0, -1));
  textFieldX = createTextField(auxPanel, labelLeft, true); // put left "[  ]"
  auxPanel.add(labelCenter); // put ',' between '[ ]' and '[ ]'
  textFieldY = createTextField(auxPanel, labelRight, false); // put right "[  ]"

  // Ensure a proper size
  textFieldX.setPreferredSize(new Dimension(WIDTHLABEL, HEIGHT));
  textFieldY.setPreferredSize(new Dimension(WIDTHLABEL, HEIGHT));

  return auxPanel;
  }
 private JTextField createTextField (JPanel auxPanel, JLabel labelXorY, boolean labelBefore) {
  JTextField textFieldXorY = new JTextField(10);
  textFieldXorY.setFont(PainelResposta.fonte);
  if (labelBefore) {
   auxPanel.add(labelXorY);
   auxPanel.add(textFieldXorY);
   }
  else {   
   auxPanel.add(textFieldXorY);
   auxPanel.add(labelXorY);
   }
  return textFieldXorY;
  }


 public void limparCampos () {
  textFieldX.setText("");
  textFieldY.setText("");
  }


 public boolean validar () {
  if (temVariavelX(textFieldX.getText(), textFieldY.getText()))
   return false;
  if (validacaoOk(textFieldX) && validacaoOk(textFieldY)) {   
   try {
    x1 = Double.valueOf(textFieldX.getText()).doubleValue();
    x2 = Double.valueOf(textFieldY.getText()).doubleValue();        
    //T System.out.println("val: " + x1 + " " + x2);
    
    } catch (NumberFormatException e) { e.printStackTrace();  }
   return true;
   }
  return false;
  }
 

 public String getResposta () {
  strItem = "Item:" + String.valueOf(numQuest) + " ";
  String v1, v2;
  v1 = "x:" + Crypto.stringToHex(String.valueOf(x1)) + " ";
  v2 = "y:" + Crypto.stringToHex(String.valueOf(x2)) + " ";
  return strItem+v1+v2;
  }


 public int comparaResposta () {
  maxNumError = 2;
  if (!erroAceitavel(x1, x1Gab)) {
   diagnostico += respostaErrada(itemX + x1) + "\r\n";
   registraDiagnostico(diagnostico);
   numErros++;
   }
  else {
   diagnostico += respostaCerta(itemX + x1) + "\r\n";
   registraDiagnostico(respostaCerta(itemX + x1));
   numAcertos++; // igraf.moduloExercicio.evento.DiagnosticEvent.numAcertos++
   }

  if (!erroAceitavel(x2, x2Gab)) { 
   diagnostico += respostaErrada(itemY + x2) + "\r\n";
   registraDiagnostico(diagnostico);
   numErros++;
   }
  else {
   diagnostico += respostaCerta(itemY + x2) + "\r\n";
   registraDiagnostico(diagnostico);
   numAcertos++; // igraf.moduloExercicio.evento.DiagnosticEvent.numAcertos++
   }

  setValorCorrecao(correcao());
  setValorResposta("(" + x1 + ", " + x2 + ")");
  return numErros;
  }

 public void updateLabels () {
  super.updateLabels();
  itemX = ResourceReader.msg("exercRAcoofdX");
  itemY = ResourceReader.msg("exercRAcoofdY");
  setLabelText(ResourceReader.msg("exercRAparOrd"));
  }

 public static void main (String[] args) {
  new ResourceReader();
  PainelRespostaPonto prp = new PainelRespostaPonto(null, 1);
  prp.textFieldX.setText("sqrt(3*x)/3");
  prp.textFieldY.setText("5/2");
  prp.validar();
  }

 }
