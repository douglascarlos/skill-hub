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
import java.util.ArrayList;

public class SelectPerson implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputProjectId = request.getParameter("project_id");
        if(inputProjectId == null){
            throw new Exception("404");
        }
        long convertedInputProjectId = Long.parseLong(inputProjectId);

        String inputPersonId = request.getParameter("person_id");
        if(inputPersonId == null){
            throw new Exception("404");
        }
        long convertedInputPersonId = Long.parseLong(inputPersonId);

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = projectDAO.find(convertedInputProjectId);

        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.find(convertedInputPersonId);

        Member member = new Member();
        member.setProject(project);
        member.setPerson(person);

        request.setAttribute("member", member);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/project/members/form.jsp");
    }

}
