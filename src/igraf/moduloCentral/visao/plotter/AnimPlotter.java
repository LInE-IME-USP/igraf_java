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
 * @see igraf/moduloCentral/visao/plotter/Plotter.java
 *
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloCentral.visao.plotter;

import java.awt.Graphics2D;
import java.util.Vector;


import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.controle.AreaDesenhoController;
import igraf.moduloCentral.controle.desenho.DesenhoAnimacaoController;
import igraf.moduloCentral.visao.desenho.DesenhoAnimacao;
import igraf.moduloSuperior.controle.entrada.Analisa;

import difusor.evento.CommunicationEvent;


public class AnimPlotter extends Plotter {

 DesenhoAnimacaoController dac ; //= new DesenhoAnimacaoController(this);
 //DesenhoTangenteController dtc = new DesenhoTangenteController(this);
 
 Vector listaAnimVisivel = new Vector();

 private AreaDesenhoController adc;
 public void setController(AreaDesenhoController adc) {
  this.adc = adc;
  }

 public void insereDesenho (String funcao, String desc) {
  if (Analisa.temParametro(funcao)) {
  /* DesenhoAnimacao da = new DesenhoAnimacao(this, funcao, ordem++);
   if (dac.insereDesenho(da)){
    listaAnimVisivel.add(da); 
    notifyScreenChanged();
    return;
    }*/
   notifyScreenChanged();
   }
  }

 public void iniciaAnimacao (boolean b) {
  adc.iniciaAnimacao(b);
  }

 public void notifyScreenChanged () {
  adc.enviarEvento(null);
  }

 public void notificaAlteracaoEstado () {
  //dac.notificaAlteracaoEstado();
  }

 public void desenha (Graphics2D g, int w, int h) {
  for (int i=0; i<dac.getNumDesenhos(); i++) {
   DesenhoAnimacao da = (DesenhoAnimacao)dac.getDesenho(i);
   da.atualizaDesenho(g);
   }  
  }

 public Vector getListaAnimacaoVisivel () {
  return dac.getListaDesenho();
  }

 public void trataMenuAnimacao (CommunicationEvent event) {
  dac.trataEvento(event);
  }

 public void trataMenuEdicaoEvent (CommunicationEvent event) {
  if (event.getCommand().equals(ResourceReader.msg("madZoomAmpliar")))
   zoom(zoomDif);
  else
  if (event.getCommand().equals(ResourceReader.msg("madZoomDiminuir")))
   zoom(-zoomDif);  
  else
  if (event.getCommand().equals(ResourceReader.msg("madZoomPadrao")))
   zoomPadrao();
  }

 public int getCorDesenho(String funcao) {
  // TODO Auto-generated method stub
  return 0;
  }

 }
