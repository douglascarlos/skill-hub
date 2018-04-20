package br.feevale.controller.project;

import br.feevale.controller.Action;
import br.feevale.dao.ProjectDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Project;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Index implements Action {

    public Responder execute(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        if(name == null){
            name = "";
        }

        ProjectDAO dao = new ProjectDAO();
        List<Project> projects = dao.filterByName(name);

        request.setAttribute("name", name);
        request.setAttribute("projects", projects);

        servlet.withSession(request, "successMessage");

        return new Forward("/WEB-INF/views/project/index.jsp");
    }
    
}
