/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @author RP, LOB
 * 
 * @description Create a panel with iGraf logo, to be part of superior panel (first line)
 *
 * @see igraf/IGraf.java: 'void linhaUm()'
 * @see igraf/menu/MenuArquivo.java: cria instância aqui

 * @see igraf/moduloSuperior/ModuloSuperior.java: initiates 'PainelBotoes' with 'painelBotoes = new PainelBotoes(pbc);'
 *
 * @see igraf/moduloCentral/ModuloCentral.java: here is created the 'HashMap hashMapIGrafMenus' with all 'igraf.moduloCentral.visao.menu.Menu(*)'
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
 *
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 *
 */

package igraf.moduloSuperior.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import igraf.basico.io.ResourceReader;
import igraf.basico.io.TrataImagem;
import igraf.basico.util.Configuracoes;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloSuperior.controle.MenuArquivoController;
import igraf.moduloSuperior.visao.PainelSuperior;


public class PainelTitulo extends PanelBackImage {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloSuperior/visao/PainelBotoes.java";

 private final Image imgLogo = TrataImagem.pegaImagem("rightTopGraf.gif"); // 120 x 23 - background #003264 = rgb(0,50,100)
 private final Image backgroundTop = TrataImagem.pegaImagem("backgroundTop.gif");

 private PainelBotoes painelBotoes; // reference to PainelBotoes to get its width

 public PainelTitulo (Image backImage) {
  super(backImage, 0, 2);
  // setBorder(BorderFactory.createLineBorder(Color.black));
  add(new javax.swing.JLabel(new javax.swing.ImageIcon(imgLogo)));
  // igraf.basico.util.Utilitarios.traceStackComponentFathers(this);
  }


 //DEGUG: change the name to 'main'
 public static void mainD (String[] args) {
  ResourceReader.setLanguage(0); // do not use 'new ResourceReader();'
  String strName = "backgroundTop.gif";
  // if (args.length==0)
  System.out.print  ("src/igraf/moduloSuperior/visao/PainelTitulo.java: testa imagem de fundo 'backgroundTop.gif' ");
  System.out.println("com imagem 'rightTopGraf.gif'");
  JFrame f = new JFrame();
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  f.setContentPane(new PainelTitulo(TrataImagem.pegaImagem(strName)));
  f.pack();
  f.setVisible(true);
  }


 public void updateLabels () {
  repaint();  
  }

 }
