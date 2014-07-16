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

package igraf.moduloCentral.controle.desenho;

import java.util.Vector;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class UndoableDrawEdit extends AbstractUndoableEdit {

 // refer�ncia � classe que implementa undo/redo
 private DesenhoPoligonoController dpc;

 // objetos que encapsulam o estado da classe que implementa undo/redo
 private Vector polygonList, savedPolygonList = null;

 // construtor que cria refer�ncia � classe que implementa undo/redo
 public UndoableDrawEdit (DesenhoPoligonoController dpc) {
  this.dpc = dpc;
  polygonList = dpc.getListaDesenhoSemReferencia();
  }

 public void undo () throws CannotUndoException {
  super.undo();
  savedPolygonList = dpc.getListaDesenhoSemReferencia();
     dpc.setListaDesenho(polygonList);
  }

 public void redo () throws CannotRedoException {
  super.redo();
  if (savedPolygonList == null) {
   throw new CannotRedoException();
   } else {
   dpc.setListaDesenho(savedPolygonList);
   savedPolygonList = null;
   }
  }

 }
