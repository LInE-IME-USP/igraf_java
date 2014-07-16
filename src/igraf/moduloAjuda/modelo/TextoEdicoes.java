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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */


package igraf.moduloAjuda.modelo;


import igraf.basico.io.ResourceReader;


public class TextoEdicoes extends JPanelBasisTopic {

 public TextoEdicoes () {
  super("manualStTextEditTitle"); // 4   Edi��es

  insereParagrafo("manualStTextEdit_I_1");

  insereSubTitulo("manualStTextEdit_1"); // 4.1   Zoom Padr�o
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
  super("4   Edi��es");

  insereParagrafo("Menu que permite ao usu�rio fazer op��es de configura��o da �rea onde s�o " +
            "desenhados os gr�ficos e textos");

  insereSubTitulo("4.1   Zoom Padr�o");
  insereParagrafo("Restaura a escala do plano cartesiano ao valor padr�o.");

  insereSubTitulo("4.2   Aumentar ou Diminuir Zoom");
  insereParagrafo("Bot�es que permitem ao usu�rio aumentar ou diminuir a \"aproxima��o\" " +
            "do gr�fico modificando a escala do plano cartesiano e o \"tamanho\" do gr�fico.");

  insereSubTitulo("4.3   Inserir Texto");
  insereParagrafo("Exibe uma janela na qual o usu�rio pode digitar um texto, escolher o tamanho " +
            "e a cor da fonte para, em seguida, inser�-lo na �rea de desenho.   Para efetivar " +
            "a inser��o, selecione as coordenadas do ponto do plano cartesiano onde pretende " +
            "que o texto seja escrito e clique em \"Inserir\"");

  insereSubTitulo("4.4   Editar (ou Apagar) Texto");
  insereParagrafo("Op��es que exibem uma janela na qual o usu�rio pode selecionar um dos textos " +
            "que porventura estejam na �rea de desenho e modific�-lo ou definitivamente apag�-lo.   " +
            "Para realizar uma das duas a��es, marque a caixa de sele��o que se encontra na frente " +
            "do texto e clique no bot�o cujo r�tulo corresponda � a��o desejada.");
  insereParagrafo("As duas op��es abrem a mesma janela e s� s�o separadas no menu por clareza.");

  insereSubTitulo("4.5   Remover Eixos");
  insereParagrafo("Permite ao usu�rio alternar entre deixar o eixo cartesiano vis�vel ou n�o.");

  insereSubTitulo("4.6   Remover Escala");
  insereParagrafo("Permite ao usu�rio alternar entre deixar a escala num�rica vis�vel ou n�o.");

  insereSubTitulo("4.7   Remover Grade");
  insereParagrafo("Permite ao usu�rio alternar entre a exibi��o dos eixos cartesianos ou de uma grade.");
 */
 }
