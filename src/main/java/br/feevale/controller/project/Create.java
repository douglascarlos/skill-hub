package br.feevale.controller.project;

import br.feevale.controller.Action;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Create implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Project project = new Project();
        request.setAttribute("project", project);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/project/registration-tabs.jsp");
    }

}
