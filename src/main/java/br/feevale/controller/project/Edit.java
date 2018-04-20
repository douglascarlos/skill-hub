package br.feevale.controller.project;

import br.feevale.controller.Action;
import br.feevale.dao.ProjectDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        if(inputId == null){
            throw new Exception("404");
        }
        long convertedInputId = Long.parseLong(inputId);

        ProjectDAO dao = new ProjectDAO();
        Project project = dao.find(convertedInputId);

        request.setAttribute("project", project);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/project/general-data.jsp");
    }

}
