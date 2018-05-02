package br.feevale.dao;

import br.feevale.model.Skill;
import br.feevale.model.Person;
import br.feevale.model.Tag;
import br.feevale.model.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SkillDAO extends DAO {

    public void save(Skill skill) {
        if (skill.getId() > 0) {
            this.update(skill);
        } else {
            this.store(skill);
        }
    }

    private void store(Skill skill) {
        String sql = "INSERT INTO skills (person_id, tag_id, level_id, created_at, updated_at) values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, skill.getPerson().getId());
            stmt.setLong(2, skill.getTag().getId());
            stmt.setLong(3, skill.getLevel().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    private void update(Skill skill) {
        String sql = "" +
                "UPDATE skills SET " +
                "person_id = ?, " +
                "tag_id = ?, " +
                "level_id = ?, " +
                "updated_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, skill.getPerson().getId());
            stmt.setLong(2, skill.getTag().getId());
            stmt.setLong(3, skill.getLevel().getId());
            stmt.setLong(4, skill.getId());

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
