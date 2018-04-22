package br.feevale.http.filter.tag;

import br.feevale.http.response.Redirect;
import br.feevale.http.validator.ValidatorForm;
import br.feevale.http.validator.tag.AttachTagsValidator;
import br.feevale.http.validator.tag.SaveTagValidator;
import br.feevale.mapper.RequestToMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebFilter(filterName = "AttachTagsFilter", urlPatterns = {"/tag"})
public class AttachTagsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        boolean shouldFilter = action instanceof String && action.equals("AttachTags");
        if (shouldFilter) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("id");

            Map<String, String> input = RequestToMap.getInstance().map(servletRequest, attributes);
            String[] childrenInput = request.getParameterValues("children[]");
            /*
            TODO: validate String[] input
             */

            ValidatorForm validator = AttachTagsValidator.getInstance();

            if (validator.validate(input)) {
                request.getSession().setAttribute("errors", validator.getErrors());
                request.getSession().setAttribute("input", input);

                String actionToRedirect = input.get("id").isEmpty() ? "" : "?action=Edit&id=" + input.get("id") + "#attach-tags";
                Redirect redirect = new Redirect("/tag" + actionToRedirect);

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
