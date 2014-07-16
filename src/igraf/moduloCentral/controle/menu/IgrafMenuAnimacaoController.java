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
 * @see igraf/moduloCentral/visao/menu/MenuAnimacao.java
 * @see igraf/moduloCentral/controle/desenho/DesenhoAnimacaoController.java: class that start or stop animation in igraf.moduloCentral.visao.AreaDesenho
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
import igraf.moduloCentral.eventos.menu.IgrafMenuAnimacaoEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuAnimacao;
import igraf.moduloCentral.visao.menu.MenuCalculo;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;

public class IgrafMenuAnimacaoController extends IgrafMenuController {

 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuAnimacaoController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents, index);
  ma = new MenuAnimacao(this, index);
  }

 IgrafMenuAnimacaoEvent ie;
 MenuAnimacao ma;

 // From: igraf.moduloCentral.controle.menu.IgrafMenuAnimacaoControlle.enviarEventoAcao(String)
 public void enviarEventoAcao (String comando) {
  ie = new IgrafMenuAnimacaoEvent(this);
  ie.setCommand(comando);
  enviarEvento(ie);
  }


 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie instanceof DisableMenuEvent) {
   removeDisabledMenuItens(ie); return;
   }
  
  if (ie instanceof ChangeLanguageEvent) {
   ma.updateLabels(); return;
   }
  
  if (ie instanceof IgrafTabUpdateEvent) try {
   IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)ie;
   if (iue.getCommand().equals(IgrafTabUpdateEvent.ANIMATION_LIST_CHANGED))
    ma.setEnableAllMenuItem(iue.getAnimationList().size()>0);   
   } catch (Exception e) {
    // Here from:  difusor.CommunicationFacade.disseminarEventoInternamente(CommunicationFacade.java:129)
    System.err.println("Error: 'igraf/moduloCentral/controle/menu/IgrafMenuAnimacaoController.java': " + e.toString());
    System.err.println("From : 'idifusor.CommunicationFacade.disseminarEventoInternamente(CommunicationFacade.java:129)'");
    // e.printStackTrace();
    }
  }


 private void removeDisabledMenuItens (CommunicationEvent ce) {
  DisableMenuEvent dme = (DisableMenuEvent)ce;
  int [] listaMenu = dme.getDisableList();
  int sizeListaMenu = listaMenu==null ? 0 : listaMenu.length;
  int i = 0;

  // Attention: menu items in desable list must be in increasing order!
  while (i<sizeListaMenu && listaMenu[i] < 399) { // 399 is the last menu item from primary 'Animation'
   switch (listaMenu[i++]) {
    // let 'IgrafMenu.removeMenuItem(String)' apply 'ResourceReader.msg(...)'
    case MenuAnimacao.STOP_ANIM:  ma.removeMenuItem("maParaAnima");      break;
    case MenuAnimacao.SHOW_CONTR: ma.removeMenuItem("maMostraControle"); break;
    case MenuAnimacao.SHOW_SLID:  ma.removeMenuItem("maMostraCDesl");    break;
    case MenuAnimacao.FUNC_TRACK: ma.removeMenuItem("maRastro");         break;
    case MenuAnimacao.INC_VEL:    ma.removeMenuItem("maVelAmplia");      break;
    case MenuAnimacao.DEC_VEL:    ma.removeMenuItem("maVelDiminui");     break;
    }
   }
  }
 
 public void setControlledObject (Object obj) {
  ma = (MenuAnimacao)obj;
  }

 public IgrafMenu getMenu () {
  return ma;
  }

 }
