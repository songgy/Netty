package com.yizhong.push.util;

import android.util.Log;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @描述 : 加密解密工具类
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午4:32:37
 */
public class SecurityUtil {

    private static final String DES = "DES";

    public static void main(String[] args) {
        System.out.println(md5("Higo-Push"));
    }

	/*
     * #############不可逆算法  MD5#############
	 */

    /**
     * @param target
     * @return
     * @描述 : 32位小写MD5
     * @创建者：yuanwei
     * @创建时间： 2018年3月26日下午4:35:18
     * @用于：
     */
    public static String md5(String target) {
        if (!StringUtils.isBlank(target)) {
            String md5 = DigestUtils.md5Hex(target);
            return md5;
        } else {
            return "";
        }
    }
    
    /*
     * #############不可逆算法  SHA1#############
	 */

    public static String shaHex(String target) {
        if (!StringUtils.isBlank(target)) {
            String sha = DigestUtils.shaHex(target);
            return sha;
        } else {
            return "";
        }
    }

    public static String sha256Hex(String target) {
        if (!StringUtils.isBlank(target)) {
            String sha = DigestUtils.sha256Hex(target);
            return sha;
        } else {
            return "";
        }
    }

    public static String sha384Hex(String target) {
        if (!StringUtils.isBlank(target)) {
            String sha = DigestUtils.sha384Hex(target);
            return sha;
        } else {
            return "";
        }
    }

    public static String sha512Hex(String target) {
        if (!StringUtils.isBlank(target)) {
            String sha = DigestUtils.sha512Hex(target);
            return sha;
        } else {
            return "";
        }
    }
    
    /*
	 * #############可逆算法  BASE64#############
	 */

    /**
     * @param target
     * @return
     * @描述 : 加密
     * @创建者：yuanwei
     * @创建时间： 2018年3月26日下午4:44:41
     * @用于：
     */
    public static String encodeBase64(String target) {
        //加密
        byte[] b = Base64.encodeBase64(target.getBytes());
        String base64 = new String(b);
        return base64;
    }

    /**
     * @param
     * @return
     * @描述 : 解密
     * @创建者：yuanwei
     * @创建时间： 2018年3月26日下午4:45:53
     * @用于：
     */
    public static String decodeBase64(String base64) {
        //解密
        byte[] b = Base64.decodeBase64(base64);
        String target = new String(b);
        return target;
    }
    
    /*
   	 * #############可逆算法  DES#############
   	 */

    /***
     *
     * @描述 : DES数据解密
     * @创建者：yuanwei
     * @创建时间： 2015年9月14日下午5:13:37
     *
     * @param data 需解密的数据
     * @param key 密钥必须大于等于8
     * @return
     * @throws RuntimeException
     */
    public static String decrypt(String data, String key) throws RuntimeException {
        try {
            if ((StringUtils.isNotBlank(data)) && (StringUtils.isNotBlank(key))) {
                return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
            }
            return null;
        } catch (InvalidKeyException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        }
    }

    /***
     *
     * @描述 : DES数据加密
     * @创建者：yuanwei
     * @创建时间： 2015年9月14日下午5:14:14
     *
     * @param data 需加密的数据
     * @param key 密钥必须大于等于8
     * @return
     * @throws RuntimeException
     */
    public static String encrypt(String data, String key) throws RuntimeException {
        try {
            if ((StringUtils.isNotBlank(data)) && (StringUtils.isNotBlank(key))) {
                return byte2hex(encrypt(data.getBytes(), key.getBytes()));
            }
            return null;
        } catch (InvalidKeyException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            Log.e(e.getMessage(), e.toString());
            throw new RuntimeException(e);
        }
    }

    /***
     *
     * @描述 : 加密
     * @创建者：yuanwei
     * @创建时间： 2015年9月14日下午5:10:59
     *
     * @param src 数据源
     * @param key 密钥 长度必须为8的倍数
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static byte[] encrypt(byte[] src, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /***
     *
     * @描述 : 解密
     * @创建者：yuanwei
     * @创建时间： 2015年9月14日下午5:07:42
     *
     * @param src 数据源
     * @param key 密钥 长度必须为8的倍数
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static byte[] decrypt(byte[] src, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(src);
    }

    /***
     *
     * @描述 : 二行制转字符串
     * @创建者：yuanwei
     * @创建时间： 2015年9月14日下午5:06:54
     *
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        for (int n = 0; (b != null) && (n < b.length); n++) {
            String stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /***
     *
     * @描述 : 字符串转二行制
     * @创建者：yuanwei
     * @创建时间： 2015年9月14日下午5:06:20
     *
     * @param b
     * @return
     */
    private static byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[(n / 2)] = ((byte) Integer.parseInt(item, 16));
        }
        return b2;
    }
}