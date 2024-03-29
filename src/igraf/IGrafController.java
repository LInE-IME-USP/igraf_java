/*
 * iGraf : interactive Graphics in the Internet
 * LInE - line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 *
 * @see igraf/moduloExercicio/eventos/DiagnosticEvent.java
 * @see igraf/moduloExercicio/visao/JanelaDiagnostico.java
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf;

import igraf.basico.io.ResourceReader;
import igraf.moduloExercicio.eventos.DiagnosticEvent;
//import igraf.moduloExercicio.visao.JanelaDiagnostico;
import igraf.moduloExercicio.visao.JanelaInfo;

import java.awt.Frame;

import javax.swing.JFrame;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class IGrafController extends CommunicationFacade {

 private IGraf igraf;


 public IGrafController (IGraf igraf) {
  this.igraf = igraf;
  }


 // @see: igraf/moduloExercicio/controle/JanelaExercicioController.java
 public void filtrarEventoEntrada (CommunicationEvent ie) {

  String strCommand = ie.getCommand();
  if (strCommand.equals(ResourceReader.msg("madPort")))
   igraf.changeLanguage(ResourceReader.PORTUGUESE);
  else
  if (strCommand.equals(ResourceReader.msg("madEngl")))
   igraf.changeLanguage(ResourceReader.ENGLISH);

  //T System.out.println("IGrafController.javafiltrarEventoEntrada: "+ie.getClass().getName());

  if (ie instanceof DiagnosticEvent) { // igraf.moduloExercicio.eventos.DiagnosticEvent
   DiagnosticEvent de = (DiagnosticEvent) ie;

   boolean showEvaluation = (IGraf.iLM_PARAM_Feedback == null || IGraf.iLM_PARAM_Feedback == "" || !IGraf.iLM_PARAM_Feedback.equals("false"));

   //T System.out.println("IGrafController.javafiltrarEventoEntrada: showEvaluation=" + showEvaluation +", iLM_PARAM_Feedback=" + IGraf.iLM_PARAM_Feedback);

   if ((de.getDiagnostico()!=null && showEvaluation)) { // de.getCodigoAcao() == -1
    // show to the student the results of his exercise
    //d new JanelaDiagnostico(de);
    new JanelaInfo(de);

    // if (de.getDiagnostico() != null) igraf.setAnswer(de.getDiagnostico()); - getAnswer() num iMA deve devolver o conteudo-resposta do aluno
    igraf.setTrace(de.getDiagnostico());

    if (de.getNumAcertos() > -1) { // igraf/moduloExercicio/evento/DiagnosticEvent.java
     int total = de.getNumAcertos() + de.getNumErros();
     igraf.setEvaluation( (double) de.getNumAcertos() / total);
     //System.out.println("IGrafController.javafiltrarEventoEntrada: "+de.getNumAcertos()+","+de.getNumErros()+"/"+total);
     }
    else
     igraf.setEvaluation(0);

    if (de.getSessao() != null) {
     igraf.setSessao(de.getSessao());
     igraf.setAnswer(de.getSessao());
     }
    } // if (de.getDiagnostico() != null && IGraf.iLM_PARAM_Feedback == null)

   } // if (ie instanceof DiagnosticEvent)

  } // public void filtrarEventoEntrada(CommunicationEvent ie)


 public void filtrarEventoSaida (CommunicationEvent ie) {
  disseminarEventoExternamente(ie);
  }

 }
