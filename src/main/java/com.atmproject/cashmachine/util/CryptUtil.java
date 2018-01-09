package com.atmproject.cashmachine.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */

/**
 *
 * Classe responsável pela criptografia da senha.
 *
 **/
public class CryptUtil {

    public static final String encodePassword(String password) {
        MessageDigest algorithm = null;
        byte messageDigest[] = new byte[0];
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
            messageDigest = algorithm.digest(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
