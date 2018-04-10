package br.feevale.http.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet{
    
    public void withSession(HttpServletRequest request, String key){
        HttpSession session = request.getSession();
        if(session.getAttribute(key) != null){
            request.setAttribute(key, session.getAttribute(key));
            session.removeAttribute(key);
        }
    }
    
    public void setSession(HttpServletRequest request, String key, String value){
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }
}
