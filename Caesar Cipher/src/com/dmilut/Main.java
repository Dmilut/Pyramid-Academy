package com.dmilut;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    private static final char[] ALPHABET = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final Path PATH_TO_ENCRYPTED  = Paths.get("Caesar Cipher/src/resources/encrypted.txt");
    private static final Path PATH_TO_DECRYPTED = Paths.get("Caesar Cipher/src/resources/decrypted.txt");

    public static void main(String[] args) throws IOException {
        String sourceMessage = "Hello World!";
        int testKey = 5;

        Util.writeFile(PATH_TO_ENCRYPTED, encrypt(sourceMessage, testKey), testKey);

        ArrayList<String> encryptedContent = Util.readFile(PATH_TO_ENCRYPTED);

        int keyFromFile = Integer.parseInt(encryptedContent.get(0));
        String encryptedMessage = encryptedContent.get(1);
        String decryptedMessage = decrypt(encryptedMessage, keyFromFile);

        Util.writeFile(PATH_TO_DECRYPTED,decryptedMessage, keyFromFile);

        System.out.println("source message = " + sourceMessage);
        System.out.println("encrypted message = " + encryptedMessage);
        System.out.println("decrypted message = " + decryptedMessage);
    }

    private static String encrypt(String message, int key) {
        StringBuilder result = new StringBuilder();

        for (Character character : message.toCharArray()) {
            if (Character.isLowerCase(character)) {
                result.append(getShiftedValueForEncrypt(character, key));
            } else {
                result.append(Character.toUpperCase(getShiftedValueForEncrypt(Character.toLowerCase(character), key)));
            }
        }

        return result.toString();
    }

    private static String decrypt(String message, int key) {
        StringBuilder result = new StringBuilder();

        for (Character character : message.toCharArray()) {
            if (Character.isLowerCase(character)) {
                result.append(getShiftedValueForDecrypt(character, key));
            } else {
                result.append(Character.toUpperCase(getShiftedValueForDecrypt(Character.toLowerCase(character), key)));
            }
        }

        return result.toString();
    }

    private static Character getShiftedValueForDecrypt(Character character, int key) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (character.equals(ALPHABET[i])) {
                if (i - key >= 0 && i - key <= ALPHABET.length - 1) {
                    return ALPHABET[i - key];
                } else {
                    return ALPHABET[ALPHABET.length + (i - key)];
                }
            }
        }

        return character;
    }

    private static Character getShiftedValueForEncrypt(Character character, int key) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (character.equals(ALPHABET[i])) {
                if (i + key <= ALPHABET.length - 1) {
                    return ALPHABET[i + key];
                } else {
                    return ALPHABET[(i + key) - ALPHABET.length];
                }
            }
        }

        return character;
    }

    private static boolean isValidKey(int key) {
        return key > 0 && key < ALPHABET.length - 1;
    }

}
