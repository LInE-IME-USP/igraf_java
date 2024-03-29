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


public final class ListaTextoAjuda {

 private static final TextoGrafico   tg  = new TextoGrafico();
 private static final TextoCalculo   tc  = new TextoCalculo();
 private static final TextoAnimacao  ta  = new TextoAnimacao();
 private static final TextoEdicoes   tad = new TextoEdicoes();
 private static final TextoExercicio te  = new TextoExercicio();
 private static final TextoSintaxe   ts  = new TextoSintaxe();
 private static final TextoConceito  tco = new TextoConceito();

 private static JPanelBasisTopic [] lista = { tg, tc, ta, tad, te, ts, tco };

 public static final int numTopicos = lista.length;

 public static JPanelBasisTopic getTexto (int i) {
  if (i < 0 || i > numTopicos)
   throw new IllegalArgumentException("ListaTextoAjuda.java: invalid index: " + i + "\nThis number is supossed to be between 0 and " + numTopicos);
  return lista[i];
  }



 }
