package ru.job4j.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    private static final Logger LOG = LoggerFactory.getLogger(Security.class.getName());

    private static String hexString(byte[] in) {
        StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%2X", b));
        }
        return builder.toString().replaceAll(" ", "0");
    }

    public static String getSHA1(String msg) {
        String sha1 = null;
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-1");
            msgDigest.update(msg.getBytes(), 0, msg.length());
            sha1 = hexString(msgDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Ошибка получения хэша SHA1", e);
        }
        return sha1;
    }
}
