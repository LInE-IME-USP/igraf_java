/*
 * iGraf - Interactive Graphics on the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author Le�nidas de Oliveira Brand�o
 * @version 1: 21/02/2009
 *
 * @description Treatment to images
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

package igraf.basico.io;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.JPanel; // to put Image

//import igraf.basico.img.Imagem;
import igraf.basico.img.LocalizacaoImagens;


public class TrataImagem { //

 public static final String str_imagens = "igraf/img/"; // endere�o dos �cones (bot�es)

 private static final String DEBUG_MSG   = ""; // "igraf/basico/io/TrataImagem.java";

 private static final String msgErroPC   = "[TI] 1"; //"[TrataImagem!pegaClasse]";
 private static final String msgErroPCpI = "[TI] 2"; //"[TrataImagem!pegaImagem]";

 public static Frame frameImg; //
 public static MediaTracker mediaTracker; //
 public static Hashtable hash_img = new Hashtable(); //
 public static Class trataClasse; // 

 // The directory where "LocalizacaoImagens.java" can not be changed!! Unless, their images also is moved...

 private static final String ComponentImage = (new LocalizacaoImagens()).nome; // truque para usar classe para pegar diret�rio
 
 // pega a classe de nome "str_classe"
 public static Class pegaClasse (String str_classe) { //
   try {
       Class classe = Class.forName(str_classe);
       return classe;
   } catch (ClassNotFoundException classnotfoundexception) {
       System.err.println(msgErroPC+": classe n�o encontrada "+str_classe);
       throw new NoClassDefFoundError(classnotfoundexception.getMessage());
       }
   }

 // Get an Image and return it in a Canvas (to be used in any iGraf Component context)
 // igraf/moduloAjuda/visao/JanelaSobre.java
 public static JPanel getImageToJPanel (final Image img, final int width, final int height, final String imgNameOrigin) {
   final JPanel jpanel = new JPanel() {
    // Put the Image into the JPanel
    public void paintComponent (java.awt.Graphics gr) {
     super.paintComponent(gr);
     // gr.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
     gr.drawImage(img, 0, 0, width, height, this);
     //T System.out.println("TrataImagem.java: getImageToJPanel: " + imgNameOrigin);
     //T System.out.println(" - width="+this.getSize().width+", height="+this.getSize().height);
     }
    };
   jpanel.setPreferredSize(new java.awt.Dimension(width, height));
   return jpanel;
   }


 // Testa se imagem tem dimensao, senao => erro (devolve 'false')
 public static boolean verificaImage (Component comp, Image image) {
   int larg, alt;
   larg = image.getWidth(comp);
   alt  = image.getHeight(comp);
   if (larg<=0) larg=10;
   if (alt <=0) alt =10;
   java.awt.image.PixelGrabber grabber = new java.awt.image.PixelGrabber(image, 0,0, larg,alt, true);
   boolean bol = false;
   try { // IMPORTANTE para que o 'pegaImagemIGeom(String)' ao usar o 'Toolkit' devolve Image!=null mesmo no caso
         //            de n�o conseguir montar a Image.
     grabber.grabPixels(5); // s� para esperar
   } catch (java.lang.InterruptedException ie) {}
   int [] pixels = (int[])grabber.getPixels();
   String str = "";
   if (pixels!=null) str = ""+pixels.length;
   if (pixels!=null) return true;
   return false;
   }

 private static boolean errorLoadImg = false;
 private static String strClass = "";
 private static int contEr = 0;

 //ATTENTION:
 // * this method is essential to build ImageIcon under "applet" version
 // * the path must start from 'ima' (the package location of the "applet" 'igraf.IGraf') otherwise, you can use full path, starting with '/'
 // Construct a ImageIcon from any image file name
 public static javax.swing.ImageIcon getImageIcon (String strName) {
   java.awt.Image image = null;
   java.net.URL resource = null;
   javax.swing.ImageIcon imageIcon = null;
   try {
     resource = igraf.IGraf.class.getResource(strName);
     imageIcon = null;
     if (resource!=null) {
        imageIcon = new javax.swing.ImageIcon(resource);
        }
     else {
        System.err.println("Error: in 'image treat', resource=" + resource + " - igraf/" + strName);
        // System.err.println("Error: getResource=" + resource);
        }
   } catch (Exception ioe) {
     System.err.println(DEBUG_MSG + "Couldn't read stream from file: " + strName);
     errorLoadImg = true;
     strClass += " - " + ioe.toString();
     if (contEr==0) ioe.printStackTrace();
     contEr++;
     }
   return imageIcon;
   } // public static void getImageIcon(String strName)


 // Return the image path
 public static String getImagePath (String str_imagem) { //
  return "igraf.basico.img." + str_imagem;
  }


 // Entra primeiro aqui
 public static Image pegaImagem (String str_imagem) { //
   Image image = null;
   boolean erro = false;

   try { // java.net.URL
     // src/igraf/basico/img/LocalizacaoImagens.java
     URL imgURL = (trataClasse != null ? trataClasse : (trataClasse=pegaClasse(ComponentImage))).getResource(str_imagem);
     String str_aux = "" + imgURL;
     // System.out.println("src/igraf/basico/io/TrataImagem.java: pegaImagem: <"+str_aux+">: "+str_imagem);
     // src/igraf/basico/io/TrataImagem.java: pegaImagem: <jar:file:/home/leo/projetos/iMA/iMA0/igraf/novo/iGraf.jar!/igraf/basico/img/backgroundTop.gif>: backgroundTop.gif

     if (imgURL==null) {
       System.err.println("Erro: em tratamento de imagem, ao tentar ler imagem "+str_imagem+" -> "+str_aux+": <vazia>");
       return null;
       }

     image = (Toolkit.getDefaultToolkit().getImage(imgURL));

   } catch (java.lang.NullPointerException npe) {
     System.err.println("Erro: em tratamento de imagem, ao tentar ler imagem "+str_imagem+" - "+image+" via Toolkit: "+npe);
     System.err.println("     classes: "+trataClasse+", "+ComponentImage);
     // npe.printStackTrace();
     return image;
     }

   return image;
   } // Image pegaImagem(String str_imagem)

 // Para leitura de arquivo via iGeom
 private static Image trataImagemComoStream (String str_arquivo) {
   return trataImagemComoStream(str_arquivo,0);
   }

   
 // Para leitura de arquivo via applet
 // Tamb�m n�o consegui fazer funcionar no Netscape...
 private static Image trataImagemComoStream (String str_arquivo, int tipo) {
   Component component = null;
   InputStream is;
   ByteArrayOutputStream baos;
   Image img1 = null;
   //System.out.println("src/igraf/basico/io/TrataImagem.java: trataImagemComoStream: "+str_arquivo);
   if (tipo==0)
      str_arquivo = str_imagens + str_arquivo; 
   trataClasse = pegaClasse(ComponentImage);
   try {
     is = trataClasse.getClass().getResourceAsStream(str_arquivo);
     // System.out.println("src/igraf/basico/io/TrataImagem.java: trataImagemComoStream: trataClasse="+trataClasse+", is="+is+", "+str_arquivo);

     baos = new ByteArrayOutputStream();

     int c;
     while ((c = is.read()) >= 0)
        baos.write(c);
     img1 = component.getToolkit().createImage(baos.toByteArray());
   } catch (Exception e) {
     System.err.println("Erro: em tratamento de imagem, ao tentar ler imagem via ByteArrayOutputStream: "+e);
     //e.printStackTrace();
     }

   return img1;
   }


 public static Image trataImagem (String str_imagem) { //
   Image image;
   try {
     image = pegaImagem(str_imagem); // monta o hash com nomes das imagens
     return image;
   } catch (Exception e) { // java.lang.NullPointerException
     System.err.println("Erro: em tratamento de imagem, ao tentar ler imagem, vazia, "+ str_imagem + ": "+e);
     return null;
     }
  }
   
 }
