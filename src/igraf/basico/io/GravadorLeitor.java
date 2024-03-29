/*
 * iGraf - Interactive Graphics on the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author  Le�nidas de Oliveira Brand�o
 *
 * @version 0.1: 19,20,21,26,27/12/2006
 * 
 * @desciption Resources to access file system;
 *          { 0:id, 1:tipo, 2: px py, 3: objetoHexa }
 * 
 * @see igraf/IGraf.java: public void init()
 * @see igraf/moduloExercicio/controle/JanelaExercicioController.java: private void actionPerformedButtonCliked(String strCommandAction, ActionEvent e)
 * 
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.basico.io;

import java.applet.Applet;
import java.awt.Frame;
import java.io.File; // File.separator
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.util.Vector;
//import javax.swing.JOptionPane;


import igraf.IGraf;
import igraf.moduloArquivo.Sistema;
import igraf.moduloExercicio.visao.JanelaInfo;


public class GravadorLeitor {

  // To debug
  //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
  public static final String IGCLASSPATH = "igraf/basico/io/GravadorLeitor.java";
  private static final String strGL = "[GL]"; // "[GravadorLeitor] ";
  private static final int    LIM_CONTEUDO = 80; // para truncar tamanho de string a ser listada na tela

  private static String enderecoDevolvidoPHP = "",
  strEnvWebInfo     = "", // para enviar 'string' passada p/ PHP via 'paramInfo' (deve conter a query da URL)
  strEnvWebDadosSis = ""; // strEnvWebDadosSis dados do sistema utilizado pelo usu�rio (plataforma, MVJ, user)
  private static Vector vetErros = new Vector(); // para lan�ar um s� 'WarningDiaglog' com todas as msgs de erro

  private static final String OUTPUT_ENC = "ISO-8859-1"; // charset to URLEncoder
  private static URL               urlStatic;
  private static URLConnection     urlConnStatic = null;

  // frame criado apenas para permitir a exibi��o do JOptionPane
  private static Frame aux = new Frame();

  // Formato de um objeto iMath
  // { 0:id, 1:tipo, 2: px py, 3: 023a83fb... }
  public static final int
  ID   = 0,
  TIPO = 1,
  DES  = 2, // descri��es de objeto como coordenadas (para imagem tamb�m largura e altura)
  OBJ  = 3;

  public static final int    // tipos de objetos
  TEXTO  = 0,
  IMAGEM = 1;

  public  void applet (Applet app) { } // definido em: 


  /**
   * Devolve uma string contendo uma iTarefa, ou seja, a proposta de
   * atividade escrita pelo professor e que dever� ser resolvida pelo
   * aluno.
   * @param maURL url do servidor que hospeda um MA
   * @return null se maURL for inv�lida
   */
  public static String getILMfile (javax.swing.JApplet jApplet, String maURL) {
    //L System.out.println("GravadorLeitor.getILMfile("+jApplet.getCodeBase().toString()+","+maURL+")");
    //  return getILMfile(maURL, jApplet.getCodeBase().toString());
    return readFromURL(jApplet, maURL);
    }

  public static String getILMfile (String maURL, String filePath) {
    return "";
    }


  // ----------------------------------------
  // ler arquivo de URL
  public static String readFromURL (java.applet.Applet applet, String strURL) {
   // Permite receber URL
   java.io.InputStream inputStream = null;
   java.net.URL endURL = null;

   java.lang.StringBuffer stringbuffer = null;
   try { //
     endURL = new java.net.URL(strURL); // se for URL
     // System.out.println("[Sistema.readFromURL: 1 "+strURL+" -> "+endURL+" -> "+endURL);
   } catch (java.net.MalformedURLException e) {
     try { // se falhou, tente completar com endere�o base do applet e completar com nome de arquivo

       endURL = new java.net.URL(applet.getCodeBase().toString()+strURL); // se for URL

       // If the file was under 'igraf' directory, it could be used:
       // endURL = igraf.IGraf.class.getResource(strURL); // se for URL

     } catch (java.net.MalformedURLException ue) {
       System.err.println("[GravadorLeitor.readFromURL: failed while reading '"+strURL+"' - it also results in URL="+endURL);
       // ue.printStackTrace();
       return "";
       } 
     } 
   try {
     inputStream = endURL.openStream();
     java.io.InputStreamReader inputstreamreader = new java.io.InputStreamReader(inputStream);
     java.io.StringWriter stringwriter = new java.io.StringWriter();
     int i = 8192;
     char[] cs = new char[i];
     try {
       for (;;) {
           int i_11_ = inputstreamreader.read(cs, 0, i);
           if (i_11_ == -1)
              break;
           stringwriter.write(cs, 0, i_11_);
           }
       stringwriter.close();
       inputStream.close();
     } catch (java.io.IOException ioexception) {
       System.err.println("Erro: leitura URL: "+strURL+": "+ioexception); //throw Trace.error(34, ioexception.getMessage());
       // ioexception.printStackTrace();
       }
     return stringwriter.toString();
   } catch (java.io.IOException ioe) {
     System.out.println("Erro: leitura URL: "+strURL+" -> "+endURL+": "+ioe.toString());
     // ioe.printStackTrace();
     }
   return "";
   }

  public static String decompoeGET_POST (String str) {
    StringTokenizer st = new StringTokenizer(str,"\"");
    StringTokenizer st1;
    String strNovo = "", s1="", s2="", s3, s4;

    if (st.hasMoreTokens()) {
      s1 = st.nextToken();
      if (st.hasMoreTokens())
        s1 = st.nextToken(); // s1 ficar� com o que est� dentro de aspas duplas
      }
    else
      s1 = str;
    //- System.out.println(" - s1 = "+s1);
    st = new StringTokenizer(s1,"&"); // processa o que estava dentro das aspas
    while (st.hasMoreTokens()) {
      try {
        s3 = s4 = "";
        s2 = st.nextToken();
        if (s2!="") {
          //-  System.out.println(" -- s2 = "+s2); // param=valor
          st1 = new StringTokenizer(s2, "=");
          s3 = st1.nextToken();            // param
          if (st1.hasMoreTokens())
            s4 = st1.nextToken();         // valor
          if (s3!="" && s4!="")
            strNovo += "&"+s3+"="+URLEncoder.encode(s4);
          }
        } catch (Exception exp) {
        exp.printStackTrace();
        System.out.println("Erro: ao tentar gravar, "+str+" token=("+s1+","+s2+")");
        return str;
        }
      }
    return strNovo;
    }

  // Try to stablish a connection: prepare sending, open the chanel and infor type and size
  // @return URLConnection urlConn
  private static URLConnection criaConexaoCom (String StringURL, String strContent, Vector vetErros, String msgErrorConex, String strAuxPrefix) {
    URL urlLocal = null;
    URLConnection urlConn = null;
    try {//urlStatic; private static URLConnection     urlConnStatic
      urlLocal = new URL(StringURL); // IGraf.STR_MA_PARAM_addressPOST
      urlConn = urlLocal.openConnection();
      urlConn.setDoInput(true);  // allow input: to get input sent from the server (if parameter 'iLM_PARAM_ChangePage' defined) 
      urlConn.setDoOutput(true); // allow output: to output to the server 
      urlConn.setUseCaches(false);
      urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      //__ urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + OUTPUT_ENC); // "ISO-8859-1"
      //__ urlConn.setRequestProperty("Cache-Control", "no-cache");
      urlConn.setRequestProperty("Content-Length", "" + Integer.toString(strContent.getBytes().length));
      System.out.println(strAuxPrefix + "criaConexaoCom(" + StringURL + "): getRequestProperty=" + urlConn.getRequestProperty("Content-Type"));
    } catch (Exception e) {
      String strUrlConn = urlConn!=null ? "urlConn.getURL()="+urlConn.getURL()+" - urlConn.toString()="+urlConn.toString() : "urlConn=null";
      System.err.println(strAuxPrefix + "Error: problems to connect to the server: " + e);
      System.err.println("    Server URL = " + StringURL);
      System.err.println("    UrlConn = "+strUrlConn);

      // Show the connection error number
      System.out.println(strAuxPrefix + "HttpURLConnection.getResponseCode = " + GravadorLeitor.responseInputFromServer(StringURL) + "");

      if (strEnvWebInfo!="") // aditional information to the server (could have complete query like 'id=1&info1=example')
        System.err.println("   Information: "+strEnvWebInfo);
      vetErros.addElement(new Integer(1)); // indica ser primeira msg de erro
      vetErros.addElement(msgErrorConex); // Ocorreu algum erro de conex�o. Tente enviar o exercicio novamente
      e.printStackTrace();
      }
    // System.out.println(strAuxPrefix + "criaConexaoCom: " + urlConn); // don't work since output is remote...
    return urlConn;
    } // private static URLConnection criaConexaoCom(String StringURL, String strContent, Vector vetErros, String msgErrorConex, String strAuxPrefix)


  // Check the status of a server
  // - 500: the server is up, but with some error (e.g., it could be a PHP with code error)
  // - 400: server is down
  public static int responseCodeFromHTML (URLConnection urlConn) {
   HttpURLConnection httpConnection = null;
   int status = -1;
   try {
     httpConnection = (HttpURLConnection) urlConn;
     status = httpConnection.getResponseCode();
     System.out.println("[GL] HttpURLConnection.getResponseCode = " + status + " (response code)");
   } catch (java.io.IOException except) {
     System.err.println("[GL] Error: invalid URL connection! (" + urlConn + ")");
     }
   return status;
   }

  // Get some information in a Web server: from String
  public static String responseInputFromServer (String strURL) {
   URL urlServer = null;
   URLConnection urlConn = null; // java.net.URLConnection
   try {
     urlServer = new URL(strURL);
     urlConn = urlServer.openConnection();
   } catch (java.io.IOException except) {
     System.err.println("[GL] Error: invalid URL! (" + strURL + ")");
     return null;
     }
   return responseInputFromServer(urlConn);
   }

  // Get some information in a Web server: from URLConnection
  public static String responseInputFromServer (URLConnection urlConn) {
   String strContent = null, strInputLine; // strFinalMark = "[end]";
   try {
     BufferedReader input = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
     while ((strInputLine = input.readLine()) != null) { // && !strInputLine.contains(strFinalMark)
      strContent += strInputLine;
      }
     strContent += input.readLine();
   } catch (java.io.IOException except) {
     System.err.println("[GL] Error: invalid URL connection! (" + urlConn + ")");
     return null;
     }
   System.out.println("[GL] resposta servidor: " + strContent + "");
   return strContent;
   } // public static String responseInputFromServer(URLConnection urlConn)


  /** 
   * Try to send the session content to a server under POST method.
   * Called from: igraf/sessao/Sessao.java: processaResposta(...)
   * Called from: igraf/moduloExercicio/controle/JanelaExercicioController.java: actionPerformedButtonCliked(...): from click in the evaluation button (internal)
   * @param Applet applet
   * @param String conteudoStr: a string contendo a sess�o do usu�rio
   * @param int resultado: 0 - 1
   * @return
   */
  public static int enviarResultadoExercicio (Applet applet, String conteudoStr, int resultado) {
    DataOutputStream printout = null;
    BufferedReader input;
    String conteudoEnv = "";

    if (!IGraf.ehApplet)  {
      System.out.println("Not an applet, nothing to be sent to any server");
      return -1;
      }

    System.out.println("[GL] Attempt to send answer. Content is: ---\n" + conteudoStr + "---");
    // try{String str="";System.out.println(str.charAt(3)); } catch(Exception e) {e.printStackTrace();}

    // Is authoring?
    boolean isAuthoring = false;
    if (IGraf.iLM_PARAM_authoring!=null && IGraf.iLM_PARAM_authoring.equals("true")) // iLM 2.0
      isAuthoring = true;
    else
    if (IGraf.STR_MA_PARAM_Teacher!=null && IGraf.STR_MA_PARAM_Teacher.equals("EnvWebTEACHER")) // iLM 1.0
      isAuthoring = true;
    if (igraf.IGraf.IS_DEBUG) System.out.println("[GL] isAuthoring=" + isAuthoring + "");

    if (isAuthoring) {
      String str1 = "";
      int sizeOf = conteudoStr==null ? 0 : conteudoStr.length();
      System.out.println("[GL] Authoring session. Sent by teacher: #size=" + sizeOf);
      if (conteudoStr == null || conteudoStr=="") {
    	// N�o foi poss�vel construir o gabarito do exerc�cio ou arquivo vazio!
        // JOptionPane.showMessageDialog(aux, ResourceReader.msg("msgGL_ExercErGabVazio"), "iGraf ", JOptionPane.ERROR_MESSAGE);
        new JanelaInfo(ResourceReader.msg("msgGL_ExercErGabVazio")); // igraf/moduloExercicio/visao/JanelaInfo.java
        return -2; // deu erro, n�o feche a janela de autoria de exerc�cio (JanelaExercicio)
        }
      } // if (IGraf.STR_MA_PARAM_Teacher!=null && IGraf.STR_MA_PARAM_Teacher.equals("EnvWebTEACHER"))


    // Can change page?
    boolean canChange = false;
    String iLM_PARAM_ChangePage = IGraf.getILM_PARAM_ChangePage(), iLM_PARAM_dontChangePage = IGraf.getILM_PARAM_dontChangePage();
    if (iLM_PARAM_ChangePage!=null && iLM_PARAM_ChangePage.equals("true")) // iLM 2.0
      canChange = true; // default=false: if absent => use as 'false' (do not change page when student send its answer)
    else
    if (iLM_PARAM_dontChangePage!=null && !iLM_PARAM_dontChangePage.equals("false")) // iLM 1.0
      canChange = true;
    if (igraf.IGraf.IS_DEBUG) System.out.println("[GL] canChange=" + canChange + "");

    // Has server URL?
    String serverURL = IGraf.getILM_PARAM_ServerToGetAnswerURL(); // return iLM_PARAM_ServerToGetAnswerURL from parameter "iLM_PARAM_ServerToGetAnswerURL"
    if (serverURL==null || serverURL=="") // iLM 2.0: not null => server address defined
      serverURL = IGraf.STR_MA_PARAM_addressPOST; // iLM 1.0: "MA_PARAM_addressPOST"
    if (serverURL.indexOf("http://")<0) { // relative addres => try to complete it
      System.out.println("[GL] sem http: " + serverURL);
      IGraf igrafApplet = IGraf.getInstanceIGraf();
      serverURL = igrafApplet.getCodeBase().toString() + serverURL;
      }
    System.out.println("[GL] Server address to get answer: " + serverURL); // endere�o PHP que tratar� a recep��o do arquivo

    if (serverURL==null || serverURL=="")
      return -1;
    // serverURL += "?MA_POST_Value=" + resultado;

    if (IGraf.STR_MA_POST_ExtraInfo!=null) {
      String str_ = decompoeGET_POST(IGraf.STR_MA_POST_ExtraInfo); // strEnvWebInfo); //
      strEnvWebInfo = "&envWebInfo=\""+str_+"\"";
      System.out.println("[GL] Aditional information in 'envWebInfo': '"+strEnvWebInfo+"'");
      }

    // NOT with applet!!!
    // strEnvWebDadosSis = URLEncoder.encode(Sistema.getPlataforma()+","+Sistema.getMRJVersion()+","+Sistema.homeUsuario());

    if (!IGraf.BOOL_SEND_ANSWER) { // igraf/IGraf.java: defined using the HTML parameter 'iLM_PARAM_SendAnswer'
      System.out.println("[GL] not allowed to send information to the server! (denied from parameter 'iLM_PARAM_SendAnswer')");
      return -1;
      }

    String strEnv = "MA_POST_Value=" + resultado + "&MA_POST_Archive=" + URLEncoder.encode(conteudoStr);
    urlConnStatic = criaConexaoCom(serverURL, strEnv, vetErros, ResourceReader.msg("msgGL_ExercicioErrConex"), "[GL] "); // URLConnection urlConnStatic
    //criaConexaoCom (String StringURL, String strContent, Vector vetErros, String msgErrorConex, String strAuxPre
    //ResourceReader.msg("msgGL_ExercicioErrConex") -> msgErrorConex

    if (urlConnStatic==null) {
      System.out.println("[GL] Error: the server address is not valid: " + serverURL);
      return -1;
      }
    else System.out.println("[GL] Server connection that will receive the student answer: " + urlConnStatic);

    try { // try new DataOutputStream from urlConnStatic.getOutputStream

      printout = new DataOutputStream(urlConnStatic.getOutputStream());
      printout.writeBytes(strEnv);
      printout.flush();
      printout.close();

      System.out.println("[GL] 3. printout=" + printout!=null ? printout.getClass().getName() : "<vazio>");
      System.out.println("[E] HttpURLConnection.getResponseCode = " + responseCodeFromHTML(urlConnStatic) + "");

    } catch (Exception e) { // from try new DataOutputStream from urlConnStatic.getOutputStream
      int resp = responseCodeFromHTML(urlConnStatic); // URLConnection urlConnStatic
      System.err.println("[GL] Error: while trying open DataOutputStream: server answer getResponseCode=" + resp + ": " + e.toString());
      System.out.println("[GL] serverURL=" + serverURL); System.out.println("[GL] urlConnStatic=" + urlConnStatic + ": " + printout);
      e.printStackTrace();
      }


    if (!canChange) // iLM_PARAM_ChangePage
      return 0; // do not change page => return now!
    System.out.println("Change page...");

    try { // try read from urlConn using InputStream
      InputStream is = urlConnStatic.getInputStream();
      input = new BufferedReader(new InputStreamReader(is));
      enderecoDevolvidoPHP = input.readLine(); // tenta pegar a p�g. devolvida pelo PHP chamado em "IGraf.STR_MA_PARAM_addressPOST" (via "echo" ou "print")
      System.out.println("[GL] URL sent back: "+enderecoDevolvidoPHP+".");
      input.close();

      if (!(IGraf.STR_MA_PARAM_Teacher!=null && IGraf.STR_MA_PARAM_Teacher.equals("EnvWebTEACHER")) ) {
        URL novaURL = new URL(enderecoDevolvidoPHP); // Mostra sa�da do URL usando o navegador (ao clicar no voltar: retorna ao applet)
        try {
          applet.getAppletContext().showDocument(novaURL);
        } catch (Exception e) {
          java.applet.Applet applet2 = new java.applet.Applet();
          //.out.println("[GL] static_iGraf="+igraf.IGraf.static_iGraf.num+" applet="+((IGraf)applet).num+" str="+enderecoDevolvidoPHP+" novaURL="+novaURL+": "+e
          applet2.getAppletContext().showDocument(novaURL);
          }
        System.out.println("[GL] It is 'model anser' sent by a teacher, do not get page with evaluation!"+"!");
        }
    } catch (Exception e) {
      String strUrlConn = urlConnStatic!=null ? "urlConnStatic.getURL()="+urlConnStatic.getURL()+" - urlConnStatic.toString()="+urlConnStatic.toString() : "urlConnStatic=null";
      System.out.println("[GL] Sorry, it was detected problems to connect to the server: " + e);
      System.out.println("    serverURL=" + serverURL);
      System.out.println("    " + strUrlConn);
      System.out.println("    URL address returned by the server = <"+enderecoDevolvidoPHP+">");
      int numMsgs = 1;
      if (enderecoDevolvidoPHP!=null && enderecoDevolvidoPHP!="")
        numMsgs = 2;
      String [] strPHP  = new String[1];
      String [] strMsgs = new String[numMsgs];
      strMsgs[0] = ResourceReader.msg("msgGL_ExercicioErrConexR"); // 
      if (enderecoDevolvidoPHP!=null && enderecoDevolvidoPHP!="") { // se houver alguma msg devolvida pelo PHP em 'IGraf.STR_MA_PARAM_addressPOST' anote-a
        strPHP[0]  = enderecoDevolvidoPHP;
        strMsgs[1] = ResourceReader.msgComVar("msgGL_ExercicioErrConexR2","OBJ",strPHP); // Erro ao tentar contatar o endere�o $OBJ$!
        }
      vetErros.addElement(new Integer(2)); // indica ser segunda msg de erro
      vetErros.addElement(strMsgs);        // anexa a msg de erro
      e.printStackTrace();
      }

    int numErros = vetErros.size(); // n�mero de msgs de erro
    if (numErros>0) {
      System.out.println("\n[GL] Result="+resultado+". Number of errors in transmission: "+numErros);
      }
    if (numErros>0) {
      String [] strMAux = new String[2*numErros];
      int tipo, cont=0;
      for (int i=0; i<numErros/2; i++) {
        tipo = ((Integer) vetErros.elementAt(2*i)).intValue();
        if (tipo==1)
          strMAux[cont++] = (String) vetErros.elementAt(2*i+1);
        else {
          String [] strAux = (String[]) vetErros.elementAt(2*i+1);
          strMAux[cont++] = strAux[0];
          if (strAux.length>1) strMAux[cont++] = strAux[1];
          }
        }
      String [] strMsgs = new String[cont+1];
      String strMsg = "";
      strMsgs[0] = ResourceReader.msg("msgGL_ExercicioErrConexAd");
      for (int i=0; i<cont; i++) 
        strMsg += strMsgs[i+1] = strMAux[i];

      // Ocorreu algum erro de conex�o, a p�gina que analisaria sua resposta n�o est� dispon�vel
      // JOptionPane.showMessageDialog(aux, strMsgs, "iGraf ", JOptionPane.ERROR_MESSAGE);
      new JanelaInfo(strMsg); // igraf/moduloExercicio/visao/JanelaInfo.java
      return -1;
      } // if (numErros>0)

    return 0;
    } // public static int enviarResultadoExercicio(Applet applet, String conteudoStr, int resultado)


  public static void mainD (String[] args) {
    getILMfile("http://localhost:8080", "/BDUpdaterWebApplication/arq.grf");
    }

  } // public class GravadorLeitor
