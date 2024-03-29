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
 * @description Control do expressions
 * 
 * @see src/igraf/moduloCentral/visao/desenho/DesenhoAnimacao.java: 'verificaFuncao(...)' calls 'Analisa.sintaxeCorreta(...)'
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloSuperior.controle.entrada;


import igraf.basico.io.ResourceReader;

import java.util.StringTokenizer;


public class Analisa {

  // Defini��o dos tipos aceitos pelo programa
  private static final int
    NUMERO   = 1,
    OPERADOR = 2,
    FUNCAO   = 3,
    ABRE     = 4,
    FECHA    = 5,
    MENOS    = 6;

  private static final double MATHE = Math.E; // "2.7182818284590452354" - "2.7183"; // Math constant

  // vetores de subtipos aceitos pelo programa
  private static String []
    t1 = {"x", "y", "@", "p", "e", "d", "#", "$", "?", "&", "!"},
    t2 = { "=", "+", "*", "/", "^"},
    t3 = {"sqrt", "sin", "cos", "ln", "exp", "arcsin", "arccos", 
    "tan", "arctan", "abs", "sec", "csc", "cot", "log2", "log10"};

  private static String message, messageError = "";

  //- Vetores de possibilidades de entrada -

  /**
   * Ap�s a leitura de um NUMERO, s� ser�o aceitos operadores,
   * e fecha-par�ntese. O sinal de menos (-) � tratado � parte
   * pelo fato de agir ora como operador bin�rio e ora como un�rio.
   */
  private static int [] p1 = {OPERADOR, FECHA, MENOS};

  /**
   * Ap�s a leitura de um OPERADOR, seja ele un�rio ou bin�rio, s�
   * ser�o aceitos n�meros, fun��es (previstas em t3) e o abre-par�ntese.
   */
  private static int [] p2 = {NUMERO, FUNCAO, ABRE};

  /**
   * Ap�s a leitura de uma FUNCAO � sempre esperado um abre-par�ntese.
   */
  private static int [] p3 = {ABRE};

  /**
   * No in�cio de uma express�o ou ap�s a leitura de um ABRE-par�ntese
   * a situa��o � a mesma e s� s�o aceitos n�meros, fun��es, outro
   * abre-parentese ou o menos un�rio.
   */
  private static int [] p4 = {NUMERO, FUNCAO, ABRE, MENOS};

  /**
   * Ap�s a leitura de um FECHA-par�ntese s� ser�o permitidos operadores
   * ou outro fecha-par�ntese.
   */
  private static int [] p5 = {OPERADOR, MENOS, FECHA};

  // A possibilidade p6 (MENOS) � a mesma que p2 e, portanto, 
  // n�o precisa ser explicitamente definida.

  /** O vetor que cont�m a lista de tipos que podem ser aceitos a cada momento */
  private static int [] possibilidadeAtual;

  /** 
   * Vari�vel de controle para monitorar a consist�ncia entre os 
   * pares de par�nteses utilizados em uma express�o.
   */
  private static int parentese; // static?

  public static String result; // static?

  private static String [] listaParametros = {"a", "b", "c", "k", "m", "n"};


  // Check the expression entered in "input text field" if it is correct to draw. Returns: 'true' or 'false'
  // In case of error: it define "messageError" (otherwise it is "")
  public static boolean verificaFuncao (String funcao) {
   int i = funcao.indexOf('[');
   
   //QUARENTENA: 04/06/2013 - com o if abaixo aceitava qq coisa comecando com "[", como "[nada]"
   //DEBUG: if (i == 0) {    return true;    }

   if (i > 0)
    funcao = funcao.substring(0, i);

   if (!sintaxeCorreta(funcao)) {
    String str = ResourceReader.msg("daErroOperador"); // Fun��o com operador ou operando inv�lido
    setMessage(str + ": " + funcao);
    // messageError = "invalid operator or operand";
    return false;
   }
   return true; // Analisa.getResult();
  }


  //TODO: Aceitando '[(1,2), (2,3)]' ou 'z'!!??

  /**
   * Verifica se a string <b>str</b> representa uma express�o matem�tica.
   * @see igraf/moduloSuperior/controle/entrada/EntradaExpressaoController.java
   *      igraf/moduloSuperior/controle/entrada/Analisa.verificaFuncao(String): above
   * @return true se <b>s</b> for uma express�o matem�tica aceita pelo iGraf
   */
  public static boolean sintaxeCorreta (String str) {
    String s0 = str;
    if (str==null || str.trim().length()==0) {
       String strErr = ResourceReader.msg("msgErrFcVazia"); // error: empty function, it is impossible to draw
       setMessage(strErr);
       // messageError = "empty function";
       return false;
       }

    str = str.trim();

    result = "";
    parentese = 0;
    possibilidadeAtual = p4;

    str = str.replace(',', '.');

    int k = str.indexOf('"');
    if (k > -1)
      str = str.substring(0, k);

    StringTokenizer st = new StringTokenizer(str, "()=+*/-^ ", true);

    String token;
    int tipo = 0;

    while (st.hasMoreTokens()) {
       token = st.nextToken();
       if (token.equals(" "))
          continue;
       if (token.equals("a"))
          token = "@";
       else
       if (token.equals("b"))
          token = "#";
       else
       if (token.equals("c"))
          token = "$";
       else
       if (token.equals("k"))
          token = "?";
       else
       if (token.equals("m"))
          token = "&";
       else
       if (token.equals("n"))
          token = "^";
       else
       if (token.equals("P") || token.equals("p"))
          token = String.valueOf(Math.PI); // Math.PI = 3.14159265358979323846
       else
       if (token.equals("D") || token.equals("d"))
          token = String.valueOf(Math.PI/180);
       else
       if (token.equals("E") || token.equals("e"))
          token = String.valueOf(Math.E);
       // s = "2.7183"; // Math.E = 2.7182818284590452354
  
       tipo = determinaTipo(token);
       if (tipo < 0)
          return false;

       if (!aceitaTipo(tipo)) {
          setMessage(ResourceReader.msg("msgASerrNaoEsperado") +": " + token); // Operador ou operando n�o esperado:
          // messageError = "operator or operand unexpected: " + token;
          return false;
          }
       result += token;
       } // while (st.hasMoreTokens())
 
    // confronta o n�mero de abre-par�nteses com o de fecha-par�nteses 
    if (parentese != 0) {
       String strErr = ResourceReader.msg("msgASerrParenteses"); // "Erro em par�nteses: mal formados"
       setMessage(strErr);
       // messageError = "parentheses mismatched";
       return false;
       }
 
    if (tipo != NUMERO & tipo != FECHA) {
       String strErr = ResourceReader.msg("msgASerrFinal"); // T�rmino incorreto da express�o
       setMessage(strErr);
       // messageError = "invalid end to expression";
       return false;
       }

    setMessage("");
    return true;
    }


  /**
   * Recebe uma parte de uma express�o matem�tica e determina de que tipo
   * se trata.   Os tipos poss�veis s�o: 1.n�meros, 2.operadores, 3.fun��es, 
   * 4.abre-par�ntese, 5.fecha-par�ntese e 6.menos-un�rio.
   * @param token: uma parte da express�o matem�tica
   * @return o n�mero inteiro associado ao tipo reconhecido pelo programa
   * e zero em caso de tipos desconhecidos.
   */
  private static int determinaTipo (String token) {    
    String erro = ResourceReader.msg("msgASerrOperInv")+": " + token; // ;"Operador ou operando n�o v�lido no iGraf
    if (Character.isDigit(token.charAt(0))) {
       try {
        Double.valueOf(token).doubleValue();
       } catch (Exception e) {
         setMessage(erro);
         // messageError = erro;
         return -1;
         }
       return 1;
       }
  
    for (int j = 0; j < t1.length; j++) {
       if (token.compareTo(t1[j])==0)
          return 1;
       }

    for (int j = 0; j < t2.length; j++) {
       if (token.compareTo(t2[j])==0)
         return 2;
       }
  
    for (int j = 0; j < t3.length; j++) {
       if (token.compareTo(t3[j])==0)
         return 3;
       }
  
    if (token.compareTo("(")==0) {
       parentese++;
       return 4;
       }

    if (token.compareTo(")")==0) {
       parentese--;
       return 5;
       }

    if (token.compareTo("-")==0)
       return 6;
  
    setMessage(erro);
    // messageError = erro;
    return -1;
    } // static int determinaTipo(String token)

  public static String getResult () {
    return result;
    }


  /**
   * Recebe o inteiro <b>tipo</b> que representa um tipo de estrutura
   * provavelmente aceita pelo programa.   Verifica se essa estrutura 
   * pode ser aceita na leitura atual.
   * @param tipo 
   * @return true no caso em que esse tipo pode ser aceito
   */
  private static boolean aceitaTipo (int tipo) {
    for (int i = 0; i < possibilidadeAtual.length; i++) {
      if (tipo == possibilidadeAtual[i]) {
        atualizaPossibilidade(tipo);
        return true;
      }
    }
    return false;
    }


  /**
   * Recebe o tipo lido mais recentemente e determina qual �
   * o vetor de possibilidades a ser usado para a verifica��o
   * na pr�xima leitura.
   * @param tipo
   */
  private static void atualizaPossibilidade (int tipo) {
    switch (tipo) {
      case NUMERO  : possibilidadeAtual = p1;  break;
      case OPERADOR: possibilidadeAtual = p2;  break;
      case FUNCAO  : possibilidadeAtual = p3;  break;
      case ABRE    : possibilidadeAtual = p4;  break;
      case FECHA   : possibilidadeAtual = p5;  break;
      case MENOS   : possibilidadeAtual = p2;  break;
      }
    }


  private static void setMessage (String strError) {
    messageError = strError;
    message = strError;
    }


  public static String getErrorMessage () {
    return messageError; // message;
    }


  /**
   * Recebe a string s, supostamente uma express�o matem�tica e verifica se cont�m uma das tr�s constantes: <b>d</b>,
   * fator para convers�o de radiano para grau, <b>e</b>, o n�mero de Euler ou <b>p</b>, pi.
   * Devolve uma string com essas constantes substitu�das pelos respectivos valores num�ricos caso sejam encontradas em s.
   * @param String str
   * @return
   */
  public static String verificaConstante (String str) {
    if (str.equalsIgnoreCase("D"))
      str = String.valueOf(Math.PI/180);
    else
    if (str.equalsIgnoreCase("E"))
      str = MATHE + ""; // "2.7183" -> 2.7182818284590452354
    else
    if (str.equalsIgnoreCase("P"))
      str = String.valueOf(Math.PI);
    return str;
    }


  public static boolean temParametro (String str) {
    StringTokenizer st = new StringTokenizer(str, " +-*/^()");
    while (st.hasMoreTokens())
      if (ehParametro(st.nextToken()))
        return true;
    return false;
    }


  private static boolean ehParametro (String token) {
    for (int i = 0; i < listaParametros.length; i++)
      if (token.equals(listaParametros[i]))
        return true;
    return false;
    }


  public static boolean ehPoligono (String pol) {
    if (pol.indexOf('[') == 0)
      return true;
    return false;
    }


  public static void main (String [] args) {
    boolean b;
    b = Analisa.verificaFuncao("x [0,1] \"coment");
    System.out.println(b + "  " + possibilidadeAtual[0]);
    }

  }
