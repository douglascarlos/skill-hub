package br.feevale.dao;

import br.feevale.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.feevale.model.Person;

public class PersonDAO {

    private Connection connection;

    public PersonDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public ArrayList<Person> list() {
        String sql = "SELECT id, name, email, enrollment_number FROM people";
        ArrayList<Person> people = new ArrayList<Person>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setEnrollmentNumber(rs.getInt("enrollment_number"));

                people.add(person);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("$$$$ " + exception.getMessage());
            throw new RuntimeException(exception);
        }
        return people;
    }

}
