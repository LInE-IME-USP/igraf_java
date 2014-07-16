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
 * @version Created on 09/01/2012, 17:42:52
 *
 * @description A panel with one check option to be selected a correspondent menu item (any item not selected became invisible)
 * 
 * @see igraf/moduloExercicio/visao/menuSelector/MenuSelectorFrame.java: instantiate 'MenuItemPanel'
 * @see igraf/moduloExercicio/visao/menuSelector/MenuItemPanel.java: instantiate this JPanel
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloExercicio.visao.menuSelector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import igraf.basico.util.EsquemaVisual;

public class DisabableMenuItem extends JPanel {

 public static final Color
   corBackground = EsquemaVisual.corBackground, // new Color( 0, 25, 50)
   corForeground = EsquemaVisual.corForeground; // Color.black

 private int menuCode;
 private boolean isMenuItemVisible;
 private JCheckBox check;
 private JLabel menuLabel;

 //TODO: em 'DisabableMenuItem' passar o IgrafMenu correspondente
 public DisabableMenuItem (String menuLabel, String menuTip, int menuCode) {
  this.isMenuItemVisible = true;
  this.menuCode = menuCode;
  initComponents();
  setMenuLabel(menuLabel);
  setToolTipText(menuTip);
  }


 public void setItemChecked (boolean isVisible) {
  this.isMenuItemVisible = isVisible;
  if (!isVisible) // if menu item is not visible => uncheck the correspondent check item
    check.setSelected(false);
  }


 public int getMenuCode () {
  return menuCode;
  }

 private void setMenuLabel (String menuLabel) {
  this.menuLabel.setText(menuLabel);
  }
 
 public String toString () {
  return menuLabel.getText();
  }

 public void setToolTipText (String text) {
  menuLabel.setToolTipText(text);
  }

 public boolean isSelected () {
  return check.isSelected();
  }

 //D private static int count=0;
 //D if(count++==0) try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

 // This method is called from within the constructor to initialize the form.
 // From: igraf.moduloExercicio.visao.menuSelector.MenuSelectorFrame.buildItemPanel(MenuSelectorFrame.java:69)
 private void initComponents () {
  //T System.out.println("src/igraf/moduloExercicio/visao/menuSelector/DisabableMenuItem.java");
  check = new JCheckBox();
  menuLabel = new JLabel();

  setLayout(new BorderLayout());

  this.setBackground(corBackground);
  check.setBackground(corBackground);
  menuLabel.setBackground(corBackground);

  this.setForeground(corForeground);
  check.setForeground(corForeground);
  menuLabel.setForeground(corForeground);

  menuLabel.addMouseListener(new MouseAdapter() {
   public void mouseClicked(MouseEvent e) {
    check.setSelected(!check.isSelected());
    isMenuItemVisible = check.isSelected();
    menuLabel.setEnabled(check.isSelected());
    }
   });

  check.setSelected(isMenuItemVisible); // src/igraf/moduloCentral/visao/menu/IgrafMenu.java: public boolean [] getEnabledMenuItem()

  check.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) { checkActionPerformed(evt); }
    });
  add(check, BorderLayout.LINE_START);

  menuLabel.setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 1));
  add(menuLabel, BorderLayout.CENTER);
  }


 // If configuration item is unchecked => shows it in vanishing format (not enabled)
 private void checkActionPerformed (ActionEvent evt) {
  menuLabel.setEnabled(check.isSelected());
  }


 }
