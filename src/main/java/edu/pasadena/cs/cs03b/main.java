package edu.pasadena.cs.cs03b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Ask the user for the path to the plaintext file
		System.out.println("Enter the path to the plaintext file:");
		String filePath = scanner.nextLine();

		// Initialize an empty string to hold the plaintext
		String plaintext = "";

		// Read the plaintext from the file
		try {
			File file = new File(filePath);
			Scanner fileScanner = new Scanner(file);
			// Loop through each line in the file
			while (fileScanner.hasNextLine()) {
				// Add each line to the plaintext string
				plaintext += fileScanner.nextLine();
			}
			// Close the file scanner after reading the file
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Ask the user for the lengths of the first and second secret keys
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
		List<Character> digits = new ArrayList<>();
		// Add digits 0-9 to the list of possible characters
		for (int i = 0; i < 10; i++) {
			digits.add((char) (i + '0'));
		}
		// Add uppercase letters A-Z to the list of possible characters
		for (char c = 'A'; c <= 'Z'; c++) {
			digits.add(c);
		}
		// Add lowercase letters a-z to the list of possible characters
		for (char c = 'a'; c <= 'z'; c++) {
			digits.add(c);
		}

		// Shuffle the list of characters to generate random keys
		Collections.shuffle(digits);

		// Initialize the keys as empty strings
		String key1 = "";
		String key2 = "";

		// Check if the key lengths are not more than the size of the list of characters
		if (keyLength1 > digits.size() || keyLength2 > digits.size()) {
			throw new IllegalArgumentException("Key length can't be more than " + digits.size());
		}

		// Generate the first key from the shuffled list of characters
		for (int i = 0; i < keyLength1; i++) {
			key1 += digits.get(i);
		}

		// Generate the second key from the shuffled list of characters
		for (int i = 0; i < keyLength2; i++) {
			key2 += digits.get(i + keyLength1);
		}

		// Print the keys for testing
		System.out.println("Key 1: " + key1);
		System.out.println("Key 2: " + key2);

		// Create a new Cipher object
		Cipher cipher = new Cipher();

		// Arrange the first key and print it for testing
		int[] arrangedKey1 = cipher.arrangeKey(key1);
		System.out.println("Arranged Key 1: " + Arrays.toString(arrangedKey1));

		// Arrange the second key and print it for testing
		int[] arrangedKey2 = cipher.arrangeKey(key2);
		System.out.println("Arranged Key 2: " + Arrays.toString(arrangedKey2));

		// Encrypt the plaintext using the first key and print the ciphertext
		String ciphertext = cipher.columnarTranspositionCipher(plaintext, key1);
		System.out.println(" \n The encrypted message is: " + ciphertext + "\n");

		// Create a new DoubleCipher object
		DoubleCipher doubleCipher = new DoubleCipher(cipher);

		// Double encrypt the plaintext using both keys and print the double ciphertext
		String doubleCiphertext = doubleCipher.doubleTranspositionCipher(plaintext, key1, key2);
		System.out.println(" \n The double encrypted message is: " + doubleCiphertext + "\n");

		// Decrypt the ciphertext using the first key and print the decrypted text
		String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
		System.out.println(" \n The decrypted message is: " + decryptedText + "\n");

		// Double decrypt the double ciphertext using both keys and print the double decrypted text
		String doubleDecryptedText = doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);
		System.out.println(" \n The double decrypted message is: " + doubleDecryptedText + "\n");

		// Create a new BruteForceAttack object and perform a brute force attack
		BruteForceAttack bruteForceAttack = new BruteForceAttack(cipher);
		bruteForceAttack.bruteForceAttack(plaintext, ciphertext, keyLength1);

		// Create a new KnownPlaintextAttack object and perform a known plaintext attack
		KnownPlaintextAttack knownPlaintextAttack = new KnownPlaintextAttack(cipher);
		knownPlaintextAttack.attack(plaintext, ciphertext, keyLength1);
	}
}