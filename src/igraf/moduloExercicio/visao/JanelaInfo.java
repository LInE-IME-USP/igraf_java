/*
 * iGraf - Interactive Graphics on the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author Le�nidas de Oliveira Brand�o
 * 
 * @description Window to inform user of his score in exercise
 *
 * @see igraf/IGrafController.java:
 *      public void filtrarEventoEntrada (CommunicationEvent ie): if (de.getDiagnostico()!=null && IGraf.iLM_PARAM_Feedback==null) { new JanelaDiagnostico(de); ... }
 *  
 * @credits
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o. 
 * 
 */

package igraf.moduloExercicio.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.basico.io.TrataImagem;
import igraf.basico.util.Configuracoes;
import igraf.basico.util.EsquemaVisual;
import igraf.moduloExercicio.eventos.DiagnosticEvent;


public class JanelaInfo extends JFrame {

  public static final String LOGONAME = "logo-igraf.gif"; // igraf/basico/img/logo-igraf.gif: 87 x 33

  private static int
     COLS = 100, LINS = 33,
     largura = 610, altura  = 410;

  // "Automatic assessment of iGraf" and " The answer was send in:"
  private static final String strMessageSuccess = ResourceReader.msg("exercJDmsg1") + "\n" + ResourceReader.msg("exercJDmsg2") + " ";

  private JPanel painel_princ,  painel_fundo;
  private JPanel painel_topo;

  private JPanel img, img_logo;

  private JTextArea Texto;

  private JButton Bfim;

  public static final Color
    corFundoTexto   = EsquemaVisual.corFundoSelecionado, // Color(0, 100, 150)
    buttonColor     = EsquemaVisual.fundoTopo,           // backgound color to the OK button
    bgColor         = EsquemaVisual.corBackground;       // backgound color to this window

  public String dados () { //
    return "";
    }


  public JanelaInfo (DiagnosticEvent diagnosticEvent) {
    // Message to be presented
    // strMessageSuccess + strTime + diagnosticEvent.getAvaliacao()
    // String strMsg  = diagnosticEvent.getAvaliacao();
    this(diagnosticEvent.getAvaliacao());
    }

  public JanelaInfo (String strMsg) {
    super("iGraf - " + ResourceReader.msg("exercJDtitle")); // 
    //System.out.println("src/igraf/moduloExercicio/visao/JanelaInfo.java:  "+strMessageSuccess);

    String strTime = "";
    if (!IGraf.ehApplet)
      strTime = getTime();

    addWindowListener( new WindowAdapter() {
	public void windowClosing(WindowEvent e) { setVisible(false); dispose(); }
      });

    painel_topo = new JPanel();
    painel_princ= new JPanel();
    painel_fundo= new JPanel();

    // Painel topo:
    JPanel img_logo = null; //New Imagem img_logo = null;
    try {
      // igraf/basico/img/logo-igraf.gif: 87 x 33
      String strLoc = this.getClass().getName()+": "+LOGONAME;
      img_logo = TrataImagem.getImageToJPanel(TrataImagem.pegaImagem(LOGONAME), COLS, LINS, strLoc);
    } catch (Exception e) { // 
      System.err.println("Erro: ao tentar desenhar logo: "+e);
      // e.printStackTrace();
      }


    // Since 'logo-igraf.gif' is transparent, it must match the JanelaInfo's background color
    img_logo.setBackground(bgColor);

    // exercJDtitle = Evaluation survey
    // exercJDmsg1 = Automatic assessment of iGraf
    JLabel labTitulo = new JLabel(ResourceReader.msg("exercJDmsg1"));
    labTitulo.setFont(EsquemaVisual.fontHB12);

    painel_topo.add("West",labTitulo);
    painel_topo.add("Eeast",img_logo);
    painel_topo.setBackground(bgColor); // it must match the JanelaInfo's background color

    Texto = new JTextArea( strMsg, 16,48 );
    Texto.setEditable(false);
    Texto.setBackground(corFundoTexto);
    Texto.setForeground(EsquemaVisual.fundoTopo);
    Texto.setFont(EsquemaVisual.fontHP12);
    // System.out.println("JanelaInfo: Texto=<"+Texto.getAccessibleContext()+">");

    this.setForeground(corFundoTexto);
    this.setBackground(bgColor); //

    this.Bfim = new JButton("OK");
    this.Bfim.setSize(10,20);
    this.Bfim.setFont(EsquemaVisual.fontHP11); // new Font ("Helvetica", Font.BOLD, 10)
    this.Bfim.setBackground(buttonColor);
    this.Bfim.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { dispose(); }
         });

    this.painel_fundo.setBackground(bgColor);
    this.painel_fundo.add(Bfim);

    this.painel_princ.setLayout(new BorderLayout());
    this.painel_princ.add(painel_topo, BorderLayout.NORTH);
    this.painel_princ.add(Texto, BorderLayout.CENTER);
    this.painel_princ.add(painel_fundo, BorderLayout.SOUTH);

    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(painel_topo, BorderLayout.NORTH);
    this.getContentPane().add(painel_princ, BorderLayout.CENTER);//SOUTH);

    this.setSize(largura, altura); // vem do IGeom.java
 
    this.setVisible(true);

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
