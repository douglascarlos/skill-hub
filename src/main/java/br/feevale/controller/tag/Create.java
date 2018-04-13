package br.feevale.controller.tag;

import br.feevale.controller.Action;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Create implements Action {

    public Responder executa(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Tag tag = new Tag();
        request.setAttribute("tag", tag);
        return new Forward("/WEB-INF/views/tag/general-data.jsp");
    }

}
