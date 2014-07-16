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
 * @description Copy the construction at iGraf to an image
 * 
 * @see igraf/moduloCentral/visao/TabbedViewer.java: copiarImagem() [ImageFromIGraf.copiarImagem(getSelectedComponent())] and exportarJPG() [ImageFromIGraf.salvarComoJPEG(getSelectedComponent())]
 * @see igraf/moduloCentral/controle/PainelCentralController.java: tratarEventoRecebido(CommunicationEvent ie): "arqMenuCopia" and "arqMenuExporta"
 * @see igraf/basico/io/ImageToBeTransfered.java: treat cut/paste from cliboard
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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageFromIGraf {

 public static void copiarImagem (Component component) {
System.out.println("src/igraf/basico/io/ImageFromIGraf.java: copiarImagem(" + component.getClass().getName()+")");
  Dimension size = component.getSize();
  BufferedImage img = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
  Graphics2D g2 = img.createGraphics();
  component.paint(g2);
  g2.drawRect(0, 0, size.width-1, size.height-1);
  ImageToBeTransfered imageSelection = new ImageToBeTransfered(img);
  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imageSelection, null);
  }
 
 
 public static void salvarComoJPEG (Component componente) {
  Dimension size = componente.getSize();
  BufferedImage img = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
  Graphics2D g2 = img.createGraphics();  
  componente.paint(g2);
  g2.drawRect(0, 0, size.width-1, size.height-1);

  try {
   File saveFile = new File(getFileName());
   ImageIO.write(img, "jpg", saveFile);
   }
  catch (Exception e) {
   JOptionPane.showMessageDialog(null,"There was an error during the registration of the image JPEG.","ERROR",0);
   }
  }

 private static String getFileName () {
  String nomeArquivo = null;
  
  Frame f = new Frame();
  FileDialog fd = new FileDialog(f);
  
  fd.setMode(FileDialog.SAVE);
  fd.setTitle("Salvar como imagem JPG");
  fd.setVisible(true);
  if (fd.getDirectory()!= null && fd.getFile()!= null)
   nomeArquivo = fd.getDirectory()+ fd.getFile();

  int t = nomeArquivo.length();

  if (!nomeArquivo.substring(t-4, t).equals(".jpg"))
   nomeArquivo += ".jpg";
  
  return nomeArquivo;
  }

 }
