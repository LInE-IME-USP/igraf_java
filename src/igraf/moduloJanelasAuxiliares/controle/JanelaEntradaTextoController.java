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

package igraf.moduloJanelasAuxiliares.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

import igraf.basico.event.ChangeLanguageEvent;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.DesenhoTextoEvent;
import igraf.moduloCentral.visao.desenho.DesenhoTexto;
import igraf.moduloJanelasAuxiliares.visao.JanelaEntradaTexto;

import difusor.CommunicationFacade;
import difusor.controle.CommunicationController;
import difusor.evento.CommunicationEvent;


public class JanelaEntradaTextoController extends JanelaController implements ActionListener, KeyListener {

 private JanelaEntradaTexto jet;
 private DesenhoTexto dt;
 private boolean editandoTexto = false;
 private boolean janelaVisivel = false;

 public JanelaEntradaTextoController (CommunicationFacade module, boolean getEvents) {
  super(module, getEvents);
  }

 public void tratarEventoRecebido (CommunicationEvent communicationEvent) {  
  String strCommand = communicationEvent.getCommand();

  if (communicationEvent instanceof ChangeLanguageEvent) 
   if (jet != null) jet.updateLabels();

  if (strCommand.equals(ResourceReader.msg("madTextoInserir"))) {      
   jet = new JanelaEntradaTexto(this);
   jet.setVisible(true);
   }
  else
  if (strCommand.equals(DesenhoTextoEvent.OPEN_EDITOR)) {
   DesenhoTextoEvent dte = (DesenhoTextoEvent)communicationEvent;

   if (!janelaVisivel) {// impedir múltiplas janelas de edição
    jet = new JanelaEntradaTexto(this);
    jet.setData(dte);
    dt = dte.getDesenhoTexto();          
    setJanelaVisivel(true);
    editandoTexto = true;
    jet.setVisible(true);
    }
   }
  }


 public void cancelarEdicao () {
  if (editandoTexto)
   enviarEvento(new DesenhoTextoEvent(this, dt));
  setJanelaVisivel(false);
  jet.dispose();
  }


 public void actionPerformed (ActionEvent e) {    
  JButton source = (JButton)e.getSource();
  String cmd = source.getActionCommand();

  if (cmd.equals("Cancelar")) {
   cancelarEdicao();    
   return;
   }

  // clique em inserir dispara DesenhoTextoEvent com todas as informações    
  if (!editandoTexto) {
   DesenhoTextoEvent dte = new DesenhoTextoEvent(this);
   dte.setCommand(DesenhoTextoEvent.INSERT_TEXT);
   dte.setFontColor(jet.getFontColor());
   dte.setTextPosition(jet.getTextPositionX(), jet.getTextPositionY());
   dte.setTextoAtual(jet.getText());    
   dte.setFont(jet.getUserSelectedFont());  
   dte.setFontSize(jet.getFontSize());
   dte.textId++;
   enviarEvento(dte);   
   }
  else {// clique em atualizar
   dt.setFontColor(jet.getFontColor());
   dt.setPosition(jet.getTextPositionX(), jet.getTextPositionY());
   dt.setFontSize(jet.getFontSize());
   String aux = dt.getText();
   dt.setText(jet.getText());    
   dt.setFont(jet.getUserSelectedFont());
   DesenhoTextoEvent dte = new DesenhoTextoEvent(this, dt);
   dte.setTextoOriginal(aux);
   enviarEvento(dte);
   jet.dispose();
   }    
  setJanelaVisivel(false);
  editandoTexto = false;
  jet.dispose();
  }


 public void keyReleased (KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
   jet.insereQuebra();
   return;
   }
  
  if (jet.getText().length() > 0) { 
   jet.habilitaInserir(true);
   jet.setTextAreaContent(jet.getText());
   }
  }

 public void keyTyped (KeyEvent e) { }
 public void keyPressed (KeyEvent e) { }

 public void setJanelaVisivel (boolean b) {
  janelaVisivel = b;
  }

 }
