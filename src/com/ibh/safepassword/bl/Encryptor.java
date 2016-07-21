/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.bl;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ihorvath
 * http://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
 */
public class Encryptor {

//  public static String encrypt(String key, String initVector, String value) {
  public static String encrypt(byte[] key, byte[] initVector, byte[] value) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector);
      SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

      byte[] encrypted = cipher.doFinal(value);
      System.out.println("encrypted string: " + DatatypeConverter.printBase64Binary(encrypted));

      return DatatypeConverter.printBase64Binary(encrypted);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

//  public static String decrypt(String key, String initVector, String encrypted) {
  public static String decrypt(byte[] key, byte[] initVector, String encrypted) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector);
      SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

      byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

      return new String(original);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }
  
  public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

//        System.out.println(decrypt(key, initVector, encrypt(key, initVector, "Hello World")));
    }
}
