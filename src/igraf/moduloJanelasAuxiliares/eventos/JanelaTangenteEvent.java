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

package igraf.moduloJanelasAuxiliares.eventos;

import difusor.evento.CommunicationEvent;
import igraf.IGraf;
import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;

public class JanelaTangenteEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "src/igraf/moduloJanelasAuxiliares/eventos/JanelaTangenteEvent.java";

 public final static String DRAW = "drawTangent";
 public final static String ERASE = "eraseTangent";
 public final static String ANIMATE = "animTangent";
 public final static String MULTIDRAWMODE = "multiDraw";
 
 private String funcaoAtual;
 private double valorX;
 private boolean animate;
 private boolean drawMode;
 
 public JanelaTangenteEvent(Object source, String arg) {
  super(source);
  recuperaEstadoTangentes(arg);
  setCommand(JanelaTangenteEvent.DRAW);
  }
 
 public JanelaTangenteEvent(Object source, String funcaoAtual, double valorX) {
  super(source);
  this.funcaoAtual = funcaoAtual;
  this.valorX = valorX;
  setCommand(JanelaTangenteEvent.DRAW);
  }
 
 
 public JanelaTangenteEvent(Object source, boolean drawMode) {
  super(source);
  this.drawMode = drawMode;
  setCommand(JanelaTangenteEvent.MULTIDRAWMODE);
  }
 
 public JanelaTangenteEvent(Object source, String funcaoAtual, boolean animate) {
  super(source);
  this.funcaoAtual = funcaoAtual;
  this.animate = animate;
  setCommand(JanelaTangenteEvent.ANIMATE);
  }
 
 public JanelaTangenteEvent(Object source) {
  super(source);
  setCommand(JanelaTangenteEvent.ERASE);
  }
 
 public boolean getAnimationMode(){
  return animate;
  }
 
 public String getFuncaoAtual() {
  return funcaoAtual;
  }
 
 public double getValorX() {
  return valorX;
  }

 public boolean getDrawMode() {
  return drawMode;
  }

 public void recuperaEstadoTangentes (String s) {
  int ini = 0, fim = 0;
  String aux = "";
  try {
   ini = s.indexOf("vx:");
   fim = s.indexOf(" ", ini);
   aux += s.substring(ini, fim) + " ";
   valorX = Double.valueOf(s.substring(ini+3, fim)).doubleValue();

   ini = s.indexOf("fx:");
   aux += s.substring(ini);
   funcaoAtual = s.substring(ini+3);     
  } catch (Exception e) {
   System.err.println(IGraf.debugErrorMsg(IGCLASSPATH) + "Error: A string de entrada n�o est� de acordo com o padr�o esperado\n");
   e.printStackTrace();
   }    
  
  }

 public String getDescription () {
  return objetivo(ResourceReader.msg("msgInternalTang")); // "notificar o sistema sobre altera��es reqalizadas na janela de visualiza��o de tangentes"
  }

 }
