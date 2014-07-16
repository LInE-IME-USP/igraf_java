/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author RP, LOB
 *
 * @description
 * @see
 *
 * Created on 09/01/2012, 18:03:58
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 */

package igraf.basico.io;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Properties;
import java.util.Vector;

import igraf.basico.util.Configuracoes;
import igraf.moduloArquivo.Sistema; // in 'boolean isFile(String)'
import igraf.IGraf; // in 'String setConfig(IGraf igraf, String [] args)'

public class ResourceReader {

  private static ResourceBundle resourceBundle;

  static Properties Prop = null;

  private static String data_cfg = ""; // data de criação do arquivo de configuração
  public static String lingua = "", pais = "";
  public static int PORTUGUESE = 0, ENGLISH = 1;

  // Arquivo de configuracoes do iGraf
  public static final String ARQUIVO_CONF = "igraf.cfg";

  /**
   * Construtor default que utiliza o "locale" padrão...
   * no nosso caso pt_BR
   */
  public ResourceReader () {
    Locale l = Locale.getDefault();
    resourceBundle = ResourceBundle.getBundle("igraf.basico.resource/StringsTable", l);
    //T System.out.println("ResourceReader.java: ResourceReader(): 1 **************** " + resourceBundle);
    }

  public ResourceReader (String table) {
    resourceBundle = ResourceBundle.getBundle(table);
    //T System.out.println("ResourceReader.java: ResourceReader(String): 2 **************** " + resourceBundle);
    }


 // From:
 // 1. igraf.IGraf.init() -> igraf.basico.io.ResourceReader.setLanguage(...)
 // 2. igraf.IGraf.changeLanguage(...) -> igraf.basico.io.ResourceReader.setLanguage(...)
 //    igraf.IGrafController.filtrarEventoEntrada(IGrafController.java:35) -> igraf.IGraf.changeLanguage(...)
 public static void setLanguage (int language) {
   Locale l = null;
   //T System.out.println("ResourceReader.java: setLanguage() **************** language=" + language);
   //T try{ String strA=""; System.out.print(strA.charAt(3)); } catch (Exception e) { e.printStackTrace(); }

   if (language == PORTUGUESE) l = new Locale("pt", "BR");
   if (language == ENGLISH)    l = new Locale("en", "US");

   resourceBundle = ResourceBundle.getBundle("igraf.basico.resource/StringsTable", l);
   }


  // Evitar uma msg de erro por uma string faltante aparecer muitas vezes na tela
  private static Vector vecErros = new Vector();

  public static String msg (String value) {
   try {
    return resourceBundle.getString(value);
   } catch (java.util.MissingResourceException mre) { // java.util.MissingResourceException
    System.err.println("Erro: falta a mensagem '"+value+"' em 'igraf/resource/StringsTable*.properties'!");
if (value.equals("Conceitos")) { mre.printStackTrace(); return value; }
    if (!vecErros.contains(value)) {
     if (IGraf.DEBUG_TRACKRESOURCE)
      mre.printStackTrace();
     vecErros.addElement(value);
     return value; // "Falta mensagem: "+value;
     }
    return value;
   } catch (Exception e) { // java.util.MissingResourceException
    System.err.println("Error: I could not find a String in 'igraf/basico/resource/StringsTable*.properties': "+value+"': "+e);
    if (IGraf.DEBUG_TRACKRESOURCE)
       e.printStackTrace();
    return value; // "Falta mensagem: "+value;
    }
   }


  public static String msg (String stringMsg, String strVar, String strValor) {
    String [] strValor1 = new String[1];
    strValor1[0] = strValor;
    // return msgComVar(msg(stringMsg), strVar, strValor1);
    return msgComVar(stringMsg, strVar, strValor1);
    }


  // Processa mensagens com variáveis, do tipo "Arquivo $ARQ$ foi gravado com sucesso"
  public static String msgComVar (String strTexto, String strVar, String [] strValor) {
    java.util.StringTokenizer strToken = new java.util.StringTokenizer(msg(strTexto), "$");
    String strFinal = "";
    int prox = 0;
    while (strToken.hasMoreTokens()) {
      String strAux = strToken.nextToken();
      if (strAux.equals(strVar)) { // se token estiver com valor igual ao de "strVar", troque-o pelo conteúdo em "strValor[]"
          String str = strValor != null ? strValor[prox++] : "";
          strFinal = strFinal+str; // concatena com valor da variável (troca "strVal" por "strValor"
          }
      else {
          strFinal = strFinal+strAux;   // concatena com o texto
          }
      }
    return strFinal;
    }

  // Formato arquivo de configurações do iGraf
  // # iGraf: http://www.matematica.br
  // # versao: 1.9.1
  // # created: data
  // # modified: data
  // lang = pt
  // background = contrast1

  // Anota configuracoes no arquivo de configuração: 'igraf.cfg'
  public static void setConfig () {
    String [] str_linhas;
    String linha;
    String str_arquvio = "";
    String data_hoje = DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis()));
    if (data_cfg==null || data_cfg=="")
       data_cfg = data_hoje;
    str_arquvio = "# versao: " + Configuracoes.getVersion() + "\n" +
                "# created: " + data_cfg + "\n" +
                "# modified: " + data_hoje + "\n" ;
    try {
    str_arquvio +=
                "user.name=" + Prop.getProperty("user.name") + "\n" +
                "java.version=" + Prop.getProperty("java.version") + "\n" +
                "java.vendor=" + Prop.getProperty("java.vendor") + "\n" +
                "os.name=" + Prop.getProperty("os.name") + "\n" ;
    } catch (Exception e) { }
    // "=" + Prop.getProperty("") + "\n" +
    }

  // Define configuracoes a partir de arquivo de configuração: 'igraf.cfg'
  public static void getConfig () {
    String [] str_linhas = null;
    String linha;
    //str_linhas = Sistema.readFileFromHDlines(ARQUIVO_CONF, "configuracoes");
    if (str_linhas==null) {
       System.out.println("Erro: arquivo de configurações '"+ARQUIVO_CONF+"' ainda está vazio");
       return;
       }
    try {
       linha = str_linhas[0];
       if (linha.trim().equals(Configuracoes.MARCA_IGRAF)) {
        System.err.println("Arquivo de configurações inválido: "+ARQUIVO_CONF);
        }
    } catch (Exception e) { System.err.println("ResourceReader[168] => Erro: no carregamento do arquivo de configuracoes "+ARQUIVO_CONF+": "+e); }
    StringTokenizer tokens = null;
    String item = null;
    int tam = str_linhas!=null ? str_linhas.length : 0;
    if (Prop==null) Prop = System.getProperties(); //

    // #
    for (int i=1; i<3; i++) try {
      linha = str_linhas[i];
      item = linha.substring(0,9);
      if (item.equals("# created")) {
         data_cfg = linha.substring(10,linha.length()); // pega data de criação
         Prop.setProperty("created",data_cfg);
         }
      } catch (Exception e) { System.err.println("Erro: configuracao: "+i+": "+e); }

    // =
    for (int i=0; i<tam; i++) try {
      tokens = new StringTokenizer(str_linhas[i],"=",false);
      item = tokens.nextToken().trim();
      //System.err.println(" - "+item);
      if (item.equals("lang")) {
         lingua = tokens.nextToken().trim(); // define lingua
         Prop.setProperty("user.language",lingua);
         }
      else
      if (item.equals("background")) {
         item = tokens.nextToken().trim(); // define lingua
         Prop.setProperty("background",data_cfg);
         //if (item.equals("contrast1")) AreaDeDesenho.setContraste(true);
         //System.err.println("contraste=|"+item+"| "+AreaDeDesenho.getContraste());
         }
      } catch (Exception e) { System.err.println("Erro: configuracao: "+i+": "+e); }
    }


  // Decompõe: 'lang=pt_BR' em String("pt","BR")
  private static boolean decompoeConfig (String str) {
    //- System.out.println("src/igraf/basico/io/ResourceReader.java: decompoeConfig("+str+"): inicio");
    if (str==null)
       return false;
    StringTokenizer tokens = new StringTokenizer(str,"=");
    String item;
    int tam_item;

    //- System.out.println(" [RR.decompoeConfig] #tokens="+tokens.countTokens());
    if (tokens.hasMoreTokens()) {
       item = tokens.nextToken();
       //- System.out.println("[RR.decompoeConfig] item="+item);
       if (item==null) return false;
       if (item.equals("lang") && tokens.hasMoreTokens()) {
        // pegou 'pt_BR'
        item = tokens.nextToken();
        tam_item = item.length();
        //- System.out.println("[RR.decompoeConfig] item="+item+" #item="+tam_item);
        if (tam_item>2) {
           // é da forma: 'pt_BR'
           lingua = item.substring(0,2); //
           if (tam_item>4)
              pais = item.substring(3,tam_item).toUpperCase(); //
           //- System.out.println("[RR.decompoeConfig] lingua="+lingua+" pais="+pais);
           return true;
           }
        else {
           // é da forma: 'pt'
           lingua = item.substring(0,2); //
           // System.out.println("[B.decompoeConfig] lingua="+lingua+" [pais="+pais+"]");
           return true;
           }
        }
       else
       if (item.equals("bg") && tokens.hasMoreTokens()) {
        // pegou 'pt_BR'
        item = tokens.nextToken();
        if (item!=null && item.equals("contrast1")) // Bundle.msg("contraste")
           ; // empty action in case of 'bg=contrast1'
        }
       else { // problema: veio 'lang='
        return false; // new String[2];
        }
       }
    return false; // new String[2];
    } // boolean decompoeConfig(String str)


  // Define location and file to be loaded:
  // * if exists 'lang=...', try to load this Location;
  // * if there exists a valid file name, return it to IGraf.IGraf(String[]) to load it
  public static String setConfig (IGraf igraf, String [] args) {
    // lang=pt; lang=en; lang=es
    String fileName = null;
    int i = -1;
    //- System.out.println("src/igraf/basico/io/ResourceReader.java: setConfig(...): #args = " + ((args==null) ? ""+0 : ""+args.length) + "");
    if (lingua==null || lingua=="") // evita sobrescrever definicao de 'igraf.cfg'
       lingua = "pt"; // default
    pais   = "BR";
    if (args!=null && args.length>0) {
       String item;
       for (i=0; i<args.length; i++) {
         item = args[i].toLowerCase().trim(); // do no use 'args[i].toLowerCase().trim()' it does not work in Unix/Linux!!
         //- System.out.println(" ("+i+","+item+") ");
         try {
           if (decompoeConfig(item)) {
              //- System.out.println(" <- OK");
              }
	   else { // try it as it was a file
              if (fileName==null && Sistema.isFile(item)) { // get only the first file, ignore the rest
                 fileName = item; // it is a file
                 }
              //- System.out.println("ResourceReader.java: setConfig(): " + item + " fileName="+fileName);
              }
         } catch (Exception e) { System.err.println("Erro: leitura de parametros para configuracao: "+e);
           e.printStackTrace(); }
         } // for (i=0; i<args.length; i++)
       }
    try {
      //-
      Locale loc = new Locale(lingua,pais);
      Locale.setDefault(loc);

      // iGraf: interative Graphic on the Internet
      System.out.println("\n .: iGraf : "+msg("igraf")+" :.\n    " + msg("msgVersao") + ": " + Configuracoes.getVersion());

      int indexLanguage = -1;
      if (loc!=null) {
         if (loc.toString().equals("pt_BR")) indexLanguage = 0; // Locale("pt", "BR")
         else
         if (loc.toString().equals("en_US")) indexLanguage = 1; // Locale("en", "US")

         // Propagate the change all over the iGraf
         if (indexLanguage>-1)
	    igraf.changeLanguage(indexLanguage);
            // does: 'setLanguage(indexLanguage);' with 'resourceBundle=ResourceBundle.getBundle("igraf.basico.resource/StringsTable", loc);'

         }

    //- System.out.println("ResourceReader.java: setConfig(): loc=" + loc + ", indexLanguage=" + indexLanguage);
    } catch (Exception e2) { e2.printStackTrace(); }

    return fileName;

    } // public static void setConfig(String [] args)


  //
  public static void defineBundle (boolean chamaDefine) {
    String msg_nome_default = "StringsTable", msg_nome,
         lingua_aux, pais_aux;
    lingua_aux = (lingua!=null && lingua.length()>0 && lingua.charAt(0)!='_') ? "_"+lingua : lingua;
    pais_aux = (pais!=null && pais.length()>0 && pais.charAt(0)!='_') ? "_"+pais : pais;

    // Com linha abaixo => aplicativo tenta entrar no prim. 'bundle=ResouceBundle...' com 'StringsTable_pt_BR_pt_BR"
    msg_nome = "StringsTable"+lingua_aux.toLowerCase()+pais_aux.toUpperCase();

    System.out.println("\n .: iGraf : "+msg("igraf")+" :.\n"); // iGraf: Gráficos Interativos na Internet

    // 'StringsTable*.properties'
    try { //try1
      // 'StringsTable_lingua_pais.properties'
      try { //try2
        resourceBundle = ResourceBundle.getBundle(msg_nome);
        //-
System.out.println("1: msg_nome="+msg_nome);
      } catch (Exception e_lingua_pais) { // (java.util.MissingResourceException mre)
        // Tente agora só com lingua
        msg_nome = "StringsTable"+lingua_aux.toLowerCase();
        // 'StringsTable_lingua.properties'
        try { //try3
          resourceBundle = ResourceBundle.getBundle(msg_nome);
          //-
System.out.println("2: msg_nome="+msg_nome);
        } catch (Exception e_lingua) { // (java.util.MissingResourceException mre)
          // msgGeomInteratInternet = Geometria Interativa na Internet

          try { //try4
            // usualmente entra aqui: ao fazer 'java igraf.IGraf ...'
            resourceBundle = ResourceBundle.getBundle("igraf/resource/StringsTable"); // ./igraf/resource/StringsTable_en_US.properties
            //-
System.out.println("3: ResourceBundle");
          } catch (Exception e) {
            System.err.println(" Tenta: tentaResourceURL:"+" msg_nome="+msg_nome+": "+e);
            // tentaResourceURL(msg_nome);
            e.printStackTrace();
            } //try-catch4

         } //try-catch3

      } //try-catch2

    } catch (java.util.MissingResourceException mre) {
      System.err.println("Erro: RB: "+mre);
      // tentaResourceURL("Messages");
      } //  catch (java.util.MissingResourceException mre)

    } // void defineBundle(boolean chamaDefine)

    //---

  }
