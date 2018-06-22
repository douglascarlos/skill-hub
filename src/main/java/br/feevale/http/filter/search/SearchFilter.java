package br.feevale.http.filter.search;

import br.feevale.dao.LevelDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.search.SearchValidator;
import br.feevale.mapper.RequestToMap;
import br.feevale.model.Level;
import br.feevale.model.Model;
import br.feevale.presenter.collectionItem.CollectionItemPresenter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "SearchFilter", urlPatterns = {"/search"})
public class SearchFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("filter");

        Map<String, String> input = RequestToMap.getInstance().map(servletRequest, attributes);
        String[] modelFilterInput = request.getParameterValues("by_model[]");
        String[] levelFilterInput = request.getParameterValues("by_level[]");

        SearchValidator validator = (SearchValidator) SearchValidator.getInstance();
        validator.validateFilterByModel(modelFilterInput);
        validator.validateFilterByLevel(levelFilterInput);

        if (validator.validate(input)) {
            request.setAttribute("errors", validator.getErrors());
            request.setAttribute("input", input);
            request.setAttribute("filter_by_model", modelFilterInput);
            request.setAttribute("filter_by_level", levelFilterInput);

            List<Model> models = new ArrayList<>();

            CollectionItemPresenter collectionItemPresenter = new CollectionItemPresenter();

            LevelDAO levelDAO = new LevelDAO();
            List<Level> levels = levelDAO.list();

            request.setAttribute("models", models);
            request.setAttribute("collectionItemPresenter", collectionItemPresenter);
            request.setAttribute("levels", levels);

            Forward forward = new Forward("/WEB-INF/views/search/index.jsp");

            forward.execute(request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
