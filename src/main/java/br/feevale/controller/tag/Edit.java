package br.feevale.controller.tag;

import br.feevale.controller.Action;
import br.feevale.dao.TagDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Edit implements Action {

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        if(inputId == null){
            throw new Exception("404");
        }
        long convertedInputId = Long.parseLong(inputId);

        TagDAO dao = new TagDAO();
        Tag tag = dao.find(convertedInputId);

        List<Tag> tagsToAttach = dao.tagsToAttach(tag);

        request.setAttribute("tag", tag);
        request.setAttribute("tagsToAttach", tagsToAttach);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");
        return new Forward("/WEB-INF/views/tag/registration-tabs.jsp");
    }

}
