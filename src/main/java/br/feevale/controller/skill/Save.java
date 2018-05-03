package br.feevale.controller.skill;

import br.feevale.controller.Action;
import br.feevale.dao.LevelDAO;
import br.feevale.dao.PersonDAO;
import br.feevale.dao.SkillDAO;
import br.feevale.dao.TagDAO;
import br.feevale.helper.Charset;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Level;
import br.feevale.model.Person;
import br.feevale.model.Skill;
import br.feevale.model.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Save implements Action{

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String inputId = request.getParameter("skill_id");
        long convertedInputId = 0;
        if(!inputId.equals("")){
            convertedInputId = Long.parseLong(inputId);
        }
        String inputPersonId = request.getParameter("person_id");
        int convertedInputPersonId = Integer.parseInt(inputPersonId);
        String inputTagId = request.getParameter("tag_id");
        int convertedInputTagId = Integer.parseInt(inputTagId);
        String inputLevelId = request.getParameter("level_id");
        int convertedInputLevelId = Integer.parseInt(inputLevelId);

        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.find(convertedInputPersonId);

        TagDAO tagDAO = new TagDAO();
        Tag tag = tagDAO.find(convertedInputTagId);

        LevelDAO levelDAO = new LevelDAO();
        Level level = levelDAO.find(convertedInputLevelId);

        SkillDAO skillDAO = new SkillDAO();
        Skill skill = new Skill();

        if(convertedInputId > 0){
            skill = person.getSkillById(convertedInputId);
        }

        skill.setPerson(person);
        skill.setTag(tag);
        skill.setLevel(level);

        skillDAO.save(skill);

        controller.setSession(request, "successMessage", "Competência salva com sucesso.");

        return new Redirect("/person");
    }

}
