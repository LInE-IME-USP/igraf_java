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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloCentral.controle.desenho;

import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.plotter.GraphPlotter;

import java.util.Vector;

import difusor.evento.CommunicationEvent;


public class DesenhoFuncaoController extends DesenhoController {


 /**
  * Lista que mant�m os pol�gonos que est�o atualmente na tela para que seja poss�vel a detec��o do mouse sobre alguma curva, bem como
  * o redesenho usando a mesma cor com que um pol�gono foi desenhado pela primeira vez.
  * Al�m disso, esta lista permite a recupera��o de todas as express�es editadas quando n�o houver gr�ficos na tela.
  */
 protected Vector listaDesenhoOculto  =  new Vector();
 // private int indexLastFunction;

 public DesenhoFuncaoController (GraphPlotter plotter) {
  super(plotter);
  }

 public void trataEvento (CommunicationEvent ie) {
  //IgrafMenuGraficoEvent imge = (IgrafMenuGraficoEvent)ie; 
  //String command = imge.getCommand();   
  //notifyScreenChanged();
  }


 /**
  * Recebe um desenho 'desenho' e insere na lista de desenhos vis�veis, o que faz com que o gr�fico associado seja exibido na �rea de desenho.
  * @param funcao
  */
 public boolean insereDesenho (Desenho desenho) {
  int indexLastFunction = indexOfFunctionOffScreen(desenho.toString());

  if (indexLastFunction > -1 & ehDesenhoVisivel(desenho.toString()))
   return false;

  if (indexLastFunction < 0) { // error...
   listaDesenhoOculto.addElement(desenho);
   listaDesenho.add(desenho);
   }
  else // expression not been showed
   if (!ehDesenhoVisivel(desenho.getFuncaoAtual())) {
    desenho.setColorIndex(indexLastFunction);
    listaDesenho.add(desenho);
    }
  notificaAlteracaoEstado();
  return true;
  }


 private int indexOfFunctionOffScreen (String descricao) {
  for (int i = 0; i < listaDesenhoOculto.size(); i++) {
   Desenho desenho = (Desenho) listaDesenhoOculto.elementAt(i);
   if (desenho.toString().equals(descricao))
    return desenho.getOrdem();
   }
  return -1;
  }


 public void desenharTodos () {
  for (int i = 0; i < listaDesenhoOculto.size(); i++) {
   insereDesenho((Desenho)listaDesenhoOculto.get(i));
   }
  }

 public Vector getListaDesenhoOculto () {
  return listaDesenhoOculto;
  }
 
 public int getNumDesenhoOculto () {
  return listaDesenhoOculto.size();
  }

 public Desenho getDesenhoOculto (int index) {
  return (Desenho)listaDesenhoOculto.get(index);
  }

 public void ocultaTodosGraficos () {
  listaDesenho.removeAllElements();
  notificaAlteracaoEstado();
  }

 public void ocultaDesenho (Desenho desenho) {
  listaDesenho.remove(desenho);
  notificaAlteracaoEstado();
  }


 // From: igraf.moduloCentral.visao.plotter.GraphPlotter.removeGrafico(GraphPlotter.java:426)
 public void removeDesenho (Desenho desenho) {
  //T //try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e1) {e1.printStackTrace(); }
  //T System.out.println("igraf/moduloCentral/controle/desenho/DesenhoFuncaoController.java: desenho=" + desenho);
  //T System.out.println("igraf/moduloCentral/controle/desenho/DesenhoFuncaoController.java: #listaDesenhoOculto=" + listaDesenhoOculto.size() + ",  #listaDesenho=" + listaDesenho.size());
  listaDesenhoOculto.remove(desenho);
  listaDesenho.remove(desenho);
  //T System.out.println("igraf/moduloCentral/controle/desenho/DesenhoFuncaoController.java: #listaDesenhoOculto=" + listaDesenhoOculto.size() + ",  #listaDesenho=" + listaDesenho.size());
  this.notificaAlteracaoEstado(); 
  }

 public void removerTodos () {
  listaDesenhoOculto.removeAllElements();
  listaDesenho.removeAllElements();
  notificaAlteracaoEstado();
  }

 public void notificaAlteracaoEstado () {
  IgrafTabUpdateEvent iue = new IgrafTabUpdateEvent(this, IgrafTabUpdateEvent.FUNCTION_LIST_CHANGED);
  iue.notificaDesenhoOculto(getNumDesenhoOculto() > 0);
  iue.setFunctionList(listaDesenho);
  enviarEvento(iue);
  }

 public void reset () {
  super.reset();
  listaDesenhoOculto.removeAllElements();
  }

 }
