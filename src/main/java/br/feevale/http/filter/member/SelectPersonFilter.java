package br.feevale.http.filter.member;

import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.member.SaveMemberValidator;
import br.feevale.http.validator.member.SelectPersonValidator;
import br.feevale.mapper.RequestToMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebFilter(filterName = "SelectPersonFilter", urlPatterns = {"/member"})
public class SelectPersonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        boolean isSaveAction = action instanceof String && action.equals("SelectPerson");
        if (isSaveAction) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("id");
            attributes.add("project_id");
            attributes.add("role");
            attributes.add("start_date");
            attributes.add("end_date");

            String[] skillsInput = request.getParameterValues("skill_id[]");

            Map<String, String> input = RequestToMap.getInstance().map(servletRequest, attributes);
            ValidatorForm validator = SelectPersonValidator.getInstance();

            if (validator.validate(input)) {
                request.getSession().setAttribute("errors", validator.getErrors());
                request.getSession().setAttribute("input", input);

                String actionToRedirect = "/member?action=";
                actionToRedirect += input.get("id").isEmpty() ? "Create&project_id=" + input.get("project_id") : "Edit&id=" + input.get("id");

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
