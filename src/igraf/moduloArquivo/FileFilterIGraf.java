/*
 * iGraf - Interactive Graphics on the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author LOB
 *
 * @description Public class to provides filter to read/register files
 * Usage:
 * - FileFilterIGraf filter = new ExtensionFileFilterIGraf("jpg, jpeg", new String[] { "JPG", "JPEG" });
 * - fileChooser.addChoosableFileFilter(filter); // JFileChooser fileChooser
 * 
 * @see igraf/moduloArquivo/Arquivo.java
 * @see igraf/moduloArquivo/Sistema.java: getFileFilter(String,String[])
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloArquivo;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class FileFilterIGraf extends FileFilter { // javax.swing.filechooser.FileFilter

  private String fileDescription;
  private String fileExtensions [];

  public FileFilterIGraf (String description, String extension) {
    this(description, new String [] { extension });
    }

  public FileFilterIGraf (String description, String extensions []) {
    if (description == null) { // Since no description, use first extension and # of extensions as description
      this.fileDescription = extensions[0] + "{" + extensions.length + "}";
    } else {
      this.fileDescription = description;
      }
    this.fileExtensions = (String[]) extensions.clone();
    // Convert array to lowercase. Don't alter original entries
    arrayToLower(this.fileExtensions);
    }

  public String getDescription () {
    return fileDescription;
    }

  private void arrayToLower (String array []) {
    int sizeOf = array.length;
    for (int ii_0=0; ii_0 < sizeOf; ii_0++) {
      array[ii_0] = array[ii_0].toLowerCase();
      }
    }

  // Ignore case; always accept directories; character before extension must be a period
  public boolean accept (File file) {
    if (file.isDirectory()) {
      return true;
    } else {
      String path = file.getAbsolutePath().toLowerCase();
      int sizeOf = fileExtensions.length;
      for (int ii_0 = 0; ii_0 < sizeOf; ii_0++) {
        String extension = fileExtensions[ii_0];
        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
          return true;
          }
        }
      }
    return false;
    }

  } // public class FileFilterIGraf extends FileFilter
