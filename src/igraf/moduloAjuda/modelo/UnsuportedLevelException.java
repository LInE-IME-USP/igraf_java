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
 * @description Help integrated to iGraf.
 * Exce��o lan�ada quando se tenta criar um t�pico com n�vel n�o
 * reconhecido pelo sistema de ajuda do iGraf. S�o permitidos apenas
 * tr�s n�veis: Topico.TITULO, Topico.SUBTITULO e Topico.SESSAO. 
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

package igraf.moduloAjuda.modelo;

public class UnsuportedLevelException extends Exception {

 String mensagem = "Um t�pico s� pode ser definido " +
              "com n�meros inteiros de 0 a 2\n" +
              "Utilize as constantes est�ticas " +
              "da classe Topico: TITULO, SUBTITULO ou SESSAO.\n";
  
 public String toString () {
  return mensagem;
  }

 }
