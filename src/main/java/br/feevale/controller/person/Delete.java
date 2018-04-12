package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.dao.PersonDAO;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete implements Action {

    public Responder executa(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        if(inputId == null){
            throw new Exception("404");
        }
        long convertedInputId = Long.parseLong(inputId);

        PersonDAO dao = new PersonDAO();
        Person person = dao.find(convertedInputId);

        dao.delete(person);

        String message = "Pessoa excluída com sucesso.";
        controller.setSession(request, "successMessage", message);

        return new Redirect("/person");
    }

}
