package br.feevale.controller.tag;

import br.feevale.controller.Action;
import br.feevale.dao.TagDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit implements Action {

    public Responder executa(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputId = request.getParameter("id");
        if(inputId == null){
            throw new Exception("404");
        }
        long convertedInputId = Long.parseLong(inputId);

        TagDAO dao = new TagDAO();
        Tag tag = dao.find(convertedInputId);

        request.setAttribute("tag", tag);

        controller.withSession(request, "errors");
        controller.withSession(request, "input");
        return new Forward("/WEB-INF/views/tag/general-data.jsp");
    }

}
