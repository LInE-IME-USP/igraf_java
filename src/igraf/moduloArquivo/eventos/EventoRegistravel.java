/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Interface que marca os eventos que devem ser registrados para que possam ser recuperados
 * a partir de arquivos gravados pelo iGraf ou para que possam ser revistos na sessão atual
 * com o uso do recurso Histórico.
 * 
 * @see 
 * 
 * @author RP, LOB
 * 
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 * 
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 * 
 */

package igraf.moduloArquivo.eventos;

public interface EventoRegistravel {

 /**
  * Método que retorna o número inteiro indicativo da ação realizada pelo usuário.
  * @return -1 para indicar que o evento não precisa ser registrado na sessão.
  */
 public int getCodigoAcao();
 
 /**
  * Método que devolve os dados sobre um evento necessários à recostrução da área de desenho
  * e/ou ao uso do recurso Histórico
  * @return dadosEvento
  */
 public String getArgumento();

 }
