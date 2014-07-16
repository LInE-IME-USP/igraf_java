package igraf.basico.img;

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

public class SaidaImagem {
	
	public static void copiarImagem(Component componente) {
		Dimension size = componente.getSize();
		BufferedImage img = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		componente.paint(g2);
		g2.drawRect(0, 0, size.width-1, size.height-1);
	 	
	 	ImageSelection imageSelection = new ImageSelection(img);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imageSelection, null);
  	}
	
	
	public static void salvarComoJPEG(Component componente) {			
		Dimension size = componente.getSize();
		BufferedImage img = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();		
		componente.paint(g2);
		g2.drawRect(0, 0, size.width-1, size.height-1);
		
//		try {
//			OutputStream out = new FileOutputStream(getFileName());
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(img);
//            param.setQuality(1.0f, false);
//            encoder.setJPEGEncodeParam(param);
//			encoder.encode(img);
//			out.close();
//		}
		try{
			File saveFile = new File(getFileName());
			ImageIO.write(img, "jpg", saveFile);
		}		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Houve um erro ao salvar imagem JPEG.","ERRO",0);
		}
	}
	
	private static String getFileName(){
		String nomeArquivo = null;
		
		Frame f = new Frame();
		FileDialog fd = new FileDialog(f);
		
		fd.setMode(FileDialog.SAVE);
		fd.setTitle("Salvar como imagem JPG");
		fd.setVisible(true);
		if (fd.getDirectory()!= null && fd.getFile()!= null)
			nomeArquivo = fd.getDirectory()+ fd.getFile();
		
		int t = nomeArquivo.length();
		
		if(!nomeArquivo.substring(t-4, t).equals(".jpg"))
			nomeArquivo += ".jpg";
		
		return nomeArquivo;
	}
}
