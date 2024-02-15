package edu.pasadena.cs.cs03b;

public class BruteForceAttack {
    private Cipher cipher;
    private DoubleCipher doubleCipher;

    public BruteForceAttack(Cipher cipher, DoubleCipher doubleCipher) {
        this.cipher = cipher;
        this.doubleCipher = doubleCipher;
    }

    public void bruteForceAttack(String plaintext, String ciphertext, int keyLength1) {
        int maxAttempts1 = (int) Math.pow(10, keyLength1);
        int attempts = 0;
        for (int i = 0; i < maxAttempts1; i++) {
            String key1 = String.format("%0" + keyLength1 + "d", i);
    
            // Try decrypting the ciphertext with the current key
            String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
            attempts++;
    
            // Print the current key and number of attempts at different intervals depending on the key length
            if (keyLength1 == 5 && attempts % 1000 == 0) {
                System.out.println("Attempt number: " + attempts);
            } else if (keyLength1 == 6 && attempts % 10000 == 0) {
                System.out.println("Attempt number: " + attempts);
            }
    
            // Check if the decrypted text matches the plaintext
            if (plaintext.equals(decryptedText)) {
                System.out.println("The correct key1 is: " + key1);
                System.out.println("Number of attempts: " + attempts);
                return;
            }
        }
    
        System.out.println("No correct key found after " + attempts + " attempts.");
    }

    // public void bruteForceAttack(String plaintext, String ciphertext, String
    // doubleCiphertext, int keyLength1, int keyLength2) {
    // int maxAttempts1 = (int) Math.pow(10, keyLength1);
    // int maxAttempts2 = (int) Math.pow(10, keyLength2);
    // int attempts = 0;
    // for (int i = 0; i < maxAttempts1; i++) {
    // String key1 = String.format("%0" + keyLength1 + "d", i);
    // for (int j = 0; j < maxAttempts2; j++) {
    // String key2 = String.format("%0" + keyLength2 + "d", j);

    // // Try decrypting the ciphertext with the current keys
    // String decryptedText = cipher.columnarTranspositionDecipher(ciphertext,
    // key1);
    // String doubleDecryptedText =
    // doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);

    // attempts++;

    // // Print the number of attempts every 10000 tries to show progress
    // if (attempts % 10000 == 0) {
    // System.out.println("Attempt number: " + attempts);
    // }

    // // Check if the decrypted text matches the plaintext
    // if (plaintext.equals(decryptedText) && plaintext.equals(doubleDecryptedText))
    // {
    // System.out.println("The correct key1 is: " + key1);
    // System.out.println("The correct key2 is: " + key2);
    // System.out.println("Number of attempts: " + attempts);
    // return;
    // } else {
    // // Debugging information
    // System.out.println("Decrypted text: '" + decryptedText + "', length: " +
    // decryptedText.length());
    // System.out.println("Double decrypted text: '" + doubleDecryptedText + "',
    // length: " + doubleDecryptedText.length());
    // System.out.println("Plaintext: '" + plaintext + "', length: " +
    // plaintext.length());
    // }

    // // Early stopping: if the decrypted text doesn't make sense, stop the inner
    // loop
    // if (!decryptedText.matches("[a-zA-Z0-9 .,!?]*") ||
    // !doubleDecryptedText.matches("[a-zA-Z0-9 .,!?]*")) {
    // break;
    // }
    // }
    // }

    // System.out.println("No correct key found after " + attempts + " attempts.");
    // }
}