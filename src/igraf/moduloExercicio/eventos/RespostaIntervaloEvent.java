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
 * @description Module to deal with exercises. Evento que transporta informa��es gerais geradas pelo sistema
 *  sobre o desempenho do aluno na resolu��o de um exerc�cio do iGraf
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

package igraf.moduloExercicio.eventos;

import igraf.basico.util.Crypto;
import igraf.moduloCentral.modelo.Acao;

public class RespostaIntervaloEvent extends RespostaEvent {

  private int inf, sup, numQuest;
  private double x1, x2;
  
  public RespostaIntervaloEvent (Object source, String resp) {
    super(source, resp);
    setCommand(WRITE_ANSWER);
    }

  public RespostaIntervaloEvent (String arg, Object source) {
    super(source, arg);
    setCommand(READ_EXERCISE);
    decodificaResposta(arg);
    }

  public int getCodigoAcao () {
    return Acao.respostaIntervalo;
    }    
    
  public int getInf () {
    return inf;
    }

  public int getNumQuest () {
    return numQuest;
    }

  public int getSup () {
    return sup;
    }

  public double getX1 () {
    return x1;
    }

  public double getX2 () {
    return x2;
    }

  public void decodificaResposta (String str) {
    String strAux = "";
    int ini = 0, fim = 0; String s1 = null;
    try {
      ini = str.indexOf("Item:");
      fim = str.indexOf(" ", ini);
      strAux = str.substring(ini+5, fim).trim();
      numQuest = Integer.valueOf(strAux).intValue();

      ini = str.indexOf("inf:");
      fim = str.indexOf(" ", ini);
      strAux = str.substring(ini+4, fim).trim();
      s1 = Crypto.hexToString(strAux);
      inf = Integer.valueOf(s1).intValue();
      
      ini = str.indexOf("x1:", fim);
      fim = str.indexOf(" ", ini);
      strAux = str.substring(ini+3, fim).trim();
      s1 = Crypto.hexToString(strAux);
      x1 = Double.valueOf(s1).doubleValue();

      ini = str.indexOf("x2:", fim);
      fim = str.indexOf(" ", ini);
      strAux = str.substring(ini+3, fim).trim();
      s1 = Crypto.hexToString(strAux);
      x2 = Double.valueOf(s1).doubleValue();

      ini = str.indexOf("sup:", fim);
      strAux = str.substring(ini+4).trim();
      s1 = Crypto.hexToString(strAux);
      sup = Integer.valueOf(s1).intValue();
      //L System.out.println("RespostaIntervaloEvent.RespostaAlgebricaEvent: x1="+x1+" x2="+x2+" sup="+sup);
      } catch (Exception e) {  }
    }

  public String getRespostaGabarito() {
    String r = "";
    r += inf == 0 ? "]" : "[";
    r += String.valueOf(x1) + ", ";
    r += String.valueOf(x2) ;
    r += sup == 0 ? "[" : "]";	
    return r;
    }

  }
