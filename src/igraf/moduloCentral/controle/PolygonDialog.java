/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 * 
 * @description It implements the
 * 
 * @see
 * 
 * @author RP, LOB
 *
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa deve ser creditado ao projeto iMática. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.controle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.PropertyChangeListener; //? property change stuff - cortar para mais simples?
import java.beans.PropertyChangeEvent; //?
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PolygonDialog extends JDialog implements ActionListener, PropertyChangeListener {

 private String typedText = null;
 private JTextField vertexField, apotemaField;

 private JOptionPane optionPane;

 private String btnString1 = "Criar polígono";
 private String btnString2 = "Cancelar";
   
 private int vertexNum;
 private float apotema;
 private boolean validData = false;

 // Returns null if the typed string was invalid; otherwise, returns the string as the user entered it.
 public String getValidatedText () {
  return typedText;
  }

 // Creates the reusable dialog
 public PolygonDialog (JFrame aFrame) {
  super(aFrame, true);
  setTitle("Parâmetros do polígono");

  vertexField  = new JTextField(10);
  apotemaField = new JTextField(10);

  //Create an array of the text and components to be displayed.
  String msgString1 = "1. Digite o número de vértices do polígono";
  String msgString2 = "2. Digite a medida do apótema do polígono";
  String msgString3 = "3. Clique abaixo no botão 'Criar polígono'";
  String msgString4 = "4. Clique na tela para definir o centro do polígono";
  Object[] array = {msgString1, vertexField, msgString2, apotemaField, " ", msgString3, msgString4};

  //Create an array specifying the number of dialog buttons
  //and their text.
  Object[] options = {btnString2, btnString1};

  //Create the JOptionPane.
  optionPane = new JOptionPane(array,JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION,null,options,options[0]);

  //Make this dialog display it.
  setContentPane(optionPane);

  //Handle window closing correctly.
  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  addWindowListener(new WindowAdapter () {
    public void windowClosing(WindowEvent we) {
     // Instead of directly closing the window, here it is changed the JOptionPane's value property
     optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
     }
    });

  //Ensure the text field always gets the first focus.
  addComponentListener(new ComponentAdapter () {
   public void componentShown(ComponentEvent ce) {
    vertexField.requestFocusInWindow();
    }
   });

  //Register an event handler that puts the text into the option pane.
  vertexField.addActionListener(this);

  //Register an event handler that reacts to option pane state changes.
  optionPane.addPropertyChangeListener(this);
  setSize(400, 260);
  setVisible(true);
  } // public PolygonDialog(JFrame aFrame)

 // This method handles events for the text field
 public void actionPerformed (ActionEvent e) {
  optionPane.setValue(btnString1);
  }

 // This method reacts to state changes in the option pane
 public void propertyChange (PropertyChangeEvent e) {
  String prop = e.getPropertyName();

  if (isVisible() && (e.getSource() == optionPane) &&
   (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
   Object value = optionPane.getValue();

   if (value == JOptionPane.UNINITIALIZED_VALUE) {
    //ignore reset
    return;
    }

   //Reset the JOptionPane's value.
   //If you don't do this, then if the user
   //presses the same button next time, no
   //property change event will be fired.
   optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

   if (btnString1.equals(value)) {
    typedText = vertexField.getText();
    try {
     vertexNum = Integer.parseInt(typedText);
    } catch (NumberFormatException nfe){
     //integer was invalid
     vertexField.selectAll();
     JOptionPane.showMessageDialog(PolygonDialog.this,"O número de vértices deve ser inteiro.","Erro",JOptionPane.ERROR_MESSAGE);
     typedText = null;
     vertexField.requestFocusInWindow();
     }

    typedText = apotemaField.getText();
    typedText = typedText.replace(',', '.');
    try {
     apotema = Float.parseFloat(typedText);
     validData = true;
     clearAndHide();
    } catch (NumberFormatException nfe){
     //float was invalid
     vertexField.selectAll();
     JOptionPane.showMessageDialog(PolygonDialog.this,"A medida do apótema deve ser um número real.","Erro",JOptionPane.ERROR_MESSAGE);
     typedText = null;
     vertexField.requestFocusInWindow();
     }

   } else { //user closed dialog or clicked cancel
    typedText = null;
    clearAndHide();
    }
   }
  } // public void propertyChange (PropertyChangeEvent e)

 public float getApotema () {
  return apotema;
  }

 public int getVertexNum () {
  return vertexNum;
  }
 
 public boolean dataValidated () {
  return vertexNum > 2 && apotema > 0;
  }


 // This method clears the dialog and hides it
 public void clearAndHide () {
  vertexField.setText(null);
  setVisible(false);
  dispose();
  }

 // public static void main(String[] args) { PolygonDialog cd = new PolygonDialog(null);
 //  System.out.println(cd.getApotema()); System.out.println(cd.getVertexNum()); }

 }
