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
 * @description Starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
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

package igraf.moduloCentral;

import java.awt.Component;
import java.util.HashMap;

import igraf.moduloCentral.controle.PainelCentralController;
import igraf.moduloCentral.controle.menu.IgrafMenuController;
import igraf.moduloCentral.controle.menu.IgrafMenuAjudaController;
import igraf.moduloCentral.controle.menu.IgrafMenuAnimacaoController;
import igraf.moduloCentral.controle.menu.IgrafMenuCalculoController;
import igraf.moduloCentral.controle.menu.IgrafMenuEdicaoController;
import igraf.moduloCentral.controle.menu.IgrafMenuExercicioController;
import igraf.moduloCentral.controle.menu.IgrafMenuGraficoController;
import igraf.moduloCentral.controle.menu.IgrafMenuPoligonoController;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.visao.PainelCentral;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloInferior.ModuloInferior;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;


public class ModuloCentral extends CommunicationFacade {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/igraf/moduloCentral/ModuloCentral.java";
 // System.err.println(IGraf.debugErrorMsg(IGCLASSPATH)+"Erro: " ...);
 // IGraf.launchStackTrace()

 public PainelCentralController pcc = new PainelCentralController(this, true);
 private PainelCentral painelCentral;

 private ModuloInferior moduloInferior; // to allow any one above ModuloCentral to access "status bar" 'igraf/moduloInferior/visao/InfoPane.java'

 //TODO: implement index to get menu item easily
 // In this case: take care of this classes and their index, it is used in
 // - ./igraf/moduloCentral/controle/menu/IgrafMenu(*).java
 // - ./igraf/moduloCentral/visao/menu/SubMenuIdioma.java
 // - ./igraf/moduloCentral/visao/menu/Menu(*).java
 // - ./igraf/moduloCentral/controle/menu/IgrafMenu(*)Controller
 private IgrafMenuGraficoController   imgc = new IgrafMenuGraficoController(this, true, 0);   // 0
 private IgrafMenuCalculoController   imcc = new IgrafMenuCalculoController(this, true, 1);   // 1
 private IgrafMenuAnimacaoController  imac = new IgrafMenuAnimacaoController(this, true, 2);  // 2
 private IgrafMenuEdicaoController    imec = new IgrafMenuEdicaoController(this, true, 3);    // 3
 private IgrafMenuExercicioController imex = new IgrafMenuExercicioController(this, true, 4); // 4
 private IgrafMenuPoligonoController  impc = new IgrafMenuPoligonoController(this, true, 5);  // 5
 private IgrafMenuAjudaController     imaj = new IgrafMenuAjudaController(this, true, 6);     // 6

 // Register 'igraf.moduloCentral.ModuloCentral.Menu(*)': MenuGrafico; 
 private HashMap hashMapIGrafMenus = new HashMap() { {
         put("menuGrf", imgc.getMenu());
         put("menuCalculo", imcc.getMenu());
         put("menuAnim", imac.getMenu());
         put("menuEdicao", imec.getMenu());
         put("menuExerc", imex.getMenu());
         put("menuPol", impc.getMenu());
         put("menuAjuda", imaj.getMenu());
         } };
 public IgrafMenuController getHashMapIGrafMenus (String strButtonName) { return (IgrafMenuController)hashMapIGrafMenus.get(strButtonName); }
 public HashMap getHashMapIGrafMenus () { return hashMapIGrafMenus; } // from: igraf/moduloSuperior/visao/PainelBotoes.java

 private IgrafMenu [] menuList = {
   imac.getMenu(),
   imaj.getMenu(),
   imcc.getMenu(), 
   imec.getMenu(),
   imex.getMenu(),
   imgc.getMenu(),
   impc.getMenu(),
   imec.getMenuIdioma()
   };


 public ModuloCentral (ModuloInferior moduloInferior) {
  painelCentral = new PainelCentral(pcc, moduloInferior);
  painelCentral.setMenuList(menuList);
  this.moduloInferior = moduloInferior;
  }

 public Component getPane () {
  return painelCentral;
  }


 public void filtrarEventoEntrada (CommunicationEvent ie) {
  //T  if (ie instanceof igraf.moduloSuperior.eventos.IgrafButtonPaneEvent) {
  //T     System.out.println("ModuloCentral.java: filtrarEventoEntrada: " + ie.toString().substring(0,100));
  //T     try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  //T     }
  if (ie instanceof ModuloCentralDisseminavelEvent)
   disseminarEventoInternamente(ie);
  }

 public void filtrarEventoSaida (CommunicationEvent ie) { 
  disseminarEventoExternamente(ie);
  }

 }
