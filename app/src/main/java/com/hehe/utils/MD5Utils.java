package com.hehe.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class MD5Utils {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getMD5(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(Charset.defaultCharset().name());
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bytes);
                byte[] digest = instance.digest();
                int length = digest.length;
                char[] cArr = new char[length << 1];
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i + 1;
                    cArr[i] = a[(digest[i2] >>> 4) & 15];
                    i = i3 + 1;
                    cArr[i3] = a[digest[i2] & 15];
                }
                return new String(cArr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i];
            if (i2 < 0) {
                i2 += 256;
            }
            if (i2 < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i2));
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static String md5Encryption32(String str) {
        String str2 = new String();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < digest.length; i++) {
                int i2 = digest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str2;
        }
    }
}
