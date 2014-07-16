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
 * @description It implements the menu controller to "Poligons"
 * 
 * @see igraf.moduloCentral.controle.menu.IgrafMenuController - extends this 'abstract class'
 * @see igraf/moduloCentral/visao/menu/MenuPoligono.java
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
import igraf.moduloCentral.eventos.menu.IgrafMenuPoligonoEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuAnimacao;
import igraf.moduloCentral.visao.menu.MenuPoligono;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class IgrafMenuPoligonoController extends IgrafMenuController {

 private IgrafMenuPoligonoEvent ie;
 private MenuPoligono mp;

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuPoligonoController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents, index);
  mp = new MenuPoligono(this, index);
  }


 public void enviarEventoAcao (String cmd) {
  ie = new IgrafMenuPoligonoEvent(this);
  ie.setCommand(cmd); // atribui o rótulo do menu ao comando
  enviarEvento(ie);
  }

 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie instanceof DisableMenuEvent) {
   removeDisabledMenuItens(ie); return;
   }

  if (ie instanceof ChangeLanguageEvent) {
   mp.updateLabels(); return;
   }
  }


 private void removeDisabledMenuItens (CommunicationEvent ce) {
  DisableMenuEvent dme = (DisableMenuEvent)ce;
  int [] listaMenu = dme.getDisableList();
  int sizeListaMenu = listaMenu==null ? 0 : listaMenu.length;
  int i = 0;

  // Attention: menu items in desable list must be in increasing order!
  while (i<sizeListaMenu && listaMenu[i] < 499) { // 499 is the last menu item from primary 'Poligon'
   switch (listaMenu[i++]){
    // let 'IgrafMenu.removeMenuItem(String)' apply 'ResourceReader.msg(...)'
    case MenuPoligono.POINT:   mp.removeMenuItem("mepPonto");     break;
    case MenuPoligono.SEGM:    mp.removeMenuItem("mepSegm");      break;
    case MenuPoligono.TRIAN:   mp.removeMenuItem("mepTri");       break;
    case MenuPoligono.RECT:    mp.removeMenuItem("mepRet");       break;
    case MenuPoligono.REG_POL: mp.removeMenuItem("mepPolReg");    break;
    case MenuPoligono.ANY_POL: mp.removeMenuItem("mepPolQqr");    break;
    case MenuPoligono.S_RECT:  mp.removeMenuItem("mepRetEsp");    break;
    case MenuPoligono.S_POL:   mp.removeMenuItem("mepPolEsp");    break;
    case MenuPoligono.PAINT:   mp.removeMenuItem("mepPintaPoli"); break;
    }
   }
  }

 public IgrafMenu getMenu () {
  return mp;
  }

 }
