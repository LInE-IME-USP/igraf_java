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
 * @description To encode and decode items of exercise
 * 
 * @see igraf/basico/io/Sistema.java
 *  
 * @credits
 * This source is free and provided by iMath Project (University of São Paulo - Brazil). In order to contribute, please
 * contact the iMath coordinator Leônidas O. Brandão.
 *
 * O código fonte deste programa é livre e desenvolvido pelo projeto iMática (Universidade de São Paulo). Para contribuir,
 * por favor contate o coordenador do projeto iMatica, professor Leônidas O. Brandão. 
 * 
 */

package igraf.basico.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Crypto {

  private static final String hexDigits = "0123456789abcdef";

  /**
   * Realiza um digest em um array de bytes através do algoritmo especificado
   * @param input - O array de bytes a ser criptografado
   * @param algoritmo - O algoritmo a ser utilizado
   * @return byte[] - O resultado da criptografia
   * @throws NoSuchAlgorithmException - Caso o algoritmo fornecido não seja
   * válido
   */
  public static byte [] digest (byte [] input, String algoritmo) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance(algoritmo);
    md.reset();
    return md.digest(input);
  }

  /**
   * Converte o array de bytes em uma representação hexadecimal.
   * @param input - O array de bytes a ser convertido.
   * @return Uma String com a representação hexa do array
   */
  private static String byteArrayToHexString (byte [] b) {
    StringBuffer buf = new StringBuffer();

    for (int i = 0; i < b.length; i++) {
    	int j = ((int) b[i]) & 0xFF; 
    	buf.append(hexDigits.charAt(j / 16)); 
    	buf.append(hexDigits.charAt(j % 16)); 
        }

    return buf.toString();
    }


  /**
   * Converte uma String hexa no array de bytes correspondente.
   * @param hexa - A String hexa
   * @return O vetor de bytes
   * @throws IllegalArgumentException when the string 'hexa' is not a sequence of valid hexa-characters
   */
  private static byte [] hexStringToByteArray (String hexa) throws IllegalArgumentException {
    //T if (hexa.length()%2 != 0)try { String srtA=""; System.out.print(srtA.charAt(3)); } catch(Exception e) { e.printStackTrace(); }
    if (hexa.length() % 2 != 0) { // the string must have a even number of charecters
       throw new IllegalArgumentException("invalid hexa code: #" + hexa + "=" + hexa.length() + " is supposed to be even");
       }

    byte [] b = new byte[hexa.length() / 2];

    for (int i = 0; i < hexa.length(); i+=2) {
        b[i / 2] = (byte) ((hexDigits.indexOf(hexa.charAt(i)) << 4) | (hexDigits.indexOf(hexa.charAt(i + 1))));
        }
    return b;
    }

  
  public static String stringToHex (String s) {
    String aux = "_h";
    aux += byteArrayToHexString(s.getBytes());
    return aux;
    }
  
  
  public static String hexToString (String str) {
    if (str.length() < 2 || !str.substring(0, 2).equals("_h")) {
       // System.err.println("Crypto.java: hexToString(" + str + ")");
       return str;
       }
    return new String(hexStringToByteArray(str.substring(2)));
    }
  
  
  public static String criptografiaMD5 (String str) {
    String result = null;
    try {
     result = new String(Crypto.digest(str.getBytes(), "md5"));
    } catch (NoSuchAlgorithmException e) { }
    return result;
    }

  // a idéia é gravar a resosta do prof criptografada e, quando for 
  // conferir a resposta do aluno criptografá-la e fazer a comparação
  // sobre os resultados criptografados
  public static void mainD (String [] args) { //DEBUG
    //D byte [] b = null;
    //D String texto = "----------------------------------------------------------------------------------\n" +
    //D                 "problemas\n" +
    //D                 "figs ch03.tex e mandar para o leo\n" +
    //D                 "abri o ex07, depois o ex05 ... e deu erro!!!\n" +
    //D                 "avaliador expr equivalente: mudar a convenção de vazio e o critério de 5% de erro\n" + 
    //D                 "resolver problema de derivadas com restrições de domínio - ex. tan (x)\n" +
    //D                 "arrumar a aplicação da regra dos trapézios\n" +
    //D                 "verificar a conformidade da implementação com o algoritmo\n" +
    //D                 "o que determina a constante aparentemente é o tamanho do intervalo;\n" +
    //D                 "k = f((a+b)/2)\n" +
    //D                 "problema do comentário para o aluno\n" +
    //D                 "thread: usar ou não"; 
    //D  try {
    //D    b = Crypto.digest(texto.getBytes(), "md5");
    //D  } catch (NoSuchAlgorithmException e) {}
    //D  // converte os bytes criptografados em string
    //D  String criptografada = Crypto.stringToHex(texto);
    //D  System.out.println("texto criptografado:\n" + criptografada);
    //D  String s = new String(hexToString(criptografada.substring(2)));
    //D  System.out.println("\ntexto recuperado:\n" + s);*/

    String s2 = Crypto.hexToString("_h2d31");
    String s3 = Crypto.hexToString("_h33");
    System.out.println(s2 + " " + s3);	  
    }

  }
