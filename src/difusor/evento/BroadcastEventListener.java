
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
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package difusor.evento;


/**
 * Interface que deve ser implementada por todos os módulos do iGraf para que possam
 * receber os eventos gerados por outras partes do programa.  Estes eventos são todos
 * subclasses de IgrafEvent e são disseminados polimorficamente... cabe ao objeto que
 * recebe filtrar os eventos que são de seu interesse tentando fazer a conversão 
 * para o tipo específico que lhe interessa dentro de um ou mais blocos try/catch.
 *
 */
public interface BroadcastEventListener {

 /**
  * Recebe um objeto IgrafEvent retransmitido pelo broadcaster.   Este evento foi
  * gerado por algum módulo do iGraf, enviado para o disseminador para que o enviasse
  * a todos os módulos do programa; a fonte do evento pode ser o mesmo módulo que recebe o evento.
  * @param ie
  */
 public void disseminarEventoInternamente (CommunicationEvent ie);
 
 
 /**
  * Recebe um objeto IgrafEvent gerado por algum componente interno do módulo.   Este evento
  * é enviado para o disseminador para que o retransmita a todos os módulos do programa; 
  * @param ie
  */
 public void disseminarEventoExternamente (CommunicationEvent ie);

 }
