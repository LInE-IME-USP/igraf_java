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
 * @description Help integrated to iGraf. Component to explain a menu item:
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


public class TextoConceito extends JPanelBasisTopic {

 public TextoConceito () {
  super("manualStTextConceptsTitle"); // Conceitos

  insereParagrafo("manualStTextConcepts_I_1");

  insereSubTitulo("manualStTextConcepts_1"); // Anima��o
  insereParagrafo("manualStTextConcepts_1_1");

  insereSubTitulo("manualStTextConcepts_2"); // Avalia��o Autom�tica
  insereParagrafo("manualStTextConcepts_2_1");

  insereSubTitulo("manualStTextConcepts_3"); // Gr�fico
  insereParagrafo("manualStTextConcepts_3_1");

  insereSubTitulo("manualStTextConcepts_4"); // Hist�rico
  insereParagrafo("manualStTextConcepts_4_1");

  insereSubTitulo("manualStTextConcepts_5"); // Servidor
  insereParagrafo("manualStTextConcepts_5_1");

  insereSubTitulo("manualStTextConcepts_6"); // Sess�o
  insereParagrafo("manualStTextConcepts_6_1");
  }

/*
  insereParagrafo("manualStTextConcepts_I_1");

  insereParagrafo("Veja abaixo uma lista dos conceitos b�sicos utilizados pelo iGraf");

  insereSubTitulo("Anima��o");
  insereParagrafo("Uma anima��o � uma seq��ncia de desenhos de gr�ficos sendo executada " +
      "rapidamente.   Cada gr�fico � exibido por uma fra��o de segundo e apagado " +
      "para que, em seguida, seja desenhado outro gr�fico ligeiramente diferente " +
      "causando a impress�o de movimento.");

  insereSubTitulo("Avalia��o Autom�tica");
  insereParagrafo("� a avalia��o realizada por um programa de computador.   Esse programa, " +
      "que � executado pelo servidor, � capaz de receber, ler e interpretar a resposta " +
      "do usu�rio comparando-a com valores pr�-definidos e emitir um parecer sobre sua corretude.");

  insereSubTitulo("Gr�fico");
  insereParagrafo("E o m�todo mais comum de visualiza��o de fun��es.   O gr�fico de uma fun��o " +
      "f consiste de todos os pontos(x,y) do plano coordenado tais que y = f(x) e x " +
      "est� no dom�nio de f.");

  insereSubTitulo("Hist�rico");
  insereParagrafo("� uma ferramenta implementada no iGraf que permite ao usu�rio rever e analisar " +
      "os passos que foram realizados para se atingir uma determinada configura��o da " +
      "�rea de desenho.   Atrav�s do hist�rico tamb�m � poss�vel analisar passagens da " +
      "sess�o que n�o s�o exibidas graficamente como, por exemplo, a(s) resposta(s) a um exerc�cio.");

  insereSubTitulo("Servidor");
  insereParagrafo("O computador a partir no qual o iGraf � executado e tornado dispon�vel pela Internet.   " +
      "Nessa m�quina s�o registrados os exerc�cios aos quais os usu�rios ter�o acesso e � " +
      "executada tamb�m a parte do programa que cuida da avalia��o autom�tica.");

  insereSubTitulo("Sess�o");
  insereParagrafo("Uma sess�o � um registro textual de todas as a��es realizadas pelo usu�rio.   A sess�o " +
      "pode ser gravada em arquivo se estiver em uso a vers�o aplicativo (para uso local) do iGraf ou " +
      "enviada ao servidor se o programa estiver em uso pela Internet.");
 */
 }
