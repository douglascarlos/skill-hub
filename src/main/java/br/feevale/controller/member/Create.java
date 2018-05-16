package br.feevale.controller.member;

import br.feevale.controller.Action;
import br.feevale.dao.PersonDAO;
import br.feevale.dao.ProjectDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Member;
import br.feevale.model.Person;
import br.feevale.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Create implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("project_id");
        if(inputId == null){
            throw new Exception("404");
        }
        long convertedInputId = Long.parseLong(inputId);

        String name = request.getParameter("name");
        if(name == null){
            name = "";
        }

        ProjectDAO dao = new ProjectDAO();
        Project project = dao.find(convertedInputId);

        PersonDAO personDAO = new PersonDAO();
        ArrayList<Person> people = personDAO.filterByName(name);

        request.setAttribute("project", project);
        request.setAttribute("name", name);
        request.setAttribute("people", people);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/project/members/select-person.jsp");
    }

}
