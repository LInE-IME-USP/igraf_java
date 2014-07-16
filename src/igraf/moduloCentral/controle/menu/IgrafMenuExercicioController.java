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
 * @see igraf/moduloCentral/visao/menu/MenuExercicio.java
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

import igraf.IGraf;
import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.LoadingAppletEvent;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuExercicioEvent;
import igraf.moduloCentral.visao.menu.IgrafMenu;
import igraf.moduloCentral.visao.menu.MenuExercicio;
import igraf.moduloExercicio.eventos.DiagnosticEvent;
import igraf.moduloExercicio.eventos.RespostaEvent;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;
import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;


public class IgrafMenuExercicioController extends IgrafMenuController {

 private IgrafMenuExercicioEvent imee;
 private MenuExercicio me;

 private boolean temAnimacao, temAnimacaoOculta, temFuncao, temFuncaoOculta, temResposta, temPoligono;
 private boolean exercicioCarregado;


 // Constructor in: igraf/moduloCentral/ModuloCentral.java
 // It define order: {IgrafMenuGraficoController, IgrafMenuCalculoController, IgrafMenuAnimacaoController, IgrafMenuEdicaoController, IgrafMenuExercicioController, IgrafMenuPoligonoController, IgrafMenuAjudaController }
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuEdicaoController
 //   4: IgrafMenuExercicioController
 //   5: IgrafMenuPoligonoController
 //   6: IgrafMenuAjudaController
 public IgrafMenuExercicioController (CommunicationFacade module, boolean getEvents, int index) {
  super(module, getEvents, index);
  me = new MenuExercicio(this, index);
  }


 public void enviarEventoAcao (String cmd) {
System.out.println("src/igraf/moduloCentral/controle/menu/IgrafMenuExercicioController.java: enviarEventoAcao: " + cmd);
  imee = new IgrafMenuExercicioEvent(this);
  imee.setCommand(cmd);
  enviarEvento(imee);
  }


 public void tratarEventoRecebido (CommunicationEvent communicationEvent) {
  String strCommand = communicationEvent.getCommand();
  //T System.out.println("src/igraf/moduloCentral/controle/menu/IgrafMenuExercicioController.java: tratarEventoRecebido: " + communicationEvent.getClass().getName() + ", " + strCommand);

  if (communicationEvent instanceof DisableMenuEvent) {
   removeDisabledMenuItens(communicationEvent);
   return;
   }

  if (strCommand.equals(RespostaEvent.READ_EXERCISE)) {
   setExercise(true);
   return;
   }

  if (communicationEvent instanceof ChangeLanguageEvent) {
   me.updateLabels();
   return;
   }


  // If it is applet (and NOT a teacher edition) => it can not create exercise or alter configuration
  if (communicationEvent instanceof LoadingAppletEvent) {
   //T System.out.println("IgrafMenuExercicioController.java: tratarEventoRecebido(...): " + communicationEvent.getClass().getName() + ": " + IGraf.ehApplet + ", " + IGraf.iLM_PARAM_authoring);
   if (IGraf.ehApplet && (IGraf.iLM_PARAM_authoring==null || !IGraf.iLM_PARAM_authoring.equals("true"))) {
    // If the GRF file content is an exercise AND it is not (authoring OR application) => remove the "Configure menus" option
    int position;
    me.setEnabledMenuItem(ResourceReader.msg("mexExercCriar"), false);      // security... "Create exercise"
    me.setEnabledMenuItem(ResourceReader.msg("mexExercEditar"), false);     // security... "Edit exercise"
    me.setEnabledMenuItem(ResourceReader.msg("mexExercMenuConfig"), false); // security... "Configure menus"
    position = me.removeMenuItem("mexExercCriar");      // set "not visible" the option "Create exercise" (must use the root of the text - befor 'ResourceReader.msg(.)'!)
    position = me.removeMenuItem("mexExercEditar");     // set "not visible" the option "Edit exercise"
    position = me.removeMenuItem("mexExercMenuConfig"); // set "not visible" the option "Configure menus"
    }
   }

  if (strCommand.equals(ResetEvent.RESET)) {
   setStudentAnswer(false); 
   setExercise(false);
   return;
   }

  if (communicationEvent instanceof IgrafTabUpdateEvent)
   if (strCommand.equals(IgrafTabUpdateEvent.FUNCTION_LIST_CHANGED) ||
       strCommand.equals(IgrafTabUpdateEvent.POLYGON_LIST_CHANGED) ||
       strCommand.equals(IgrafTabUpdateEvent.ANIMATION_LIST_CHANGED)) {
    IgrafTabUpdateEvent iue = (IgrafTabUpdateEvent)communicationEvent;
    String strCommandTab = iue.getCommand();

    if (strCommandTab.equals(IgrafTabUpdateEvent.FUNCTION_LIST_CHANGED)) {
     temFuncao = iue.getFunctionList().size() > 0;
     temFuncaoOculta = iue.temDesenhoOculto();
     }
    else
    if (strCommandTab.equals(IgrafTabUpdateEvent.POLYGON_LIST_CHANGED)) {
     temPoligono = iue.isPolygonListChanged();
     }
    else
    if (strCommandTab.equals(IgrafTabUpdateEvent.ANIMATION_LIST_CHANGED )) {
     temAnimacao =  iue.getAnimationList().size() > 0;
     temAnimacaoOculta = iue.temDesenhoOculto();
     }

    //TM1 System.out.println("src/igraf/moduloCentral/controle/menu/IgrafMenuExercicioController.java: tratarEventoRecebido(CommunicationEvent " + communicationEvent.getClass().getName() + ": " + ResourceReader.msg("mexExercHistorico"));

    // Set Exercise menu item enabled or disabled
    me.setEnabledMenuItem(ResourceReader.msg("mexExercHistorico"), temFuncaoOculta | temAnimacaoOculta | temPoligono);

    if (iue.isItExercise()) // igraf.moduloCentral.eventos.IgrafTabUpdateEvent iue: in tab changes
     setExercise(true);
    else
    if (!exercicioCarregado)
      setExercise(false);
    }

  if (strCommand.equals(DiagnosticEvent.LOAD_RESULT)) { // para o carregamento do arquivo   
   setStudentAnswer(true); 
   }
  else
  if (strCommand.equals(ResourceReader.msg("msgMenuGrfNovaSes"))) {
   setExercise(false);
   me.setEnabledMenuItem(ResourceReader.msg("mexExercHistorico"), false);
   }

  modoExameResposta();

  } // public void tratarEventoRecebido(CommunicationEvent communicationEvent)


 /**
  * Método que define se devem ser exibidas as respostas dos alunos.<br><br> 
  * Existem três possíveis configurações de acesso às respostas:<br>
  * a. usuário aluno: não exibir respostas anteriores<br>
  * b. usuário aluno: exibir histórico de respostas<br>
  * d. usuário professor: sempre exibir histórico de respostas
  */
 
 // Existe algum caso em que o professor não possa ver as respostas de seus alunos?
 // Se não, não tem muito sentido o uso da variável iLM_PARAM_VIEW_Teacher
 
 // Ao implementar este método senti a necessidade de identificar o tipo de usuário...
 // Daí, pergunto: está em uso no iTarefa a variável 'IGraf.STR_MA_PARAM_Teacher' ?
 
 private void modoExameResposta () {
  }


 private void setExercise (boolean isExercise) {
  exercicioCarregado = true;

  // Applet
  // + authoring: mexExercCriar=true; mexExercMenuConfig=true
  // + not auth.: mexExercCriar=false; mexExercMenuConfig=false
  // Application
  // + any situation: mexExercCriar=true; mexExercMenuConfig=true

  me.setEnabledMenuItem(ResourceReader.msg("mexExercResp"), isExercise); // mexExercEval = Visualize answers

  if (!IGraf.ehApplet || (IGraf.iLM_PARAM_authoring!=null && IGraf.iLM_PARAM_authoring.equals("true"))) {
   // setEnabledMenuItem(ResourceReader.msg("mexExercResp"), true);       // Answer / send
   me.setEnabledMenuItem(ResourceReader.msg("mexExercEval"), true);       // Visualize answers
   me.setEnabledMenuItem(ResourceReader.msg("mexExercCriar"), true);      // Create exercise
   me.setEnabledMenuItem(ResourceReader.msg("mexExercEditar"), true);     // Edit exercise
   me.setEnabledMenuItem(ResourceReader.msg("mexExercMenuConfig"), true); // Configure menus
   }
  else { // If the GRF file content is an exercise AND it is not (authoring OR application) => remove the "Configure menus" option
   int position;
   me.setEnabledMenuItem(ResourceReader.msg("mexExercCriar"), isExercise);      // security... "Create exercise"
   me.setEnabledMenuItem(ResourceReader.msg("mexExercEditar"), false);     // security... "Edit exercise"
   me.setEnabledMenuItem(ResourceReader.msg("mexExercMenuConfig"), false); // security... "Configure menus"
   position = me.removeMenuItem("mexExercCriar");      // set "not visible" the option "Create exercise" (must use the root of the text - befor 'ResourceReader.msg(.)'!)
   position = me.removeMenuItem("mexExercEditar");     // set "not visible" the option "Edit exercise"
   position = me.removeMenuItem("mexExercMenuConfig"); // set "not visible" the option "Configure menus"
   //T System.out.println("IgrafMenuExercicioController.java: setExercise(boolean): eliminando " + ResourceReader.msg("mexExercMenuConfig") + ": position=" + position);
   }
  }

 private void setStudentAnswer (boolean hasAnswer) {
  me.setEnabledMenuItem(ResourceReader.msg("mexExercEval"), hasAnswer);
  me.setEnabledMenuItem(ResourceReader.msg("mexExercResp"), !hasAnswer);
  }


 // Started from 'igraf.IGraf.java: public void loadIGraf(String strFileContent)'
 private void removeDisabledMenuItens (CommunicationEvent ce) {
  DisableMenuEvent dme = (DisableMenuEvent)ce;
  int [] listaMenu = dme.getDisableList();
  int sizeListaMenu = listaMenu==null ? 0 : listaMenu.length;
  int i = 0;
  // System.out.println("src/igraf/moduloCentral/controle/menu/IgrafMenuExercicioController.java: " + ce.getClass().getName());
  // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

  // Attention: menu items in desable list must be in increasing order!
  while (i<sizeListaMenu && listaMenu[i] < 699) { // 699 is the last menu item from primary 'Exercise'
   switch (listaMenu[i++]) {
    // let 'IgrafMenu.removeMenuItem(String)' apply 'ResourceReader.msg(...)'
    case MenuExercicio.HISTORY:    me.removeMenuItem("mexExercHistorico");  break;
    case MenuExercicio.ANSWER:     me.removeMenuItem("mexExercResp");       break;
    case MenuExercicio.EVALUATION: me.removeMenuItem("mexExercEval");       break;
    case MenuExercicio.CREATION:   me.removeMenuItem("mexExercCriar");      break;
    case MenuExercicio.EDITING:    me.removeMenuItem("mexExercEditar");     break;
    case MenuExercicio.CONFIG:     me.removeMenuItem("mexExercMenuConfig"); break;
    }
   }
  }

 public void setControlledObject (Object obj) {
  me = (MenuExercicio)obj;
  }

 public IgrafMenu getMenu () {
  return me;
  }

 }
