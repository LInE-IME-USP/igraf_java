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
 * @description Method to distribute event treatment
 * Classe que define as opera��es b�sicas que todo m�dulo do iGraf deve implementar.   Estas opera��es
 * tratam do envio de eventos para o ambiente externo ao m�dulo para que sejam disseminados pelo sistema
 * e da recep��o de eventos gerados em outras parte do programa.
 * 
 * @see ...
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package difusor;

import java.util.Iterator;
import java.util.Vector;

import igraf.IGraf;
import igraf.moduloArquivo.Arquivo; // error messages

import difusor.controle.CommunicationController;
import difusor.evento.BroadcastEventListener;
import difusor.evento.CommunicationEvent;
import difusor.evento.InternComponentEventListener;
import difusor.evento.ModuleEventListener;

public abstract class CommunicationFacade implements BroadcastEventListener, InternComponentEventListener {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "difusor/CommunicationFacade.java";
 // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }  }


 // private static int contID = 0;
 // private final int ID = contID++;
 private String name = ""; // name to identify the class that origin this communication

 { // this block ensure that all classes that extends 'CommunicationFacade' when is initialized run this code
  name = this.getClass().getName();
  }
   

 // Object list in "broadcasters"... in this implementation it contains 'difusor.Broadcaster'
 private Vector listaOuvintesExternos = new Vector();

 /**
  * Insere o objeto i na lista de ouvintes de eventos gerados no m�dulo atual do iGraf.
  * Na implementa��o atual, o �nico objeto que ouve eventos de m�dulos � o broadcaster.
  * @param ModuleEventListener modEventListener
  */
 public void addBroadcaster (ModuleEventListener modEventListener) {
  listaOuvintesExternos.add(modEventListener);
  }
 
 /**
  * Remove o objeto i, ouvinte de eventos gerados no m�dulo atual.
  * @param modEventListener
  */
 public void removeBroadcaster (ModuleEventListener modEventListener) {
  listaOuvintesExternos.remove(modEventListener);
  }


 /**
  * M�todo que recebe um IgrafEvent enviado por algum objeto interno ao m�dulo e
  * o envia ao objeto broadcaster para que fa�a a sua distribui��o a todos os 
  * m�dulos do iGraf. 
  * @param ie
  */
 public void disseminarEventoExternamente (CommunicationEvent ie) {

  if (IGraf.DEBUG_EVENT_EXT) System.out.println("src/difusor/CommunicationFacade.java: disseminarEventoExternamente: " + ie.getClass().getName() + " -  " + ie.getCommand() + "");
  //T " (ID="+ID+"): CommunicationEvent);

  //TODO: ver este laco, hoje existe apenas um 'ouvidor externo' o 'difusor.Broadcaster' !!!
  // difusor/Broadcaster.java implements 'difusor/evento/ModuleEventListener.java'
  // . void disseminarEvento (CommunicationEvent e): broadcastEvent(e);
  for (int ii_0=0; ii_0<listaOuvintesExternos.size(); ii_0++) {
   ModuleEventListener moduleEventListener = (ModuleEventListener) listaOuvintesExternos.elementAt(ii_0);
   if (moduleEventListener!=null) { //SECURITY: it is not supposed to be null...
    if (IGraf.DEBUG_EVENT_EXT) System.out.println(" - " + ii_0 + ": " + moduleEventListener.getClass().getName() + " CommunicationEvent=" + ie.getClass().getName()); // difusor.evento.ModuleEventListener

    // moduleEventListener.disseminarEvento(ie); // in 'difusor/Broadcaster.java: public void disseminarEvento(CommunicationEvent e): broadcastEvent(e);
    //TODO: if 'Vector listaOuvintesExternos' has other than 'Broadcaster' cut the 2 lines bellow and uncomment 'moduleEventListener.disseminarEvento(ie);'
    Broadcaster broadcaster = (Broadcaster) moduleEventListener;
    broadcaster.broadcastEvent(ie);
    }
   else { // Do not even try, it is empty!
    System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": disseminarEventoExternamente(CommunicationEvent): ModuleEventListener moduleEventListener=" + moduleEventListener);
    }
   // Broadcaster.java: novamente chama 'CommunicationFacade' com
   //  + Vector listaModulos = new Vector();
   //  . public void disseminarEvento (CommunicationEvent e):
   //    broadcastEvent(e);
   //  . private void broadcastEvent (CommunicationEvent e):
   //    for (Iterator iter = listaModulos.iterator(); iter.hasNext();) {
   //      CommunicationFacade modulo = (CommunicationFacade) iter.next();
   //      modulo.filtrarEventoEntrada(e); // CommunicationFacade.java: abstract void filtrarEventoEntrada(CommunicationEvent ie)
   //      }
   }
  }


 // m�todos que implementam o tratamento de entrada de eventos

 // Lista de ouvintes gen�ricos, internos ao m�dulo
 protected Vector listaOuvintesInternos = new Vector();
 
 //Tstatic int count=0;

 /** 
  * Insere o objeto communicationController, interno ao m�dulo, na lista de ouvintes de eventos vindos do ambiente externo.
  * @param CommunicationController communicationController
  */
 public void addInternEventListener (CommunicationController communicationController) {

  //T if (communicationController instanceof igraf.moduloCentral.controle.AreaDesenhoController) {
  //T System.out.println("\n"+IGCLASSPATH+": addInternEventListener(AreaDesenhoController): ADDC="+((igraf.moduloCentral.controle.AreaDesenhoController)communicationController).ID+" in "+this.getClass().getName());
  //T if (count++==1) try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  //T }
  if (communicationController==null || listaOuvintesInternos.contains(communicationController)) {
   //SECURITY: keep this code to ensure that a listener will never the inserted twice (this would proccess a difficult to track error)

   // Register error in GRF: "[ CommunicationFacade :: <this message> ], "
   String strErr = "addInternEventListener(CommunicationController): CommunicationController=" +
          (communicationController==null ? "NULL (it is not supposed)!!" : communicationController.getClass().getName() + " it is already in 'listaOuvintesInternos'!!");
   if (IGraf.IS_DEBUG) {
     System.err.println(IGCLASSPATH + ": DEBUG!! Attention: in '" + this.getClass().getName() + "." + strErr);
     System.out.println(" " + strErr);
     //T IGraf.launchStackTrace();
     }
   Arquivo.addLogError("CommunicationFacade.java", strErr);

   return; //SECURITY: keep this code to ensure that a listener will never the inserted twice (this would proccess a difficult to track error)

   } // if (communicationController==null || listaOuvintesInternos.contains(communicationController))

  listaOuvintesInternos.add(communicationController); // add to listeners 'Vector listaOuvintesInternos'

  }


 /** 
  * Remove o objeto communicationController da lista de ouvintes de eventos vindos do ambiente externo.
  * igraf/moduloCentral/visao/TabbedViewer.java: changeControlTab(int index)
  * @param communicationController
  */
 public void removeInternEventListener (CommunicationController communicationController) {

  if (communicationController==null || !listaOuvintesInternos.contains(communicationController)) {
   //SECURITY: keep this code to ensure that a listener will never the inserted twice (this would proccess a difficult to track error)

   // Register error in GRF: "[ CommunicationFacade :: <this message> ], "
   String strErr = "removeInternEventListener(CommunicationController): CommunicationController=";
   if (communicationController==null) strErr += "NULL (it is not supposed)!!";
   else strErr += communicationController.getClass().getName() + " is NOT in 'Vector listaOuvintesInternos'!!";

   if (IGraf.IS_DEBUG) {
     System.err.println(IGCLASSPATH + ": DEBUG!! Attention: in '" + this.getClass().getName() + "." + strErr);
     System.out.println(" " + strErr);
     }
   Arquivo.addLogError("CommunicationFacade.java", strErr);

   return; //SECURITY: keep this code to ensure that a listener will never the inserted twice (this would proccess a difficult to track error)

   } // if (communicationController==null || listaOuvintesInternos.contains(communicationController))

  listaOuvintesInternos.remove(communicationController); // remove from listeners 'Vector listaOuvintesInternos'

  }


 /**
  * M�todo que recebe um objeto IgrafEvent enviado pelo broadcaster e o dissemina no
  * ambiente interno do m�dulo para que possa ser tratado pelos objetos interessados.
  * Para que um objeto possa receber tais eventos dever� herdar da classe
  * IgrafModuleInternController.
  * List of controllers:
  *  0: igraf.moduloCentral.controle.PainelCentralController
  *  1: igraf.moduloCentral.controle.AreaDesenhoController
  *  2: igraf.moduloCentral.controle.AreaDesenhoController
  *  3: igraf.moduloCentral.controle.menu.IgrafMenuAnimacaoController
  *  4: igraf.moduloCentral.controle.menu.IgrafMenuAjudaController
  *  5: igraf.moduloCentral.controle.menu.IgrafMenuCalculoController
  *  6: igraf.moduloCentral.controle.menu.IgrafMenuEdicaoController
  *  7: igraf.moduloCentral.controle.menu.IgrafMenuExercicioController
  *  8: igraf.moduloCentral.controle.menu.IgrafMenuGraficoController
  *  9: igraf.moduloCentral.controle.menu.IgrafMenuPoligonoController
  */
 public void disseminarEventoInternamente (CommunicationEvent communicationEvent) {
  int ii_0 = -1;
  CommunicationController communicationController = null;

  if (IGraf.DEBUG_EVENT_INT) System.out.println(IGraf.DEBUG_EVENT_INT_INDENT + "src/difusor/CommunicationFacade.java: disseminarEventoInternamente: " + communicationEvent.getClass().getName() + "");

  try {
   for (ii_0=0; ii_0<listaOuvintesInternos.size(); ii_0++) {
    communicationController = (CommunicationController) listaOuvintesInternos.get(ii_0);
    if (communicationController==null) { //SECURITY: it is not supposed to be null...
       System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": disseminarEventoInternamente(CommunicationEvent): ModuleEventListener communicationController=" + communicationController);
       continue; // Do not even try, it is empty!
       }

    if (IGraf.DEBUG_EVENT_INT) System.out.println(IGraf.DEBUG_EVENT_INT_INDENT + " - " + ii_0 + ": " + communicationEvent.getClass().getName() + " CommunicationController=" + communicationController.getClass().getName());

    communicationController.tratarEventoRecebido(communicationEvent);

    }
   } catch (Exception e) {
    System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": disseminarEventoInternamente(CommunicationEvent): ii_0=" + ii_0 + ", communicationController=" + communicationController);
    e.printStackTrace();
    }

  } // public void disseminarEventoInternamente(CommunicationEvent communicationEvent)


 /**
  * M�todo que recebe um IgrafEvent e verifica se ele pode entrar no m�dulo.
  * Na implementa��o deste m�todo, deve-se verificar, por tentativa de convers�o (try/catch), se o evento recebido � aceit�vel pelo m�dulo.
  * Sabendo que um evento pode ser aceito, ele deve ser passado como argumento em uma chamada a disseminarEventoInternamente(IgrafEvent).
  * Tal abordagem tem dois objetivos: evitar a propaga��o desordenada de eventos 
  * pelo sistema e for�ar quem desenvolver novos m�dulos para o iGraf a pensar
  * sobre a natureza dos eventos que seu m�dulo pode receber ou n�o.
  * 
  * @param communicationEvent
  */
 public abstract void filtrarEventoEntrada (CommunicationEvent communicationEvent);
 
 
 /**
  * M�todo que recebe um IgrafEvent e verifica se ele pode ou n�o sair do m�dulo.
  * Na implementa��o deste m�todo, deve-se verificar, por tentativa de convers�o (try/catch), se o evento recebido um tipo aceit�vel pelo m�dulo.
  * Sabendo que um evento pode ser aceito, ele deve ser passado como argumento em uma chamada a disseminarEventoInternamente(IgrafEvent).
  * Tal abordagem tem dois objetivos: evitar a propaga��o desordenada de eventos pelo sistema e for�ar quem desenvolver novos m�dulos para o iGraf a pensar
  * sobre a natureza dos eventos que seu m�dulo pode receber ou n�o.
  * 
  * @param communicationEvent
  */ 
 public abstract void filtrarEventoSaida (CommunicationEvent communicationEvent);

 }
