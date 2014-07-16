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

import igraf.moduloCentral.eventos.DesenhoTextoEvent;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.visao.desenho.DesenhoTexto;
import igraf.moduloCentral.visao.plotter.GraphPlotter;
import difusor.evento.CommunicationEvent;

public class DesenhoTextoController extends DesenhoController {
 
 private int ordem = 0, lastId = 0;

 public DesenhoTextoController (GraphPlotter plotter) {
  super(plotter);
  DesenhoTextoEvent.textId = 0;
  }

 public void trataEvento (CommunicationEvent ie) {
  String command = ie.getCommand();   
  
  if (command.equals(DesenhoTextoEvent.EDIT_TEXT)){
   DesenhoTextoEvent dte = (DesenhoTextoEvent)ie;   
   listaDesenho.add(dte.getDesenhoTexto());
   }
  
  if (command.equals(DesenhoTextoEvent.ERASE_TEXT)){   
   DesenhoTextoEvent dte = (DesenhoTextoEvent)ie;   
   int i = dte.getEraseIndex();
   
   if (i < listaDesenho.size())
    listaDesenho.remove(i);

   notificaAlteracaoEstado();
   }
  
  if (command.equals(DesenhoTextoEvent.INSERT_TEXT )){
   float e = 1;
   DesenhoTextoEvent dte = (DesenhoTextoEvent)ie; 

   DesenhoTexto dt = new DesenhoTexto(plotter, dte.getTextoAtual(), ordem++);
   dt.setFontColor(dte.getFontColor());
   
   float x = dte.getTextPositionX();
   float y = dte.getTextPositionY();
   if (dte.versaoAntiga()) {
    e = plotter.getEscala();
    x = x/e;  y = -y/e;
    dte.setTextPosition(x, y);
    dte.clearVersao();
    enviarEvento(dte);
    }
   dt.setPosition(x, y);
   dt.setFontSize(dte.getFontSize()); 
   
   // evita inserção duplicada de texto; paliativo para a 
   // replicação de eventos do sistema... requer solução definitiva
   if (lastId != dte.textId){ 
    insereDesenho(dt);
    lastId = dte.textId;
    notificaAlteracaoEstado();
    }   
   }
  }

 public void removeTexto (int textIndex) {
  listaDesenho.removeElementAt(textIndex);  
  }
 
 public void notificaAlteracaoEstado () {
  IgrafTabUpdateEvent iue = new IgrafTabUpdateEvent(this, IgrafTabUpdateEvent.TEXT_LIST_CHANGED);
  iue.setTextListSize(listaDesenho.size());
  enviarEvento(iue);
  }
 
 public void reset () {
  super.reset();
  ordem = 0;
  }

 }
