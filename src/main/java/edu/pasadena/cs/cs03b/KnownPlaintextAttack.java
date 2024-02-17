package edu.pasadena.cs.cs03b;

public class KnownPlaintextAttack {
    private Cipher cipher;

    public KnownPlaintextAttack(Cipher cipher) {
        this.cipher = cipher;
    }

    // Method to perform the known-plaintext attack
    public void attack(String plaintext, String ciphertext, int keyLength) {
        System.out.println("Starting Known-plaintext attack...");

        // Capture the start time of the attack
        long startTime = System.currentTimeMillis();

        // Generate a sorted key string of the given length
        StringBuilder key = new StringBuilder(keyLength);
        for (int i = 0; i < keyLength; i++) {
            key.append((char) ('0' + i % 10)); // Use digits 0-9 for simplicity
        }

        System.out.println("Generating and testing keys...");

        // Generate all permutations of the key and test each one
        generatePermutations(key.toString(), "", plaintext, ciphertext);

        // Capture the end time of the attack
        long endTime = System.currentTimeMillis();

        // Calculate the duration of the attack
        long duration = endTime - startTime;

        // Print the duration of the attack
        System.out.println("Finished Known-plaintext attack. Time taken: " + duration + " milliseconds");
    }

    // Recursive method to generate all permutations of a string
    private void generatePermutations(String str, String prefix, String plaintext, String ciphertext) {
        if (str.length() == 0) {
            // Test the generated key by decrypting the ciphertext and comparing it to the
            // plaintext
            String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, prefix);
            if (decryptedText.equals(plaintext)) {
                // If the decrypted text matches the plaintext, print the key
                System.out.println("Key found: " + prefix);
            }
        } else {
            // For each character in the string, generate all permutations of the remaining
            // characters
            for (int i = 0; i < str.length(); i++) {
                generatePermutations(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i), plaintext,
                        ciphertext);
            }
        }
    }
}