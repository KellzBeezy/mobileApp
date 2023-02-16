package com.example.kellz;


import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Passecurity {
    public static String password;
    static String saltValue;
    static String encryptedPassword;

    public static void pass(){

//        password = "Kelvin1234";


        saltValue = "UM8oIsOFraM8MiAjQecW";
//                PassBaseEncrypter.getSaltValue(20);

        encryptedPassword = "LeB/mNKnvHz2OKeRW8YQFbY2pwQxQ3bcEkChygYwLFQ=";
//                PassBaseEncrypter.generateSecurePassword(password,saltValue);

        System.out.println("Plain password: "+password);
        System.out.println("Salt value: "+saltValue);
        System.out.println("Encrypted password: "+encryptedPassword);

        Boolean status = PassBaseEncrypter.verifyUserPassword(password,encryptedPassword,saltValue);

        if(status){
            System.out.println("The password matched.");
        }
        else{
            System.out.println("The password do not match.");
        }
    }
}
class PassBaseEncrypter{

    private static final Random random =  new SecureRandom();
    private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int iterations = 10000;
    private static final int keyLength = 256;

    public static String getSaltValue(int length){
        StringBuilder finalVal = new StringBuilder(length);

        for(int i = 0;i < length; i++){
            finalVal.append(characters.charAt(random.nextInt(characters.length())));

        }
        return new String(finalVal);
    }

    public static byte[] hash(char[] password,byte[] salt){
        PBEKeySpec spec = new PBEKeySpec(password,salt,iterations,keyLength);
        Arrays.fill(password,Character.MIN_VALUE);

        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new AssertionError("Error while hashing a password : "+e.getMessage(),e);
        }
        finally {
            spec.clearPassword();
        }
    }

    public static String generateSecurePassword(String password,String salt){
        String finalVal =  null;

        byte[] securePassword = hash(password.toCharArray(),salt.getBytes());

        finalVal = Base64.getEncoder().encodeToString(securePassword);

        return finalVal;
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt){

        boolean finalValue = false;

        //Generate a new secure password with the same salt
        String newSecuredPassword =  generateSecurePassword(providedPassword,salt);

        //Verify if the passwords matches
        finalValue = newSecuredPassword.equalsIgnoreCase(securedPassword);

        return finalValue;

    }

}
