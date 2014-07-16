/*
 * iGraf - Interactive Graphics on the Internet: http://www.matematica.br/igraf
 * 
 * Free interactive solutions to teach and learn
 * 
 * iMath Project: http://www.matematica.br
 * LInE           http://line.ime.usp.br
 *
 * @author Leônidas de Oliveira Brandão
 *
 * @description Windos with basic information of iGraf. Credits.
 * 
 * @see igraf/menu/MenuAjuda.java: chama esta classe
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.moduloAjuda.visao; 


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import igraf.basico.io.ResourceReader;
import igraf.basico.io.TrataImagem;
import igraf.basico.util.Configuracoes;
import igraf.basico.util.EsquemaVisual;


public class JanelaSobre extends JFrame {

  public static final String LOGONAME = "logo-igraf.gif"; // igraf/basico/img/logo-igraf.gif: 87 x 33

  private static int largura = 310,
                     altura  = 410;

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
    StringBuffer stringbuffer = new StringBuffer(ResourceReader.msg("msgMem")+": "); // Free Memory
    try {
      stringbuffer.append(Runtime.getRuntime().freeMemory());
      stringbuffer.append(" bytes.\n");
    } catch (Exception e) { System.err.println("Erro: memória disponível, janela sobre iGeom: "+e); }
    return stringbuffer.toString();
    }

   
  public JanelaSobre () {
    super(ResourceReader.msg("msgAFsobre")); // "Sobre o iGeom"
    addWindowListener( new WindowAdapter() {
	public void windowClosing(WindowEvent e) { 
		setVisible(false);
		dispose(); }
      });
    // Message to be presented
    String strMsg  =
      ResourceReader.msg("msgAFmsg0") +                                      //" Sistema para ensino/aprendizagem de\n"
      ResourceReader.msg("msgAFmsg1") +                                      //"   Geometria (Geometria Dinâmica)\n"
      ResourceReader.msg("msgAFmsg2") +                                      //"     http://www.matematica.br\n\n"

      ResourceReader.msg("msgAFmsg3") +                                      // "Desenvolvimento:\n"
      ResourceReader.msg("msgAFmsg4") +                                      // "Reginaldo do Prado\n\n"

      ResourceReader.msg("msgAFmsg5") +                                      // "Coordenação:\n"
      ResourceReader.msg("msgAFmsg6") +                                      // "Leônidas Oliveira Brandão - DCC - IME\n\n\n"
      ResourceReader.msg("msgAFmsg7") +                                      //" Instituto de Matemática e Estatística\n"
      ResourceReader.msg("msgAFmsg8") +                                      //" Universidade de São Paulo\n"
      ResourceReader.msg("msgAFmsg9","VERSAO",Configuracoes.getVersion()) +  //" Versão "+versao+"\n"
      "\n" + dados(); // dados(versao);

    painel_topo = new JPanel();
    painel_princ= new JPanel();
    painel_fundo= new JPanel();

    // Painel topo:
    JPanel img_logo = null; //New Imagem img_logo = null;
    try {
      // igraf/basico/img/logo-igraf.gif: 87 x 33
      String strLoc = this.getClass().getName()+": "+LOGONAME;
      img_logo = TrataImagem.getImageToJPanel(TrataImagem.pegaImagem(LOGONAME), 87, 33, strLoc);
    } catch (Exception e) { // 
      System.err.println("Erro: ao tentar desenhar logo: "+e);
      // e.printStackTrace();
      }


    // Since 'logo-igraf.gif' is transparent, it must match the JanelaSobre's background color
    img_logo.setBackground(bgColor);

    JLabel labTitulo = new JLabel(ResourceReader.msg("msgAFsobre"));
    labTitulo.setFont(EsquemaVisual.fontHB12);

    painel_topo.add("West",labTitulo);
    painel_topo.add("Eeast",img_logo);
    painel_topo.setBackground(bgColor); // it must match the JanelaSobre's background color

    Texto = new JTextArea( strMsg, 16,48 );
    Texto.setEditable(false);
    Texto.setBackground(corFundoTexto);
    Texto.setForeground(EsquemaVisual.fundoTopo);
    Texto.setFont(EsquemaVisual.fontHP12);
    // System.out.println("JanelaSobre: Texto=<"+Texto.getAccessibleContext()+">");

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

  }
