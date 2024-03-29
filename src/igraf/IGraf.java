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
 * @description Main class of iGraf. Start point to application and "applet".
 * Esta � classe principal do programa e est� implementada na forma <b>superapplet</b>, ou seja,
 * funciona tanto como applet quanto como uma aplica��o independente.
 * Instancia todos os participantes e estabelece seus relacionamentos.
 *
 * Attention: the recommended width is 750. In small screen use at least 300.
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

package igraf;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.GravadorLeitor;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.Configuracoes;
import igraf.moduloArquivo.Sistema;
import igraf.moduloArquivo.eventos.LoadingIGrafEvent;
import igraf.moduloArquivo.eventos.LoadingAppletEvent;
import igraf.moduloCentral.eventos.FrameResizedEvent;
import igraf.moduloExercicio.controle.JanelaExercicioController;


/**
 */

public class IGraf extends JApplet implements ComponentListener {

 //DEBUG
 //
 protected static IGraf instanceIGraf = null;
 public static IGraf getInstanceIGraf () { return instanceIGraf; }

 public static final boolean IS_DEBUG_LOG = true; //DEBUG: special debug flag : must be reviewed to produce a log file //TODO

 public static final boolean IS_DEBUG = true; //DEBUG: flag to show debug messages
 public static String debugErrorMsg (String strName) { //C Class class
   String strErr = "";
   //C try { //DEBUG: for security reason...
    if (IS_DEBUG)
       strErr = strName + ": "; //C class.getClass().getName();
   //C } catch (Exception except) { strErr = "" + class; }
   return strErr;
   }
 public static void launchStackTrace () { //C Class class
   try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
   }

 // Used in: difusor/CommunicationFacade.java; difusor/Broadcaster.java; difusor/Broadcaster.java
 public static final boolean DEBUG_EVENT_EXT = false; //true; // //DEBUG: flag to show information about CommunicationFacade.disseminarEventoExternamente(...)
 public static final boolean DEBUG_EVENT_INT = false; //true; //DEBUG: flag to show information about CommunicationFacade.disseminarEventoInternamente(...)
 public static final String  DEBUG_EVENT_INT_INDENT = "  [I] "; // indentation to be used in 'disseminarEventoInternamente' (related to 'disseminarEventoExternamente')

 public static final boolean DEBUG_TRACKRESOURCE = false; //true; //DEBUG: flag to show stack in absence of any String in 'igraf/basico/resource/StringsTable*.properties'
     
 public static final boolean LAYOUTNOVO = true; // false; //LAYOUTNOVO => src/igraf/moduloSuperior/ModuloSuperior.java usara 2 paineis apenas, acima com botoes (TODOS) e abaixo a caixa de entrada de funcoes

 public static final int INDEXDEFAULTLANGUAGE = 0; // default language index : { pt_BR, en_US } => default is pt_BR

 public static boolean ehApplet = true; //
 public static boolean loading = true;

 public static String
 STR_MA_PARAM_Teacher     = "", // "EnvWebTEACHER"
 STR_MA_PARAM_addressPOST = "",
 STR_MA_PARAM_ExtraInfo   = "",
 STR_MA_POST_ExtraInfo    = "",
 
 // novos par�metros
 /**
  * iLM_PARAM_View* (2011/05/16)<br>
  * indica ao iMA que deve entrar em modo de exame da resposta enviada.
  * iLM_PARAM_View: geral, normalmente para aluno
  * iLM_PARAM_ViewTeacher: visualiza��o especial para professor
  */ 
 iLM_PARAM_View        = null,
 iLM_PARAM_ViewTeacher = null;

 private static String // iLM 2.0:
  iLM_PARAM_SendAnswer = "",           // iLM 2.0: !"false" => send to the server the answer whenever the user click in the evaluation button (do not change page) (@see BOOL_SEND_ANSWER)
  iLM_PARAM_ServerToGetAnswerURL = "", // Web Service to get the iLM answer (in LMS)
  iLM_PARAM_ChangePage = "";           // default=false: if absent => use as 'false' (do not change page when student send its answer)

 private static String // entrada de dados via HTML
  STR_MA_PARAM_notSEND = "",        // Proposition MA_PARAM_notSEND
  STR_MA_PARAM_Proposition = "",    // MA_PARAM_Proposition - old version o iLM: to get file through URL, when 'MA_PARAM_PropositionURL' is 'true'
  STR_MA_PARAM_PropositionURL = ""; // MA_PARAM_PropositionURL - old version o iLM: to get file through URL

 public static String getILM_PARAM_ServerToGetAnswerURL () { return iLM_PARAM_ServerToGetAnswerURL; } // iLM 2.0
 public static String getILM_PARAM_ChangePage () { return iLM_PARAM_ChangePage; }                     // iLM 2.0
 public static String getILM_PARAM_dontChangePage () { return iLM_PARAM_dontChangePage; }             // iLM 1.0

 // iLM 2.0: default is send the answer whenever user push evalauation button
 // Used in: igraf/basico/io/GravadorLeitor.java: enviarResultadoExercicio(Applet,String,int)
 public static boolean
  BOOL_SEND_ANSWER = true;          // do not send <=> IGraf.iLM_PARAM_SendAnswer=="false"

 private GravadorLeitor gl = null; // Qdo 'applet', usado em: GravadorLeitor.enviarResultadoExercicio(String,int)
 private ContentPane generalContentPanel;
 
 
 // implementa��o dos novos par�metros
 
 /** 
  * Url do arquivo da atividade (�nico par�metro obrigatorio)<br>
  * Substitui 'MA_PARAM_PropositionURL', mas agora guardando diretamente o endere�o
  */
 public static String iLM_PARAM_AssignmentURL = null;
 
 /**
  * Conte�do, em formato string, da atividade<br>
  * Substitui 'MA_PARAM_Proposition', mas agora sem sobrecarga)
  * Este par�metro passa a ser uma facilidade p/ quem far� pg "avulsas" c/ iMA)
  */
 public static String iLM_PARAM_Assignment = "";
 
 
/**
 * Indica ao iMA que ele NAO deve mostrar a retroacao sobre a resposta do aluno.<br>
 * iGraf does NOT show the evaluation only if the parameter is set AND with value "false"
 * Must be used only with iLM with automatica evaluation, case of iGraf.
 */
 public static String iLM_PARAM_Feedback = ""; 

 
/**
 * Indica ao iMA que ele NAO deve enviar ao servidor a resposta do aluno, mesmo que ele clique em botao para isso.<br>
 * Par�metro novo que mant�m apenas uma das funcionalidades do antigo 'MA_PARAM_notSEND'<br>
 */
 public static String iLM_PARAM_dontSendAnswer = "";
 

 /**
  * Indica ao iMA que ele NAO deve trocar de pagina ap�s o aluno clicar no bot�o para avaliar/enviar.<br>
  * (at� 2011/12/07 estava 'iLM_PARAM_noChangePage')
  */
 public static String iLM_PARAM_dontChangePage = "";
 
 
 /**
  * Indica ao iMA que deve permitir autoria (legado, para passar sobre a seguranca iGeom p/ nao mostrar gabarito)
  * (substitui o 'MA_PARAM_Teacher')
  */
 public static String iLM_PARAM_authoring = "";
 
 
 
 // Controller class that allows to IGraf class access to other contrellers
 // - difusor.CommunicationFacade is extended by:
 //   + igraf.IGrafController
 //   + igraf.moduloAjuda.ModuloAjuda
 //   + igraf.moduloCentral.ModuloCentral
 //   + igraf.moduloArquivo.ModuloArquivo
 //   + igraf.moduloInferior.ModuloInferior
 //   + igraf.moduloExercicio.ModuloExercicio
 //   + igraf.moduloJanelasAuxiliares.ModuloJanelasAuxiliares
 //   + igraf.moduloSuperior.ModuloSuperior
 private IGrafController igc = new IGrafController(this); // IGrafController extends difusor.CommunicationFacade 

 public void IGraf (String[] args) {
  IGraf.ehApplet = false;
  init();
  start();

  if (args.length > 0) { // try to load parameters: 'lang=pt_BR' and 'file_name'

     if (args[0] != null) {
        String strFileName = ResourceReader.setConfig(this, args); // process 'lang=pt_BR' and returns file name (if there exists one)
        String strFileContent = null;
        //T System.out.println("IGraf.java: IGraf(): strFileName=" + strFileName);
        if (strFileName!=null) {
           IGraf.loading = true;

           strFileContent = Sistema.readFileFromHD(strFileName, ""); // read content file

           //DEVEL Do not use the above code, it is just to register the other reading method,
           //DEVEL it only could work if the file is under 'igraf' directory
           //DEVEL strFileContent = GravadorLeitor.getILMfile(this, strFileName);
           //T System.out.println("IGraf.java: IGraf(): #strFileContent=" + (strFileContent==null ? "0" : ""+strFileContent.length()) );

           if (strFileContent!=null && strFileContent!="") { // try to load the file content (must be GRF)
              // IGrafController igc: nao tem 'disseminarEventoExternamente(...)' mas sim 'difusor.CommunicationFacade'
              // igraf/IGrafController.java extends CommunicationFacade
              loadIGraf(strFileContent);
              }

           }
        }

     }
  IGraf.loading = false;

  // Necessary to make relation between:
  // * menus (igraf/moduloSuperior/visao/PainelBotoes.java) and its configuration (igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java).
  // * exercise (igraf/moduloExercicio/ModuloExercicio.java) and status bar (igraf/moduloInferior/controle/InfoPaneController.java)
  generalContentPanel.relationsModulesDinamics(this, ehApplet);

  } // void IGraf (String[] args)


 //
 public void loadIGraf (String strFileContent) {
  if (IGraf.ehApplet)
    igc.disseminarEventoExternamente(new LoadingAppletEvent(this, strFileContent));
  else
    igc.disseminarEventoExternamente(new LoadingIGrafEvent(this, strFileContent));
  }


 // Inicia parametros
 private void inicializaParametros () {

  // ---
  // iLM protocol 2.0: start
  // 

  // Parameters defined after 2012, Feb 02 (02/02/2012)
  IGraf.iLM_PARAM_AssignmentURL        = this.getParameter("iLM_PARAM_AssignmentURL");        // URL where the file content is
  IGraf.iLM_PARAM_authoring            = this.getParameter("iLM_PARAM_authoring");            // it allow create/edit exercise if, and only if, "true"
  IGraf.iLM_PARAM_Feedback             = this.getParameter("iLM_PARAM_Feedback");             // do not show feedback to the studend if, and only if, "false"
  IGraf.iLM_PARAM_ServerToGetAnswerURL = this.getParameter("iLM_PARAM_ServerToGetAnswerURL"); // Web Service to get the iLM answer (in LMS)
  IGraf.iLM_PARAM_ChangePage           = this.getParameter("iLM_PARAM_ChangePage");           // default=false: if absent => use as 'false' (do not change page when student send its answer)

  if (IGraf.iLM_PARAM_ServerToGetAnswerURL!=null && IGraf.iLM_PARAM_ServerToGetAnswerURL.length()>0)
    IGraf.STR_MA_PARAM_Teacher = IGraf.iLM_PARAM_ServerToGetAnswerURL; // final variable

  //T System.out.println("IGraf.inicializaParametros(): iLM_PARAM_authoring=" + iLM_PARAM_authoring);

  IGraf.STR_MA_PARAM_PropositionURL = IGraf.iLM_PARAM_AssignmentURL; // it will define URL where iGraf must find the content

  // Para JavaScript: se usar m�todos p�blicos 'getAnswer()' e 'getEvaluation()' => o bot�o de "avaliar" N�O deve enviar exerc�cio
  // se str_param_sendAnswer="true" => envia exerc�cio para URL indicada em 'MA_PARAM_addresPOST' ao clicar no bot�o interno de avalia��o
  // protocol 2.0: convention 04/2011
  IGraf.iLM_PARAM_SendAnswer = this.getParameter("iLM_PARAM_SendAnswer"); // do not send student answer to the server <=> it is "false"
  if (IGraf.iLM_PARAM_SendAnswer!=null && IGraf.iLM_PARAM_SendAnswer!="") {
   if (IGraf.iLM_PARAM_SendAnswer.toLowerCase().equals("false"))
    IGraf.BOOL_SEND_ANSWER = false; // do not send evaluation when studant push "evaluation" button!
   // default: IGraf.BOOL_SEND_ANSWER = true;
   }

  // iLM protocol 2.0: end
  // ---


  // Abrir arquivo Web:
  // 1. MA_PARAM_notSEND: "true" => usar 'MA_PARAM_Proposition' como endereco URL de arquivo
  // 2. MA_PARAM_PropositionURL: "true" => usar 'MA_PARAM_Proposition' como endereco URL de arquivo
  // 3. MA_PARAM_Proposition:
  //NV if (STR_MA_PARAM_SENDanswer==null || STR_MA_PARAM_SENDanswer=="") // iLM 1.0?
  //NV  STR_MA_PARAM_notSEND           = this.getParameter("MA_PARAM_notSEND"); // "true" => nao enviar resposta, nao trocar de pagina, ler de URL (a ser eliminado)

  if (IGraf.STR_MA_PARAM_PropositionURL==null || IGraf.STR_MA_PARAM_PropositionURL=="") { // iLM 1.0?
   IGraf.STR_MA_PARAM_Proposition       = this.getParameter("MA_PARAM_Proposition"); //  old version o iLM: to get file through URL, when 'MA_PARAM_PropositionURL' is 'true'
   IGraf.STR_MA_PARAM_PropositionURL    = this.getParameter("MA_PARAM_PropositionURL"); // old version o iLM: to get file through URL
   }

   IGraf.STR_MA_PARAM_Teacher     = this.getParameter("MA_PARAM_Teacher"); // "EnvWebTEACHER"

  if (IGraf.STR_MA_PARAM_addressPOST==null || IGraf.STR_MA_PARAM_addressPOST=="")
   IGraf.STR_MA_PARAM_addressPOST = this.getParameter("MA_PARAM_addressPOST");

  // No iTarefa 2013 esta 'param name='MA_PARAM_addresPOST' value='http:localhost/saw/mod/iassign/view.php?action=get_answer&id=92&iassign_current=46&write_solution=0&userid_iassign=3'
  if (IGraf.STR_MA_PARAM_addressPOST==null || IGraf.STR_MA_PARAM_addressPOST=="")
   IGraf.STR_MA_PARAM_addressPOST = this.getParameter("MA_PARAM_addresPOST");

  IGraf.STR_MA_POST_ExtraInfo    = this.getParameter("MA_POST_ExtraInfo");
  
  // par�metros abandonados
  IGraf.iLM_PARAM_View           = this.getParameter("iLM_PARAM_View");
  IGraf.iLM_PARAM_ViewTeacher    = this.getParameter("iLM_PARAM_ViewTeacher");
  IGraf.iLM_PARAM_Assignment     = this.getParameter("iLM_PARAM_Assignment");  
  IGraf.iLM_PARAM_dontSendAnswer = this.getParameter("iLM_PARAM_dontSendAnswer");
  IGraf.iLM_PARAM_dontChangePage = this.getParameter("iLM_PARAM_dontChangePage"); // see IGraf.iLM_PARAM_ChangePage - default=false: if absent => use as 'false' (do not change page when student send its answer)

  // Se algum falhar, tente com nomes 'antigos' do SAW
  if (IGraf.STR_MA_PARAM_addressPOST=="" || IGraf.STR_MA_PARAM_addressPOST==null)
   IGraf.STR_MA_PARAM_addressPOST = this.getParameter("enderecoPOST");

  if (STR_MA_PARAM_Proposition=="" || STR_MA_PARAM_Proposition==null)
   STR_MA_PARAM_Proposition = this.getParameter("paramGabarito");

  if (IGraf.STR_MA_PARAM_ExtraInfo=="" || IGraf.STR_MA_PARAM_ExtraInfo==null)
   IGraf.STR_MA_PARAM_ExtraInfo = this.getParameter("paramInfo");
  
  String strLang = this.getParameter("lang");
  setLanguage(strLang);
  System.out.println();
  }

 /**
  * Determina as dimens�es do painel principal, ou seja, a parte do programa
  * vis�vel assim que o mesmo � iniciado.
  * Esse m�todo s� � chamado caso o programa esteja sendo executado na forma
  * de applet.
  */
 public void init () {

  //DEBUG
  instanceIGraf = this; //CAUTION: in applet, if a page has several copy of iGraf this could fail

  if (IGraf.ehApplet) // must be befor to define menus!
   inicializaParametros();

  ResourceReader.setLanguage(0); // new ResourceReader(0);
  gl = new GravadorLeitor();
  gl.applet(this);

  // ContentPane makes 'ModuloExercicio me = new ModuloExercicio()'
  // ModuloExercicio make 'JanelaExercicioController jec = new JanelaExercicioController(this, true)
  // JanelaExercicioController needs in 'actionPerformed(ActionEvent e)' access to IGraf to send answer to the server when click "Evaluation"
  generalContentPanel = new ContentPane(this, IGraf.ehApplet);

  generalContentPanel.addIgrafController(igc);

  if (IGraf.ehApplet) {
   String str_content = null;

   if (iLM_PARAM_AssignmentURL != null) { // it is iLM 2.0 protocol
    str_content = GravadorLeitor.getILMfile(this,iLM_PARAM_AssignmentURL); // it uses 'GravadorLeitor.readFromURL(jApplet, maURL);'
    }   
   else
   if (iLM_PARAM_Assignment != null) { // it is iLM 1.0 protocol
    str_content = GravadorLeitor.getILMfile(this,iLM_PARAM_Assignment); // it uses 'GravadorLeitor.readFromURL(jApplet, maURL);'
    }
   else
   if (STR_MA_PARAM_PropositionURL!=null && STR_MA_PARAM_PropositionURL.toLowerCase().equals("true")) { // it is iLM 1.0 protocol
     str_content = GravadorLeitor.getILMfile(this,STR_MA_PARAM_Proposition);
     }
   // System.out.println("IGraf.init(): STR_MA_PARAM_PropositionURL="+STR_MA_PARAM_PropositionURL);

   // System.out.println("IGraf.init(): "+str_content);

   // if (STR_MA_PARAM_notSEND!=null && STR_MA_PARAM_notSEND.toLowerCase().equals("true")) {
   //  // must use MA_PARAM_Proposition as URL of the GRF file with iGraf content
   //  str_content = GravadorLeitor.getILMfile(this,STR_MA_PARAM_Proposition);
   // }
   // just to check
   //System.out.println("IGraf.init(): STR_MA_PARAM_notSEND="+STR_MA_PARAM_notSEND); // +": "+str_content

   if (str_content != null)
    igc.disseminarEventoExternamente(new LoadingAppletEvent(this, str_content));
   else
    if (STR_MA_PARAM_Proposition != null)
     igc.disseminarEventoExternamente(new LoadingAppletEvent(this, STR_MA_PARAM_Proposition));

   if (iLM_PARAM_View != null || iLM_PARAM_ViewTeacher != null)
    igc.disseminarEventoExternamente(new LoadingAppletEvent(this, LoadingAppletEvent.STUDENT_VIEW));

    // a implementacao atual nao diferencia entre visao de aluno ou de professor

   }
  else;
  //System.out.println("IGraf.init(): ERRO - STR_MA_PARAM_notSEND="+STR_MA_PARAM_notSEND);

  this.getContentPane().add(generalContentPanel);

  } // void init()


 public void componentResized (ComponentEvent e) {
  igc.disseminarEventoExternamente(new FrameResizedEvent(this, e.getComponent().getBounds()));
  }

 public void componentHidden (ComponentEvent e) { }
 public void componentMoved (ComponentEvent e) { }
 public void componentShown (ComponentEvent e) { }

 private int indexLanguage = INDEXDEFAULTLANGUAGE;
 public int getLanguage () { return indexLanguage; } //DEBUG
 public void changeLanguage (int lang) {
  // igraf.IGrafController igc - IGrafController extends difusor.CommunicationFacade
  if (indexLanguage==lang) { // nothing to be done...
     return;
     }

  try {
   ResourceReader.setLanguage(lang);

   // igraf/IGrafController.java: difusor/CommunicationFacade.java: public void disseminarEventoExternamente(CommunicationEvent ie)
   // Actually it is a CommunicationEvent = 'igraf.basico.event.ChangeLanguageEvent'
   //TODO: colocar diretamente aqui os eventos que a troca de lingua dispara!
   igc.disseminarEventoExternamente(new ChangeLanguageEvent(this));

   indexLanguage = lang;
  } catch (Exception except) { except.printStackTrace(); }

  }

 // esse m�todo deve ser adaptado a cada nova l�ngua introduzida no sistema
 private void setLanguage (String lang) {
  if (lang==null || lang=="") return; // important: when is missing the tag 'lang'
  if (lang.equalsIgnoreCase("en_us"))
   changeLanguage(ResourceReader.ENGLISH);
   }

 //---
 // LMS + JavaScript

 // Para uso em LMS via JavaScript
 // JavaScript:
 //  var valor_resposta = document.eMA.getEvaluation(); // valor
 //  var resposta_exerc = document.eMA.getAnswer();  // resposta
 //  var sessao_icomb = document.eMA.getTrace();

 private double evaluation = -1;
 private String answer = "", sessao = "", trace = "";

 /**
  * M�todo que registra a resposta do aluno a um exerc�cio do iGraf.
  * Estou supondo que a resposta do aluno n�o seja toda a sess�o... talvez seja melhor separar e
  * para ler a sess�o usar o getTrace()
  * @param answer
  * @called by igraf.IGrafController.filtrarEventoEntrada
  */
 public void setAnswer (String answer) {
  this.answer = answer; // informacoes sobre a resposta do aluno
  //L
System.out.println("Set a partir de IGraf.setAnswer:\n" + answer+"\n---");
  }

 /**
  * M�todo que retorna como avalia��o o n�mero de acertos obtidos pelo aluno em um determinado exerc�cio
  * Called in: igraf/IGrafController.java
  * Event generated in: igraf/moduloExercicio/controle/JanelaExercicioController.java: actionPerformed(JanelaExercicioController.java:241)
  * @param evaluation
  */
 public void setEvaluation (double evaluation) {
  if (IS_DEBUG) System.out.println("IGraf.getEvaluation: sessao="+sessao);
  this.evaluation = evaluation;
  if (IS_DEBUG) System.out.println(" getEvaluation(): evaluation=" + evaluation);
  //     igraf.basico.util.Utilitarios.rastrearError();
  }


 /**
  * M�todo que recebe uma string 'sessao' contendo todas as a��es realizadas pelo usu�rio e atribui �
  * vari�vel 'sessao' local.   Este m�todo � chamado quando o usu�rio faz o envio dos resultados de um
  * exerc�cio clicando no bot�o 'Avaliar Exerc�cio' indiretamente, via tratamento de eventos. 
  * @param sessao
  */
 public void setSessao (String sessao) {
  this.sessao = sessao;
  //L 
System.out.println("\nSet a partir de IGraf.setSessao: #sessao=" + sessao.length() +"\n---");
  // System.out.println("IGraf.setSessao: sessao="+sessao);
  }

 // igraf/IGrafController.java: filtrarEventoEntrada(IgrafEvent ie)
 public void setTrace (String trace) {
  this.trace = trace;
  //System.out.println("Set a partir de IGraf.setTrace:\n" + trace+"\n---");
  }

 //-------------------------------------------------
 // iLM 2.0: public method to any web server get the exercise evaluation
 // Get the exercise automatic evaluation
 public double getEvaluation () {
  // Attention: 'this.evaluation' must be defined in method 'void setEvaluation(double evaluation)'
  return evaluation;
  }

 // public void setAnswer (double evaluation) {  this.evaluation = evaluation;  }

 public String getAnswer (boolean openDialog) { // From: igraf/moduloExercicio/controle/JanelaExercicioController.java: actionPerformedButtonCliked(String,ActionEvent)
  if (openDialog)
   sessao = JanelaExercicioController.showAnswerDialog(); // igraf.moduloExercicio.controle.JanelaExercicioController
  System.out.println("igraf/IGraf.java: getAnswer(boolean): " + sessao);
  return sessao;
  }


 // iLM 2.0: public method to any web server get the exercise content
 // Get the exercise content
 public String getAnswer () { // From: src/igraf/moduloArquivo/Arquivo.java; 
  if (evaluation==-1) {
   System.out.println("igraf/IGraf.java: getAnswer(): empty answer!!!! Please, use the button \"Exerceise | Evaluation\"");
   return "-1";
   }
  
  //sessao = JanelaExercicioController.showAnswerDialog(); // igraf.moduloExercicio.controle.JanelaExercicioController
  System.out.println("igraf/IGraf.java: getAnswer(): " + sessao);
  return sessao;
  }


 // Pega anotacoes sobre a resposta do aluno
 public String getTrace () {
  // System.out.println("IGraf.getTrace: "+answer);
  return answer;
  }
 //-------------------------------------------------

 // LMS + JavaScript: final
 //---

 public void fecharIGraf () {
  ResourceReader.setConfig(); // anota em 'igraf.cfg' os dados de configura��o atuais
  igc.disseminarEventoExternamente(new IgrafClosingEvent(this));  
  }


 /**
  * M�todo principal; implementado para suportar a execu��o
  * do programa na condi��o standalone.
  * @param args
  */  
 public static void main (String[] args) {  
  final IGraf igraf = new IGraf();

  igraf.IGraf(args); // try parameters

  JFrame frameIGraf = new JFrame();
  frameIGraf.setTitle(".: " + ResourceReader.msg("igraf") + ":.");
  frameIGraf.setSize(Configuracoes.lTFP+5,Configuracoes.aTFP); //static final int lTFP = 832;'
  frameIGraf.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) { 
    igraf.fecharIGraf(); 
    }
   } );

  frameIGraf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  frameIGraf.addComponentListener(igraf);
  frameIGraf.getContentPane().add(igraf);
  frameIGraf.setVisible(true);
  }

 }
