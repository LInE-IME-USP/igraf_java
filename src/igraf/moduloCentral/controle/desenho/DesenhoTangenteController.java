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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.controle.desenho;

import igraf.moduloCentral.visao.desenho.DesenhoTangente;
import igraf.moduloCentral.visao.plotter.GraphPlotter;
import igraf.moduloJanelasAuxiliares.eventos.JanelaTangenteEvent;

import java.util.Vector;

import difusor.evento.CommunicationEvent;


public class DesenhoTangenteController extends DesenhoController {

 boolean multiDrawMode;
 private DesenhoTangente dt;

 public DesenhoTangenteController (GraphPlotter p) {
  super(p);
  listaDesenho = new Vector();
  }

 public void removeTangente () {
  try {
   listaDesenho.removeElementAt(
   listaDesenho.size()-1);
   } catch (RuntimeException e) { }
  }


 public void removeTangente (String funcao) {
  //T System.out.println("src/igraf/moduloCentral/controle/desenho/DesenhoTangenteController.java: funcao=" + funcao);
  String aux;
  //T int index = -1;
  for (int ii_0 = 0; ii_0 < listaDesenho.size(); ii_0++) {
   dt = (DesenhoTangente) listaDesenho.elementAt(ii_0);
   aux = dt.getFuncaoOriginal();
   if (aux.equals(funcao)) {
    listaDesenho.remove(ii_0);
    //T index = ii_0;
    return;
    }
   }
  }


 public void insereDesenhoTangente (String funcao, double valorX) {
  int corPai = plotter.getCorDesenho(funcao);
    
  try {
   listaDesenho.addElement(new DesenhoTangente(plotter, funcao, corPai, valorX));
  } catch (Exception e) {
   System.out.println("GerenciadorTangente.removeTodasTangentes/nErro: cor inconsistente"); 
   e.printStackTrace();
   }
  multiDrawMode = false;
  }

 public boolean atualizaDesenhoTangente (String funcao, double x) {
  int k = -1;
  for (int i = listaDesenho.size()-1; i > -1; i--) {   
   if (listaDesenho.elementAt(i).toString().equals(funcao))
    k = i;   
   }
  
  if (k > -1) {
   dt = (DesenhoTangente)listaDesenho.elementAt(k);
   dt.setValorX(x);
   return true; // encontrou e atualizou a última tangente inserida
   }
  
  return false; // não encontrou
  }


 public String getFx (String funcao) {
  DesenhoTangente dt;

  for (int i = 0; i < listaDesenho.size(); i++) {    
   dt = (DesenhoTangente)listaDesenho.elementAt(i);
   if (dt.toString().equals(funcao))
    return dt.getFx();
   }

  return "";
  } 

 public void setAnimacaoTangente (String funcao, boolean animar) {
  for (int i = 0; i < listaDesenho.size(); i++) {
   dt = (DesenhoTangente) listaDesenho.elementAt(i);   
   dt.setAnimacao(animar);
   }
  }

 public void trataEvento (CommunicationEvent ie) {
  JanelaTangenteEvent jte = (JanelaTangenteEvent)ie;
  String cmd = jte.getCommand();
  
  if (cmd.equals(JanelaTangenteEvent.DRAW)) {
   
   if (multiDrawMode) {
    insereDesenhoTangente(jte.getFuncaoAtual(), jte.getValorX());
    return;
    }
   
   if (!atualizaDesenhoTangente(jte.getFuncaoAtual(), jte.getValorX()))
    insereDesenhoTangente(jte.getFuncaoAtual(), jte.getValorX());
   }
  
  if (cmd.equals(JanelaTangenteEvent.ERASE)) {
   listaDesenho.removeAllElements();
   }
  else
  if (cmd.equals(JanelaTangenteEvent.ANIMATE)) {   
   setAnimacaoTangente(jte.getFuncaoAtual(), jte.getAnimationMode());
   multiDrawMode = false;
   }
  else
  if (cmd.equals(JanelaTangenteEvent.MULTIDRAWMODE)) {
   multiDrawMode = jte.getDrawMode();
   }
  }


 }
