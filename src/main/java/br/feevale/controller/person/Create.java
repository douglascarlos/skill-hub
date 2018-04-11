package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Create implements Action {

    public Responder executa(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Person person = new Person();
        request.setAttribute("person", person);
        return new Forward("/WEB-INF/views/person/general-data.jsp");
    }

}
