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
 * @description Help integrated to iGraf. Component to explain a menu item: Calculus
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


public class TextoCalculo extends JPanelBasisTopic {

 public TextoCalculo () {
  super("manualStTextCalcTitle"); // "2   C�lculo"

  insereParagrafo("manualStTextCalc_I_1");

  insereSubTitulo("manualStTextCalc_1"); // 2.1   Visualizar Derivada
  insereParagrafo("manualStTextCalc_1_1");

  insereSubTitulo("manualStTextCalc_2"); // 2.2   Visualizar Tangente
  insereParagrafo("manualStTextCalc_2_1");
  insereParagrafo("manualStTextCalc_2_2");
  insereParagrafo("manualStTextCalc_2_3");
  insereParagrafo("manualStTextCalc_2_4");

  insereSubTitulo("manualStTextCalc_3"); // 2.3   Visualizar Integral Indefinida
  insereParagrafo("manualStTextCalc_3_1");

  insereSubTitulo("manualStTextCalc_4"); // 2.4   Visualizar Integral Definida
  insereParagrafo("manualStTextCalc_4_1");
  insereParagrafo("manualStTextCalc_4_2");
  }

/*
  insereParagrafo("Menu de ferramentas associadas ao C�lculo Diferencial e Integral");

  insereSubTitulo("2.1   Visualizar Derivada");
  insereParagrafo("Permite ao usu�rio tra�ar o gr�fico da fun��o derivada f'(x) para " +
            "a fun��o f(x) cuja express�o se encontra na �rea de edi��o.   Caso " +
            "n�o haja uma express�o na �rea de edi��o, digite uma ou selecione uma " +
            "curva clicando sobre a mesma.");

  insereSubTitulo("2.2   Visualizar Tangente");
  insereParagrafo("Exibe uma janela na qual � poss�vel selecionar uma fun��o f(x) na lista " +
            "de fun��es editadas pelo usu�rio.   Uma vez selecionada tal fun��o, o " +
            "usu�rio poder� digitar valores reais para x e visualizar a reta tangente " +
            "a f(x) no ponto (x, f(x)).   Clique no campo de entrada de valores de x e " +
            "pressione uma das teclas direcionais (\"up\" ou \"down\") no teclado do seu " +
            "computador para ajustar dinamicamente o valor de x.");
  insereParagrafo("� poss�vel selecionar, caso exista, uma fun��o diferente de f(x), digamos g(x), " +
            "e realizar a mesma opera��o que foi feita para f(x).   A tangente a f(x) � " +
            "desenhada e calculada de forma independente daquela que est� associada a g(x), " +
            "isso permite a an�lise de v�rias tangentes a v�rias fun��es simultaneamente.");
  insereParagrafo("O tra�ado da reta tangente � sempre feito automaticamente, ou seja, assim  que " +
            "o usu�rio modifica o valor de x o gr�fico � desenhado.  Esse comportamento, no " +
            "entanto, permite apenas a visualiza��o de uma reta tangente a cada curva por vez.  " +
            "Se o usu�rio desejar analisar o comportamento de v�rias retas tangentes a pontos " +
            "distintos de uma mesma curva, dever� marcar a op��o \"V�rias Tangentes\".   Assim " +
            "que tiver selecionada uma tangente de interesse, dever� clicar em \"Fixar Tangente\".");
  insereParagrafo("O iGraf oferece ainda uma op��o de visualiza��o das tangentes a uma fun��o " +
            "atrav�s de uma anima��o.  Por padr�o, os limites dessa anima��o s�o os valores " +
            "extremos v�siveis do eixo x.   Para visualizar tal anima��o, basta marcar a op��o " +
            "\"Anima��o Tangente\".");

  insereSubTitulo("2.3   Visualizar Integral Indefinida");
  insereParagrafo("Desenha o gr�fico da antiderivada F(x) da fun��o f(x) que est� na �rea de edi��o.   " +
            "Por padr�o, o ponto de corte de F(x) no eixo das ordenadas � zero.   Uma " +
            "caracter�stica importante dessa curva � que ela � calculada numericamente.  Assim, " +
            "embora o usu�rio possa visualizar seu gr�fico �, na vers�o atual do programa, imposs�vel " +
            "recuperar a express�o matem�tica que o gera.");

  insereSubTitulo("2.4   Visualizar Integral Definida");
  insereParagrafo("Exibe uma janela na qual � poss�vel selecionar na lista de fun��es editadas aquelas " +
            "que ser�o usadas para delimitar verticalmente a regi�o de integra��o.");

  insereParagrafo("O usu�rio deve definir o intervalo de integra��o digitando os valores diretamente " +
            "nos campos de texto em \"Intervalo de Integra��o\", caso n�o o fa�a, ser� usado o " +
            "intervalo padr�o [0..1].");
 */

 }
