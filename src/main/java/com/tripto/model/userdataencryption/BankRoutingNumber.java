package com.tripto.model.userdataencryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class BankRoutingNumber {
  private static final int keyLength = 128;
  private static final int iterationCount = 40000;
  private static final byte[] salt = new String("45368564").getBytes();

  private String encryptedBankRoutingNumber;
  private SecretKeySpec key;

  public BankRoutingNumber() {}

  public BankRoutingNumber(String bankRoutingNumber) throws Exception {
    key = createSecretKey(bankRoutingNumber.toCharArray());
    encryptedBankRoutingNumber = encrypt(bankRoutingNumber, key);
  }

  public void setBankAccountNumber(String bankRoutingNumber) throws Exception {
    key = createSecretKey(bankRoutingNumber.toCharArray());
    encryptedBankRoutingNumber = encrypt(bankRoutingNumber, key);
  }

  private SecretKeySpec createSecretKey(char[] unencryptedBankRoutingNumber)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
    PBEKeySpec keySpec =
        new PBEKeySpec(unencryptedBankRoutingNumber, salt, iterationCount, keyLength);
    SecretKey keyTmp = keyFactory.generateSecret(keySpec);
    return new SecretKeySpec(keyTmp.getEncoded(), "AES");
  }

  private String encrypt(String property, SecretKeySpec key)
      throws GeneralSecurityException, UnsupportedEncodingException {
    Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    pbeCipher.init(Cipher.ENCRYPT_MODE, key);
    AlgorithmParameters parameters = pbeCipher.getParameters();
    IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
    byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
    byte[] iv = ivParameterSpec.getIV();
    return base64Encode(iv) + ":" + base64Encode(cryptoText);
  }

  private String base64Encode(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }

  private String decrypt(String string, SecretKeySpec key)
      throws GeneralSecurityException, IOException {
    String iv = string.split(":")[0];
    String property = string.split(":")[1];
    Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
    return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
  }

  private static byte[] base64Decode(String property) throws IOException {
    return Base64.getDecoder().decode(property);
  }
}
