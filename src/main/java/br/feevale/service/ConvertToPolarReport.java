package br.feevale.service;


import br.feevale.model.TagCounter;

import java.util.ArrayList;
import java.util.List;

public class ConvertToPolarReport {

    private List<String> colors;

    public ConvertToPolarReport(){
        this.resetColor();
    }

    private String getColor(){
        String color = this.colors.remove(0);
        this.colors.add(color);
        return color;
    }

    private void resetColor(){
        this.colors = new ArrayList<>();
        colors.add("rgb(255,193,7)"); //amber
        colors.add("rgb(244,67,54)"); //red
        colors.add("rgb(103,58,183)"); //deep-purple
        colors.add("rgb(3,169,244)"); //light-blue
        colors.add("rgb(76,175,80)"); //green
        colors.add("rgb(255,87,34)"); //deep-orange
        colors.add("rgb(96,125,139)"); //blue-gray
        colors.add("rgb(233,30,99)"); //pink
        colors.add("rgb(63,81,181)"); //indigo
        colors.add("rgb(139,195,74)"); //light-green
        colors.add("rgb(121,85,72)"); //brown
        colors.add("rgb(171,71,188)"); //purple light-1
        colors.add("rgb(0,150,136)"); //teal
        colors.add("rgb(205,220,57)"); //lime
        colors.add("rgb(158,158,158)"); //gray
        colors.add("rgb(0,0,0)"); //blacks
    }

    public String generateCount(List<TagCounter> tags){
        this.resetColor();
        String labels = "";
        String datasets = "";
        String backgroundColor = "";

        for (TagCounter tag : tags){
            labels += "\"" + tag.getName() + "\",";
            datasets += tag.getCount() + ",";
            backgroundColor += "\"" + this.getColor() + "\",";
        }

        String reportStructure = "{\n" +
                "   \"type\":\"polarArea\",\n" +
                "   \"data\":{\n" +
                "      \"labels\":[" + labels + "],\n" +
                "      \"datasets\":[\n" +
                "         {\n" +
                "            \"label\":\"Gráfico do Tipo Polar de Competências\",\n" +
                "            \"data\":[" + datasets + "],\n" +
                "            \"backgroundColor\":[" + backgroundColor + "]\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";
        return reportStructure;
    }

    public String generateAvarage(List<TagCounter> tags){
        this.resetColor();
        String labels = "";
        String datasets = "";
        String backgroundColor = "";

        for (TagCounter tag : tags){
            labels += "\"" + tag.getName() + "\",";
            datasets += tag.getAvarage() + ",";
            backgroundColor += "\"" + this.getColor() + "\",";
        }

        String reportStructure = "{\n" +
                "   \"type\":\"polarArea\",\n" +
                "   \"data\":{\n" +
                "      \"labels\":[" + labels + "],\n" +
                "      \"datasets\":[\n" +
                "         {\n" +
                "            \"label\":\"Gráfico do Tipo Polar de Competências\",\n" +
                "            \"data\":[" + datasets + "],\n" +
                "            \"backgroundColor\":[" + backgroundColor + "]\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";
        return reportStructure;
    }
}
