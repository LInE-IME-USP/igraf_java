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
 * @description Define os parâmetros de configuração do sistema iGraf na forma de variáveis estáticas. 
 *              Variáveis estáticas para configuração do interface gráfica
 * 
 * @version 
 * @date  22/05/2006
 * @alter 09/04/2006
 * 
 * @see igraf/basico/util/Configuracoes.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

// 4.4.0.10: 11/07/2013: melhoria na comunicao botao interno (completa nome com http do codebase)

// 4.4.0.9: 10/07/2013: acertos bug em carga de arquivo; novo layout de botao File; fechar em X agora tb pergunta se deseja gravar
// 4.4.0.8: 23/06/2013: acertos em gravar/ler arquivos (passou para swing); acerto bug de 4.4.0.3 que impedia gravar itens menus removidos
// 4.4.0.7: 21/06/2013: adicionadas msg para erros em respsta numerica; tratando tag 'iLM_PARAM_ChangePage'
// 4.4.0.6: 21/06/2013: botao responder e parametro para enviar resposta (estava incompativel com iTarefa atual)
// 4.4.0.5: 18/06/2013: varios acertos animacao de funcoes: janela de controle (desabilita item sem funcao; cores; lig. direta com o controle da animacao); 'DesenhoAnimacao'; acesso direto
// 4.4.0.4: 09/06/2013: varios acertos janelas: de edicao parametros; panel de comentario de exercicio
// 4.4.0.3: 09/06/2013: varios acertos: bug em editar exercicio; em comunicacao exerc.; em configurar menus
// 4.4.0.1: 04/06/2013: acerto em envio/analise de resposta
// 4.4.0.0: 04/06/2013: finalizada modelo de ajuda (falta parte de Ingles); acertos em controle de mensagens internas
// 4.3.2.0: 29/05/2013: novo modelo de ajuda (permite internacionalizar)
// 4.3.1.1: 25/05/2013: internacionalizacao aba e outros pequenos acertos
// 4.3.1.0: 24/05/2013: varios acertos para troca de aba com mover eixo sem perder na outra aba
// 4.3.0.2: 19/05/2013: acerto de deslocamento para "integral indefinida" (agora na posicao correta)
// 4.3.0.1: 19/05/2013: acerto para mostrar "integral indefinida" apos carregar arquivo (aplica sobre ultima funcao)
// 4.3.0.0: 15/05/2013: novo modelo de layout, novo sistema de menus - muita alteracoes
// 4.2.3.1: 12/04/2013: eliminado 'zoom in,out' com thread em submenus; acerto para posicionar submenuitens
// 4.2.3.0: 11/04/2013: acerto bug voltar lingua para 'pt_BR' ao abrir conf. de menus; acerto 'gap' em aba/ADD em applet; outros pequenos
// 4.1.4.3: 11/11/2011: acertos visuais de destaque (texto) e internos (indentacao e valores estaticos)
// 4.1.4.2: 11/11/2011: evitar bug de HTML com 'lang' vazio
// 3.0.0: 05-19/06/2009: agora criptografa escondendo info. de exercício; acertos em interface de resposta e autoria de exercício
// 2.1.0: 05-13/06/2009: permite edição de texto com clique duplo; grava texto na pos. de movimento; ao ler arq. linha cmd permite gravar c/ mesmo nome
// 2.0.3: 03/06/2009: acertos em 'exercicio/PainelComentario.java', principalment internacionalização
// 2.0.2: 20/05/2009: acerto em 'exercicio/Resposta*.java' para aceitar gabarito de versao <= 1.8.3
// 2.0.1: 03/05/2009: acerto em 'igraf/V/writer/Writer' para aceitar texto com 'cor: 0' no lugar do atual 'r:0 g:0 b:0'
// 2.0.0: 25/04/2009: muitos acerto (lê arq. via parâmetro; contraste; internacionalização)
// 1.9.1: 23/04/2009: esta nao foi publicada!
//        ampliado internacionalização; leitura de parâmetros para lingua/contraste; funções públicas para LMS via JavaScript
// 1.9  : 14/03/2008

package igraf.basico.util;

import java.awt.Font;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;


public class Configuracoes {

  /**
   * A string cujo conteúdo aparece alinhado à direita no 
   * painel superior (versão) do programa.<br>
   * 4.4.0.10: 11/07/2013: melhoria na comunicao botao interno (completa nome com http do codebase)
   * 4.4.0.9: 10/07/2013: acertos bug em carga de arquivo; novo layout de botao File; fechar em X agora tb pergunta se deseja gravar
   * 4.4.0.8: 2013/06/23: acertos em gravar/ler arquivos (passou para swing); acerto bug de 4.4.0.3 que impedia gravar itens menus removidos
   * 4.4.0.7: 2013/06/21: adicionadas msg para erros em respsta numerica; tratando tag 'iLM_PARAM_ChangePage'
   * 4.4.0.6: 2013/06/21: botao responder e parametro para enviar resposta (estava incompativel com iTarefa atual)
   * 4.4.0.5: 2013/06/18: varios acertos animacao de funcoes: janela de controle (desabilita item sem funcao; cores; lig. direta com o controle da animacao); 'DesenhoAnimacao'; acesso direto
   * 4.4.0.4: 2013/06/09: varios acertos janelas: de edicao parametros; panel de comentario de exercicio
   * 4.4.0.3: 2013/06/09: varios acertos: bug em editar exercicio; em comunicacao exerc.; em configurar menus
   * 4.4.0.1: 2013/06/04: acerto em envio/analise de resposta
   * 4.4.0.0: 2013/06/04: finalizada modelo de ajuda (falta parte de Ingles); acertos em controle de mensagens internas
   * 4.3.2.0: 2013/05/29: modelo ajuda com possibilidade de internacionalizacao
   * 4.3.1.1: 2013/05/25: internacionalizacao aba e outros pequenos acertos
   * 4.3.1.0: 2013/05/24: varios acertos para arrastar eixos sem estragar noutra aba
   * 4.3.0.2: 2013/05/19: pequeno acerto em "integral indefinida": agora mostra corretamente posicao
   * 4.3.0.1: 2013/05/19: pequenos acertos ("integral indefinida"), mas ainda existe problema numerico
   * 4.3.0.0: 2013/05/15: novo modelo de layout, novo sistema de menus - muita alteracoes<br>
   * 4.2.3.1: 2013/04/12<br>
   * 4.2.3.0: 2013/04/11<br>
   * 4.2.2.1: 2013/01/07<br>
   * 4.2.2  : 2012/11/05<br>
   * 4.2.1  : 2012/07/14<br>
   * 4.2.0.2: 2012/02/10<br>
   * 4.2.0.1: 2012/01/17<br>
   * 4.2    : 2012/01/03<br>
   * 4.1.5.2: 2011/12/29<br>
   * 4.1.5.1: 2011/12/28<br>
   * 4.1.5  : 2011/12/24<br>
   * 4.1.4.3: 2011/11/11<br>
   * 4.1.4.2: 2011/11/11<br>
   * 4.1.4.1: 2011/11/02<br>
   * 4.1.4  : 2011/10/04<br>
   * 4.1.3  : 2011/07/22<br>
   * 4.1.2  : 2011/03/26<br>
   * 4.1.1  : 2011/03/20<br>
   */
  private static final String strVersao = "4.4.0.10";
  public static String getVersion() {
   return strVersao;
   }

  /**
   * A string cujo conteúdo aparece centralizado no painel 
   * superior (título) do programa.
   */
  public static final String strTitulo = ".: "+ResourceReader.msg("igraf")+" :."; // "iGraf - Gráficos Interativos na Internet"
  
  public static String getStringTitulo(){
   return ".: "+ResourceReader.msg("igraf")+" :.";
   }

  public static final String MARCA_IGRAF = "# iGraf: http://www.matematica.br";

  // Auxiliares  
  public static final boolean RASTREAR_RESP = false; // exercicio/PainelSeletorTipoResposta.java

  /**
   * Constante que representa um deslocamento na direção horizontal.
   * Por padrão seu valor é 1.
   */
  public static final int dx = 1;// para deslocamentos

  /**
   * Constante que representa um deslocamento na direção horizontal.
   * Por padrão seu valor é 1.
   */
  public static final int dy = 1; // para deslocamentos

  /**
   * Valor usado para desconto em 'labelMensagens' e 'edicao'
   */
  public static final int descX = 10*dx;// para desconto em 'labelMensagens' e 'edicao'



  // painel: geral, contém "tudo"
  /*
   * A abscissa do canto esquerdo superior do painel principal
   */
  private static final int xTFP = 1;

  /*
   * A ordenada do canto esquerdo superior do painel principal
   */
  private static final int yTFP = 1;

  /**
   * Largura do painel principal
   */
  public static final int lTFP = 832;

  /**
   * Altura do painel principal
   */
  public static final int aTFP = 600;

  /* Determina a altura dos label's título e versão */ 
  public static final int altF = 30;



  // largura, altura de botões: PainelDeBotoes.java

  /**
   * Largura dos botões dos painéis de botões
   */
  public static final int larBt = 20;

  /**
   * Altura dos botões dos painéis de botões
   */
  public static final int altBt = 34; 



  // titulo: iGraf - gráficos Interativos na Internet
  /**
   * A largura da área retangular ocupada pelo título do programa.
   */
  public static final int lTFNome=265;

  /**
   * A altura da área retangular ocupada pelo título do programa.
   */
  public static final int aTFNome=altF;

  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pelo título do programa.
   */
  public static final int xTFNome=xTFP+(lTFP-lTFNome)/2;

  /**
   * A ordenada do canto superior esquerdo da área retangular
   * ocupada pelo título do programa.
   */
  public static final int yTFNome=yTFP+dy;



  // versão
  /**
   * A largura da área retangular ocupada pelo (label) versão do programa.
   */
  public static final int lTFVersao=120;

  /**
   * A altura da área retangular ocupada pelo (label) versão do programa.
   */
  public static final int aTFVersao=altF;

  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pelo (label) versão do programa.
   */
  public static final int xTFVersao=xTFP+lTFP-lTFVersao;

  /**
   * A ordenada do canto superior esquerdo da área retangular
   * ocupada pelo (label) versão do programa.
   */
  public static final int yTFVersao=yTFP+dy;



  // painel de botões
  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pelo painel de botões.
   */
  public static final int xTFPBt=xTFP+dx;

  /**
   * A ordenada do canto superior esquerdo da área retangular
   * ocupada pelo painel de botões.
   */
  public static final int yTFPBt=yTFP+aTFVersao+dy;

  /**
   * A largura da área retangular ocupada pelo painel de botões do programa.
   */
  public static final int lTFPBt=lTFP-dy;

  /**
   * A altura da área retangular ocupada pelo painel de botões do programa.
   */
  public static final int aTFPBt=altBt;



  // edição: área para digitar expressão aritmética
  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pela área de edição de função.
   */
  public static final int xTFEdicao = 4;

  /**
   * A ordenada do canto superior esquerdo da área retangular
   * ocupada pela área de edição de função.
   */
  public static final int yTFEdicao=yTFPBt+aTFPBt+dy;

  /**
   * A largura da área retangular ocupada pelo painel de botões do programa.
   */
  public static final int lTFEdicao=lTFP-11;

  /**
   * A altura da área retangular ocupada pelo painel de botões do programa e altura da barra superior (com logo).
   */
  public static final int aTFEdicao=25; // 30;



  // área de desenho: na verdade é uma área para desenho, com os eixos
  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pela área de desenho de função.
   */
  public static final int xTFADD = 4;

  /**
   * A ordenada do canto superior esquerdo da área retangular
   * ocupada pela área de desenho de função.
   */
  public static final int yTFADD=yTFEdicao+aTFEdicao+1;

  /**
   * A altura da área retangular ocupada pela área de desenho do programa.
   */
  public static final int lTFADD=lTFP-11;

  /**
   * A altura da área retangular ocupada pela área de desenho do programa.
   */
  public static final int aTFADD=aTFP-(aTFVersao+aTFPBt+aTFEdicao+55);


  //  barra de mensagem: parte inferior
  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pela barra de mensagens.
   */
  public static final int xTFMsg = 4;

  /**
   * A abscissa do canto superior esquerdo da área retangular
   * ocupada pela barra de mensagens.
   */
  public static final int yTFMsg = yTFADD+aTFADD+3;

  /**
   * A largura da área retangular ocupada pelo label de mensagens na
   * parte inferior do programa.
   */
  public static final int lTFMsg = lTFP-11;

  /**
   * A altura da área retangular ocupada pelo label de mensagens na
   * parte inferior do programa.
   */
  public static final int aTFMsg = 24;

  // igraf/moduloCentral/visao/plotter/GraphPlotter.java
  public static final Font fontFunctionName = new Font("Arial", Font.PLAIN, 18);

  }
