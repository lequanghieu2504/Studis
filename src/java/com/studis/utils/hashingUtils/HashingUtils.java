/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.utils.hashingUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
/**
 *
 * @author ho huy
 */
public class HashingUtils {

    // The number of iterations for the PBKDF2 hashing algorithm to increase security
    private static final int INTERATIONS = 65536;

    // The length of the hash key (in bits)
    private static final int KEY_LENGTH = 256;

    // The algorithm used for hashing passwords
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * Generates a random salt to be used in password hashing.
     * The salt is a random string that ensures the uniqueness of the hash.
     * 
     * @return A Base64-encoded string representing the generated salt.
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom(); // Secure random number generator
        byte[] salt = new byte[16];  // Create a byte array of 16 bytes for the salt
        random.nextBytes(salt);  // Fill the byte array with random values
        return Base64.getEncoder().encodeToString(salt);  // Return the salt as a Base64-encoded string
    }

    /**
     * Hashes a password using the PBKDF2 algorithm with the given salt.
     * The password and salt are combined to create a unique hash.
     * 
     * @param password The plain text password to be hashed.
     * @param salt The salt to be used in the hashing process.
     * @return A Base64-encoded string representing the hashed password.
     */
    public static String hashPassword(String password, String salt) {
        try {
            byte[] saltByte = Base64.getDecoder().decode(salt);  // Decode the salt from Base64 to bytes

            PBEKeySpec spec = new PBEKeySpec(  // Create a PBEKeySpec with the password, salt, iterations, and key length
                    password.toCharArray(),  // Convert password to char array
                    saltByte,  // Salt in byte array
                    INTERATIONS,  // Number of iterations
                    KEY_LENGTH  // Length of the generated key in bits
            );

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  // Get the key factory for PBKDF2WithHmacSHA256
            byte[] hashedBytes = keyFactory.generateSecret(spec).getEncoded();  // Generate the hash

            return Base64.getEncoder().encodeToString(hashedBytes);  // Return the hash as a Base64-encoded string

        } catch (Exception e) {
            // If any exception occurs, throw a runtime exception with the error message
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Verifies a password by comparing the hashed password with the new hash generated from the provided password and salt.
     * 
     * @param password The plain text password to verify.
     * @param salt The salt used for hashing.
     * @param hashedPassword The stored hashed password to compare with.
     * @return True if the password matches the stored hash, false otherwise.
     */
    public static boolean verifyPassword(String password, String salt, String hashedPassword) {
        // Generate a new hash from the provided password and salt
        String newHash = hashPassword(password, salt);
        
        // Compare the newly generated hash with the stored hashed password
        return newHash.equals(hashedPassword);
    }
}
