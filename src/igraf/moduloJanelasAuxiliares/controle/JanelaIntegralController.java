/**
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @author RP, LOB
 *
 * @description
 *
 * @see 
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 *
 **/

package igraf.moduloJanelasAuxiliares.controle;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloJanelasAuxiliares.visao.integral.JanelaIntegral;


public class JanelaIntegralController extends JanelaController {
 
 private JanelaIntegral janelaIntegral;
 
 public JanelaIntegralController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  } 

 public void tratarEventoRecebido (CommunicationEvent ie) {
  super.tratarEventoRecebido(ie);

  if (ie instanceof ChangeLanguageEvent) {
   if(janelaIntegral != null) janelaIntegral.updateLabels();
   }  
  else
  if (ie instanceof GraphicOnScreenChangedEvent && janelaIntegral != null) 
   atualizaListas();
  else
  if (ie.getCommand().equals(ResourceReader.msg("mcIICalc"))) {
   janelaIntegral = new JanelaIntegral(this);
   atualizaListas();
   }
  }

 private void atualizaListas () {
  preencheChoiceFuncoes(janelaIntegral.getChoiceUm());
  preencheChoiceFuncoes(janelaIntegral.getChoiceDois());
  janelaIntegral.getChoiceDois().insert("<"+ResourceReader.msg("msgEixoX")+">", 0); // "<eixo x>"
  }

 }
