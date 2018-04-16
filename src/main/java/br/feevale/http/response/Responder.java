package br.feevale.http.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Responder {

    protected String destino;

    public Responder(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
