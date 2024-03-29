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
 * @description Help integrated to iGraf. Component to explain a menu item: Animation
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

public class TextoAnimacao extends JPanelBasisTopic {


//manualStTextAnim = 

 public TextoAnimacao () {
  super("manualStTextAnimTitle"); // ResourceReader.msg("manualStTextAnimTitle")); // "3   Anima��o"

  // 
  // import igraf.moduloAjuda.visao.componentesDoTexto.Topico;
  // 
  // insereSubTitulo: insereTopico: igraf/moduloAjuda/visao/componentesDoTexto/Topico.java
  // insereParagrafo:               igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java

  insereParagrafo("manualStTextAnim_I_1");
  insereParagrafo("manualStTextAnim_I_2");
  insereParagrafo("manualStTextAnim_I_3");

  insereSubTitulo("manualStTextAnim_1"); // 3.1   Parar / Animar
  insereParagrafo("manualStTextAnim_1_1");
  insereParagrafo("manualStTextAnim_1_2");

  insereSubTitulo("manualStTextAnim_2"); // 3.2   Exibir Painel de Controle
  insereParagrafo("manualStTextAnim_2_1");

  insereSubTitulo("manualStTextAnim_3"); // 3.3   Exibir controle deslizante
  insereParagrafo("manualStTextAnim_3_1");
  insereParagrafo("manualStTextAnim_3_2");
  insereParagrafo("manualStTextAnim_3_3");

  insereSubTitulo("manualStTextAnim_4"); // 3.4   Iniciar rastro de fun��o

  insereParagrafo("manualStTextAnim_4_1");

  insereSubTitulo("manualStTextAnim_5"); // 3.5   Aumentar e Diminuir velocidade

  insereParagrafo("manualStTextAnim_5_1");

/*
  insereParagrafo("No iGraf, uma anima��o para a fun��o f(x) no intervalo [m, n] " +
            "� o desenho cont�nuo de v�rios gr�ficos de f(a), sendo 'a' o " +
            "par�metro de anima��o, ou seja, 'a' assume valores de m a n em " +
            "passos de 0.1 e a cada valor de 'a' um gr�fico � exibido. ");

  insereParagrafo("Para iniciar uma anima��o basta digitar na �rea de edi��o uma " +
            "express�o que contenha um par�metro de anima��o como a.x^2 ou c.x + m.   " +
            "A primeira express�o come�a a executar a anima��o imediatamente ap�s " +
            "o comando para desenhar.   Por padr�o, o par�metro 'a' varia no intervalo " +
            "[-1, 1].  A segunda express�o, no entanto, s� funcionar� quando o usu�rio " +
            "habilitar o uso dos par�metros 'c' e 'm'; esses par�metros, por padr�o " +
            "t�m valor fixo igual a 1.");

  insereParagrafo("Os par�metros de anima��o usados pelo iGraf s�o:  a, b, c, k, m e n.   " +
            "Clique com o bot�o direito do mouse na �rea de desenho para alterar o valor " +
            "de cada par�metro individualmente");

  insereSubTitulo("3.1   Parar / Animar");

  insereParagrafo("P�ra ou reinicia todas as anima��es editadas at� o momento.   As " +
            "anima��es que estiverem ocultas tamb�m obedecer�o a esse comando.");

  insereParagrafo("Toda anima��o, por padr�o, ser� executada por tempo indefinido.   " +
            "Cabe ao usu�rio interferir nesse comportamento atrav�s dessa op��o, " +
            "parando ou reiniciando o movimento.");

  insereSubTitulo("3.2   Exibir Painel de Controle");

  insereParagrafo("Quando est�o ocorrendo v�rias anima��es na tela o visual fica muito " +
            "\"carregado\" e pode ser desej�vel parar algumas delas... ou, at� mesmo, " +
            "parar todas.   O iGraf oferece um painel de controle atrav�s do qual � " +
            "poss�vel selecionar as anima��es que podem ou n�o ser executadas em um " +
            "dado momento.   Esta op��o exibe, ou oculta, esse painel. ");

  insereSubTitulo("3.3   Exibir controle deslizante");

  insereParagrafo("� poss�vel, ao usu�rio, fazer com que uma anima��o seja executada " +
            "passo a passo.   Para isso, basta parar a anima��o e selecionar esta " +
            "op��o.   Ser� exibido um controle deslizante que permitir� a intera��o " +
            "com o desenho dinamicamente.");

  insereParagrafo("Esse controle permite ao usu�rio interagir com o desenho usando o mouse " +
            "(clicando no bot�o deslizante), clicando nas setas direcionais (� direita " +
            "e � esquerda) ou ainda, inserindo valores diretamente no campo de texto � direita.");

  insereParagrafo("� importante destacar que essa op��o s� funciona se a anima��o estiver parada.");

  insereSubTitulo("3.4   Iniciar rastro de fun��o");

  insereParagrafo("Permite ao usu�rio escolher entre visualizar todos os gr�ficos gerados durante a " +
            "anima��o ou apenas o �ltimo.  Por padr�o, fica na tela apenas o �ltimo gr�fico.  ");

  insereSubTitulo("3.5   Aumentar e Diminuir velocidade");

  insereParagrafo("Op��es que permitem ao usu�rio variar a velocidade de execu��o da anima��o.");
*/
  }


 }
