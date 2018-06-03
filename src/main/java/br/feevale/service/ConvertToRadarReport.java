package br.feevale.service;

import br.feevale.model.Member;
import br.feevale.model.Person;
import br.feevale.model.Project;
import br.feevale.model.Skill;

import java.util.*;

public class ConvertToRadarReport {

    private List<String> colors;

    public ConvertToRadarReport(){
        this.colors = new ArrayList<>();
        colors.add("255,193,7"); //amber
        colors.add("244,67,54"); //red
        colors.add("103,58,183"); //deep-purple
        colors.add("3,169,244"); //light-blue
        colors.add("76,175,80"); //green
        colors.add("255,87,34"); //deep-orange
        colors.add("96,125,139"); //blue-gray
        colors.add("233,30,99"); //pink
        colors.add("63,81,181"); //indigo
        colors.add("139,195,74"); //light-green
        colors.add("121,85,72"); //brown
        colors.add("171,71,188"); //purple light-1
        colors.add("0,150,136"); //teal
        colors.add("205,220,57"); //lime
        colors.add("158,158,158"); //gray
        colors.add("0,0,0,"); //black
    }

    public String getColor(){
        String color = this.colors.remove(0);
        this.colors.add(color);
        return color;
    }

    public String generate(Project project){
        String labels = "";
        String weights = "";
        String datasets = "";

        for (Skill skill : project.getSkills()) {
            labels += "\"" + skill.getTag().getName() + "\",";
            weights += skill.getLevel().getWeight() + ",";
        }


        String color = this.getColor();
        datasets += "{\n" +
                "            \"label\":\"" + project.getName() + "\",\n" +
                "            \"data\":[" + weights + "],\n" +
                "            \"fill\":true,\n" +
                "            \"backgroundColor\":\"rgba(" + color + ", 0.2)\",\n" +
                "            \"borderColor\":\"rgb(" + color + ")\",\n" +
                "            \"pointBackgroundColor\":\"rgb(" + color + ")\",\n" +
                "            \"pointBorderColor\":\"#000\",\n" +
                "            \"pointHoverBackgroundColor\":\"#000\",\n" +
                "            \"pointHoverBorderColor\":\"rgb(" + color + ")\"\n" +
                "        },";

        for(Member member : project.getMembers()){

            weights = "";

            HashMap<String, Skill> skillMap = new HashMap<String, Skill>();
            for (Skill skill : member.getSkills()) {
                skillMap.put(skill.getTag().getName(), skill);
            }

            for (Skill skill : project.getSkills()) {
                if(skillMap.containsKey(skill.getTag().getName())){
                    weights += skillMap.get(skill.getTag().getName()).getLevel().getWeight() + ",";
                }else{
                    weights += "0,";
                }
            }

            String rgb = this.getColor();
            datasets += "{\n" +
                    "            \"label\":\"" + member.getPerson().getName() + "\",\n" +
                    "            \"data\":[" + weights + "],\n" +
                    "            \"fill\":true,\n" +
                    "            \"backgroundColor\":\"rgba(" + rgb + ", 0.2)\",\n" +
                    "            \"borderColor\":\"rgb(" + rgb + ")\",\n" +
                    "            \"pointBackgroundColor\":\"rgb(" + rgb + ")\",\n" +
                    "            \"pointBorderColor\":\"#000\",\n" +
                    "            \"pointHoverBackgroundColor\":\"#000\",\n" +
                    "            \"pointHoverBorderColor\":\"rgb(" + rgb + ")\"\n" +
                    "        },";
        }

        String data = "{\n";
        data += "labels:[" + labels + "],\n";
        data += "datasets:[" + datasets + "],\n";
        data += "},";

        String reportStructure = "{\n" +
                "        type: 'radar',\n" +
                "        data: " + data + "\n" +
                "        options: {\n" +
                "            \"elements\":{\n" +
                "                \"line\":{\n" +
                "                    \"tension\":0,\n" +
                "                    \"borderWidth\":3\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        return reportStructure;
    }

    public String generate(Person person){
        String labels = "";
        String weights = "";

        for (Skill skill : person.getSkills()) {
            labels += "\"" + skill.getTag().getName() + "\",";
            weights += skill.getLevel().getWeight() + ",";
        }

        String dataset = "{\n" +
                "            \"label\":\"" + person.getName() + "\",\n" +
                "            \"data\":[" + weights + "],\n" +
                "            \"fill\":true,\n" +
                "            \"backgroundColor\":\"rgba(255, 193, 7, 0.2)\",\n" +
                "            \"borderColor\":\"rgb(255, 193, 7)\",\n" +
                "            \"pointBackgroundColor\":\"rgb(255, 193, 7)\",\n" +
                "            \"pointBorderColor\":\"#000\",\n" +
                "            \"pointHoverBackgroundColor\":\"#000\",\n" +
                "            \"pointHoverBorderColor\":\"rgb(255, 193, 7)\"\n" +
                "        },";

        String data = "{\n";
        data += "labels:[" + labels + "],\n";
        data += "datasets:[" + dataset + "],\n";
        data += "},";

        String reportStructure = "{\n" +
                "        type: 'radar',\n" +
                "        data: " + data + "\n" +
                "        options: {\n" +
                "            \"elements\":{\n" +
                "                \"line\":{\n" +
                "                    \"tension\":0,\n" +
                "                    \"borderWidth\":3\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        return reportStructure;
    }
}
