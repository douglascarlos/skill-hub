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

    public Level find(long id){
        String sql = "SELECT id, name, ordination, weight FROM levels WHERE id = ?";

        Level level;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            level = new Level();

            while (rs.next()) {
                level.setId(rs.getLong("id"));
                level.setName(rs.getString("name"));
                level.setOrdination(rs.getInt("ordination"));
                level.setWeight(rs.getInt("weight"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }

        if(level.getId() == 0){
            throw new RuntimeException("Nível não encontrado.");
        }

        return level;
    }

}
