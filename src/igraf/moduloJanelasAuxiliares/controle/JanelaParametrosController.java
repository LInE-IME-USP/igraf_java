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

import igraf.basico.event.ChangeLanguageEvent;
import igraf.moduloJanelasAuxiliares.eventos.AtualizaParametroEvent;
import igraf.moduloJanelasAuxiliares.visao.animacao.JanelaParametros;
import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public class JanelaParametrosController extends JanelaController {

 private JanelaParametros jp;
 
 public JanelaParametrosController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }

 public void enviaAtualizacaoParametro (AtualizaParametroEvent ape) {
  enviarEvento(ape);
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {  
  if (ie instanceof ChangeLanguageEvent)
   if (jp != null) jp.updateLabels();
  
  if (ie.getCommand().equals(AtualizaParametroEvent.SHOW_PARAM_PANEL)) {
   jp = new JanelaParametros(this);
   }
  }

 }
