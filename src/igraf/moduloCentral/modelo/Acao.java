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
 * @description Uma Ação é um objeto que registra e identifica o uso, pelo usuário, de uma funcionalidade do programa.
 * Para inserir uma nova ação no programa você, supostamente um programador, deve seguir os passos abaixo:<br>
 * Crie um rótulo (String) para a ação na lista apropriada, p.e. NOVA_ACAO_DESENHO na listaAcaoDesenho ;<br>
 * Crie o mesmo rótulo em igraf.resource.StringsTable.properties;<br>
 * Crie uma constante inteira na lista deste mesmo arquivo e associe-a à nova ação usando preferencialmente um nome coerente p.e. inserirNovaAcao = k;<br>
 * Use um 'switch/case' no método Sessao.refazGrafico() usando esse identificador como chave de seleção<br>
 * Coloque uma chamada para Sessao.insereAcao() no comando que dispara essa nova ação.<br>
 *
 * @see igraf/moduloCentral/modelo/Sessao.java: public boolean registraEvento(CommunicationEvent communicationEvent): listaAcao.addElement(new Acao(code, param));
 * @see igraf/ContentPane.java: makes 'ModuloExercicio me = new ModuloExercicio();'
 * @see igraf/moduloExercicio/ModuloExercicio.java: makes 'JanelaExercicioController jec = new JanelaExercicioController(this, true);'
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 * @description
 * @author RP, LOB
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
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
  * Lista que contém todas as ações referentes ao desenho no iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a zero e menor que 100 
  */
 private static final String [] listaAcaoDesenho = {
   "DESENHO_INDIVIDUAL", "DESENHO_MULTIPLO",
   "ESCONDER_ULTIMO_GRAFICO", "DELETAR_GRAFICO_SELECIONADO",
   "DELETAR_TODOS_GRAFICOS", "ESCONDER_TODOS_GRAFICOS", "EDITAR_FUNCAO", "INSERIR_ABA",
   " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", 
   " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "POLIGONO", "UNDO_POLIGONO", "REDO_POLIGONO"};

 /** 
  * Lista que contém todas as ações referentes à animação no iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a 100 e menor que 200 
  */
 private static final String [] listaAcaoAnimacao = {"ANIMACAO", "RASTRO_NA_TELA", "AUMENTAR_VELOCIDADE_ANIMACAO", "DIMINUIR_VELOCIDADE_ANIMACAO"};

 /** 
  * Lista que contém todas as ações referentes à exibição dos eixos no iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a 200 e menor que 300 
  */
 private static final String [] listaAcaoEixos = {"EIXOS", "ESCALA", "GRADE", "DESLOCAR_ORIGEM"};

 /** 
  * Lista que contém todas as ações referentes às ferramentas do Cálculo no iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a 300 e menor que 400 
  */
 private static final String [] listaAcaoCalculo = {"TRACAR_DERIVADA", "TRACAR_TANGENTE", "CALCULAR_INTEGRAL", "INT_INDEFINIDA"};

 /** 
  * Lista que contém todas as ações referentes ao <i>zooming</i> no iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a 400 e menor que 500 
  */
 private static final String [] listaAcaoZoom = {"MAIS_ZOOM", "MENOS_ZOOM", "ZOOM_PADRAO"};

 /** 
  * Lista que contém todas as ações referentes à inserção de texto
  * na área de desenho do iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a 500 e menor que 600 
  */
 private static final String [] listaAcaoTexto = {"INSERE_TEXTO", "APAGA_TEXTO", "EDITA_TEXTO",  "ATUALIZA_POSICAO_TEXTO"};

 /** 
  * Lista que contém todas as ações referentes aos exercícios no iGraf.
  * Todos os valores associados a essa lista têm identificador inteiro
  * maior ou igual a 600 e menor que 700 
  */
 private static final String [] listaAcaoExercicio = {
   "HABILITA_ENVIO_RESPOSTA", "RESPOSTA_NUMERICA", "RESPOSTA_ALGEBRICA",
   "RESPOSTA_PONTO", "RESPOSTA_INTERVALO", "INSERIR_COMENTARIO", "HABILITA_VISAO_RESPOSTA", 
   "RESPOSTA_OBJETIVA", "RESPOSTA_DISCURSIVA", "DESABILITA_MENU", "INSERIR_COMENTARIO_ALUNO"};

 /**
  * Tabela de ações(funcionalidades) disponíveis no iGraf
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
  * Lista de constantes inteiras que identificam ações realizáveis pelo sistema
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

 // constantes associadas à animação
  animar =              100,
  deixarRastro =        101,
  aumentarVelocidade =  102,
  diminuirVelocidade =  103,

  // constantes associadas ao desenho dos eixos
  exibirEixos =     200,
  exibirEscala =    201,
  exibirGrade =     202,
  deslocarOrigem =  203,

  // constantes associadas às ferramentas do Cálculo
  derivar =          300,
  desenhaTangente =  301,
  intDefinida =      302,
  intIndefinida =    303,

  // constantes associadas ao estado do plano cartesiano
  mudaPlanoCartesiano    = 400,
  diminuirZoom           = 401,
  zoomPadrao             = 402,

  // constantes associadas à manipulação de textos
  inserirTexto           = 500,
  apagarTexto            = 501,
  editarTexto            = 502,
  atualizarPosicaoTexto  = 503,

  // constantes associadas a manipulação de exercícios
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

 // String que contém argumentos para a ação separados por vírgula
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
 // devolve núm. das ação correspondentes ao tipos de exercício
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
