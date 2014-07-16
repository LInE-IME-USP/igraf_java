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
 * Exceção lançada quando se tenta criar um tópico com nível não
 * reconhecido pelo sistema de ajuda do iGraf. São permitidos apenas
 * três níveis: Topico.TITULO, Topico.SUBTITULO e Topico.SESSAO. 
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

package igraf.moduloAjuda.modelo;

public class UnsuportedLevelException extends Exception {

 String mensagem = "Um tópico só pode ser definido " +
              "com números inteiros de 0 a 2\n" +
              "Utilize as constantes estáticas " +
              "da classe Topico: TITULO, SUBTITULO ou SESSAO.\n";
  
 public String toString () {
  return mensagem;
  }

 }
