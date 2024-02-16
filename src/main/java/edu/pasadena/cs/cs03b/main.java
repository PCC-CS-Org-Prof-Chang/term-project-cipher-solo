package edu.pasadena.cs.cs03b;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the path to the plaintext file:");
		String filePath = scanner.nextLine();

		String plaintext = "";

		// Read the plaintext from the file
		try {
			File file = new File(filePath);
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				plaintext += fileScanner.nextLine();
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println("Enter the length of the first secret key (5-6):");
        int keyLength1 = scanner.nextInt();
        System.out.println("Enter the length of the second secret key (5-6):");
        int keyLength2 = scanner.nextInt();

        // Check if the key lengths are within the valid range
        if (keyLength1 < 5 || keyLength1 > 6 || keyLength2 < 5 || keyLength2 > 6) {
            System.out.println("Invalid key length. Please enter a number between 5 and 6.");
            return;
        }

        // Generate two random secret keys
        Random random = new Random();
        String key1 = "";
        String key2 = "";
        for (int i = 0; i < keyLength1; i++) {
            key1 += random.nextInt(10); // Generate a random digit from 0-9
        }
        for (int i = 0; i < keyLength2; i++) {
            key2 += random.nextInt(10); // Generate a random digit from 0-9
        }

		Cipher cipher = new Cipher();
		String ciphertext = cipher.columnarTranspositionCipher(plaintext, key1);
		System.out.println(" \n The encrypted message is: " + ciphertext + "\n");

		DoubleCipher doubleCipher = new DoubleCipher(cipher);
		String doubleCiphertext = doubleCipher.doubleTranspositionCipher(plaintext, key1, key2);
		System.out.println(" \n The double encrypted message is: " + doubleCiphertext + "\n");

		String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
		System.out.println(" \n The decrypted message is: " + decryptedText + "\n");

		String doubleDecryptedText = doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);
		System.out.println(" \n The double decrypted message is: " + doubleDecryptedText + "\n");

        BruteForceAttack bruteForceAttack = new BruteForceAttack(cipher, doubleCipher);
        bruteForceAttack.bruteForceAttack(plaintext, ciphertext, keyLength1);
	}
}