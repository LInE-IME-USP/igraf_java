
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

import java.util.EventObject;

/**
 * Superclasse de todos os eventos do modelo de broadcasting que
 * t�m por finalidade comunicar altera��es em partes de uma aplica��o. 
 * @author user
 *
 */
public abstract class CommunicationEvent extends EventObject {

 private static int count = 0;
 public final int ID = count++;

 private String command = "";
 protected int x = 0;
 
 public CommunicationEvent (Object source) {
  super(source);
  }
 
 public CommunicationEvent (Object source, String command) {
  super(source);
  setCommand(command);
  }
 
 /**
  * Define o comando que este evento deve transportar ao objeto ouvinte.
  * Recebe a string cmd que � um comando, geralmente do menu, para a realiza��o
  * de uma a��o pelo sistema.   Quando um evento n�o define explicitamente seu
  * comando, getCommand retorna uma string vazia, n�o nula.
  * @param cmd
  */
 public void setCommand (String cmd) {
  command = cmd;
  }
 
 /**
  * Retorna o comando que este evento transportou ao objeto ouvinte.
  * Se evento n�o definiu, na sua cria��o,  explicitamente seu
  * comando, getCommand retorna uma string vazia, n�o nula.
  */
 public String getCommand () {
  return command;
  }
 
 protected String objetivo (String s) {
  return "Event launched by the class " + getSource() + " with the objective of " + s;
  }

 protected int eventType; 
 public static final int MOUSE_CLICKED = 1,
                         MOUSE_ENTERED = 2,
                         MOUSE_EXITED  = 3; 
  
 public int getEventType() {
  return eventType;
  }
 

 // esse valor de x � necess�rio devido aos poss�veis ajustes 
 // feitos pelo usu�rio na largura da janela de exibi��o do igraf
 public int getX () {
  return x;
  }
 
 /**
  * M�todo que descreve a finalidade de um evento.  Deve ser implementado em todos
  * os eventos que subclassificam CommunicationEvent para efeito de documenta��o.<br>
  * Espera-se poder obter nesta documenta��o, pelo menos, tr�s informa��es:<br>
  * 1. Finalidade e motiva��o da cria��o do evento<br>
  * 2. Local de origem do evento (em que ele classe foi gerado)<br>
  * 3. Poss�veis locais  aos quais se destina este evento<br>
  * 
  * @return String que descreve o evento
  */
 public abstract String getDescription ();

 }
