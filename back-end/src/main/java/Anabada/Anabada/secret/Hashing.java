package Anabada.Anabada.secret;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static final String SALT="!@salt$%^&";

    public static String hashingPassword(String input){
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] hashData=md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger num=new BigInteger(1,hashData);
            StringBuilder hex=new StringBuilder(num.toString(16));
            while (hex.length()<32){
                hex.insert(0,'0');
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            return input;
        }
    }
}
