package br.feevale.http.filter.project;

import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.project.SaveProjectValidator;
import br.feevale.mapper.RequestToMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebFilter(filterName = "SaveProjectFilter", urlPatterns = {"/project"})
public class SaveProjectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        boolean isSaveProjectAction = action instanceof String && action.equals("Save");
        if (isSaveProjectAction) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("id");
            attributes.add("name");
            attributes.add("description");

            Map<String, String> input = RequestToMap.getInstance().map(servletRequest, attributes);
            ValidatorForm validator = SaveProjectValidator.getInstance();

            if (validator.validate(input)) {
                request.getSession().setAttribute("errors", validator.getErrors());
                request.getSession().setAttribute("input", input);

                String actionToRedirect = input.get("id").isEmpty() ? "Create" : "Edit&id=" + input.get("id");
                Redirect redirect = new Redirect("/project?action=" + actionToRedirect);

                redirect.setContextPath(servletRequest.getServletContext().getContextPath());
                redirect.execute(request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
