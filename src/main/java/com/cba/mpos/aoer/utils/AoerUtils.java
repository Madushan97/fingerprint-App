package com.cba.mpos.aoer.utils;

//import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Calendar;
import java.util.Date;


public class AoerUtils {

    public static String prepareURL(String url, String append) {
        if (url.endsWith("/")) {
            if (append.startsWith("/")) {
                return url + append.substring(1);
            } else {
                return url + append;
            }
        } else {
            if (append.startsWith("/")) {
                return url + append;
            } else {
                return url + "/" + append;
            }
        }
    }

    /*public static String getBase64Credentials(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }*/

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String[] commaSeparatedStringToArray(String commaSeparatedString) {
        return commaSeparatedString == null || commaSeparatedString.isEmpty() ? new String[]{} : commaSeparatedString.trim().split("\\s*,\\s*");
    }
}
