package br.feevale.helper;

import java.nio.ByteBuffer;

public class Charset {

    public static String toIso88591(String value){
        java.nio.charset.Charset iso88591Charset = java.nio.charset.Charset.forName("ISO-8859-1");

        ByteBuffer outputBuffer = iso88591Charset.encode(value);
        byte[] outputData = outputBuffer.array();

        return new String(outputData);
    }
}
