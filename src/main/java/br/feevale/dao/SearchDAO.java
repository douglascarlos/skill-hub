package br.feevale.dao;

import br.feevale.model.Model;
import br.feevale.model.Person;
import br.feevale.model.Project;
import br.feevale.model.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO extends DAO {

    public List<Model> search(String filter){
        String sql = "SELECT DISTINCT(p.id) as id, p.name, p.email, p.enrollment_number, NULL as description, 'Person' as type\n" +
                "FROM people p\n" +
                "LEFT JOIN skills s ON s.person_id = p.id AND s.deleted_at IS NULL\n" +
                "LEFT JOIN members m ON m.person_id = p.id\n" +
                "WHERE p.deleted_at IS NULL AND (\n" +
                "\tUPPER(p.name) like UPPER('%" + filter + "%') \n" +
                " \tOR s.tag_id IN (SELECT id\n" +
                " \t\t\t\t\tFROM tags\n" +
                " \t\t\t\t\tWHERE deleted_at IS NULL \n" +
                " \t\t\t\t\tAND UPPER(name) LIKE UPPER('%" + filter + "%'))\n" +
                "\tOR m.project_id IN (SELECT id\n" +
                "\t\t\t\t\t\tFROM projects \n" +
                "\t\t\t\t\t\tWHERE deleted_at IS NULL \n" +
                "\t\t\t\t\t\tAND UPPER(name) like UPPER('%" + filter + "%'))\n" +
                ")\n" +
                "UNION\n" +
                "SELECT DISTINCT(t.id) as id, t.name, '' as email, 0 as enrollment_number, '' as description, 'Tag' as type\n" +
                "FROM tags t\n" +
                "LEFT JOIN skills s ON s.tag_id = t.id AND s.deleted_at IS NULL\n" +
                "LEFT JOIN member_skill ms ON ms.skill_id = s.id \n" +
                "LEFT JOIN members m ON m.id = ms.member_id \n" +
                "WHERE t.deleted_at IS NULL AND (\n" +
                "\tUPPER(name) LIKE UPPER('%" + filter + "%')\n" +
                "\tOR s.person_id IN (SELECT id\n" +
                "\t\t\t\t\t\tFROM people \n" +
                "\t\t\t\t\t\tWHERE deleted_at IS NULL \n" +
                "\t\t\t\t\t\tAND UPPER(name) like UPPER('%" + filter + "%'))\n" +
                "\tOR m.project_id IN (SELECT id\n" +
                "\t\t\t\t\t\tFROM projects \n" +
                "\t\t\t\t\t\tWHERE deleted_at IS NULL \n" +
                "\t\t\t\t\t\tAND UPPER(name) like UPPER('%" + filter + "%'))\n" +
                ")\n" +
                "UNION\n" +
                "SELECT DISTINCT(pj.id) as id, pj.name, '' as email, 0 as enrollment_number, pj.description, 'Project' as type\n" +
                "FROM projects pj\n" +
                "LEFT JOIN members m ON m.project_id = pj.id\n" +
                "LEFT JOIN member_skill ms ON ms.member_id = m.id\n" +
                "LEFT JOIN skills s ON s.id = ms.skill_id\n" +
                "WHERE pj.deleted_at IS NULL AND (\n" +
                "\tUPPER(pj.name) like UPPER('%" + filter + "%')\n" +
                "  \tOR m.person_id IN (SELECT id\n" +
                "  \t\t\t\t\t\tFROM people \n" +
                "  \t\t\t\t\t\tWHERE deleted_at IS NULL \n" +
                "  \t\t\t\t\t\tAND UPPER(name) like UPPER('%" + filter + "%'))\n" +
                " \tOR s.tag_id IN (SELECT id\n" +
                "  \t\t\t\t\tFROM tags\n" +
                "  \t\t\t\t\tWHERE deleted_at IS NULL \n" +
                "  \t\t\t\t\tAND UPPER(name) LIKE UPPER('%" + filter + "%'))\n" +
                ")";
        System.out.println(sql);

        ArrayList<Model> models = new ArrayList<Model>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");

                switch (type){
                    case "Tag":
                        Tag tag = new Tag();
                        tag.setId(rs.getLong("id"));
                        tag.setName(rs.getString("name"));
                        models.add(tag);
                        break;

                    case "Person":
                        Person person = new Person();
                        person.setId(rs.getLong("id"));
                        person.setName(rs.getString("name"));
                        person.setEmail(rs.getString("email"));
                        person.setEnrollmentNumber(rs.getInt("enrollment_number"));
                        models.add(person);
                        break;

                    case "Project":
                        Project project = new Project();
                        project.setId(rs.getLong("id"));
                        project.setName(rs.getString("name"));
                        project.setDescription(rs.getString("description"));
                        models.add(project);
                        break;

                    default:
                        throw new RuntimeException("Erro ao identificar Model");

                }

            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return models;
    }

}
