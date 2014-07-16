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
 * @description Window to select function.
 *
 * @see igraf/moduloJanelasAuxiliares/ModuloJanelasAuxiliares.java: private JanelaAnimacaoController jac = new JanelaAnimacaoController(this, true);
 * @see igraf/moduloJanelasAuxiliares/
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloJanelasAuxiliares.controle;

import java.util.Vector;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.GraphicOnScreenChangedEvent;
import igraf.moduloCentral.visao.desenho.DesenhoAnimacao;
import igraf.moduloJanelasAuxiliares.visao.animacao.JanelaAnimacao;

import difusor.CommunicationFacade;
import difusor.evento.CommunicationEvent;


public class JanelaAnimacaoController extends JanelaController {

 private JanelaAnimacao janelaAnimacao;
 private Vector listOfDesenhoAnimacao;

 // private DesenhoAnimacao desenhoAnimacao;

 public void setControlledObject (Object obj) { }

 // Unique instance from
 //  at igraf.moduloJanelasAuxiliares.ModuloJanelasAuxiliares.<init>(ModuloJanelasAuxiliares.java:49)
 //  at igraf.ContentPane.<init>(ContentPane.java:52)
 public JanelaAnimacaoController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }


 public void tratarEventoRecebido (CommunicationEvent ie) {
  String strCommand = ie.getCommand();
  // igraf/moduloCentral/eventos/GraphicOnScreenChangedEvent.java: public static final String SCREEN_CHANGED = "screen changed";
  if (strCommand.equals(GraphicOnScreenChangedEvent.SCREEN_CHANGED)) {
   //TODO: eliminar difusor!!!
   // From: at igraf.moduloCentral.visao.plotter.GraphPlotter.notifyScreenChanged(GraphPlotter.java:514)
   //       at igraf.moduloCentral.visao.plotter.GraphPlotter.trataMenuGraficoEvent(GraphPlotter.java:314) <-
   // 
   // From: at igraf.moduloCentral.visao.PainelCentral.insereAba(PainelCentral.java:153)                   <-
   // System.out.println("\n\nsrc/igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java: tratarEventoRecebido(CommunicationEvent): 1 - " + ie.getClass().getName() + ": " + strCommand);
   //D try{String str="";System.out.print(str.charAt(3));}catch(Exception e) {e.printStackTrace();} 
   GraphicOnScreenChangedEvent ge = (GraphicOnScreenChangedEvent) ie; // igraf/moduloCentral/eventos/GraphicOnScreenChangedEvent.java
   listOfDesenhoAnimacao = ge.getListaAnimacaoVisivel();
   }
  else
  if (listOfDesenhoAnimacao != null && strCommand.equals(ResourceReader.msg("maMostraControle"))) {
   //TODO: A ser eliminado
   //TODO: Usar o 'src/igraf/moduloSuperior/visao/PainelBotoes.java' com 'HashMap hashMapControllers' com acesso direto a todos os controladores.
   //TODO: Ao clicar sobre item de menu disparar diretamente o tratador.
   //if (IGraf.IS_DEBUG) {
   //  System.err.println("\n"+IGCLASSPATH + ": tratarEventoRecebido(CommunicationEvent): DEBUG!! Attention: this event was not supposed to remain here!");
   //  System.out.println(" - command: " + strCommand);
   //  }
   // System.out.println("\n\nsrc/igraf/moduloJanelasAuxiliares/controle/JanelaAnimacaoController.java: tratarEventoRecebido(CommunicationEvent): 2 - " + ie.getClass().getName() + ": " + strCommand);
   //D try{String str="";System.out.print(str.charAt(3));}catch(Exception e) {e.printStackTrace();} 

   janelaAnimacao = new JanelaAnimacao(this);
   loadLabelList();
   }
  }

 // igraf/moduloJanelasAuxiliares/visao/animacao/LabelAnimacao.java: the animation of an specific function could be changed under 'animation window'
 public DesenhoAnimacao getDesenhoAnimacao (int index) {
  return (DesenhoAnimacao) listOfDesenhoAnimacao.get(index);
  }

 private void loadLabelList () {
  DesenhoAnimacao desenhoAnimacao;
  for (int i = 0; i < listOfDesenhoAnimacao.size(); i++) {
   desenhoAnimacao = (DesenhoAnimacao) listOfDesenhoAnimacao.get(i); // igraf.moduloCentral.visao.desenho.DesenhoAnimacao
   janelaAnimacao.setLabel(desenhoAnimacao.getFuncaoOriginal(), i); // put this function description as an item into the animation window
   }
  }

 }
