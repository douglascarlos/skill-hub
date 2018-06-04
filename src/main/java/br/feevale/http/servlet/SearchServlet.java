package br.feevale.http.servlet;

import br.feevale.controller.Action;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends Servlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "Index";
        }
        
        String nomeDaClasse = "br.feevale.controller.search." + action;

        try {
            Class classe = Class.forName(nomeDaClasse);
            Action controller = (Action) classe.newInstance();
            
            Responder responder = controller.execute(this, request, response);
            
            if(responder instanceof Redirect){
                ((Redirect) responder).setContextPath(getServletContext().getContextPath());
            }
            
            responder.execute(request, response);

        } catch (Exception e) {
            throw new ServletException(this.getServletInfo() + " causou uma exceção: ", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Person Servelet";
    }// </editor-fold>

}
