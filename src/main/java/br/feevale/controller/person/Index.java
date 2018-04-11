package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.dao.PersonDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.Person;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Index implements Action {
    
    @Override
    public Responder executa(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        if(name == null){
            name = "";
        }

        PersonDAO dao = new PersonDAO();
        List<Person> people = dao.filterByName(name);

        request.setAttribute("name", name);
        request.setAttribute("people", people);

        servlet.withSession(request, "successMessage");

        return new Forward("/WEB-INF/views/person/index.jsp");
    }
    
}
