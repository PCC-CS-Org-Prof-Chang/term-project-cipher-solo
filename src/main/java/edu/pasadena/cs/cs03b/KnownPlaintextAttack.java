package edu.pasadena.cs.cs03b;

import java.util.ArrayList;
import java.util.List;

public class KnownPlaintextAttack {
    private Cipher cipher;

    public KnownPlaintextAttack(Cipher cipher) {
        this.cipher = cipher;
    }

    public void attack(String plaintext, String ciphertext, int keyLength) {
        System.out.println("Starting Known-plaintext attack...");

        // Generate a sorted key string of the given length
        StringBuilder key = new StringBuilder(keyLength);
        for (int i = 0; i < keyLength; i++) {
            key.append((char) ('0' + i % 10)); // Use digits 0-9 for simplicity
        }

        System.out.println("Generating and testing keys...");

        generatePermutations(key.toString(), "", plaintext, ciphertext);

        System.out.println("Finished Known-plaintext attack.");
    }

    private void generatePermutations(String str, String prefix, String plaintext, String ciphertext) {
        if (str.length() == 0) {
            // Test the generated key
            String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, prefix);
            if (decryptedText.equals(plaintext)) {
                System.out.println("Key found: " + prefix);
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                generatePermutations(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i), plaintext,
                        ciphertext);
            }
        }
    }
}