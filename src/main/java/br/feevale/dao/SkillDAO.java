package br.feevale.dao;

import br.feevale.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SkillDAO extends DAO {

    public List<Skill> listByPerson(Person person){
        String sql = "SELECT s.id, s.tag_id, t.name as tag_name, s.level_id, l.name as level_name, l.ordination, l.weight " +
                "FROM skills s " +
                "JOIN tags t ON t.id = s.tag_id " +
                "JOIN levels l ON l.id = s.level_id " +
                "WHERE s.deleted_at IS NULL AND s.person_id = ? " +
                "ORDER BY l.weight desc";
        ArrayList<Skill> skills = new ArrayList<Skill>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, person.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getLong("id"));

                Tag tag = new Tag();
                tag.setId(rs.getLong("tag_id"));
                tag.setName(rs.getString("tag_name"));

                Level level = new Level();
                level.setId(rs.getLong("level_id"));
                level.setName(rs.getString("level_name"));
                level.setWeight(rs.getInt("weight"));
                level.setOrdination(rs.getInt("ordination"));

                skill.setTag(tag);
                skill.setLevel(level);
                skill.setPerson(person);

                skills.add(skill);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return skills;
    }

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

    public void delete(Skill skill) {
        String sql = "" +
                "UPDATE skills SET " +
                "deleted_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, skill.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    public List<Skill> listByProject(Project project){
        String sql = "SELECT s.id, s.tag_id, t.name as tag_name, s.level_id, l.name as level_name, l.ordination, l.weight " +
                "FROM skills s " +
                "JOIN tags t ON t.id = s.tag_id " +
                "JOIN levels l ON l.id = s.level_id " +
                "JOIN member_skill ms ON ms.skill_id = s.id " +
                "JOIN members m ON m.id = ms.member_id " +
                "WHERE s.deleted_at IS NULL AND m.project_id = ? " +
                "ORDER BY l.weight desc";
        ArrayList<Skill> skills = new ArrayList<Skill>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, project.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getLong("id"));

                Tag tag = new Tag();
                tag.setId(rs.getLong("tag_id"));
                tag.setName(rs.getString("tag_name"));

                Level level = new Level();
                level.setId(rs.getLong("level_id"));
                level.setName(rs.getString("level_name"));
                level.setWeight(rs.getInt("weight"));
                level.setOrdination(rs.getInt("ordination"));

                skill.setTag(tag);
                skill.setLevel(level);
                //skill.setPerson(person);

                skills.add(skill);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return skills;
    }

    public List<Skill> listByMember(Member member){
        String sql = "SELECT s.id, s.tag_id, t.name as tag_name, s.level_id, l.name as level_name, l.ordination, l.weight " +
                "FROM skills s " +
                "JOIN tags t ON t.id = s.tag_id " +
                "JOIN levels l ON l.id = s.level_id " +
                "JOIN member_skill ms ON ms.skill_id = s.id " +
                "WHERE s.deleted_at IS NULL AND ms.member_id = ? " +
                "ORDER BY l.weight desc";
        ArrayList<Skill> skills = new ArrayList<Skill>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, member.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getLong("id"));

                Tag tag = new Tag();
                tag.setId(rs.getLong("tag_id"));
                tag.setName(rs.getString("tag_name"));

                Level level = new Level();
                level.setId(rs.getLong("level_id"));
                level.setName(rs.getString("level_name"));
                level.setWeight(rs.getInt("weight"));
                level.setOrdination(rs.getInt("ordination"));

                skill.setTag(tag);
                skill.setLevel(level);
                //skill.setPerson(person);

                skills.add(skill);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return skills;
    }

}
