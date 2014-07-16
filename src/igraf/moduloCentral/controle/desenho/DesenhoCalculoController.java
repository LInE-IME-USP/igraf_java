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
 * @see igraf/moduloSuperior/visao/PainelBotoes.java: creates JButton
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new' para 'MenuGrafico(this, index)'
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

import igraf.basico.io.ResourceReader;
import igraf.basico.util.GeraPoligono;
import igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.desenho.DesenhoRegiaoIntegral;
import igraf.moduloCentral.visao.plotter.GraphPlotter;
import igraf.moduloJanelasAuxiliares.eventos.JanelaIntegralEvent;

import java.util.Vector;

import difusor.evento.CommunicationEvent;

public class DesenhoCalculoController extends DesenhoController {

 private GraphPlotter plotter;
 private Vector listaDesenhoOculto = new Vector();

 public DesenhoCalculoController (GraphPlotter plotter) {
  super(plotter);
  this.plotter = plotter;
  }

 // Came from:
 // * igraf/moduloCentral/controle/AreaDesenhoController.java: tratarEventoRecebido(CommunicationEvent ie): areaDDesenho.gp.trataCalculoEvent((IgrafMenuCalculoEvent)ie)
 // * igraf/moduloCentral/visao/AreaDesenho.java: igraf.moduloCentral.visao.plotter.GraphPlotter gp
 // * igraf/moduloCentral/visao/plotter/GraphPlotter.java: trataCalculoEvent(CommunicationEvent ie): desenhoCalculoController.trataEvento(ie)
 // * igraf/moduloCentral/controle/desenho/DesenhoCalculoController.java: trataEvento(ie)
 public void trataEvento (CommunicationEvent ie) {

  if (ie instanceof IgrafMenuCalculoEvent) {
   IgrafMenuCalculoEvent imce = (IgrafMenuCalculoEvent) ie;
   String command = imce.getCommand();
   //T System.out.println("src/igraf/moduloCentral/controle/desenho/DesenhoCalculoController.java: trataEvento(CommunicationEvent): " + ie.getClass().getName() + ": " + command);
   // igraf.moduloCentral.eventos.menu.IgrafMenuCalculoEvent
   if (command.equals(ResourceReader.msg("mcDerVer")))
    plotter.desenhaDerivada(imce.getExpressao());
   else
   if (command.equals(ResourceReader.msg("mcIIVer")))
    plotter.desenhaIntegralIndefinida(imce.getExpressao());
   //TODO: por uniformidade precisa de codigos para outras opcoes de "menu calculo"?
   //T else
   //T if (command.equals(ResourceReader.msg("mcIICalc")))
   //T  plotter.getAreaDesenho().repaint(); // desenhaAntiDerivada(imce.getExpressao()); // desenha(Graphics2D g, int w, int h)

   notifyScreenChanged();
   }

  if (ie instanceof JanelaIntegralEvent) {
   JanelaIntegralEvent jie = (JanelaIntegralEvent) ie;

   if (jie.getCommand().equals(JanelaIntegralEvent.DRAW)) {
    DesenhoRegiaoIntegral dri = new DesenhoRegiaoIntegral(plotter, jie.getLista(), jie.getFrom(), jie.getTo(), jie.getState());
    listaDesenho.insertElementAt(dri, 0);
    listaDesenhoOculto.insertElementAt(dri, 0);
    }

   if (jie.getCommand().equals(JanelaIntegralEvent.CHANGE))
    mudaPinturaRegiaoIntegral(jie.getState());
  
   if (jie.getCommand().equals(JanelaIntegralEvent.ERASE))
    removeRegiaoIntegral();  
   }
  }

 private  void removeRegiaoIntegral () {
  for (int i = 0; i < listaDesenho.size(); i++) {
   Desenho d = (Desenho) listaDesenho.elementAt(i);

   if (d instanceof DesenhoRegiaoIntegral)
    listaDesenho.removeElement(d);
   }
  }

 private void mudaPinturaRegiaoIntegral (boolean b) {
  DesenhoRegiaoIntegral dri;
  for (int i = 0; i < listaDesenho.size(); i++) {
   if (listaDesenho.elementAt(i) instanceof DesenhoRegiaoIntegral) {
    dri = (DesenhoRegiaoIntegral)listaDesenho.elementAt(i);
    dri.alteraModoPintura(b);
    }
   }
  }

 public Vector getListaIntegralOculta () {
  return listaDesenhoOculto;
  }

 public void apagaDesenho (Desenho d) {
  listaDesenho.remove(d);
  }

 public void apagaTodasIntegrais () {
  listaDesenho.removeAllElements();
  }

 public void removerIntegrais () {
  listaDesenhoOculto.removeAllElements();
  listaDesenho.removeAllElements();
  }

 public void desenharTodasIntegrais () {
  for (int i = 0; i < listaDesenhoOculto.size(); i++) {
   insereDesenho((Desenho)listaDesenhoOculto.get(i));
   }
  }

 public void reset () {
  super.reset();
  listaDesenhoOculto.removeAllElements();
  }

 private void desenhaIntegralIndefinida (String expressao) { 
  //if (expressao.length() > 0)
  //insereFuncao("integral de f(x) = " + expressao, ""); 
  }

 private void desenhaDerivada (String expressao) {
  String s = GeraPoligono.getDerivada(expressao); 
  //insereFuncao(s, new String(" derivada de f(x) = " + expressao));
  }

 }
