package br.feevale.controller.search;

import br.feevale.controller.Action;
import br.feevale.dao.SearchDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Index implements Action {

    public Responder execute(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String inputFilter = request.getParameter("filter");
        if(inputFilter == null){
            inputFilter = "";
        }

        SearchDAO searchDAO = new SearchDAO();
        List<Model> models = searchDAO.search(inputFilter);


        request.setAttribute("filter", inputFilter);
        request.setAttribute("models", models);

        servlet.withSession(request, "successMessage");
        servlet.withSession(request, "errors");
        servlet.withSession(request, "input");
        return new Forward("/WEB-INF/views/search/index.jsp");
    }
    
}
