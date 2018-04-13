package br.feevale.controller.tag;

import br.feevale.controller.Action;
import br.feevale.dao.TagDAO;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Save implements Action{

    public Responder executa(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String inputId = request.getParameter("id");
        long convertedInputId = 0;
        if(!inputId.equals("")){
            convertedInputId = Long.parseLong(inputId);
        }
        String inputName = request.getParameter("name");

        TagDAO dao = new TagDAO();
        Tag tag = new Tag();

        if(convertedInputId > 0){
            tag = dao.find(convertedInputId);
        }

        tag.setName(inputName);

        dao.save(tag);

        controller.setSession(request, "successMessage", "Tag salva com sucesso.");

        return new Redirect("/tag");
    }

}
