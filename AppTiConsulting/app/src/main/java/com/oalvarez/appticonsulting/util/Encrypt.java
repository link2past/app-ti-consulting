package com.oalvarez.appticonsulting.util;

/**
 * Created by oalvarez on 14/12/2016.
 */

import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {

    private final String characterEncoding = "UTF-8";
    private final String cipherTransformation="AES/CBC/PKCS5Padding";
    private final String aesEncryptionAlgorithm = "AES";

    @Nullable
    private byte[] decrypt(byte[] cipherText, byte[] key, byte[] initialVector){
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            cipherText = cipher.doFinal(cipherText);
            return cipherText;
        }
        catch (NoSuchAlgorithmException e){
            Log.e("TAG", "No such algorithm " + cipherTransformation, e);
        }catch (NoSuchPaddingException e) {
            Log.e("TAG", "No such padding PKCS5", e);
        }catch (InvalidKeyException e) {
            Log.e("TAG", "Invalid Key Exception", e);
        }catch (InvalidAlgorithmParameterException e) {
            Log.e("TAG", "InvalidAlgorithmParameterException", e);
        }catch (IllegalBlockSizeException e) {
            Log.e("TAG", "IllegalBlockSizeException", e);
        }
        catch (BadPaddingException e) {
            Log.e("TAG", "BadPaddingException", e);
        }
        return null;
    }

    @Nullable
    private byte[] encrypt(byte[] plainText, byte[] key, byte[] initialVector){
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            plainText = cipher.doFinal(plainText);
            return plainText;
        }catch (NoSuchAlgorithmException e){
            Log.e("TAG", "No such algorithm " + cipherTransformation, e);
        }catch (NoSuchPaddingException e) {
            Log.e("TAG", "No such padding PKCS5", e);
        }catch (InvalidKeyException e) {
            Log.e("TAG", "Invalid Key Exception", e);
        }catch (InvalidAlgorithmParameterException e) {
            Log.e("TAG", "InvalidAlgorithmParameterException", e);
        }catch (IllegalBlockSizeException e) {
            Log.e("TAG", "IllegalBlockSizeException", e);
        }
        catch (BadPaddingException e) {
            Log.e("TAG", "BadPaddingException", e);
        }

        return null;
    }

    @Nullable
    private byte[] getKeyBytes(String key){
        try{
            byte[] keyBytes = new byte[16];
            byte[] parameterKeyBytes = key.getBytes(characterEncoding);
            System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
            return keyBytes;
        }
        catch (UnsupportedEncodingException e){
            Log.e("TAG", "UnsupportedEncodingException", e);
        }

        return null;
    }

    public String encrypt(String plainText, String key) {
        try {

            byte[] plainTextBytes = plainText.getBytes(characterEncoding);
            byte[] keyBytes = getKeyBytes(key);
            return Base64.encodeToString(encrypt(plainTextBytes, keyBytes, keyBytes), Base64.DEFAULT);

        } catch (UnsupportedEncodingException e) {
            Log.e("TAG", "UnsupportedEncodingException", e);
        }
        return null;
    }

    public String decrypt(String encryptedText, String key){
        try{
            byte[] cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT);
            byte[] keyBytes = getKeyBytes(key);
            return new String(decrypt(cipheredBytes, keyBytes, keyBytes), characterEncoding);
        }catch (UnsupportedEncodingException e) {
            Log.e("TAG", "UnsupportedEncodingException", e);
        }
        return null;
    }
}
