package br.feevale.helper;

public class Formatter {

    public static String paddingLeft(int input, int padLenght, String padString) {
        return String.format("%" + padString + padLenght + "d", input);
    }
}
