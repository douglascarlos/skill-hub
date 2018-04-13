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

}
