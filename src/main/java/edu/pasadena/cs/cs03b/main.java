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
		List<Character> digits = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			digits.add((char) (i + '0'));
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			digits.add(c);
		}
		for (char c = 'a'; c <= 'z'; c++) {
			digits.add(c);
		}

		Collections.shuffle(digits);

		String key1 = "";
		String key2 = "";

		if (keyLength1 > digits.size() || keyLength2 > digits.size()) {
			throw new IllegalArgumentException("Key length can't be more than " + digits.size());
		}

		for (int i = 0; i < keyLength1; i++) {
			key1 += digits.get(i);
		}

		for (int i = 0; i < keyLength2; i++) {
			key2 += digits.get(i + keyLength1);
		}

		// Print the keys for testing
		System.out.println("Key 1: " + key1);
		System.out.println("Key 2: " + key2);

		Cipher cipher = new Cipher();

		int[] arrangedKey1 = cipher.arrangeKey(key1); // Call the method on the instance
		System.out.println("Arranged Key 1: " + Arrays.toString(arrangedKey1));

		int[] arrangedKey2 = cipher.arrangeKey(key2); // Call the method on the instance
		System.out.println("Arranged Key 2: " + Arrays.toString(arrangedKey2));

		String ciphertext = cipher.columnarTranspositionCipher(plaintext, key1);
		System.out.println(" \n The encrypted message is: " + ciphertext + "\n");

		DoubleCipher doubleCipher = new DoubleCipher(cipher);
		String doubleCiphertext = doubleCipher.doubleTranspositionCipher(plaintext, key1, key2);
		System.out.println(" \n The double encrypted message is: " + doubleCiphertext + "\n");

		String decryptedText = cipher.columnarTranspositionDecipher(ciphertext, key1);
		System.out.println(" \n The decrypted message is: " + decryptedText + "\n");

		String doubleDecryptedText = doubleCipher.doubleTranspositionDecipher(doubleCiphertext, key1, key2);
		System.out.println(" \n The double decrypted message is: " + doubleDecryptedText + "\n");

		BruteForceAttack bruteForceAttack = new BruteForceAttack(cipher);
		bruteForceAttack.bruteForceAttack(plaintext, ciphertext, keyLength1);

		KnownPlaintextAttack knownPlaintextAttack = new KnownPlaintextAttack(cipher);
		knownPlaintextAttack.attack(plaintext, ciphertext, keyLength1);
	}
}