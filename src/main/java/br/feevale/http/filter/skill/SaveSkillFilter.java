package br.feevale.http.filter.skill;

import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.skill.SaveSkillValidator;
import br.feevale.mapper.RequestToMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebFilter(filterName = "SaveSkillFilter", urlPatterns = {"/skill"})
public class SaveSkillFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        boolean isSaveAction = action instanceof String && action.equals("Save");
        if (isSaveAction) {
            System.out.println("1");
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("skill_id");
            attributes.add("person_id");
            attributes.add("tag_id");
            attributes.add("level_id");
            System.out.println("2");
            Map<String, String> input = RequestToMap.getInstance().map(servletRequest, attributes);
            ValidatorForm validator = SaveSkillValidator.getInstance();
            System.out.println("3");
            if (validator.validate(input)) {
                System.out.println("4");
                request.getSession().setAttribute("errors", validator.getErrors());
                request.getSession().setAttribute("input", input);

                String actionToRedirect = input.get("skill_id").isEmpty() ? "" : "&skill_id=" + input.get("skill_id");
                Redirect redirect = new Redirect("/person?action=Edit&id=" + input.get("person_id") + actionToRedirect);
                System.out.println("5");
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
