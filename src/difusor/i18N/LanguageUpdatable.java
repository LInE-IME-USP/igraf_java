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
 * seus dados em função da mudança do idioma.
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

package difusor.i18N;

public interface LanguageUpdatable {

/**
  * Método que deve ser implementado por todas as classe que
  * desejarem obter atualizações em função da alteração de
  * idioma. Implementação do modelo de internacionalização
  * dos programas da família iMA.
  */
 abstract void updateLabels ();

 }
