
/*
 * iGraf : interactive Graphics in the Internet
 * LInE - line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @author RP, LOB
 *
 * @description
 * 
 * @see difusor/Broadcaster.java: Broadcaster implements ModuleEventListener
 * @see difusor/evento/CommunicationEvent.java
 * @see difusor/CommunicationFacade.java: public void disseminarEventoExternamente(CommunicationEvent ie): ModuleEventListener iel=...; iel.disseminarEvento(CommunicationEvent ie);
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 *
 */

package difusor.evento;

import java.util.EventListener;

/**
 * Interface implementada pelo broadcaster para que possa ouvir eventos saindo dos
 * m�dulos, captur�-los e os retransmitir a todos os m�dulos, incluindo aquele que
 * gerou o evento. 
 */

public interface ModuleEventListener extends EventListener {

 /**
  * M�todo chamado de dentro do m�dulo para notificar ao broadcaster que ocorreu
  * um evento que precisa ser disseminado pelo sistema.  Recebe um objeto IgrafEvent 
  * e o redistribui a todos os m�dulos do programa.
  * @param e
  */
 public void disseminarEvento (CommunicationEvent e); // implemented in 'difusor/Broadcaster.java': public void disseminarEvento(CommunicationEvent e): broadcastEvent(e);

 }
