package br.feevale.controller.member;

import br.feevale.controller.Action;
import br.feevale.dao.PersonDAO;
import br.feevale.dao.ProjectDAO;
import br.feevale.helper.Charset;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Member;
import br.feevale.model.Person;
import br.feevale.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

public class Create implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputProjectId = request.getParameter("project_id");
        if(inputProjectId == null){
            throw new Exception("404");
        }
        long convertedInputProjectId = Long.parseLong(inputProjectId);

        String inputRole = Charset.toIso88591(request.getParameter("role"));
        String inputStartDate = Charset.toIso88591(request.getParameter("start_date"));
        String inputEndDate = Charset.toIso88591(request.getParameter("end_date"));

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = projectDAO.find(convertedInputProjectId);

        Member member = new Member();
        member.setProject(project);
        member.setRole(inputRole);
        member.setStartDate(inputStartDate);
        member.setEndDate(inputEndDate);

        request.setAttribute("member", member);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/project/members/form.jsp");
    }

}
