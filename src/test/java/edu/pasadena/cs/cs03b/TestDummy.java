package edu.pasadena.cs.cs03b;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestDummy {
  @Test
  public void testEncryptionAndDecryptionWithDifferentKeys() {
    String plaintext = "HELLO WORLD";
    String key1 = "ABCDE";
    String key2 = "12345";

    Cipher cipher = new Cipher();
    DoubleCipher doubleCipher = new DoubleCipher(cipher);

    String ciphertext = cipher.columnarTranspositionCipher(plaintext, key1);
    String doubleCiphertext = doubleCipher.doubleTranspositionCipher(plaintext, key1, key2);

    String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
    String doubleDecryptedText = doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);

    assertEquals(plaintext, decryptedText);
    assertEquals(plaintext, doubleDecryptedText);
  }

  @Test
  public void testEncryptionAndDecryptionWithLongText() {
    String plaintext = "THIS IS A LONGER TEXT THAT SHOULD STILL BE ENCRYPTED AND DECRYPTED CORRECTLY";
    String key1 = "123456";
    String key2 = "ABCDEF";

    Cipher cipher = new Cipher();
    DoubleCipher doubleCipher = new DoubleCipher(cipher);

    String ciphertext = cipher.columnarTranspositionCipher(plaintext, key1);
    String doubleCiphertext = doubleCipher.doubleTranspositionCipher(plaintext, key1, key2);

    String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
    String doubleDecryptedText = doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);

    assertEquals(plaintext, decryptedText);
    assertEquals(plaintext, doubleDecryptedText);
  }

  @Test
  public void testEncryptionAndDecryptionWithSpecialCharacters() {
    String plaintext = "HELLO, WORLD! THIS TEXT INCLUDES SPECIAL CHARACTERS.";
    String key1 = "12345";
    String key2 = "67890";

    Cipher cipher = new Cipher();
    DoubleCipher doubleCipher = new DoubleCipher(cipher);

    String ciphertext = cipher.columnarTranspositionCipher(plaintext, key1);
    String doubleCiphertext = doubleCipher.doubleTranspositionCipher(plaintext, key1, key2);

    String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
    String doubleDecryptedText = doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);

    assertEquals(plaintext, decryptedText);
    assertEquals(plaintext, doubleDecryptedText);
  }
}