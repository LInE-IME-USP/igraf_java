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
 * @description Panel to select the exercise type
 * 
 * @see igraf/IGrafController.java
 *      public void filtrarEventoEntrada (CommunicationEvent ie): if (de.getDiagnostico()!=null && IGraf.iLM_PARAM_Feedback==null) { new JanelaDiagnostico(de); ... }
 * 
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloExercicio.visao;

import igraf.moduloExercicio.eventos.DiagnosticEvent;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;


public class JanelaDiagnostico {

  // "Automatic assessment of iGraf" and " The answer was send in:"
  private static final String strMessageSuccess = ResourceReader.msg("exercJDmsg1") + "\n" + ResourceReader.msg("exercJDmsg2") + " ";

  public JanelaDiagnostico (DiagnosticEvent de) {
System.out.println("src/igraf/moduloExercicio/visao/JanelaDiagnostico.java: 1: applet=" + IGraf.ehApplet);
    String strTime = "";
    if (!IGraf.ehApplet)
      strTime = getTime();
System.out.println("src/igraf/moduloExercicio/visao/JanelaDiagnostico.java: 2: " + strTime);
//javax.swing.JFrame frame
//java.awt.Frame frame = (java.awt.Frame) javax.swing.SwingUtilities.getAncestorOfClass(this);//IGraf.getInstanceIGraf());
    JOptionPane jOptionPane = new JOptionPane(strMessageSuccess + strTime + de.getAvaliacao() + "\n", JOptionPane.INFORMATION_MESSAGE);
System.out.println("src/igraf/moduloExercicio/visao/JanelaDiagnostico.java: antes visible");
    //d javax.swing.JDialog dialogPane = jOptionPane.createDialog(IGraf.getInstanceIGraf(), "iGraf - " + ResourceReader.msg("exercJDtitle")); // "Evaluation survey"
    //d dialogPane.setBackground(java.awt.Color.white);
    //d dialogPane.setVisible(true);
//2 Object[] options = { "OK" }; // options={"Grade", "Save", "Cancel"};
//2 //selection = JOptionPane.showOptionDialog(this, "Do you want to grade now or save your work to continue later?", "Grade Or Save",
//2 //JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
//2 //jOptionPane.showOptionDialog(frame, strMessageSuccess + strTime + de.getAvaliacao() + "\n", JOptionPane.INFORMATION_MESSAGE);
//2 //showOptionDialog(java.awt.Component,java.lang.Object,java.lang.String,int,int,javax.swing.Icon,java.lang.Object[],java.lang.Object) in javax.swing.JOptionPane cannot be applied to (igraf.IGraf,java.lang.String,int)

//2 //jOptionPane.showOptionDialog(IGraf.getInstanceIGraf(), strMessageSuccess + strTime + de.getAvaliacao() + "\n", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this, options, options[0]);
//2 jOptionPane.showOptionDialog(IGraf.getInstanceIGraf(), strMessageSuccess, strTime + de.getAvaliacao() + "\n", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, getIcon(), options, options[0]);
System.out.println("src/igraf/moduloExercicio/visao/JanelaDiagnostico.java: apos visible");
    // JOptionPane.showMessageDialog(null, strMessageSuccess + strTime+ de.getAvaliacao() + "\n", "Diagnostic", JOptionPane.INFORMATION_MESSAGE);
    }

  private javax.swing.ImageIcon getIcon () {
    javax.swing.ImageIcon icon = null;
    try {
     // igraf/basico/img/logo-igraf.gif: 87 x 33
     icon = igraf.basico.io.TrataImagem.getImageIcon("igraf/basico/img/logo-igraf.gif");
     // String strLoc = this.getClass().getName()+": "+LOGONAME;
     // img_logo = TrataImagem.getImageToJPanel(TrataImagem.pegaImagem(LOGONAME), 87, 33, strLoc);
    } catch (Exception e) { //
     System.err.println("JanelaDiagnostico.java: Erro: ao tentar desenhar logo: "+e);
    // e.printStackTrace();
    }
    return icon;
    }

  private String getTime () {
    Calendar c = new GregorianCalendar();
    String dia = putZero(c.get(Calendar.DAY_OF_MONTH));
    String mes = putZero(c.get(Calendar.MONTH)+1);
    String ano = putZero(c.get(Calendar.YEAR));
    String hor = putZero(c.get(Calendar.HOUR_OF_DAY));
    String min = putZero(c.get(Calendar.MINUTE));
    String seg = putZero(c.get(Calendar.SECOND));
    
    return dia + "/" + mes + "/" + ano + " - " + hor + ":" + min + ":" + seg + "\n\n";
    }

  private String putZero (int num) {
    String str = String.valueOf(num);
    return num > 9 ? str : "0" + str;
    }

  }
