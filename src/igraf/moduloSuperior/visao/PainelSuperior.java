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
 * @description Superior panel with all primary menu and with textarea for function. It has some decoration aside menu options.
 * 
 * @see igraf/moduloSuperior/ModuloSuperior.java: initiates 'PainelBotoes' with 'painelBotoes = new PainelBotoes(pbc);'
 * @see igraf/moduloSuperior/PainelTitulo.java: panel with iGraf decorations
 * @see igraf/moduloSuperior/PanelBackImage.java: auxiliary panel to presents JPanel with background image
 * 
 * @see igraf/moduloCentral/ModuloCentral.java: here is created the 'HashMap hashMapIGrafMenus' with all 'igraf.moduloCentral.visao.menu.Menu(*)'
 * @see igraf/moduloSuperior/controle/PainelBotoesController.java: this, starting class to build all menus: IgrafMenuAjudaController; ... IgrafMenuPoligonoController
 * @see igraf/moduloSuperior/visao/PainelBotoes.java : here is create 'JButton'
 * @see igraf/moduloCentral/controle/PainelCentralController.java: controls all events generates in central panel (igraf/moduloCentral/*)
 * @see igraf/moduloCentral/controle/menu/IgrafMenuGraficoController.java: it starts this class with 'mg = new MenuGrafico(this, index)'
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import difusor.i18N.LanguageUpdatable;

import igraf.basico.io.ResourceReader;
import igraf.basico.io.TrataImagem;
import igraf.basico.util.EsquemaVisual;
import igraf.basico.util.Configuracoes;
import igraf.moduloSuperior.controle.MenuArquivoController;

public class PainelSuperior extends JPanel implements LanguageUpdatable {

 // Alturas e posicionamento do primeiro bloco de componentes (painel norte)
 public static final int HEIGTH_BUTTON = 25;
 public static final int WIDTH_BUTTON = 47; // used in PainelBotoes
 public static final int WIDTH_MENU_BUTTONS = 282; // 47 * 6
 public static final int WIDTH_FILE = 47;

 // Background image to the top panels of iGraf : igraf/basico/img/backgroundTop.gif: 17 x 23
 private final Image backImage = TrataImagem.pegaImagem("backgroundTop.gif"); // background image to PanelBackImage and PainelTitulo

 private JPanel panelBorderLayout;    // container of: panelButtons at left and panelTitulo at right
 private PanelBackImage panelButtons; // panel with all menu buttons: menuArquivo if applications and all the others
 private PainelTitulo painelTitulo;   // panel with iGraf decoration
 private PainelBotoes painelBotoes;   // primary buttons (except File)
 private MenuFile menuArquivo; // primary butto File

 private MenuArquivoController menuArquivoController;

 //DEBUG: you can return the 3 lines layout changing 'igraf.IGraf.LAYOUTNOVO = true;' to 'false'
 private int numberOfLines = 3;

 public PainelSuperior (boolean twoLines, MenuArquivoController mac, PainelBotoes painelBotoes) {
  this.menuArquivoController = mac;
  this.painelBotoes = painelBotoes;

  if (twoLines) {  // New layout: 2013: one superior panel with all menu (file and others) and with textarea for functions
    this.numberOfLines = 2;
    }
  this.setLayout(new GridLayout(this.numberOfLines, 1)); // how many lines: each line is one panel

  mac.setControlledObject(this);
  setBackground(EsquemaVisual.corBarraSupInf); // in case back image fails...
  // setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
  }

 public int widthPanelButtons () {
  return  this.painelBotoes.getWidth();
  }

 // From: igraf/moduloSuperior/ModuloSuperior.java
 public void criaPainelTitulo (boolean ehApplet, EntradaExpressao panelExpression) {
  panelBorderLayout = new PanelBackImage(backImage); //new JPanel();             // container of: panelButtons at left and panelTitulo at right
  panelButtons = new PanelBackImage(backImage); // panel with all menu buttons: menuArquivo if applications and all the others

  painelTitulo = new PainelTitulo(backImage); // here => igraf.IGraf.LAYOUTNOVO == true

  panelBorderLayout.setBackground(EsquemaVisual.corBarraSupInf);
  panelBorderLayout.setLayout(new BorderLayout());
  this.painelBotoes.setBackground(EsquemaVisual.corBarraSupInf);

  int largFile = MenuFile.WIDTHMENUFILE;
  int withAllButtons = WIDTH_MENU_BUTTONS;

  // If it is an applet => do not insert the File menu
  if (!ehApplet) {
    menuArquivo = new MenuFile(ResourceReader.msg("arquivo"), menuArquivoController, HEIGTH_BUTTON);
    withAllButtons += WIDTH_FILE;
    }

  if (numberOfLines==2) { // New layout: 05/2013: two lines with panels
    // here => igraf.IGraf.LAYOUTNOVO == true

    this.setPreferredSize(new Dimension(igraf.basico.util.Configuracoes.lTFP, 2 * (HEIGTH_BUTTON+4))); // important to adjust the heigth of this superior panel

    this.painelBotoes.setLayout(new GridLayout(1,6));
    this.painelBotoes.setPreferredSize(new Dimension(withAllButtons,HEIGTH_BUTTON)); // important to fix the width of the menu buttons
    if (!ehApplet)
      this.panelButtons.add(menuArquivo.getMenuFileLabel(), BorderLayout.WEST);

    panelButtons.add(this.painelBotoes, BorderLayout.EAST);
    panelButtons.add(painelTitulo, BorderLayout.WEST);

    panelBorderLayout.add(panelButtons, BorderLayout.WEST);
    panelBorderLayout.add(painelTitulo, BorderLayout.EAST);

    add(panelBorderLayout);
    add(panelExpression);

    }
  else  { // here => igraf.IGraf.LAYOUTNOVO == false
    menuArquivo.setPreferredSize(new Dimension(WIDTH_FILE, HEIGTH_BUTTON));
    this.setPreferredSize(new Dimension(igraf.basico.util.Configuracoes.lTFP, 3 * (HEIGTH_BUTTON+7))); //
    panelBorderLayout.add(menuArquivo.getMenuFileLabel(),BorderLayout.WEST);
    panelBorderLayout.add(painelTitulo,BorderLayout.EAST);

    add(panelBorderLayout);
    add(panelExpression);
    add(painelBotoes);

    }

  }

 public void updateLabels () {
  painelTitulo.updateLabels();  
  }

 }
