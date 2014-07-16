/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Interface que marca os eventos que devem ser registrados para que possam ser recuperados
 * a partir de arquivos gravados pelo iGraf ou para que possam ser revistos na sess�o atual
 * com o uso do recurso Hist�rico.
 * 
 * @see 
 * 
 * @author RP, LOB
 * 
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 * 
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf.moduloArquivo.eventos;

public interface EventoRegistravel {

 /**
  * M�todo que retorna o n�mero inteiro indicativo da a��o realizada pelo usu�rio.
  * @return -1 para indicar que o evento n�o precisa ser registrado na sess�o.
  */
 public int getCodigoAcao();
 
 /**
  * M�todo que devolve os dados sobre um evento necess�rios � recostru��o da �rea de desenho
  * e/ou ao uso do recurso Hist�rico
  * @return dadosEvento
  */
 public String getArgumento();

 }
