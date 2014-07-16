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
 * @description Event from change in configuration menus.
 * 
 * @see igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java: processChangesToMenus() register any menu item change
 *      (old solution: cc.enviarEvento(new DisableMenuEvent(this, getDisableList()));)
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloExercicio.visao.menuSelector;

import java.util.StringTokenizer;

import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;
import difusor.evento.CommunicationEvent;

public class DisableMenuEvent extends CommunicationEvent implements EventoRegistravel, ModuloCentralDisseminavelEvent {

 public DisableMenuEvent (Object source, String arg) {
  super(source, arg);
  }


 public String getDescription () {
  return "";
  }


 public int getCodigoAcao () {
  return Acao.desabilitaMenu;
  }

 public String getArgumento () {
  return getCommand();
  }

 // Each menu controller call this method (with the same line '{609,...}'
 // igraf.moduloCentral.controle.menu.IgrafMenu(*)Controller.removeDisabledMenuItens(IgrafMenuCalculoController.java:86)
 // igraf.moduloCentral.controle.menu.IgrafMenu(*)Controller.tratarEventoRecebido(IgrafMenuCalculoController.java:61)
static int count=0;
 public int [] getDisableList () {
  //T System.out.println("src/igraf/moduloExercicio/visao/menuSelector/DisableMenuEvent.java: getDisableList(): " + getCommand());
  //T if (count++==1) try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  StringTokenizer st = new StringTokenizer(getCommand(), ",");
  int [] lista = new int[st.countTokens()];
  
  for (int i = 0; i < lista.length; i++) {
   lista[i] = Integer.parseInt(st.nextToken().trim());
   }  
  return lista;
  }

 }
