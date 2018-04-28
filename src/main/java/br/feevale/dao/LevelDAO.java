package br.feevale.dao;

import br.feevale.model.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LevelDAO extends DAO {

    public ArrayList<Level> list() {
        String sql = "SELECT id, name, weight, ordination FROM levels ORDER BY ordination";
        ArrayList<Level> levels = new ArrayList<Level>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Level level = new Level();
                level.setId(rs.getLong("id"));
                level.setName(rs.getString("name"));
                level.setWeight(rs.getInt("weight"));
                level.setOrdination(rs.getInt("ordination"));

                levels.add(level);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return levels;
    }

}
