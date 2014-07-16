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

import java.awt.Component; // to 'igraf.basico.event.AttentionToolTip''
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import igraf.basico.event.AttentionToolTip;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.Crypto;


public class PainelRespostaNumerica extends PainelResposta {

 protected JTextField textFieldEnterExpression;
 protected JPanel panel;
 protected String respostaAluno, respostaGabarito;


 public PainelRespostaNumerica (JanelaExercicio janelaExercicio) {
  this.janelaExercicio = janelaExercicio;
  configurePanelAtRight();
  setLabelText(ResourceReader.msg("exercRNdigR"));
  }


 public PainelRespostaNumerica (JanelaExercicio janelaExercicio, int num) { //
  this(janelaExercicio);
  setNumeroResposta(num);
  }


 // igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(CommunicationEvent)
 public PainelRespostaNumerica (JanelaExercicio janelaExercicio, String respGab, int num) {
  super.janelaExercicio = janelaExercicio;
  setLabelText(ResourceReader.msg("exercRNdigR"));
  respostaGabarito = respGab;
  configurePanelAtRight();
  setNumeroResposta(num);
  }


 // Also from: igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java
 protected void configurePanelAtRight () {
  //System.out.println("src/igraf/moduloExercicio/visao/PainelRespostaNumerica.java: configurePanelAtRight()");
  textFieldEnterExpression = new JTextField();
  panel = new JPanel(new GridLayout(3,1)); // in igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java
  adicionaConteudoDireito(textFieldEnterExpression);
  }


 public void limparCampos () {
  try {
   textFieldEnterExpression.setText("");
   } catch (Exception e) { }
  }

 public String getResposta () {
  return super.getResposta() + "val:" + Crypto.stringToHex(textFieldEnterExpression.getText()) + " ";
  }


 //T try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
 public boolean validar () {
  if (textFieldEnterExpression==null) {
   //TODO: serious error! Send a warning to us?
   //DEBUG: serious error! Send a warning to us?
   String [] msgs = { "" + this.numQuest };
   String msgErr = ResourceReader.msgComVar("exercRNerrDebug1", "OBJ", msgs);
   this.janelaExercicio.setMessage(msgErr); // "Something very strange happened! The exercise item number $OBJ$ is damaged"
   AttentionToolTip.showToolTipText(this, msgErr); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

   return false;
   }
  String textNumberValue = textFieldEnterExpression.getText();
  if (textNumberValue==null || textNumberValue=="" || textNumberValue.length()==0) {
   String [] msgs = { "" + this.numQuest };
   String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs) + ". " + ResourceReader.msg("exercRNerrEmpt");
   indicaCampoComErro(textFieldEnterExpression);
   this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$." "It is empty!"
   AttentionToolTip.showToolTipText(textFieldEnterExpression, msgErr); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

   return false;
   }

  if (validacaoOk(textFieldEnterExpression,true)) { // defined in: 'igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java'
   respostaAluno = textNumberValue;
   return true;
   }
  return false;
  } // public boolean validar()


 private double valorGabarito () {
  double doubleValue = -1;
  try {
   doubleValue = Double.parseDouble(respostaGabarito);
  } catch (Exception except) { System.err.println("PainelRespostaNumerica.valorGabarito(): " + except.toString()); }
  return doubleValue;
  }

 public double valorResposta () {
  double doubleValue = -1;
  try {
   doubleValue = Double.parseDouble(respostaAluno);
  } catch (Exception except) { System.err.println("PainelRespostaNumerica.valorResposta(): " + except.toString()); } // except.printStackTrace();
  return doubleValue;
  }

 public int comparaResposta () {
  maxNumError = 1;
  if (!erroAceitavel(valorGabarito(), valorResposta())) {
   diagnostico += respostaErrada(respostaAluno) + "\r\n";
   registraDiagnostico(diagnostico);
   numErros++;
   }
  else {
   diagnostico += respostaCerta(respostaAluno) + "\r\n";
   registraDiagnostico(diagnostico);
   numAcertos++; // igraf.moduloExercicio.evento.DiagnosticEvent.numAcertos++
   }

  setValorCorrecao(correcao());
  setValorResposta(String.valueOf(valorResposta()));    
  return numErros;
  }

 public void updateLabels () {
  super.updateLabels();
  setLabelText(ResourceReader.msg("exercRNdigR")); 
  }

 }
