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
 * This source is free and provided by iMath Project (University of S�o Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 *
 * O c�digo fonte deste programa � livre e desenvolvido pelo projeto iM�tica (Universidade de S�o Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
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

 // Indica modo de inser��o de pontos
 // public static final String POINT_MODE   = ResourceReader.msg("mepPonto");
 //
 // Indica modo de inser��o de segmento
 // public static final String SEGM_MODE    = ResourceReader.msg("mepSegm");
 //
 // Indica modo de inser��o de tri�ngulo
 // public static final String TRIA_MODE    = ResourceReader.msg("mepTri");
 //
 // Indica modo de inser��o de ret�ngulo
 // public static final String RECT_MODE    = ResourceReader.msg("mepRet");
 //
 // Indica modo de inser��o de ret�ngulo especial
 // public static final String S_RECT_MODE  = ResourceReader.msg("mepRetEsp");
 //
 // Indica modo de inser��o de pol�gono regular
 // public static final String R_POLI_MODE  = ResourceReader.msg("mepPolReg");
 //
 // Indica modo de inser��o de pol�gono qualquer
 // public static final String POLI_MODE    = ResourceReader.msg("mepPolQqr");
 //
 // Indica modo de pintura de regi�es da grade
 public static final String P_PAINT_MODE = ResourceReader.msg("mepPintaPoli");


 public static final String UNDO = "undo_polygon";
 public static final String REDO = "redo_polygon";

 // Indica que o sistema deve sair do modo de entrada de pol�gono
 public static final String DRAW_POLYGON = "draw_polygon";

 // Indica que o pol�gono a ser desenhado est� sendo carregado de arquivo
 public static final String LOAD_POLYGON = "load_polygon";

 public IgrafMenuPoligonoEvent (Object source) {
  super(source);
  }

  // Construtor usado para as opera��es de undo/redo
 public IgrafMenuPoligonoEvent (String command, Object source) {
  super(source, command);
  }

 private String argumento = "";
 private DesenhoPoligonoController dpc;

 public IgrafMenuPoligonoEvent (Object source, String command) {
  super(source, command);
  dpc = (DesenhoPoligonoController)source;
  argumento = String.valueOf(dpc.getVertexMode())  + "; " +      // 0 ou 1 para modo especial
              String.valueOf(dpc.getPolygonType()) + "; "        // 0 ou 1 para pol�gono regular
                           + dpc.getFillingMode()+ "; "          // 0 ou 1 para pol�gono preenchido
                           + dpc.getLineColorToStringRGB()+ "; " // cor do contorno do pol�gono
                           + dpc.getFillColorToStringRGB()+ "; " // cor do preenchimento
                           + dpc.getVertexList();                // lista de v�rtices do pol�gono 
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
   String s = "Arquivo contendo pol�gono com sintaxe de registro incorreta.";
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
   System.err.println("O modo de entrada de v�rtices deve ser 0 (real) ou 1 (inteiro).");
   e.printStackTrace();
   }
  return i;
  }

 public int getPolygonType () {
  int i = 0;

  try {
   i = Integer.parseInt(polygonType.trim());
  } catch (Exception e) {
   System.err.println("O tipo do pol�gono deve ser 0 (irregular) ou 1 (regular).");
   e.printStackTrace();
   }
  return i;
  }

 public int getFillingMode () {
  int i = 0;
  try {
   i = Integer.parseInt(fillingMode.trim());
  } catch (Exception e) {
   System.err.println("O modo de preenchimento do pol�gono deve ser 0 (apenas arestas) ou 1 (preenchido).");
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
  return ResourceReader.msgComVar("msgInternalChangeClickMenu","OBJ",msgVet); // "Evento gerado na classe " + getSource() + " com o objetivo de notificar a intera��o (clique) do usu�rio no menu " + "MenuPoligono"
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
