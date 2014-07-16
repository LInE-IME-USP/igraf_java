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
import igraf.moduloArquivo.eventos.EventoRegistravel;
import igraf.moduloCentral.eventos.ModuloCentralDisseminavelEvent;
import igraf.moduloCentral.modelo.Acao;

public class JanelaIntegralEvent extends CommunicationEvent implements ModuloCentralDisseminavelEvent, EventoRegistravel {

 public static final String DRAW = "drawArea",
                            CHANGE = "changeArea",
                            ERASE = "eraseArea";
 private String lista[] = {"", ""};
 private double from, to;
 private boolean state = false;

 /**
  * Construtor que cria um objeto PainelIntegralEvent vazio para notificar sobre a necessiade
  * de remover a regi�o de integra��o da �rea de desenho atual.
  * @param source
  */
 public JanelaIntegralEvent (Object source) {
  super(source);
  setCommand("eraseArea");
   }

 /**
  * Construtor que cria um objeto PainelIntegralEvent que cont�m o estado que o desenho deve
  * assumir na tela.  Entenda-se por estado, a divis�o (ou n�o) da �rea pintada em duas regi�es
  * distintas, uma abaixo e outra acima do eixo das abscissas.
  * @param source
  * @param state
  */
 public JanelaIntegralEvent (Object source, boolean state) {
  this(source);
  this.state = state;
  setCommand("changeArea");
  } 

 /**
  * Construtor que cria um objeto PainelIntegralEvent que cont�m as informa��es necess�rias para delimitar
  * a �rea que deve ser pintada como resultado da integra��o definida.  A lista de strings cont�m o par de
  * fun��es que limitam a regi�o na vertical, from e to s�o os valores reais que delimitam a regi�o lateralmente.
  * @param source
  * @param lista
  * @param from
  * @param to
  */
 public JanelaIntegralEvent (Object source, String[] lista, double from, double to) {
  this(source);
  this.lista = lista;
  this.from = from;
  this.to = to;
  setCommand("drawArea");
  }

 public JanelaIntegralEvent (Object source, String arg) {
  this(source);
  setCommand("drawArea");
  quebraArgumento(arg);
  }

 public boolean getState () {
  return state;
  }

 public double getFrom () {
  return from;
  }

 public String[] getLista () {
  return lista;
  }

 public double getTo () {
  return to;
  }

 private void quebraArgumento (String arg) {
  try {
   int a = arg.indexOf("from:");
   int b = arg.indexOf("to:");
   int c = arg.indexOf("modo:");
   int d = arg.indexOf("list:[");
   int e = arg.indexOf("]");
   int f = arg.indexOf("[", e);
   int g = arg.indexOf("]", f);
   
   from = Double.parseDouble(arg.substring(a+5, b-1));
   to   = Double.parseDouble(arg.substring(b+3, c-1));

   //leo Eliminado:
   //leo state = Boolean.parseBoolean(arg.substring(c+5, d-1));
   //leo Porque: Boolean.parseBoolean(String): return Boolean.toBoolean(String): return ((name != null) && name.equalsIgnoreCase("true"));
   try {  //leo
      String str_aux = arg.substring(c+5, d-1); //leo
      state = ((str_aux != null) && str_aux.equalsIgnoreCase("true")); 
    } catch (Exception eB) { //leo
      System.err.println("Erro: igraf/moduloJanelasAuxiliares/eventos/JanelaIntegralEvent.java: em teste de boolean: "+eB.toString());
       }
   //leo

   lista[0] = arg.substring(d+6, e);
   lista[1] = arg.substring(f+1, g);
  } catch (Exception e) { // carrega utilizando o formato das vers�es mais antigas
   int h = arg.indexOf("list:");
   int i = arg.indexOf(" ", h+1);
   lista[0] = arg.substring(h+5, i);
   lista[1] = arg.substring(i+1);
   }
  }

 public String getArgumento () {
  return "from:" + getFrom() + 
         " to:" + getTo() + 
         " modo:" + getState() + 
         " list:[" + lista[0] +
         "] [" + lista[1] + "]";
  }

 public int getCodigoAcao () {
  return Acao.intDefinida;
  }


 public String getDescription () {
  return objetivo(ResourceReader.msg("msgInternalCalcIntegral")); // "notificar o sistema sobre altera��es reqalizadas na janela de c�lculo integral"
  }

 }
