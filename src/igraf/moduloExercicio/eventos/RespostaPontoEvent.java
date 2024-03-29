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
 * @description Module to deal with exercises.
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

import igraf.IGraf;
import igraf.basico.util.Crypto;
import igraf.moduloCentral.modelo.Acao;

public class RespostaPontoEvent extends RespostaEvent {

  private double x1, x2;
  private int numQuest;

  public RespostaPontoEvent (Object source, String resp) {
    super(source, resp);
    setCommand(WRITE_ANSWER);
    }

  public RespostaPontoEvent (String arg, Object source) {
    super(source, arg);
    setCommand(READ_EXERCISE);
    decodificaResposta(arg);
    }

  public int getCodigoAcao () {
    return Acao.respostaPonto;
    }


  public void decodificaResposta (String strToDecod) {
    // if (IGraf.IS_DEBUG) System.err.println("RespostaPontoEvent.java: decodificaResposta(" + strToDecod + ")");

    int ini = 0, fim = 0;
    String s1 = "", s2 = "", s3 = "";

    try {
      ini = strToDecod.indexOf("Item:");
      fim = strToDecod.indexOf(" ", ini);

      try {
        s1 = strToDecod.substring(ini+5, fim).trim();
      } catch (Exception e) {
        try { // tenta com versao anterior <= 1.8: "Q:" no lugar do atual "Item:"
          ini = strToDecod.indexOf("Q:");
          fim = strToDecod.indexOf(" ", ini);
          s1 = strToDecod.substring(ini+2, fim).trim();
        } catch (Exception e2) {
          System.err.println("Erro: decodificacao de exercicio: ponto: linha: " + strToDecod + "\n erro: " + e2);
          e2.printStackTrace();
          return;
          }
        }

      // Item:2 x:_h322e30 y:_h332e30
      ini = strToDecod.indexOf("x:"); // "x:NUM" (n�o pode haver espa�o em branco ap�s o ':'
      fim = strToDecod.indexOf(" ", ini);

      s2 = strToDecod.substring(ini+2, fim).trim();

      ini = strToDecod.indexOf("y:"); // "y:NUM" (n�o pode haver espa�o em branco ap�s o ':'
      s3 = strToDecod.substring(ini+2).trim();

    } catch (Exception e) {
      System.err.println("Erro: decodificacao de exercicio: ponto: linha: " + strToDecod + "\n erro: " + e);
      e.printStackTrace();
      return;
      }

    try {
      //R System.err.println(" s2=" + Crypto.hexToString(s2) + ", s3 = " + Crypto.hexToString(s3));

      numQuest = Integer.valueOf(s1).intValue(); // n�mero do item "Item:NUM"
      x1 = Double.valueOf(Crypto.hexToString(s2)).doubleValue(); // decodifique
      x2 = Double.valueOf(Crypto.hexToString(s3)).doubleValue(); // decodifique

    } catch (NumberFormatException e1) {
      if (IGraf.IS_DEBUG) System.err.println("RespostaPontoEvent.java: decodificaResposta(" + strToDecod + "): error in decoding...");
      System.err.println("[RP] Error 1: in decode 'point answer': not under iGraf format? " +
                         " I found: ('" + s1 + "' , '" + s2 + "' , '" + s3 + "'): (" + x1 + "," + x2 + "): " + e1);

      // stringValida(s2, s3);

    } catch (Exception e2) {
      if (IGraf.IS_DEBUG) System.err.println("RespostaPontoEvent.java: decodificaResposta(" + strToDecod + "): error in decoding...");
      System.err.println("[RP] Error 2: in decode 'point answer': not under iGraf format? "  +
                         " I found: ('" + s1 + "' , '" + s2 + "' , '" + s3 + "'): (" + x1 + "," + x2 + "): " + e2);
      e2.printStackTrace();
      return;
      }
    //L System.out.println("RespostaPontoEvent.decodificaResposta: "+s1+" | "+x1+" | "+x2);
    }

  private boolean stringValida (String s1, String s2) {
    if (s1.indexOf("infinito") > -1 || s1.indexOf("vazio") > -1 ||
        s2.indexOf("infinito") > -1 || s2.indexOf("vazio") > -1)
      return true;
    return false;
    }

  public double getX1 () {
    return x1;
    }

  public double getX2 () {
    return x2;
    }

  public int getNumQuest () {
    return numQuest;
    }

  public String getRespostaGabarito () {
    return "(" + String.valueOf(x1) + ", " + String.valueOf(x2) + ")";
    }

  }
