/***
 * @author kennyfan382 (Aidan Hammond)
 *
 * THIS IS MY TAKE ON A CIPHER
 * IT IS A VARIANT OF AN XOR CIPHER
 * 
 * HOW IT WORKS
 * ---
 * the char indexes of the alphabet strings are matched with both the key and the message. the numbers are the XOR'ed. 
 * the output of that XOR gets converted into another number (which will be the index of the encrypted char)
 */

import java.util.*;

public class IndXorCip {
    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 .:,*;()!£$%^&-_+=/#~@'[]{}|`¬\"\"'";

    static Map<Character, Integer> charToIndex = new HashMap<>();
    static Map<Integer, Character> indexToChar = new HashMap<>();


    static { //Once class is initilaised, the HashMaps need the alphabet and indexes in them
        for (int i = 0; i < alphabet.length(); i++) {
            charToIndex.put(alphabet.charAt(i), i); //Alphabet location then Index
            indexToChar.put(i, alphabet.charAt(i)); //Index then alphabet location
        }
    }

    public static char xorChars(char c1, char c2) {
        int i1 = charToIndex.getOrDefault(c1, -1); //if c1 not in alphabet, use 0 instead
        int i2 = charToIndex.getOrDefault(c2, -1);
        int xor = (i1 ^ i2) % alphabet.length(); //The XOR of the two indexes creates one final index, modulus that with alphabet.length() to secure the range
        return indexToChar.get(xor); //Translate the new number (index) to a new letter (usi ng this hashmap)
    }

    public static String encrypt(String plain, String key) {
        StringBuilder output = new StringBuilder(); //Result of XORing the input char and the key
        for (int i = 0; i < plain.length(); i++) {
            char p = plain.charAt(i);
            char k = key.charAt(i % key.length()); //To ensure that the key won't affect the full messages encryption due to length issues
            output.append(xorChars(p, k)); //XORe'd char added to end result!
        }
        return output.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(alphabet.length());
        Scanner in = new Scanner(System.in);
        System.out.println("What is your message?");
        String plaintext = in.nextLine();
        System.out.println("What is your key?");
        String key = in.nextLine();

        String cipher = encrypt(plaintext, key);
        String decrypted = encrypt(cipher, key); // Reversible because XOR again
        decrypted.trim();

        System.out.println("Encrypted: " + cipher + "\n\n\n");
        System.out.println("Decrypted: " + decrypted);
    }
}
