package br.feevale.http.filter.person;

import br.feevale.http.filter.ValidatorForm;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Redirect;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

@WebFilter(filterName = "SavePersonFilter", urlPatterns = {"/person"})
public class SavePersonFilter extends ValidatorForm implements Filter {

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

            this.numeric("ID", input.get("id"));
            this.required("Nome", input.get("name"));
            this.min("Nome", input.get("name"));
            this.max("Nome", input.get("name"));
            this.required("Matrícula", input.get("enrollment_number"));
            this.numeric("Matrícula", input.get("enrollment_number"));
            this.exact("Matrícula", input.get("enrollment_number"), 7);
            this.required("E-mail", input.get("email"));
            this.max("E-mail", input.get("email"));
            this.email("E-mail", input.get("email"));

            if (this.isNotValid()) {
                request.getSession().setAttribute("errors", this.getErrors());
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
