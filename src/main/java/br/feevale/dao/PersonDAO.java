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
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return people;
    }

    public ArrayList<Person> filterByName(String name) {
        String sql = "SELECT id, name, email, enrollment_number FROM people WHERE UPPER(name) like '%" + name.toUpperCase() + "%'";
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
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return people;
    }

    public void save(Person person){
        if(person.getId() > 0){
            this.update(person);
        }
        this.store(person);
    }

    private void store(Person person){
        String sql = "INSERT INTO people (enrollment_number, name, email, created_at, updated_at) values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, person.getEnrollmentNumber());
            stmt.setString(2, person.getName());
            stmt.setString(3, person.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    private void update(Person person){

    }

}
