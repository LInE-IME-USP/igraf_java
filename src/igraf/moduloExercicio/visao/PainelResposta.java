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
 * @see igraf/moduloExercicio/controle/JanelaExercicioController.java: PainelRespostaAlgebrica pra = new PainelRespostaAlgebrica(...)
 * @see igraf/moduloExercicio/visao/PainelListaResposta.java: String responder (): totalAcertos += pr.getNumAcertos();
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


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JComponent; // Component 
import javax.swing.JLabel; // Label
import javax.swing.JPanel; // Panel
import javax.swing.JTextField; // TextField

import igraf.IGraf;
import igraf.basico.event.AttentionToolTip;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.basico.util.Funcao;
import igraf.moduloSuperior.controle.entrada.Analisa;

import difusor.i18N.LanguageUpdatable;


public abstract class PainelResposta extends JPanel implements LanguageUpdatable {

 protected static final int WIDTH = 500, HEIGHT = 25; // PainelRespostaPonto.java and PainelRespostaIntervalo.java
 protected static final int WIDTHLABEL = 100; // to JLabel in 'PainelRespostaPonto.java'

 protected static final Font fonte = new Font("Arial", Font.PLAIN, 10);

 private float precisao;
 double ERROMAX = 0.011;
 protected int // in 'igraf/moduloExercicio/visao/PainelRespostaIntervalo.java, PainelRespostaPonto.java, ...'
   numQuest,
   numAcertos = 0, // definido por PainelResposta*.java
   numErros = 0;   // idem
 protected int maxNumError;

 protected String numResp, diagnostico = ""; // in 'igraf/moduloExercicio/visao/PainelRespostaIntervalo.java, PainelRespostaPonto.java,...'
 private String valorResposta, valorCorrecao;
 
 protected String semAcertos    = ResourceReader.msg("tentNaoCert");
 protected String acertoTotal   = ResourceReader.msg("tentTotCert");
 protected String acertoParcial = ResourceReader.msg("tentParCert");

 private Vector listaDiagnostico;

 private JLabel labelPanel, labelNumberOfAnswers;  
 private JPanel panelLeft, panelRight;

 //import igraf.moduloExercicio.visao.JanelaExercicio;
 protected JanelaExercicio janelaExercicio;


 /**
  * Recebe uma resposta e o n�mero da quest�o considerada e devolve o n�mero de erros encontrados.
  * Each found error is inserted into the diagnostic list to help the student to understand his error.
  */
 public abstract int comparaResposta ();

 // In: igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java
 //     igraf/moduloExercicio/visao/PainelRespostaNumerica.java
 //     igraf/moduloExercicio/visao/PainelRespostaPonto.java
 protected abstract void configurePanelAtRight (); //

 public abstract boolean validar ();
 public abstract void limparCampos ();


 public PainelResposta () { // Abstract class => can not define 'janelaExercicio'
  //DEBUG: try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  // this.janelaExercicio = janelaExercicio; - not now...
  precisao = 0.01f;
  setLayout(new GridLayout(1,2,3,3));
  listaDiagnostico = new Vector();

  panelLeft = new JPanel(); // new JPanel() { public Insets getInsets() { return new Insets(2,2,2,2);    }    };
  panelLeft.setLayout(new BorderLayout());
  add(panelLeft);

  panelRight = new JPanel(new GridLayout(1,1)); // painel para resposta (usualmente um JTextField em RespostaAlgebrica, RespostaIntervalo...)
  add(panelRight);

  labelPanel = new JLabel(); // vai informa��o do tipo de item (p.e., "Digite um par ordenado" em RespostaPonto)
  labelPanel.setForeground(EsquemaVisual.corLetraTipoItem);
  //labelPanel.setAlignment(JLabel.LEFT); // Label.CENTER);
  panelLeft.add(labelPanel);

  labelNumberOfAnswers = new JLabel();
  labelNumberOfAnswers.setForeground(EsquemaVisual.corLetraRotulo);
  panelLeft.add("West", labelNumberOfAnswers); // painel que vai o "Item N"
  setBackground(EsquemaVisual.corAreaDesenho);
  } // public PainelResposta()



 // From: 'igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java' in 'validacaoOk(JTextField): validacaoOk(textField,true)'
 // From: 'igraf/moduloExercicio/visao/PainelRespostaNumerica.java' in 'validar(): if (validacaoOk(textFieldEnterExpression)) ...'
 protected boolean validacaoOk (JTextField textField, boolean onlyExpr) {
  String str = removeEspacos(textField.getText()); // importante: a express�o de entrada N�O pode ter espa�os
  str = str.replace(',', '.');
  textField.setText(str);
  //T System.out.println("src/igraf/moduloExercicio/visao/PainelResposta.java: validacaoOk(...): 1: " + str);

  if (str.length() == 0) {
    String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
    String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs);
    this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
    AttentionToolTip.showToolTipText(textField, msgErr); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

    indicaCampoComErro(textField);
    enviaMensagemErro(ResourceReader.msg("exercRAErroCampoResp")); //

    return false;
    }

  if (Analisa.temParametro(str)) {
    String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
    String msgErr = ResourceReader.msg("exercRAErroAnimNao");
    this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
    AttentionToolTip.showToolTipText(textField, msgErr); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

    indicaCampoComErro(textField);
    enviaMensagemErro(msgErr); //
    return false;
    }

  if (Analisa.temParametro(str)) { // igraf.moduloSuperior.controle.entrada.Analisa
    String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
    String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs);
    this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
    AttentionToolTip.showToolTipText(textField, msgErr);

    indicaCampoComErro(textField);
    enviaMensagemErro(msgErr);
    System.err.println(IGraf.debugErrorMsg("src/igraf/moduloExercicio/visao/PainelResposta.java: ") + "validacaoOk(...): the number can not have parameters! As in " + str);
    return false;
    }
  else
  if (Analisa.sintaxeCorreta(str)) { // igraf.moduloSuperior.controle.entrada.Analisa
    return true;
    }
  else {
    String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
    String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs);
    this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
    AttentionToolTip.showToolTipText(textField, msgErr);

    indicaCampoComErro(textField);
    enviaMensagemErro(ResourceReader.msg("exercRAErroExpInv") + str);
    return false;
    }
  } // protected boolean validacaoOk(JTextField textField, boolean onlyExpr)


 // From: 'igraf/moduloExercicio/visao/PainelRespostaPonto.java' and 'igraf/moduloExercicio/visao/PainelRespostaNumerica.java'
 protected boolean validacaoOk (JTextField textField) {
  Funcao funcao = null;
  String str = removeEspacos(textField.getText());
  //T System.out.println("src/igraf/moduloExercicio/visao/PainelResposta.java: validacaoOk(...): 1: " + str);

  if (str.equals("i") || str.equalsIgnoreCase("infinity") || str.equalsIgnoreCase("infinito")) {
   textField.setText(String.valueOf(Double.POSITIVE_INFINITY));
   return true;
   }
  else
  if (str.equals("-i") || str.equalsIgnoreCase("-infinity") || str.equalsIgnoreCase("-infinito")) {
   textField.setText(String.valueOf(Double.NEGATIVE_INFINITY));
   return true;
   }

  str = str.replace(',', '.');
  //T System.out.println("src/igraf/moduloExercicio/visao/PainelResposta.java: validacaoOk(...): 2: " + str); 

  try {
   //Double.valueOf(s).doubleValue();
   funcao = new Funcao(1);
   funcao.constroiExpressao(str);
   textField.setText(String.valueOf(funcao.f(0)));
   return true;
   } catch (NullPointerException e) {
   //   indicaCampoComErro(textField);
   //   enviaMensagemErro("N�mero ou fun��o inv�lida: " + s); 
   //   e.printStackTrace(); 
   return false;
   }
  } // protected boolean validacaoOk(JTextField textField)


 protected boolean temVariavelX (String x1, String x2) {
  return !(x1.indexOf('x') < 0 & x2.indexOf('x') < 0);
  }


 protected boolean erroAceitavel (double respGabarito, double respAluno) {   
  double erro = Math.abs(respGabarito - respAluno);
  if (erro > ERROMAX)
   return false;
  return true;
  }


 protected void indicaCampoComErro (JTextField textField) {
  textField.requestFocus();
  textField.selectAll();
  }


 protected String removeEspacos (String str) {
  StringTokenizer st = new StringTokenizer(str, " ", false);
  String strAux = "";
  while (st.hasMoreTokens())
   strAux += st.nextToken();
  return strAux;
  }


 protected void enviaMensagemErro (String mensagem) {
  // System.out.println("Resposta: "+mensagem);
  try {
    //new JanelaFeedback(ResourceReader.msg("exercRErroAval"), mensagem); // "Erro na tentativa de avalia��o"
    this.janelaExercicio.setMessage(mensagem);
    System.out.println("PainelResposta.java: " + mensagem);
  } catch (Exception e) {
    System.err.println("Error: there was an error message report <"+mensagem+">: "+e);
    e.printStackTrace();
    }
  }


 protected String respostaCerta (String s) {
  return " Item " + numQuest + ". Parab�ns! A resposta  \"" + s + "\"  est� de acordo com o esperado.  ";
  }


 protected String respostaErrada (String s) {
  return " Item " + numQuest + ". A resposta  \"" + s + "\"  n�o est� de acordo com o esperado.  ";
  }


 protected float getPrecisao () {
  return precisao;
  }


 protected void setLabelText (String text) {
  labelPanel.setText(text);
  }

 protected void setPrecisao (float precisao) {
  this.precisao = precisao;
  }

 protected void setNumeroResposta (int num) {
  this.numQuest = num;
  numResp = "Item:" + String.valueOf(num) + " ";
  labelNumberOfAnswers.setText("Item " + String.valueOf(num) + " "); // 
  }

 protected int getNumResposta () {
  return numQuest;
  }


 /** 
  * M�todo que retorna o argumento a ser gravado na sessao de cria��o de um exerc�cio.
  * @return resposta (do professor) para criar gabarito.
  */
 public String getResposta () {
  return numResp;
  }

 protected void adicionaConteudoDireito (JComponent p) {
  panelRight.add(p);
  }


 public String getDiagnostico () {
  return diagnostico;
  }


 public void resetDiagnostico () {
  diagnostico = "";
  }


 protected void registraDiagnostico (String s) {  
  listaDiagnostico.addElement(s);
  }


 public Vector getListaDiagnostico () {
  return listaDiagnostico;
  }


 public int getNumErros () {
  return numErros;
  }


 public int getNumAcertos () {
  return numAcertos;
  }


 public void reset () {
  numAcertos = 0;
  numErros = 0;
  }


 public void setNumErros (int numErros) {
  this.numErros = numErros;
  }


 public void setValorCorrecao (String valorGabarito) {
  this.valorCorrecao = valorGabarito;
  }


 public void setValorResposta (String valorResposta) {
  this.valorResposta = valorResposta;
  }


 public String getValorCorrecao () {
  return valorCorrecao;
  }


 public String getValorResposta () {
  return valorResposta;
  }


 protected String correcao () {
  if (numErros == 0)
   return acertoTotal;
  if (numErros == maxNumError)
   return semAcertos;
  else return acertoParcial;
  }


 public Dimension getPreferredSize () {
  return new Dimension(WIDTH, HEIGHT);
  }


 public Insets getInsets () {
  return new Insets(2,2,2,2);
  }


 public void updateLabels () {
  semAcertos    = ResourceReader.msg("tentNaoCert");
  acertoTotal   = ResourceReader.msg("tentTotCert");
  acertoParcial = ResourceReader.msg("tentParCert");  
  }

 }
