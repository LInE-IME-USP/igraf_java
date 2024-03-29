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
 * @description Abstract class to be implemented to any menu controller
 * 
 * @see 
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloCentral.controle.menu;

import igraf.moduloCentral.visao.menu.IgrafMenu;
import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

public abstract class IgrafMenuController extends CommunicationController {

 private IgrafMenu im;
 private String [] listaOpcoes;

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents);
  }


 public void setControlledObject (Object obj) {
  IgrafMenu im = (IgrafMenu)obj;
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {}
 
 /**
  * Recebe uma string que � um comando, o r�tulo do item de menu, indicando
  * que a��o deve ser realizada em algum ponto do sistema.  O valor desta
  * string deve ser usado para definir que subtipo de IgrafEvent deve ser
  * enviado ao sistema de difus�o de eventos e, consequentemente, determinar
  * quais objetos ouvintes ser�o afetados pela recep��o deste evento.
  * @param comando
  */
 public abstract void enviarEventoAcao (String comando);

 public abstract IgrafMenu getMenu ();

 }
