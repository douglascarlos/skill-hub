package br.feevale.controller.person;

import br.feevale.controller.Action;
import br.feevale.http.response.Redirect;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Save implements Action{

    public Responder executa(Servlet controller, HttpServletRequest request, HttpServletResponse response) throws Exception {

        controller.setSession(request, "successMessage", "Pessoa salva com sucesso.");

        return new Redirect("/person");
    }

}
