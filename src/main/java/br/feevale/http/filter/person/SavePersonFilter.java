package br.feevale.http.filter.person;

import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.person.SavePersonValidator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "SavePersonFilter", urlPatterns = {"/person"})
public class SavePersonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        if(action == null){
            action = "Index";
        }

        if (action.equals("Save")) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            Map<String, String> input = new HashMap();
            input.put("id", servletRequest.getParameter("id"));
            input.put("enrollment_number", servletRequest.getParameter("enrollment_number"));
            input.put("name", servletRequest.getParameter("name"));
            input.put("email", servletRequest.getParameter("email"));

            ValidatorForm validator = SavePersonValidator.getInstance();

            if (validator.validate(input)) {
                request.getSession().setAttribute("errors", validator.getErrors());
                request.getSession().setAttribute("input", input);

                String actionToRedirect = input.get("id").isEmpty() ? "Create" : "Edit&id=" + input.get("id");
                Redirect redirect = new Redirect("/person?action=" + actionToRedirect);

                redirect.setContextPath(servletRequest.getServletContext().getContextPath());
                redirect.executar(request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
