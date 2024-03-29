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
 * @description Help integrated to iGraf. Component to explain a menu item: Help
 *
 * @see igraf/moduloAjuda/visao/componentesDoTexto.java
 * @see igraf/moduloAjuda/visao/Topico.java: extends 'componentesDoTexto' to represent a title in any topic in manual
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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map.Entry; // HashMap
import java.util.Iterator;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloAjuda.visao.Help;
import igraf.moduloAjuda.visao.componentesDoTexto.Paragrafo;
import igraf.moduloAjuda.visao.componentesDoTexto.Topico;


public class JPanelBasisTopic extends JPanel {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloAjuda/modelo/JPanelBasisTopic.java";
 //D { System.out.println("AQUI"); try{String str="";System.out.print(str.charAt(3));}catch(Exception e) {e.printStackTrace();} }

 private static final int TOPICHEIGHT = Help.TOPICHEIGHT; // 20
 private static final int TOPICWIDTH = Help.TOPICSWIDTH; // 620

 private int altura = 45;
 private final Dimension dimension = new Dimension(TOPICWIDTH, altura);

 private String rootNameOfTopic; // root name of this topic (must use 'ResourceReader.msg(rootNameOfTopic)' to present in 'Topico' or 'Paragrafo'
 private Topico titleTopic; // reference to the first Topico (with this title)
 private static HashMap hashMapOfItems = new HashMap(); // all item of each topic is here (it could be: Topico or Paragrafo)

 public JPanelBasisTopic (String strTopicName) {
  setLayout(new FlowLayout(FlowLayout.LEFT, 0, 4)); // necessary be FlowLayout since it has an arbitrary number of JPanel (Topico.java or Paragrafo.java)
  //T System.out.println("JPanelBasisTopic.java: topico=" +strTopicName);
  setTitulo(strTopicName); // 'Topico insereTopico(String nomeTopico, int nivel)' precisa receber a raiz!

  // to ensure in Java 4 that space between 'Topico.java' and 'Paragrafo.java' has no background gap
  setBackground(igraf.basico.util.EsquemaVisual.corFundoParagrafos); // same in 'igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java' and 'Topico.java'
  //R setSize(dimension);

  }

 /**
  * Recebe uma string titulo e a posiciona no topo da p�gina de conte�do da ajuda.
  * Caso esse m�todo seja chamado mais de uma vez, o resultado pode ser imprevis�vel.
  * @param String strTitle
  */
 private void setTitulo (final String strTitle) {
  this.titleTopic = insereTopico(strTitle, Topico.TITULO); // TITULO=0 // reference to the first Topico (with this title)
  this.rootNameOfTopic = strTitle;
  }

 // igraf/moduloAjuda/visao/navegador/PainelConteudo.java: 'basicTopic.getRootNameOfTopic()' and 'jPanelBasisTopic.getRootNameOfTopic()'
 public String getRootNameOfTopic () {
  return rootNameOfTopic;
  }


 /**
  * Recebe uma string subTitulo e a posiciona imediatamente ap�s o �ltimo par�grafo
  * editado.  Nesse contexto, a chamada ao m�todo titulo() pode ser considerada como
  * inser��o de um par�grafo.
  * @param String strSessionName
  */
 void insereSessao (String strSessionName) {
  insereTopico(strSessionName, Topico.SESSAO);
  }

 private Topico insereTopico (String rootTopicName, int nivel) {
  Topico topico = null;
  try {
    //T System.out.println("JPanelBasisTopic.java: insereTopico: rootTopicName=" + rootTopicName);

    topico = new Topico(rootTopicName, nivel, TOPICWIDTH); // do not use here 'ResourceReader.msg(...)', it must be used in 'igraf.moduloAjuda.visao.componentesDoTexto.Topico' and 'Paragrafo'

    try { // set a proper height to this topic
      atualizaAltura(topico.getAltura());
    } catch (java.lang.IllegalAccessError ia) {
      System.err.println(IGraf.debugErrorMsg(IGCLASSPATH)+"Erro: "+ia+"\nAcesso ilegal: rootTopicName="+rootTopicName+" nivel="+nivel+" topico="+topico);
      }

    add(topico);
  } catch (Exception e) {
    System.err.println(IGraf.debugErrorMsg(IGCLASSPATH)+"[insereTopico] Erro: "+e+"\nrootTopicName="+rootTopicName+" nivel="+nivel+" topico="+topico);
    }
  hashMapOfItems.put(rootTopicName, topico); // new Topico

  topico.setPreferredSize(new Dimension(TOPICWIDTH, TOPICHEIGHT)); // necessary to fill all height of the topic

  return topico;
  }

 //T static int count=0;

 /**
  * Get an string and put it as title after the last paragraph inserted (igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java)
  * Came from: igraf.moduloAjuda.modelo.TextoGrafico.TextoGrafico() and from others 'Texto*.Texto*()'
  * @param String subTitulo: is the root message (must be showed as 'ResourceReader.msg(subTitulo)')
  */
 void insereSubTitulo (String subTitulo) {
  //T System.out.println(" - " + subTitulo + "");
  insereTopico(subTitulo, Topico.SUBTITULO);
  }


 /**
  * Get an string and put it as paragraph after the last paragraph or title (igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java)
  * Came from: igraf.moduloAjuda.modelo.TextoGrafico.TextoGrafico() and from others 'Texto*.Texto*()'
  * @param String text: is the root message (must be showed as 'ResourceReader.msg(text)')
  */
 void insereParagrafo (String text) {
  //T System.out.println("   " + text + "");
  Paragrafo paragrafo = new Paragrafo(text, TOPICWIDTH);
  atualizaAltura(paragrafo.getAltura()); // important to extend the topic explation height
  hashMapOfItems.put(text, paragrafo); // new Paragrafo
  add(paragrafo);
  }

 /**
  * Recebe uma string nomeFigura, tenta carregar a figura dentre os recursos dipon�veis e,
  * estando a figura pronta, exibe-a posicionando imediatamente ap�s o �ltimo par�grafo
  * editado.  Nesse contexto, a chamada ao m�todo titulo() pode ser considerada como
  * inser��o de um par�grafo.   Para que essa figura possa ser carregada corretamente, deve
  * ser colocada dentro do diret�rio igraf.Resource. Devolve falso caso n�o seja
  * poss�vel carregar a figura.
  * @param titulo
  */
 boolean insereFigura (String nomeFigura) {
  return false;
  }

 private void atualizaAltura (int valor) {
  dimension.setSize(TOPICWIDTH, altura += valor + 10);
  }

 public Dimension getPreferredSize () {
  return dimension; // ensure a proper size to topics and paragraphs
  }

 public Insets getInsets () {
  return new Insets(0,4,4,4); // (0,10,10,10);
  }

 public String toString () {
  return rootNameOfTopic;
  }


 public static void updateLabels () {
  // insereSubTitulo: insereTopico: igraf/moduloAjuda/visao/componentesDoTexto/Topico.java
  // insereParagrafo:               igraf/moduloAjuda/visao/componentesDoTexto/Paragrafo.java
  Topico topico = null;
  Paragrafo paragrafo;
  Object objValue;
  String strKey, strValue;
  int count = 0;
  //T System.out.println("JPanelBasisTopic.java: updateLabels()");

  Iterator listIterator = hashMapOfItems.entrySet().iterator();  // hashMapOfItems.values().iterator();
  while (listIterator.hasNext()) {
    Entry thisEntry = (Entry) listIterator.next();
    strKey = (String) thisEntry.getKey();
    objValue = thisEntry.getValue();
    if (objValue instanceof Topico) {
      topico = (Topico)objValue;
      topico.setText(ResourceReader.msg(strKey));
      }
    else
    if (objValue instanceof Paragrafo) {
      paragrafo = (Paragrafo)objValue;
      paragrafo.setText(ResourceReader.msg(strKey));
      }
    //T System.out.println(" " + count + "." + strKey);
    count++;
    }
  //T System.out.println("JPanelBasisTopic.java: updateLabels(): fim");

  } //  public void updateLabels()

 }
