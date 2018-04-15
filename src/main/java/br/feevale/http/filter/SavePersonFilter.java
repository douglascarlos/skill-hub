package br.feevale.http.filter;

import br.feevale.http.response.Forward;
import br.feevale.http.response.Redirect;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.validator.routines.EmailValidator;

@WebFilter(filterName = "SavePersonFilter", urlPatterns = {"/person"})
public class SavePersonFilter implements Filter {

    private boolean isValid;
    private ArrayList<String> errors;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        if(action == null){
            action = "Index";
        }
        System.out.println("-------------");
        if (action.equals("Save")) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            String inputId = servletRequest.getParameter("id");
            String inputEnrollmentNumber = servletRequest.getParameter("enrollment_number");
            String inputName = servletRequest.getParameter("name");
            String inputEmail = servletRequest.getParameter("email");

            ArrayList errors = new ArrayList<String>();

            try {
                if (!inputId.isEmpty()) {
                    Long convertedInputId = Long.parseLong(inputId);
                }
            } catch (Exception exception) {
                errors.add("O campo ID deve conter número válido.");
            }

            if (inputEnrollmentNumber == null || inputEnrollmentNumber.isEmpty()) {
                errors.add("O campo Matrícula é obrigatório.");
            }

            try {
                Integer convertedInputEnrollmentNumber = Integer.parseInt(inputEnrollmentNumber);
            } catch (Exception exception) {
                errors.add("O campo Matrícula deve conter um número válido.");
            }

            if (inputEnrollmentNumber instanceof String && inputEnrollmentNumber.length() != 7) {
                errors.add("O campo Matrícula deve conter 7 caracteres.");
            }

            if (inputName == null || inputName.isEmpty()) {
                errors.add("O campo Nome é obrigatório.");
            }

            if (inputName instanceof String && inputName.length() < 3) {
                errors.add("O campo Nome deve conter pelo menos 3 caracteres.");
            }

            if (inputName instanceof String && inputName.length() > 255) {
                errors.add("O campo Nome deve conter no máximo 255 caracteres.");
            }

            if (inputEmail == null || inputEmail.equals("")) {
                errors.add("O campo E-mail é obrigatório.");
            }

            if (inputEmail instanceof String && inputEmail.length() > 255) {
                errors.add("O campo E-mail deve conter no máximo 255 caracteres.");
            }

            if (!EmailValidator.getInstance().isValid(inputEmail)) {
                errors.add("O campo E-mail deve conter um e-mail válido.");
            }

            boolean isInvalid = !errors.isEmpty();

            if (isInvalid) {
                System.out.println("invalido");

                request.getSession().setAttribute("errors", errors);
                request.getSession().setAttribute("name", inputName);

                String actionToRedirect = inputId.isEmpty() ? "Create" : "Edit&id=" + inputId;

                Redirect redirect = new Redirect("/person?action=" + actionToRedirect);

                redirect.setContextPath(servletRequest.getServletContext().getContextPath());
                redirect.executar(request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("-------------");
    }

    @Override
    public void destroy() {
    }
}
