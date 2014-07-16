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
 * @see igraf/moduloArquivo/ModuloArquivo.java
 * 
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 *
 */

package igraf.moduloCentral.controle.desenho;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.controle.PolygonDialog;
import igraf.moduloCentral.eventos.IgrafTabUpdateEvent;
import igraf.moduloCentral.eventos.ResetEvent;
import igraf.moduloCentral.eventos.menu.IgrafMenuPoligonoEvent;
import igraf.moduloCentral.modelo.RegularPolygonModel;
import igraf.moduloCentral.visao.desenho.Desenho;
import igraf.moduloCentral.visao.desenho.DesenhoPoligono;
import igraf.moduloCentral.visao.desenho.DesenhoRegiaoPlano;
import igraf.moduloCentral.visao.plotter.Plotter;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEditSupport;

import difusor.evento.CommunicationEvent;

public class DesenhoPoligonoController extends DesenhoController {

 protected Vector listaDesenhoOculto = new Vector();
 public boolean modoCriacao;
 private boolean pintar = false;

 private int creationType = -1;
 public final int POINT = 0, SEGMENT = 1, TRIANGLE = 2, RECTANGLE = 3,
                  S_RECTANGLE = 4, POLYGON = 5,  R_POLYGON = 6, S_POLYGON = 7, P_POLYGON = 8;

 private RegularPolygonModel rpm;


 // implementação do processo undo/redo
 protected UndoableEditSupport ues = new UndoableEditSupport(this);
 protected UndoManager manager = new UndoManager();

 public DesenhoPoligonoController (Plotter plotter) {
  super(plotter);
  listaDesenho = new Vector();
  ues.addUndoableEditListener(manager);
  }

 public void postNewEdit () {
  UndoableDrawEdit edit = new UndoableDrawEdit(this);
  ues.postEdit(edit);
  }

 public boolean undo () {
  try {
   manager.undo();
   notificaAlteracaoEstado();
   return true;
  } catch (CannotUndoException e) {
   System.err.println("não existem ações a desfazer");
   }   return false;
  }

 public boolean redo () {
  try {
   manager.redo();
   notificaAlteracaoEstado();
   return true;
  } catch (CannotRedoException e) {
   System.err.println("não existem ações desfeitas a refazer");
   }   return false;
  }

 public Vector getListaDesenhoSemReferencia () {
  Vector aux = new Vector();
  if (listaDesenho.size() > 0) {
   DesenhoPoligono dp;
   for (int i = 0; i < listaDesenho.size(); i++) {
    dp = new DesenhoPoligono((DesenhoPoligono)listaDesenho.get(i));
    aux.add(dp);
    }
   }
  return aux;
  }

 private void resetUndoManager () {
  manager.discardAllEdits();
  }
 // fim da implementação do processo undo/redo


 //TODO: eliminar 'ResourceReader.msg(...)' usando apenas chave item (ResourceReader.msg("msgMenuGrfNovaSes") -> "msgMenuGrfNovaSe"
 public void trataEvento (CommunicationEvent ie) {

  if (ie.getCommand().equals(IgrafMenuPoligonoEvent.UNDO) ||
     ie.getCommand().equals(IgrafMenuPoligonoEvent.REDO) ||
     ie.getCommand().equals(IgrafMenuPoligonoEvent.DRAW_POLYGON))
   return;

  String command = ie.getCommand();
  modoCriacao = true;

  if (command.equals(ResourceReader.msg("msgMenuGrfNovaSes"))) {
   reset(); resetUndoManager(); return;
   }
  else
  if (command.equals(ResourceReader.msg("mepPonto"))) {
   creationType = POINT;
   }
  else
  if (command.equals(ResourceReader.msg("mepSegm"))) {
   creationType = SEGMENT;
   }
  else
  if (command.equals(ResourceReader.msg("mepTri"))) {
   creationType = TRIANGLE;
   }
  else
  if (command.equals(ResourceReader.msg("mepRet"))) {
   creationType = RECTANGLE;
   }
  else
  if (command.equals(ResourceReader.msg("mepRetEsp"))) {
   creationType = S_RECTANGLE;
   }
  else
  if (command.equals(ResourceReader.msg("mepPolQqr"))) {
   creationType = POLYGON;
   }
  else
  if (command.equals(ResourceReader.msg("mepPolReg"))) {
   creationType = R_POLYGON;
   PolygonDialog pd = new PolygonDialog(null);

   if (pd.dataValidated())
    rpm = new RegularPolygonModel(pd.getApotema(), pd.getVertexNum());
   else
    return;
   }
  else
  if (command.equals(ResourceReader.msg("mepPolEsp"))) {
   creationType = S_POLYGON;
   }
  else
  if (command.equals(ResourceReader.msg("mepPintaPoli")) || command.equals(ResourceReader.msg("mepNPintaPoli"))) {
   plotter.setPaintMode(pintar = !pintar);
   modoCriacao = false;
   }
  }


 public boolean inserePoligono (Desenho dp) { // [(-1, -3) (-4, 2 ) (3,3) ]
  postNewEdit();
  if (dp.ok) {
   listaDesenho.add(dp);
   listaDesenhoOculto.add(dp);
   notificaAlteracaoEstado();
   return true;
   }
  return false;
  }

 public void removeDesenho (Desenho d) {
  listaDesenhoOculto.remove(d);
  listaDesenho.remove(d);
  this.notificaAlteracaoEstado();
  }

 public void removeDesenho () {
  if (creationType != POINT) {
   try {
    listaDesenhoOculto.removeElementAt(listaDesenhoOculto.size()-1);
    listaDesenho.removeElementAt(listaDesenho.size()-1);
    this.notificaAlteracaoEstado();
    } catch (Exception e) { }
   }
  }

 public void removerTodos () {
  listaDesenhoOculto.removeAllElements();
  listaDesenho.removeAllElements();
  notificaAlteracaoEstado();
  }

 public void desenharTodos () {
  for (int i = 0; i < listaDesenhoOculto.size(); i++) {
   d = (DesenhoPoligono)listaDesenhoOculto.get(i);
   listaDesenho.add(d);
   }
  notificaAlteracaoEstado();
  }

 public void ocultaDesenho (Desenho d) {
  listaDesenho.remove(d);
  }

 public void ocultaTodosGraficos () {
  listaDesenho.removeAllElements();
  notificaAlteracaoEstado();
  }

 public Vector getListaDesenhoOculto () {
  return listaDesenhoOculto;
  }

 // acrescenta o vértice (x,y) ao polígono em construção
 public void insereVertice(int x, int y) {
  if (modoCriacao) {
     float px = plotter.xPixelToReal(x);
     float py = plotter.yPixelToReal(y);

     switch (creationType) {
        case POINT: criaPonto(px, py);   break;
        case SEGMENT: criaPoligono(px, py, 2, 0); break;
        case TRIANGLE: criaPoligono(px, py, 3, 0); break;
        case RECTANGLE: criaRetangulo(px, py, 0); break;
        case S_RECTANGLE: criaRetanguloEspecial(px, py, 1); break;
        case R_POLYGON: criaPoligonoRegular(px, py); break;
        case POLYGON: criaPoligono(px, py, 2012, 0); break;
        case S_POLYGON: criaPoligonoEspecial(px, py, 2012, 1); break;
      }
   }
  }

 // fazer os pontos piscarem durante a inserção
 private DesenhoPoligono d = null;
 private String  vertexList = null;
 private int vertexMode = 0;

 public String getVertexList () {
  return vertexList;
  }

 public int getPolygonType () {
  return creationType == R_POLYGON ? 1 : 0;
  }

 private void criaPonto (float x, float y) {
  d = new DesenhoPoligono(plotter, ("[("+ x +","+ y +")]"), 0, plotter.isInPaintMode(), 0);
  inserePoligono(d);
  encerraModoEntrada();
  }

 private int cont;
 private void criaPoligono (float x, float y, int nVertex, int vertexMode) {
  if (d == null) {
    d = new DesenhoPoligono(plotter, "[(" + x +"," + y + ")]", 0, plotter.isInPaintMode(), vertexMode);
    this.vertexMode = vertexMode;
    inserePoligono(d);
    cont = 1;
    }
  else {
   if (cont++ < nVertex ) {
     d.insereVertice(x, y);
     notificaAlteracaoEstado();
     }
   if (cont == nVertex)
     encerraModoEntrada();
   }
  }

 private float vx, vy;
 private void criaRetangulo (float x, float y, int vertexMode) {
  if (d == null) {
   d = new DesenhoPoligono(plotter, ("[("+ x +","+ y +")]"), 0, plotter.isInPaintMode(), vertexMode);
   this.vertexMode = vertexMode;
   inserePoligono(d);
   vx = x; vy = y;
   }
  else {
   d.insereVertice(vx, y);
   d.insereVertice(x, y);
   d.insereVertice(x, vy);
   notificaAlteracaoEstado();
   encerraModoEntrada();
   }
  }


 private void criaPoligonoEspecial (float x, float y, int i, int vertexMode) {
  criaPoligono((float)Math.round(x), (float)Math.round(y), i, vertexMode);
  }

 private void criaRetanguloEspecial (float x, float y, int vertexMode) {
  criaRetangulo((float)Math.round(x), (float)Math.round(y), vertexMode);
  }

 private void criaPoligonoRegular (float x, float y) {
  if (rpm == null) {
   modoPadrao();
   return;
   // é melhor não deixar o cursor mudar,
   // indicando que os dados de entrada estavam
   // incorretos e, além disso: tem que avisar
   }
  float a = rpm.getApotema();
  int   n = rpm.getNumVertex();
  float ang = 360 /(float) n;
  double rad = Math.toRadians(ang);
  float v = a / (float)Math.cos(rad/2);
  float px, py;

  d = new DesenhoPoligono(plotter, ("[("+ x +","+ (y+v) +")]"), 0, plotter.isInPaintMode(), 0);
  inserePoligono(d);

  Point2D original = new Point2D.Double(x, y+v);
  AffineTransform at = AffineTransform.getRotateInstance(rad, x, y);

  for (int i = 1; i < n; i++) {
     original = at.transform(original, null);
     px = (float)original.getX();
     py = (float)original.getY();
     d.insereVertice(px, py);
   }
  encerraModoEntrada();
  }

 public void encerraModoEntrada () {
  vertexList = d.toString();
  plotter.enviarEvento(new IgrafMenuPoligonoEvent(this, IgrafMenuPoligonoEvent.DRAW_POLYGON));
  modoPadrao();
  }


 // encerra inserção de vértices
 public void modoPadrao () {
  modoCriacao = false;
  vertexMode = 0;
  cont = 2012;
  d = null;
  }

 // este método e outros associados à estratégia de ocultar
 // desenhos via menu precisam ser revistos... ainda fazem sentido?
 public void notificaAlteracaoEstado () {
  IgrafTabUpdateEvent iue = new IgrafTabUpdateEvent(this, IgrafTabUpdateEvent.POLYGON_LIST_CHANGED);
  iue.notificaDesenhoOculto(listaDesenhoOculto.size() > 0);
  iue.setPolygonList(listaDesenho);
  iue.polygonListChanged(true);
  enviarEvento(iue);
  }

 public int getCreationType () {
  return creationType;
  }

 public String getLineColorToStringRGB () {
  return  d.getLineColorToStringRGB();
  }

 public String getFillColorToStringRGB () {
  return d.getFillColorToStringRGB();
  }

 public int getFillingMode () {
  return d.isFilledPolygon() ? 1 : 0;
  }

 private DesenhoPoligono aux;
 public boolean look4Point (float x, float y) {
  for (int i = 0; i < listaDesenho.size(); i++) {
   aux = (DesenhoPoligono)listaDesenho.get(i);
   if (aux.mouseOverVertex(x, y))
    return true;
   }
  return false;
  }

 public void dragPoint (int x, int y) {
  aux.changeVertex(plotter.xPixelToReal(x), plotter.yPixelToReal(y));
  }

 public int getVertexMode () {
  return vertexMode;
  }

 }
