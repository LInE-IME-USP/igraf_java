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
 * @description 
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

package igraf.moduloExercicio.visao;

import igraf.IGraf;
import igraf.basico.event.AttentionToolTip;
import igraf.basico.io.ResourceReader;
import igraf.basico.util.Crypto;
import igraf.basico.util.Funcao;
import igraf.moduloCentral.visao.desenho.DesenhoFuncao;
import igraf.moduloExercicio.controle.JanelaExercicioController;
import igraf.moduloSuperior.controle.entrada.Analisa;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JComboBox; // Choice
import javax.swing.JPanel; // Panel
import javax.swing.JTextField; // TextField

public class PainelRespostaAlgebrica extends PainelRespostaNumerica {


  //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
  public static final String IGCLASSPATH = "igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java: ";

  private  String strItem;
  private int start, end;

  private JComboBox choice;

  public PainelRespostaAlgebrica (JanelaExercicio janelaExercicio, int num) {
    super(janelaExercicio, num);
    //T System.out.println("\nsrc/igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java: num=" + num);
    setLabelText(ResourceReader.msg("exercRespDica"));
    }


  // igraf/moduloExercicio/controle/JanelaExercicioController.java: tratarEventoRecebido(CommunicationEvent)
  public PainelRespostaAlgebrica (JanelaExercicio janelaExercicio, int num, int start, int end, String respGab) {
    this(janelaExercicio, num);
    super.janelaExercicio = janelaExercicio;
    setLimits(start, end);
    respostaGabarito = respGab;
    }


  protected void configurePanelAtRight () {
    if (JanelaExercicio.getModo() == JanelaExercicio.CRIACAO)
      super.configurePanelAtRight();
    else {
      choice = new JComboBox();
      panel = new JPanel(new GridLayout(3,1)); // in 'igraf/moduloExercicio/visao/PainelRespostaNumerica.java'
      adicionaConteudoDireito(choice);
      }
    }


 //D try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }


 // From: igraf.moduloExercicio.controle.JanelaExercicioController.enviaEventoResposta(JanelaExercicioController.java)
  public String getResposta () {
    if (respostaAluno==null || respostaAluno=="") {
      System.err.println(igraf.IGraf.debugErrorMsg(IGCLASSPATH) + " getResposta(): respostaAluno=" + respostaAluno);
      enviaMensagemErro(ResourceReader.msg("exercRAErroCampoResp"));
      return "";
      }
    String s1 = "a:" + String.valueOf(start) + " ";
    String s2 = "b:" + String.valueOf(end) + " ";
    strItem = "Item:" +String.valueOf(numQuest) + " ";

    return strItem + s1 + s2 + "val:" + Crypto.stringToHex(respostaAluno) + " ";
    }


  // From: 'igraf/moduloExercicio/visao/PainelRespostaNumerica.java' in 'public boolean validar(): if (validacaoOk(textFieldEnterExpression)) ...'
  protected boolean validacaoOk (JTextField textField) {
    return validacaoOk(textField, true); // in 'PainelResposta.java'
    }
   
/*
    String str = removeEspacos(textField.getText()); // importante: a expressão de entrada NÃO pode ter espaços
    str = str.replace(',', '.');
    textField.setText(str);
    //T System.out.println(igraf.IGraf.debugErrorMsg(IGCLASSPATH) + " validacaoOk(...): 1: " + str);

    if (str.length() == 0) {
      String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
      String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs);
      this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
      AttentionToolTip.showToolTipText(textFieldEnterExpression, msgErr); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

      indicaCampoComErro(textField);
      enviaMensagemErro(ResourceReader.msg("exercRAErroCampoResp")); //

      return false;
      }

    if (Analisa.temParametro(str)) {
      String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
      String msgErr = ResourceReader.msg("exercRAErroAnimNao");
      this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
      AttentionToolTip.showToolTipText(textFieldEnterExpression, msgErr); // igraf.basico.event.AttentionToolTip: presents for a while a window with this warning

      indicaCampoComErro(textField);
      enviaMensagemErro(msgErr); //
      return false;
      }

    if (Analisa.temParametro(str)) { // igraf.moduloSuperior.controle.entrada.Analisa
      String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
      String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs);
      this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
      AttentionToolTip.showToolTipText(textFieldEnterExpression, msgErr);

      indicaCampoComErro(textField);
      enviaMensagemErro(msgErr);
      System.err.println(igraf.IGraf.debugErrorMsg(IGCLASSPATH) + " validacaoOk(...): the number can not have parameters! As in " + str);
      return false;
      }
    else
    if (Analisa.sintaxeCorreta(str)) { // igraf.moduloSuperior.controle.entrada.Analisa
      return true;
      }
    else {
      String [] msgs = { "" + this.numQuest }; // PainelResposta.java: int numQuest
      String msgErr = ResourceReader.msgComVar("exercRNerrNN", "OBJ", msgs);
      this.janelaExercicio.setMessage(msgErr); // "You must enter a number in the exercise item number $OBJ$"
      AttentionToolTip.showToolTipText(textFieldEnterExpression, msgErr);

      indicaCampoComErro(textField);
      enviaMensagemErro(ResourceReader.msg("exercRAErroExpInv") + str);
      return false;
      }
    } // boolean validacaoOk(JTextField textField)
*/

  public int comparaResposta () {
   maxNumError = 1;
    if (!comparaFuncoes(respostaGabarito, respostaAluno, start, end)) {
      diagnostico += respostaErrada(respostaAluno) + "\r\n";
      registraDiagnostico(diagnostico);
      numErros++;
      }
    else {
      diagnostico += respostaCerta(respostaAluno) + "\r\n";
      registraDiagnostico(respostaCerta(respostaAluno));
      numAcertos++; //L
      //System.out.println("PainelRespostaAlgebrica.comparaResposta(): numAcertos="+numAcertos);
      }

    setValorCorrecao(correcao());
    setValorResposta(respostaAluno);
    return numErros;
    }

  /**
   * Recebe duas funções na forma de strings e os limites de um intervalo numérico.   Compara
   * as duas funções e responde se são equivalentes.  A estratégia de análise das funções consiste
   * em calcular as duas funções para todos os pontos do intervalo fornecido e comparar os valores obtidos.
   * Esse método admite um erro máximo de 1%.
   * @param f
   * @param g
   * @param ini - limite inferior do intervalo de avaliação das funções f e g
   * @param fim - limite superior do intervalo de avaliação das funções f e g
   * @return true se as funções forem equivalentes
   */
  private boolean comparaFuncoes (String f, String g, double ini, double fim) {
    Funcao fx = new Funcao(1);
    Funcao gx = new Funcao(1);
    int contAuxErros = 0;

    fx.constroiExpressao(f);
    gx.constroiExpressao(g);

    for (double i = ini; i < fim; i++) {
      if (Double.isNaN(fx.f(i)))  continue;
      if (!erroAceitavel(fx.f(i), gx.f(i))) contAuxErros++;
      }

    if (contAuxErros > 0) { // 1% da quantidade de pontos testados
      //L System.out.println("PainelRespostaAlgebrica.comparaFuncoes(...): Erro, numAcertos="+numAcertos);
      return false;
      }

    //System.out.println("PainelRespostaAlgebrica.comparaFuncoes(...): numAcertos="+numAcertos);
    return true;
    }


  public void setLimits (int min, int max) {
    start = min;
    end = max;
    }


  public void updateLabels () {
    super.updateLabels();
    setLabelText(ResourceReader.msg("exercRespDica"));
    }


  // From: 'igraf/moduloExercicio/visao/PainelListaResposta.java' 
  protected void setChoiceItens (Vector vector) {
    //System.out.println("src/igraf/moduloExercicio/visao/PainelRespostaAlgebrica.java: setChoiceItens(...): #vector=" + vector.size());
    if (vector != null && choice != null) {
      //R choice.removeAll();
      for (int ii_0=0; ii_0<vector.size(); ii_0++) {
        //R choice.add(((DesenhoFuncao)vector.elementAt(ii_0)).getFuncaoAtual());
        choice.insertItemAt(((DesenhoFuncao)vector.elementAt(ii_0)).getFuncaoAtual(), ii_0);
        }
      //R choice.getParent().validate();
      }
    }


  public boolean validar () {
   if (JanelaExercicio.getModo() == JanelaExercicio.CRIACAO)
     return super.validar();
   else {
     respostaAluno = (String) choice.getSelectedItem();
     return true;
    }
   }

  }
