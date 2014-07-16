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
 * @description Uma A��o � um objeto que registra e identifica o uso, pelo usu�rio, de uma funcionalidade do programa.
 * Para inserir uma nova a��o no programa voc�, supostamente um programador, deve seguir os passos abaixo:<br>
 * Crie um r�tulo (String) para a a��o na lista apropriada, p.e. NOVA_ACAO_DESENHO na listaAcaoDesenho ;<br>
 * Crie o mesmo r�tulo em igraf.resource.StringsTable.properties;<br>
 * Crie uma constante inteira na lista deste mesmo arquivo e associe-a � nova a��o usando preferencialmente um nome coerente p.e. inserirNovaAcao = k;<br>
 * Use um 'switch/case' no m�todo Sessao.refazGrafico() usando esse identificador como chave de sele��o<br>
 * Coloque uma chamada para Sessao.insereAcao() no comando que dispara essa nova a��o.<br>
 *
 * @see igraf/moduloCentral/modelo/Sessao.java: public boolean registraEvento(CommunicationEvent communicationEvent): listaAcao.addElement(new Acao(code, param));
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 * @see igraf/moduloExercicio/ModuloExercicio.java: makes 'JanelaExercicioController jec = new JanelaExercicioController(this, true);'
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 * @description
 * @author RP, LOB
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 */

package igraf.moduloCentral.modelo;

import igraf.basico.io.ResourceReader;
import igraf.moduloExercicio.visao.PainelResposta;
import igraf.moduloExercicio.visao.PainelRespostaAlgebrica;
import igraf.moduloExercicio.visao.PainelRespostaIntervalo;
import igraf.moduloExercicio.visao.PainelRespostaNumerica;
import igraf.moduloExercicio.visao.PainelRespostaPonto;


public class Acao {

 /** 
  * Lista que cont�m todas as a��es referentes ao desenho no iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a zero e menor que 100 
  */
 private static final String [] listaAcaoDesenho = {
   "DESENHO_INDIVIDUAL", "DESENHO_MULTIPLO",
   "ESCONDER_ULTIMO_GRAFICO", "DELETAR_GRAFICO_SELECIONADO",
   "DELETAR_TODOS_GRAFICOS", "ESCONDER_TODOS_GRAFICOS", "EDITAR_FUNCAO", "INSERIR_ABA",
   " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", 
   " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "POLIGONO", "UNDO_POLIGONO", "REDO_POLIGONO"};

 /** 
  * Lista que cont�m todas as a��es referentes � anima��o no iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a 100 e menor que 200 
  */
 private static final String [] listaAcaoAnimacao = {"ANIMACAO", "RASTRO_NA_TELA", "AUMENTAR_VELOCIDADE_ANIMACAO", "DIMINUIR_VELOCIDADE_ANIMACAO"};

 /** 
  * Lista que cont�m todas as a��es referentes � exibi��o dos eixos no iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a 200 e menor que 300 
  */
 private static final String [] listaAcaoEixos = {"EIXOS", "ESCALA", "GRADE", "DESLOCAR_ORIGEM"};

 /** 
  * Lista que cont�m todas as a��es referentes �s ferramentas do C�lculo no iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a 300 e menor que 400 
  */
 private static final String [] listaAcaoCalculo = {"TRACAR_DERIVADA", "TRACAR_TANGENTE", "CALCULAR_INTEGRAL", "INT_INDEFINIDA"};

 /** 
  * Lista que cont�m todas as a��es referentes ao <i>zooming</i> no iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a 400 e menor que 500 
  */
 private static final String [] listaAcaoZoom = {"MAIS_ZOOM", "MENOS_ZOOM", "ZOOM_PADRAO"};

 /** 
  * Lista que cont�m todas as a��es referentes � inser��o de texto
  * na �rea de desenho do iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a 500 e menor que 600 
  */
 private static final String [] listaAcaoTexto = {"INSERE_TEXTO", "APAGA_TEXTO", "EDITA_TEXTO",  "ATUALIZA_POSICAO_TEXTO"};

 /** 
  * Lista que cont�m todas as a��es referentes aos exerc�cios no iGraf.
  * Todos os valores associados a essa lista t�m identificador inteiro
  * maior ou igual a 600 e menor que 700 
  */
 private static final String [] listaAcaoExercicio = {
   "HABILITA_ENVIO_RESPOSTA", "RESPOSTA_NUMERICA", "RESPOSTA_ALGEBRICA",
   "RESPOSTA_PONTO", "RESPOSTA_INTERVALO", "INSERIR_COMENTARIO", "HABILITA_VISAO_RESPOSTA", 
   "RESPOSTA_OBJETIVA", "RESPOSTA_DISCURSIVA", "DESABILITA_MENU", "INSERIR_COMENTARIO_ALUNO"};

 /**
  * Tabela de a��es(funcionalidades) dispon�veis no iGraf
  */
 private static String [][] matrizAcao = {
   listaAcaoDesenho,  // linha 0
   listaAcaoAnimacao, // linha 1
   listaAcaoEixos,    // linha 2
   listaAcaoCalculo,  // linha 3
   listaAcaoZoom,     // linha 4
   listaAcaoTexto,    // linha 5
   listaAcaoExercicio // linha 6
   };

 /**
  * Lista de constantes inteiras que identificam a��es realiz�veis pelo sistema
  */
 public static final int
  // constantes associadas ao desenho
  desenhar =       0,
  desenharTodos =  1,
  apagarUltimo =   2,
  apagarGrafico =  3,
  removerTodos =   4,
  apagarTodos =    5,
  editarFuncao =   6,
  inserirAba =     7,

  criarPoligono    = 30,
  desfazerPoligono = 31,
  refazerPoligono  = 32,

 // constantes associadas � anima��o
  animar =              100,
  deixarRastro =        101,
  aumentarVelocidade =  102,
  diminuirVelocidade =  103,

  // constantes associadas ao desenho dos eixos
  exibirEixos =     200,
  exibirEscala =    201,
  exibirGrade =     202,
  deslocarOrigem =  203,

  // constantes associadas �s ferramentas do C�lculo
  derivar =          300,
  desenhaTangente =  301,
  intDefinida =      302,
  intIndefinida =    303,

  // constantes associadas ao estado do plano cartesiano
  mudaPlanoCartesiano    = 400,
  diminuirZoom           = 401,
  zoomPadrao             = 402,

  // constantes associadas � manipula��o de textos
  inserirTexto           = 500,
  apagarTexto            = 501,
  editarTexto            = 502,
  atualizarPosicaoTexto  = 503,

  // constantes associadas a manipula��o de exerc�cios
  habilitarEnvioResposta = 600,
  respostaNumerica       = 601,
  respostaAlgebrica      = 602,
  respostaPonto          = 603,
  respostaIntervalo      = 604,
  inserirComentario      = 605,
  habilitarVisaoResposta = 606,
  respostaObjetiva       = 607,
  respostaDiscursiva     = 608,
  desabilitaMenu         = 609,
  inserirComentarioAluno = 610;

 private int codigoAcao, linhaAcao, colunaAcao;
 private String descricao;

 // String que cont�m argumentos para a a��o separados por v�rgula
 private String listaArg;

 // Acao () { }

 private Acao (int i) {
  codigoAcao = i;
  linhaAcao = i / 100;
  colunaAcao = i % 100;    
  descricao = ResourceReader.msg(matrizAcao[linhaAcao][colunaAcao]);
  }

 public Acao (int i, String listaArg) {
  this(i);
  this.listaArg = listaArg;
  //  if (i==602) try {String s=""; System.out.println(s.charAt(3)); } catch (Exception e) { e.printStackTrace(); }
  }


 public static boolean ehExercicio (int acao) {
  return (acao>=habilitarEnvioResposta && acao<=inserirComentario); //  fora de [600, 605]
  }

 // Chamado: 'exercicio/Avaliador.geraGabarito(Resposta [] acaoResp)'
 // devolve n�m. das a��o correspondentes ao tipos de exerc�cio
 public static int acaoDeResposta (PainelResposta resp) {
  if (resp instanceof PainelRespostaAlgebrica)
   return respostaAlgebrica; // 602,
  if (resp instanceof PainelRespostaIntervalo)
   return respostaIntervalo; // 604
  if (resp instanceof PainelRespostaNumerica)
   return respostaNumerica; // 601
  if (resp instanceof PainelRespostaPonto)
   return respostaPonto; // 603
  return -1;
  }

 public String getListaArg () {
  return listaArg;
  }

 public int getCodigoAcao () {
  return codigoAcao;
  }

 public String getDescricao () {
  return descricao;
  }

 public String toString () {
  return String.valueOf(codigoAcao)+ " " + listaArg;
  }
 
 public String getAcaoAsString () {
  String index = Integer.toString(codigoAcao);     
  String abre = "{", aux;
  if (codigoAcao < 100) abre += "0";
  if (codigoAcao < 10 ) abre += "0";
  return aux = abre + index + ", " + getDescricao() + ", " + getListaArg() + "}\r\n";     
  }

 public void setListaArg (String listaArg) {
  this.listaArg = listaArg;
  }

 }
