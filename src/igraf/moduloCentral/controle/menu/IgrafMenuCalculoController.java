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
 * @description 
 * 
 * @see igraf/moduloCentral/visao/menu/MenuCalculo.java
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

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuCalculo;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class IgrafMenuCalculoController extends IgrafMenuController {

 IgrafMenuCalculoEvent ie; 
 MenuCalculo mc;

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuCalculoController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents, index);
  mc = new MenuCalculo(this, index);
  }
 
 public void enviarEventoAcao (String comando) {
  enviarEvento(new IgrafMenuCalculoEvent(this, comando));
  }
 
 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie instanceof DisableMenuEvent) {
   removeDisabledMenuItens(ie); // remove menu items (define from 'Exercise ! Configure menu')
   return;
   }

  if (ie instanceof ChangeLanguageEvent) {
   mc.updateLabels();
   return;
   }

  try {
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)ie;
   if (iue.getCommand().equals(IgrafTabUpdateEvent.FUNCTION_LIST_CHANGED))
    mc.setEnableAllMenuItem(iue.getFunctionList().size()>0);
  } catch (Exception e) {
    //L System.err.println("Error: 'igraf/moduloCentral/controle/menu/IgrafMenuCalculoController.java': " + e.toString());
    // e.printStackTrace();
    }

  if (ie.getCommand().equals(ResourceReader.msg("msgMenuGrfNovaSes"))) {
   mc.setEnableAllMenuItem(false);
   }
  }

 private void removeDisabledMenuItens (CommunicationEvent ce) {
  DisableMenuEvent dme = (DisableMenuEvent)ce;
  int [] listaMenu = dme.getDisableList();
  int sizeListaMenu = listaMenu==null ? 0 : listaMenu.length;
  int i = 0;

  // Attention: menu items in desable list must be in increasing order!
  while (i<sizeListaMenu && listaMenu[i] < 299) { // 299 is the last menu item from primary 'Calculus'
   switch (listaMenu[i++]) {
    // let 'IgrafMenu.removeMenuItem(String)' apply 'ResourceReader.msg(...)'
    case MenuCalculo.DERIVATIVE: mc.removeMenuItem("mcDerVer"); break;
    case MenuCalculo.TANGENT:    mc.removeMenuItem("mcTgVer"); break;
    case MenuCalculo.IND_INTEG:  mc.removeMenuItem("mcIIVer"); break;
    case MenuCalculo.DEF_INTEG:  mc.removeMenuItem("mcIICalc"); break;
    }
   }
  }

 public IgrafMenu getMenu () {
  return mc;
  }

 }
