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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SelectPerson implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        long convertedInputId = 0;
        if(!inputId.equals("")){
            convertedInputId = Long.parseLong(inputId);
        }

        String inputProjectId = request.getParameter("project_id");
        if(inputProjectId == null){
            throw new Exception("404");
        }
        long convertedInputProjectId = Long.parseLong(inputProjectId);

        String inputRole = Charset.toIso88591( request.getParameter("role"));

        String inputStartDate = Charset.toIso88591(request.getParameter("start_date"));
        LocalDate convertedStartDate = LocalDate.now();

        String inputEndDate = Charset.toIso88591(request.getParameter("end_date"));
        LocalDate convertedEndDate = LocalDate.now();

        MemberDAO memberDAO = new MemberDAO();
        Member member = new Member();

        if(convertedInputId > 0){
            System.out.println("--- find");
//            member = memberDAO.find(convertedInputId);
        }

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = projectDAO.find(convertedInputProjectId);

        member.setProject(project);
        member.setRole(inputRole);
        member.setStartDate(convertedStartDate);
        member.setEndDate(convertedEndDate);

        if(member.exists()){
            System.out.println("==== ta editando");
//            memberDAO.save(member);
            controller.setSession(request, "successMessage", "Membro salvo com sucesso. (501)");
            return new Redirect("/project?action=Edit&id=" + member.getProject().getId());
        }else{
            String name = request.getParameter("name");
            if(name == null){
                name = "";
            }

            PersonDAO personDAO = new PersonDAO();
            ArrayList<Person> people = personDAO.filterByName(name);

            request.setAttribute("member", member);
            request.setAttribute("name", name);
            request.setAttribute("people", people);

            controller.withSession(request, "errors");
            controller.withSession(request, "input");

            return new Forward("/WEB-INF/views/project/members/select-person.jsp");
        }
    }

}