/*
 * @author Leônidas de Oliveira Brandão
 * @see    igraf/io/TrataImagem.java
 * @description
 * Truque para pegar local das imagens a partir de "TrataImage.pegaImagem(str_imagem)"
 *  image = (Toolkit.getDefaultToolkit().getImage(
 *            (trataClasse != null ? trataClasse : (trataClasse = pegaClasse(ComponentImage))).getResource(str_imagem)));
 */

package igraf.basico.img;


public class LocalizacaoImagens {
  public String nome = this.getClass().getName();
  }
