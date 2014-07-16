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
 * @description Base class to each primary menu item ("primary buttons")
 * @see IgrafMenu.java and LanguageUpdatable
 * @see 'src/igraf/moduloCentral/visao/menu/IgrafMenu.java': where it is instantiated in
 * @see 'src/difusor/i18N/LanguageUpdatable.java': interface with a single method ('void updateLabels()')
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão.
 * 
 */

package igraf.moduloCentral.visao.menu;

import java.awt.Rectangle;

import igraf.basico.io.ResourceReader;
import igraf.moduloCentral.controle.menu.IgrafMenuController;
import difusor.i18N.LanguageUpdatable;

public class SubMenuIdioma extends IgrafMenu implements LanguageUpdatable {

 private String [] listaOpcoes = {
   ResourceReader.msg("madPort"),
   ResourceReader.msg("madEngl")
   };

 private String[] descricao = {
   ResourceReader.msg("madOpcaoPort"),  
   ResourceReader.msg("madOpcaoEngl")
   };

 // Constructor: index in {0, 1, 2, 3, 4, 5, 6 }, meaning
 //   0: IgrafMenuGraficoController
 //   1: IgrafMenuCalculoController
 //   2: IgrafMenuAnimacaoController
 //   3: IgrafMenuPoligonoController
 //   4: IgrafMenuEdicaoController
 //   5: IgrafMenuExercicioController
 //   6: IgrafMenuAjudaController
 public SubMenuIdioma (IgrafMenuController imc, int index) {
  super(imc, index);
  isSubMenu = true;
  setListaItens(listaOpcoes, descricao);
  r = new Rectangle(550, 227, 120, listaOpcoes.length*20);
  setBounds(r);
  }

 public void updateLabels () {
  //T System.out.println("src/igraf/moduloCentral/visao/menu/SubMenuIdioma.java: updateLabels()");
  String [] labels = {
    ResourceReader.msg("madPort" ), 
    ResourceReader.msg("madEngl") };

  String[] help = {
    ResourceReader.msg("madOpcaoPort"),  
    ResourceReader.msg("madOpcaoEngl") };

  listaOpcoes = labels;  // to ensure no one get the wrong vector...
  descricao = help; // to ensure no one get the wrong vector...

  //TODO: nova versao de PopupMenu NAO implementada para 'submenu' idioma
  updateLabels(labels, help);

  }

 public String toString () {
  return ResourceReader.msg("madLang");
  }

 }
