package br.feevale.dao;

import br.feevale.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DAO {

    public List<Member> listByProject(Project projectFilter){
        String sql = "SELECT " +
                "m.id, m.role, m.start_date, m.end_date, " +
                "m.person_id, p.name as person_name, p.enrollment_number, p.email, " +
                "m.project_id, pj.name as project_name, pj.description " +
                "FROM members m " +
                "LEFT JOIN people p ON p.id = m.person_id " +
                "LEFT JOIN projects pj ON pj.id = m.project_id " +
                "WHERE m.project_id = ? " +
                "ORDER BY p.name";

        ArrayList<Member> members = new ArrayList<Member>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, projectFilter.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setRole(rs.getString("role"));
                member.setStartDate(rs.getDate("start_date"));
                member.setEndDate(rs.getDate("end_date"));

                Person person = new Person();
                person.setId(rs.getLong("person_id"));
                person.setName(rs.getString("person_name"));
                person.setEmail(rs.getString("email"));
                person.setEnrollmentNumber(rs.getInt("enrollment_number"));

                Project project = new Project();
                project.setId(rs.getLong("id"));
                project.setName(rs.getString("project_name"));
                project.setDescription(rs.getString("description"));

                member.setPerson(person);
                member.setProject(project);

                members.add(member);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return members;
    }

}
