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
 * @description Help in image copy, used to hold an image while on the clipboard. Menu option "File | Copy drawing area"
 * 
 * @see igraf/moduloSuperior/visao/MenuFile.java
 * @see igraf/moduloArquivo/ModuloArquivo.java
 * @see igraf/moduloArquivo/controle/ArquivoController.java
 * @see igraf/moduloArquivo/Arquivo.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.basico.io;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Image;
import java.io.IOException;

// Inner class is used to hold an image while on the clipboard
public class ImageToBeTransfered implements Transferable {

  // the Image object which will be housed by the ImageToBeTransfered
  private Image image;

  public ImageToBeTransfered (Image image) {
    this.image = image;
    }

  // Returns the supported flavors of our implementation
  public DataFlavor [] getTransferDataFlavors () {
    return new DataFlavor [] {DataFlavor.imageFlavor};
    }
  
  // Returns true if flavor is supported
  public boolean isDataFlavorSupported (DataFlavor flavor) {
    return DataFlavor.imageFlavor.equals(flavor);
    }

  // Returns Image object housed by Transferable object
  public Object getTransferData (DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    if (!DataFlavor.imageFlavor.equals(flavor)) {
      throw new UnsupportedFlavorException(flavor);
      }
    // else return the payload
    return image;
    }

  }
