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
 * @description Help integrated to iGraf. Component to explain a menu item: Graphics
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


public class TextoGrafico extends JPanelBasisTopic {

 public TextoGrafico () {
  super("manualStTextGraTitle"); // 1   Gr�fico

  insereParagrafo("manualStTextGra_I_1");

  insereSubTitulo("manualStTextGra_1"); // 1.1   Desenhar
  insereParagrafo("manualStTextGra_1_1");

  insereSubTitulo("manualStTextGra_2"); // 1.2   Desenhar Todos
  insereParagrafo("manualStTextGra_2_1");

  insereSubTitulo("manualStTextGra_3"); // 1.3   Editar Express�o
  insereParagrafo("manualStTextGra_3_1");
  insereParagrafo("manualStTextGra_3_2");
  insereParagrafo("manualStTextGra_3_3");
  insereParagrafo("manualStTextGra_3_4");
  insereParagrafo("manualStTextGra_3_5");

  insereSubTitulo("manualStTextGra_4"); // 1.4   Exibir Express�es
  insereParagrafo("manualStTextGra_4_1");

  insereSubTitulo("manualStTextGra_5"); // 1.5   Esconder
  insereParagrafo("manualStTextGra_5_1");

  insereSubTitulo("manualStTextGra_6"); // 1.6   Esconder �ltimo
  insereParagrafo("manualStTextGra_6_1");

  insereSubTitulo("manualStTextGra_7"); // 1.7   Esconder Todos
  insereParagrafo("manualStTextGra_7_1");

  insereSubTitulo("manualStTextGra_8"); // 1.8   Remover gr�fico
  insereParagrafo("manualStTextGra_8_1");

  insereSubTitulo("manualStTextGra_9"); // 1.8   Remover todos
  insereParagrafo("manualStTextGra_9_1");

  insereSubTitulo("manualStTextGra_10"); // 1.10   Nova aba
  insereParagrafo("manualStTextGra_10_1");

  insereSubTitulo("manualStTextGra_11"); // 1.11   Remover aba
  insereParagrafo("manualStTextGra_11_1");

  insereSubTitulo("manualStTextGra_12"); // 1.12   Nova sess�o
  insereParagrafo("manualStTextGra_12_1");
  }

/*
  super("1   Gr�fico");

  insereParagrafo("Menu de comandos associados ao tra�ado e edi��o de gr�ficos na �rea de desenho.");
  insereSubTitulo("1.1   Desenhar");
  insereParagrafo("Imprime na �rea de desenho o gr�fico referente a express�o que est� na �rea de edi��o.   " +
            "Clicar nesta op��o ou teclar <enter> t�m o mesmo efeito.");


  insereSubTitulo("1.2   Desenhar Todos");
  insereParagrafo("Desenha todos os gr�ficos previamente editados pelo usu�rio na sess�o atual do iGraf " +
            "e que, por algum motivo, foram tornados ocultos ");

  insereSubTitulo("1.3   Editar Express�o");
  insereParagrafo("A ferramenta de edi��o de express�es serve para modificar gr�ficos de fun��o, anima��es " +
            "ou pol�gonos que est�o na �rea de desenho.");
  insereParagrafo("Ao abrir a janela de edi��o, selecione a express�o que deseja editar e, no campo de texto, " +
            "fa�a as altera��es que julgar necess�rias. A nova express�o s� ser� aceita se for v�lida " +
            "e voc� ver� o resultado imediatamente na �rea de desenho.");
  insereParagrafo("� poss�vel editar tamb�m a cor do gr�fico.  Na lista de cores, selecione a op��o \"Outras " +
            "Cores\" e ver� uma nova janela onde poder� escolher praticamente qualquer cor para o gr�fico " +
            "usando o sistema aditivo de cores RGB (Red - Green - Blue).");
  insereParagrafo("Para confirmar a edi��o, clique no bot�o \"Ok\".   Clicar no \'x\' do canto superior direito " +
            "da janela tem o mesmo efeito.");
  insereParagrafo("Saiba mais sobre o sistema aditivo de cores em \"http://pt.wikipedia.org/wiki/RGB\". " +
            "Veja uma interessante lista de cores em \"http://pt.wikipedia.org/wiki/Lista_de_cores\"");

  insereSubTitulo("1.4   Exibir Express�es");
  insereParagrafo("Alterna entre exibir ou n�o, na �rea de desenho, as express�es das curvas vis�veis");

  insereSubTitulo("1.5   Esconder");
  insereParagrafo("Apaga o gr�fico correspondente � express�o que est� na �rea de edi��o deixando " +
            "os outros inalterados.");

  insereSubTitulo("1.6   Esconder �ltimo"); 
  insereParagrafo("Apaga o �ltimo gr�fico desenhado na sess�o atual.  � importante notar que " +
            "o gr�fico apagado faz parte da sess�o e, portanto, pode ser recuperado.");

  insereSubTitulo("1.7   Esconder Todos"); 
  insereParagrafo("Apaga todos os gr�ficos da tela mas mant�m a lista de express�es na mem�ria de " +
            "tal modo que os gr�ficos podem ser redesenhados clicando-se na op��o Desenhar Todos.");

  insereSubTitulo("1.8   Remover gr�fico"); 
  insereParagrafo("Remove o �ltimo gr�ficos selecionado");

  insereSubTitulo("1.9   Remover todos"); 
  insereParagrafo("Apaga todos os gr�ficos da tela e da mem�ria de forma \"irrecuper�vel\".   Os gr�ficos " +
            "que foram apagados s� poder�o ser visualizados novamente com o uso do Hist�rico, a " +
            "ferramenta de inspe��o de sess�o do iGraf acessada atrav�s do menu Exerc�cio.");

  insereSubTitulo("1.10   Nova Aba");
  insereParagrafo("Insere na sess�o atual do iGraf uma nova aba.   Cada aba tem funcionamento independente.");

  insereSubTitulo("1.11   Remover Aba");
  insereParagrafo("Remove a aba que estiver selecionada.  Esta a��o n�o pode ser desfeita.");

  insereSubTitulo("1.12   Nova Sess�o");
  insereParagrafo("Inicia uma nova sess�o do iGraf apagando todos os registros da sess�o atual.   Tem o mesmo " +
            "efeito que clicar no menu \"Arquivo\" e selecionar a op��o \"Novo\".");
 */
 }
