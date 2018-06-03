package br.feevale.controller.report;

import br.feevale.controller.Action;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index implements Action {

    public Responder execute(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String name = request.getParameter("name");
//        if(name == null){
//            name = "";
//        }



//        request.setAttribute("name", name);


        return new Forward("/WEB-INF/views/report/index.jsp");
    }
    
}
