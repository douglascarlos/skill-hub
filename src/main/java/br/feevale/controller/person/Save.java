package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.dao.PersonDAO;
import br.feevale.helper.Charset;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Save implements Action{

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String inputId = request.getParameter("id");
        long convertedInputId = 0;
        if(!inputId.equals("")){
            convertedInputId = Long.parseLong(inputId);
        }
        String inputEnrollmentNumber = request.getParameter("enrollment_number");
        int convertedInputEnrollmentNumber = Integer.parseInt(inputEnrollmentNumber);
        String inputName = Charset.toIso88591( request.getParameter("name"));
        String inputEmail = Charset.toIso88591(request.getParameter("email"));

        PersonDAO dao = new PersonDAO();
        Person person = new Person();

        if(convertedInputId > 0){
            person = dao.find(convertedInputId);
        }

        person.setEnrollmentNumber(convertedInputEnrollmentNumber);
        person.setName(inputName);
        person.setEmail(inputEmail);

        dao.save(person);

        controller.setSession(request, "successMessage", "Pessoa salva com sucesso.");

        return new Redirect("/person");
    }

}
