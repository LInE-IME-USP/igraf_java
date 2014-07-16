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
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 * @see igraf/moduloExercicio/ModuloExercicio.java: makes 'JanelaExercicioController jec = new JanelaExercicioController(this, true);'
 * @see igraf/moduloExercicio/visao/JanelaExercicio.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.GravadorLeitor;
import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.controle.ArquivoController;
import igraf.moduloCentral.eventos.EstadoTelaEvent;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloExercicio.ModuloExercicio;
import igraf.moduloExercicio.eventos.DiagnosticEvent;
import igraf.moduloExercicio.eventos.RespostaAlgebricaEvent;
import igraf.moduloExercicio.eventos.RespostaDiscursivaEvent;
import igraf.moduloExercicio.eventos.RespostaEvent;
import igraf.moduloExercicio.eventos.RespostaIntervaloEvent;
import igraf.moduloExercicio.eventos.RespostaNumericaEvent;
import igraf.moduloExercicio.eventos.RespostaPontoEvent;
import igraf.moduloExercicio.visao.JanelaExercicio;
import igraf.moduloExercicio.visao.PainelComentario;
import igraf.moduloExercicio.visao.PainelListaResposta;
import igraf.moduloExercicio.visao.PainelResposta;
import igraf.moduloExercicio.visao.PainelRespostaAlgebrica;
import igraf.moduloExercicio.visao.PainelRespostaIntervalo;
import igraf.moduloExercicio.visao.PainelRespostaNumerica;
import igraf.moduloExercicio.visao.PainelRespostaPonto;
import igraf.moduloInferior.visao.InfoPane;
import igraf.moduloSuperior.visao.PainelBotoes;


public class JanelaExercicioController extends CommunicationController implements ActionListener {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloExercicio/controle/JanelaExercicioController.java";

 private IGraf igraf; // to send exercise when user push Evaluation internal button

 private PainelComentario pc;
 private static JanelaExercicio janelaExercicio;
 private PainelListaResposta painelListaResposta;
 private Vector listaFuncao;

 private PainelBotoes painelBotoes; // 'igraf/moduloSuperior/visao/PainelBotoes.java' is to access "menu exercise"
 // From: igraf/ContentPane.java: relationsModulesToBroadcaster(IGraf igraf, boolean ehApplet): me.setPainelBotoes(ms.getPainelBotoes());
 public void  setPainelBotoes (PainelBotoes painelBotoes) { this.painelBotoes = painelBotoes; janelaExercicio.setPainelBotoes(painelBotoes); } // from 'igraf/moduloExercicio/ModuloExercicio.java'

 private InfoPane infoPane; // to access "status bar"


 // From: igraf/moduloExercicio/JanelaExercicioController
 public JanelaExercicioController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  //T System.out.println("\nsrc/igraf/moduloExercicio/controle/JanelaExercicioController.java: inicio");
  //T try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e1) {e1.printStackTrace(); }
  this.infoPane = ((ModuloExercicio)module).getInfoPane();
  //T System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: module=" + module.getClass().getName() + ", infoPane=" + this.infoPane);

  janelaExercicio = new JanelaExercicio(this, this.painelBotoes, this.infoPane);
  painelListaResposta = janelaExercicio.getPainelListaResposta();
  pc = janelaExercicio.getPainelComentario();
  }

 // ContentPane makes 'ModuloExercicio me = new ModuloExercicio()'
 // ModuloExercicio make 'JanelaExercicioController   jec = new JanelaExercicioController(this, true)
 // JanelaExercicioController needs in 'actionPerformed(ActionEvent e)' access to IGraf to send answer to the server when click "Evaluation"
 public void setIGraf (IGraf igraf) {
  this.igraf = igraf; // Exercise
  }


 // Set visible the window to construct exercise
 public static String showAnswerDialog () {
  janelaExercicio.setVisible(true);
  return ArquivoController.getSessao(); // igraf.moduloArquivo.controle.ArquivoController
  }


 // Came here with each funcion in drawing area
 public void tratarEventoRecebido (CommunicationEvent communicationEvent) {
    
  if (communicationEvent instanceof GraphicOnScreenChangedEvent) {
   try {
    listaFuncao = ((GraphicOnScreenChangedEvent)communicationEvent).getListaFuncaoVisivel();
    if (listaFuncao==null) { //TODO: NAO deveria chegar a este ponto!
       // if (IGraf.IS_DEBUG) System.out.println("igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(CommunicationEvent): listaFuncao=" + listaFuncao);
       return;
       }
    //T System.out.println("\nsrc/igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(CommunicationEvent): " + listaFuncao);
    painelListaResposta.setChoiceItens(listaFuncao); // PainelListaResposta painelListaResposta; Vector listaFuncao
    } catch (Exception e) { e.printStackTrace(); }
   }
  // System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido("+communicationEvent.getClass().getName()+"): listaFuncao=" + listaFuncao);

  if (communicationEvent instanceof ChangeLanguageEvent) {
   if (janelaExercicio != null) janelaExercicio.updateLabels(); // igraf.moduloExercicio.visao.JanelaExercicio janelaExercicio
   return;
   }

  String strCommand = communicationEvent.getCommand();

  if (strCommand.equals(ResetEvent.RESET_TAB) || strCommand.equals(ResetEvent.RESET)) { // "reset tab"
   // From: igraf/moduloCentral/visao/plotter/GraphPlotter.java
   //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 4 - " + ResetEvent.RESET_TAB);
   janelaExercicio.reset();
   painelListaResposta.reset();
   pc.reset();
   }
  else
  if (strCommand.equals(ResourceReader.msg("mexExercCriar"))) { // Create exercise
   //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 5 - create");
   janelaExercicio.setMode(JanelaExercicio.CRIACAO); // 1
   janelaExercicio.creatNewExercise(); // create new exercise - preserve the old one just in case user cancel the creation
   janelaExercicio.setVisible(true);
   }
  else
  if (strCommand.equals(ResourceReader.msg("mexExercEditar"))) { // Edit exercise
   //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 5 - edit");
   janelaExercicio.setMode(JanelaExercicio.CRIACAO); // 1
   janelaExercicio.setVisible(true);
   }
  else
  if (strCommand.equals(ResourceReader.msg("mexExercResp"))) { // Answer / send
   //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 6");
   showAnswerDialog();
   return;
   }
  // falta modo editar
  else
  if (strCommand.equals(EstadoTelaEvent.REPORT_LIMITS)) {
   //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 7");
   EstadoTelaEvent ete = (EstadoTelaEvent)communicationEvent;
   painelListaResposta.setLimits(ete.getXMin(), ete.getXMax());
   }
  else
  if (strCommand.equals(RespostaEvent.READ_EXERCISE)) {
   //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 8");
   janelaExercicio.setMode(JanelaExercicio.ENVIO);

   if (communicationEvent instanceof RespostaAlgebricaEvent) {
    //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 9");
    RespostaAlgebricaEvent r = (RespostaAlgebricaEvent) communicationEvent;
    PainelRespostaAlgebrica pra = new PainelRespostaAlgebrica(this.janelaExercicio, r.getNumQuest(), r.getStart(), r.getEnd(), r.getRespostaGabarito());
    painelListaResposta.inserePainelResposta(pra);
    janelaExercicio.atualizarDimensoes();
    painelListaResposta.addToNumberOfAnswers(4); // add 1 to PainelListaResposta.java: int numberOfAnswerType4
    }
   else
   if (communicationEvent instanceof RespostaNumericaEvent) {
    //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 10");
    RespostaNumericaEvent rne = (RespostaNumericaEvent) communicationEvent;
    PainelRespostaNumerica prn = new PainelRespostaNumerica(this.janelaExercicio, rne.getRespostaGabarito(), rne.getNumQuest());
    painelListaResposta.inserePainelResposta(prn);
    janelaExercicio.atualizarDimensoes();
    painelListaResposta.addToNumberOfAnswers(1); // add 1 to PainelListaResposta.java: int numberOfAnswerType1
    }
   else
   if (communicationEvent instanceof RespostaIntervaloEvent) {
    //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 11");
    RespostaIntervaloEvent r = (RespostaIntervaloEvent) communicationEvent;
    PainelRespostaIntervalo prn = new PainelRespostaIntervalo(this.janelaExercicio, r.getNumQuest(), r.getInf(), r.getSup(), r.getX1(), r.getX2());
    painelListaResposta.inserePainelResposta(prn);
    janelaExercicio.atualizarDimensoes();
    painelListaResposta.addToNumberOfAnswers(3); // add 1 to PainelListaResposta.java: int numberOfAnswerType3
    }
   else
   if (communicationEvent instanceof RespostaPontoEvent) {
    //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 12");
    RespostaPontoEvent rpe = (RespostaPontoEvent) communicationEvent;
    PainelRespostaPonto prn = new PainelRespostaPonto(this.janelaExercicio, rpe.getNumQuest(), rpe.getX1(), rpe.getX2());
    painelListaResposta.inserePainelResposta(prn);
    janelaExercicio.atualizarDimensoes();
    painelListaResposta.addToNumberOfAnswers(5); // add 1 to PainelListaResposta.java: int numberOfAnswerType5
    }
   else
   if (communicationEvent instanceof RespostaDiscursivaEvent) {
    //T if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: command='" + strCommand + "': 13");
    RespostaDiscursivaEvent rde = (RespostaDiscursivaEvent)communicationEvent;
    pc.setComentario(rde.getComentario());
    painelListaResposta.addToNumberOfAnswers(2); // add 1 to PainelListaResposta.java: int numberOfAnswerType2
    }
   }

  } // public void tratarEventoRecebido(CommunicationEvent communicationEvent)


 // Change item in: igraf/moduloExercicio/visao/PainelSeletorTipoResposta.java: JComboBox choiceList
 public void actionPerformedItemStateChanged (JComboBox choiceOrigin, ActionEvent e) {
  try { // this is NOT invoked by iGraf but a runnable 'java.awt.EventDispatchThread.run(EventDispatchThread.java:100)'
   //R Choice choiceOrigin = (Choice)e.getSource();
   int index = 0;
   if (choiceOrigin!=null)
      index = choiceOrigin.getSelectedIndex();
   else // System.err.println("\nError: in exercise window controller: ItemEvent " + e.getClass().getName() + ": choice is " + choiceOrigin + "!!");
      System.err.println("\nError: in exercise window controller: ItemEvent " + e.getClass().getName() + ": choice is " + choiceOrigin + "!!");
   //T System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: index=" + index);

   //ATTENTION: if it is used a seperator (an empty item in 'PainelSeletorTipoResposta.java: JComboBox choiceList')
   //ATTENTION: you must avoid its index here adding something like '&& index!=5' (it the separator is in index = 5)
   if (index!=0) { // 'if (index!=0 && index!=PainelSeletorTipoResposta.SEPINDEX)'?
    janelaExercicio.alteraPainelResposta(index);

    if (index==4) //ATTENTION: index correspondent to "Math Expression to function" in 'PainelSeletorTipoResposta.java'
     enviarEvento(new RespostaAlgebricaEvent(this));

    }
  } catch (Exception except) {
   System.err.println("Error: in exercise window controller: " + e.getClass().getName() + ": " + except);
   except.printStackTrace();
   } 
  }


 private void enviaEventoResposta (Vector listaResposta) {
  for (int i = 0; i < listaResposta.size(); i++) {
   PainelResposta panelResp = (PainelResposta)listaResposta.get(i);

   if (panelResp instanceof PainelRespostaAlgebrica) {
    enviarEvento(new RespostaAlgebricaEvent(this, panelResp.getResposta()));
    }
   else
   if (panelResp instanceof PainelRespostaNumerica) {
    enviarEvento(new RespostaNumericaEvent(this, panelResp.getResposta()));
    }
   else
   if (panelResp instanceof PainelRespostaIntervalo) {
    enviarEvento(new RespostaIntervaloEvent(this, panelResp.getResposta()));
    }
   else
   if (panelResp instanceof PainelRespostaPonto) {
    enviarEvento(new RespostaPontoEvent(this, panelResp.getResposta()));
    }
   }
  }

 //RespostaEvent re = new RespostaEvent(this);

 public void actionPerformed (ActionEvent e) {
  //T System.out.println("\nsrc/igraf/moduloExercicio/controle/JanelaExercicioController.java: ItemEvent=" + e.getClass().getName());

  Object actionSource = e.getSource();
  if (actionSource instanceof JButton)
    actionPerformedButtonCliked(((JButton)e.getSource()).getActionCommand(), e);
  else
  if (actionSource instanceof JComboBox)
    actionPerformedItemStateChanged((JComboBox)e.getSource(), e);
  }


 private void actionPerformedButtonCliked (String strCommandAction, ActionEvent e) {
  // String strCommandAction = ((JButton)e.getSource()).getActionCommand();

  //SEND to the server!
  //AQUI colocar SEND!!!

  // src/igraf/basico/io/GravadorLeitor.java
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "actionPerformed action=" + strCommandAction);

  if (IGraf.IS_DEBUG) System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: evaluation=" + igraf.getEvaluation());

  boolean validData = painelListaResposta.dadosValidos(); // igraf.moduloExercicio.visao.PainelListaResposta
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: validData=" + validData);

  if (!validData) {
   if (IGraf.IS_DEBUG) System.err.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: ");
   System.err.println("Error: it is missing some answer... Cancel the evaluation and submission");
   return; // do not dismiss the 'static JanelaExercicio janelaExercicio'
   }
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: OK");

  //TODO: rever...
  // para a gravação do exercício: professor
  //T if (strCommandAction.equals(ResourceReader.msg("exercFQRCGabarito")) && validData) { // "Confirm answers"/"Confirmar gabarito"
  //T  //T System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: professor: " + strCommandAction); // "Confirmar respostas"
  //T  //re.setCommand(RespostaEvent.WRITE_ANSWER);
  //T  //enviarEvento(re);
  //T  enviaEventoResposta(painelListaResposta.getListaResposta());
  //T  if (janelaExercicio.getComentario().length() > 0)
  //T   enviarEvento(new RespostaDiscursivaEvent(janelaExercicio.getComentario(), this, Acao.inserirComentario));
  //T  }
  //T else System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: NAO professor");

  // exercFQREnvResp   = Avaliar [Evaluate]
  // strCommandAction  = Confirmar respostas
  // exercFQRCGabarito = Confirmar respostas - igraf/moduloExercicio/visao/JanelaExercicio.java
  //T System.out.println(" - exercFQREnvResp = " + ResourceReader.msg("exercFQREnvResp") + ", exercFQRCGabarito=" + ResourceReader.msg("exercFQRCGabarito") +
  //T                   ", strCommandAction = " + strCommandAction + ", painelListaResposta.dadosValidos = " + validData + " : ");
  //T System.out.println(" - strCommandAction=exercFQREnvResp? " + (strCommandAction.equals(ResourceReader.msg("exercFQRCGabarito"))) + ", painelListaResposta.dadosValidos=" + validData + " => " +
  //T (strCommandAction.equals(ResourceReader.msg("exercFQREnvResp")) && validData));

System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: strCommandAction=" + strCommandAction + "=" + ResourceReader.msg("exercFQRCGabarito")+"?");

  // para o envio da resposta: aluno
  if (strCommandAction.equals(ResourceReader.msg("exercFQRCGabarito")) && validData) { // "Confirm answers"
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: entrou confirma");
   DiagnosticEvent de = new DiagnosticEvent(this,DiagnosticEvent.SEND_RESULT , "",  Acao.habilitarVisaoResposta);

   de.setResultado(painelListaResposta.responder()); // igraf.moduloExercicio.eventos.DiagnosticEvent: makes 'this.resultado=resultado;' and 'this.sessao=sessao;'

   enviaEventoRespostaAluno(painelListaResposta.getListaRespostaAluno());
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: apos lancar DiagnosticEvent: #" + janelaExercicio.getComentario().length());

   if (janelaExercicio.getComentario().length() > 0)
    enviarEvento(new RespostaDiscursivaEvent(janelaExercicio.getComentario(), this, Acao.inserirComentarioAluno));
 
   de.setNumAcertos(painelListaResposta.getTotalAcertos());
   de.setNumErros(painelListaResposta.getTotalErros());
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: acertos=" + painelListaResposta.getTotalAcertos() +
", erros="+painelListaResposta.getTotalErros());

try{
   painelListaResposta.limparCampos();
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: limpou. modulo=" + this.getModulo().getClass().getName());
   enviarEvento(de); // difusor/controle/CommunicationController.java: modulo.filtrarEventoSaida(de)
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: enviou");
}catch(Exception e1){e1.printStackTrace();}

   // Try to send to the server (MA_PARAM_addresPOST)
   // In: igraf.basico.io.GravadorLeitor
   String answer = igraf.getAnswer(true); // false => internal call, open dialog window to answer
   int evaluationInt = (int) igraf.getEvaluation();
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: evaluationInt="+evaluationInt+"="+igraf.getEvaluation()+"\nContent=\n" + answer);
   int resultSend = GravadorLeitor.enviarResultadoExercicio(igraf, answer, evaluationInt);
System.out.println("src/igraf/moduloExercicio/controle/JanelaExercicioController.java: resultSend="+resultSend);

   if (IGraf.IS_DEBUG) System.out.println("igraf/moduloExercicio/controle/JanelaExercicioController.java: evaluation=" + evaluationInt);
   // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
   // exercFQREnvResp
   // [GL] isAuthoring=false
   // [GL] Server address to get answer: null

   } // if (strCommandAction.equals(ResourceReader.msg("exercFQRCGabarito")) && validData)


  janelaExercicio.fechaJanela();
  }


 private void enviaEventoRespostaAluno (Vector listaResposta) {
  String strItemAnswer;
  for (int i = 0; i < listaResposta.size(); i++) {
   strItemAnswer = (String)listaResposta.get(i);
   enviarEvento(new DiagnosticEvent(this, DiagnosticEvent.SEND_RESULT , strItemAnswer, Acao.respostaObjetiva));
   }
  }

 }
