package br.feevale.controller.project;

import br.feevale.controller.Action;
import br.feevale.dao.ProjectDAO;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Save implements Action{

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String inputId = request.getParameter("id");
        long convertedInputId = 0;
        if(!inputId.equals("")){
            convertedInputId = Long.parseLong(inputId);
        }
        String inputName = request.getParameter("name");
        String inputDescription = request.getParameter("description");

        ProjectDAO dao = new ProjectDAO();
        Project project = new Project();

        if(convertedInputId > 0){
            project = dao.find(convertedInputId);
        }

        project.setName(inputName);
        project.setDescription(inputDescription);

        dao.save(project);

        controller.setSession(request, "successMessage", "Projeto salvo com sucesso.");

        return new Redirect("/project");
    }

}
