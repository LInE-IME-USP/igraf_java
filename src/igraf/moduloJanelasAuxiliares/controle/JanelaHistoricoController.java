/**
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
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
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 **/

package igraf.moduloJanelasAuxiliares.controle;

import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.IgrafMenuArquivoEvent;
import igraf.moduloCentral.eventos.IgrafSessaoEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloJanelasAuxiliares.visao.JanelaHistorico;
import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class JanelaHistoricoController extends JanelaController {

 private JanelaHistorico jh;

 public JanelaHistoricoController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie.getCommand().equals(ResourceReader.msg("mexExercHistorico"))){
   jh = new JanelaHistorico(this);
   iniciaHistorico();
   }
  
  if (ie instanceof IgrafMenuArquivoEvent && jh != null) {
   IgrafMenuArquivoEvent imae = (IgrafMenuArquivoEvent) ie;   
   jh.setNextStep(imae.getCommand());
   }
  }
 
 public void iniciaHistorico () {
  enviarEvento(new IgrafSessaoEvent(this));
  enviarEvento(new ResetEvent(this, ResetEvent.VIEW_HISTORY));
  }

 }
