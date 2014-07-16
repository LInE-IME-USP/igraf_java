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
 * @see igraf/moduloCentral/visao/menu/MenuAjuda.java
 * @see igraf/moduloAjuda/eventos/HelpEvent.java: extends difusor.evento.CommunicationEvent implements igraf.moduloAjuda.eventos.ModuloAjudaDisseminavel
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

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;
import igraf.IGraf;
import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloAjuda.eventos.HelpEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuAjuda;
import igraf.moduloCentral.visao.menu.MenuAnimacao;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;

public class IgrafMenuAjudaController extends IgrafMenuController {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloCentral/controle/menu/IgrafMenuAjudaController.java";

 private MenuAjuda menuAjuda;
 private HelpEvent help;

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuAjudaController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents, index);
  menuAjuda = new MenuAjuda(this, index);
  }


 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie instanceof DisableMenuEvent) {
   removeDisabledMenuItens(ie);
   return;
   }
  else
  if (ie instanceof ChangeLanguageEvent) {
   menuAjuda.updateLabels();
   }
  }


 // Remove an option from menu item
 private void removeDisabledMenuItens (CommunicationEvent ce) {
  DisableMenuEvent dme = (DisableMenuEvent)ce;
  int [] listaMenu = dme.getDisableList();
  int sizeListaMenu = listaMenu==null ? 0 : listaMenu.length;
  int i = 0;
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "removeDisabledMenuItens " + ce.getClass().getName());

  // Attention: menu items in desable list must be in increasing order!
  while (i<sizeListaMenu && listaMenu[i] < 799) { // 799 is the last menu item from primary 'Help'
   switch (listaMenu[i++]) {
    // let 'IgrafMenu.removeMenuItem(String)' apply 'ResourceReader.msg(...)'
    case MenuAjuda.CONTENT:  menuAjuda.removeMenuItem("majCont");    break; // Content
    case MenuAjuda.SEARCH:   menuAjuda.removeMenuItem("majBusca");   break; // Search
    case MenuAjuda.SHORTCUT: menuAjuda.removeMenuItem("majAtalhos"); break; // Shortcuts
    case MenuAjuda.ABOUT:    menuAjuda.removeMenuItem("majSobre");   break; // About
    }
   }
  }


 // From: igraf.moduloSuperior.visao.PainelBotoes.actionPerformed()
 // igraf/moduloCentral/controle/menu/IgrafMenuAjudaController.java: enviarEventoAcao(Conteúdo): igraf.moduloAjuda.eventos.HelpEvent
 public void enviarEventoAcao (String comando) {
  help = new HelpEvent(this, comando);
  //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + "enviarEventoAcao(" + comando + "): " + help.getClass().getName());
  // try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }

  enviarEvento(help); // in difusor/controle/CommunicationController.java
  // src/difusor/controle/CommunicationController.java: public void enviarEvento (CommunicationEvent e): CommunicationFacade modulo; modulo.filtrarEventoSaida(e);
  // src/igraf/moduloAjuda/ModuloAjuda.java: extends CommunicationFacade
  // - public void filtrarEventoEntrada (CommunicationEvent ie): if (ie instanceof ModuloAjudaDisseminavel) disseminarEventoInternamente(ie);
  // src/difusor/CommunicationFacade.java
  // - public void disseminarEventoInternamente (CommunicationEvent communicationEvent): communicationController.tratarEventoRecebido(communicationEvent);
  // src/igraf/moduloAjuda/ModuloAjuda.java: extends CommunicationFacade
  // - public void filtrarEventoEntrada (CommunicationEvent ie): if (ie instanceof ModuloAjudaDisseminavel) disseminarEventoInternamente(ie);
  // - public void filtrarEventoSaida (CommunicationEvent ie) {}

  }

 public IgrafMenu getMenu () {
  return menuAjuda;
  }

 }
