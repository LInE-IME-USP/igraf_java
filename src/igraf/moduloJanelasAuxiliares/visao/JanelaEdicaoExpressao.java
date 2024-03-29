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
 * @description Draws animation to functions with parameters
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

package igraf.moduloJanelasAuxiliares.visao;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloJanelasAuxiliares.controle.JanelaEdicaoExpressaoController;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

public class JanelaEdicaoExpressao extends JFrame implements ItemListener {

 private int functionIndex = 0;
 private JanelaEdicaoExpressaoController jeec;

 private javax.swing.JButton cancelButton;
 private javax.swing.JComboBox colorComboBox;
 private javax.swing.JComboBox functionComboBox;
 private javax.swing.JPanel jPanel1;
 private javax.swing.JPanel jPanel2;
 private javax.swing.JPanel jPanel3;
 private javax.swing.JPanel jPanel4;
 private javax.swing.JTextField tf;
 private javax.swing.JLabel label;
 private javax.swing.JButton okButton;

 public JanelaEdicaoExpressao (JanelaEdicaoExpressaoController jeec) {
   super(ResourceReader.msg("msgJanEFTitulo")); // Edit function window
   initComponents();
   setColorComboBox ();
   tf.addKeyListener(jeec);
   okButton.addActionListener(jeec);
   colorComboBox.addItemListener(jeec);
   cancelButton.addActionListener(jeec);
   functionComboBox.addItemListener(jeec);
   functionComboBox.addItemListener(this);
   functionComboBox.setName("functionComboBox");
   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }

 public void setFunctionList (String [] lista) {
   functionComboBox.setModel(new DefaultComboBoxModel(lista)); // javax.swing.JComboBox functionComboBox
   functionComboBox.setSelectedIndex(functionIndex); // setSelectedItem(Object anObject)
   }

 public void setColorIndex (int index) {
   if (colorComboBox.isShowing())
     colorComboBox.setSelectedIndex(index);
   }

 public int getColorIndex () {
   return colorComboBox.getSelectedIndex();
   }

 public void setFunctionIndex (int index) {
   functionComboBox.setSelectedIndex(index);
   functionIndex = index;
   }

 public String getText () {
   return tf.getText();
   }
 
 public void setColorComboBox () {
   String lista [] = new String[Desenho.getColorNames().length+2]; 
   int i;
   for (i = 0; i < Desenho.getColorNames().length; i++) {
     lista[i] = (Desenho.getColorNames()[i]);
     }
   lista[i] = ("---------------------------------");
   //TODO habilitar o uso de outras cores
   //lista[i+1] = (ResourceReader.msg("tiOutrasCores")); // "Outras Cores"
   colorComboBox.setModel(new DefaultComboBoxModel(lista));
   colorComboBox.setName("colorComboBox");
   }

 public void itemStateChanged (ItemEvent e) {
   tf.setText((String)functionComboBox.getSelectedItem());
   functionIndex = functionComboBox.getSelectedIndex();
   }
 
 public void setText (String s) {
   tf.setText(s);
   }

 public void updateLabels () {
   cancelButton.setText(ResourceReader.msg("msgCancelar")); // 
   okButton.setText(ResourceReader.msg("msgOK")); 
   setTitle(ResourceReader.msg("msgJanEFTitulo"));
   label.setText(ResourceReader.msg("msgJanEFexiste"));
   }

 // This method is called from within the constructor to initialize the form
 private void initComponents () {
   label = new javax.swing.JLabel();
   jPanel1 = new javax.swing.JPanel();
   functionComboBox = new javax.swing.JComboBox();
   colorComboBox = new javax.swing.JComboBox();
   tf = new javax.swing.JTextField();
   jPanel2 = new javax.swing.JPanel();
   jPanel3 = new javax.swing.JPanel();
   cancelButton = new javax.swing.JButton();
   okButton = new javax.swing.JButton();
   jPanel4 = new javax.swing.JPanel();

   getContentPane().setLayout(new java.awt.GridLayout(4, 1, 0, 3));

   label.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
   label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
   label.setText(ResourceReader.msg("msgJanEFexiste"));
   getContentPane().add(label);

   jPanel1.setLayout(new java.awt.GridLayout(1, 2));
   jPanel1.add(functionComboBox);
   jPanel1.add(colorComboBox);

   getContentPane().add(jPanel1);
   getContentPane().add(tf);

   jPanel2.setLayout(new java.awt.GridLayout(1, 4 ));
   jPanel2.add(jPanel3);

   cancelButton.setText(ResourceReader.msg("msgCancelar"));
   jPanel2.add(cancelButton);

   okButton.setText(ResourceReader.msg("msgOK"));
   jPanel2.add(okButton);
   jPanel2.add(jPanel4);

   getContentPane().add(jPanel2);

   pack();
   }

 }
