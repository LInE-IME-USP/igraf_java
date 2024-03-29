/*
 * iGraf : interactive Graphics in the Internet
 * LInE - http://line.ime.usp.br
 * 
 * Free interactive solutions to teach and learn
 * http://www.matematica.br
 * 
 * @description Interface que marca os eventos que devem ser registrados para que possam ser recuperados
 * a partir de arquivos gravados pelo iGraf ou para que possam ser revistos na sess�o atual
 * com o uso do recurso Hist�rico.
 * 
 * @see 
 * 
 * @author RP, LOB
 * 
 * @credits
 * This source code must be credited to iMath Project. In order to contribute, please
 * contact the iMath coordinator Le�nidas O. Brand�o.
 * 
 * O c�digo fonte deste programa deve ser creditado ao projeto iM�tica. Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Le�nidas O. Brand�o.
 * 
 */

package igraf.moduloArquivo.eventos;

//import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
//import difusor.evento.CommunicationEvent;

public class LoadingAppletEvent extends LoadingIGrafEvent {

 public LoadingAppletEvent (Object source, int userTypeView) {
  super(source, userTypeView);
  }


 // From: igraf/IGraf.java: public void IGraf(String[] args): 'igc.disseminarEventoExternamente(new LoadingAppletEvent(this, strFileContent));'
 public LoadingAppletEvent (Object source, String dados) {
  super(source, dados);
  }


 public String getDescription () {
  return objetivo(" the iGraf is running in applet mode.");
  }

 }
