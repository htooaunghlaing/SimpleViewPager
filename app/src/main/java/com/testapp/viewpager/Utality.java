package com.testapp.viewpager;

import java.util.Random;

public class Utality {

    private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String getRandomAlphabet() {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rd = new Random();
        char letter = abc.charAt(rd.nextInt(abc.length()));
        return String.valueOf(letter);
    }

    public static String getRandomColor(){
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);

        String colorCode = String.format("#%06x", rand_num);
        return colorCode;
    }
}
