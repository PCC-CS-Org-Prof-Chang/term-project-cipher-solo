package edu.pasadena.cs.cs03b;

public class DoubleCipher {
    private Cipher cipher;

    public DoubleCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    // Method to encrypt plaintext using double transposition cipher
    public String doubleTranspositionCipher(String plaintext, String key1, String key2) {
        // First transposition using key1
        String intermediateText = cipher.columnarTranspositionCipher(plaintext, key1);
        // Second transposition using key2 on the result of the first transposition
        String ciphertext = cipher.columnarTranspositionCipher(intermediateText, key2);
        return ciphertext; // Return the encrypted text
    }

    // Method to decrypt ciphertext using double transposition cipher
    public String doubleTranspositionDecipher(String ciphertext, String key1, String key2) {
        // First transposition using key2
        String intermediateText = cipher.columnarTranspositionDecipher(ciphertext, key2);
        // Second transposition using key1 on the result of the first transposition
        String plaintext = cipher.columnarTranspositionDecipher(intermediateText, key1);
        return plaintext; // Return the decrypted text
    }
}