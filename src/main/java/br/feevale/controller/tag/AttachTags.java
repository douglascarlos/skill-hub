package br.feevale.controller.tag;

import br.feevale.controller.Action;
import br.feevale.dao.TagDAO;
import br.feevale.helper.Charset;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AttachTags implements Action{

    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        long convertedInputId = Long.parseLong(inputId);

        TagDAO dao = new TagDAO();
        Tag tag = dao.find(convertedInputId);
        ArrayList<Tag> tagsToAttach = new ArrayList<Tag>();

        String[] childrenInput = request.getParameterValues("children[]");
        for(int index = 0; index < childrenInput.length; index++) {
            long convertedId = Long.parseLong(childrenInput[index]);
            tagsToAttach.add(dao.find(convertedId));
        }

        tag.setChildren(tagsToAttach);

        dao.updateChildrenTags(tag);

        controller.setSession(request, "successMessage", "Tags vinculadas com sucesso.");

        return new Redirect("/tag");
    }

}
