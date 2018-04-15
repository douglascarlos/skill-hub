package br.feevale.dao;

import br.feevale.connection.ConnectionFactory;

import java.sql.Connection;

public abstract class DAO {
    protected Connection connection;

    public DAO() {
        this.connection = ConnectionFactory.getConnection();
    }

}