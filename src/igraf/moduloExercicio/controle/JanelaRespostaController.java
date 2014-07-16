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
 * @description Module to deal with exercises.
 * 
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 * @see igraf/moduloExercicio/ModuloExercicio.java: makes 'JanelaRespostaController jrc = new JanelaRespostaController(this, true);'
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

import java.util.Vector;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloExercicio.eventos.DiagnosticEvent;
import igraf.moduloExercicio.eventos.RespostaDiscursivaEvent;
import igraf.moduloExercicio.eventos.RespostaEvent;
import igraf.moduloExercicio.visao.resposta.AnswerVisualizerFrame;
import igraf.moduloExercicio.visao.resposta.Attempt;
import igraf.moduloExercicio.visao.resposta.DiscursiveAnswer;
import igraf.moduloExercicio.visao.resposta.ObjectiveAnswer;


public class JanelaRespostaController extends CommunicationController {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class                                                                                                    
 public static final String IGCLASSPATH = "igraf/moduloExercicio/controle/JanelaRespostaController.java";
 //IGraf.launchStackTrace();

 private Vector attemptList;
 private Vector answerList;
 private Vector gabarito;
 private AnswerVisualizerFrame avf;
 private int item, k = 1;

 public JanelaRespostaController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  // System.out.println("\nsrc/igraf/muloExercicio/controle/JanelaRespostaController.java: module=" + module.getClass().getName());
  reset();
  }


 private void reset () {
  attemptList = new Vector();
  answerList = new Vector();
  gabarito = new Vector();
  item = 1;
  }


 // From: difusor.CommunicationFacade.disseminarEventoInternamente(CommunicationEvent communicationEvent): communicationController.tratarEventoRecebido(communicationEvent);
 public void tratarEventoRecebido (CommunicationEvent ie) {
  String strCommand = ie.getCommand();
  if (strCommand.equals(ResetEvent.RESET))
   reset();
  else
  if (strCommand.equals(ResourceReader.msg("mexExercEval")))
   avf = new AnswerVisualizerFrame(attemptList);

  if (ie instanceof DiagnosticEvent)
   trataDiagnostico(ie);
  else
  if (ie instanceof RespostaEvent && strCommand.equals(RespostaEvent.READ_EXERCISE) && !(ie instanceof RespostaDiscursivaEvent)) {
   RespostaEvent re = (RespostaEvent)ie;
   gabarito.add(re.getRespostaGabarito());
   }
  }

 private void trataDiagnostico (CommunicationEvent ie) {
  DiagnosticEvent de = (DiagnosticEvent) ie;
  if (de.getCommand().equals(DiagnosticEvent.LOAD_RESULT)) {
   ObjectiveAnswer  objectiveAnswer;
   DiscursiveAnswer discursiveAnswer;
   Attempt attempt;

   switch (de.getCodigoAcao()) {
    case Acao.respostaObjetiva:
     String r = (String)gabarito.get(item-1);
     objectiveAnswer = new ObjectiveAnswer(item++, de.getTentativa(), r, de.getStatus());
     answerList.add(objectiveAnswer);
     break;
    case Acao.respostaDiscursiva:
     discursiveAnswer = new DiscursiveAnswer(de.getArgumento());
     answerList.add(discursiveAnswer);
     break;
    case Acao.inserirComentarioAluno:
     discursiveAnswer = new DiscursiveAnswer(de.getArgumento());
     answerList.add(discursiveAnswer);
     break;
    case Acao.habilitarVisaoResposta:
     attempt = new Attempt(answerList, String.valueOf(k++));
     answerList = new Vector();
     attemptList.add(attempt);
     item = 1;
     break;
    default:
     if (igraf.IGraf.IS_DEBUG) System.err.println("igraf/moduloExercicio/controle/JanelaRespostaController.java: trataDiagnostico(CommunicationEvent): ");
     System.err.println("Error in action code: type=" + de.getCodigoAcao());
    } //switch (de.getCodigoAcao())
   } // if (de.getCommand().equals(DiagnosticEvent.LOAD_RESULT))
  } // private void trataDiagnostico(CommunicationEvent ie)

 }
