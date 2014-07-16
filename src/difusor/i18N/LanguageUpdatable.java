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
 * @description Interface que confere a uma classe a capacidade de atualizar
 * seus dados em fun��o da mudan�a do idioma.
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

package difusor.i18N;

public interface LanguageUpdatable {

/**
  * M�todo que deve ser implementado por todas as classe que
  * desejarem obter atualiza��es em fun��o da altera��o de
  * idioma. Implementa��o do modelo de internacionaliza��o
  * dos programas da fam�lia iMA.
  */
 abstract void updateLabels ();

 }
