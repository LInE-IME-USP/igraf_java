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
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
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
