package br.feevale.mapper;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class RequestToMap implements Mapper {

    public static RequestToMap getInstance(){
        return new RequestToMap();
    }

    public Map<String, String> map(ServletRequest request, List<String> attributes) {
        Map<String, String> input = new HashMap();

        for (int index=0; index<attributes.size(); index++){
            input.put(attributes.get(index), request.getParameter(attributes.get(index)));
        }

        return input;
    }
}
