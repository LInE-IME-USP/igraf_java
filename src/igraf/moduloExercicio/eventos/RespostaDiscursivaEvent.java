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
 * @description Module to deal with exercises. Event of discursive answer.
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

package igraf.moduloExercicio.eventos;

import igraf.IGraf;

public class RespostaDiscursivaEvent extends RespostaEvent {

 //DEBUG: if IGraf.IS_DEBUG get a complete path of this class
 public static final String IGCLASSPATH = "igraf/moduloExercicio/eventos/RespostaDiscursivaEvent.java";

 private String comentario;
 private int acao;

 public RespostaDiscursivaEvent (String comentario, Object source, int acao) {
   super(source);  
   this.comentario = comentario;
   this.acao = acao;
   setCommand(READ_EXERCISE);
   }
 
 public String getComentario () {
   return comentario;
   }
 
 public String getArgumento () {
   return comentario;
   }
 
 public int getCodigoAcao () {
   return acao;
   }

 public String getRespostaGabarito () {
   if (IGraf.IS_DEBUG) { //DEBUG: devolvendo vazio??? Ver de onde vem
     System.err.println(IGraf.debugErrorMsg(IGCLASSPATH)+"DEBUG: getRespostaGabarito() devolvendo string vazia!");
     IGraf.launchStackTrace();
     }
   return "";
   }

 }
