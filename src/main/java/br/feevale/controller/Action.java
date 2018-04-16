package br.feevale.controller;

import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface Action {
    
    public Responder execute(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception;
    
}
