package br.feevale.controller.skill;

import br.feevale.controller.Action;
import br.feevale.dao.PersonDAO;
import br.feevale.dao.SkillDAO;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Person;
import br.feevale.model.Skill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputSkillId = request.getParameter("skill_id");
        if(inputSkillId == null){
            throw new Exception("404");
        }
        long convertedInputSkillId = Long.parseLong(inputSkillId);

        String inputPersonId = request.getParameter("person_id");
        if(inputPersonId == null){
            throw new Exception("404");
        }
        long convertedInputPersonId = Long.parseLong(inputPersonId);

        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.find(convertedInputPersonId);

        Skill skill = person.getSkillById(convertedInputSkillId);

        SkillDAO skillDAO = new SkillDAO();
        skillDAO.delete(skill);

        String message = "Competência excluída com sucesso.";
        controller.setSession(request, "successMessage", message);

        return new Redirect("/person");
    }

}
