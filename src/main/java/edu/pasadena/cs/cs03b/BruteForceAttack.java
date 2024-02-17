package edu.pasadena.cs.cs03b;

public class BruteForceAttack {
    private Cipher cipher;

    public BruteForceAttack(Cipher cipher) {
        this.cipher = cipher;
    }

    // Method to perform the brute force attack
    public void bruteForceAttack(String plaintext, String ciphertext, int keyLength1) {
        // Calculate the maximum number of attempts based on the key length
        int maxAttempts1 = (int) Math.pow(10, keyLength1);
        int attempts = 0;

        System.out.println("Brute force attack working...");

        // Capture the start time of the attack
        long startTime = System.currentTimeMillis();

        // Loop over all possible keys
        for (int i = 0; i < maxAttempts1; i++) {
            // Generate the current key
            String key1 = String.format("%0" + keyLength1 + "d", i);

            // Try decrypting the ciphertext with the current key
            String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
            attempts++;

            // Print the current key and number of attempts at different intervals depending
            // on the key length
            if (keyLength1 == 5 && attempts % 1000 == 0) {
                System.out.println("Attempt number: " + attempts);
            } else if (keyLength1 == 6 && attempts % 10000 == 0) {
                System.out.println("Attempt number: " + attempts);
            }

            // Check if the decrypted text matches the plaintext
            if (plaintext.equals(decryptedText)) {
                // Capture the end time of the attack
                long endTime = System.currentTimeMillis();

                // Calculate the duration of the attack
                long duration = endTime - startTime;

                // Print the results of the attack
                System.out.println("Brute force has worked. Decrypted text is: " + decryptedText);
                System.out.println("Number of attempts: " + attempts);
                System.out.println("Time taken: " + duration + " milliseconds");
                return;
            }
        }

        // Print a message if no correct key was found after all attempts
        System.out.println("No correct key found after " + attempts + " attempts.");
    }
}