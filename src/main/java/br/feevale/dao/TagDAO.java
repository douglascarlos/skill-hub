package br.feevale.dao;

import br.feevale.connection.ConnectionFactory;
import br.feevale.model.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagDAO {

    private Connection connection;

    public TagDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public ArrayList<Tag> list() {
        String sql = "SELECT id, name FROM tags WHERE deleted_at IS NULL";
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setName(rs.getString("name"));

                tags.add(tag);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return tags;
    }

    public ArrayList<Tag> filterByName(String name) {
        String sql = "" +
                "SELECT id, name " +
                "FROM tags " +
                "WHERE deleted_at IS NULL AND UPPER(name) like '%" + name.toUpperCase() + "%' " +
                "ORDER BY name";
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setName(rs.getString("name"));

                tags.add(tag);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return tags;
    }

    public Tag find(long id){
        String sql = "SELECT id, name FROM tags WHERE deleted_at IS NULL AND id = ?";

        Tag tag;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            tag = new Tag();

            while (rs.next()) {
                tag.setId(rs.getLong("id"));
                tag.setName(rs.getString("name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }

        if(tag.getId() == 0){
            throw new RuntimeException("Tag não encontrada.");
        }

        return tag;
    }

    public void save(Tag tag){
        if(tag.getId() > 0){
            this.update(tag);
        }else{
            this.store(tag);
        }
    }

    private void store(Tag tag){
        String sql = "INSERT INTO tags (name, created_at, updated_at) values (?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, tag.getName());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    private void update(Tag tag){
        String sql = "" +
                "UPDATE tags SET " +
                "name = ?, " +
                "updated_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, tag.getName());
            stmt.setLong(2, tag.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

}
