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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.modelo;


import igraf.basico.io.ResourceReader;


public class TextoCalculo extends JPanelBasisTopic {

 public TextoCalculo () {
  super("manualStTextCalcTitle"); // "2   Cálculo"

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
  insereParagrafo("Menu de ferramentas associadas ao Cálculo Diferencial e Integral");

  insereSubTitulo("2.1   Visualizar Derivada");
  insereParagrafo("Permite ao usuário traçar o gráfico da função derivada f'(x) para " +
            "a função f(x) cuja expressão se encontra na área de edição.   Caso " +
            "não haja uma expressão na área de edição, digite uma ou selecione uma " +
            "curva clicando sobre a mesma.");

  insereSubTitulo("2.2   Visualizar Tangente");
  insereParagrafo("Exibe uma janela na qual é possível selecionar uma função f(x) na lista " +
            "de funções editadas pelo usuário.   Uma vez selecionada tal função, o " +
            "usuário poderá digitar valores reais para x e visualizar a reta tangente " +
            "a f(x) no ponto (x, f(x)).   Clique no campo de entrada de valores de x e " +
            "pressione uma das teclas direcionais (\"up\" ou \"down\") no teclado do seu " +
            "computador para ajustar dinamicamente o valor de x.");
  insereParagrafo("É possível selecionar, caso exista, uma função diferente de f(x), digamos g(x), " +
            "e realizar a mesma operação que foi feita para f(x).   A tangente a f(x) é " +
            "desenhada e calculada de forma independente daquela que está associada a g(x), " +
            "isso permite a análise de várias tangentes a várias funções simultaneamente.");
  insereParagrafo("O traçado da reta tangente é sempre feito automaticamente, ou seja, assim  que " +
            "o usuário modifica o valor de x o gráfico é desenhado.  Esse comportamento, no " +
            "entanto, permite apenas a visualização de uma reta tangente a cada curva por vez.  " +
            "Se o usuário desejar analisar o comportamento de várias retas tangentes a pontos " +
            "distintos de uma mesma curva, deverá marcar a opção \"Várias Tangentes\".   Assim " +
            "que tiver selecionada uma tangente de interesse, deverá clicar em \"Fixar Tangente\".");
  insereParagrafo("O iGraf oferece ainda uma opção de visualização das tangentes a uma função " +
            "através de uma animação.  Por padrão, os limites dessa animação são os valores " +
            "extremos vísiveis do eixo x.   Para visualizar tal animação, basta marcar a opção " +
            "\"Animação Tangente\".");

  insereSubTitulo("2.3   Visualizar Integral Indefinida");
  insereParagrafo("Desenha o gráfico da antiderivada F(x) da função f(x) que está na área de edição.   " +
            "Por padrão, o ponto de corte de F(x) no eixo das ordenadas é zero.   Uma " +
            "característica importante dessa curva é que ela é calculada numericamente.  Assim, " +
            "embora o usuário possa visualizar seu gráfico é, na versão atual do programa, impossível " +
            "recuperar a expressão matemática que o gera.");

  insereSubTitulo("2.4   Visualizar Integral Definida");
  insereParagrafo("Exibe uma janela na qual é possível selecionar na lista de funções editadas aquelas " +
            "que serão usadas para delimitar verticalmente a região de integração.");

  insereParagrafo("O usuário deve definir o intervalo de integração digitando os valores diretamente " +
            "nos campos de texto em \"Intervalo de Integração\", caso não o faça, será usado o " +
            "intervalo padrão [0..1].");
 */

 }
