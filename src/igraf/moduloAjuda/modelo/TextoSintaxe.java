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
 * @description Help integrated to iGraf. Component to explain: a sintax of accepted expressions
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

package igraf.moduloAjuda.modelo;


import igraf.basico.io.ResourceReader;


public class TextoSintaxe extends JPanelBasisTopic {

 public TextoSintaxe () {
  super("manualStTextSinTitle"); // Sintaxe

  insereParagrafo("manualStTextSin_I_1");

  insereSubTitulo("manualStTextSin_1"); // Nota��o de express�es
  insereParagrafo("manualStTextSin_1_1");

  insereSubTitulo("manualStTextSin_2"); // Vari�veis
  insereParagrafo("manualStTextSin_2_1");

  insereSubTitulo("manualStTextSin_3"); // Operadores
  insereParagrafo("manualStTextSin_3_1");
  insereParagrafo("manualStTextSin_3_2");
  insereParagrafo("manualStTextSin_3_3");

  insereSubTitulo("manualStTextSin_4"); // Fun��es pr�-definidas
  insereParagrafo("manualStTextSin_4_1");
  insereParagrafo("manualStTextSin_4_2");
  insereParagrafo("manualStTextSin_4_3");
  insereParagrafo("manualStTextSin_4_4");
  insereParagrafo("manualStTextSin_4_5");
  insereParagrafo("manualStTextSin_4_6");

  insereSubTitulo("manualStTextSin_5"); // Par�metros
  insereParagrafo("manualStTextSin_5_1");
  }
/*
  super("Sintaxe");

  insereParagrafo("A fim de simplificar seu uso, no iGraf basta digitar a express�o matem�tica que representa a fun��o, por exemplo " +
  "pode-se digitar apenas \"x + 2\" para obter o gr�fico da fun��o \"f(x)=x+2\". Do mesmo modo, se digitar \"a*x + b\" o iGraf desenhar� " +
  "a fun��o de grau 1 podendo-se variar seus par�metros \"a\" e \"b\". Para isso veja a op��o \"Anima��o\". " +
  "Para conseguir acesso � lista de todos os par�metros para anima��o, quando o \"mouse\" estiver sobre a �rea de desenho, clique no bot�o " +
  "direito dele. \n" +
  "Abaixo est�o listadas as regras b�sicas para uso do iGraf.");

  insereSubTitulo("Nota��o de express�es");
  insereParagrafo("As express�es aceitas pelo iGraf devem usar nota��o infixa, ou seja, " +
      "um operador entre dois operandos, por exemplo: x + 6.   O operador de adi��o " +
      "est� entre dois operandos, o 'x' e o '6'.   Exce��o a essa regra � feita para o uso do menos " +
      "un�rio, como em -3.   Fun��es pr�-definidas ser�o aplicadas aos operandos que " +
      "estiverem entre par�nteses, como em tan(x^2 - 9)");

  insereSubTitulo("Vari�veis");
  insereParagrafo("A �nica vari�vel suportada pela vers�o atual do iGraf � a letra min�scula 'x'.   " +
      "Os gr�ficos exibidos na �rea de desenho s�o gr�ficos de f(x).   Assim, quando o " +
      "usu�rio digita na �rea de edi��o: \"x + 2\", o gr�fico visualizado ser� o de f(x) = x + 2." +
      "   N�o � necess�rio digitar \"f(x) =\" ou mesmo \"y =\"");

  insereSubTitulo("Operadores");
  insereParagrafo("As opera��es matem�ticas b�sicas no programa usam os operadores (+) para soma, (-) " +
      "para subtra��o, (*) para multiplica��o, (/) para divis�o e (^) para potencia��o.   " +
      "Veja um exemplo de express�o v�lida: x^2 + 2*x - (1/x).");
  insereParagrafo("Note que par�nteses s�o permitidos e t�m a mesma fun��o que teriam em uma express�o " +
      "matem�tica qualquer.  Na express�o do exemplo acima os par�nteses s�o desnecess�rios, " +
      "pois o iGraf segue as regras matem�ticas padr�o para preced�ncia de operadores e, " +
      "portanto, a divis�o ser� naturalmente executada antes da subtra��o. ");
  insereParagrafo("Os par�nteses tamb�m podem ser colocados dentro de outros par�nteses desde que o " +
      "usu�rio tenha o cuidado de fechar cada par�ntese que abrir.");

  insereSubTitulo("Fun��es pr�-definidas");
  insereParagrafo("O iGraf pode desenhar gr�ficos das fun��es abaixo listadas e de suas combina��es.");
  insereParagrafo("Trigonom�tricas: sin(x) para seno, cos(x) para cosseno, tan(x) para tangente, bem " +
      "como as suas inversas asin(x), acos(x) e atan(x).");
  insereParagrafo("Raiz quadrada: sqrt(x) para raiz quadrada.");
  insereParagrafo("Logar�tmica: ln(x) para logaritmo natural.");
  insereParagrafo("Exponencial: exp(x) para exponencial.");
  insereParagrafo("M�dulo: abs(x) para m�dulo.");

  insereSubTitulo("Par�metros");
  insereParagrafo("Al�m das fun��es e operadores acima, o iGraf suporta o uso de par�metros de anima��o.   " +
      "Esses valores s�o usados quando se deseja observar o comportamento do gr�fico de uma " +
      "fun��o quando seus coeficientes est�o variando em um " +
      "intervalo.   Assim, � poss�vel digitar na �rea de edi��o: a*x^2 + b*x + c, atribuir " +
      "intervalos de varia��o para a, b e c e visualizar uma anima��o na qual se pode analisar " +
      "a influ�ncia de cada coeficiente sobre o formato da curva.");
 */

 }
