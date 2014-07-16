
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

import java.util.EventObject;

/**
 * Superclasse de todos os eventos do modelo de broadcasting que
 * têm por finalidade comunicar alterações em partes de uma aplicação. 
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
  * Recebe a string cmd que é um comando, geralmente do menu, para a realização
  * de uma ação pelo sistema.   Quando um evento não define explicitamente seu
  * comando, getCommand retorna uma string vazia, não nula.
  * @param cmd
  */
 public void setCommand (String cmd) {
  command = cmd;
  }
 
 /**
  * Retorna o comando que este evento transportou ao objeto ouvinte.
  * Se evento não definiu, na sua criação,  explicitamente seu
  * comando, getCommand retorna uma string vazia, não nula.
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
 

 // esse valor de x é necessário devido aos possíveis ajustes 
 // feitos pelo usuário na largura da janela de exibição do igraf
 public int getX () {
  return x;
  }
 
 /**
  * Método que descreve a finalidade de um evento.  Deve ser implementado em todos
  * os eventos que subclassificam CommunicationEvent para efeito de documentação.<br>
  * Espera-se poder obter nesta documentação, pelo menos, três informações:<br>
  * 1. Finalidade e motivação da criação do evento<br>
  * 2. Local de origem do evento (em que ele classe foi gerado)<br>
  * 3. Possíveis locais  aos quais se destina este evento<br>
  * 
  * @return String que descreve o evento
  */
 public abstract String getDescription ();

 }
