package br.feevale.controller.member;

import br.feevale.controller.Action;
import br.feevale.dao.MemberDAO;
import br.feevale.dao.PersonDAO;
import br.feevale.dao.ProjectDAO;
import br.feevale.helper.Charset;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Member;
import br.feevale.model.Person;
import br.feevale.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectSkill implements Action{

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {

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

        MemberDAO memberDAO = new MemberDAO();
        Member member = new Member();

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = projectDAO.find(convertedInputProjectId);

        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.find(convertedInputPersonId);

        member.setProject(project);
        member.setRole(inputRole);
        member.setStartDate(inputStartDate);
        member.setEndDate(inputEndDate);
        member.setPerson(person);

        request.setAttribute("member", member);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/project/members/select-skill.jsp");
    }

}
