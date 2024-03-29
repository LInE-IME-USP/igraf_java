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
 * @description Classe controladora que define a interface que deve ser implementada por todas as
 * classes que fazem parte de um m�dulo, necessitam que seus eventos sejam disseminados
 * pelo sistema e/ou precisam receber eventos do meio externo ao m�dulo.
 *  // Distribute the event 'e' to a correct module (listener of the events)
 *  //  0:igraf.moduloArquivo.ModuloArquivo
 *  //  1:igraf.moduloCentral.ModuloCentral
 *  //  2:igraf.moduloExercicio.ModuloExercicio
 *  //  3:igraf.moduloInferior.ModuloInferior
 *  //  4:igraf.moduloJanelasAuxiliares.ModuloJanelasAuxiliares
 *  //  5:igraf.moduloSuperior.ModuloSuperior
 *  //  6:igraf.moduloAjuda.ModuloAjuda
 *  //  7:igraf.IGrafController
 * 
 * @see igraf/moduloExercicio/controle/JanelaExercicioController.java: it 'extends CommunicationController implements ItemListener, ActionListener'
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package difusor.controle;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;


public abstract class CommunicationController {

 private CommunicationFacade modulo;

 /**
  * Construtor que recebe o m�dulo que o cont�m, mais um valor booleano (getEvents) que indica o modo de
  * intera��o no qual este controlador deve operar. Por padr�o, todo controlador � um gerador
  * de eventos. Para que ele seja tamb�m um receptor de eventos, getEvents deve ter valor == true.
  * 
  * @param modulo
  * @param eventMode
  */
 public CommunicationController (CommunicationFacade module, boolean getEvents) {
  //T System.out.println("\nsrc/difusor/controle/CommunicationController.java: " + module);
  this.modulo = module;  
  if (getEvents) modulo.addInternEventListener(this);  
  }

 public CommunicationController (CommunicationFacade module) {
  this(module, false);
  }

 public CommunicationController () { }
 
 /**
  * M�todo que deve ser implementado pelas subclasses para fazer o tratamento
  * dos eventos recebidos que, porventura, sejam de seu interesse.
  * @param e
  */
 public abstract void tratarEventoRecebido (CommunicationEvent e);

 public CommunicationFacade getModulo () {
  return modulo;
  }
 

 /**
  * Recebe um evento gerado pelo objeto controlado por este controlador e
  * o envia para o ambiente externo para que seja disseminado pelo sistema. 
  * Esse m�todo permite, por padr�o, que um controlador envie eventos
  * por isso que todo controlador deve ser associado a um m�dulo (igraf.moduloArquivo.ModuloArquivo, ...).
  * @param e
  */
 public void enviarEvento (CommunicationEvent e) { 
  modulo.filtrarEventoSaida(e);
  }

 }
