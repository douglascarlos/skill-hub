package br.feevale.http.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends Responder{
    
    protected String contextPath;

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Redirect(String destino) {
        super(destino);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect(this.contextPath + this.destino);
    }
    
}
