package com.lingzhizhuxuanlv.springboot.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_SIGN = "MD5withRSA";
    private static String publicKey = null;
    private static String privateKey = null;

    public void setPublicKey(String pub) {
        publicKey = pub;
    }
    public void setPrivateKey(String pri) {
        privateKey = pri;
    }

    public static void main(String[] args) throws Exception {
        // 生成公钥和私钥
        generatorKeyPair();
        String source = "我是程序猿！";
        System.out.println("加密前的数据：\r\n" + source);
        System.out.println("--------------------------公钥加密，私钥解密------------------------------");
        // 公钥加密
        String target = encryptionByPublicKey(source);
        // 私钥解密
        decryptionByPrivateKey(target);
        System.out.println("--------------------------私钥加密并且签名，公钥验证签名并且解密------------------------------");
        // 私钥加密
        target = encryptionByPrivateKey(source);
        // 签名
        String sign = signByPrivateKey(target);
        // 验证签名
        verifyByPublicKey(target, sign);
        // 公钥解密
        decryptionByPublicKey(target);

    }

    /**
     * 生成密钥对
     */
    public static void generatorKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        byte[] keyBs = rsaPublicKey.getEncoded();
        publicKey = encodeBase64(keyBs);
        System.out.println("生成的公钥：\r\n" + publicKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyBs = rsaPrivateKey.getEncoded();
        privateKey = encodeBase64(keyBs);
        System.out.println("生成的私钥：\r\n" + privateKey);
    }

    /**
     * 获取公钥
     */
    public static PublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * 获取私钥
     */
    public static PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    /**
     * 公钥加密
     */
    public static String encryptionByPublicKey(String source) throws Exception {
        PublicKey publicKey = getPublicKey();
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipher.update(source.getBytes("UTF-8"));
        String target = encodeBase64(cipher.doFinal());
        System.out.println("公钥加密后的数据：\r\n" + target);
        return target;
    }

    /**
     * 公钥解密
     */
    public static void decryptionByPublicKey(String target) throws Exception {
        PublicKey publicKey = getPublicKey();
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        cipher.update(decodeBase64(target));
        String source = new String(cipher.doFinal(), "UTF-8");
        System.out.println("公钥解密后的数据：\r\n" + source);
    }

    /**
     * 公钥验证签名
     */
    public static void verifyByPublicKey(String target, String sign) throws Exception {
        PublicKey publicKey = getPublicKey();
        Signature signature = Signature.getInstance(ALGORITHM_SIGN);
        signature.initVerify(publicKey);
        signature.update(target.getBytes("UTF-8"));
        if (signature.verify(decodeBase64(sign))) {
            System.out.println("签名正确！");
        } else {
            System.out.println("签名错误！");
        }
    }

    /**
     * 私钥加密
     */
    public static String encryptionByPrivateKey(String source) {
        try{
            PrivateKey privateKey = getPrivateKey();
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            cipher.update(source.getBytes("UTF-8"));
            String target = encodeBase64(cipher.doFinal());
            System.out.println("私钥加密后的数据：\r\n" + target);
            return target;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 私钥解密
     */
    public static void decryptionByPrivateKey(String target) throws Exception {
        PrivateKey privateKey = getPrivateKey();
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        cipher.update(decodeBase64(target));
        String source = new String(cipher.doFinal(), "UTF-8");
        System.out.println("私钥解密后的数据：\r\n" + source);
    }

    /**
     * 私钥签名
     */
    public static String signByPrivateKey(String target) {
        try{
            PrivateKey privateKey = getPrivateKey();
            Signature signature = Signature.getInstance(ALGORITHM_SIGN);
            signature.initSign(privateKey);
            signature.update(target.getBytes("UTF-8"));
            String sign = encodeBase64(signature.sign());
            System.out.println("生成的签名：\r\n" + sign);
            return sign;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * base64编码
     */
    public static String encodeBase64(byte[] source) throws Exception {
        return new String(Base64.encodeBase64(source), "UTF-8");
    }

    /**
     * Base64解码
     */
    public static byte[] decodeBase64(String target) throws Exception {
        return Base64.decodeBase64(target.getBytes("UTF-8"));
    }

}
