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
 * @description Model to load and save files. It register in session all actions performed.
 * 
 * @see igraf/IGraf.java: start any construction in GRF file
 * @see igraf/moduloJanelasAuxiliares/visao/JanelaHistorico.java: this is the window where sessions' action is presented
 * @see igraf/moduloArquivo/eventos/IgrafMenuArquivoEvent.java
 * @see igraf/moduloArquivo/ModuloArquivo.java: instantiate this class ('ArquivoController ac = new ArquivoController(this, true);')
 * @see igraf/moduloArquivo/Arquivo.java: where a file is really saved
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloArquivo.modelo;

import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.Sistema;
import igraf.moduloArquivo.controle.ArquivoController;
import igraf.moduloArquivo.eventos.IgrafMenuArquivoEvent;
import igraf.moduloCentral.eventos.DesenhoTextoEvent;
import igraf.moduloCentral.eventos.EstadoTelaEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuEdicaoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuGraficoEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuPoligonoEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloCentral.modelo.Sessao;
import igraf.moduloExercicio.eventos.DiagnosticEvent;
import igraf.moduloExercicio.eventos.RespostaAlgebricaEvent;
import igraf.moduloExercicio.eventos.RespostaDiscursivaEvent;
import igraf.moduloExercicio.eventos.RespostaIntervaloEvent;
import igraf.moduloExercicio.eventos.RespostaNumericaEvent;
import igraf.moduloExercicio.eventos.RespostaPontoEvent;
import igraf.moduloExercicio.visao.PainelResposta;
import igraf.moduloExercicio.visao.menuSelector.DisableMenuEvent;
import igraf.moduloJanelasAuxiliares.eventos.EdicaoExpressaoEvent;
import igraf.moduloJanelasAuxiliares.eventos.JanelaIntegralEvent;
import igraf.moduloJanelasAuxiliares.eventos.JanelaTangenteEvent;
import igraf.moduloSuperior.eventos.EntradaExpressaoEvent;


public class ArquivoModel {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloArquivo/modelo/ArquivoModel.java";

         //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": refazGrafico("+key+","+arg+"): ");
         //T IGraf.launchStackTrace();

 private Sessao sessao;

 // o tamanho da sessão é a quantidade de ações realizadas pelo usuário
 private int tamanhoSessao = 0, numLinha = 0, ultimaLinha;
 private boolean sessionChanged = false;
 private Vector listaSessao = new Vector();
 private Vector listaAcao;

 private ArquivoController controller;


 // at igraf.moduloArquivo.modelo.ArquivoModel.setController(ArquivoModel.java:70)
 // at igraf.moduloArquivo.controle.ArquivoController.<init>(ArquivoController.java:44)
 // at igraf.moduloArquivo.ModuloArquivo.<init>(ModuloArquivo.java:23)
 // at igraf.ContentPane.<init>(ContentPane.java:45)
 // at igraf.IGraf.init(IGraf.java:248)
 // at igraf.IGraf.IGraf(IGraf.java:157)
 // at igraf.IGraf.main(IGraf.java:422)
 public void setController (ArquivoController controller) {
  this.controller = controller;
  // try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }
  }


 // Each session is associated with a single tab
 public void insereSessao (Sessao sessao) {
  int sizeOfSession = listaSessao.size();
  //T System.out.println("src/igraf/moduloArquivo/modelo/ArquivoModel.java: insereSessao: #" + sizeOfSession);
  sessionChanged = sessao.getListaAcao().size() > 0;
  for (int i = 0; i < sizeOfSession; i++) {
   this.sessao = (Sessao)listaSessao.get(i);
   if (this.sessao.getTabIndex() == sessao.getTabIndex()) {
    listaSessao.setElementAt(sessao, i);
    //T System.out.println("src/igraf/moduloArquivo/modelo/ArquivoModel.java: insereSessao: "+this.sessao.getTabIndex()+"=="+sessao.getTabIndex()+": i="+i);
    return;
    }
   }
  listaSessao.add(sessao);
  this.sessao = sessao;
  }

 public void sessionChanged (boolean c) {
  sessionChanged = c;
  }

 public boolean sessionChanged () {
  return sessionChanged;
  }


 // Get iGraf current session
 // From: igraf/moduloArquivo/Arquivo.java: 'constroiArquivo()' and 'codigoApplet()'
 public String getListaSessaoAsString () {
  int sizeOfSession = listaSessao.size();
  Collections.sort(listaSessao);
  String strContent = igraf.moduloArquivo.Arquivo.cabecalho(); // header to iGraf file

  //T System.out.println("src/igraf/moduloArquivo/modelo/ArquivoModel.java: getListaSessaoAsString: #" + sizeOfSession);

  for (int i=0; i < sizeOfSession; i++) {
   sessao = (Sessao)listaSessao.get(i);
   //strContent += "\r\n[aba " + i + "]\r\n";
   strContent += sessao.getSessaoAsString();
   }
  return strContent;
  }

 public void reset () {
  controller.enviarEvento(new ResetEvent(this));
  listaSessao = new Vector();
  tamanhoSessao = 0;
  numLinha = 0;
  sessionChanged = false;
  }

 public int getTamanhoSessao () {
  return tamanhoSessao;
  }


 // If it is the last action of this session => put a message END in the windos 'JanelaHistorico'
 public void nextStep (Acao action) {
  int k = action.getCodigoAcao();
  String arg = action.getListaArg();
  refazGrafico(k, arg);
  // igraf.moduloArquivo.controle.ArquivoController controller
  controller.enviarEvento(new IgrafMenuArquivoEvent(this,action.getDescricao()+ " " + arg));

  if (ultimaLinha == numLinha) {
   
   controller.enviarEvento(new IgrafMenuArquivoEvent(this, ResourceReader.msg("FIM") + "\n")); // igraf.moduloArquivo.eventos.IgrafMenuArquivoEvent
   // In 'JanelaHistorico.java' it makes: setEnabledButton("stepFront", false) and setEnabledButton("forward", false)
   }

  }

 public void restabelerSessao () {
  while(numLinha < ultimaLinha)
   nextStep((Acao)listaAcao.get(numLinha++));
  numLinha = 0;
  }

 public void carregarListaAcao () {
  listaAcao = sessao.getListaAcao();
  ultimaLinha = listaAcao.size();
  }

 public void proximaAcao () {
  if (numLinha < ultimaLinha)
   nextStep((Acao)listaAcao.get(numLinha++));
  else
   numLinha = 0;
  }



 //_________________________________________________________________________________________

 private static boolean onePrint = false; //DEBUG


 // Monta vetor com todas as linhas de instrução: cercadas por { e  }; ignore as demais
 // Call from event created in: IGraf. IGraf(String[] args): igc.disseminarEventoExternamente(*) (com *='new LoadingIGrafEvent(this, strFileContent))' or 'new LoadingAppletEvent(this, strFileContent))')
 public static String [] limpaLinha (String strArq) {
  String [] linhas;
  Vector vec = new Vector();
  String lnh="", lnhAux="";
  int tam;
  int ii_0=0, cont=0; // 0='{';  1=comando; 2='}'
  StringTokenizer st;

  //T System.out.println("ArquivoModel.java\n--------------");

  if (strArq==null || strArq=="")
     return null;

  // Ignore lines starting with '#'
  // Verifica se existe marca de linha para exercício: "# exercise: web"
  // Preserve just the content, with no comment line (started with '#')
  int index = strArq.indexOf("{");
  strArq = strArq.substring(index, strArq.length()).trim(); // forget lines starting with '#'

  //T System.out.println(strArq+"\n--------------\n");

  //CAUTION: it is necessary to use only these 2 symbols, with no space beetwen them
  st = new StringTokenizer(strArq, "{}", true);

  while (st.hasMoreTokens()) {
   lnh = st.nextToken();
   lnh = lnh.trim(); // limpa brancos iniciais e finais
   //T System.out.println(ii_0 + ": '" + lnh + "'");
   if (lnh=="" || lnh.length()==0) // necessário qdo não é via Web: o '\n' define novo 'token'
    continue; // tente próximo token
   if (ii_0%3 == 0) { // se comando => deve ser '{' => início de comando
    if (lnh.charAt(0)!='{')
     continue; // não é válido
    ii_0 = 1; // prepara para próximo: núcleo do comando
    }
   else if (ii_0%3 == 1) { // se comando => deve ser o núcleo do comando
     lnhAux = lnh;
     ii_0 = 2; // prepara para próximo: final do comando
     }
    else {           // se comando => deve ser '}' => final de comando
     tam = lnh.length();
     if (lnh.charAt(tam-1)!='}')
      continue; // invalid line!
     // valid line: {... }
     lnh = "{" + lnhAux + " }";
     //T System.out.println(ii_0 + ": '" + lnh + "' <--------------------- ");
     cont++;
     vec.addElement(lnh); // = lnh.substring(1,lnh.length()-1); //
     ii_0 = 0; // prepara para próximo: início de comando
     }
   } // while (st.hasMoreTokens())

  //T System.out.println("ArquivoModel.java\n--------------");
  linhas = new String[vec.size()];
  for (int i=0; i<vec.size(); i++) {
   linhas[i] = (String)vec.elementAt(i);
   //T System.out.println(i + ": '" + linhas[i] + "'");
   }

  return linhas;

  } //  public static String [] limpaLinha(String strArq)


 private boolean carregando, anotouExercicio;
 private Vector vetSessao, vetorRespostas, vetSessaoCripto, vetSessaoCriptoIndice;

 // Reconstrói sessão a partir de conteúdo de arquivo em 'str_arq'
 public void refazSessao (String str_arq) {
  String [] listaLinhas = limpaLinha(str_arq); // monta vetor com todas as linhas de instrução: cercadas por { e  }; ignore as demais
  String linha, arg = "" ;
  carregando = true;
  int key;

  vetSessao = new Vector(); // vetor para arquivar linhas de sessão (todas, sem criptografia)
  vetorRespostas = new Vector(); // limpa vetor com itens tipo 'exercicio/Resposta.java'
  // exercicio.FrameQuestaoResposta.setRespAluno(false); // define como não sendo resposta de aluno
  vetSessaoCripto = new Vector(); // vetor para arquivar linhas criptografadas
  vetSessaoCriptoIndice = new Vector(); // vetor para arquivar linhas criptografadas

  //T System.out.println("----------------------\n"+"Sessao.refazSessao: #listaLinhas=" + listaLinhas.length);
  for (int i=0; i<listaLinhas.length; i++) {
   linha = listaLinhas[i];
   key = -1; arg = linha;
   if (linha.charAt(0) == '{') try { //
    key   = getKey(linha);
    arg   = getArg(linha);
    //T System.out.println(i + ": '" + key + "' = '" + arg + "'");
    refazGrafico(key, arg);
    } catch (Exception e) { System.err.println("Erro: ao ler arquivo, na linha "+i+" chave="+key+": "+linha+": "+e);  }
   //T else System.out.println(i + ": '" + linha + "'");

   }
  //T System.out.println("----------------------");

  if (anotouExercicio) { // has exercise: && Acao.ehExercicio(acao)
   // Resposta [] vetResp = getAcoesResposta();
   // System.out.println("Sessao.refazSessao: anotouExercicio=" + anotouExercicio+" #vetResp="+vetResp.length);
   // av.geraGabarito(vetResp); // insere os itens de exercício para a interface 'exercicio/FrameQuestaoResposta.java'
   }

  anotouExercicio = false; // para anotar uma única vez no arquivo a instrução '{600, arquivo contendo exercício, null }'
  carregando = false;
  }



 private void refazGrafico (int key, String arg) {
  PainelResposta resp = null; // se for exercício, anote em 'Vector vetorRespostas'

  //System.out.println((k<10?"0":"") + k++ + ". code: " + key + "  arg: " + arg);

  try {
   switch (key) {
    // Graphics
    case Acao.desenhar:      controller.enviarEvento(new EntradaExpressaoEvent(this, arg)); break;
    case Acao.desenharTodos: controller.enviarEvento(new IgrafMenuGraficoEvent(this, IgrafMenuGraficoEvent.DRAW_ALL)); break;
    case Acao.apagarUltimo:  controller.enviarEvento(new IgrafMenuGraficoEvent(this, IgrafMenuGraficoEvent.HIDE_LAST));break;
    case Acao.apagarGrafico: controller.enviarEvento(new IgrafMenuGraficoEvent(this, arg, IgrafMenuGraficoEvent.HIDE_GRAPH));break;
    case Acao.removerTodos:  controller.enviarEvento(new IgrafMenuGraficoEvent(this, IgrafMenuGraficoEvent.DELETE_ALL)); break;
    case Acao.apagarTodos:   controller.enviarEvento(new IgrafMenuGraficoEvent(this, IgrafMenuGraficoEvent.HIDE_ALL)); break;
    case Acao.editarFuncao:  controller.enviarEvento(new EdicaoExpressaoEvent(arg, this)); break;
    case Acao.inserirAba:    controller.enviarEvento(new IgrafMenuGraficoEvent(this, IgrafMenuGraficoEvent.INSERT_TAB)); break;

    case Acao.criarPoligono:    controller.enviarEvento(new IgrafMenuPoligonoEvent(this, IgrafMenuPoligonoEvent.LOAD_POLYGON, arg)); break;
    case Acao.desfazerPoligono: controller.enviarEvento(new IgrafMenuPoligonoEvent(IgrafMenuPoligonoEvent.UNDO, this)); break;
    case Acao.refazerPoligono:  controller.enviarEvento(new IgrafMenuPoligonoEvent(IgrafMenuPoligonoEvent.REDO, this)); break;

    // Animation
    //TODO: register or not?
    case Acao.animar:   break;
    case Acao.deixarRastro:       break;
    case Acao.aumentarVelocidade: break; // aumentar velocidade da animação; break;
    case Acao.diminuirVelocidade: break; // diminuir velocidade da animação; break;

    // Axes
    case Acao.exibirEixos:    controller.enviarEvento(new IgrafMenuEdicaoEvent(this, IgrafMenuEdicaoEvent.LOAD_INFO, Acao.exibirEixos));  break;
    case Acao.exibirEscala:   controller.enviarEvento(new IgrafMenuEdicaoEvent(this, IgrafMenuEdicaoEvent.LOAD_INFO, Acao.exibirEscala));  break;
    case Acao.exibirGrade:    controller.enviarEvento(new IgrafMenuEdicaoEvent(this, IgrafMenuEdicaoEvent.LOAD_INFO, Acao.exibirGrade));  break;

    // Calculus
    case Acao.derivar:         controller.enviarEvento(new IgrafMenuCalculoEvent(this, arg, IgrafMenuCalculoEvent.DERIVATE));  break;
    case Acao.desenhaTangente: controller.enviarEvento(new JanelaTangenteEvent(this, arg)); break;
    case Acao.intDefinida:     controller.enviarEvento(new JanelaIntegralEvent(this, arg)); break;
    case Acao.intIndefinida:   controller.enviarEvento(new IgrafMenuCalculoEvent(this, arg, IgrafMenuCalculoEvent.INTEGRATE));
         // igraf.moduloArquivo.controle.ArquivoController controller - estende 'difusor.controle.CommunicationController'
         // difusor/CommunicationFacade.java: tem 'Vector listaOuvintesExternos' e 'Vector listaOuvintesInternos'
         break;

    // case Acao.habilitarEnvioResposta: // para arquivo que contém exercício a responder
    //   RespostaEvent re = new RespostaEvent(this); re.setCommand(RespostaEvent.READ_EXERCISE); controller.enviarEvento(re);  break;

    // Exercise
    case Acao.habilitarEnvioResposta: return;
    case Acao.respostaNumerica:  controller.enviarEvento(new RespostaNumericaEvent(arg, this))  ; break;
    case Acao.respostaAlgebrica: controller.enviarEvento(new RespostaAlgebricaEvent(arg, this)) ; break;
    case Acao.respostaPonto:     controller.enviarEvento(new RespostaPontoEvent(arg, this))     ; break;
    case Acao.respostaIntervalo: controller.enviarEvento(new RespostaIntervaloEvent(arg, this)) ; break;
    case Acao.inserirComentario: controller.enviarEvento(new RespostaDiscursivaEvent(arg, this, Acao.inserirComentario)); break;
    case Acao.desabilitaMenu:    controller.enviarEvento(new DisableMenuEvent(this, arg))       ; break;
    case Acao.habilitarVisaoResposta: // para arquivo que contém exercício respondido
      controller.enviarEvento(new DiagnosticEvent(this,DiagnosticEvent.LOAD_RESULT , "",  Acao.habilitarVisaoResposta));  break;
    case Acao.respostaObjetiva:
      controller.enviarEvento(new DiagnosticEvent(this,DiagnosticEvent.LOAD_RESULT , arg,  Acao.respostaObjetiva));  break;
    case Acao.respostaDiscursiva:
      controller.enviarEvento(new DiagnosticEvent(this,DiagnosticEvent.LOAD_RESULT , arg,  Acao.respostaDiscursiva));  break;
    case Acao.inserirComentarioAluno:
      controller.enviarEvento(new DiagnosticEvent(this,DiagnosticEvent.LOAD_RESULT , arg, Acao.inserirComentarioAluno));  break;

    case Acao.deslocarOrigem:
    case Acao.mudaPlanoCartesiano:

    // Zoom
    case Acao.diminuirZoom:
    case Acao.zoomPadrao: controller.enviarEvento(new EstadoTelaEvent(this, arg));  break;

    // Edition: text
    case Acao.inserirTexto: DesenhoTextoEvent.textId++; controller.enviarEvento(new DesenhoTextoEvent(this, arg, DesenhoTextoEvent.INSERT_TEXT)); break;
    case Acao.apagarTexto: controller.enviarEvento(new DesenhoTextoEvent(this, arg, DesenhoTextoEvent.ERASE_TEXT)); break;
    case Acao.editarTexto: break;
    case Acao.atualizarPosicaoTexto: break;

    default: System.err.println("Erro no carregamento do arquivo\n Não foi possivel anotar acao "+key+": "+arg);
    }
   } catch (RuntimeException e) {e.printStackTrace(); }

  if (resp!=null)
   vetorRespostas.addElement(resp);

  } // void refazGrafico(int key, String arg)


 public static String getArg (String linha) {
  int virg  = linha.indexOf(',', 6) + 1;
  int fecha = linha.length() - 1;
  return linha.substring(virg, fecha);
  }

 public static int getKey (String linha) {
  int aux = linha.indexOf(',');
  String oper  = linha.substring(1, aux);
  return Integer.parseInt(oper);
  }

 }
