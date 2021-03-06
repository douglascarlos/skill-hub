package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.dao.LevelDAO;
import br.feevale.dao.PersonDAO;
import br.feevale.dao.SkillDAO;
import br.feevale.dao.TagDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Level;
import br.feevale.model.Person;
import br.feevale.model.Skill;
import br.feevale.model.Tag;
import br.feevale.service.ConvertToRadarReport;

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

        String inputSkillId = request.getParameter("skill_id");
        long convertedInputSkillId = 0;
        if(inputSkillId != null){
            convertedInputSkillId = Long.parseLong(inputSkillId);
        }

        PersonDAO personDAO= new PersonDAO();
        Person person = personDAO.find(convertedInputId);

        TagDAO tagDAO = new TagDAO();
        ArrayList<Tag> tagsToAttach = tagDAO.tagsToAttach(person);

        LevelDAO levelDAO = new LevelDAO();
        ArrayList<Level> levels = levelDAO.list();

        Skill skill = new Skill();
        boolean isEditSkill = convertedInputSkillId > 0 && !person.getSkills().isEmpty();
        if(isEditSkill){
            skill = person.getSkillById(convertedInputSkillId);
        }

        ConvertToRadarReport serviceRadarReport = new ConvertToRadarReport();
        String radarReportData = serviceRadarReport.generate(person);

        request.setAttribute("person", person);
        request.setAttribute("tagsToAttach", tagsToAttach);
        request.setAttribute("levels", levels);
        request.setAttribute("skill", skill);
        request.setAttribute("radarReportData", radarReportData);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");
        controller.withSession(request, "successMessage");

        return new Forward("/WEB-INF/views/person/registration-tabs.jsp");
    }

}
