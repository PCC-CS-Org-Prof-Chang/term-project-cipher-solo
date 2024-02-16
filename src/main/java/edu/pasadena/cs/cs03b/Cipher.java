package edu.pasadena.cs.cs03b;

import java.util.Arrays;

public class Cipher {
    // Method to encrypt plaintext using columnar transposition cipher
    public String columnarTranspositionCipher(String plaintext, String key) {
        // Arrange the key
        int[] arrange = arrangeKey(key);

        // Calculate the number of rows for the grid
        int len = plaintext.length();
        int row = (int) Math.ceil((double) len / key.length());

        // Create the grid
        char[][] grid = new char[row][key.length()];
        int z = 0;
        // Fill the grid with characters from the plaintext
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (len == z) {
                    // If at the end of text, fill with '*'
                    grid[x][y] = '*';
                } else {
                    grid[x][y] = plaintext.charAt(z);
                    z++;
                }
            }
        }

        // Generate the ciphertext from the grid
        String ciphertext = "";
        for (int x = 0; x < key.length(); x++) {
            for (int y = 0; y < row; y++) {
                ciphertext += grid[y][arrange[x]];
            }
        }

        return ciphertext;
    }

    // Method to arrange the key
    private int[] arrangeKey(String key) {
        // Split the key into an array and sort it
        String[] keys = key.split("");
        Arrays.sort(keys);
        int[] num = new int[key.length()];
        boolean[] assigned = new boolean[key.length()]; // Array to keep track of assigned positions

        // Map each character in the original key to its position in the sorted key
        for (int x = 0; x < keys.length; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (keys[x].equals(key.charAt(y) + "") && !assigned[y]) {
                    num[y] = x;
                    assigned[y] = true; // Mark this position as assigned
                    break;
                }
            }
        }

        return num;
    }

    // Method to decrypt ciphertext using columnar transposition cipher
    public String columnarTranspositionDecipher(String ciphertext, String key) {
        // Arrange the key
        int[] arrange = arrangeKey(key);

        // Calculate the number of columns for the grid
        int len = ciphertext.length();
        int col = (int) Math.ceil((double) len / key.length());
        int row = key.length();
        int filler = col * row - len; // Calculate the number of filler characters

        // Create the grid
        char[][] grid = new char[row][col];
        int z = 0;
        // Fill the grid with characters from the ciphertext
        for (int x = 0; x < key.length(); x++) {
            for (int y = 0; y < col; y++) {
                // Check if we're in a filler position
                if (y == col - 1 && arrange[x] >= row - filler) {
                    grid[arrange[x]][y] = '*';
                } else {
                    grid[arrange[x]][y] = ciphertext.charAt(z);
                    z++;
                }
            }
        }

        // Generate the plaintext from the grid
        String plaintext = "";
        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                if (grid[y][x] != '*') {
                    plaintext += grid[y][x];
                }
            }
        }

        return plaintext;
    }

}
