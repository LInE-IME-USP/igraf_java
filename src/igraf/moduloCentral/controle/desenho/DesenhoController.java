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

import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.plotter.Plotter;

import java.util.Vector;

import difusor.evento.CommunicationEvent;


public abstract class DesenhoController {
 
 protected Vector listaDesenho; // DesenhoPoligonoController; ... DesenhoTextoController
 protected Plotter plotter; // DesenhoPoligonoController; ... DesenhoTextoController
 // public void setListaDesenho (Vector lstDes) { listaDesenho = lstDes; }

 public DesenhoController (Plotter plotter) {
  this.plotter = plotter;
  listaDesenho =  new Vector();
  }

 protected void limpaListaDesenho () {
  listaDesenho.removeAllElements();
  }

 protected void enviarEvento (CommunicationEvent ie) {
  plotter.enviarEvento(ie);
  }

 public Vector getListaDesenho () {
  return listaDesenho;
  }
 
 public Vector getListaDesenhoSemReferencia () {
  Vector aux;  
  if (listaDesenho.size() == 0)
   aux = new Vector();
  else {
   aux = new Vector(listaDesenho);
   }
  return aux;
  }
 
 public void setListaDesenho (Vector listaDesenho) {
  this.listaDesenho = listaDesenho;
  }
 
 public boolean insereDesenho (Desenho d){
  listaDesenho.add(d);
  return true;
  }
 
 void notifyScreenChanged () {
  plotter.notifyScreenChanged();
  }
 
 public int getNumDesenhos () {
  return listaDesenho.size();
  }
 
 public Desenho getDesenho (int index) {
  return (Desenho)listaDesenho.get(index);
  }
 
 /**
  * Recebe uma funcao e verifica se ja esta na listas do plotter, se estiver, seu redesenho
  * sera evitado.
  * @param expr
  * @return
  */
 boolean ehDesenhoVisivel (String expr) {
  Desenho desenho;
  for (int i = 0; i < listaDesenho.size(); i++) {
   desenho = (Desenho)listaDesenho.get(i);   
   if (desenho.toString().equals(expr))
    return true;
   }
  return false;
  }
 
 public abstract void trataEvento (CommunicationEvent ie);

 public void reset () {
  listaDesenho.removeAllElements();  
  }

 }
