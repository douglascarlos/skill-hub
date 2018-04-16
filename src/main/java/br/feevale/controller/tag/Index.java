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

public class Index implements Action {
    @Override
    public Responder execute(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String inputName = request.getParameter("name");
        if(inputName == null){
            inputName = "";
        }

        TagDAO dao = new TagDAO();
        List<Tag> tags = dao.filterByName(inputName);

        request.setAttribute("name", inputName);
        request.setAttribute("tags", tags);

        servlet.withSession(request, "successMessage");

        return new Forward("/WEB-INF/views/tag/index.jsp");
    }
}
