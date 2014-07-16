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
 * @description Help integrated to iGraf. Component to explain a menu item: Edition
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


public class TextoEdicoes extends JPanelBasisTopic {

 public TextoEdicoes () {
  super("manualStTextEditTitle"); // 4   Edições

  insereParagrafo("manualStTextEdit_I_1");

  insereSubTitulo("manualStTextEdit_1"); // 4.1   Zoom Padrão
  insereParagrafo("manualStTextEdit_1_1");

  insereSubTitulo("manualStTextEdit_2"); // 4.2    Zoom: aumentar
  insereParagrafo("manualStTextEdit_2_1");

  insereSubTitulo("manualStTextEdit_3"); // 4.3   Zoom: diminuir
  insereParagrafo("manualStTextEdit_3_1");

  insereSubTitulo("manualStTextEdit_4"); // 4.4   Texto: inserir
  insereParagrafo("manualStTextEdit_4_1");

  insereSubTitulo("manualStTextEdit_5"); // 4.5   Texto: editar
  insereParagrafo("manualStTextEdit_5_1");
  insereParagrafo("manualStTextEdit_5_2");

  insereSubTitulo("manualStTextEdit_6"); // 4.6   Texto: remover
  insereParagrafo("manualStTextEdit_6_1");

  insereSubTitulo("manualStTextEdit_7"); // 4.7   Eixos: eemover
  insereParagrafo("manualStTextEdit_7_1");

  insereSubTitulo("manualStTextEdit_8"); // 4.8   Escala: remover
  insereParagrafo("manualStTextEdit_8_1");

  insereSubTitulo("manualStTextEdit_9"); // 4.9   Grade: remover
  insereParagrafo("manualStTextEdit_9_1");

  insereSubTitulo("manualStTextEdit_10"); // 4.10  Grade: remover
  insereParagrafo("manualStTextEdit_10_1");

  }

/*
  super("4   Edições");

  insereParagrafo("Menu que permite ao usuário fazer opções de configuração da área onde são " +
            "desenhados os gráficos e textos");

  insereSubTitulo("4.1   Zoom Padrão");
  insereParagrafo("Restaura a escala do plano cartesiano ao valor padrão.");

  insereSubTitulo("4.2   Aumentar ou Diminuir Zoom");
  insereParagrafo("Botões que permitem ao usuário aumentar ou diminuir a \"aproximação\" " +
            "do gráfico modificando a escala do plano cartesiano e o \"tamanho\" do gráfico.");

  insereSubTitulo("4.3   Inserir Texto");
  insereParagrafo("Exibe uma janela na qual o usuário pode digitar um texto, escolher o tamanho " +
            "e a cor da fonte para, em seguida, inserí-lo na área de desenho.   Para efetivar " +
            "a inserção, selecione as coordenadas do ponto do plano cartesiano onde pretende " +
            "que o texto seja escrito e clique em \"Inserir\"");

  insereSubTitulo("4.4   Editar (ou Apagar) Texto");
  insereParagrafo("Opções que exibem uma janela na qual o usuário pode selecionar um dos textos " +
            "que porventura estejam na área de desenho e modificá-lo ou definitivamente apagá-lo.   " +
            "Para realizar uma das duas ações, marque a caixa de seleção que se encontra na frente " +
            "do texto e clique no botão cujo rótulo corresponda à ação desejada.");
  insereParagrafo("As duas opções abrem a mesma janela e só são separadas no menu por clareza.");

  insereSubTitulo("4.5   Remover Eixos");
  insereParagrafo("Permite ao usuário alternar entre deixar o eixo cartesiano visível ou não.");

  insereSubTitulo("4.6   Remover Escala");
  insereParagrafo("Permite ao usuário alternar entre deixar a escala numérica visível ou não.");

  insereSubTitulo("4.7   Remover Grade");
  insereParagrafo("Permite ao usuário alternar entre a exibição dos eixos cartesianos ou de uma grade.");
 */
 }
