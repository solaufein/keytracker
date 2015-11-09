package com.keytracker.utils;

public class LoggingUtils {
    public static void logChar(Character c) {
        System.out.print(c);
    }

    public static void logKeyFrom(int keyCode, boolean isLowerCase) {
        if (isLowerCase) {
            System.out.print(Character.toString((char) keyCode).toLowerCase());
        } else {
            System.out.print(Character.toString((char) keyCode));
        }
    }

    public static void printAsciFrom(String test) {
        for (int i = 0; i < test.length(); ++i) {

            char c = test.charAt(i);
            int j = (int) c;
            System.out.println(c + " = " + j);
        }
        System.out.println("");
    }

    public static String character(int charCode) {
        return Character.toString((char) charCode);
    }
}
