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

package igraf.moduloJanelasAuxiliares.eventos;

import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;
import igraf.moduloJanelasAuxiliares.controle.JanelaEdicaoExpressaoController;

import java.awt.Color;

import difusor.evento.CommunicationEvent;


public class EdicaoExpressaoEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, EventoRegistravel {

 JanelaEdicaoExpressaoController jeec;
 Color corAtual;
 String funcaoOriginal, funcaoAtual;
 
 public EdicaoExpressaoEvent (Object source) {
  super(source);
  jeec = (JanelaEdicaoExpressaoController)source;
  funcaoOriginal = jeec.getFuncaoAtual();
  funcaoAtual = jeec.getNovaFuncao();
  corAtual = jeec.getColor();  
  }
 
 public EdicaoExpressaoEvent (String arg, Object source) {
  super(source);
  decodificaArgumento(arg);
  }
 
 /**
  * @return a fun��o que havia <b>antes</b> da edi��o
  */
 public String getFuncaoOriginal () {
  return funcaoOriginal;
  }
 
 public String getFuncaoAtual () {
  return funcaoAtual;
  }
 
 public void setColor (Color cor) {
  corAtual = cor;
  }
 
 public Color getColor () {
  return corAtual;
  }

 public String getArgumento () {
  String arg = 
  "r:" + corAtual.getRed() + " " +
        "g:" + corAtual.getGreen() + " " +
        "b:" + corAtual.getBlue() + " " +
        "f:" + getFuncaoOriginal() + " " +
        "h:" + getFuncaoAtual();
  return arg;
  }

 public int getCodigoAcao () {
  return Acao.editarFuncao;
  }

 private void decodificaArgumento (String s) {
  int ini = 0, fim = 0, r, g, b;
  try {
    ini = s.indexOf("r:"); // para n�o aceitar "r:" de "cor:"
    fim = s.indexOf(" ", ini+2);
    r = Integer.valueOf(s.substring(ini+2, fim)).intValue();

    ini = s.indexOf("g:");
    fim = s.indexOf(" ", ini);
    g = Integer.valueOf(s.substring(ini+2, fim)).intValue();

    ini = s.indexOf("b:");
    fim = s.indexOf(" ", ini);
    b = Integer.valueOf(s.substring(ini+2, fim)).intValue();
  } catch (Exception e) {

    // At� vers�o 1.8.6 sintaxe (de 18/01/2008) era: "cor: 5" e n�o 'r:255 g:0 b:0'
    //  - em 'TextInput.setColor(int i)' fazia '2: Color.blue', '3: Color.green', '4: Color.magenta', '5: Color.red', '0: Color.black'
    try { // tenta versao at� 1.8.6
     ini = s.indexOf("cor:");
     int icor = Integer.parseInt(s.substring(ini+4, ini+5));
     if (icor==2) { r=0; g=0; b=200; }
     else
     if (icor==3) { r=0; g=200; b=0; }
     else
     if (icor==4) { r=0; g=200; b=200; }
     else
     if (icor==5) { r=200; g=0; b=0; }
     else { r=0; g=0; b=0; }
    } catch (Exception e2) {
      r=0; g=0; b=0;
      System.err.println("Erro: em visual/janela ao tentar processar cor de texto form. antigo: \""+s+"\": "+e2);
      // e1.printStackTrace();
      }

    }

  corAtual = new Color(r,g,b);
  try {
    ini = s.indexOf("f:");
    fim = s.indexOf("h:");  // s.indexOf('h');
    funcaoOriginal = s.substring(ini+2, fim-1);
    funcaoAtual = s.substring(fim+2);
  } catch (Exception e) {

    // At� vers�o 1.8.6 sintaxe (de 18/01/2008) era: "f: x  g: 5" e n�o 'f: x  h: 5'
    try { // tenta versao at� 1.8.6
      fim = s.indexOf("g:");
      funcaoOriginal = s.substring(ini+2, fim-1);
      funcaoAtual = s.substring(fim+2);
    } catch (Exception e2) {
      funcaoOriginal = "";
      funcaoAtual = "";
      System.err.println("Erro: cor em formato antigo: \""+s+"\": "+e2);
      }
    }
  //   System.out.println("EEE: " + getArgumento());
  }


 public String getDescription () {
  return objetivo("notificar sobre a realiza��o de edi��o sobre gr�fico que est� na tela");
  }

 }
