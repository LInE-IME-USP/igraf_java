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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */


package igraf.moduloAjuda.modelo;


import igraf.basico.io.ResourceReader;


public class TextoGrafico extends JPanelBasisTopic {

 public TextoGrafico () {
  super("manualStTextGraTitle"); // 1   Gráfico

  insereParagrafo("manualStTextGra_I_1");

  insereSubTitulo("manualStTextGra_1"); // 1.1   Desenhar
  insereParagrafo("manualStTextGra_1_1");

  insereSubTitulo("manualStTextGra_2"); // 1.2   Desenhar Todos
  insereParagrafo("manualStTextGra_2_1");

  insereSubTitulo("manualStTextGra_3"); // 1.3   Editar Expressão
  insereParagrafo("manualStTextGra_3_1");
  insereParagrafo("manualStTextGra_3_2");
  insereParagrafo("manualStTextGra_3_3");
  insereParagrafo("manualStTextGra_3_4");
  insereParagrafo("manualStTextGra_3_5");

  insereSubTitulo("manualStTextGra_4"); // 1.4   Exibir Expressões
  insereParagrafo("manualStTextGra_4_1");

  insereSubTitulo("manualStTextGra_5"); // 1.5   Esconder
  insereParagrafo("manualStTextGra_5_1");

  insereSubTitulo("manualStTextGra_6"); // 1.6   Esconder Último
  insereParagrafo("manualStTextGra_6_1");

  insereSubTitulo("manualStTextGra_7"); // 1.7   Esconder Todos
  insereParagrafo("manualStTextGra_7_1");

  insereSubTitulo("manualStTextGra_8"); // 1.8   Remover gráfico
  insereParagrafo("manualStTextGra_8_1");

  insereSubTitulo("manualStTextGra_9"); // 1.8   Remover todos
  insereParagrafo("manualStTextGra_9_1");

  insereSubTitulo("manualStTextGra_10"); // 1.10   Nova aba
  insereParagrafo("manualStTextGra_10_1");

  insereSubTitulo("manualStTextGra_11"); // 1.11   Remover aba
  insereParagrafo("manualStTextGra_11_1");

  insereSubTitulo("manualStTextGra_12"); // 1.12   Nova sessão
  insereParagrafo("manualStTextGra_12_1");
  }

/*
  super("1   Gráfico");

  insereParagrafo("Menu de comandos associados ao traçado e edição de gráficos na área de desenho.");
  insereSubTitulo("1.1   Desenhar");
  insereParagrafo("Imprime na área de desenho o gráfico referente a expressão que está na área de edição.   " +
            "Clicar nesta opção ou teclar <enter> têm o mesmo efeito.");


  insereSubTitulo("1.2   Desenhar Todos");
  insereParagrafo("Desenha todos os gráficos previamente editados pelo usuário na sessão atual do iGraf " +
            "e que, por algum motivo, foram tornados ocultos ");

  insereSubTitulo("1.3   Editar Expressão");
  insereParagrafo("A ferramenta de edição de expressões serve para modificar gráficos de função, animações " +
            "ou polígonos que estão na área de desenho.");
  insereParagrafo("Ao abrir a janela de edição, selecione a expressão que deseja editar e, no campo de texto, " +
            "faça as alterações que julgar necessárias. A nova expressão só será aceita se for válida " +
            "e você verá o resultado imediatamente na área de desenho.");
  insereParagrafo("É possível editar também a cor do gráfico.  Na lista de cores, selecione a opção \"Outras " +
            "Cores\" e verá uma nova janela onde poderá escolher praticamente qualquer cor para o gráfico " +
            "usando o sistema aditivo de cores RGB (Red - Green - Blue).");
  insereParagrafo("Para confirmar a edição, clique no botão \"Ok\".   Clicar no \'x\' do canto superior direito " +
            "da janela tem o mesmo efeito.");
  insereParagrafo("Saiba mais sobre o sistema aditivo de cores em \"http://pt.wikipedia.org/wiki/RGB\". " +
            "Veja uma interessante lista de cores em \"http://pt.wikipedia.org/wiki/Lista_de_cores\"");

  insereSubTitulo("1.4   Exibir Expressões");
  insereParagrafo("Alterna entre exibir ou não, na área de desenho, as expressões das curvas visíveis");

  insereSubTitulo("1.5   Esconder");
  insereParagrafo("Apaga o gráfico correspondente à expressão que está na área de edição deixando " +
            "os outros inalterados.");

  insereSubTitulo("1.6   Esconder Último"); 
  insereParagrafo("Apaga o último gráfico desenhado na sessão atual.  É importante notar que " +
            "o gráfico apagado faz parte da sessão e, portanto, pode ser recuperado.");

  insereSubTitulo("1.7   Esconder Todos"); 
  insereParagrafo("Apaga todos os gráficos da tela mas mantém a lista de expressões na memória de " +
            "tal modo que os gráficos podem ser redesenhados clicando-se na opção Desenhar Todos.");

  insereSubTitulo("1.8   Remover gráfico"); 
  insereParagrafo("Remove o último gráficos selecionado");

  insereSubTitulo("1.9   Remover todos"); 
  insereParagrafo("Apaga todos os gráficos da tela e da memória de forma \"irrecuperável\".   Os gráficos " +
            "que foram apagados só poderão ser visualizados novamente com o uso do Histórico, a " +
            "ferramenta de inspeção de sessão do iGraf acessada através do menu Exercício.");

  insereSubTitulo("1.10   Nova Aba");
  insereParagrafo("Insere na sessão atual do iGraf uma nova aba.   Cada aba tem funcionamento independente.");

  insereSubTitulo("1.11   Remover Aba");
  insereParagrafo("Remove a aba que estiver selecionada.  Esta ação não pode ser desfeita.");

  insereSubTitulo("1.12   Nova Sessão");
  insereParagrafo("Inicia uma nova sessão do iGraf apagando todos os registros da sessão atual.   Tem o mesmo " +
            "efeito que clicar no menu \"Arquivo\" e selecionar a opção \"Novo\".");
 */
 }
