/*
 * iGraf - Interactive Graphics on the Internet:  http://www.matematica.br/igraf
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
 * @see igraf/moduloSuperior/PainelSuperior.java: it is the superior panel basis
 * @see igraf/moduloSuperior/PainelTitulo.java: panel with iGraf decorations
 * @see igraf/moduloSuperior/PanelBackImage.java: auxiliary panel to presents JPanel with background image
 *
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 *
 */

package igraf.moduloSuperior.visao;

import igraf.basico.util.EsquemaVisual;
import igraf.moduloSuperior.controle.entrada.EntradaExpressaoController;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;


// JComboBox implementa ActionListener por padrão
public class EntradaExpressao extends JComboBox {

 JTextComponent editor;

 public EntradaExpressao (EntradaExpressaoController eec) {

  // any keyboard use is registered
  editor = (JTextComponent)getEditor().getEditorComponent();
  editor.addKeyListener(eec);
  eec.setControlledObject(this);

  setEditable(true);
  addActionListener(eec);
  setForeground(Color.black); // Configuracoes.corLetras
  setFont(EsquemaVisual.fontHP10);
  setBackground(EsquemaVisual.corFundoEdicao);
  }


 /**
  * Insere a função editada na lista drop down
  * @param text
  */
 public void setText (String text) {
  if (text != null & !(text.length() < 1)) {
   removeItem(text);
   insertItemAt(text, 0);
   setSelectedItem(text);
   }
  }
 
 /**
  * Verifica se uma determinada função já foi editada.
  * @param String funcao
  * @return boolean
  */
 public boolean temFuncao (String funcao) {
  for (int i = 0; i < getItemCount(); i++) {
   if (((String)getItemAt(i)).equals(funcao))
    return true;
   }
  return false;
  }

 public JTextComponent getTextEditor () {
  return editor;
  }

 }
