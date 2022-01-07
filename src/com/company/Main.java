package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        double[] a1 = {1, 2, 3};
        double[] a2 = a1.clone();
        a2[0] = -1;
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
    }
}
