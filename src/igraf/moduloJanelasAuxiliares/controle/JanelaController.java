/*
 * iGraf : interactive Graphics in the Internet
 * LInE - line.ime.usp.br
 *
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 *
 * @author RP, LOB
 *
 * @description Panel to select the exercise type
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 *
 */

package igraf.moduloJanelasAuxiliares.controle;

import java.awt.Choice;
import java.util.Iterator;
import java.util.Vector;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;

import igraf.IGraf;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.desenho.DesenhoFuncao;

// a lista desenho vis�vel cont�m tudo o que estiver na tela para poder
// contemplar a edi��o de pol�gonos e de anima��es; este controlador deve, portanto,
// implementar filtros... tornar o uso da lista restrito �s classes que necessitam

public abstract class JanelaController extends CommunicationController {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class                                                       
 public static final String IGCLASSPATH = "src/igraf/moduloJanelasAuxiliares/controle/JanelaController.java";

 protected Vector listaDesenho, listaFuncao;

 public JanelaController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }


 public void tratarEventoRecebido (CommunicationEvent ie) {
  if (ie.getCommand().equals(GraphicOnScreenChangedEvent.SCREEN_CHANGED)) {
   GraphicOnScreenChangedEvent g = (GraphicOnScreenChangedEvent) ie; // igraf/moduloCentral/eventos/GraphicOnScreenChangedEvent.java
   listaDesenho = g.getListaDesenhoVisivel();
   listaFuncao = g.getListaFuncaoVisivel();
   // System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": tratarEventoRecebido(CommunicationEvent): ******************** " + ie.getClass().getName());
   // IGraf.launchStackTrace();
   }
  }


 /**
  * Recebe um objeto choice e o preenche apenas com fun��es, desprezando
  * anima��es e pol�gonos.
  * @param choiceFuncoes
  */
 public void preencheChoiceFuncoes (Choice choiceFuncoes) {
  int k;
  try {
   k = choiceFuncoes.getSelectedIndex();
   choiceFuncoes.removeAll();
   //T System.out.println(IGraf.debugErrorMsg(IGCLASSPATH) + ": preencheChoiceFuncoes() ******************** ");

   for (int ii_0=0; ii_0<listaFuncao.size(); ii_0++) {
    //T System.out.println(" - "+ii_0+": "+((DesenhoFuncao)listaFuncao.elementAt(ii_0)).getFuncaoAtual());
    choiceFuncoes.add(((DesenhoFuncao)listaFuncao.elementAt(ii_0)).getFuncaoAtual());
    }     

   //T System.out.println("\nsrc/igraf/moduloJanelasAuxiliares/controle/JanelaController.java: preencheChoiceFuncoes(): #choiceFuncoes=" + choiceFuncoes.size() + ", k=" + k + "");
   choiceFuncoes.getParent().validate();
   choiceFuncoes.select(k);

   } catch (Exception e) { }
  }


 /**
  * Recebe um objeto choice e o preenche com todas as express�es referentes
  * aos desenhos que est�o na tela atualmente.
  * @param choiceDesenho
  */

 // implementar um m�todo que sempre d� a express�o do desenho
 public void preencheChoiceDesenho (Choice choiceDesenho) {
  int k;
  try {
   k = choiceDesenho.getSelectedIndex();
   choiceDesenho.removeAll();
   for (int i = 0; i < listaDesenho.size(); i++) {
    Desenho dsn = (Desenho) listaDesenho.get(i);
    choiceDesenho.add(dsn.toString());
    }
   choiceDesenho.getParent().validate();
   if (k > 0) {
    //T System.out.println("\nsrc/igraf/moduloJanelasAuxiliares/controle/JanelaController.java: preencheChoiceDesenho(): #choiceDesenho=" + choiceDesenho.size() + ", k=" + k + "");
    choiceDesenho.select(k);
    }
   } catch (Exception e) {
    if (IGraf.IS_DEBUG) System.err.println(IGCLASSPATH + ": preencheChoiceDesenho(Choice): " + e.toString());
    }
  }

 public String[] getListaDesenho () {
  String[] lista = new String[listaDesenho.size()];

  for (int i = 0; i < listaDesenho.size(); i++) {
   Desenho d = (Desenho) listaDesenho.get(i);
   lista[i] = d.toString();
   }
  return lista;
  }

 }
