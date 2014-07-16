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
 * @description Class with static variables to build the view (interfaces)
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


package igraf.basico.util;

import java.awt.Color;
import java.awt.Font;

// public interface EsquemaVisual: para ofuscar - igraf/V/slider/AnimSlider.java; igraf/V/janela/PopUpFrame.java
public class EsquemaVisual {

  // igraf/moduloInferior/visao/InfoPane.java
  public static final String msgBarra = "iGraf - http://www.matematica.br/igraf";

  public static final Color corBarraSupInf = new Color(32, 64, 128);

  public static Color corAreaDesenho = Color.white; // em igraf/V/AreaDeDesenho

  public static final Color corFundo = new Color( 0, 50, 100); // background to top menu in 'igraf/moduloSuperior/visao/MenuFile.java'

  // Fundo azul escuro fosco
  // igraf/moduloAjuda/visao/JanelaSobre: background color to the text area with information about iGraf
  public static final Color corFundoSelecionado = new Color(0, 100, 150); // cor escura azul esverdeada

  public static final Color
        corF                  = new Color(255, 255, 255),
        corF2                 = new Color(250, 250, 250),
        corF3                 = new Color(000, 245, 245),

        fundoTopo             = Color.white, // cor para jundo dos topos das janelas JanelaTexto, JanelaExercicio, JanelaConfirmacao
 
        fundo_menu_secundario = new Color( 60,  90, 150),
        fundo_menu_primario   = new Color( 40,  90, 200);  // cor p/ fundo barra menu primária

  // Highlight color: 
  public static final Color
     corHighlightText = new Color( 0, 100, 50), // text color in highlight: src/igraf/moduloCentral/visao/desenho/DesenhoTexto.java
     corHighlightTextBorder = new Color( 0, 100, 50), // border of text in highlight: src/igraf/moduloCentral/visao/desenho/DesenhoTexto.java
     corHighlightMenuItem = new Color( 0, 50, 150); // menu item under mouse: src/igraf/moduloCentral/visao/menu/IgrafMenuItem.java

  // Color to menu items and to window configuration to menus, respectivelly in:
  // * igraf/moduloSuperior/visao/PainelBotoes.java
  // * igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java
  // * igraf/moduloCentral/visao/menu/[MenuGrafico | MenuCalculo | MenuAnimacao | MenuEdicao | MenuExercicio | MenuAjuda].java
  public static final Color
     corBackground = new Color(230, 235, 255), // also back in 'igraf/moduloAjuda/visao/JanelaSobre.java', 'igraf/moduloSuperior/visao/MenuFile.java', 'igraf/moduloExercicio/visao/resposta/AnswerVisualizerFrame.java'
     corBackPanel  = new Color(220, 225, 245), // back in attempt in 'igraf/moduloExercicio/visao/resposta/AnswerVisualizerFrame.java'
     corForeground = Color.black,
     corTitle      = new Color( 10, 100, 200);



  // To draw graphics' functions
  //
  // Drawing colors: to plot each graphic's function:  igraf/moduloCentral/visao/plotter/Plotter.java; igraf/moduloCentral/visao/desenho/Desenho.java
  public static final Color COLOR_laranja = new Color(170,  40, 245);
  public static final Color COLOR_roxo    = new Color( 55, 140, 100); // foi para azul claro (255, 140,   0)
  public static final Color COLOR_bordo   = new Color(105, 155, 240); // foi para azul claro (185,  55, 160)
  public static final Color COLOR_marinho = new Color( 30, 125, 210);
  public static final Color COLOR_marrom  = new Color(150,  90,  90);

  // igraf/moduloCentral/eventos/DesenhoTextoEvent.java: cor de texto
  public static final int
  cR2 =  0, cG2 =   0, cB2 = 200, //      2:  r=  0; g=  0; b=200; - r=0; g=0; b=200;
  cR3 =  0, cG3 = 200, cB3 =   0, //      3:  r=  0; g=200; b=  0; - r=0; g=200; b=0;
  cR4 =  0, cG4 = 100, cB4 = 100, //      4:  r=  0; g=100; b=100; - r=0; g=200; b=200;
  cR5 =  0, cG5 = 100, cB5 =   0, //      5:  r=  0; g=100; b=  0; - r=200; g=0; b=0;
  cR1 =  0, cG1 =   0, cB1 =   0; // demais  // n:  r=  0; g=  0; b=  0; = demais


  //
  // igraf/moduloCentral/visao/desenho/Desenho.java
  public static final int STROKE_DEFAULT = 1; // default thickness to lines
  public static final int STROKE_THICK   = 2; // thicker lines


  /**
   * A cor das letras dos painéis que exibem texto. Incluindo Ajuda.
   */
  public static final Color corLetras = Color.white;

  /**
   * Cor de fundo da área de digitação das expressões matemáticas.
   */
  public static final Color corFundoEdicao = Color.white;

  /**
   * Colors to manual. From: igraf/moduloAjuda/visao/Help.java
   */
  public static final Color corLetrasBotoes      = Color.white; // color to each manu item (at the left side of manual)
  public static final Color corLetrasAtivoBotoes = Color.gray; // color to item menu active (its topic is presented)
  public static final Color corLetrasRotulos     = Color.black; // cor de letras para Paragrafo.java em moduloAjuda
  public static final Color corFundoParagrafos   = Color.white; // color to each explantion (igraf/moduloAjuda/visao/navegador/PainelConteudo.java, igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java)
  public static final Color corFundoBotoes       = new Color(0,90,180); // background color to buttons (igraf/moduloAjuda/visao/navegador/NavigatorLabel and igraf/moduloJanelasAuxiliares/visao/JanelaHistorico)
  public static final Color corDesFundoBotoes    = new Color(50,140,230); // background color to buttons to disabled buttons (idem)
  public static final Color corAcesa             = new Color(70,160,250); // color of the menu item under the mouse - in igraf/moduloAjuda/visao/navegador/NavigatorLabel.java and HelpButton.java

  /**
   * Font Helvetica: <simple, size 12> <negrito(bold), tamanho 12>
   */
  public static final Font
    fontHB12 = new Font("Helvetica", Font.BOLD, 12), // igraf/moduloAjuda/visao/navegador/NavigatorLabel.java and HelpButton.java
    fontHP12 = new Font("Helvetica", Font.PLAIN,12), // igraf/moduloAjuda/visao/JanelaSobre.java
    fontBotoes = new Font("Helvetica", Font.PLAIN,12),
    fontBotoesSelecionado = new Font("Helvetica", Font.BOLD, 12),
    fontHB11  = new Font("Helvetica", Font.BOLD, 11),  // igraf/moduloJanelasAuxiliares/visao/animacao/JanelaAnimacao.java
    fontHP11  = new Font("Helvetica", Font.PLAIN, 11), // igraf/moduloJanelasAuxiliares/visao/animacao/JanelaAnimacao.java
    fontHP10  = new Font("Helvetica", Font.PLAIN, 10),
    fontHB10  = new Font("Helvetica", Font.BOLD, 10);


  // EsquemaVisual de exercicio 
  // public static final Color corFundo = corAreaDesenho;
  public static final Color corFundoBotao =  corFundoBotoes;
  public static final Color corLetraAreaTexto = Color.black;
  public static final Color corLetraRotulo = Color.black; // Color.white; // Color.blue;
  public static final Color corLetraTipoItem = new Color(0, 0, 150);
  public static final Color corLetraRotuloComentario = Color.white; //
  public static final Color corLetraBotao = corLetrasBotoes;
  public static final Color corMoldura = Color.black;
  }
