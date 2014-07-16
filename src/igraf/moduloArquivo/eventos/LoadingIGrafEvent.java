/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Interface que marca os eventos que devem ser registrados para que possam ser recuperados
 * a partir de arquivos gravados pelo iGraf ou para que possam ser revistos na sessão atual
 * com o uso do recurso Histórico.
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

package igraf.moduloArquivo.eventos;

import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import difusor.evento.CommunicationEvent;

public class LoadingIGrafEvent extends CommunicationEvent implements ModuloArquivoDisseminavelEvent, ModuloCentralDisseminavelEvent {

 private String dados = null;
 public static final int STUDENT_VIEW = 0;
 public static final int TEACHER_VIEW = 1;
 private int userTypeView;

 //DEBUG: try{String srtA=""; System.out.print(srtA.charAt(3));}catch(Exception e) {e.printStackTrace(); }

 public LoadingIGrafEvent (Object source, int userTypeView) {
  super(source);
  this.userTypeView = userTypeView;
  }


 // From: igraf/IGraf.java: public void IGraf(String[] args): 'igc.disseminarEventoExternamente(new LoadingIGrafEvent(this, strFileContent));'
 public LoadingIGrafEvent (Object source, String dados) {
  super(source);
  this.dados = dados;
  }

 // dados sao todas as informacoes gravadas em um arquivo .grf
 public String getDados () {
  return dados;
  }

 public int getUserTypeView () {
  return userTypeView;
  }

 public String getDescription () {
  return objetivo(" the iGraf is running in application mode.");
  }

 }
