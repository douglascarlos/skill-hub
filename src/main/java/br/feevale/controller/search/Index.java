package br.feevale.controller.search;

import br.feevale.controller.Action;
import br.feevale.dao.LevelDAO;
import br.feevale.dao.SearchDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Level;
import br.feevale.model.Model;
import br.feevale.presenter.collectionItem.CollectionItemPresenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Index implements Action {

    public Responder execute(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String inputFilter = request.getParameter("filter");
        if(inputFilter == null){
            inputFilter = "";
        }

        String[] modelFilterInput = request.getParameterValues("by_model[]");
        String[] levelFilterInput = request.getParameterValues("by_level[]");

        SearchDAO searchDAO = new SearchDAO();
        List<Model> models = searchDAO.search(inputFilter, modelFilterInput, levelFilterInput);

        CollectionItemPresenter collectionItemPresenter = new CollectionItemPresenter();

        LevelDAO levelDAO = new LevelDAO();
        List<Level> levels = levelDAO.list();

        request.setAttribute("filter", inputFilter);
        request.setAttribute("filter_by_model", modelFilterInput);
        request.setAttribute("filter_by_level", levelFilterInput);
        request.setAttribute("models", models);
        request.setAttribute("collectionItemPresenter", collectionItemPresenter);
        request.setAttribute("levels", levels);

        servlet.withSession(request, "successMessage");
        servlet.withSession(request, "errors");
        servlet.withSession(request, "input");
        return new Forward("/WEB-INF/views/search/index.jsp");
    }
    
}
