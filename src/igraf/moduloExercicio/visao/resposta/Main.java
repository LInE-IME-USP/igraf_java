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

package igraf.moduloExercicio.visao.resposta;

import java.util.Vector;

public class Main {	

 public static void main (String[] args) {
  Vector answerList  = new Vector();
  Vector attemptList = new Vector();

  ObjectiveAnswer objAnswer;
  Attempt atempt;
  for (int j = 0; j < 10; j++) {
   for (int i = 0; i < 4; i++) {
       objAnswer = new ObjectiveAnswer(i, "resposta", "gabarito", "certo"); // criar respostas
       answerList.add(objAnswer); // coloc�-las em listas
    }
   answerList.add(new DiscursiveAnswer("Uma resposta beeeeemmmm legal..."));
   atempt = new Attempt(answerList, String.valueOf(j+1));// passar as listas para as tentativas
   attemptList.add(atempt);// criar a lista de tentativas
   answerList = new Vector();
   }

  // criar a janela de visualiza��o
  AnswerVisualizerFrame avf = new AnswerVisualizerFrame(attemptList);
  }

 }
