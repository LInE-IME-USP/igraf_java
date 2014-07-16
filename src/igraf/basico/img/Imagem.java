/*
 * @author  Leônidas de Oliveira Brandão
 *
 * @description Para manipular imagens em botões e janelas
 * Chamado em: 
 *             
 */

package igraf.basico.img; 

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Imagem extends Canvas { //

  private Image img;
  private String nome = "";
  public String nome () { return nome; }
   
  private Image offscreen = null;  // para técnica de DOUBLE BUFFERING evita "flicker" e aqui consegue-se
   
  public int largura = 30, altura = 33; // para ser usado por quem cria Imagem, para controlar tamanho mínimo
   
  public static Dimension tamanhoBarraCentral;

  public Imagem (Image img, String nome) {
    this.img = img;
    this.nome = nome;
    }
   

  // Chamado sempre que precisa atualizar desenho
  public void pinta () {
    //System.out.println("pinta");
    if (offscreen!=null) {
       Graphics g = offscreen.getGraphics(); // copia tudo de uma vez para a tela
       paint(g); // pinta tudo: nesta posição conseguimos fazer uma pintura para cada chamada 
       }
    else repaint();
    }
   
  // auxiliar para impedir largura <= 0, principalmente no Java 4
  public void setLarg (int larg) {
    largura = larg;
    this.setSize(larg,getSize().height);
    }
  public void setAlt (int alt) {
    altura = alt;
    this.setSize(getSize().width, alt);
    }


  // 10/07/2008: 2 obs. abaixo erram sobre outra versão de 'paint' para 'double buffering'
  // Está dando erro no Netscape4+Java 1.3.1_02
  public void paint (Graphics g) {
    // Dimension imTamanho = new Dimension(img.getWidth(this), img.getHeight(this)), tamanho;
    Dimension tamanho;
    int imTamLarg = 30, imTamAlt = 30;
    int tamLarg = 30, tamAlt = 30;
    int alt = altura/2, larg = largura/2;
    imTamLarg = img.getWidth(this);
    imTamAlt = img.getHeight(this);
    if (imTamLarg>0) this.setSize(imTamLarg,imTamAlt);
    else
       this.setSize(30,30); // para altarar o tamanho final dos botões
    tamanho = this.getSize();//size();
    tamLarg = tamanho.width;
    tamAlt = tamanho.height;
    //- String str ="";
    try {
      //- str = "[imTamLarg="+imTamLarg+", "+imTamAlt+"="+imTamAlt+" (tamLarg,tamAlt)="+tamLarg+","+tamAlt+")] ";
      //- if (tamLarg>imTamLarg) larg = (tamLarg - imTamLarg)/2; //str = "[larg1="+larg+"="+tamLarg/2+"-"+imTamLarg/2+"]";
      //- else larg = (imTamLarg - tamLarg)/2; //str = "[larg2="+larg+"="+imTamLarg/2+"-"+tamLarg/2+"]";
      //- if (tamAlt>imTamAlt) alt  = (tamAlt - imTamAlt)/2; //
      //- else alt  = (imTamAlt - tamAlt)/2; //
      larg = 1; alt = 1;
      //- str +=" (larg,alt)=("+larg+","+alt+")";

      // Double buffering
      if (offscreen == null) // primeiro "paint" entra antes de construir primeira "offscreen"
         offscreen = this.createImage(larg,alt); // precisa ser construido dentro do "paint"
      g = offscreen.getGraphics(); // pega último buffer "gráfico"
      //g.drawImage(img, larg, alt, this);
      g = this.getGraphics();
      // g.drawImage(offscreen,0,0,this);
      g.drawImage(img, larg, alt, this);
    } catch (java.lang.NullPointerException e) { // 
         System.err.println("Erro: ao tentar desenhar imagem '"+this.nome+"': "+"("+larg+","+alt+"): "+e);
         // e.printStackTrace();
         // g.drawImage(img, larg, alt, this);
         }
      catch (java.lang.Exception e2) { // está dando erro no Netscape4+Java 1.3.1_02
         // Sempre que recarrega 'applet' dá erro com alt=larg=0
         System.err.println("Erro: ao tentar desenhar imagem '"+this.nome+"': "+"("+larg+","+alt+"): "+e2);
         // e2.printStackTrace();
         }
    //- System.err.println("[Imagem] "+str);
    }

  }
