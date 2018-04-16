package br.feevale.dao;

import br.feevale.http.validator.Unique;
import br.feevale.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO extends DAO implements Unique {

    public ArrayList<Person> list() {
        String sql = "SELECT id, name, email, enrollment_number FROM people WHERE deleted_at IS NULL";
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
        String sql = "" +
                "SELECT id, name, email, enrollment_number " +
                "FROM people " +
                "WHERE deleted_at IS NULL AND UPPER(name) like '%" + name.toUpperCase() + "%' " +
                "ORDER BY name";
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

    public void save(Person person) {
        if (person.getId() > 0) {
            this.update(person);
        } else {
            this.store(person);
        }
    }

    public Person find(long id) {
        String sql = "SELECT id, name, email, enrollment_number FROM people WHERE deleted_at IS NULL AND id = ?";

        Person person;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            person = new Person();

            while (rs.next()) {
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setEnrollmentNumber(rs.getInt("enrollment_number"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }

        if (person.getId() == 0) {
            throw new RuntimeException("Pessoa não encontrada.");
        }

        return person;
    }

    public void delete(Person person) {
        String sql = "" +
                "UPDATE people SET " +
                "deleted_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, person.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    private void store(Person person) {
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

    private void update(Person person) {
        String sql = "" +
                "UPDATE people SET " +
                "enrollment_number = ?, " +
                "name = ?, " +
                "email = ?, " +
                "updated_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, person.getEnrollmentNumber());
            stmt.setString(2, person.getName());
            stmt.setString(3, person.getEmail());
            stmt.setLong(4, person.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean unique(String value, String column) {
        return this.unique(value, column, 0);
    }

    @Override
    public boolean unique(String value, String column, long exceptId) {
        String sql = "SELECT id FROM people WHERE deleted_at IS NULL AND UPPER(" + column + ") = UPPER(?)";

        if(column.equals("enrollment_number")) {
            sql = "SELECT id FROM people WHERE deleted_at IS NULL AND " + column + " = ?";
        }

        Person person = new Person();
        person.setId(exceptId);

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            if(column.equals("enrollment_number")){
                if(value.isEmpty()){
                    return true;
                }
                stmt.setInt(1, Integer.parseInt(value));
            }else{
                stmt.setString(1, value);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                person.setId(rs.getLong("id"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return person.getId() == exceptId;
    }
}
