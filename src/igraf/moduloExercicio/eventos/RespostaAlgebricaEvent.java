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


public class RespostaAlgebricaEvent extends RespostaEvent {

  public final static String GET_LIMITS = "get limits";

  private int numQuest, start, end;
  private String respostaGabarito;

  // construtor usado para enviar a resposta do gabarito para a sess�o, 
  // de onde ser�, posteriormente gravada em arquivo
  public RespostaAlgebricaEvent (Object source, String resp) {
    super(source, resp);
    setCommand(WRITE_ANSWER);
    }

  // construtor usado para enviar a resposta previamente registrada no 
  // gabarito (arquivo) para o sistema de avalia��o autom�tica
  public RespostaAlgebricaEvent (String arg, Object source) {
    super(source, arg);
    decodificaResposta(arg);
    //L System.out.println("RespostaAlgebricaEvent.RespostaAlgebricaEvent: "+respostaGabarito);
    setCommand(READ_EXERCISE);
    }

  // construtor usado quando da solicita��o dos limites da tela
  public RespostaAlgebricaEvent (Object source) {
    super(source);
    setCommand(GET_LIMITS);
    }


  public int getEnd () {
    return end;
    }

  public int getNumQuest () {
    return numQuest;
    }

  public String getRespostaGabarito () {
    return respostaGabarito;
    }

  public int getStart () {
    return start;
    }

  public int getCodigoAcao () {
    if (getCommand().equals(GET_LIMITS))
      return -1;
    return Acao.respostaAlgebrica;
    }


  public void decodificaResposta (String strDecod) {
    String strAux = "";
    int ini = 0, fim = 0;

    try {
      ini = strDecod.indexOf("Item:");
      fim = strDecod.indexOf(" ", ini);

      try {
	strAux = strDecod.substring(ini+5, fim).trim();
        numQuest = Integer.valueOf(strAux).intValue();
      } catch (Exception e) {
        try { // tenta com versao anterior <= 1.8: "Q:" no lugar do atual "Item:"
          ini = strDecod.indexOf("Q:");
          fim = strDecod.indexOf(" ", ini);
          strAux = strDecod.substring(ini+2, fim).trim();
          numQuest = Integer.valueOf(strAux).intValue();
        } catch (Exception e2) {
          if (IGraf.IS_DEBUG)
	    System.err.println("src/igraf/moduloExercicio/eventos/RespostaAlgebricaEvent.java: Error: trying to decode 'algebraic expression': " + strDecod + ": " + e2);
          //e2.printStackTrace();
          return;
          }
        }

      ini = strDecod.indexOf("a:", fim);
      fim = strDecod.indexOf(" ", ini);
      strAux = strDecod.substring(ini+2, fim).trim();
      start = Integer.valueOf(strAux).intValue();

      ini = strDecod.indexOf("b:", fim);
      fim = strDecod.indexOf(" ", ini);
      strAux = strDecod.substring(ini+2, fim).trim();
      end = Integer.valueOf(strAux).intValue();

      ini = strDecod.indexOf("val:", fim);
      strAux = strDecod.substring(ini+4).trim();
      respostaGabarito = Crypto.hexToString(strAux);

      // Se for leitura de arquivo e este for exerc�cio (cont�m a linha "# exercise: web") => decodifique a express�o aritm�tica
      // if (IGraf.ehExercicioWeb() && !FrameQuestaoResposta.getRespAluno())
      //  resposta = Crypto.hexToString(resposta); // decodifique

      //- System.err.println("RespostaAlgebrica.decodificaResposta: "+s0+" -> "+resposta);
      } catch (IllegalArgumentException iae) {
        System.err.println("RA: Erro: " + respostaGabarito +": "+iae);
        }

    catch (Exception e) {
      System.err.println("Erro: in exercise 'algebraic expression': " + strDecod + ": " + e.toString());
      e.printStackTrace();
      return;
      }
    }

  }
