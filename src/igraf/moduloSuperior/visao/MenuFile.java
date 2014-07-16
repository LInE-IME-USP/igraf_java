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
 * @description Menu option related to file. It is only applied to application, reason why it is here and not in 'igraf/moduloCentral/visao/menu/'
 * 
 * @see igraf/moduloSuperior/PainelSuperior.java: it is the superior panel basis
 * @see igraf/moduloSuperior/PainelTitulo.java: panel with iGraf decorations
 * @see igraf/moduloSuperior/PanelBackImage.java: auxiliary panel to presents JPanel with background image
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

import igraf.basico.io.ResourceReader;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloSuperior.controle.MenuArquivoController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import difusor.i18N.LanguageUpdatable;
import igraf.basico.io.TrataImagem;

public class MenuFile extends JPopupMenu implements LanguageUpdatable {

 //TODO: passar para 'src/igraf/moduloCentral/visao/menu/MenuArquivo.java'? Usar icone grafico?
 public static final int WIDTHMENUFILE  = 100; // used in: igraf/moduloSuperior/visao/PainelSuperior.java
 // public static final int HEIGHTMENUFILE =  25;

 private static final String IMG = "basico/img/button_file_bg.gif"; // 98 x 23 (do not use 'igraf/basico/img/button_file_bg.gif')
 private final javax.swing.ImageIcon icon = getBGimage();

 private static final Color
   corFundoMenuTopo = EsquemaVisual.corFundo, // background color to menu top ("File")
   corFundoPS = EsquemaVisual.corBackground;  // background color to popup menu items

 private JLabel menuLabel; // label to menu top ("File")

 private JMenuItem
   // menuItemNew,menuItemOpen,menuItemRegister,menuItemCopy,menuItemExport,menuItemHTML,menuItemPrint,menuItemQuit
   menuItemNew       = new JMenuItem(ResourceReader.msg("arqMenuNovo")), // "Novo"
   menuItemOpen      = new JMenuItem(ResourceReader.msg("arqMenuAbra")), // "Abrir"
   menuItemRegister  = new JMenuItem(ResourceReader.msg("arqMenuGrave")), // "Gravar"
   //DEBUG: menuItemRegisterAs = new JMenuItem(ResourceReader.msg("arqMenuComo")), // "Gravar Como"                 - //DEBUG: turned off (gravar is working as "save as")
   //DEBUG: menuItemRegisterAsExerc = new JMenuItem(ResourceReader.msg("arqMenuExerc")), // "Gravar como exercício" - //DEBUG: turned off (gravar already save if exercise)
   menuItemCopy      = new JMenuItem(ResourceReader.msg("arqMenuCopia")), // "Copiar"
   menuItemExport    = new JMenuItem(ResourceReader.msg("arqMenuExporta")), // "Exportar"
   menuItemHTML      = new JMenuItem(ResourceReader.msg("arqMenuHTML")),  // "Gerar Página HTML"
   menuItemPrint     = new JMenuItem(ResourceReader.msg("arqMenuImprima")), // "Imprimir"
   menuItemQuit      = new JMenuItem(ResourceReader.msg("arqMenuSaia")); // "Sair"

 private static javax.swing.ImageIcon getBGimage () { // javax.swing.ImageIcon
   try { // igraf/basico/img/button_file_bg.gif: 98 x 23
     return TrataImagem.getImageIcon(IMG); // igraf.basico.io.TrataImagem
   } catch (Exception e) { //
     System.err.println("Erro: ao tentar abrir imagem " + IMG + ": " + e);
     e.printStackTrace();
     }
   return null;
   }


 public MenuFile (String strMsg, MenuArquivoController fmc, final int altura) {
  menuLabel = new JLabel(strMsg, SwingConstants.CENTER) {
    public void paintComponent (java.awt.Graphics gr) {
      gr.drawImage(icon.getImage(), 0, 0, null);
      super.paintComponent(gr);
      //T int width = icon.getIconWidth(), heigtht = icon.getIconHeight();
      //T System.out.println(this.getClass().getName() + ": " + icon + " - (" + width + "," + heigtht + ")");
      }
    };
  menuLabel.addMouseListener(fmc); // igraf.moduloSuperior.controle.MenuArquivoController
  menuLabel.setOpaque(false);//(true);
  menuLabel.setForeground(EsquemaVisual.corLetras);
  menuLabel.setBorder(BorderFactory.createLineBorder(Color.black));
  menuLabel.setPreferredSize(new Dimension(WIDTHMENUFILE, altura));

  // menuItemQuit: arqMenuSaia = Exit
  // @see: MenuArquivoController fmc: actionPerformed (ActionEvent e): with 'IGraf.getInstanceIGraf().fecharIGraf();'

  // menuLabel = new JLabel(strMsg, SwingConstants.CENTER);   menuLabel.addMouseListener(fmc);
  // menuLabel.setOpaque(true);   menuLabel.setBackground(corFundoMenuTopo);
  // menuLabel.setForeground(EsquemaVisual.corLetras);  menuLabel.setBorder(BorderFactory.createLineBorder(Color.black));
  // menuLabel.setPreferredSize(new Dimension(WIDTHMENUFILE, altura));

  buildPopupMenu(fmc);
  fmc.setMenu(this);
  }

 private void buildJMenuItem (JMenuItem jMenuItem, MenuArquivoController menuArquivoController) {
  jMenuItem.setBackground(corFundoPS);
  jMenuItem.addActionListener(menuArquivoController);
  this.add(jMenuItem);
  }


 private void buildPopupMenu (MenuArquivoController menuArquivoController) {
  buildJMenuItem(menuItemNew,menuArquivoController);
  buildJMenuItem(menuItemOpen,menuArquivoController);
  addSeparator();
  buildJMenuItem(menuItemRegister,menuArquivoController);
  //DEBUG: turned off ('menuItemRegister' is working as "save as" and already save if exercise)
  //DEBUG: buildJMenuItem(menuItemRegisterAs,menuArquivoController);
  //DEBUG: buildJMenuItem(menuItemRegisterAsExerc,menuArquivoController);
  addSeparator();
  buildJMenuItem(menuItemCopy,menuArquivoController);
  buildJMenuItem(menuItemExport,menuArquivoController);
  addSeparator();
  buildJMenuItem(menuItemHTML,menuArquivoController);
  buildJMenuItem(menuItemPrint,menuArquivoController);
  addSeparator();
  buildJMenuItem(menuItemQuit,menuArquivoController);
  }


 public JLabel getMenuFileLabel () {
  return menuLabel;
  }


 // Update messages when language is changed
 public void updateLabels () {
  menuLabel.setText(ResourceReader.msg("arquivo"));
  menuItemRegister.setText(ResourceReader.msg("arqMenuGrave")); // "Gravar"
  //DEBUG: menuItemRegisterAs.setText(ResourceReader.msg("arqMenuComo")); // "Gravar Como"
  //DEBUG: menuItemRegisterExerc.setText(ResourceReader.msg("arqMenuExerc")); // "Gravar como exercício"
  menuItemNew.setText(ResourceReader.msg("arqMenuNovo"));       // "Novo"
  menuItemOpen.setText(ResourceReader.msg("arqMenuAbra"));      // "Abrir"
  menuItemHTML.setText(ResourceReader.msg("arqMenuHTML"));      // "Gerar Página HTML"
  menuItemPrint.setText(ResourceReader.msg("arqMenuImprima"));  // "Imprimir"
  menuItemCopy.setText(ResourceReader.msg("arqMenuCopia"));     // "Copiar"
  menuItemExport.setText(ResourceReader.msg("arqMenuExporta")); // "Exportar"  
  menuItemQuit.setText(ResourceReader.msg("arqMenuSaia"));      // "Sair"
  }

 }
