package br.feevale.controller.member;

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

        System.out.println("---- vamos salvar");

        String inputProjectId = request.getParameter("project_id");
        if(inputProjectId == null){
            throw new Exception("404");
        }
        long convertedInputProjectId = Long.parseLong(inputProjectId);

        String inputRole = Charset.toIso88591( request.getParameter("role"));
        String inputStartDate = Charset.toIso88591(request.getParameter("start_date"));
        String inputEndDate = Charset.toIso88591(request.getParameter("end_date"));

        String inputPersonId = request.getParameter("person_id");
        if(inputPersonId == null){
            throw new Exception("404");
        }
        long convertedInputPersonId = Long.parseLong(inputPersonId);

        System.out.println(convertedInputProjectId);
        System.out.println(inputRole);
        System.out.println(inputStartDate);
        System.out.println(inputEndDate);
        System.out.println(convertedInputPersonId);

//        String inputId = request.getParameter("id");
//        long convertedInputId = 0;
//        if(!inputId.equals("")){
//            convertedInputId = Long.parseLong(inputId);
//        }
//        String inputEnrollmentNumber = request.getParameter("enrollment_number");
//        int convertedInputEnrollmentNumber = Integer.parseInt(inputEnrollmentNumber);
//        String inputName = Charset.toIso88591( request.getParameter("name"));
//        String inputEmail = Charset.toIso88591(request.getParameter("email"));
//
//        PersonDAO dao = new PersonDAO();
//        Person person = new Person();
//
//        if(convertedInputId > 0){
//            person = dao.find(convertedInputId);
//        }
//
//        person.setEnrollmentNumber(convertedInputEnrollmentNumber);
//        person.setName(inputName);
//        person.setEmail(inputEmail);
//
//        dao.save(person);
//
//        controller.setSession(request, "successMessage", "Pessoa salva com sucesso.");
//
        return new Redirect("/project");
    }

}
