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

package igraf.moduloCentral.modelo;

public class RegularPolygonModel {

 private float aresta;
 private float apotema;
 private int numVertex; 
 
 public RegularPolygonModel (float apotema, int numVertex) {
  this.apotema = apotema;
  this.numVertex = numVertex;
  }

 public float getAresta () {
  return aresta;
  }

 public float getApotema () {
  return apotema;
  }

 public int getNumVertex () {
  return numVertex;
  }

 }
