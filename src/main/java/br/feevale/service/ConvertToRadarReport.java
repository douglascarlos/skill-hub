package br.feevale.service;

import br.feevale.model.Person;
import br.feevale.model.Skill;

import java.util.List;

public class ConvertToRadarReport {

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
