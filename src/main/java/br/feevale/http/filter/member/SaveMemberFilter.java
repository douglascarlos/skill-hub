package br.feevale.http.filter.member;

import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.member.SaveMemberValidator;
import br.feevale.mapper.RequestToMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebFilter(filterName = "SaveMemberFilter", urlPatterns = {"/member"})
public class SaveMemberFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        boolean isSaveAction = action instanceof String && action.equals("Save");
        if (isSaveAction) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("project_id");
            attributes.add("person_id");
            attributes.add("role");
            attributes.add("start_date");
            attributes.add("end_date");

            String[] skillsInput = request.getParameterValues("skill_id[]");

            Map<String, String> input = RequestToMap.getInstance().map(servletRequest, attributes);
            SaveMemberValidator validator = (SaveMemberValidator) SaveMemberValidator.getInstance();
            validator.validateSkills(skillsInput);

            if (validator.validate(input)) {
                request.getSession().setAttribute("errors", validator.getErrors());
                request.getSession().setAttribute("input", input);

                String actionToRedirect = "/member?action=SelectSkill" +
                        "&project_id=" + input.get("project_id") +
                        "&role=" + input.get("role") +
                        "&start_date=" + input.get("start_date") +
                        "&end_date=" + input.get("end_date") +
                        "&person_id=" + input.get("person_id");
                Redirect redirect = new Redirect(actionToRedirect);

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
