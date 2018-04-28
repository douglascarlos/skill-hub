package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.dao.LevelDAO;
import br.feevale.dao.PersonDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Level;
import br.feevale.model.Person;
import br.feevale.model.Skill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Edit implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        if(inputId == null){
            throw new Exception("404");
        }
        long convertedInputId = Long.parseLong(inputId);

        PersonDAO dao = new PersonDAO();
        Person person = dao.find(convertedInputId);

        LevelDAO levelDAO = new LevelDAO();
        ArrayList<Level> levels = levelDAO.list();

        Skill skill = new Skill();

        request.setAttribute("person", person);
        request.setAttribute("levels", levels);
        request.setAttribute("skill", skill);
        //set levels
        //set tag to attach
        //set person with skills

        controller.withSession(request, "errors");
        controller.withSession(request, "input");

        return new Forward("/WEB-INF/views/person/registration-tabs.jsp");
    }

}
