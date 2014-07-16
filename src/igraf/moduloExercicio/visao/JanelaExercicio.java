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
 * @description This window prepare an exercise. In it user (teacher) can choose any item to be evaluated.
 * 
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 * @see igraf/moduloExercicio/ModuloExercicio.java: makes 'JanelaExercicioController jec = new JanelaExercicioController(this, true);'
 * @see igraf/moduloExercicio/controle/JanelaExercicioController.java: makes 'janelaExercicio = new JanelaExercicio(this, this.infoPane)'
 * @see igraf/moduloExercicio/visao/menuSelector/MenuItemPanel.java: extends JPanel with blocks, one to each primary menu
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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloExercicio.controle.JanelaExercicioController;
import igraf.moduloInferior.visao.InfoPane;
import igraf.moduloInferior.controle.InfoPaneController;
import igraf.moduloSuperior.visao.PainelBotoes;

import difusor.i18N.LanguageUpdatable;


public class JanelaExercicio extends JDialog implements  ActionListener, LanguageUpdatable {

 public static final int ENVIO = 0, CRIACAO = 1, ALT = 300, LARG = 570;

 private JPanel buttonsPanel, southPanel;

 private PainelListaResposta jePainelListaResposta = null; // options in the exercise
 private PainelListaResposta jePainelListaRespostaOld = null; // preserve the last exercise (case user cancel the new one, the old one is retuned)
 private PainelSeletorTipoResposta jePainelSeletorTipoResposta = null;
 private PainelSeletorTipoResposta jePainelSeletorTipoRespostaOld = null; // preserve the last exercise (case user cancel the new one, the old one is retuned)
 private PainelComentario jePainelComentario;

 //Test: public PainelSeletorTipoResposta getPs () { return jePainelSeletorTipoResposta; }
 //try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }

 private PainelBotoes painelBotoes; // to access "menu exercise"
 public void setPainelBotoes (PainelBotoes painelBotoes) { this.painelBotoes = painelBotoes; } // from: igraf/moduloExercicio/controle/JanelaExercicioController.java

 private InfoPane infoPane; // to access "status bar"
 //private InfoPaneController infoPaneController; // InfoPaneController.setMessage(String strMsg)
 //public void setInfoPaneController (InfoPaneController infoPC) { this.infoPaneController = infoPC; } // From: 
 //protected void setMessage (String strMsg) { infoPaneController.setMessage(strMsg); } // InfoPaneController.setMessage(String)
 protected void setMessage (String strMsg) { infoPane.setStatusBarMessage(strMsg); } // InfoPaneController.setMessage(String)

 private static JButton createButton (String title, String hint) {
   JButton jbutton = new JButton(ResourceReader.msg(title));
   jbutton.setToolTipText(ResourceReader.msg(hint));
   return jbutton;
   }

 // Avaliar; Limpar Campos; Cancelar
 private JButton
   buttonCancel = createButton("exercFQRcancel", "exercFQRcancelTooltip"), // Cancel
   buttonClear = createButton("exercFQRLimpa", "exercFQRLimpaTooltip"), // Clear answers
   buttonEvaluate = createButton("exercFQRCGabarito", "exercFQRCGabaritoTooltip"); // Confirm answers
   //ATTENTION: in 'igraf/moduloExercicio/controle/JanelaExercicioController.java: actionPerformedButtonCliked(...)' must use 'exercFQRCGabarito' to open window

 private int altura; // largura = 570

 private static int modo;
 public static int getModo () { return modo; } // in 'igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java'

 // Para distinguir se é resposta do aluno (não criptografa em 'Resposta*.java'
 // consultado em 'exercicio/Resposta*.java' ao chamar 'insereAcao(String s, int num)'
 private static boolean respAluno = false;
 public  static boolean getRespAluno () { return respAluno;  } // exercicio/Avaliador.java; exercicio.Resposta*.java
 public  static void    setRespAluno (boolean bool) { respAluno = bool;  } // igraf/sessao/Sessao.refazSessao(String str_arq)


 private JanelaExercicioController janelaExercicioController = null;


 // From: igraf/moduloExercicio/controle/JanelaExercicioController.java
 public JanelaExercicio (JanelaExercicioController jec, PainelBotoes painelBotoes, InfoPane infoP) {
  super(new JFrame(), false);
  this.janelaExercicioController = jec;
  this.painelBotoes = painelBotoes;
  this.infoPane = infoP;
  //T 
System.out.println("src/igraf/moduloExercicio/visao/JanelaExercicio.java: painelBotoes=" + this.painelBotoes+" infoPane=" + this.infoPane);
  this.respAluno = true;

  this.jePainelListaResposta = new PainelListaResposta(this);
  this.jePainelComentario = new PainelComentario(this); 

  // "Respostas objetivas"; "Inserir campos de resp. para os itens do exercício"; "A resp. para este exerc. deve ser apenas gráfica e/ou discursiva"
  this.jePainelSeletorTipoResposta = new PainelSeletorTipoResposta(jec); // modo: 0 => objetivas; 1 => ins. campos; 2 => gráf. ou texto

  JPanel northPanel01;
  northPanel01 = new JPanel(new BorderLayout());
  southPanel = new JPanel(new BorderLayout());

  northPanel01.add("North", jePainelComentario);
  northPanel01.add("South", jePainelSeletorTipoResposta);

  buttonsPanel = configuraBotoes(jec);

  southPanel.add("North", this.jePainelListaResposta);  
  southPanel.add("South", buttonsPanel); 

  addWindowListener(new WindowAdapter () {
   public void windowClosing(WindowEvent e) { fechaJanela();  }
   });

  getContentPane().add("North", northPanel01);
  getContentPane().add("South", southPanel);
  pack();
  }


 // Show the construction exercise window
 // From: igraf.moduloExercicio.controle.JanelaExercicioController.tratarEventoRecebido(JanelaExercicioController.java:140)
 public void setMode (int modo) {
  JanelaExercicio.modo = modo;

  if (modo == 0) { // exercise to the student
   setTitle("iGraf - " + ResourceReader.msg("exercFQRPRAluno")); 
   }
  else { // modo==1: it is authoring to the teacher
   setTitle("iGraf - " + ResourceReader.msg("exercFQRPCGabarito"));
   buttonEvaluate.setLabel(ResourceReader.msg("exercFQRCGabarito")); 
   }
  // System.out.println("src/igraf/moduloExercicio/visao/JanelaExercicio.java: modo="+modo);
  // try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
  jePainelComentario.setMode(modo);
  jePainelSeletorTipoResposta.setMode(modo);
  }


 // Painel inferior: com botões "buttonCancel", "limpar" e "avaliar"
 private JPanel configuraBotoes (JanelaExercicioController jec) {
  JPanel buttonsPanel = new JPanel(new GridLayout(1,3));

  buttonCancel.addActionListener(this);
  buttonClear.addActionListener(this);
  buttonEvaluate.addActionListener(jec);

  buttonCancel.setBackground(EsquemaVisual.corFundoBotao);
  buttonClear.setBackground(EsquemaVisual.corFundoBotao);
  buttonEvaluate.setBackground(EsquemaVisual.corFundoBotao);

  buttonCancel.setForeground(EsquemaVisual.corLetraBotao);
  buttonClear.setForeground(EsquemaVisual.corLetraBotao);
  buttonEvaluate.setForeground(EsquemaVisual.corLetraBotao);

  buttonsPanel.add(buttonCancel);
  buttonsPanel.add(buttonClear);
  buttonsPanel.add(buttonEvaluate);

  return buttonsPanel;
  } // private void configuraBotoes(JanelaExercicioController jec)


 // Item in JComboBox changed: insert a new exercise item or remove the last one
 // igraf.moduloExercicio.controle.JanelaExercicioController.itemStateChanged(JanelaExercicioController.java:207)
 public void alteraPainelResposta (int index) {
  // System.out.println("src/igraf/moduloExercicio/visao/JanelaExercicio.java: alteraPainelResposta(" + index + ")");
  this.jePainelListaResposta.alteraPainelResposta(index);
  //R validate(); - nao mais necessario no Swing
  pack(); // neet to present JPanel changes (after insertion or remotion of an exercise item)
  }


 // Create a new exercise
 // From: igraf/moduloExercicio/controle/JanelaExercicioController.java
 public void creatNewExercise () {
  if (this.jePainelListaRespostaOld!=null) { // was tried another "answer model"
   //T System.out.println("\nJanelaExercicio.java: creatNewExercise(): criar novo: ja existia um...");
   this.jePainelListaRespostaOld = this.jePainelListaResposta; // preserve the last exercise (case user cancel the new one, the old one is retuned)
   this.jePainelListaResposta = new PainelListaResposta(this);
   this.jePainelSeletorTipoRespostaOld = this.jePainelSeletorTipoResposta;
   this.jePainelSeletorTipoResposta = new PainelSeletorTipoResposta(this.janelaExercicioController);
   southPanel.remove(this.jePainelListaRespostaOld);
   southPanel.add("North", this.jePainelListaResposta);
   pack();
   }
  else {
   //T System.out.println("\nJanelaExercicio.java: creatNewExercise(): criar novo: comecou agora");
   //this.painelBotoes.setEnabledMenuItem("MenuExercicio", "mexExercEditar", true);
   }
  } // public void creatNewExercise()


 // Forget any item inserted in "answer model" - the previous one will be preserved
 public void cancel () {
  setVisible(false);
  respAluno = false;
  }


 public void fechaJanela () {
  if (this.jePainelListaRespostaOld!=null) { // return the old list of item
   //T System.out.println("\nJanelaExercicio.java: fechaJanela(): tem anterior");
   PainelListaResposta jePainelListaRespostaTemp = this.jePainelListaResposta;
   PainelSeletorTipoResposta jePainelSeletorTipoRespostaTemp = this.jePainelSeletorTipoResposta;

   this.jePainelListaResposta = this.jePainelListaRespostaOld;
   this.jePainelListaRespostaOld = jePainelListaRespostaTemp;

   this.jePainelSeletorTipoResposta = this.jePainelSeletorTipoRespostaOld;
   this.jePainelSeletorTipoRespostaOld = jePainelSeletorTipoRespostaTemp;
   }
  else {
   //T System.out.println("\nJanelaExercicio.java: fechaJanela(): primeira vez");
   this.jePainelListaRespostaOld = this.jePainelListaResposta;
   this.jePainelSeletorTipoRespostaOld = this.jePainelSeletorTipoResposta;
   }

  if (this.jePainelListaResposta!=null) { // this "model answer" has something => activate option "Edit exercise"
   this.painelBotoes.setEnabledMenuItem("menuExerc", "mexExercEditar", true);
   }

  setVisible(false);
  respAluno = false;
  }


 // Actions: Cancel | Clear answers | Confirm answers
 public void actionPerformed (ActionEvent e) {
  Object objectSource = e.getSource();
  // System.out.println("JanelaExercicio.java: actionPerformed: " + objectSource);
  if (objectSource == buttonCancel)
   cancel();
  else
  if (objectSource == buttonClear)
   jePainelListaResposta.limparCampos(); // PainelListaResposta
  }


 public PainelListaResposta getPainelListaResposta () {
  return jePainelListaResposta;
  }


 public void reset () {
  jePainelSeletorTipoResposta.reset(); // PainelSeletorTipoResposta
  atualizarDimensoes();
  }


 public void atualizarDimensoes () {
  validate();
  pack();
  }


 public String getComentario () {
  return jePainelComentario.getComentario();
  }


 public PainelComentario getPainelComentario () {
  return jePainelComentario;
  }


 public void updateLabels () {
  buttonCancel.setLabel(ResourceReader.msg("msgCancelar")); // Cancel
  buttonClear.setLabel(ResourceReader.msg("exercFQRLimpa")); // Clear answers                                                                   
  buttonEvaluate.setLabel(ResourceReader.msg("exercFQRCGabarito")); // Confirm answers 

  buttonCancel.setToolTipText(ResourceReader.msg("exercFQRcancelTooltip"));
  buttonClear.setToolTipText(ResourceReader.msg("exercFQRLimpaTooltip"));
  buttonEvaluate.setToolTipText(ResourceReader.msg("exercFQRCGabaritoTooltip"));

  jePainelComentario.updateLabels(); // PainelComentario jePainelComentario

  jePainelSeletorTipoResposta.updateLabels(); // PainelSeletorTipoResposta jePainelSeletorTipoResposta

  jePainelListaResposta.updateLabels(); // PainelListaResposta jePainelListaResposta
  }

 }
