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
 * @see igraf/moduloCentral/visao/menu/MenuEdicao.java
 * @see igraf/moduloCentral/eventos/menu/IgrafMenuEdicaoEvent.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloCentral.controle.menu;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuEdicaoEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuEdicao;
import igraf.moduloCentral.visao.menu.MenuPoligono;
import igraf.moduloCentral.visao.menu.SubMenuIdioma;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class IgrafMenuEdicaoController extends IgrafMenuController {

 private IgrafMenuEdicaoEvent ie;
 private MenuEdicao me;

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuEdicaoController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents, index);
  me = new MenuEdicao(this, index);
  }

 public void enviarEventoAcao (String comando) {
  ie =  new IgrafMenuEdicaoEvent(this);
  ie.setCommand(comando);  
  enviarEvento(ie);  
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  //System.out.println("src/igraf/moduloCentral/controle/menu/IgrafMenuEdicaoController.java: ********** " + ie.getClass());
  //if (ie instanceof IgrafMenuEdicaoEvent) try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  if (ie instanceof DisableMenuEvent) {
   removeDisabledMenuItens(ie); return;
   }
  
  if (ie instanceof ChangeLanguageEvent) {
   // MenuEdicao me
   //T System.out.println("src/igraf/moduloCentral/controle/menu/IgrafMenuEdicaoController.java: ++++++++++ " + me.getClass());
   //try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

   me.updateLabels(); 
   ((SubMenuIdioma)getMenuIdioma()).updateLabels();
   return;
   }
  
  try {
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)ie;
   me.setEnabledMenuItem(ResourceReader.msg("madTextoEditar"), iue.temTextoVisivel());
   me.setEnabledMenuItem(ResourceReader.msg("madTextoRemover"), iue.temTextoVisivel());
   } catch (Exception e) {
    //L System.err.println("Error: 'igraf/moduloCentral/controle/menu/IgrafMenuEdicaoController.java': " + e.toString());
    }
  }


 private void removeDisabledMenuItens (CommunicationEvent ce) {
  DisableMenuEvent dme = (DisableMenuEvent)ce;
  int [] listaMenu = dme.getDisableList();
  int sizeListaMenu = listaMenu==null ? 0 : listaMenu.length;
  int i = 0;

  // Attention: menu items in desable list must be in increasing order!
  while (i<sizeListaMenu && listaMenu[i] < 599) { // 599 is the last menu item from primary 'Edition'
   switch (listaMenu[i++]) {
    // let 'IgrafMenu.removeMenuItem(String)' apply 'ResourceReader.msg(...)'
    case MenuEdicao.ZOOM_DEFAULT: me.removeMenuItem("madZoomPadrao"); break;
    case MenuEdicao.INCR_ZOOM:    me.removeMenuItem("madZoomAmpliar"); break;
    case MenuEdicao.DECR_ZOOM:    me.removeMenuItem("madZoomDiminuir"); break;
    case MenuEdicao.TEXT_INSERT:  me.removeMenuItem("madTextoInserir"); break;
    case MenuEdicao.TEXT_EDIT:    me.removeMenuItem("madTextoEditar"); break;
    case MenuEdicao.TEXT_REMOVE:  me.removeMenuItem("madTextoRemover"); break;
    case MenuEdicao.TOGGLE_AXES:  me.removeMenuItem("madEixosRemover"); break;    
    case MenuEdicao.TOGGLE_SCALE: me.removeMenuItem("madEscalaRemover"); break;
    case MenuEdicao.TOGGLE_GRID:  me.removeMenuItem("madGradeRemover"); break;
    case MenuEdicao.LANGUAGE:     me.removeMenuItem("madLang"); break;
    }
   }
  }

 public IgrafMenu getMenu () {
  return me;
  }

 public IgrafMenu getMenuIdioma () {
  return me.getMenuIdioma();
  }

 }
