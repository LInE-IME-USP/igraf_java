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
 * @description
 *
 * @see
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.eventos.menu;

import igraf.basico.io.ResourceReader;
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.controle.desenho.DesenhoPoligonoController;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import difusor.evento.CommunicationEvent;

public class IgrafMenuPoligonoEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, EventoRegistravel {

 // Indica modo de inserção de pontos
 // public static final String POINT_MODE   = ResourceReader.msg("mepPonto");
 //
 // Indica modo de inserção de segmento
 // public static final String SEGM_MODE    = ResourceReader.msg("mepSegm");
 //
 // Indica modo de inserção de triângulo
 // public static final String TRIA_MODE    = ResourceReader.msg("mepTri");
 //
 // Indica modo de inserção de retângulo
 // public static final String RECT_MODE    = ResourceReader.msg("mepRet");
 //
 // Indica modo de inserção de retângulo especial
 // public static final String S_RECT_MODE  = ResourceReader.msg("mepRetEsp");
 //
 // Indica modo de inserção de polígono regular
 // public static final String R_POLI_MODE  = ResourceReader.msg("mepPolReg");
 //
 // Indica modo de inserção de polígono qualquer
 // public static final String POLI_MODE    = ResourceReader.msg("mepPolQqr");
 //
 // Indica modo de pintura de regiões da grade
 public static final String P_PAINT_MODE = ResourceReader.msg("mepPintaPoli");


 public static final String UNDO = "undo_polygon";
 public static final String REDO = "redo_polygon";

 // Indica que o sistema deve sair do modo de entrada de polígono
 public static final String DRAW_POLYGON = "draw_polygon";

 // Indica que o polígono a ser desenhado está sendo carregado de arquivo
 public static final String LOAD_POLYGON = "load_polygon";

 public IgrafMenuPoligonoEvent (Object source) {
  super(source);
  }

  // Construtor usado para as operações de undo/redo
 public IgrafMenuPoligonoEvent (String command, Object source) {
  super(source, command);
  }

 private String argumento = "";
 private DesenhoPoligonoController dpc;

 public IgrafMenuPoligonoEvent (Object source, String command) {
  super(source, command);
  dpc = (DesenhoPoligonoController)source;
  argumento = String.valueOf(dpc.getVertexMode())  + "; " +      // 0 ou 1 para modo especial
              String.valueOf(dpc.getPolygonType()) + "; "        // 0 ou 1 para polígono regular
                           + dpc.getFillingMode()+ "; "          // 0 ou 1 para polígono preenchido
                           + dpc.getLineColorToStringRGB()+ "; " // cor do contorno do polígono
                           + dpc.getFillColorToStringRGB()+ "; " // cor do preenchimento
                           + dpc.getVertexList();                // lista de vértices do polígono 
  }

 public IgrafMenuPoligonoEvent (Object source, String command, String arg) {
  super(source, command);
  parseLoadingArgument(arg);
  }

 private String polygonType, fillingMode, strLineColor, strFillColor, vertexList, vertexMode;

 private void parseLoadingArgument (String arg) {
  StringTokenizer st = new StringTokenizer(arg, ";");
  try {
   vertexMode   = st.nextToken();
   polygonType  = st.nextToken();
   fillingMode  = st.nextToken();
   strLineColor = st.nextToken();
   strFillColor = st.nextToken();
   vertexList   = st.nextToken();
  } catch (Exception e) {
   String s = "Arquivo contendo polígono com sintaxe de registro incorreta.";
   JOptionPane.showMessageDialog(null, s, "Erro no carregamento do arquivo", JOptionPane.WARNING_MESSAGE);
   return; //e.printStackTrace();  
   }
  argumento = polygonType + "; " + fillingMode + "; " + strLineColor +
                            "; " + strFillColor+ "; " + vertexList   ;
  }

 public int getVertexMode () {
  int i = 0;

  try {
   i = Integer.parseInt(vertexMode.trim());
  } catch (Exception e) {
   System.err.println("O modo de entrada de vértices deve ser 0 (real) ou 1 (inteiro).");
   e.printStackTrace();
   }
  return i;
  }

 public int getPolygonType () {
  int i = 0;

  try {
   i = Integer.parseInt(polygonType.trim());
  } catch (Exception e) {
   System.err.println("O tipo do polígono deve ser 0 (irregular) ou 1 (regular).");
   e.printStackTrace();
   }
  return i;
  }

 public int getFillingMode () {
  int i = 0;
  try {
   i = Integer.parseInt(fillingMode.trim());
  } catch (Exception e) {
   System.err.println("O modo de preenchimento do polígono deve ser 0 (apenas arestas) ou 1 (preenchido).");
   e.printStackTrace();
   }
  return i;
  }

 public String getStrFillColor () {
  return strFillColor.trim();
  }

 public String getStrLineColor () {
  return strLineColor.trim();
  }

 public String getVertexList () {
  return vertexList.trim();
  }

 public String getDescription () {
  String [] msgVet =  { ""+getSource(), "MenuPoligono" };
  return ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a interação (clique) do usuário no menu " + "MenuPoligono"
  }

 public int getCodigoAcao () {
  if (getCommand().equals(UNDO))
   return Acao.desfazerPoligono;
 
  if (getCommand().equals(REDO))
   return Acao.refazerPoligono;
 
  return Acao.criarPoligono;
  }

 public String getArgumento () {
  return argumento;
  }

 }
